package org.classmatechen.oceanengine.error;

import java.util.List;

import org.classmatechen.basic.req.retry.AbstarctExceptionHandler;

public abstract class DyExceptionHandler extends AbstarctExceptionHandler {

    @Override
    public boolean support(Exception e) {
        return e instanceof DyException && supportCode().contains(((DyException) e).getCode());
    }

    protected abstract List<Long> supportCode();
}
