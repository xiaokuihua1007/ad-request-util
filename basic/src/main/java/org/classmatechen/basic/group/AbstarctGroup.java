package org.classmatechen.basic.group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.classmatechen.basic.Request;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstarctGroup<P, R> implements Group<P> {

    private Request<P, R> request;

    public AbstarctGroup(Request<P, R> request) {
        this.request = request;
    }

    @Override
    public List<GroupFail<P>> execute() {

        int i = 0;
        Iterator<Param<P>> params = getParams();
        List<GroupFail<P>> fail = new ArrayList<>();
        log.info("group request start ...");
        while (params.hasNext()) {
            i++;
            Param<P> param = params.next();
            log.debug("request {} start ...", i);
            try {
                request(this.request, param);
                log.info("request {} finish ...", i);
            } catch (Exception e) {
                fail.add(new GroupFail<>(param, e.getMessage()));
                log.error("request {} error occur ... param: {} error: {}", i, param, e.getMessage());
            }
        }
        log.info("group request finish ...");
        return fail.isEmpty() ? null : fail;
    }

    protected abstract void request(Request<P, R> request, Param<P> param);

    protected abstract Iterator<Param<P>> getParams();
    
    protected abstract void accept(R data, Param<P> param);
}
