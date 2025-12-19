package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.ReportCustomConfigGetV30Api;
import com.bytedance.ads.model.ReportCustomConfigGetV30DataTopics;
import com.bytedance.ads.model.ReportCustomConfigGetV30Response;
import com.bytedance.ads.model.ReportCustomConfigGetV30ResponseDataListInner;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReportCustomConfigGetGet extends DyRequest<ReportCustomConfigGetGet.Param, List<ReportCustomConfigGetV30ResponseDataListInner>> {

    @Getter
    @AllArgsConstructor
    public static class Param {

        private Long advertiserId;
        private List<ReportCustomConfigGetV30DataTopics> dataTopics;
    }

    @Override
    protected Response<List<ReportCustomConfigGetV30ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {

        ReportCustomConfigGetV30Response response = new ReportCustomConfigGetV30Api(client).openApiV30ReportCustomConfigGetGet(
            param.getAdvertiserId(),
            param.getDataTopics()
        );
        return Parser.list(response, ReportCustomConfigGetV30ResponseDataListInner.class);
    }
}
