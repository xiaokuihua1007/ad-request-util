package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.HourlyReportApiListStruct;
import com.tencent.ads.model.v3.HourlyReportDateRange;
import com.tencent.ads.model.v3.HourlyReportsGetResponseData;
import com.tencent.ads.model.v3.IntegratedListApiFilteringStruct;
import com.tencent.ads.model.v3.OrderByStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取小时报表
 * https://developers.e.qq.com/v3.0/docs/api/hourly_reports/get
 */
public class HourlyReportsGet extends TxRequest<HourlyReportsGet.Param, List<HourlyReportApiListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private String level;
        private HourlyReportDateRange dateRange;
        private List<String> groupBy;
        private List<String> fields;
        private List<IntegratedListApiFilteringStruct> filtering;
        private List<OrderByStruct> orderBy;
        private String timeLine;
        // private Long page;
        // private Long pageSize;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<HourlyReportApiListStruct>> todo(TencentAds client, Param param) throws ApiException, TencentAdsResponseException {

        HourlyReportsGetResponseData response = client.hourlyReports().hourlyReportsGet(
            param.getAccountId(),
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
        return Parser.page(response, HourlyReportApiListStruct.class);
    }
}
