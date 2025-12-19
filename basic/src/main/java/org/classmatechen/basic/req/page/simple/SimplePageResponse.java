package org.classmatechen.basic.req.page.simple;

import java.util.List;
import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.res.ResponseImpl;

public class SimplePageResponse<R> extends ResponseImpl<List<R>> implements PageResponse<R> {

    public SimplePageResponse(List<R> data) {
        super(data);
    }

    @Override
    public boolean update(Page page) {
        if (page instanceof SimplePage) {
            return ((SimplePage) page).updatePage(getData().size());
        }
        return false;
    }
}
