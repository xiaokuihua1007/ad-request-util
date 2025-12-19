package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;

import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AdgroupNegativewordsGetResponseData;
import com.tencent.ads.model.v3.NegativeWordAdgroupStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询广告否定词
 * https://developers.e.qq.com/v3.0/docs/api/adgroup_negativewords/get
 */
public class AdgroupNegativewordsGet extends TxRequest<AdgroupNegativewordsGet.Param, List<NegativeWordAdgroupStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param {
    
        private Long accountId;
        private List<Long> adgroupIds;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<NegativeWordAdgroupStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        AdgroupNegativewordsGetResponseData response = client.adgroupNegativewords().adgroupNegativewordsGet(
            param.getAccountId(),
            param.getAdgroupIds(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.list(response, NegativeWordAdgroupStruct.class);
    }   
}
