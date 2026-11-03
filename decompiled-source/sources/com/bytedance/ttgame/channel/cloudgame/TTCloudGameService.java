package com.bytedance.ttgame.channel.cloudgame;

import android.app.Activity;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.module.account.api.LoginLogger;
import com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.cloudgame.api.ICloudGameService;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import com.meituan.robust.Constants;
/* loaded from: classes3.dex */
public class TTCloudGameService implements ITTCloudGameService {
    private static final String TAG = "gsdk_account_tt_cloud_game_service";
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService
    public boolean isCloudRuntime() {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", "com.bytedance.ttgame.channel.cloudgame.TTCloudGameService", "isCloudRuntime", new String[0], "boolean");
        ICloudGameService iCloudGameService = (ICloudGameService) ModuleManager.INSTANCE.getService(ICloudGameService.class);
        if (iCloudGameService != null) {
            return iCloudGameService.isCloudRuntime();
        }
        LoginLogger.e(TAG, "isCloudRuntime -> cloudGame 未引入");
        return false;
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService
    public void cloudGameLogin(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", "com.bytedance.ttgame.channel.cloudgame.TTCloudGameService", "cloudGameLogin", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        ICloudGameService iCloudGameService = (ICloudGameService) ModuleManager.INSTANCE.getService(ICloudGameService.class);
        if (iCloudGameService != null) {
            iCloudGameService.cloudGameLogin(activity, iAccountCallback);
        } else {
            LoginLogger.e(TAG, "cloudGameLogin -> cloudGame 未引入");
        }
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService
    public void cloudGameLoginV2(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", "com.bytedance.ttgame.channel.cloudgame.TTCloudGameService", "cloudGameLoginV2", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        ICloudGameService iCloudGameService = (ICloudGameService) ModuleManager.INSTANCE.getService(ICloudGameService.class);
        if (iCloudGameService != null) {
            iCloudGameService.cloudGameLoginV2(activity, iAccountCallback);
        } else {
            LoginLogger.e(TAG, "cloudGameLoginV2 -> cloudGame 未引入");
        }
    }
}
