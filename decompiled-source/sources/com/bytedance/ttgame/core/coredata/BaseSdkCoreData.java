package com.bytedance.ttgame.core.coredata;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.bytedance.ttgame.channel.configs.SharedPrefsKey;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.core.event.Constant;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.bytedance.ttgame.main.internal.net.CronetDependAdapter;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeReceiver;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class BaseSdkCoreData {
    private static final String BOE = "boe";
    private static final String ON_LINE = "online";
    private static final String SANDBOX = "sandbox";
    public static Context appContext = null;
    private static boolean isNetworkAvailable = true;
    public Map<String, Object> channelConfig;
    public SdkConfig config;
    private int memoryLevel = 0;
    private boolean enableCronet = true;
    private boolean enableCacheService = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(Context context, SdkConfig sdkConfig) {
        if (context == null) {
            return;
        }
        appContext = context;
        this.config = sdkConfig;
        if (TextUtils.isEmpty(sdkConfig.gameId)) {
            SdkConfig sdkConfig2 = this.config;
            sdkConfig2.gameId = sdkConfig2.appId;
        }
        NetworkChangeManager.getInstance().register(context, new NetworkChangeReceiver.INetworkAvailable() { // from class: com.bytedance.ttgame.core.coredata.BaseSdkCoreData.1
            @Override // com.bytedance.ttgame.sdk.module.utils.NetworkChangeReceiver.INetworkAvailable
            public void isAvailable(boolean z) {
                boolean unused = BaseSdkCoreData.isNetworkAvailable = z;
            }
        });
        appContext.registerComponentCallbacks(new ComponentCallbacks2() { // from class: com.bytedance.ttgame.core.coredata.BaseSdkCoreData.2
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks2
            public void onTrimMemory(int i2) {
                ICoreInternalService iCoreInternalService;
                BaseSdkCoreData.this.memoryLevel = i2;
                if (i2 != 15 || (iCoreInternalService = (ICoreInternalService) ModuleManager.INSTANCE.getService(ICoreInternalService.class)) == null) {
                    return;
                }
                iCoreInternalService.sendLog(Constant.APP_MEMORY_WARNING, new JSONObject());
            }
        });
    }

    public String getEnv() {
        return this.config.mIsBoe ? "boe" : this.config.mIsSandBox ? "sandbox" : "online";
    }

    public static boolean isNetworkAvailable() {
        return isNetworkAvailable;
    }

    public boolean isDebug() {
        try {
            SdkConfig sdkConfig = this.config;
            if (sdkConfig == null || !sdkConfig.mIsDebug) {
                Context context = appContext;
                if (context == null) {
                    return false;
                }
                if (!context.getSharedPreferences("shared_prefs_doraemon", 0).getBoolean(SharedPrefsKey.DEBUG_FLAG_OPEN, false)) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public int getMemoryLevel() {
        return this.memoryLevel;
    }

    public boolean isEnableCronet() {
        return this.enableCronet && isCronetSoExit();
    }

    public void setEnableCronet(boolean z) {
        this.enableCronet = z;
    }

    private boolean isCronetSoExit() {
        return CronetDependAdapter.b();
    }

    public boolean isEnableCacheService() {
        return this.enableCacheService;
    }

    public void setEnableCacheService(boolean z) {
        this.enableCacheService = z;
    }
}
