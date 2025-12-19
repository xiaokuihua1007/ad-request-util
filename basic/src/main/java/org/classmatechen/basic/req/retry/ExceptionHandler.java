package org.classmatechen.basic.req.retry;

import org.classmatechen.basic.util.Order;

public interface ExceptionHandler extends Order {

    boolean support(Exception e);

    void handle(ExceptionContext context);
}
