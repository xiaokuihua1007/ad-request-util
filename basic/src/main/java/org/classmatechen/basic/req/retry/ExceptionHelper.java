package org.classmatechen.basic.req.retry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionHelper {

    private static final ExceptionHelper helper = new ExceptionHelper();
    private List<ExceptionHandler> handlers = new ArrayList<>();

    public static void register(ExceptionHandler handler) {
        helper.handlers.add(handler);
        helper.handlers = helper.handlers.stream().sorted().collect(Collectors.toList());
    }

    public static boolean handle(ExceptionContext context) {

        boolean handled = false;
        for (ExceptionHandler handler : helper.handlers) {
            if (handler.support(context.getError())) {
                handler.handle(context);
                handled = true;
            }
        }
        return handled;
    }
}
