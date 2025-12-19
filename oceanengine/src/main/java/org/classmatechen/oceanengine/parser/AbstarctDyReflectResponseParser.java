package org.classmatechen.oceanengine.parser;

import java.util.ArrayList;
import java.util.List;

import org.classmatechen.basic.parser.AbstarctReflectResponseParser;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;

public abstract class AbstarctDyReflectResponseParser extends AbstarctReflectResponseParser {

    public static final MethodDefinition<Long> getCode = new MethodDefinition<>("getCode", Long.class);
    public static final MethodDefinition<String> getMessage = new MethodDefinition<>("getMessage", String.class);

    @Override
    public String supportPackage() {
        return "com.bytedance.ads.model";
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        List<MethodDefinition<?>> list = new ArrayList<>();
        list.add(getCode);
        list.add(getMessage);
        list.addAll(requiredMoreMethods());
        return list;
    }

    protected abstract List<MethodDefinition<?>> requiredMoreMethods();

    protected Long getCode(Object object) {
        return UnSafeUtil.toObject(ReflectUtil.invoke(object, getCode.getMethod()), getCode.getReturnType());
    }

    protected String getMessage(Object object) {
        return UnSafeUtil.toObject(ReflectUtil.invoke(object, getMessage.getMethod()), getMessage.getReturnType());
    }
}
