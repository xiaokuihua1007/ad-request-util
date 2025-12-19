package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AdReportListStruct;
import com.tencent.ads.model.v3.AdUnionReportsGetResponseData;
import com.tencent.ads.model.v3.OrderByStruct;
import com.tencent.ads.model.v3.ReportDateRange;
import com.tencent.ads.model.v3.UnionReportFiltering;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联盟广告位报表接口
 * https://developers.e.qq.com/v3.0/docs/api/ad_union_reports/get
 */
public class AdUnionReportsGet extends TxRequest<AdUnionReportsGet.Param, List<AdReportListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private ReportDateRange dateRange;
        private List<String> fields;
        private UnionReportFiltering filtering;
        private List<String> groupBy;
        private List<OrderByStruct> orderBy;
        // private Long page;
        // private Long pageSize;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<AdReportListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        AdUnionReportsGetResponseData response = client.adUnionReports().adUnionReportsGet(
            param.getAccountId(),
            param.getDateRange(),
            param.getFields(),
            param.getFiltering(),
            param.getGroupBy(),
            param.getOrderBy(),
            param.getPage(),
            param.getPageSize(),
            param.getHeaderPair()
        );
        return Parser.page(response, AdReportListStruct.class);
    }   
}
