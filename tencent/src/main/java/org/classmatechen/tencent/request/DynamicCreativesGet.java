package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;
import org.classmatechen.tencent.basic.CombinedPage;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.DynamicCreativesGetListStruct;
import com.tencent.ads.model.v3.DynamicCreativesGetResponseData;
import com.tencent.ads.model.v3.FilteringStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取动态创意
 * https://developers.e.qq.com/v3.0/docs/api/dynamic_creatives/get
 */
public class DynamicCreativesGet extends TxRequest<DynamicCreativesGet.Param, List<DynamicCreativesGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends CombinedPage {
    
        private Long accountId;
        private List<FilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Boolean isDeleted;
        // private String paginationMode;
        // private String cursor;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<DynamicCreativesGetListStruct>> todo(TencentAds client, Param param) throws ApiException, TencentAdsResponseException {

        DynamicCreativesGetResponseData response = client.dynamicCreatives().dynamicCreativesGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getFields(),
            param.getIsDeleted(),
            param.getPaginationMode(),
            param.getCursor(),
            param.getHeaderPair()
        );
        return Parser.page(response, DynamicCreativesGetListStruct.class);
    }
}
