package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.res.ResponseImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.CustomAudienceEstimationsGetResponseData;
import com.tencent.ads.model.v3.EstimationAudienceSpec;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;

/**
 * 人群覆盖数预估
 * https://developers.e.qq.com/v3.0/docs/api/custom_audience_estimations/get
 */
public class CustomAudienceEstimationsGet extends TxRequest<CustomAudienceEstimationsGet.Param, Long> {

    @Data
    public static class Param {
    
        private Long accountId;
        private String type;
        private EstimationAudienceSpec audienceSpec;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<Long> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        CustomAudienceEstimationsGetResponseData response = client.customAudienceEstimations().customAudienceEstimationsGet(
            param.getAccountId(),
            param.getType(),
            param.getAudienceSpec(),
            param.getFields(),
            param.getHeaderPair()
        );
        return new ResponseImpl<>(response.getUserCount());
    }   
}
