package org.classmatechen.tencent.client.impl.init;

import java.util.Objects;

import org.classmatechen.basic.Request;
import org.classmatechen.basic.pubsub.AccessTokenRefreshedEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.pubsub.RefreshTokenRefreshedEvent;
import org.classmatechen.tencent.TxContext;
import org.classmatechen.tencent.client.impl.AccessTokenProvider;
import org.classmatechen.tencent.request.OauthToken;

import com.tencent.ads.model.v3.OauthTokenResponseData;

public class InitAccessTokenProvider implements AccessTokenProvider {

    private TencentProvider provider;

    public InitAccessTokenProvider(TencentProvider provider) {
        this.provider = provider;
    }

    @Override
    public String accessToken(TxContext context) {

        Tencent dep = provider.tencent(context);
        if (Objects.isNull(dep)) {
            return null;
        }
        Request<OauthToken.Param, OauthTokenResponseData> request = new OauthToken();
        
        OauthToken.InitTokenParam param = new OauthToken.InitTokenParam(dep.getClientId(), dep.getClientSecret(), dep.getAuthorizationCode(), dep.getRedirectUri());
        OauthTokenResponseData response = null;
        try {
            response = request.request(null, param).getData();
        } catch (Exception e) {

        }
        if (Objects.isNull(response)) {
            return null;
        }
        Publisher.publish(new AccessTokenRefreshedEvent(context, response.getAccessToken()));
        Publisher.publish(new RefreshTokenRefreshedEvent(context, response.getRefreshToken()));
        return response.getAccessToken();
    }

    @Override
    public int getOrder() {
        return provider.getOrder();
    }
}
