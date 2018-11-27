# Add logger to application
1) We use slf4j as abstraction layer 
    ```
    dependencies {
        compile(
                'org.slf4j:slf4j-api:1.7.25'
        )
    }
    ```
2) Use Logger in your code (JUnit 5 Test)
    ```
    import org.junit.jupiter.api.Test;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    
    public class MessageLoggerTest {
    
        private static final Logger logger = LoggerFactory.getLogger(MessageLoggerTest.class);
    
        @Test
        void logHelloWorld() {
            logger.trace("I am at trace level!");
            logger.debug("I am at debug level!");
            logger.info("I am at info level!");
            logger.warn("I am at warn level!");
            logger.error("I am at error level!");
        }
    }
    ```
3) This will result in following error while execution
    ```
    SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    SLF4J: Defaulting to no-operation (NOP) logger implementation
    SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
    ```
4) We are currently missing a framework implementing slf4j-api. Let us use log4j2. 
   Update the `build.gradle`
   ```
   compile(
               'org.slf4j:slf4j-api:1.7.25',
               'org.apache.logging.log4j:log4j-slf4j-impl:2.11.1'
       )
   ```
5) The next execution results in
    ```
    ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
    10:51:26.539 [main] ERROR loggingexample.MessageLoggerTest - I am at error level!
    Process finished with exit code 0
    ```
    Because the application is looking for a log4j2 config file, we know the included
    framework was taken. 
6) Create a logging-property file. We will place it in src/test/resources, so our
   tests will be enable to log, too. Starting with original example from
   https://logging.apache.org/log4j/2.x/manual/configuration.html
   
   ```
   status = error
   dest = err
   name = PropertiesConfig
    
   property.filename = target/rolling/rollingtest.log
    
   filter.threshold.type = ThresholdFilter
   filter.threshold.level = debug
    
   appender.console.type = Console
   appender.console.name = STDOUT
   appender.console.layout.type = PatternLayout
   appender.console.layout.pattern = %m%n
   appender.console.filter.threshold.type = ThresholdFilter
   appender.console.filter.threshold.level = error
    
   appender.rolling.type = RollingFile
   appender.rolling.name = RollingFile
   appender.rolling.fileName = ${filename}
   appender.rolling.filePattern = target/rolling2/test1-%d{MM-dd-yy-HH-mm-ss}-%i.log.gz
   appender.rolling.layout.type = PatternLayout
   appender.rolling.layout.pattern = %d %p %C{1.} [%t] %m%n
   appender.rolling.policies.type = Policies
   appender.rolling.policies.time.type = TimeBasedTriggeringPolicy
   appender.rolling.policies.time.interval = 2
   appender.rolling.policies.time.modulate = true
   appender.rolling.policies.size.type = SizeBasedTriggeringPolicy
   appender.rolling.policies.size.size=100MB
   appender.rolling.strategy.type = DefaultRolloverStrategy
   appender.rolling.strategy.max = 5
    
   logger.rolling.name = com.example.my.app
   logger.rolling.level = debug
   logger.rolling.additivity = false
   logger.rolling.appenderRef.rolling.ref = RollingFile
    
   rootLogger.level = info
   rootLogger.appenderRef.stdout.ref = STDOUT
   ```

7) Running tests again results in:
    ```
    I am at error level!
    ``` 

# Analyze and customize the property file

## General structure
A property file can contain following properties:
* some basic information like: name
* appender
* logger
* rootLogger
* filter

Every property gets a name when it is defined, e.g.

`appender.<name>`

Next the attributes for the current property are defined, e.g. for log level

`appender.<name>.level`

## Questions
1) Why are only error events shown in console?
    Because we currently did not define a logger property for our own packages. That means our logging output
    will be assigned to rootLogger with Log-Level: Info. But this does not result in info messages logged, right?
    Caused by the threshold filter defined for the console appender, all output except error messages will not be
    printed on console. After removing it the output is:
    ```
    I am at debug level!
    I am at info level!
    I am at warn level!
    I am at error level!
    ```
    Still missing the trace message...
    The file contains a root Threshold-Filter on debug level. So trace messages are currently filtered.
    Remove it, too and result is:
    ```
    I am at trace level!
    I am at debug level!
    I am at info level!
    I am at warn level!
    I am at error level!
    ```
    As expected.


