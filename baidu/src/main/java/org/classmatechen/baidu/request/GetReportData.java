package org.classmatechen.baidu.request;

import java.util.List;

import org.classmatechen.baidu.BdRequest;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.simple.SimplePage;
import org.classmatechen.basic.req.page.simple.SimplePageImpl;

import com.baidu.dev2.api.sdk.common.ApiRequestHeader;
import com.baidu.dev2.api.sdk.invoke.ApiException;
import com.baidu.dev2.api.sdk.openapireport.api.OpenApiReportService;
import com.baidu.dev2.api.sdk.openapireport.model.GetReportDataRequestWrapper;
import com.baidu.dev2.api.sdk.openapireport.model.ReportData;
import com.baidu.dev2.api.sdk.openapireport.model.ReportDataRequest;

/**
 * 搜索词报告
 * https://dev2.baidu.com/content?sceneType=0&pageId=102477&nodeId=704&subhead=
 */
public class GetReportData extends BdRequest<GetReportData.Param, List<ReportData>> {

    public static class Param extends ReportDataRequest implements SimplePage {

        private SimplePage page;

        public Param() {
            this.page = new SimplePageImpl();
        }

        @Override
        public boolean updatePage(int currentRow) {
            return this.page.updatePage(currentRow);
        }

        @Override
        public void setStartRow(Integer startRow) {
            page.setStartRow(startRow);
        }

        @Override
        public void setRowCount(Integer rowCount) {
            page.setRowCount(rowCount);
        }

        @Override
        public Integer getStartRow() {
            return this.page.getStartRow();
        }

        @Override
        public Integer getRowCount() {
            return this.page.getRowCount();
        }
    }

    @Override
    protected Response<List<ReportData>> todo(ApiRequestHeader header, Param param) throws ApiException {
        
        GetReportDataRequestWrapper wrapper = new GetReportDataRequestWrapper();
        wrapper.setHeader(header);
        wrapper.setBody(param);
        return Parser.page(new OpenApiReportService().getReportData(wrapper), ReportData.class);
    }
}
