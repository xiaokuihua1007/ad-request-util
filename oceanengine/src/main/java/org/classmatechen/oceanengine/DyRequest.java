package org.classmatechen.oceanengine;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;
import org.classmatechen.oceanengine.error.DyException;
import org.classmatechen.oceanengine.res.DyResponse;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;

public abstract class DyRequest<P, R> extends AbstractRequest<P, R> {

    @Override
    public Response<R> doRequest(Context context, P param) throws Exception {

        if (!(context instanceof DyContext)) {
            throw new RuntimeException("unsupport context type");
        }
        Response<R> response = todo(((DyContext) context).getClient(), param);
        if (!(response instanceof DyResponse)) {
            throw new RuntimeException("parse response error");
        }
        DyResponse<R> res = (DyResponse<R>) response;
        if (res.getCode() > 0) {
            throw new DyException(res.getCode(), res.getMessage());
        } else {
            return response;
        }
    }

    protected abstract Response<R> todo(ApiClient client, P param) throws ApiException;
}
