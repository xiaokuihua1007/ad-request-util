package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.FundsGetListStruct;
import com.tencent.ads.model.v3.FundsGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;

/**
 * 获取资金账户信息
 * https://developers.e.qq.com/v3.0/docs/api/funds/get
 */
public class FundsGet extends TxRequest<FundsGet.Param, List<FundsGetListStruct>> {

    @Data
    public static class Param {
    
        private Long accountId;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<FundsGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        FundsGetResponseData response = client.funds().fundsGet(param.getAccountId(), param.getFields(), param.getHeaderPair());
        return Parser.list(response, FundsGetListStruct.class);
    }  
}
