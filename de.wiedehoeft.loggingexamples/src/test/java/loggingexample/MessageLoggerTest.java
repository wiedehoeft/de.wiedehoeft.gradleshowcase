package loggingexample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;

public class MessageLoggerTest {

    private static final Logger logger = LogManager.getLogger(MessageLoggerTest.class);
    private static long threadIdentifier;

    @Test
    void logHelloWorld() throws InterruptedException {
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

        Runnable runnable3 = () -> {
            logger.debug("Any expensive operation {}", () -> notAlwaysExecuteMe());
        };
        inThreadContext(runnable3);
    }

    @Test
    void executeFlyMigration() {
        Flyway.configure().dataSource("jdbc:h2:~/test", "sa", "sa").load().migrate();
    }

    private String notAlwaysExecuteMe() {
        logger.info("Method called!");
        return "Hello from debug logger";
    }

    private void inThreadContext(Runnable runnable) throws InterruptedException {
        ThreadContext.put("Thread", threadIdentifier + "");
        new Thread(runnable).start();
        ThreadContext.clearAll();
        threadIdentifier++;
    }
}
