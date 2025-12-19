package org.classmatechen.basic.req.page.classic;

import org.classmatechen.basic.req.page.Page;

public interface ClassicPage extends Page {

    boolean updatePage(Long totalPage);

    Long getPage();

    Long getPageSize();

    void setPageSize(Long pageSize);
}
