package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AgencyGetListStruct;
import com.tencent.ads.model.v3.AgencyGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询腾讯广告服务商信息
 * https://developers.e.qq.com/v3.0/docs/api/agency/get
 */
public class AgencyGet extends TxRequest<AgencyGet.Param, List<AgencyGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private List<String> fields;
        // private Long page;
        // private Long pageSize;
        private Long accountId;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<AgencyGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        AgencyGetResponseData response = client.agency().agencyGet(
            param.getFields(),
            param.getPage(),
            param.getPageSize(),
            param.getAccountId(),
            param.getHeaderPair()
        );
        return Parser.page(response, AgencyGetListStruct.class);
    }   
}
