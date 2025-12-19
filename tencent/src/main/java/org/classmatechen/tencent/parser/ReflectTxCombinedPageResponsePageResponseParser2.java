package org.classmatechen.tencent.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.PageResponseParser;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;
import org.classmatechen.tencent.basic.TxCombinedPageResponse;

public class ReflectTxCombinedPageResponsePageResponseParser2 extends AbstarctTxReflectResponseParser implements PageResponseParser {

    private final MethodDefinition<?> getList;
    private final MethodDefinition<Long> getTotalPage;
    private final MethodDefinition<Long> getCursor;
    private final MethodDefinition<Boolean> isHasMore;

    public ReflectTxCombinedPageResponsePageResponseParser2(
        MethodDefinition<?> getList,
        MethodDefinition<Long> getTotalPage,
        MethodDefinition<Long> getCursor,
        MethodDefinition<Boolean> isHasMore
    ) {
        this.getList = getList;
        this.getTotalPage = getTotalPage;
        this.getCursor = getCursor;
        this.isHasMore = isHasMore;
    }

    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {

        List<R> list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getList.getMethod()), cls);
        Long totalPage = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getTotalPage.getMethod()), this.getTotalPage.getReturnType());
        Long cursor = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.getCursor.getMethod()), this.getCursor.getReturnType());
        Boolean isHasMore = UnSafeUtil.toObject(ReflectUtil.invoke(object, this.isHasMore.getMethod()), this.isHasMore.getReturnType());
        if (Objects.isNull(isHasMore) || !isHasMore) {
            cursor = null;
        }
        return new TxCombinedPageResponse<>(list, totalPage, cursor);
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        return Arrays.asList(
            this.getList,
            this.getCursor,
            this.isHasMore
        );
    }
}
