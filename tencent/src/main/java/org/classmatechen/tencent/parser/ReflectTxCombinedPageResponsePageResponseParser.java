package org.classmatechen.tencent.parser;

import java.util.Arrays;
import java.util.List;

import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.PageResponseParser;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;
import org.classmatechen.tencent.basic.CombinedPageResponse;

public class ReflectTxCombinedPageResponsePageResponseParser extends AbstarctTxReflectResponseParser implements PageResponseParser {

    private final MethodDefinition<?> getList;
    private final MethodDefinition<Long> getTotalPage;
    private final MethodDefinition<String> getNextCursor;

    public ReflectTxCombinedPageResponsePageResponseParser(
        MethodDefinition<?> getList,
        MethodDefinition<Long> getTotalPage,
        MethodDefinition<String> getNextCursor
    ) {
        this.getList = getList;
        this.getTotalPage = getTotalPage;
        this.getNextCursor = getNextCursor;
    }

    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {

        List<R> list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
        Long totalPage = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getTotalPage.getMethod()), this.getTotalPage.getReturnType());
        String nextCursor = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getNextCursor.getMethod()), this.getNextCursor.getReturnType());
        return new CombinedPageResponse<>(list, totalPage, nextCursor);
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        return Arrays.asList(
            this.getList,
            this.getTotalPage,
            this.getNextCursor
        );
    }
}
