package org.classmatechen.tencent.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.res.ResponseImpl;
import org.classmatechen.tencent.TxRequest;
import com.tencent.ads.ApiException;
import com.tencent.ads.Pair;
import com.tencent.ads.exception.TencentAdsResponseException;
import com.tencent.ads.v3.TencentAds;

import lombok.Data;

/**
 * 获取文件接口
 * https://developers.e.qq.com/v3.0/docs/api/async_report_files/get
 */
public class AsyncReportFilesGet extends TxRequest<AsyncReportFilesGet.Param, String> {

    @Data
    public static class Param {
    
        private Long taskId;
        private Long fileId;
        private Long accountId;
        private Long organizationId;
        private List<String> fields;
        private Pair[] headerPair;
    }

    @Override
    protected Response<String> todo(TencentAds client, Param param)
            throws ApiException, TencentAdsResponseException {

        String response = client.asyncReportFiles().asyncReportFilesGet(
            param.getTaskId(),
            param.getFileId(),
            param.getAccountId(),
            param.getOrganizationId(),
            param.getFields(),
            param.getHeaderPair()
        );
        return new ResponseImpl<>(response);
    }   
}
