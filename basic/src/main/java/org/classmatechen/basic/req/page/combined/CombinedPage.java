package org.classmatechen.basic.req.page.combined;

import java.util.Objects;

import org.classmatechen.basic.req.page.classic.ClassicPage;
import org.classmatechen.basic.req.page.classic.ClassicPageImpl;
import org.classmatechen.basic.req.page.cursor.Cursor;

public class CombinedPage implements Cursor, ClassicPage {

    private ClassicPage page;
    private Cursor cursor;

    public CombinedPage() { }

    public CombinedPage(ClassicPage page, Cursor cursor) {
        this.page = page;
        this.cursor = cursor;
    }

    @Override
    public Long getCursor() {
        if (Objects.isNull(cursor)) {
            return null;
        }
        return cursor.getCursor();
    }

    @Override
    public boolean updateCursor(Long cursor) {
        if (Objects.isNull(this.cursor)) {
            return false;
        }
        return this.cursor.updateCursor(cursor);
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
        if (Objects.isNull(this.page)) {
            return false;
        }
        return this.page.updatePage(totalPage);
    }

    @Override
    public void setPageSize(Long pageSize) {
        if (Objects.isNull(this.page)) {
            this.page = new ClassicPageImpl();
        }
        this.page.setPageSize(pageSize);
    }
}
