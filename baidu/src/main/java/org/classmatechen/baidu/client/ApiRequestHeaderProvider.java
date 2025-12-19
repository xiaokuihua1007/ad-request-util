package org.classmatechen.baidu.client;

import org.classmatechen.baidu.BdContext;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;

public interface ApiRequestHeaderProvider {

    ApiRequestHeader getClient(BdContext context);
}
