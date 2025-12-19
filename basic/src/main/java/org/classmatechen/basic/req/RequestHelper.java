package org.classmatechen.basic.req;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.classmatechen.basic.Request;

@Deprecated
public class RequestHelper {

    private static final RequestHelper helper = new RequestHelper();
    private final Map<String, Request<?, ?>> requests = new HashMap<>();

    public static Request<?, ?> request(Class<?> cls) {
        
        String className = cls.getName();
        RequestHelper self = helper;
        String error = null;

        Request<?, ?> request = (Request<?, ?>) self.requests.get(className);
        if (Objects.isNull(request)) {
            synchronized (self) {
                request = (Request<?, ?>) self.requests.get(className);
                if (Objects.isNull(request)) {
                    try {
                        Class<?> clazz = Class.forName(className);
                        if (Objects.nonNull(clazz)) {
                            request = (Request<?, ?>) clazz.newInstance();
                            self.requests.put(className, request);
                        }
                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                }
            }
        }
        if (Objects.isNull(request)) {
            throw new RuntimeException(error);
        }
        return request;
    }
}
