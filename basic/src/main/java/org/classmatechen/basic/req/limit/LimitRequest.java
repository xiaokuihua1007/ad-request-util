package org.classmatechen.basic.req.limit;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LimitRequest<P, R> implements Request<P, R> {

    // 请求
    private final Request<P, R> request;
    private final long millis;

    public LimitRequest(Request<P, R> request) {
        this(request, 1000l);
    }

    public LimitRequest(Request<P, R> request, long millis) {
        this.request = request;
        this.millis = millis;
    }

    @Override
    public Response<R> request(Context context, P param) {
        try {
            Thread.sleep(this.millis);
        } catch (InterruptedException e) {

        }
        Response<R> response = request.request(context, param);
        return response;
    }
}
