package org.classmatechen.baidu;


import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;
import com.baidu.dev2.api.sdk.invoke.ApiException;

public abstract class BdRequest<P, R> extends AbstractRequest<P, R> {

    @Override
    protected Response<R> doRequest(Context context, P param) throws Exception {

        if (!(context instanceof BdContext)) {
            throw new RuntimeException("unsupport context type");
        }
        return todo(((BdContext) context).getHeader(), param);
    }

    protected abstract Response<R> todo(ApiRequestHeader header, P param) throws ApiException;
}