2) How can we add a more specific log level for our own classes/packages and/or log to defined rolling file?
    For this we should add a custom logger, for our own packages.
    ```
    logger.rolling.name = loggingexample
    logger.rolling.level = trace
    logger.rolling.additivity = false
    logger.rolling.appenderRef.rolling.ref = RollingFile
    logger.rolling.appenderRef.stdout.ref = STDOUT
    ```
    Now the log output will be written to file and console and we can decide which log level we want to use.
    Careful: Threshold filters like defined before would filter these results, too!

3) How to define different logLevels for console and file?
    Just add a different log level for the file appender:
    `logger.rolling.appenderRef.rolling.level = info`


4) How could we add a custom log pattern to our output?
See https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout for further explanation.
Try to print the fully qualified class name in file log output.

5) How can we add thread information to our output?
    Let us mess up our test class
    ```
        @Test
        void logHelloWorld() {
           Runnable runnable = () -> logger.info("I am at info level!");
           inThreadContext(runnable);
   
           Runnable runnable0 = () -> {
               logger.trace("I am at trace level!");
               logger.debug("I am at debug level!");
               logger.info("I am at info level!");
               logger.warn("I am at warn level!");
               logger.error("I am at error level!");
           };
           inThreadContext(runnable0);
   
           Runnable runnable2 = () -> logger.error("I am at error level!");
           inThreadContext(runnable2);
        }
    ```
    Now we are no longer able to get needed context information, to see what thread created which output.
    A typical scenario in a multi user application, like a webservice.
    Log4j2 uses Map Diagnostic Context for this scenario, see https://logging.apache.org/log4j/2.x/manual/thread-context.html.
    The missing method could be look like this (Thread context is imported from apache.logging.log4j):
    ```
     private void inThreadContext(Runnable runnable) throws InterruptedException {
            ThreadContext.put("Thread", threadIdentifier + "");
            new Thread(runnable).start();
            ThreadContext.clearAll();
            threadIdentifier++;
     }
    ```
    The constant variable is
    ` private static long threadIdentifier;`

6) How can we lazy log with lambdas?
    Let us add a new Runnable
    ```
     Runnable runnable3 = () -> {
        logger.debug("Any expensive operation {}", notAlwaysExecuteMe());
    };
    inThreadContext(runnable3);
    ```
    The new method is
    ```
    private String notAlwaysExecuteMe() {
        logger.info("Method called!");
        return "Hello from debug logger";
    }
    ```
    Change the log level for console to info. Then we will see that the method will be called,
    although we are currently not interested in debug message. An earlier solution was to write
    declarations like this:
    ```
    if(logger.isDebugEnabled()) {
       ...
    }
    ```
    This results in messy code which is harder to read, because there are more information, you are not
    interested.
    Since Java 8 there is a better solution:
    ```
    Runnable runnable3 = () -> {
        logger.debug("Any expensive operation {}", () -> notAlwaysExecuteMe());
    };
    inThreadContext(runnable3);
    ```
    The drawback is that there is currently no slf4j support, so we need to use the log4j2 logger.
    ```
    import org.apache.logging.log4j.Logger;
    import org.apache.logging.log4j.ThreadContext;
    
    public class MessageLoggerTest {
        private static final Logger logger = LogManager.getLogger(MessageLoggerTest.class);
        ...
    }
    ```
    
7) Customize third party libraries log output.
    Just be sure that logger name matches package name of library:
    ```
    logger.flyway.name=org.flywaydb
    logger.flyway.level = warn
    logger.flyway.additivity = false
    logger.flyway.appenderRef.stdout.ref = STDOUT
    ```