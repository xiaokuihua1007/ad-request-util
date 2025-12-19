package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.EcommerceOrderListStruct;
import com.tencent.ads.model.v3.DateRange;
import com.tencent.ads.model.v3.EcommerceOrderGetResponseData;
import com.tencent.ads.model.v3.FilteringStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取订单
 * https://developers.e.qq.com/v3.0/docs/api/ecommerce_order/get
 */
public class EcommerceOrderGet extends TxRequest<EcommerceOrderGet.Param, List<EcommerceOrderListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
        Long accountId;
        private DateRange dateRange;
        private List<FilteringStruct> filtering;
        private String date;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<EcommerceOrderListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        EcommerceOrderGetResponseData response = client.ecommerceOrder().ecommerceOrderGet(
            param.getAccountId(),
            param.getDateRange(),
            param.getFiltering(),
            param.getDate(),
            param.getPage(),
            param.getPageSize(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, EcommerceOrderListStruct.class);
    }
}
