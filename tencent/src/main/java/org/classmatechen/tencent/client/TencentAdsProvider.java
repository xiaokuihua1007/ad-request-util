package org.classmatechen.tencent.client;

import org.classmatechen.tencent.TxContext;

import com.tencent.ads.v3.TencentAds;

public interface TencentAdsProvider {

    TencentAds getClient(TxContext context);
}
