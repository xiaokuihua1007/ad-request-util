package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.ComponentSharingGetListStruct;
import com.tencent.ads.model.v3.ComponentSharingGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询创意组件共享信息
 * https://developers.e.qq.com/v3.0/docs/api/component_sharing/get
 */
public class ComponentSharingGet extends TxRequest<ComponentSharingGet.Param, List<ComponentSharingGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long organizationId;
        private Long componentId;
        // private Long page;
        // private Long pageSize;
        private Boolean isDeleted;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<ComponentSharingGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        ComponentSharingGetResponseData response = client.componentSharing().componentSharingGet(
            param.getOrganizationId(),
            param.getComponentId(),
            param.getPage(),
            param.getPageSize(),
            param.getIsDeleted(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, ComponentSharingGetListStruct.class);
    }   
}
