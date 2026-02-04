package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.DpaDetailGetV2Api;
import com.bytedance.ads.model.DpaDetailGetV2Filtering;
import com.bytedance.ads.model.DpaDetailGetV2Response;
import com.bytedance.ads.model.DpaDetailGetV2ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取商品列表
 * https://open.oceanengine.com/labels/34/docs/1696710578078732
 */
@Deprecated
public class DpaDetailGetGet extends DyRequest<DpaDetailGetGet.Param, List<DpaDetailGetV2ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
        private Long advertiserId;
        private Long productPlatformId;
        private DpaDetailGetV2Filtering filtering;
    }

    @Override
    protected Response<List<DpaDetailGetV2ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {

        DpaDetailGetV2Response response = new DpaDetailGetV2Api(client).openApi2DpaDetailGetGet(
            param.getAdvertiserId(),
            param.getProductPlatformId(),
            param.getFiltering(),
            param.getPage().intValue(),
            param.getPageSize().intValue()
        );
        return Parser.page(response, DpaDetailGetV2ResponseDataListInner.class);
    }
}
