package org.classmatechen.oceanengine.parser;

import java.util.Arrays;
import java.util.List;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.PageResponseParser;
import org.classmatechen.basic.req.page.classic.ClassicPageResponse;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;
import org.classmatechen.oceanengine.res.DyPageResponse;

public class ReflectDyClassicPageResponseParser extends AbstarctDyReflectResponseParser implements PageResponseParser {

    private final MethodDefinition<?> getList;
    private final MethodDefinition<Long> getTotalPage;

    public ReflectDyClassicPageResponseParser(MethodDefinition<?> getList, MethodDefinition<Long> getTotalPage) {
        this.getList = getList;
        this.getTotalPage = getTotalPage;
    }


    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {

        Long code = getCode(object);
        String message = getMessage(object);
        List<R> list = null;
        Long totalPage = null;
        if (code == 0) {
            list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
            totalPage = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getTotalPage.getMethod()), this.getTotalPage.getReturnType());
        }
        return new DyPageResponse<>(list, code, message, new ClassicPageResponse<>(list, totalPage));
    }

    @Override
    protected List<MethodDefinition<?>> requiredMoreMethods() {
        return Arrays.asList(
            this.getList,
            this.getTotalPage
        );
    }
}
