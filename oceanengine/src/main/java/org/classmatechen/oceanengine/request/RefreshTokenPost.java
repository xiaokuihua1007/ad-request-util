package org.classmatechen.oceanengine.request;

import com.bytedance.ads.api.Oauth2RefreshTokenApi;
import com.bytedance.ads.model.Oauth2RefreshTokenRequest;
import com.bytedance.ads.model.Oauth2RefreshTokenResponse;
import com.bytedance.ads.model.Oauth2RefreshTokenResponseData;
import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;
import org.classmatechen.oceanengine.error.DyException;
import org.classmatechen.oceanengine.res.DyResponse;

/**
 * 刷新Refresh Token
 * https://open.oceanengine.com/labels/7/docs/1696710506097679
 */
public class RefreshTokenPost extends AbstractRequest<Oauth2RefreshTokenRequest, Oauth2RefreshTokenResponseData> {

    @Override
    protected Response<Oauth2RefreshTokenResponseData> doRequest(Context context, Oauth2RefreshTokenRequest param) throws Exception {
        Oauth2RefreshTokenRequest request = new Oauth2RefreshTokenRequest()
                .appId(param.getAppId())
                .secret(param.getSecret())
                .refreshToken(param.getRefreshToken());

        Oauth2RefreshTokenResponse response = new Oauth2RefreshTokenApi().openApiOauth2RefreshTokenPost(request);
        Long code = response.getCode();
        if (code != 0) {
            throw new DyException(code, response.getMessage());
        }
        return new DyResponse<Oauth2RefreshTokenResponseData>(response.getData(), code, response.getMessage());
    }
}
