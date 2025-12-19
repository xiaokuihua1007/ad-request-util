package org.classmatechen.oceanengine.request;

import java.util.List;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.PromotionCostProtectStatusGetV30Api;
import com.bytedance.ads.model.PromotionCostProtectStatusGetV30Response;
import com.bytedance.ads.model.PromotionCostProtectStatusGetV30ResponseDataCompensateStatusInfoListInner;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

import lombok.Data;

/**
 * 批量获取广告成本保障状态
 * https://open.oceanengine.com/labels/7/docs/1755355980850191
 */
public class PromotionCostProtectStatusGetGet extends DyRequest<PromotionCostProtectStatusGetGet.Param, List<PromotionCostProtectStatusGetV30ResponseDataCompensateStatusInfoListInner>> {

    @Data
    public static class Param {

        private Long advertiserId;
        private List<Long> promotionIds;
    }

    @Override
    protected Response<List<PromotionCostProtectStatusGetV30ResponseDataCompensateStatusInfoListInner>> todo(ApiClient client, Param param) throws ApiException {

        PromotionCostProtectStatusGetV30Response response = new PromotionCostProtectStatusGetV30Api(client).openApiV30PromotionCostProtectStatusGetGet(
            param.getAdvertiserId(),
            param.getPromotionIds()
        );
        return Parser.list(response, PromotionCostProtectStatusGetV30ResponseDataCompensateStatusInfoListInner.class);
    }
}
