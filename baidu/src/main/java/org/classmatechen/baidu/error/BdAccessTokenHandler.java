package org.classmatechen.baidu.error;

import org.classmatechen.basic.pubsub.AccessTokenExpiredEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.req.retry.AbstarctExceptionHandler;
import org.classmatechen.basic.req.retry.ExceptionContext;

public class BdAccessTokenHandler extends AbstarctExceptionHandler {

    @Override
    public boolean support(Exception e) {
        return e instanceof BdAccessTokenError;
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
