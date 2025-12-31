package org.classmatechen.baidu.client.impl.refresh;

import java.util.Objects;

import org.classmatechen.baidu.BdContext;
import org.classmatechen.baidu.client.impl.AccessTokenProvider;
import org.classmatechen.baidu.request.RefreshToken;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.pubsub.AccessTokenRefreshedEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.pubsub.RefreshTokenRefreshedEvent;

import com.baidu.dev2.api.sdk.manual.oauth.model.AccessTokenInfo;
import com.baidu.dev2.api.sdk.manual.oauth.model.RefreshTokenRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenAccessTokenProvider implements AccessTokenProvider {

    private final RefreshTokenProvider provider;

    public RefreshTokenAccessTokenProvider(RefreshTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public String accessToken(BdContext context) {

        RefreshDep dep = provider.refreshDep(context);
        if (Objects.isNull(dep)) {
            return null;
        }
        Request<RefreshTokenRequest, AccessTokenInfo> request = new RefreshToken();
        
        RefreshTokenRequest param = new RefreshTokenRequest();
        param.setAppId(dep.getAppId());
        param.setRefreshToken(dep.getRefreshToken());
        param.setSecretKey(dep.getSecretKey());
        param.setUserId(dep.getUserId());
        AccessTokenInfo response = null;
        try {
            response = request.request(null, param).getData();
        } catch (Exception e) {

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
