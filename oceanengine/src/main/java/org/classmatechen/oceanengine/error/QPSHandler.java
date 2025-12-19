package org.classmatechen.oceanengine.error;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.req.retry.ExceptionContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QPSHandler extends DyExceptionHandler {
    
    private final long millis;

    public QPSHandler() {
        this(1000l);
    }

    public QPSHandler(long millis) {
        this.millis = millis;
    }

    @Override
    protected List<Long> supportCode() {
        return Arrays.asList(40130L, 40110L);
    }

    @Override
    public void handle(ExceptionContext context) {
        try {
            log.info("qps occur sleep ...");
            Thread.sleep(this.millis);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
