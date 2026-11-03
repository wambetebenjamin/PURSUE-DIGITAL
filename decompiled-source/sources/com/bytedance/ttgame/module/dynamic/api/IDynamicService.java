package com.bytedance.ttgame.module.dynamic.api;

import android.app.Activity;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.rn.api.IUnityNotificationListener;
import com.bytedance.ttgame.module.rn.api.IWindowOperationListener;
import com.bytedance.ttgame.module.rn.api.WindowListener;
import com.bytedance.ttgame.module.rn.api.model.RNAPICallback;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: IDynamicService.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH&J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH&J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J \u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J4\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH&J4\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH&J4\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00072\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001cH&J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$H&J<\u0010%\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010)\u001a\u00020*H&J4\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010)\u001a\u00020*H&J(\u0010+\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010.\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0014H&J(\u0010/\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0007H&¨\u00063"}, d2 = {"Lcom/bytedance/ttgame/module/dynamic/api/IDynamicService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "closeRNWindow", "", "gameActivity", "Landroid/app/Activity;", RNConfig.WINDOW_ID, "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/module/rn/api/WindowListener;", "getLatestModuleLists", "", "activity", "result", "Lcom/bytedance/ttgame/module/dynamic/api/ModulesCallBack;", "getModuleLists", "getOpenWindowList", "", "Lcom/bytedance/ttgame/module/dynamic/api/IDynamicWindow;", "hideRNWindow", "Lcom/bytedance/ttgame/module/rn/api/IWindowOperationListener;", "initBundlePackages", "openModuleByID", "moduleID", "customData", "", "", "pageListener", "Lcom/bytedance/ttgame/module/dynamic/api/IDynamicListener;", "openModuleByName", RNConfig.MODULE_NAME, "openModuleByUrl", "url", "popAllWindow", "registerRNUnityNotificationListener", "RNUnityNotificationName", "Lcom/bytedance/ttgame/module/rn/api/IUnityNotificationListener;", "runApiWithModuleName", "moudleName", "method", "json", "s1", "Lcom/bytedance/ttgame/module/rn/api/model/RNAPICallback;", "sendEventToRN", "eventName", "params", "showRNWindow", "updateUserInfo", "role_id", "server_id", "role_name", "dynamic_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface IDynamicService extends IModuleApi {
    boolean closeRNWindow(Activity activity, String str, WindowListener windowListener);

    void getLatestModuleLists(Activity activity, ModulesCallBack modulesCallBack);

    void getModuleLists(Activity activity, ModulesCallBack modulesCallBack);

    List<IDynamicWindow> getOpenWindowList();

    void hideRNWindow(Activity activity, String str, IWindowOperationListener iWindowOperationListener);

    void initBundlePackages(Activity activity);

    void openModuleByID(Activity activity, String str, Map<String, ? extends Object> map, IDynamicListener iDynamicListener);

    void openModuleByName(Activity activity, String str, Map<String, ? extends Object> map, IDynamicListener iDynamicListener);

    void openModuleByUrl(Activity activity, String str, Map<String, ? extends Object> map, IDynamicListener iDynamicListener);

    void popAllWindow(Activity activity);

    void registerRNUnityNotificationListener(IUnityNotificationListener iUnityNotificationListener);

    void runApiWithModuleName(Activity activity, String str, String str2, Map<String, ? extends Object> map, RNAPICallback rNAPICallback);

    void runApiWithModuleName(String str, String str2, Map<String, ? extends Object> map, RNAPICallback rNAPICallback);

    void sendEventToRN(Activity activity, String str, String str2, String str3);

    void showRNWindow(Activity activity, String str, IWindowOperationListener iWindowOperationListener);

    void updateUserInfo(Activity activity, String str, String str2, String str3);
}
