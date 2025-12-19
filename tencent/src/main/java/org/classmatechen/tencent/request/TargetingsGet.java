package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPage;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.model.v3.TargetingsGetListStruct;
import com.tencent.ads.model.v3.TargetingsGetRequest;
import com.tencent.ads.model.v3.TargetingsGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Setter;

/**
 * 获取定向模板
 * https://developers.e.qq.com/v3.0/docs/api/targetings/get
 */
public class TargetingsGet extends TxRequest<TargetingsGet.Param, List<TargetingsGetListStruct>> {

    public static class Param extends TargetingsGetRequest implements ClassicPage {

        @Setter
        private Pair[] headerPair;
        private ClassicPage page;

        public Param() {
            this.page = new ClassicPageImpl();
        }

        @Override
        public boolean updatePage(Long totalPage) {
            return this.page.updatePage(totalPage);
        }

        @Override
        public Long getPage() {
            return this.page.getPage();
        }

        @Override
        public Long getPageSize() {
            return this.page.getPageSize();
        }

        @Override
        public void setPageSize(Long pageSize) {
            this.page.setPageSize(pageSize);
        }
    }

    @Override
    protected Response<List<TargetingsGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        TargetingsGetResponseData response = client.targetings().targetingsGet(
            param,
            param.headerPair
        );
        return Parser.page(response, TargetingsGetListStruct.class);
    }
}
