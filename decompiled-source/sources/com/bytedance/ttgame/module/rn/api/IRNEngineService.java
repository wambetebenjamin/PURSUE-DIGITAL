package com.bytedance.ttgame.module.rn.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.bytedance.bdturing.methods.l;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.boot_manager_api.BootTaskMethodExclude;
import com.bytedance.ttgame.module.rn.api.model.BundleVersionCallback;
import com.bytedance.ttgame.module.rn.api.model.CommonHttpRequestListener;
import com.bytedance.ttgame.module.rn.api.model.GeckoBundleManager;
import com.bytedance.ttgame.module.rn.api.model.RNWindow;
import com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface;
import com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: IRNEngineService.kt */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H&J<\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H&J3\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u0005H&J \u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH&J \u0010\u001e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020 H&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#H&J\u0016\u0010$\u001a\u00020\u00032\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050&H&J\b\u0010'\u001a\u00020\u0005H&J\b\u0010(\u001a\u00020)H&J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020+0&H&J\b\u0010,\u001a\u00020\tH'J \u0010-\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u00010.j\f\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u0001`0H&J\u0014\u00101\u001a\u0004\u0018\u00010\u00052\b\u0010\"\u001a\u0004\u0018\u00010#H&J\n\u00102\u001a\u0004\u0018\u00010\u0005H&J \u00103\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u000204H&J\u0010\u00105\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u00106\u001a\u00020)H&J\b\u00107\u001a\u00020\tH&J\u0010\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\u0005H&J\u0018\u0010:\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020<H&J\u0010\u0010=\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000bH&J\u0018\u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020)2\u0006\u0010\f\u001a\u00020\u0005H&J\u0018\u0010@\u001a\u00020\u00032\u0006\u0010?\u001a\u00020)2\u0006\u0010\f\u001a\u00020\u0005H&J\u0012\u0010A\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H&J\u0010\u0010B\u001a\u00020\u00032\u0006\u0010C\u001a\u00020DH'J\u0010\u0010B\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020EH&J\u0010\u0010F\u001a\u00020\u00032\u0006\u0010G\u001a\u00020HH&J(\u0010I\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H&J,\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00052\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020/0O2\u0006\u0010P\u001a\u00020\u0005H&JX\u0010Q\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010R\u001a\u00020\u00052\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000f2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010U\u001a\u00020\u00052\u0006\u0010V\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020WH&J\u0010\u0010X\u001a\u00020\u00032\u0006\u0010Y\u001a\u00020)H&J\u0010\u0010Z\u001a\u00020\u00032\u0006\u0010[\u001a\u00020\u0005H&J\u0010\u0010\\\u001a\u00020\u00032\u0006\u0010]\u001a\u00020\u0005H&J\u0010\u0010^\u001a\u00020\u00032\u0006\u0010_\u001a\u00020\tH&J\u0010\u0010`\u001a\u00020\u00032\u0006\u0010a\u001a\u00020bH&J \u0010c\u001a\u00020\u00032\u0016\u0010d\u001a\u0012\u0012\u0004\u0012\u00020\u00050.j\b\u0012\u0004\u0012\u00020\u0005`0H&J\u0010\u0010e\u001a\u00020\u00032\u0006\u0010f\u001a\u00020\tH&J\u0010\u0010g\u001a\u00020\u00032\u0006\u0010h\u001a\u00020\tH&J<\u0010i\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000b2\"\u0010j\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020/0kj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020/`l2\u0006\u0010\u0017\u001a\u00020mH&J.\u0010n\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0010\u0010N\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010k2\b\u0010\u0017\u001a\u0004\u0018\u00010oH&J \u0010p\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u000204H&J\u0010\u0010q\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000bH&J\b\u0010r\u001a\u00020\u0003H&J\b\u0010s\u001a\u00020\u0003H&J\u0010\u0010t\u001a\u00020\u00032\u0006\u0010u\u001a\u00020vH&¨\u0006w"}, d2 = {"Lcom/bytedance/ttgame/module/rn/api/IRNEngineService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "addGeckoBundleManager", "", "name", "", "manager", "Lcom/bytedance/ttgame/module/rn/api/model/GeckoBundleManager;", "appOpenMarketUrl", "", "gameActivity", "Landroid/app/Activity;", "url", "inGameId", "customData", "", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/module/rn/api/IRNPageListener;", "authDouYinScope", "activity", ParamKeyConstants.WebViewConstants.REDIRECT_QUERY_SCOPE, "", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/rn/api/LinkDouYinAuthCallback;", "(Landroid/app/Activity;[Ljava/lang/String;Lcom/bytedance/ttgame/module/rn/api/LinkDouYinAuthCallback;)V", "bundlePrePath", "checkupdate", "newVersion", "first", "closeRNWindow", RNConfig.WINDOW_ID, "Lcom/bytedance/ttgame/module/rn/api/WindowListener;", "copyCommonBundle", "context", "Landroid/content/Context;", "downloadGeckoPackage", "packageList", "", "getAllBundleVersions", "getBundleMode", "", "getOpenWindowList", "Lcom/bytedance/ttgame/module/rn/api/model/RNWindow;", "getRNDebug", "getReactPackages", "Ljava/util/ArrayList;", "Ljava/lang/Object;", "Lkotlin/collections/ArrayList;", "getStoreRegionDevice", "getStoreRegionUser", "hideRNWindow", "Lcom/bytedance/ttgame/module/rn/api/IWindowOperationListener;", "initBundlePackages", "isEmulator", "isRNSupported", "jsFileExits", RNConfig.MODULE_NAME, "openPage", "bundle", "Landroid/os/Bundle;", "popAllWindow", "preFetchData", "p", "preFetchImage", "queryDouYinLinkInfo", "registerRNAPI", "commonBridge", "Lcom/bytedance/ttgame/sdk/module/bridge/ICommonBridgeInterface;", "Lcom/bytedance/ttgame/sdk/module/bridge/ICommonBridgeListener;", "registerRNUnityNotificationListener", "RNUnityNotificationName", "Lcom/bytedance/ttgame/module/rn/api/IUnityNotificationListener;", "sendEventToRN", "eventName", "params", "sendMessageToGame", "s", "map", "Ljava/util/Map;", "s1", "sendRequest", "type", "dataMap", "headerMap", "originData", "isjson", "Lcom/bytedance/ttgame/module/rn/api/model/CommonHttpRequestListener;", "setBundleMode", "mode", "setCommonPackage", "packageName", "setDeviceLevel", "level", "setFreshMode", "freshMode", "setIRNCommonInterface", "commonInterface", "Lcom/bytedance/ttgame/module/rn/api/IRNCommonInterface;", "setPrelodPackage", "packages", "setRNDebug", "rnDebug", "setResizeMode", "b", "shareDouYinContent", "content", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "Lcom/bytedance/ttgame/module/rn/api/ShareDouYinCallback;", "showDouYinCard", "Lcom/bytedance/ttgame/module/rn/api/ShowDouyinCardCallback;", "showRNWindow", "showTestPage", "startByteSync", "startSync", "updateLocalBundleVersion", "resultCallback", "Lcom/bytedance/ttgame/module/rn/api/model/BundleVersionCallback;", "rncore_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IRNEngineService extends IModuleApi {
    void addGeckoBundleManager(String str, GeckoBundleManager geckoBundleManager);

    boolean appOpenMarketUrl(Activity activity, String str);

    boolean appOpenMarketUrl(Activity activity, String str, String str2, Map<String, ? extends Object> map, IRNPageListener iRNPageListener);

    void authDouYinScope(Activity activity, String[] strArr, LinkDouYinAuthCallback linkDouYinAuthCallback);

    String bundlePrePath();

    void checkupdate(String str, boolean z, boolean z2);

    boolean closeRNWindow(Activity activity, String str, WindowListener windowListener);

    void copyCommonBundle(Context context);

    void downloadGeckoPackage(List<String> list);

    String getAllBundleVersions();

    int getBundleMode();

    List<RNWindow> getOpenWindowList();

    @BootTaskMethodExclude
    boolean getRNDebug();

    ArrayList<Object> getReactPackages();

    String getStoreRegionDevice(Context context);

    String getStoreRegionUser();

    void hideRNWindow(Activity activity, String str, IWindowOperationListener iWindowOperationListener);

    void initBundlePackages(Activity activity);

    int isEmulator();

    boolean isRNSupported();

    boolean jsFileExits(String str);

    void openPage(Activity activity, Bundle bundle);

    void popAllWindow(Activity activity);

    void preFetchData(int i2, String str);

    void preFetchImage(int i2, String str);

    void queryDouYinLinkInfo(LinkDouYinAuthCallback linkDouYinAuthCallback);

    @BootTaskMethodExclude
    void registerRNAPI(ICommonBridgeInterface iCommonBridgeInterface);

    void registerRNAPI(ICommonBridgeListener iCommonBridgeListener);

    void registerRNUnityNotificationListener(IUnityNotificationListener iUnityNotificationListener);

    void sendEventToRN(Activity activity, String str, String str2, String str3);

    void sendMessageToGame(String str, Map<String, Object> map, String str2);

    void sendRequest(String str, String str2, Map<String, String> map, Map<String, String> map2, String str3, int i2, CommonHttpRequestListener commonHttpRequestListener);

    void setBundleMode(int i2);

    void setCommonPackage(String str);

    void setDeviceLevel(String str);

    void setFreshMode(boolean z);

    void setIRNCommonInterface(IRNCommonInterface iRNCommonInterface);

    void setPrelodPackage(ArrayList<String> arrayList);

    void setRNDebug(boolean z);

    void setResizeMode(boolean z);

    void shareDouYinContent(Activity activity, HashMap<String, Object> hashMap, ShareDouYinCallback shareDouYinCallback);

    void showDouYinCard(Activity activity, HashMap<?, ?> hashMap, ShowDouyinCardCallback showDouyinCardCallback);

    void showRNWindow(Activity activity, String str, IWindowOperationListener iWindowOperationListener);

    void showTestPage(Activity activity);

    void startByteSync();

    void startSync();

    void updateLocalBundleVersion(BundleVersionCallback bundleVersionCallback);
}
