package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AudienceGrantRelationsGetResponseData;
import com.tencent.ads.model.v3.AudienceGrantRelationsGetSingleFilter;
import com.tencent.ads.model.v3.GrantInfo;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取人群授权信息
 * https://developers.e.qq.com/v3.0/docs/api/audience_grant_relations/get
 */
public class AudienceGrantRelationsGet extends TxRequest<AudienceGrantRelationsGet.Param, List<GrantInfo>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private List<AudienceGrantRelationsGetSingleFilter> filtering;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<GrantInfo>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        AudienceGrantRelationsGetResponseData response = client.audienceGrantRelations().audienceGrantRelationsGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, GrantInfo.class);
    }   
}
