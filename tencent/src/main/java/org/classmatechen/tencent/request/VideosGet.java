package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.FilteringStruct;
import com.tencent.ads.model.v3.VideosGetListStruct;
import com.tencent.ads.model.v3.VideosGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取视频文件
 * https://developers.e.qq.com/v3.0/docs/api/videos/get
 */
public class VideosGet extends TxRequest<VideosGet.Param, List<VideosGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private Long organizationId;
        private List<FilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private Long labelId;
        private Long businessScenario;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<VideosGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        VideosGetResponseData response = client.videos().videosGet(
            param.getAccountId(),
            param.getOrganizationId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getLabelId(),
            param.getBusinessScenario(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, VideosGetListStruct.class);
    }   
}
