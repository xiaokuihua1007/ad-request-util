package org.classmatechen.tencent.request;

import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.impl.CheckAble;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.tencent.TxRequest;
import org.classmatechen.tencent.basic.TxCombinedPage;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AdvertiserGetListStruct;
import com.tencent.ads.model.v3.AdvertiserGetResponseData;
import com.tencent.ads.model.v3.FilteringStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询腾讯广告广告主信息
 * https://developers.e.qq.com/v3.0/docs/api/advertiser/get
 */
public class AdvertiserGet extends TxRequest<AdvertiserGet.Param, List<AdvertiserGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends TxCombinedPage implements CheckAble {

        private List<String> fields;
        // private String paginationMode;
        // private Long pageSize;
        private Long agencyId;
        private Long accountId;
        private List<FilteringStruct> filtering;
        // private Long page;
        // private Long cursor;
        private Pair[] headerPair;

        @Override
        public String check() {
            if (Objects.isNull(getPageSize())) {
                return "pageSize must not be null";
            }
            return null;
        }
    }

    @Override
    protected Response<List<AdvertiserGetListStruct>> todo(TencentAds client, Param param) throws ApiException, TencentAdsResponseException {
        AdvertiserGetResponseData response = client.advertiser().advertiserGet(
                                                param.getFields(),
                                                param.getPaginationMode(),
                                                param.getPageSize(),
                                                param.getAgencyId(),
                                                param.getAccountId(),
                                                param.getFiltering(),
                                                param.getPage(),
                                                param.getCursor(),
                                                param.getHeaderPair()
                                            );
        return Parser.page(response, AdvertiserGetListStruct.class);
    }
}
