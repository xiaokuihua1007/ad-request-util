package org.classmatechen.basic.group.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.Request;
import org.classmatechen.basic.group.AbstractListGroup;
import org.classmatechen.basic.group.Consumer;
import org.classmatechen.basic.group.Param;

public class ListGroup<P, R> extends AbstractListGroup<P, R> {

    private Iterator<Param<P>> params;
    private Consumer<P, List<R>> consumer;

    public ListGroup(Request<P, List<R>> request) {
        super(request);
    }

    public ListGroup(Request<P, List<R>> request, Consumer<P, List<R>> consumer) {
        super(request);
        this.consumer = consumer;
    }

    public ListGroup(Request<P, List<R>> request, Iterator<Param<P>> params, Consumer<P, List<R>> consumer) {
        super(request);
        this.params = params;
        this.consumer = consumer;
    }

    public ListGroup(Request<P, List<R>> request, List<Param<P>> params, Consumer<P, List<R>> consumer) {
        super(request);
        this.params = params.iterator();
        this.consumer = consumer;
    }

    public ListGroup(Request<P, List<R>> request, Param<P> param, Consumer<P, List<R>> consumer) {
        super(request);
        this.params = Arrays.asList(param).iterator();
        this.consumer = consumer;
    }

    public void setParam(Iterator<Param<P>> params) {
        this.params = params;
    }

    public void setParam(List<Param<P>> params) {
        this.params = params.iterator();
    }

    public void setParam(Param<P> params) {
        this.params = Arrays.asList(params).iterator();
    }

    public void setConsumer(Consumer<P, List<R>> consumer) {
        this.consumer = consumer;
    }

    @Override
    protected Iterator<Param<P>> getParams() {
        if (Objects.isNull(this.params)) {
            throw new RuntimeException("no param");
        }
        return this.params;
    }

    @Override
    protected void accept(List<R> list, Param<P> param) {
        if (Objects.isNull(consumer)) {
            return;
        }
        consumer.accept(param.getContext(), param.getParam(), list);
    }
}
