package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.ReportCustomGetV30Api;
import com.bytedance.ads.model.ReportCustomGetV30DataTopic;
import com.bytedance.ads.model.ReportCustomGetV30FiltersInner;
import com.bytedance.ads.model.ReportCustomGetV30OrderByInner;
import com.bytedance.ads.model.ReportCustomGetV30Response;
import com.bytedance.ads.model.ReportCustomGetV30ResponseDataRowsInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class ReportCustomGetGet extends DyRequest<ReportCustomGetGet.Param, List<ReportCustomGetV30ResponseDataRowsInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        List<String> dimensions;
        private Long advertiserId;
        private List<String> metrics;
        private List<ReportCustomGetV30FiltersInner> filters;
        private String startTime;
        private String endTime;
        private List<ReportCustomGetV30OrderByInner> orderBy;
        // private Integer page;
        // private Integer pageSize;
        private ReportCustomGetV30DataTopic dataTopic;
    }

    @Override
    protected Response<List<ReportCustomGetV30ResponseDataRowsInner>> todo(ApiClient client, Param param) throws ApiException {

        ReportCustomGetV30Response response = new ReportCustomGetV30Api(client).openApiV30ReportCustomGetGet(
            param.getDimensions(),
            param.getAdvertiserId(),
            param.getMetrics(),
            param.getFilters(),
            param.getStartTime(),
            param.getEndTime(),
            param.getOrderBy(),
            param.getPage().intValue(),
            param.getPageSize().intValue(),
            param.getDataTopic()
        );
        return Parser.page(response, ReportCustomGetV30ResponseDataRowsInner.class);
    }
}
