package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.LabelsGetLabel;
import com.tencent.ads.model.v3.LabelsGetResponseData;
import com.tencent.ads.model.v3.LabelsGetSingleFilter;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标签广场标签获取
 * https://developers.e.qq.com/v3.0/docs/api/labels/get
 */
public class LabelsGet extends TxRequest<LabelsGet.Param, List<LabelsGetLabel>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private List<LabelsGetSingleFilter> filtering;
        // private Long page;
        // private Long pageSize;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<LabelsGetLabel>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        LabelsGetResponseData response = client.labels().labelsGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, LabelsGetLabel.class);
    }   
}
