package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.ToolsVideoCoverSuggestV2Api;
import com.bytedance.ads.model.ToolsVideoCoverSuggestV2Response;
import com.bytedance.ads.model.ToolsVideoCoverSuggestV2ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取视频智能封面
 * https://open.oceanengine.com/labels/7/docs/1696710602404864
 */
public class VideoCoverSuggestGet extends DyRequest<VideoCoverSuggestGet.Param, List<ToolsVideoCoverSuggestV2ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long advertiserId;
        private String videoId;
    }

    @Override
    protected Response<List<ToolsVideoCoverSuggestV2ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {

        ToolsVideoCoverSuggestV2Response response = new ToolsVideoCoverSuggestV2Api(client).openApi2ToolsVideoCoverSuggestGet(
            param.getAdvertiserId(),
            param.getVideoId()
        );
        return Parser.list(response, ToolsVideoCoverSuggestV2ResponseDataListInner.class);
    }
}
