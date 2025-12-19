package org.classmatechen.tencent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.parser.ReflectListResponseParser;
import org.classmatechen.basic.req.retry.ExceptionHelper;
import org.classmatechen.basic.util.ReflectUtil;
import org.classmatechen.tencent.client.TencentAdsProvider;
import org.classmatechen.tencent.client.impl.AccessTokenProvider;
import org.classmatechen.tencent.client.impl.TencentAdsProviderImpl;
import org.classmatechen.tencent.client.impl.init.InitAccessTokenProvider;
import org.classmatechen.tencent.client.impl.init.TencentProvider;
import org.classmatechen.tencent.client.impl.refresh.RefreshTokenAccessTokenProvider;
import org.classmatechen.tencent.client.impl.refresh.RefreshTokenProvider;
import org.classmatechen.tencent.error.TxAccessTokenHandler;
import org.classmatechen.tencent.parser.ReflectTxClassicPageResponsePageResponseParser;
import org.classmatechen.tencent.parser.ReflectTxCombinedPageResponsePageResponseParser;
import org.classmatechen.tencent.parser.ReflectTxCombinedPageResponsePageResponseParser2;
import org.classmatechen.tencent.parser.ReflectTxListResponsePageResponseParser;

public class TencentInit {

    private static final MethodDefinition<?> getList = new MethodDefinition<>("getList");
    private static final MethodDefinition<?> getAdgroupList = new MethodDefinition<>("getAdgroupList");
    private static final MethodDefinition<Long> getTotalPage = new MethodDefinition<>("getPageInfo.getTotalPage", Long.class);
    private static final MethodDefinition<String> getNextCursor = new MethodDefinition<>("getCursorPageInfo.getNextCursor", String.class);
    private static final MethodDefinition<Boolean> isHasMore = new MethodDefinition<>("getCursorPageInfo.isHasMore", Boolean.class);
    private static final MethodDefinition<Long> getCursor = new MethodDefinition<>("getCursorPageInfo.getCursor", Long.class);

    private final List<TencentAdsProvider> providers;

    public TencentInit(List<AccessTokenProvider> providers0, List<RefreshTokenProvider> providers1, List<TencentProvider> providers2) {

        this.providers = new ArrayList<>();
        if (Objects.nonNull(providers0)) {
            this.providers.addAll(providers0.stream().sorted().map(provider -> new TencentAdsProviderImpl(provider)).collect(Collectors.toList()));
        }
        if (Objects.nonNull(providers1)) {
            this.providers.addAll(providers1.stream().sorted().map(provider -> new TencentAdsProviderImpl(new RefreshTokenAccessTokenProvider(provider))).collect(Collectors.toList()));
        }
        if (Objects.nonNull(providers2)) {
            this.providers.addAll(providers2.stream().sorted().map(provider -> new TencentAdsProviderImpl(new InitAccessTokenProvider(provider))).collect(Collectors.toList()));
        }
        init();
    }

    private void init() {

        ReflectUtil.setField("org.classmatechen.tencent.TxContextImpl.providers", providers);

        Parser.register(new ReflectTxCombinedPageResponsePageResponseParser2(getList, getTotalPage, getCursor, isHasMore));
        Parser.register(new ReflectTxCombinedPageResponsePageResponseParser(getList, getTotalPage, getNextCursor));
        Parser.register(new ReflectTxClassicPageResponsePageResponseParser(getList, getTotalPage));
        Parser.register(new ReflectListResponseParser("com.tencent.ads.model", getList));
        Parser.register(new ReflectTxListResponsePageResponseParser(getAdgroupList));

        ExceptionHelper.register(new TxAccessTokenHandler());
    }
}
