package com.bytedance.ttgame.module.account.channel.toutiao;

import android.text.TextUtils;
import com.bytedance.frameworks.baselib.network.http.cronet.impl.b;
import com.bytedance.frameworks.baselib.network.http.exception.d;
import com.bytedance.ttgame.account.R;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.main.internal.INetService;
import com.bytedance.ttgame.module.account.api.IAccountErrorHandleService;
import com.bytedance.ttgame.module.account.toutiao.account.utils.LoginErrorCode;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.sdk.module.utils.CertDateInvalidUtlis;
import com.meituan.robust.Constants;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public class AccountErrorHandleService implements IAccountErrorHandleService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private final IAccountErrorHandleService.CloseError closeError = new IAccountErrorHandleService.CloseError() { // from class: com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService.1
        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.CloseError
        public GSDKError createGSDKError() {
            return new GSDKError(LoginErrorCode.GSDK_PANEL_CLOSED, "");
        }
    };
    private final IAccountErrorHandleService.ClientError clientError = new IAccountErrorHandleService.ClientError() { // from class: com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService.2
        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.ClientError
        public GSDKError createGSDKError(String str) {
            return createGSDKError(-105999, str);
        }

        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.ClientError
        public GSDKError createGSDKError(int i2, String str) {
            return new GSDKError(-105999, ModuleManager.INSTANCE.getAppContext().getResources().getString(R.string.gsdk_account_client_error), i2, str);
        }
    };
    private final IAccountErrorHandleService.NetworkError networkError = new IAccountErrorHandleService.NetworkError() { // from class: com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService.3
        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.NetworkError
        public GSDKError createGSDKError() {
            return createGSDKError(null);
        }

        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.NetworkError
        public GSDKError createGSDKError(Throwable th) {
            String str = "";
            if (th != null) {
                if (TextUtils.isEmpty(th.getMessage())) {
                    String str2 = "ExceptionType:" + th.getClass().toString() + ", ";
                    str = th instanceof b ? str2 + "StatusCode:" + ((b) th).d() + "" : str2;
                } else {
                    str = th.getMessage();
                }
            }
            return new GSDKError(-103001, getNetworkErrorGMsg(th), getNetworkErrorCode(th), str);
        }

        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.NetworkError
        public String getNetworkErrorGMsg(Throwable th) {
            if (th != null && CertDateInvalidUtlis.isCertDateInvalid(th.getMessage())) {
                return ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getAppContext().getResources().getString(R.string.gsdk_account_cert_date_invalid);
            }
            return ((ICoreInternalService) ModuleManager.INSTANCE.getService(ICoreInternalService.class)).getAppContext().getResources().getString(R.string.gsdk_account_network_error);
        }

        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.NetworkError
        public int getNetworkErrorCode(Throwable th) {
            INetService iNetService = (INetService) ModuleManager.INSTANCE.getService(INetService.class);
            return ((iNetService == null || !(iNetService.getNetEffectiveConnectionType() == -1 || iNetService.getNetEffectiveConnectionType() == 1)) && !(th instanceof d)) ? -3000 : -3011;
        }
    };
    private final IAccountErrorHandleService.CancelError cancelError = new IAccountErrorHandleService.CancelError() { // from class: com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService.4
        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.CancelError
        public GSDKError createGSDKError(String str) {
            return createGSDKError(str, null);
        }

        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.CancelError
        public GSDKError createGSDKError(String str, String str2) {
            return new GSDKError(-100001, str, -1001, str2);
        }
    };
    private final IAccountErrorHandleService.SharkError sharkError = new IAccountErrorHandleService.SharkError() { // from class: com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService.5
        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.SharkError
        public GSDKError createGSDKError(int i2, String str) {
            return new GSDKError(LoginErrorCode.SHARK_DIALOG_DISMISS, str, i2, str);
        }
    };
    private final IAccountErrorHandleService.Success success = new IAccountErrorHandleService.Success() { // from class: com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService.6
        @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService.Success
        public GSDKError createGSDKError() {
            return new GSDKError(0, "success");
        }
    };

    private int getOSDKErrorCode(int i2) {
        if (i2 != -21) {
            if (i2 != 7) {
                if (i2 != -3) {
                    if (i2 != -2) {
                        switch (i2) {
                            case -16:
                            case -15:
                            case -14:
                            case -13:
                            case -12:
                                return -103001;
                            default:
                                return LoginErrorCode.OSDK_OTHER;
                        }
                    }
                    return -100001;
                }
                return -103001;
            }
            return -100011;
        }
        return -103001;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public GSDKError convertError(int i2, String str) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "convertError", new String[]{Constants.INT, "java.lang.String"}, "com.bytedance.ttgame.base.GSDKError");
        return new GSDKError(i2, str);
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.CloseError getCloseError() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getCloseError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$CloseError");
        return this.closeError;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.ClientError getClientError() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getClientError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$ClientError");
        return this.clientError;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.NetworkError getNetworkError() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getNetworkError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$NetworkError");
        return this.networkError;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.CancelError getCancelError() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getCancelError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$CancelError");
        return this.cancelError;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.SharkError getSharkError() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getSharkError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$SharkError");
        return this.sharkError;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.Success getSuccess() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getSuccess", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$Success");
        return this.success;
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public GSDKError convertOSDKError(int i2, String str) {
        String str2;
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "convertOSDKError", new String[]{Constants.INT, "java.lang.String"}, "com.bytedance.ttgame.base.GSDKError");
        try {
            str2 = new JSONObject().put("code", i2).put("message", str).toString();
        } catch (JSONException unused) {
            str2 = "";
        }
        return new GSDKError(getOSDKErrorCode(i2), str, i2, str, str2);
    }
}
