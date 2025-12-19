package org.classmatechen.basic.req.page.combined;

import java.util.List;
import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.res.ResponseImpl;

public class CombinedPageResponse<R> extends ResponseImpl<List<R>> implements PageResponse<R> {

    private Long totalPage;
    private Long cursor;

    public CombinedPageResponse(List<R> data, Long totalPage, Long cursor) {
        super(data);
        this.totalPage = totalPage;
        this.cursor = cursor;
    }

    @Override
    public boolean update(Page page) {
        boolean flag = false;
        if (page instanceof CombinedPage) {
            CombinedPage smartPage = (CombinedPage) page;
            if (smartPage.updatePage(totalPage)) {
                flag = true;
            }
            if (smartPage.updateCursor(cursor)) {
                flag = true;
            }
        }
        return flag;
    }
}
