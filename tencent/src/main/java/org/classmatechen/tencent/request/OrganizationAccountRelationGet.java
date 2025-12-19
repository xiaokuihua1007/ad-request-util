package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;
import org.classmatechen.tencent.basic.TxCombinedPage;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.OrganizationAccountRelationGetListStruct;
import com.tencent.ads.model.v3.OrganizationAccountRelationGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询组织下广告账户信息
 * https://developers.e.qq.com/v3.0/docs/api/organization_account_relation/get
 */
public class OrganizationAccountRelationGet extends TxRequest<OrganizationAccountRelationGet.Param, List<OrganizationAccountRelationGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends TxCombinedPage {

        // private String paginationMode;
        private Long accountId;
        private String advertiserType;
        private Long businessUnitId;
        // private Long cursor;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<OrganizationAccountRelationGetListStruct>> todo(TencentAds client, Param param) throws ApiException, TencentAdsResponseException {

        OrganizationAccountRelationGetResponseData response = client
            .organizationAccountRelation()
            .organizationAccountRelationGet(
                param.getPaginationMode(),
                param.getAccountId(),
                param.getAdvertiserType(),
                param.getBusinessUnitId(),
                param.getCursor(),
                param.getPage(),
                param.getPageSize(),
                param.getFields(),
                param.getHeaderPair()
            );
        return Parser.page(response, OrganizationAccountRelationGetListStruct.class);
    }
}
