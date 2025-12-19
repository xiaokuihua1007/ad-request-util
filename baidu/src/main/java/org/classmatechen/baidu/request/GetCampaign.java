package org.classmatechen.baidu.request;

import java.util.List;

import org.classmatechen.baidu.ReflectBdRequest;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;

import com.baidu.dev2.api.sdk.campaign.api.CampaignService;
import com.baidu.dev2.api.sdk.campaign.model.ApiCampaignQueryRequest;
import com.baidu.dev2.api.sdk.campaign.model.ApiCampaignType;
import com.baidu.dev2.api.sdk.campaign.model.GetCampaignRequestWrapper;
import com.baidu.dev2.api.sdk.invoke.ApiException;

public class GetCampaign extends ReflectBdRequest<ApiCampaignQueryRequest, List<ApiCampaignType>> {

    @Override
    protected Class<?> getWrapper() {
        return GetCampaignRequestWrapper.class;
    }

    @Override
    protected Response<List<ApiCampaignType>> todo(Object wrapper) throws ApiException {
        return Parser.list(new CampaignService().getCampaign((GetCampaignRequestWrapper) wrapper), ApiCampaignType.class);
    }
}
