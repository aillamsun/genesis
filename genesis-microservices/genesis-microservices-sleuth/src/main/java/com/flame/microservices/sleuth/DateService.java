package com.flame.microservices.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by sungang on 2017/1/5.
 */
@Service
public class DateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateService.class);

    @Autowired
    private Tracer tracer;

    String getTimeAsString() {

        Span dateServiceSpan = this.tracer.createSpan("dateService");

        try {
            LOGGER.info("Returning time as String");
            dateServiceSpan.logEvent("This is my date service event");
            return LocalDateTime.now().toString();
        } finally {
            this.tracer.close(dateServiceSpan);
        }
    }
}
