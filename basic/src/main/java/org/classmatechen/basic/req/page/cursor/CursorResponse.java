package org.classmatechen.basic.req.page.cursor;

import java.util.List;
import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.res.ResponseImpl;

import lombok.Getter;

@Getter
public class CursorResponse<R> extends ResponseImpl<List<R>> implements PageResponse<R> {

    private Long cursor;

    public CursorResponse(List<R> data, Long cursor) {
        super(data);
        this.cursor = cursor;
    }

    @Override
    public boolean update(Page page) {
        if (page instanceof Cursor) {
            return ((Cursor) page).updateCursor(this.cursor);
        }
        return false;
    }
}
