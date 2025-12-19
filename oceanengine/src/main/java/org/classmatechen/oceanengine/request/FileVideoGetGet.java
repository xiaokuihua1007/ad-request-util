package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.FileVideoGetV2Api;
import com.bytedance.ads.model.FileVideoGetV2Filtering;
import com.bytedance.ads.model.FileVideoGetV2Response;
import com.bytedance.ads.model.FileVideoGetV2ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取视频素材
 * https://open.oceanengine.com/labels/7/docs/1696710601820172
 */
public class FileVideoGetGet extends DyRequest<FileVideoGetGet.Param, List<FileVideoGetV2ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long advertiserId;
        private FileVideoGetV2Filtering filtering;
    }

    @Override
    protected Response<List<FileVideoGetV2ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {

        FileVideoGetV2Response response = new FileVideoGetV2Api(client).openApi2FileVideoGetGet(
            param.getAdvertiserId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize()
        );
        return Parser.page(response, FileVideoGetV2ResponseDataListInner.class);
    }
}
