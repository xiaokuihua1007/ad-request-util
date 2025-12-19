package org.classmatechen.tencent.parser;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.PageResponseParser;
import org.classmatechen.basic.req.page.classic.ClassicPageResponse;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;

public class ReflectTxClassicPageResponsePageResponseParser extends AbstarctTxReflectResponseParser implements PageResponseParser {

    private final MethodDefinition<?> getList;
    private final MethodDefinition<Long> getTotalPage;

    public ReflectTxClassicPageResponsePageResponseParser(MethodDefinition<?> getList, MethodDefinition<Long> getTotalPage) {
        this.getList = getList;
        this.getTotalPage = getTotalPage;
    }

    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {

        List<R> list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
        Long totalPage = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getTotalPage.getMethod()), this.getTotalPage.getReturnType());
        return new ClassicPageResponse<>(list, totalPage);
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        return Arrays.asList(
            this.getList,
            this.getTotalPage
        );
    }
}
