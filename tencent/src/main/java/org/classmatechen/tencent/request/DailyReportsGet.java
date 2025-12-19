package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.DailyReportsFilteringStruct;
import com.tencent.ads.model.v3.DailyReportsGetResponseData;
import com.tencent.ads.model.v3.OrderByStruct;
import com.tencent.ads.model.v3.ReportDateRange;
import com.tencent.ads.model.v3.ReportStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取广告文案
 * https://developers.e.qq.com/v3.0/docs/api/creativetools_text/get
 */
public class DailyReportsGet extends TxRequest<DailyReportsGet.Param, List<ReportStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {

        private String level;
        private ReportDateRange dateRange;
        private List<String> groupBy;
        private List<String> fields;
        private Long accountId;
        private List<DailyReportsFilteringStruct> filtering;
        private List<OrderByStruct> orderBy;
        private String timeLine;
        // private Long page;
        // private Long pageSize;
        private Long organizationId;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<ReportStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        DailyReportsGetResponseData response = client.dailyReports().dailyReportsGet(
            param.getLevel(),
            param.getDateRange(),
            param.getGroupBy(),
            param.getFields(),
            param.getAccountId(),
            param.getFiltering(),
            param.getOrderBy(),
            param.getTimeLine(),
            param.getPage(),
            param.getPageSize(),
            param.getOrganizationId(),
            param.getHeaderPair()
        );
        return Parser.page(response, ReportStruct.class);
    }
}
