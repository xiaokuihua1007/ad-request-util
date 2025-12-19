package org.classmatechen.basic.req.page.simple;

import org.classmatechen.basic.req.page.Page;

public interface SimplePage extends Page {

    boolean updatePage(int currentRow);

    void setStartRow(Integer startRow);

    void setRowCount(Integer rowCount);

    Integer getStartRow();

    Integer getRowCount();
}
