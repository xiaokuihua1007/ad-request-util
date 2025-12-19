package org.classmatechen.basic.req.page.cursor.str;

import org.classmatechen.basic.req.page.Page;

public interface StrCursor extends Page {

    String getCursor();

    boolean updateCursor(String cursor);
}
