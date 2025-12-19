package org.classmatechen.oceanengine;

import org.classmatechen.basic.Context;

import com.bytedance.ads.ApiClient;

public interface DyContext extends Context {

    Long getAppId();

    ApiClient getClient();
}
