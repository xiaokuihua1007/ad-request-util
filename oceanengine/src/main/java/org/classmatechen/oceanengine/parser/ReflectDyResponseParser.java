package org.classmatechen.oceanengine.parser;

import java.util.Arrays;
import java.util.List;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.ObjectResponseParser;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;
import org.classmatechen.oceanengine.res.DyResponse;

public class ReflectDyResponseParser extends AbstarctDyReflectResponseParser implements ObjectResponseParser {

    private final MethodDefinition<?> getData;

    public ReflectDyResponseParser(MethodDefinition<?> getData) {
        this.getData = getData;
    }

    @Override
    public <R> Response<R> parse(Object object, Class<R> cls) {
        Long code = getCode(object);
        String message = getMessage(object);
        R data = null;
        if (code == 0) {
            data = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getData.getMethod()), cls);
        }
        return new DyResponse<R>(data, code, message);
    }

    @Override
    protected List<MethodDefinition<?>> requiredMoreMethods() {
        return Arrays.asList(
            this.getData
        );
    }
}
