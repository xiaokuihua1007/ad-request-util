package org.classmatechen.oceanengine.client.impl;

import java.util.Objects;

import org.classmatechen.oceanengine.DyContext;
import org.classmatechen.oceanengine.client.ApiClientProvider;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.Configuration;

public class ApiClientProviderImpl implements ApiClientProvider {

    private final AccessTokenProvider provider;

    public ApiClientProviderImpl(AccessTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public ApiClient getClient(DyContext context) {

        String accessToken = provider.accessToken(context);
        if (Objects.isNull(accessToken)) {
            return null;
        }
        return Configuration.getDefaultApiClient().addDefaultHeader("Access-Token", accessToken);
    }
}
