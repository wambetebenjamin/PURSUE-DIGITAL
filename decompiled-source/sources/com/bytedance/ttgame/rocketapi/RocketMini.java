package com.bytedance.ttgame.rocketapi;

import android.content.Context;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.log.IGLogUploadCallback;
import com.bytedance.ttgame.rocketapi.callback.ILibLoader;
import com.bytedance.ttgame.rocketapi.callback.InitCallback;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: RocketMini.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J'\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eJ\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u001c\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J0\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u00102\b\u0010 \u001a\u0004\u0018\u00010\u001d2\b\u0010!\u001a\u0004\u0018\u00010\u001d2\b\u0010\"\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010#\u001a\u00020\u00052\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001c\u0010&\u001a\u00020\u00052\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020)0(H\u0016J(\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u000200H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/RocketMini;", "Lcom/bytedance/ttgame/rocketapi/IRocketMiniApi;", "()V", "rocketApi", "enableCacheService", "", "enable", "", "enableCronet", "getComponent", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getDeviceID", "", "getSDKConfig", "Lcom/bytedance/ttgame/core/SdkConfig;", "init", "application", "Landroid/content/Context;", "initCallback", "Lcom/bytedance/ttgame/rocketapi/callback/InitCallback;", "initAfterAttachBaseContext", "mainProcessName", "monitorCommonLog", "logType", "logExtr", "Lorg/json/JSONObject;", "monitorEvent", "serviceName", "category", "metric", "extraLog", "setLibraryLoader", "libLoader", "Lcom/bytedance/ttgame/rocketapi/callback/ILibLoader;", "setSDKConfig", "sdkConfigMap", "", "", "uploadLog", "startTime", "", "endTime", "scene", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/main/internal/log/IGLogUploadCallback;", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class RocketMini implements IRocketMiniApi {
    public static final RocketMini INSTANCE = new RocketMini();
    private static IRocketMiniApi rocketApi;

    private RocketMini() {
    }

    static {
        try {
            rocketApi = (IRocketMiniApi) ModuleManager.getPublicService$default(ModuleManager.INSTANCE, IRocketMiniApi.class, null, 2, null);
        } catch (Throwable unused) {
            Log.d("gsdk_init", "can not find IRocketMiniApi impl");
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void setSDKConfig(Map<String, ? extends Object> sdkConfigMap) {
        Intrinsics.checkNotNullParameter(sdkConfigMap, "sdkConfigMap");
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.setSDKConfig(sdkConfigMap);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public SdkConfig getSDKConfig() {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            return iRocketMiniApi.getSDKConfig();
        }
        return null;
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void initAfterAttachBaseContext(Context application) {
        Intrinsics.checkNotNullParameter(application, "application");
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.initAfterAttachBaseContext(application);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void initAfterAttachBaseContext(Context application, String mainProcessName) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(mainProcessName, "mainProcessName");
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.initAfterAttachBaseContext(application, mainProcessName);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void init(Context application, InitCallback initCallback) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(initCallback, "initCallback");
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.init(application, initCallback);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public String getDeviceID() {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            return iRocketMiniApi.getDeviceID();
        }
        return null;
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public <T extends IModuleApi> T getComponent(Class<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            return (T) iRocketMiniApi.getComponent(clazz);
        }
        return null;
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void setLibraryLoader(ILibLoader iLibLoader) {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.setLibraryLoader(iLibLoader);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void enableCronet(boolean z) {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.enableCronet(z);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void enableCacheService(boolean z) {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.enableCacheService(z);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void monitorCommonLog(String str, JSONObject jSONObject) {
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.monitorCommonLog(str, jSONObject);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.IRocketMiniApi
    public void uploadLog(long j2, long j3, String scene, IGLogUploadCallback callback) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Intrinsics.checkNotNullParameter(callback, "callback");
        IRocketMiniApi iRocketMiniApi = rocketApi;
        if (iRocketMiniApi != null) {
            iRocketMiniApi.uploadLog(j2, j3, scene, callback);
        }
    }
}
