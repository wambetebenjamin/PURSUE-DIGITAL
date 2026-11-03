package com.bytedance.ttgame.module.crash.api;

import com.bytedance.bdturing.methods.l;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.pipo.payment.common.lib.c;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: ICrashService.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003#$%J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\u001c\u0010\t\u001a\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H&J\b\u0010\u000f\u001a\u00020\u0003H&J\"\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&J&\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H&JR\u0010\u0016\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001c2\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001cH&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\"H&¨\u0006&"}, d2 = {"Lcom/bytedance/ttgame/module/crash/api/ICrashService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "addCustomParams", "", "extraParams", "", "", "addSceneTag", "scene", "addTags", "tagMap", "ensureNotReachHere", e.f7575a, "", "message", "initAddLocalLog", "initCrashAndLog", "key", "", DeviceRequestsHelper.DEVICE_INFO_MODEL, "attachUserData", "Lcom/bytedance/ttgame/module/crash/api/ICrashService$TTWebviewAttachUserData;", "reportCrash", c.de, "name", "stackTrace", "stacktrace", "filters", "", "customParams", "setCrashUploadCallback", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/crash/api/ICrashService$ICrashUploadCallback;", "setScriptStackCallback", "Lcom/bytedance/ttgame/module/crash/api/ICrashService$IScriptCallback;", "ICrashUploadCallback", "IScriptCallback", "TTWebviewAttachUserData", "crash_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface ICrashService extends IModuleApi {

    /* compiled from: ICrashService.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/module/crash/api/ICrashService$ICrashUploadCallback;", "", "afterUploadOne", "", "crashType", "", "originalData", "crash_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public interface ICrashUploadCallback {
        void afterUploadOne(String str, String str2);
    }

    /* compiled from: ICrashService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/crash/api/ICrashService$IScriptCallback;", "", "getCrashName", "", "getCrashReason", "getCrashStack", "crash_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public interface IScriptCallback {
        String getCrashName();

        String getCrashReason();

        String getCrashStack();
    }

    /* compiled from: ICrashService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0014\u0012\u0006\b\u0001\u0012\u00020\u0004\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/bytedance/ttgame/module/crash/api/ICrashService$TTWebviewAttachUserData;", "", "getUserData", "", "", "crash_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public interface TTWebviewAttachUserData {
        Map<? extends String, ? extends String> getUserData();
    }

    void addCustomParams(Map<String, String> map);

    void addSceneTag(String str);

    void addTags(Map<String, String> map);

    void ensureNotReachHere(Throwable th, String str);

    void initAddLocalLog();

    void initCrashAndLog(int i2, String str, TTWebviewAttachUserData tTWebviewAttachUserData);

    void reportCrash(String str, String str2, String str3);

    void reportCrash(String str, String str2, String str3, Map<String, String> map, Map<String, String> map2);

    void setCrashUploadCallback(ICrashUploadCallback iCrashUploadCallback);

    void setScriptStackCallback(IScriptCallback iScriptCallback);
}
