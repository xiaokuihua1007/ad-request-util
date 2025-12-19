package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.OrderByStruct;
import com.tencent.ads.model.v3.ReportDateRange;
import com.tencent.ads.model.v3.TargetReportApiListStruct;
import com.tencent.ads.model.v3.TargetingFilteringStruct;
import com.tencent.ads.model.v3.TargetingTagReportsGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取定向标签报表
 * https://developers.e.qq.com/v3.0/docs/api/targeting_tag_reports/get
 */
public class TargetingTagReportsGet extends TxRequest<TargetingTagReportsGet.Param, List<TargetReportApiListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private String type;
        private String level;
        private ReportDateRange dateRange;
        private List<String> groupBy;
        private List<String> fields;
        private List<TargetingFilteringStruct> filtering;
        private List<OrderByStruct> orderBy;
        private String timeLine;
        // private Long page;
        // private Long pageSize;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<TargetReportApiListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        TargetingTagReportsGetResponseData response = client.targetingTagReports().targetingTagReportsGet(
            param.getAccountId(),
            param.getType(),
            param.getLevel(),
            param.getDateRange(),
            param.getGroupBy(),
            param.getFields(),
            param.getFiltering(),
            param.getOrderBy(),
            param.getTimeLine(),
            param.getPage(),
            param.getPageSize(),
            param.getHeaderPair()
        );
        return Parser.page(response, TargetReportApiListStruct.class);
    }   
}
