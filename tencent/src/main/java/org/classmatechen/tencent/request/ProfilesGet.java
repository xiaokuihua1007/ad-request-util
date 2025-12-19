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
import com.tencent.ads.model.v3.ProfilesGetListStruct;
import com.tencent.ads.model.v3.ProfilesGetResponseData;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取朋友圈头像昵称跳转页
 * https://developers.e.qq.com/v3.0/docs/api/profiles/get
 */
public class ProfilesGet extends TxRequest<ProfilesGet.Param, List<ProfilesGetListStruct>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {
    
        private Long accountId;
        private List<FilteringStruct> filtering;
        // private Long page;
        // private Long pageSize;
        private Long organizationId;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<List<ProfilesGetListStruct>> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        ProfilesGetResponseData response = client.profiles().profilesGet(
            param.getAccountId(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize(),
            param.getOrganizationId(),
            param.getFields(),
            param.getHeaderPair()
        );
        return Parser.page(response, ProfilesGetListStruct.class);
    }   
}
