package org.classmatechen.baidu.client.impl;

import java.util.Objects;

import org.classmatechen.baidu.BdContext;
import org.classmatechen.baidu.client.ApiRequestHeaderProvider;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;

public class ApiRequestHeaderProviderImpl implements ApiRequestHeaderProvider {

    private final AccessTokenProvider provider;

    public ApiRequestHeaderProviderImpl(AccessTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public ApiRequestHeader getClient(BdContext context) {

        String accessToken = provider.accessToken(context);
        if (Objects.isNull(accessToken)) {
            return null;
        }
        ApiRequestHeader apiRequestHeader = new ApiRequestHeader();
        apiRequestHeader.setUserName(null);
        apiRequestHeader.setAccessToken(accessToken);

        return apiRequestHeader;
    }
}
