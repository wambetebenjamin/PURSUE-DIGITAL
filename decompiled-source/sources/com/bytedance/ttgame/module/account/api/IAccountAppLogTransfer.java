package com.bytedance.ttgame.module.account.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import kotlin.Metadata;
/* compiled from: IAccountAppLogTransfer.kt */
@InternalApi
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH&¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/account/api/IAccountAppLogTransfer;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "catchSQLiteException", "", "threadName", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IAccountAppLogTransfer extends IModuleApi {
    void catchSQLiteException(String str, Exception exc);
}
