package org.classmatechen.oceanengine;

import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.util.Platform;
import org.classmatechen.oceanengine.client.ApiClientProvider;

import com.bytedance.ads.ApiClient;
import lombok.Getter;
import lombok.ToString;

@ToString
public class DyContextImpl implements DyContext {

    /**
     * @see OceanengineInit#init
     */
    private static List<ApiClientProvider> providers;

    @Getter
    private final Long appId;

    public DyContextImpl(Long appId) {
        this.appId = appId;
    }

    @Override
    public ApiClient getClient() {

        for (ApiClientProvider provider : DyContextImpl.providers) {
            ApiClient client = provider.getClient(this);
            if (Objects.nonNull(client)) {
                return client;
            }
        }
        throw new RuntimeException("no such client");
    }

    @Override
    public Object getId() {
        return getAppId();
    }

    @Override
    public Platform getPlatform() {
        return Platform.Oceanegine;
    }
}
