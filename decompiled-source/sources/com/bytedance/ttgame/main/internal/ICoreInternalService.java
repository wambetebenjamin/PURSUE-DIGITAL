package com.bytedance.ttgame.main.internal;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.rocketapi.callback.ILibLoader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;
@InternalApi
/* loaded from: classes3.dex */
public interface ICoreInternalService extends IModuleApi {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SchedulerType {
        public static final int BACKGROUND = 1;
        public static final int DEFAULT = 0;
        public static final int IDLE = 4;
        public static final int MAIN = 3;
        public static final int SERIAL = 2;
    }

    void appLogUpdateCustomHeaders();

    Context changeContextLocale(Context context);

    Context getAppContext();

    Bundle getAppLogCustomHeaders();

    String getAppLogOpenUdid();

    String getApplogSession();

    Map<String, Object> getChannelConfig();

    String getCurrentLanguage();

    String getDeviceId();

    String getEnv();

    ExecutorService getExecutor(int i2);

    String getGameId();

    String getInstallId();

    ILibLoader getLibraryLoader();

    int getMemoryLevel();

    String getOaid(Context context);

    String getPangoLinkChannel(Context context);

    String getPlayerDeviceId();

    ScheduledExecutorService getScheduledExecutor();

    SdkConfig getSdkConfig();

    String getUrl(String str);

    boolean isDebug();

    boolean isNewUser();

    void monitorCommonLog(String str, JSONObject jSONObject);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void monitorStatusRate(String str, int i2, JSONObject jSONObject);

    void recordMiscLog(Context context, String str, JSONObject jSONObject);

    void sendItfStatistics(String str, Map<String, Object> map);

    void sendLog(String str, JSONObject jSONObject);

    void sendLog(boolean z, String str, JSONObject jSONObject);

    void setLibraryLoader(ILibLoader iLibLoader);

    void setPlayerDeviceId(String str);
}
