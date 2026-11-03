package com.bytedance.ttgame.main.internal;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.rocketapi.privacy.IPermissionReqListener;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.WebViewClient;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;
import timber.log.Timber;
@InternalApi
/* loaded from: classes3.dex */
public interface IMainInternalService extends IModuleApi {

    /* loaded from: classes3.dex */
    public enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SchedulerType {
        public static final int BACKGROUND = 1;
        public static final int DEFAULT = 0;
        public static final int IDLE = 4;
        public static final int MAIN = 3;
        public static final int SERIAL = 2;
    }

    void alog(LogLevel logLevel, String str, String str2);

    void appLogUpdateCustomHeaders();

    Context changeContextLocale(Context context);

    void dispatchPermissionResult(Activity activity, int i2, String[] strArr, int[] iArr);

    IAgentWebSettings generateWebSettings();

    boolean geoNameIdWhiteList();

    Context getAppContext();

    String getCDid(Context context);

    Map<String, Object> getChannelConfig();

    String getCurrentLanguage();

    int getCurrentNetState(Context context);

    String getDeviceId();

    String getDownloadSource();

    String getEnv();

    ExecutorService getExecutor(int i2);

    String getInstallId();

    String getPangoLinkChannel(Context context);

    String getPlayerDeviceId();

    ScheduledExecutorService getScheduledExecutor();

    SdkConfig getSdkConfig();

    String getUrl(String str);

    boolean isDebug();

    void isEmulator(ICallback<EmulatorResult> iCallback);

    void monitorCommonLog(String str, JSONObject jSONObject);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    WebViewClient newSslWebViewClient();

    Timber.DebugTree newThreadAwareDebugTree();

    void requestPermission(Activity activity, String[] strArr, IPermissionReqListener iPermissionReqListener);

    void sendItfStatistics(String str, Map<String, Object> map);

    void sendLog(String str, JSONObject jSONObject);

    void sendLog(boolean z, String str, JSONObject jSONObject);

    void setDebug(boolean z);

    void setPlayerDeviceId(String str);
}
