package org.classmatechen.oceanengine.res;

import java.util.List;

import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.res.PageResponse;

public class DyPageResponse<R> extends DyResponse<List<R>> implements PageResponse<R> {

    private PageResponse<R> response;

    public DyPageResponse(List<R> data, Long code, String message, PageResponse<R> page) {
        super(data, code, message);
        response = page;
    }

    @Override
    public boolean update(Page page) {
        return response.update(page);
    }
}
