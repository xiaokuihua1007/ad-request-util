package org.classmatechen.tencent.client.impl.refresh;

import java.util.Objects;

import org.classmatechen.basic.Request;
import org.classmatechen.basic.pubsub.AccessTokenRefreshedEvent;
import org.classmatechen.basic.pubsub.Publisher;
import org.classmatechen.tencent.TxContext;
import org.classmatechen.tencent.client.impl.AccessTokenProvider;
import org.classmatechen.tencent.request.OauthToken;
import com.tencent.ads.model.v3.OauthTokenResponseData;

public class RefreshTokenAccessTokenProvider implements AccessTokenProvider {

    private final RefreshTokenProvider provider;

    public RefreshTokenAccessTokenProvider(RefreshTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public String accessToken(TxContext context) {

        RefreshDep dep = provider.refreshDep(context);
        if (Objects.isNull(dep)) {
            return null;
        }
        Request<OauthToken.Param, OauthTokenResponseData> request = new OauthToken();
        
        OauthToken.RefreshTokenParam param = new OauthToken.RefreshTokenParam(dep.getClientId(), dep.getClientSecret(), dep.getRefreshToken(), dep.getRedirectUri());
        OauthTokenResponseData response = null;
        try {
            response = request.request(null, param).getData();
        } catch (Exception e) {

        }
        if (Objects.isNull(response)) {
            return null;
        }
        Publisher.publish(new AccessTokenRefreshedEvent(context, response.getAccessToken()));
        return response.getAccessToken();
    }

    @Override
    public int getOrder() {
        return provider.getOrder();
    }
}
