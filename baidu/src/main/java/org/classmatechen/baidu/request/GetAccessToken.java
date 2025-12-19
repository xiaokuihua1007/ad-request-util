package org.classmatechen.baidu.request;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.AbstractRequest;
import org.classmatechen.basic.parser.Parser;

import com.baidu.dev2.api.sdk.manual.oauth.api.OAuthService;
import com.baidu.dev2.api.sdk.manual.oauth.model.AccessTokenInfo;
import com.baidu.dev2.api.sdk.manual.oauth.model.GetAccessTokenRequest;

/**
 * 换取授权令牌接口
 * https://dev2.baidu.com/content?sceneType=0&pageId=100441&nodeId=421&subhead=3.4%E3%80%81%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3
 */
public class GetAccessToken extends AbstractRequest<GetAccessTokenRequest, AccessTokenInfo> {

    @Override
    protected Response<AccessTokenInfo> doRequest(Context context, GetAccessTokenRequest param) throws Exception {
        return Parser.object(new OAuthService().getAccessToken(param), AccessTokenInfo.class);
    }
}
