package org.classmatechen.basic.req.page.cursor.str;

import java.util.List;
import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.res.ResponseImpl;

public class StrCursorResponse<R> extends ResponseImpl<List<R>> implements PageResponse<R> {

    private String cursor;

    public StrCursorResponse(List<R> data) {
        super(data);
    }

    @Override
    public boolean update(Page page) {
        if (page instanceof StrCursor) {
            return ((StrCursor) page).updateCursor(this.cursor);
        }
        return false;
    }
}
