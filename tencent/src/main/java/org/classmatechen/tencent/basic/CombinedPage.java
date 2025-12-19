package org.classmatechen.tencent.basic;

import java.util.Objects;

import org.classmatechen.basic.req.page.classic.ClassicPage;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.basic.req.page.cursor.str.StrCursor;
import org.classmatechen.basic.req.page.cursor.str.StrCursorImpl;

import lombok.Getter;
import lombok.Setter;

public class CombinedPage implements StrCursor, ClassicPage {

    public static final String PAGE_MODE = "PAGINATION_MODE_NORMAL";
    public static final String CURSOR_MODE = "PAGINATION_MODE_CURSOR";

    private ClassicPage page;
    private StrCursor cursor;
    @Getter
    @Setter
    private String paginationMode = CURSOR_MODE;

    public CombinedPage() { }

    public CombinedPage(ClassicPage page, StrCursor cursor) {
        this.page = page;
        this.cursor = cursor;
    }

    @Override
    public String getCursor() {
        if (Objects.isNull(cursor)) {
            return null;
        }
        return cursor.getCursor();
    }

    @Override
    public Long getPage() {
        if (Objects.isNull(page)) {
            return null;
        }
        return page.getPage();
    }

    @Override
    public Long getPageSize() {
        if (Objects.isNull(page)) {
            return null;
        }
        return page.getPageSize();
    }

    @Override
    public boolean updatePage(Long totalPage) {
        if (paginationMode.equals(CURSOR_MODE)) {
            return false;
        }
        return this.page.updatePage(totalPage);
    }

    @Override
    public boolean updateCursor(String cursor) {
        if (paginationMode.equals(PAGE_MODE)) {
            return false;
        }
        if (Objects.isNull(cursor)) {
            return false;
        }
        if (Objects.isNull(this.cursor)) {
            this.cursor = new StrCursorImpl();
        }
        return this.cursor.updateCursor(cursor);
    }

    @Override
    public void setPageSize(Long pageSize) {
        if (Objects.isNull(this.page)) {
            this.page = new ClassicPageImpl();
        }
        this.page.setPageSize(pageSize);
    }
}
