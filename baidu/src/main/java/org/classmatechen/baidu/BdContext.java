package org.classmatechen.baidu;

import org.classmatechen.basic.Context;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;

public interface BdContext extends Context {

    String getAppId();

    ApiRequestHeader getHeader();
}
