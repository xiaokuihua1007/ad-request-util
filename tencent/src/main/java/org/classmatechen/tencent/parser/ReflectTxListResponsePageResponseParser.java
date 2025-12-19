package org.classmatechen.tencent.parser;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.parser.ListResponseParser;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.res.ListResponse;
import org.classmatechen.basic.res.ListResponseImpl;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;

public class ReflectTxListResponsePageResponseParser extends AbstarctTxReflectResponseParser implements ListResponseParser {

    private MethodDefinition<?> getList;

    public ReflectTxListResponsePageResponseParser(MethodDefinition<?> getList) {
        this.getList = getList;
    }

    @Override
    public <R> ListResponse<R> parse(Object object, Class<R> cls) {

        List<R> list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
        return new ListResponseImpl<>(list);
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        return Arrays.asList(
            this.getList
        );
    }
}
