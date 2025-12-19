package org.classmatechen.oceanengine.request;

import java.util.List;

import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.oceanengine.DyRequest;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.ProjectListV30Api;
import com.bytedance.ads.model.ProjectListV30Filtering;
import com.bytedance.ads.model.ProjectListV30Response;
import com.bytedance.ads.model.ProjectListV30ResponseDataListInner;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取项目列表
 * https://open.oceanengine.com/labels/7/docs/1740937147595776
 */
public class ProjectListGet extends DyRequest<ProjectListGet.Param, List<ProjectListV30ResponseDataListInner>> {

    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class Param extends ClassicPageImpl {

        private Long advertiserId;
        private List<String> fields;
        private ProjectListV30Filtering filtering;
    }

    @Override
    protected Response<List<ProjectListV30ResponseDataListInner>> todo(ApiClient client, Param param) throws ApiException {
        ProjectListV30Response response = new ProjectListV30Api(client).openApiV30ProjectListGet(
            param.getAdvertiserId(),
            param.getFields(),
            param.getFiltering(),
            param.getPage(),
            param.getPageSize()
        );
        return Parser.page(response, ProjectListV30ResponseDataListInner.class);
    }
}
