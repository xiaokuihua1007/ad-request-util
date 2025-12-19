package org.classmatechen.basic.req.page.cursor.str;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StrCursorImpl implements StrCursor {

    private String cursor;

    @Override
    public boolean updateCursor(String cursor) {
        if (Objects.nonNull(cursor) && cursor.length() > 0) {
            this.cursor = cursor;
            return true;
        }
        return false;
    }
}
