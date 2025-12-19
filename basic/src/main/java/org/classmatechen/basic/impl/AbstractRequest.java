package org.classmatechen.basic.impl;

import java.util.Objects;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.Response;

/**
 * 1. 校验参数
 * 2. 如果请求产生异常, 包装为运行时异常并抛出
 */
public abstract class AbstractRequest<P, R> implements Request<P, R> {

    @Override
    public Response<R> request(Context context, P param) {

        checkParam(param);
        Response<R> response;
        try {
            response = doRequest(context, param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private void checkParam(P param) {

        if (Objects.nonNull(param)) {
            if (param instanceof CheckAble) {
                String error = ((CheckAble) param).check();
                if (Objects.nonNull(error) && error.length() > 0) {
                    throw new RuntimeException(error);
                }
            }
        }
    }

    protected abstract Response<R> doRequest(Context context, P param) throws Exception;
}
