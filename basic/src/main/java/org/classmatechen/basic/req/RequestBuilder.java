package org.classmatechen.basic.req;

import java.util.Iterator;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.req.limit.LimitRequest;
import org.classmatechen.basic.req.page.Page;
import org.classmatechen.basic.req.page.PageRequest;
import org.classmatechen.basic.req.retry.RetryRequest;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RequestBuilder<P, R> {

    private Request request;
    private RequestBuilder self;

    public RequestBuilder(Request<P, R> request) {
        this.request = request;
        self = this;
    }

    public RequestBuilder<P, R> limit() {
        self.request = new LimitRequest(self.request);
        return (RequestBuilder<P, R>) self;
    }

    public RequestBuilder<P, R> limit(long millis) {
        self.request = new LimitRequest(self.request, millis);
        return (RequestBuilder<P, R>) self;
    }

    public RequestBuilder<P, R> retry() {
        self.request = new RetryRequest(self.request);
        return (RequestBuilder<P, R>) self;
    }

    public RequestBuilder<P, R> retry(int retry) {
        self.request = new RetryRequest(self.request, retry);
        return (RequestBuilder<P, R>) self;
    }

    public RequestBuilder<P, Iterator<R>> page() {
        Request request = new PageRequest(self.request);
        self = new Inner(request);
        return (RequestBuilder<P, Iterator<R>>) self;
    }

    public Request<P, R> build() {
        return (Request<P, R>) self.request;
    }

    private static class Inner<P extends Page, R> extends RequestBuilder<P, Iterator<R>> {

        public Inner(Request<P, Iterator<R>> request) {
            super(request);
        }

        @Override
        public Request<P, Iterator<R>> build() {
            return (Request<P, Iterator<R>>) super.request;
        }
    }
}
