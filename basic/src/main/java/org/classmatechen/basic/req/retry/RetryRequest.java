package org.classmatechen.basic.req.retry;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.res.ResponseImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryRequest<P, R> implements Request<P, R> {

    // 请求
    private final Request<P, R> request;
    // 最大重试次数
    private final int maxRetryCount;
    // 当前重试次数
    private int currentRetryCount = -1;
    // 表示 null
    private final Response<R> nullable = new ResponseImpl<>(null);
    // 默认最大重试次数为 1
    private final static int DEFAULT_MAX_RETRY_COUNT = 1;

    public RetryRequest(Request<P, R> request) {
        this(request, DEFAULT_MAX_RETRY_COUNT);
    }

    public RetryRequest(Request<P, R> request, int max) {
        this.request = request;
        this.maxRetryCount = max < DEFAULT_MAX_RETRY_COUNT ? DEFAULT_MAX_RETRY_COUNT : max;
    }

    @Override
    public Response<R> request(Context context, P param) {

        currentRetryCount = -1;
        Response<R> response = nullable;
        Exception exception = null;
        while (true) {
            currentRetryCount++;
            try {
                if (currentRetryCount > 0) {
                    log.info("retry-request retry-count: {}", currentRetryCount);
                }
                response = this.request.request(context, param);
            } catch (Exception error) {
                exception = (Exception) error.getCause();
                log.warn("error occur in retry-request ... param: {} error: {}", param, exception.getMessage());
            }
            if (response != nullable) {
                break;
            }
            if (currentRetryCount >= maxRetryCount) {
                break;
            } else {
                if (!ExceptionHelper.handle(new ExceptionContext(exception, context, param, maxRetryCount, currentRetryCount))) {
                    throw new RuntimeException(exception);
                }
            }
        }
        if (response == nullable) {
            // log.error("retry over max count, error {}", exception.getMessage());
            throw new RuntimeException("retry over max count, error " + exception.getMessage());
        }
        return response;
    }
}
