package org.classmatechen.basic.group;

import java.util.Objects;

import org.classmatechen.basic.Request;

public abstract class AbstractObjectGroup<P, R> extends AbstarctGroup<P, R> {

    public AbstractObjectGroup(Request<P, R> request) {
        super(request);
    }

    @Override
    protected void request(Request<P, R> request, Param<P> param) {

        R data = request.request(param.getContext(), param.getParam()).getData();
        if (Objects.nonNull(data)) {
            accept(data, param);
        }
    }
}
