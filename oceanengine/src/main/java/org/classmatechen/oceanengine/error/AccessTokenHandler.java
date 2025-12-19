package org.classmatechen.oceanengine.error;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.pubsub.AccessTokenExpiredEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.req.retry.ExceptionContext;

public class AccessTokenHandler extends DyExceptionHandler {

    @Override
    protected List<Long> supportCode() {
        return Arrays.asList(40102L, 40105L);
    }

    @Override
    public void handle(ExceptionContext context) {
        Publisher.publish(new AccessTokenExpiredEvent(context.getContext()));
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
