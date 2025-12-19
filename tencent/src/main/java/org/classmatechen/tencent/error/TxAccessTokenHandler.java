package org.classmatechen.tencent.error;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.pubsub.AccessTokenExpiredEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.req.retry.AbstarctExceptionHandler;
import org.classmatechen.basic.req.retry.ExceptionContext;

import com.tencent.ads.exception.TencentAdsResponseException;

public class TxAccessTokenHandler extends AbstarctExceptionHandler {

    private final List<Long> codes = Arrays.asList(11000L, 11002L, 11004L);

    @Override
    public boolean support(Exception e) {
        if (!(e instanceof TencentAdsResponseException)) {
            return false;
        }
        TencentAdsResponseException exception = (TencentAdsResponseException) e;
        return codes.contains(exception.getCode());
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
