package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AdContext;
import com.tencent.ads.model.v3.ComponentDetailFilteringStruct;
import com.tencent.ads.model.v3.ComponentDetailGetListStruct;
import com.tencent.ads.model.v3.ComponentDetailGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取创意组件详情
 * https://developers.e.qq.com/v3.0/docs/api/component_detail/get
 */
public class ComponentDetailGet extends TxRequest<ComponentDetailGet.Param, List<ComponentDetailGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private List<ComponentDetailFilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private Long organizationId;
        private AdContext adContext;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<ComponentDetailGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        ComponentDetailGetResponseData response = client.componentDetail().componentDetailGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getOrganizationId(),
            param.getAdContext(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, ComponentDetailGetListStruct.class);
    }   
}
