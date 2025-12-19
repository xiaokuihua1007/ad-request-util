package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.combined.CombinedPage;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.PromotionListV30Api;
import com.bytedance.ads.model.PromotionListV30Filtering;
import com.bytedance.ads.model.PromotionListV30IncludingMaterialAtrributes;
import com.bytedance.ads.model.PromotionListV30Response;
import com.bytedance.ads.model.PromotionListV30ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取广告列表
 * https://open.oceanengine.com/labels/7/docs/1741028841006095
 */
public class PromotionListGet extends DyRequest<PromotionListGet.Param, List<PromotionListV30ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends CombinedPage {
        private Long advertiserId;
        private PromotionListV30Filtering filtering;
        private List<String> fields;
        private PromotionListV30IncludingMaterialAtrributes includingMaterialAtrributes;
        private Long count;
    }

    @Override
    protected Response<List<PromotionListV30ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {
        PromotionListV30Response response = new PromotionListV30Api(client).openApiV30PromotionListGet(
            param.getAdvertiserId(),
            param.getFiltering(),
            param.getFields(),
            param.getIncludingMaterialAtrributes(),
            param.getPage(),
            param.getPageSize(),
            param.getCursor(),
            param.getCount()
        );
        return Parser.page(response, PromotionListV30ResponseDataListInner.class);
    }
}
