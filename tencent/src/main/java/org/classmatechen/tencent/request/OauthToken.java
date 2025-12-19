package org.classmatechen.tencent.request;

import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;
import org.classmatechen.basic.impl.CheckAble;
import org.classmatechen.basic.res.ResponseImpl;
import com.tencent.ads.ApiContextConfig;
import com.tencent.ads.Pair;
import com.tencent.ads.model.v3.OauthTokenResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通过 Authorization Code 获取 Access Token 或刷新 Access Token
 * https://developers.e.qq.com/v3.0/docs/api/oauth/token
 */
public class OauthToken extends AbstractRequest<OauthToken.Param, OauthTokenResponseData> {

    @Getter
    @AllArgsConstructor
    public static class Param {

        private Long clientId;
        private String clientSecret;
        private String grantType;
        private String authorizationCode;
        private String refreshToken;
        private String redirectUri;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Getter
    public static class InitTokenParam extends Param implements CheckAble {

        public InitTokenParam(Long clientId, String clientSecret, String authorizationCode, String redirectUri) {
            super(clientId, clientSecret, "authorization_code", authorizationCode, null, redirectUri, null, null);
        }

        @Override
        public String check() {
            if (Objects.isNull(getClientId()) || Objects.isNull(getClientSecret()) || Objects.isNull(getAuthorizationCode()) || Objects.isNull(getRedirectUri())) {
                return "参数错误:clientId clientSecret authorizationCode redirectUri不能为空";
            }
            return null;
        }
    }

    @Getter
    public static class RefreshTokenParam extends Param implements CheckAble {

        public RefreshTokenParam(Long clientId, String clientSecret, String refreshToken, String redirectUri) {
            super(clientId, clientSecret, "refresh_token", null, refreshToken, redirectUri, null, null);
        }

        @Override
        public String check() {
            if (Objects.isNull(getClientId()) || Objects.isNull(getClientSecret()) || Objects.isNull(getRefreshToken()) || Objects.isNull(getRedirectUri())) {
                return "参数错误:clientId clientSecret refreshToken redirectUri不能为空";
            }
            return null;
        }
    }

    @Override
    protected Response<OauthTokenResponseData> doRequest(Context context, Param param)
            throws Exception {

        TencentAds tencentAds = TencentAds.getInstance();
        tencentAds.init((new ApiContextConfig()).isDebug(false));

        OauthTokenResponseData response = tencentAds.oauth().oauthToken(
            param.getClientId(),
            param.getClientSecret(),
            param.getGrantType(),
            param.getAuthorizationCode(),
            param.getRefreshToken(),
            param.getRedirectUri(),
            param.getFields(),
            param.getHeaderPair()
        );
        return new ResponseImpl<>(response);
    }
}
