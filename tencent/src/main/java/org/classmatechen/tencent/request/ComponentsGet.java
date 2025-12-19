package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.ComponentsGetListStruct;
import com.tencent.ads.model.v3.ComponentsGetResponseData;
import com.tencent.ads.model.v3.FilteringStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取创意组件
 * https://developers.e.qq.com/v3.0/docs/api/components/get
 */
public class ComponentsGet extends TxRequest<ComponentsGet.Param, List<ComponentsGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private Long organizationId;
        private List<FilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private Boolean isDeleted;
        private List<String> fields;
        private String componentIdFilteringMode;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<ComponentsGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        ComponentsGetResponseData response = client.components().componentsGet(
            param.getAccountId(),
            param.getOrganizationId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getIsDeleted(),
            param.getFields(),
            param.getComponentIdFilteringMode(),
            param.getHeaderPair()
        );
        return Parser.page(response, ComponentsGetListStruct.class);
    }   
}
