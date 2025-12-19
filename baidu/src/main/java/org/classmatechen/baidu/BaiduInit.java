package org.classmatechen.baidu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.classmatechen.baidu.client.ApiRequestHeaderProvider;
import org.classmatechen.baidu.client.impl.AccessTokenProvider;
import org.classmatechen.baidu.client.impl.ApiRequestHeaderProviderImpl;
import org.classmatechen.baidu.client.impl.init.InitAccessTokenProvider;
import org.classmatechen.baidu.client.impl.init.BaiduProvider;
import org.classmatechen.baidu.client.impl.refresh.RefreshTokenAccessTokenProvider;
import org.classmatechen.baidu.client.impl.refresh.RefreshTokenProvider;
import org.classmatechen.baidu.error.BdAccessTokenHandler;
import org.classmatechen.baidu.parser.ReflectBdSimplePageResponseParser;
import org.classmatechen.basic.parser.MethodDefinition;
import org.classmatechen.basic.parser.Parser;
import org.classmatechen.basic.req.retry.ExceptionHelper;
import org.classmatechen.basic.util.ReflectUtil;

import com.baidu.dev2.api.sdk.common.ApiResponseHeader;

public class BaiduInit {

    public static final MethodDefinition<ApiResponseHeader> getHeader = new MethodDefinition<>("getHeader", ApiResponseHeader.class);
    private static final MethodDefinition<?> getData = new MethodDefinition<>("getBody.getData");

    private final List<ApiRequestHeaderProvider> providers;

    public BaiduInit(List<AccessTokenProvider> providers0, List<RefreshTokenProvider> providers1, List<BaiduProvider> providers2) {

        this.providers = new ArrayList<>();
        if (Objects.nonNull(providers0)) {
            this.providers.addAll(providers0.stream().sorted().map(provider -> new ApiRequestHeaderProviderImpl(provider)).collect(Collectors.toList()));
        }
        if (Objects.nonNull(providers1)) {
            this.providers.addAll(providers1.stream().sorted().map(provider -> new ApiRequestHeaderProviderImpl(new RefreshTokenAccessTokenProvider(provider))).collect(Collectors.toList()));
        }
        if (Objects.nonNull(providers2)) {
            this.providers.addAll(providers2.stream().sorted().map(provider -> new ApiRequestHeaderProviderImpl(new InitAccessTokenProvider(provider))).collect(Collectors.toList()));
        }
        init();
    }

    private void init() {

        ReflectUtil.setField("org.classmatechen.baidu.BdContextImpl.providers", providers);

        Parser.register(new ReflectBdSimplePageResponseParser(getData));

        ExceptionHelper.register(new BdAccessTokenHandler());
    }
}
