package org.classmatechen.basic.group;

import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.Request;

public abstract class AbstractListGroup<P, R> extends AbstarctGroup<P, List<R>> {

    public AbstractListGroup(Request<P, List<R>> request) {
        super(request);
    }

    @Override
    protected void request(Request<P, List<R>> request, Param<P> param) {

        List<R> list = request.request(param.getContext(), param.getParam()).getData();
        if (Objects.nonNull(list) && list.size() > 0) {
            accept(list, param);
        }
    }
}
