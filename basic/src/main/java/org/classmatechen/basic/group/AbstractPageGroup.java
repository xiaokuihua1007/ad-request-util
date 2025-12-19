package org.classmatechen.basic.group;

import java.util.Iterator;
import java.util.List;

import org.classmatechen.basic.Request;
import org.classmatechen.basic.req.page.Page;

public abstract class AbstractPageGroup<P extends Page, R> extends AbstarctGroup<P, Iterator<List<R>>> {

    public AbstractPageGroup(Request<P, Iterator<List<R>>> request) {
        super(request);
    }

    @Override
    protected void request(Request<P, Iterator<List<R>>> request, Param<P> param) {

        Iterator<List<R>> iterator = request.request(param.getContext(), param.getParam()).getData();
        while (iterator.hasNext()) {
            List<R> list = iterator.next();
            accept(list, param);
        }
    }

    protected abstract void accept(List<R> list, Param<P> param);

    @Override
    protected void accept(Iterator<List<R>> data, Param<P> param) { }
}
