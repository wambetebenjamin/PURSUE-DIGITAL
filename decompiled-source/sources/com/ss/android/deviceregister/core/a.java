package com.ss.android.deviceregister.core;

import com.bytedance.common.utility.o;
import com.ss.android.common.applog.UrlConfig;
/* compiled from: DeviceRegisterConfig.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static String[] f18165a = null;

    /* renamed from: b  reason: collision with root package name */
    private static InterfaceC0329a f18166b = null;

    /* renamed from: c  reason: collision with root package name */
    private static String f18167c = "log.isnssdk.com";

    /* renamed from: d  reason: collision with root package name */
    private static boolean f18168d = false;

    /* renamed from: e  reason: collision with root package name */
    private static String[] f18169e = null;
    public static boolean sAntiCheatingSwitch = false;

    /* compiled from: DeviceRegisterConfig.java */
    /* renamed from: com.ss.android.deviceregister.core.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0329a {
        boolean getEncryptSwitch();
    }

    public static void setEncryptInstance(InterfaceC0329a interfaceC0329a) {
        if (interfaceC0329a != null) {
            f18166b = interfaceC0329a;
        }
    }

    public static String[] URL_DEVICE_REGISTER() {
        String[] strArr = f18165a;
        return (strArr == null || strArr.length <= 0 || o.isEmpty(strArr[0])) ? new String[]{"https://" + f18167c + UrlConfig.PATH_DEVICE_REGISTER, "https://" + f18167c + UrlConfig.PATH_DEVICE_REGISTER} : f18165a;
    }

    public static boolean isAntiCheatingSwitchOpen() {
        return sAntiCheatingSwitch;
    }

    public static void setAntiCheatingSwitch(boolean z) {
        sAntiCheatingSwitch = z;
    }

    public static void setDeviceRegisterURL(String[] strArr) {
        if (strArr == null || strArr.length <= 0 || o.isEmpty(strArr[0])) {
            return;
        }
        f18165a = strArr;
    }

    public static void a(String[] strArr) {
        if (strArr == null || strArr.length <= 0 || o.isEmpty(strArr[0])) {
            return;
        }
        f18169e = strArr;
    }

    public static void setInitWithActivity(boolean z) {
        f18168d = z;
    }

    public static boolean isInitWithActivity() {
        return f18168d;
    }

    public static boolean isEncrypt() {
        InterfaceC0329a interfaceC0329a = f18166b;
        if (interfaceC0329a != null) {
            return interfaceC0329a.getEncryptSwitch();
        }
        return true;
    }
}
