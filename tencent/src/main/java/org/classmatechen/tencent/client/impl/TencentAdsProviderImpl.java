package org.classmatechen.tencent.client.impl;

import java.util.Objects;

import org.classmatechen.tencent.TxContext;
import org.classmatechen.tencent.client.TencentAdsProvider;

import com.tencent.ads.ApiContextConfig;
import com.tencent.ads.v3.TencentAds;

public class TencentAdsProviderImpl implements TencentAdsProvider {

    private final AccessTokenProvider provider;

    public TencentAdsProviderImpl(AccessTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public TencentAds getClient(TxContext context) {

        String accessToken = provider.accessToken(context);
        if (Objects.isNull(accessToken)) {
            return null;
        }
        TencentAds tencentAds = TencentAds.getInstance();
        ApiContextConfig apiContextConfig = new ApiContextConfig().isDebug(false);
        apiContextConfig.accessToken(accessToken);
        tencentAds.init(apiContextConfig);

        return tencentAds;
    }
}
