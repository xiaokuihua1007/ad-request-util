package org.classmatechen.baidu.request;

import java.util.List;

import org.classmatechen.baidu.ReflectBdRequest;
import org.classmatechen.basic.Response;
import org.classmatechen.basic.parser.Parser;

import com.baidu.dev2.api.sdk.account.api.AccountService;
import com.baidu.dev2.api.sdk.account.model.ApiAccountQueryRequest;
import com.baidu.dev2.api.sdk.account.model.ApiAccountType;
import com.baidu.dev2.api.sdk.account.model.GetAccountInfoRequestWrapper;
import com.baidu.dev2.api.sdk.invoke.ApiException;

/**
 * 查询账户
 * https://dev2.baidu.com/content?sceneType=0&pageId=100256&nodeId=63&subhead=
 */
public class GetAccountInfo extends ReflectBdRequest<ApiAccountQueryRequest, List<ApiAccountType>> {

    @Override
    protected Class<?> getWrapper() {
        return GetAccountInfoRequestWrapper.class;
    }

    @Override
    protected Response<List<ApiAccountType>> todo(Object wrapper) throws ApiException {
        return Parser.list(new AccountService().getAccountInfo((GetAccountInfoRequestWrapper) wrapper), ApiAccountType.class);
    }
}
