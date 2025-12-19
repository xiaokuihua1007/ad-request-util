package org.classmatechen.basic.req.page.classic;

import java.util.List;
import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.res.ResponseImpl;

import lombok.Getter;

@Getter
public class ClassicPageResponse<R> extends ResponseImpl<List<R>> implements PageResponse<R> {

    private Long totalPage;

    public ClassicPageResponse(List<R> data, Long totalPage) {
        super(data);
        this.totalPage = totalPage;
    }

    @Override
    public boolean update(Page page) {
        if (page instanceof ClassicPage) {
            return ((ClassicPage) page).updatePage(totalPage);
        }
        return false;
    }
}
