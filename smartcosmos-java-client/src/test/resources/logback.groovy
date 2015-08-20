import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.DEBUG

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.FileAppender

appender("FILE", FileAppender) {
  file = "target/logistic-service-demo.log"
  append = false
  encoder(PatternLayoutEncoder) {
      pattern = "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"
    }
}

root(DEBUG, ["FILE"])