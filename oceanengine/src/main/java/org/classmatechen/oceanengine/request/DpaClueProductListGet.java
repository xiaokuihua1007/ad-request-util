package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.DpaClueProductListV2Api;
import com.bytedance.ads.model.DpaClueProductListV2Filtering;
import com.bytedance.ads.model.DpaClueProductListV2Response;
import com.bytedance.ads.model.DpaClueProductListV2ResponseDataProductsInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取升级版商品列表
 * https://open.oceanengine.com/labels/34/docs/1779430442685440
 */
public class DpaClueProductListGet extends DyRequest<DpaClueProductListGet.Param, List<DpaClueProductListV2ResponseDataProductsInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long advertiserId;
        private DpaClueProductListV2Filtering filtering;
    }

    @Override
    protected Response<List<DpaClueProductListV2ResponseDataProductsInner>> todo(ApiClient client, Param param) throws ApiException {

        DpaClueProductListV2Response response = new DpaClueProductListV2Api(client).openApi2DpaClueProductListGet(
            param.getAdvertiserId(),
            param.getPage(),
            param.getPageSize(),
            param.getFiltering()
        );
        return Parser.page(response, DpaClueProductListV2ResponseDataProductsInner.class);
    }
}
