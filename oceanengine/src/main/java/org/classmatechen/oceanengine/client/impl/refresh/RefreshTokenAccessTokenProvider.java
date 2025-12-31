package org.classmatechen.oceanengine.client.impl.refresh;

import java.util.Objects;

import org.classmatechen.basic.Request;
import org.classmatechen.basic.pubsub.AccessTokenRefreshedEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.pubsub.RefreshTokenRefreshedEvent;
import org.classmatechen.oceanengine.DyContext;
import org.classmatechen.oceanengine.client.impl.AccessTokenProvider;
import org.classmatechen.oceanengine.request.RefreshTokenPost;

import com.bytedance.ads.model.Oauth2RefreshTokenRequest;
import com.bytedance.ads.model.Oauth2RefreshTokenResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenAccessTokenProvider implements AccessTokenProvider {

    private final RefreshTokenProvider provider;

    public RefreshTokenAccessTokenProvider(RefreshTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public String accessToken(DyContext context) {

        RefreshDep dep = provider.refreshDep(context);
        if (Objects.isNull(dep)) {
            return null;
        }
        Request<Oauth2RefreshTokenRequest, Oauth2RefreshTokenResponseData> request = new RefreshTokenPost();
        
        Oauth2RefreshTokenRequest param = new Oauth2RefreshTokenRequest();
        param.setAppId(dep.getAppId());
        param.setSecret(dep.getSecret());
        param.setRefreshToken(dep.getRefreshToken());
        Oauth2RefreshTokenResponseData response = null;
        try {
            response = request.request(null, param).getData();
        } catch (Exception e) {
            log.error("refresh token error {}", e.getMessage());
        }
        if (Objects.isNull(response)) {
            return null;
        }

        log.info("refresh token response: {}", response);
        Publisher.publish(new AccessTokenRefreshedEvent(context, response.getAccessToken()));
        Publisher.publish(new RefreshTokenRefreshedEvent(context, response.getRefreshToken()));
        return response.getAccessToken();
    }

    @Override
    public int getOrder() {
        return provider.getOrder();
    }
}
