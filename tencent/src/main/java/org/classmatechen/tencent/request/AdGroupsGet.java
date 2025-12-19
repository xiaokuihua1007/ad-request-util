package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;
import org.classmatechen.tencent.basic.CombinedPage;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AdgroupsGetListStruct;
import com.tencent.ads.model.v3.AdgroupsGetResponseData;
import com.tencent.ads.model.v3.FilteringStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取广告
 * https://developers.e.qq.com/v3.0/docs/api/adgroups/get
 */
public class AdGroupsGet extends TxRequest<AdGroupsGet.Param, List<AdgroupsGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends CombinedPage {
    
        private Long accountId;
        private List<FilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private Boolean isDeleted;
        private List<String> fields;
        // private String paginationMode;
        // private String cursor;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<AdgroupsGetListStruct>> todo(TencentAds client, Param param) throws ApiException, TencentAdsResponseException {
        AdgroupsGetResponseData response = client.adgroups().adgroupsGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getIsDeleted(),
            param.getFields(),
            param.getPaginationMode(),
            param.getCursor(),
            param.getHeaderPair()
        );
        return Parser.page(response, AdgroupsGetListStruct.class);
    }
}
