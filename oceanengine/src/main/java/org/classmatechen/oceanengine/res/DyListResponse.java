package org.classmatechen.oceanengine.res;

import java.util.List;

import org.classmatechen.basic.res.ListResponse;

public class DyListResponse<R> extends DyResponse<List<R>> implements ListResponse<R> {

    public DyListResponse(List<R> data, Long code, String message) {
        super(data, code, message);
    }
}
