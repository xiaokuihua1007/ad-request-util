package org.classmatechen.oceanengine.client;

import org.classmatechen.oceanengine.DyContext;

import com.bytedance.ads.ApiClient;

public interface ApiClientProvider {

    ApiClient getClient(DyContext context);
}
