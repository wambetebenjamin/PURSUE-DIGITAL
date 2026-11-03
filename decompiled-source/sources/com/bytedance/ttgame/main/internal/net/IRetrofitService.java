package com.bytedance.ttgame.main.internal.net;

import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
@InternalApi
/* loaded from: classes3.dex */
public interface IRetrofitService extends IModuleApi {
    IRetrofit createAccountBsdkRetrofit();

    IRetrofit createAccountGsdkRetrofit();

    IRetrofit createNewRetrofit(String str);

    String getAccountBsdkUrl();

    String getAccountGsdkUrl();

    String getHostToPassport();

    void initHost(SdkConfig sdkConfig);

    void mergeAccountHost(boolean z);

    boolean needMergeAccountHost();

    boolean useAccountPrivatizationHost();
}
