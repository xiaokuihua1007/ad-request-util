package org.classmatechen.oceanengine.request;

import java.util.List;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.AdvertiserInfoV2Api;
import com.bytedance.ads.model.AdvertiserInfoV2Response;
import com.bytedance.ads.model.AdvertiserInfoV2ResponseData;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

import lombok.Data;

/**
 * 获取广告主信息
 * https://open.oceanengine.com/labels/7/docs/1696710508983311
 */
public class AdvertiserInfoGet extends DyRequest<AdvertiserInfoGet.Param, List<AdvertiserInfoV2ResponseData>> {

    @Data
    public static class Param {

        private List<Long> advertiserIds;
        private List<String> fields;
    }

    @Override
    protected Response<List<AdvertiserInfoV2ResponseData>> todo(ApiClient client, Param param) throws ApiException {

        AdvertiserInfoV2Response response = new AdvertiserInfoV2Api(client)
            .openApi2AdvertiserInfoGet(
                param.getAdvertiserIds(),
                param.getFields()
            );
        return Parser.list(response, AdvertiserInfoV2ResponseData.class);
    }
}
