package com.bytedance.ttgame.rocketapi;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.main.internal.log.IGLogUploadCallback;
import com.bytedance.ttgame.rocketapi.callback.ILibLoader;
import com.bytedance.ttgame.rocketapi.callback.InitCallback;
import java.util.Map;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: IRocketMiniApi.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J'\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH&¢\u0006\u0002\u0010\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\rH&J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0018\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\rH&J\u001c\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&J0\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001aH&J\u0012\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\"H&J\u001c\u0010#\u001a\u00020\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020&0%H&J(\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-H&¨\u0006."}, d2 = {"Lcom/bytedance/ttgame/rocketapi/IRocketMiniApi;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "enableCacheService", "", "enable", "", "enableCronet", "getComponent", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getDeviceID", "", "getSDKConfig", "Lcom/bytedance/ttgame/core/SdkConfig;", "init", "application", "Landroid/content/Context;", "initCallback", "Lcom/bytedance/ttgame/rocketapi/callback/InitCallback;", "initAfterAttachBaseContext", "mainProcessName", "monitorCommonLog", "logType", "logExtr", "Lorg/json/JSONObject;", "monitorEvent", "serviceName", "category", "metric", "extraLog", "setLibraryLoader", "libLoader", "Lcom/bytedance/ttgame/rocketapi/callback/ILibLoader;", "setSDKConfig", "sdkConfigMap", "", "", "uploadLog", "startTime", "", "endTime", "scene", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/main/internal/log/IGLogUploadCallback;", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface IRocketMiniApi extends IModuleApi {
    void enableCacheService(boolean z);

    void enableCronet(boolean z);

    <T extends IModuleApi> T getComponent(Class<T> cls);

    String getDeviceID();

    SdkConfig getSDKConfig();

    void init(Context context, InitCallback initCallback);

    void initAfterAttachBaseContext(Context context);

    void initAfterAttachBaseContext(Context context, String str);

    void monitorCommonLog(String str, JSONObject jSONObject);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void setLibraryLoader(ILibLoader iLibLoader);

    void setSDKConfig(Map<String, ? extends Object> map);

    void uploadLog(long j2, long j3, String str, IGLogUploadCallback iGLogUploadCallback);
}
