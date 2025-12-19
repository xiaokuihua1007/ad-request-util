package org.classmatechen.basic.res;

import org.classmatechen.basic.req.page.Page;

public interface PageResponse<R> extends ListResponse<R> {

    boolean update(Page page);
}
