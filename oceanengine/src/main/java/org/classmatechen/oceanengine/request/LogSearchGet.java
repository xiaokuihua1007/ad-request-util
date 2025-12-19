package org.classmatechen.oceanengine.request;

import java.util.List;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.ToolsLogSearchV2Api;
import com.bytedance.ads.model.ToolsLogSearchV2Response;
import com.bytedance.ads.model.ToolsLogSearchV2ResponseDataLogsInner;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志查询
 * https://open.oceanengine.com/labels/7/docs/1696710682956815
 */
public class LogSearchGet extends DyRequest<LogSearchGet.Param, List<ToolsLogSearchV2ResponseDataLogsInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {

        private Long advertiserId;
        private String endTime;
        private List<Long> objectId;
        // private Long page;
        // private Long pageSize;
        private String startTime;
    }

    @Override
    protected Response<List<ToolsLogSearchV2ResponseDataLogsInner>> todo(ApiClient client, Param param)
            throws ApiException {

        ToolsLogSearchV2Response response = new ToolsLogSearchV2Api(client).openApi2ToolsLogSearchGet(
            param.getAdvertiserId(),
            param.getEndTime(),
            param.getObjectId(),
            param.getPage(),
            param.getPageSize(),
            param.getStartTime()
        );
        return Parser.page(response, ToolsLogSearchV2ResponseDataLogsInner.class);
    }
}
