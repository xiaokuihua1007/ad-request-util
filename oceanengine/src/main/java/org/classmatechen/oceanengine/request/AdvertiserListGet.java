package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.CustomerCenterAdvertiserListV2Api;
import com.bytedance.ads.model.CustomerCenterAdvertiserListV2AccountSource;
import com.bytedance.ads.model.CustomerCenterAdvertiserListV2Filtering;
import com.bytedance.ads.model.CustomerCenterAdvertiserListV2Response;
import com.bytedance.ads.model.CustomerCenterAdvertiserListV2ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取工作台下账户列表
 * https://open.oceanengine.com/labels/7/docs/1696710520884224
 */
public class AdvertiserListGet extends DyRequest<AdvertiserListGet.Param, List<CustomerCenterAdvertiserListV2ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private CustomerCenterAdvertiserListV2AccountSource accountSource;
        private Long ccAccountId;
        private CustomerCenterAdvertiserListV2Filtering filtering;
        // private Long page;
        // private Long pageSize;
    }

    @Override
    protected Response<List<CustomerCenterAdvertiserListV2ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {

        CustomerCenterAdvertiserListV2Response response = new CustomerCenterAdvertiserListV2Api(client).openApi2CustomerCenterAdvertiserListGet(
            param.getAccountSource(),
            param.getCcAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize()
        );
        return Parser.page(response, CustomerCenterAdvertiserListV2ResponseDataListInner.class);
    }
}
