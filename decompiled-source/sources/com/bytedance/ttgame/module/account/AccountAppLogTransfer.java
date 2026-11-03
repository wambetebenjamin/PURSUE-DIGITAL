package com.bytedance.ttgame.module.account;

import android.util.Log;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IGameSdkConfigService;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer;
import com.bytedance.ttgame.module.account.api.LoginLogger;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: AccountAppLogTransfer.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/module/account/AccountAppLogTransfer;", "Lcom/bytedance/ttgame/module/account/api/IAccountAppLogTransfer;", "()V", "GODZILLA_CATCH_SQLITE_EXCEPTION", "", "catchSQLiteException", "", "threadName", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AccountAppLogTransfer implements IAccountAppLogTransfer {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private final String GODZILLA_CATCH_SQLITE_EXCEPTION = "godzilla_catch_sqlite_exception";

    @Override // com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer
    public void catchSQLiteException(String str, Exception exc) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer", "com.bytedance.ttgame.module.account.AccountAppLogTransfer", "catchSQLiteException", new String[]{"java.lang.String", "java.lang.Exception"}, Constants.VOID);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("thread_name", str);
            jSONObject.put("crash_info", Log.getStackTraceString(exc));
            IGameSdkConfigService iGameSdkConfigService = (IGameSdkConfigService) ModuleManager.getService$default(ModuleManager.INSTANCE, IGameSdkConfigService.class, false, (String) null, 6, (Object) null);
            jSONObject.put("ui_flag", iGameSdkConfigService != null ? Integer.valueOf(iGameSdkConfigService.getUiFlag()) : null);
            jSONObject.put("timestamp", System.currentTimeMillis());
            IMainInternalService iMainInternalService = (IMainInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
            if (iMainInternalService != null) {
                iMainInternalService.sendLog(this.GODZILLA_CATCH_SQLITE_EXCEPTION, jSONObject);
            }
        } catch (Exception e2) {
            LoginLogger.w("AppLogTransferService", e2);
        }
    }
}
