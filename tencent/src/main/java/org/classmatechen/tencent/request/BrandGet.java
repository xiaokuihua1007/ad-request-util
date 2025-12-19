package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.BrandGetListStruct;
import com.tencent.ads.model.v3.BrandGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取品牌形象列表
 * https://developers.e.qq.com/v3.0/docs/api/brand/get
 */
public class BrandGet extends TxRequest<BrandGet.Param, List<BrandGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<BrandGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        BrandGetResponseData response = client.brand().brandGet(
            param.getAccountId(),
            param.getPage(),
            param.getPageSize(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, BrandGetListStruct.class);
    }   
}
