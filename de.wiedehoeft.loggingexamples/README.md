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
    I am at info level!
    I am at warn level!
    I am at error level!
    ``` 

# Analyze and customize the property file
TBD
   
