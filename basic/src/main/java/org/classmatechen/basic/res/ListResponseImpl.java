package org.classmatechen.basic.res;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListResponseImpl<R> implements ListResponse<R> {

    private List<R> list;

    @Override
    public List<R> getData() {
        return this.list;
    }
}
