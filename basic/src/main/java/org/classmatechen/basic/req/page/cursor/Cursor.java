package org.classmatechen.basic.req.page.cursor;

import org.classmatechen.basic.req.page.Page;

public interface Cursor extends Page {

    boolean updateCursor(Long cursor);

    Long getCursor();
}
