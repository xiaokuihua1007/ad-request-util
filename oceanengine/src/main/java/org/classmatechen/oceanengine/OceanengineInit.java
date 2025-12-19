package org.classmatechen.oceanengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.retry.ExceptionHelper;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.oceanengine.client.ApiClientProvider;
import org.classmatechen.oceanengine.client.impl.AccessTokenProvider;
import org.classmatechen.oceanengine.client.impl.ApiClientProviderImpl;
import org.classmatechen.oceanengine.client.impl.init.InitAccessTokenProvider;
import org.classmatechen.oceanengine.client.impl.init.OceanengineProvider;
import org.classmatechen.oceanengine.client.impl.refresh.RefreshTokenAccessTokenProvider;
import org.classmatechen.oceanengine.client.impl.refresh.RefreshTokenProvider;
import org.classmatechen.oceanengine.error.AccessTokenHandler;
import org.classmatechen.oceanengine.error.QPSHandler;
import org.classmatechen.oceanengine.error.RefreshTokenHandler;
import org.classmatechen.oceanengine.parser.ReflectDyClassicPageResponseParser;
import org.classmatechen.oceanengine.parser.ReflectDyCombinedPageResponseParser;
import org.classmatechen.oceanengine.parser.ReflectDyListResponseParser;
import org.classmatechen.oceanengine.parser.ReflectDyResponseParser;

public class OceanengineInit {

    private static final MethodDefinition<Long> getTotalPage = new MethodDefinition<>("getData.getPageInfo.getTotalPage", Long.class);
    private static final MethodDefinition<Long> getCursor = new MethodDefinition<>("getData.getCursorInfo.getCursor", Long.class);
    private static final MethodDefinition<Boolean> getHasMore = new MethodDefinition<>("getData.getCursorInfo.getHasMore", Boolean.class);

    private static final MethodDefinition<?> getData = new MethodDefinition<>("getData");
    private static final MethodDefinition<?> getList = new MethodDefinition<>("getData.getList");
    private static final MethodDefinition<?> getLogs = new MethodDefinition<>("getData.getLogs");
    private static final MethodDefinition<?> getRows = new MethodDefinition<>("getData.getRows");
    private static final MethodDefinition<?> getCompensateStatusInfoList = new MethodDefinition<>("getData.getCompensateStatusInfoList");

    private final List<ApiClientProvider> providers;

    public OceanengineInit(List<AccessTokenProvider> providers0, List<RefreshTokenProvider> providers1, List<OceanengineProvider> providers2) {

        this.providers = new ArrayList<>();
        if (Objects.nonNull(providers0)) {
            this.providers.addAll(providers0.stream().sorted().map(provider -> new ApiClientProviderImpl(provider)).collect(Collectors.toList()));
        }
        if (Objects.nonNull(providers1)) {
            this.providers.addAll(providers1.stream().sorted().map(provider -> new ApiClientProviderImpl(new RefreshTokenAccessTokenProvider(provider))).collect(Collectors.toList()));
        }
        if (Objects.nonNull(providers2)) {
            this.providers.addAll(providers2.stream().sorted().map(provider -> new ApiClientProviderImpl(new InitAccessTokenProvider(provider))).collect(Collectors.toList()));
        }

        init();
    }

    private void init() {

        ReflectUtil.setField("org.classmatechen.oceanengine.DyContextImpl.providers", providers);

        Parser.register(new ReflectDyCombinedPageResponseParser(getList, getTotalPage, getCursor, getHasMore));
        Parser.register(new ReflectDyClassicPageResponseParser(getRows, getTotalPage));
        Parser.register(new ReflectDyClassicPageResponseParser(getLogs, getTotalPage));
        Parser.register(new ReflectDyClassicPageResponseParser(getList, getTotalPage));
        Parser.register(new ReflectDyListResponseParser(getCompensateStatusInfoList));
        Parser.register(new ReflectDyListResponseParser(getList));
        Parser.register(new ReflectDyListResponseParser(getData));
        Parser.register(new ReflectDyResponseParser(getData));

        ExceptionHelper.register(new AccessTokenHandler());
        ExceptionHelper.register(new RefreshTokenHandler());
        ExceptionHelper.register(new QPSHandler(5000l));
    }
}
