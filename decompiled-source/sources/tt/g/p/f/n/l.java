package tt.g.p.f.n;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Debug;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.brentvatne.react.ReactVideoViewManager;
import com.bytedance.gameprotect.GameProtect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class l {
    public static Activity a() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            for (Object obj : ((Map) declaredField.get(invoke)).values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField(ReactVideoViewManager.PROP_PAUSED);
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static View a(String str) {
        View b2 = b();
        if (b2 == null) {
            return null;
        }
        if (str.equals(" ")) {
            return b2;
        }
        for (View view : a(b2)) {
            if (view.toString().startsWith(str)) {
                return view;
            }
        }
        return null;
    }

    public static String a(String str, String str2, String str3) {
        try {
            Class<?> cls = Class.forName(str);
            Method declaredMethod = cls.getDeclaredMethod(str2, new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField(str3);
            declaredField.setAccessible(true);
            return ((Instrumentation) declaredField.get(invoke)).getClass().getName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static List<View> a(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                arrayList.add(childAt);
                arrayList.addAll(a(childAt));
            }
        }
        return arrayList;
    }

    public static View b() {
        Window window;
        Activity a2 = a();
        if (a2 == null || (window = a2.getWindow()) == null) {
            return null;
        }
        return window.getDecorView();
    }

    public static DisplayMetrics c() {
        Resources resources;
        Activity a2 = a();
        if (a2 == null || (resources = a2.getResources()) == null) {
            return null;
        }
        return resources.getDisplayMetrics();
    }

    public static String d() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        try {
            if (Modifier.isNative(TelephonyManager.class.getDeclaredMethod("getDeviceId", new Class[0]).getModifiers())) {
                arrayList.add("getDeviceId");
            }
            if (Modifier.isNative(TelephonyManager.class.getDeclaredMethod("getSubscriberId", new Class[0]).getModifiers())) {
                arrayList.add("getSubscriberId");
            }
            if (Modifier.isNative(System.class.getDeclaredMethod("getProperty", String.class).getModifiers())) {
                arrayList.add("getProperty");
            }
            if (Modifier.isNative(Debug.class.getDeclaredMethod("isDebuggerConnected", new Class[0]).getModifiers())) {
                arrayList.add("isDebuggerConnected");
            }
            if (Modifier.isNative(WifiInfo.class.getDeclaredMethod("getMacAddress", new Class[0]).getModifiers())) {
                arrayList.add("getMacAddress");
            }
            if (Modifier.isNative(WifiInfo.class.getDeclaredMethod("getBSSID", new Class[0]).getModifiers())) {
                arrayList.add("getBSSID");
            }
            if (Modifier.isNative(WifiInfo.class.getDeclaredMethod("getSSID", new Class[0]).getModifiers())) {
                arrayList.add("getSSID");
            }
            if (Modifier.isNative(ConnectivityManager.class.getDeclaredMethod("getActiveNetworkInfo", new Class[0]).getModifiers())) {
                arrayList.add("getActiveNetworkInfo");
            }
            if (Build.VERSION.SDK_INT >= 21 && Modifier.isNative(NetworkRequest.Builder.class.getDeclaredMethod("addTransportType", Integer.TYPE).getModifiers())) {
                arrayList.add("addTransportType");
            }
            if (Modifier.isNative(Thread.class.getDeclaredMethod("getStackTrace", new Class[0]).getModifiers())) {
                arrayList.add("getStackTrace");
            }
            if (Modifier.isNative(GameProtect.class.getDeclaredMethod("getPayEnv", new Class[0]).getModifiers())) {
                arrayList.add("getPayEnv");
            }
        } catch (Exception unused) {
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (i2 == arrayList.size() - 1) {
                sb.append((String) arrayList.get(i2));
            } else {
                sb.append((String) arrayList.get(i2)).append(",");
            }
        }
        return sb.toString();
    }
}
