package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.CustomAudienceStruct;
import com.tencent.ads.model.v3.CustomAudiencesGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取客户人群
 * https://developers.e.qq.com/v3.0/docs/api/custom_audiences/get
 */
public class CustomAudiencesGet extends TxRequest<CustomAudiencesGet.Param, List<CustomAudienceStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private Long audienceId;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<CustomAudienceStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        CustomAudiencesGetResponseData response = client.customAudiences().customAudiencesGet(
            param.getAccountId(),
            param.getAudienceId(),
            param.getPage(),
            param.getPageSize(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, CustomAudienceStruct.class);
    }   
}
