package org.classmatechen.baidu.client.impl.init;

import java.util.Objects;

import org.classmatechen.baidu.BdContext;
import org.classmatechen.baidu.client.impl.AccessTokenProvider;
import org.classmatechen.baidu.request.GetAccessToken;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.pubsub.AccessTokenRefreshedEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.basic.pubsub.RefreshTokenRefreshedEvent;

import com.baidu.dev2.api.sdk.manual.oauth.model.AccessTokenInfo;
import com.baidu.dev2.api.sdk.manual.oauth.model.GetAccessTokenRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitAccessTokenProvider implements AccessTokenProvider {

    private BaiduProvider provider;

    public InitAccessTokenProvider(BaiduProvider provider) {
        this.provider = provider;
    }

    @Override
    public String accessToken(BdContext context) {

        Baidu dep = provider.baidu(context);
        if (Objects.isNull(dep)) {
            return null;
        }
        Request<GetAccessTokenRequest, AccessTokenInfo> request = new GetAccessToken();
        
        GetAccessTokenRequest param = new GetAccessTokenRequest();
        param.setAppId(dep.getAppId());
        param.setAuthCode(dep.getAuthCode());
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
