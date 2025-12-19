package org.classmatechen.oceanengine.error;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.pubsub.RefreshTokenExpiredEvent;
import org.classmatechen.basic.req.retry.ExceptionContext;

public class RefreshTokenHandler extends DyExceptionHandler {

    @Override
    protected List<Long> supportCode() {
        return Arrays.asList(40103L);
    }

    @Override
    public void handle(ExceptionContext context) {
        Publisher.publish(new RefreshTokenExpiredEvent(context.getContext()));
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
