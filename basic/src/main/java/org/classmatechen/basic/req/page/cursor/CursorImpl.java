package org.classmatechen.basic.req.page.cursor;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CursorImpl implements Cursor {

    private Long cursor;

    @Override
    public boolean updateCursor(Long cursor) {
        if (Objects.isNull(cursor)) {
            return false;
        }
        this.cursor = cursor;
        return true;
    }
}
