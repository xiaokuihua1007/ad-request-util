package org.classmatechen.basic.req.page.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplePageImpl implements SimplePage {

    private Integer startRow;
    private Integer rowCount;

    @Override
    public boolean updatePage(int currentRow) {
        if (currentRow == rowCount) {
            this.startRow = this.startRow + this.rowCount;
            return true;
        }
        return false;
    }
}
