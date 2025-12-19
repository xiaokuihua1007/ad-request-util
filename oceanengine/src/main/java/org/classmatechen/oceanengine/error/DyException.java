package org.classmatechen.oceanengine.error;

import lombok.Getter;

public class DyException extends Exception {

    @Getter
    private long code;

    public DyException(long code, String message) {
        super(message);
        this.code = code;
    }
}
