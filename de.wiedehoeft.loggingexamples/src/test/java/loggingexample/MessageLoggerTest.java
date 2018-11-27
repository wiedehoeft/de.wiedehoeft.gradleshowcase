package loggingexample;

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
