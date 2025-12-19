package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;

import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.DetailStruct;
import com.tencent.ads.model.v3.MarketingTargetAssetDetailGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;

public class MarketingTargetAssetDetailGet extends TxRequest<MarketingTargetAssetDetailGet.Param, List<DetailStruct>> {

    @Data
    public static class Param {
    
        private Long marketingAssetId;
        private String marketingTargetType;
        private Long accountId;
        private Long organizationId;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<DetailStruct>> todo(TencentAds client, Param param) throws ApiException, TencentAdsResponseException {
        
        MarketingTargetAssetDetailGetResponseData response = client.marketingTargetAssetDetail().marketingTargetAssetDetailGet(
            param.getMarketingAssetId(),
            param.getMarketingTargetType(),
            param.getAccountId(),
            param.getOrganizationId(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.list(response, DetailStruct.class);
    }
}
