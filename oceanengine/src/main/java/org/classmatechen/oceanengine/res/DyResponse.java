package org.classmatechen.oceanengine.res;

import org.classmatechen.basic.res.ResponseImpl;

import lombok.Getter;

@Getter
public class DyResponse<R> extends ResponseImpl<R> {

    private Long code;
    private String message;

    public DyResponse(R data, Long code, String message) {
        super(data);
        this.code = code;
        this.message = message;
    }
}
