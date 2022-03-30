import ch.qos.logback.core.util.FileSize
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

String logDirectory = System.getProperty("user.home") + File.separator +  "log_phitb_sales" + File.separator
appender("ROLLING", RollingFileAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%date{HH:mm:ss} [%level] %logger - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = logDirectory+"normal/p1-%d{yyyy-MM-dd}.log"
        maxHistory = 30
        totalSizeCap = FileSize.valueOf("100MB")
    }
}
appender("FULL_STACKTRACE", RollingFileAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%date{HH:mm:ss.SSS} [%level] %logger - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = logDirectory+"critical/p1-%d{yyyy-MM-dd}.log"
        maxHistory = 30
        totalSizeCap = FileSize.valueOf("100MB")
    }
    append=true
}
logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
root(INFO,['ROLLING'])
