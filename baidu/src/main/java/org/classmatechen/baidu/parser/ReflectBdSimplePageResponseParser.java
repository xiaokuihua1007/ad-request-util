package org.classmatechen.baidu.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.classmatechen.baidu.BaiduInit;
import org.classmatechen.baidu.error.BdAccessTokenError;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.PageResponseParser;
import org.classmatechen.basic.req.page.simple.SimplePageResponse;
import org.classmatechen.basic.res.PageResponse;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.basic.util.UnSafeUtil;

import com.baidu.dev2.api.sdk.common.ApiErrorInfo;
import com.baidu.dev2.api.sdk.common.ApiResponseHeader;

public class ReflectBdSimplePageResponseParser extends AbstarctBdReflectResponseParser implements PageResponseParser {

    private final List<Long> codes = Arrays.asList(894061L, 89405L);

    private final MethodDefinition<?> getData;

    public ReflectBdSimplePageResponseParser(MethodDefinition<?> getData) {
        this.getData = getData;
    }

    @Override
    public <R> PageResponse<R> parse(Object object, Class<R> cls) {

        checkHeader(object);
        List<R> list = UnSafeUtil.toList(ReflectUtil.invoke(object, this.getData.getMethod()), cls);
        return new SimplePageResponse<>(list);
    }

    private void checkHeader(Object object) {

        ApiResponseHeader header;
        try {
            header = UnSafeUtil.toObject(ReflectUtil.invoke(object, BaiduInit.getHeader.getMethod()), BaiduInit.getHeader.getReturnType());
        } catch (Exception e) {
            throw new RuntimeException("parse baidu request header error");
        }
        List<ApiErrorInfo> failures = header.getFailures();
        if (Objects.nonNull(failures) && failures.size() > 0) {
            for (ApiErrorInfo info : failures) {
                if (codes.contains(info.getCode())) {
                    throw new BdAccessTokenError(info.getMessage());
                }
            }
            throw new RuntimeException(failures.get(0).getMessage());
        }
    }

    @Override
    public List<MethodDefinition<?>> requiredMethods() {
        return Arrays.asList(
            this.getData
        );
    }
}
