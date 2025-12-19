package org.classmatechen.oceanengine.request;

import com.bytedance.ads.api.Oauth2AccessTokenApi;
import com.bytedance.ads.model.Oauth2AccessTokenRequest;
import com.bytedance.ads.model.Oauth2AccessTokenResponse;
import com.bytedance.ads.model.Oauth2AccessTokenResponseData;
import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;
import org.classmatechen.oceanengine.error.DyException;
import org.classmatechen.oceanengine.res.DyResponse;

/**
 * 获取Access Token
 * https://open.oceanengine.com/labels/7/docs/1696710505596940
 */
public class AccessTokenPost extends AbstractRequest<Oauth2AccessTokenRequest, Oauth2AccessTokenResponseData> {

    @Override
    protected Response<Oauth2AccessTokenResponseData> doRequest(Context context, Oauth2AccessTokenRequest param) throws Exception {

        Oauth2AccessTokenApi accessTokenApi = new Oauth2AccessTokenApi();
        Oauth2AccessTokenResponse response = accessTokenApi.openApiOauth2AccessTokenPost(param);
        Long code = response.getCode();
        if (code != 0) {
            throw new DyException(code, response.getMessage());
        }
        return new DyResponse<Oauth2AccessTokenResponseData>(response.getData(), code, response.getMessage());
    }
}
