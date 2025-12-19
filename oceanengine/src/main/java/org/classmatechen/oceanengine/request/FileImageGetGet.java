package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.FileImageGetV2Api;
import com.bytedance.ads.model.FileImageGetV2Filtering;
import com.bytedance.ads.model.FileImageGetV2Response;
import com.bytedance.ads.model.FileImageGetV2ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取图片素材
 * https://open.oceanengine.com/labels/7/docs/1696710601254912
 */
public class FileImageGetGet extends DyRequest<FileImageGetGet.Param, List<FileImageGetV2ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long advertiserId;
        private FileImageGetV2Filtering filtering;
    }

    @Override
    protected Response<List<FileImageGetV2ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {

        FileImageGetV2Response response = new FileImageGetV2Api(client).openApi2FileImageGetGet(
            param.getAdvertiserId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize()
        );
        return Parser.page(response, FileImageGetV2ResponseDataListInner.class);
    }
}
