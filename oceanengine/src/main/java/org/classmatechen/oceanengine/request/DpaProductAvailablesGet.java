package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.DpaProductAvailablesV2Api;
import com.bytedance.ads.model.DpaProductAvailablesV2Response;
import com.bytedance.ads.model.DpaProductAvailablesV2ResponseDataListInner;

/**
 * 获取商品库元信息
 * https://open.oceanengine.com/labels/34/docs/1696710577566735
 */
@Deprecated
public class DpaProductAvailablesGet extends DyRequest<Long, List<DpaProductAvailablesV2ResponseDataListInner>> {

    @Override
    protected Response<List<DpaProductAvailablesV2ResponseDataListInner>> todo(ApiClient client, Long advertiserId) throws ApiException {

        DpaProductAvailablesV2Response response = new DpaProductAvailablesV2Api(client).openApi2DpaProductAvailablesGet(advertiserId);
        return Parser.list(response, DpaProductAvailablesV2ResponseDataListInner.class);
    }
}
