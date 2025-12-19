package org.classmatechen.oceanengine.client.impl.init;

import java.util.Objects;

import org.classmatechen.basic.Request;
import org.classmatechen.basic.pubsub.AccessTokenRefreshedEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.pubsub.RefreshTokenRefreshedEvent;
import org.classmatechen.oceanengine.DyContext;
import org.classmatechen.oceanengine.client.impl.AccessTokenProvider;
import org.classmatechen.oceanengine.request.AccessTokenPost;
import com.bytedance.ads.model.Oauth2AccessTokenRequest;
import com.bytedance.ads.model.Oauth2AccessTokenResponseData;

public class InitAccessTokenProvider implements AccessTokenProvider {

    private OceanengineProvider provider;

    public InitAccessTokenProvider(OceanengineProvider provider) {
        this.provider = provider;
    }

    @Override
    public String accessToken(DyContext context) {

        Oceanengine oceanengine = provider.oceanengine(context);
        if (Objects.isNull(oceanengine)) {
            return null;
        }
        Request<Oauth2AccessTokenRequest, Oauth2AccessTokenResponseData> request = new AccessTokenPost();
        
        Oauth2AccessTokenRequest param = new Oauth2AccessTokenRequest();
        param.setAppId(oceanengine.getAppId());
        param.setSecret(oceanengine.getSecret());
        param.setAuthCode(oceanengine.getAuthCode());
        Oauth2AccessTokenResponseData response = null;
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
