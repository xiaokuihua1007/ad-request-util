package org.classmatechen.basic.req.page;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.Context;
import org.classmatechen.basic.Request;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.res.ResponseImpl;

/**
 * 把请求包装为可以翻页的请求
 */
public class PageRequest<P extends Page, R> implements Request<P, Iterator<List<R>>> {

    private Request<P, List<R>> request;

    public PageRequest(Request<P, List<R>> request) {
        this.request = request;
    }

    @Override
    public Response<Iterator<List<R>>> request(Context context, P param) {

        Response<List<R>> res = this.request.request(context, param);
        if (!(res instanceof PageResponse)) {
            throw new RuntimeException("this request is not pageable");
        }
        return new ResponseImpl<Iterator<List<R>>>(new Inner<P, R>(request, context, param, (PageResponse<R>) res));
    }

    private static class Inner<P extends Page, R> implements Iterator<List<R>> {

        private final Request<P, List<R>> request;
        private final Context context;
        private final P param;
        private PageResponse<R> response;
        private boolean callHasNext = false;

        private Inner(Request<P, List<R>> request, Context context, P param, PageResponse<R> data) {
            this.request = request;
            this.context = context;
            this.param = param;
            this.response = data;
        }

        @Override
        public boolean hasNext() {

            callHasNext = true;

            if (Objects.isNull(response)) {
                return false;
            }
            List<R> list = response.getData();
            return Objects.nonNull(list) && list.size() > 0;
        }

        @Override
        public List<R> next() {

            if (!callHasNext) {
                throw new RuntimeException("must call hasNext first");
            } else {
                callHasNext = false;
            }

            List<R> list = response.getData();
            if (this.response.update(param)) {
                this.response = (PageResponse<R>) this.request.request(context, param);
            } else {
                this.response = null;
            }
            return list;
        }
    }
}
