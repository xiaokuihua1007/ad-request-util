package org.classmatechen.baidu;

import java.util.List;
import java.util.Objects;

import org.classmatechen.baidu.client.ApiRequestHeaderProvider;
import org.classmatechen.basic.util.Platform;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;

import lombok.Getter;
import lombok.ToString;

@ToString
public class BdContextImpl implements BdContext {

    /**
     * @see BaiduInit#init
     */
    private static List<ApiRequestHeaderProvider> providers;

    @Getter
    private final String appId;

    public BdContextImpl(String appId) {
        this.appId = appId;
    }

    @Override
    public ApiRequestHeader getHeader() {

        for (ApiRequestHeaderProvider provider : BdContextImpl.providers) {
            ApiRequestHeader header = provider.getClient(this);
            if (Objects.nonNull(header)) {
                return header;
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
        return Platform.Biadu;
    }
}
