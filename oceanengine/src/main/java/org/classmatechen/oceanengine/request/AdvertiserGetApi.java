package org.classmatechen.oceanengine.request;

import java.util.List;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.Oauth2AdvertiserGetApi;
import com.bytedance.ads.model.Oauth2AdvertiserGetResponse;
import com.bytedance.ads.model.Oauth2AdvertiserGetResponseDataListInner;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

/**
 * 获取已授权账户
 * https://open.oceanengine.com/labels/7/docs/1696710506574848
 */
public class AdvertiserGetApi extends DyRequest<String, List<Oauth2AdvertiserGetResponseDataListInner>> {

    @Override
    protected Response<List<Oauth2AdvertiserGetResponseDataListInner>> todo(ApiClient client, String param)
            throws ApiException {

        Oauth2AdvertiserGetResponse response = new Oauth2AdvertiserGetApi(client).openApiOauth2AdvertiserGetGet(param);
        return Parser.list(response, Oauth2AdvertiserGetResponseDataListInner.class);
    }
}
