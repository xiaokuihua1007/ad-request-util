package org.classmatechen.basic.req.retry;

import org.classmatechen.basic.Context;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionContext {

    private Exception error;
    private Context context;
    private Object param;
    private int maxRetryCount;
    private int currentRetryCount;
}
