package org.classmatechen.oceanengine.request;

import java.util.Objects;

import org.classmatechen.basic.Response;
import org.classmatechen.oceanengine.DyRequest;
import org.classmatechen.oceanengine.res.DyResponse;

import com.bytedance.ads.ApiClient;
import com.bytedance.ads.ApiException;
import com.bytedance.ads.api.ProjectCreateV30Api;
import com.bytedance.ads.model.ProjectCreateV30Request;
import com.bytedance.ads.model.ProjectCreateV30Response;

public class ProjectCreatePost extends DyRequest<ProjectCreateV30Request, Long> {

    @Override
    protected Response<Long> todo(ApiClient client, ProjectCreateV30Request param) throws ApiException {

        ProjectCreateV30Response response = new ProjectCreateV30Api(client).openApiV30ProjectCreatePost(param);
        Long projectId = Objects.isNull(response.getData()) ? null : response.getData().getProjectId();
        return new DyResponse<>(projectId, response.getCode(), response.getMessage());
    }
}
