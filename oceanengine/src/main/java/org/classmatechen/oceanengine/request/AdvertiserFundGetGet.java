package org.classmatechen.oceanengine.request;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.AdvertiserFundGetV2Api;
import com.bytedance.ads.model.AdvertiserFundGetV2GrantTypeSplit;
import com.bytedance.ads.model.AdvertiserFundGetV2Response;
import com.bytedance.ads.model.AdvertiserFundGetV2ResponseData;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

import lombok.Data;

/**
 * 查询账号余额
 * https://open.oceanengine.com/labels/7/docs/1696710526192652
 */
public class AdvertiserFundGetGet extends DyRequest<AdvertiserFundGetGet.Param, AdvertiserFundGetV2ResponseData> {

    @Data
    public static class Param {
    
        private Long advertiserId;
        private AdvertiserFundGetV2GrantTypeSplit grantTypeSplit;
    }

    @Override
    protected Response<AdvertiserFundGetV2ResponseData> todo(ApiClient client, Param param)
            throws ApiException {

        AdvertiserFundGetV2Response response = new AdvertiserFundGetV2Api(client).openApi2AdvertiserFundGetGet(
            param.getAdvertiserId(),
            param.getGrantTypeSplit()
        );
        return Parser.object(response, AdvertiserFundGetV2ResponseData.class);
    }
}
