package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.AsyncReportsGetResponseData;
import com.tencent.ads.model.v3.GetAsyncReportsFilteringStruct;
import com.tencent.ads.model.v3.TaskListStruct;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取异步报表任务
 * https://developers.e.qq.com/v3.0/docs/api/async_reports/get
 */
public class AsyncReportsGet extends TxRequest<AsyncReportsGet.Param, List<TaskListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private List<GetAsyncReportsFilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private Long organizationId;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<TaskListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        AsyncReportsGetResponseData response = client.asyncReports().asyncReportsGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getOrganizationId(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, TaskListStruct.class);
    }   
}
