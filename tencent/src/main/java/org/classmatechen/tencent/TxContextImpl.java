package org.classmatechen.tencent;

import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.util.Platform;
import org.classmatechen.tencent.client.TencentAdsProvider;

import com.tencent.ads.v3.TencentAds;

import lombok.Getter;
import lombok.ToString;

@ToString
public class TxContextImpl implements TxContext {

    /**
     * @see TencentInit#init
     */
    private static List<TencentAdsProvider> providers;

    @Getter
    private final Long clientId;

    public TxContextImpl(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public TencentAds getClient() {

        for (TencentAdsProvider provider : TxContextImpl.providers) {
            TencentAds client = provider.getClient(this);
            if (Objects.nonNull(client)) {
                return client;
            }
        }
        throw new RuntimeException("no such client");
    }

    @Override
    public Object getId() {
        return getClientId();
    }

    @Override
    public Platform getPlatform() {
        return Platform.Tencent;
    }
}
