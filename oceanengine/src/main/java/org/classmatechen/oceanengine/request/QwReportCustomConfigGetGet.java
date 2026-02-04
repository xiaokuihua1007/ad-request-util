package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.QianchuanReportCustomConfigGetV10Api;
import com.bytedance.ads.model.QianchuanReportCustomConfigGetV10DataTopics;
import com.bytedance.ads.model.QianchuanReportCustomConfigGetV10Response;
import com.bytedance.ads.model.QianchuanReportCustomConfigGetV10ResponseDataCustomConfigDatasInner;

import lombok.Data;

/**
 * 获取全域素材数据-可用指标和维度
 * https://open.oceanengine.com/labels/12/docs/1824289917859843
 */
public class QwReportCustomConfigGetGet extends DyRequest<QwReportCustomConfigGetGet.Param, List<QianchuanReportCustomConfigGetV10ResponseDataCustomConfigDatasInner>> {

    @Data
    public static class Param {
    
        private Long advertiserId;
        private List<QianchuanReportCustomConfigGetV10DataTopics> dataTopics;
    }

    @Override
    protected Response<List<QianchuanReportCustomConfigGetV10ResponseDataCustomConfigDatasInner>> todo(ApiClient client, Param param) throws ApiException {
        QianchuanReportCustomConfigGetV10Response response = new QianchuanReportCustomConfigGetV10Api(client).openApiV10QianchuanReportCustomConfigGetGet(
            param.getAdvertiserId(),
            param.getDataTopics()
        );
        return Parser.list(response, QianchuanReportCustomConfigGetV10ResponseDataCustomConfigDatasInner.class);
    }
}
