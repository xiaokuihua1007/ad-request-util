package org.classmatechen.tencent;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;

import com.tencent.ads.ApiException;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.v3.TencentAds;

public abstract class TxRequest<P, R> extends AbstractRequest<P, R> {

    @Override
    public Response<R> doRequest(Context context, P param) throws Exception {

        if (!(context instanceof TxContext)) {
            throw new RuntimeException("unsupport context type");
        }
        return todo(((TxContext) context).getClient(), param);
    }

    protected abstract Response<R> todo(TencentAds client, P param) throws ApiException, TencentAdsResponseException;
}
