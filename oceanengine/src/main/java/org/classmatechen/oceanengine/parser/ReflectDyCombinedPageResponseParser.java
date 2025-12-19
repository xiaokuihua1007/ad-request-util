package org.classmatechen.oceanengine.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.PageResponseParser;
import org.classmatechen.basic.req.page.combined.CombinedPageResponse;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;
import org.classmatechen.oceanengine.res.DyPageResponse;

public class ReflectDyCombinedPageResponseParser extends AbstarctDyReflectResponseParser implements PageResponseParser {

    private final MethodDefinition<?> getList;
    private final MethodDefinition<Long> getTotalPage;
    private final MethodDefinition<Long> getCursor;
    private final MethodDefinition<Boolean> getHasMore;

    public ReflectDyCombinedPageResponseParser(
        MethodDefinition<?> getList,
        MethodDefinition<Long> getTotalPage,
        MethodDefinition<Long> getCursor,
        MethodDefinition<Boolean> getHasMore
    ) {
        this.getList = getList;
        this.getTotalPage = getTotalPage;
        this.getCursor = getCursor;
        this.getHasMore = getHasMore;
    }


    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {

        Long code = getCode(object);
        String message = getMessage(object);
        List<R> list = null;
        Long totalPage = null;
        Long cursor = null;
        if (code == 0) {
            list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
            totalPage = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getTotalPage.getMethod()), this.getTotalPage.getReturnType());
            cursor = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getCursor.getMethod()), this.getCursor.getReturnType());
            Boolean hasMore = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getHasMore.getMethod()), this.getHasMore.getReturnType());
            if (Objects.isNull(hasMore) || !hasMore) {
                cursor = null;
            }
        }
        return new DyPageResponse<>(list, code, message, new CombinedPageResponse<>(list, totalPage, cursor));
    }

    @Override
    protected List<MethodDefinition<?>> requiredMoreMethods() {
        return Arrays.asList(
            this.getList,
            this.getTotalPage,
            this.getCursor,
            this.getHasMore
        );
    }
}
