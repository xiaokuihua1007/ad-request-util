package org.classmatechen.oceanengine.parser;

import java.util.Arrays;
import java.util.List;
import org.classmatechen.basic.parser.ListResponseParser;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.res.ListResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;
import org.classmatechen.oceanengine.res.DyListResponse;

public class ReflectDyListResponseParser extends AbstarctDyReflectResponseParser implements ListResponseParser {

    private final MethodDefinition<?> getList;

    public ReflectDyListResponseParser(MethodDefinition<?> getList) {
        this.getList = getList;
    }

    @Override
    public <R> ListResponse<R> parse(Object object, Class<R> cls) {

        Long code = getCode(object);
        String message = getMessage(object);
        List<R> list = null;
        if (code == 0) {
            list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
        }
        return new DyListResponse<>(list, code, message);
    }

    @Override
    public List<MethodDefinition<?>> requiredMoreMethods() {
        return Arrays.asList(
            this.getList
        );
    }
}
