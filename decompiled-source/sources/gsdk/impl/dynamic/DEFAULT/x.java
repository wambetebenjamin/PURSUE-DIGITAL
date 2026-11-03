package gsdk.impl.dynamic.DEFAULT;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.module.dynamic.api.IDynamicWindow;
import com.bytedance.ttgame.module.dynamic.framework.DynmamicWindowSupportFragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: DynamicWindowManager.java */
/* loaded from: classes10.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    private static List<IDynamicWindow> f20286a = Collections.synchronizedList(new ArrayList());

    /* compiled from: DynamicWindowManager.java */
    /* loaded from: classes10.dex */
    public interface a {
        void a(boolean z);
    }

    public static String a(Activity activity, String str, Bundle bundle, String str2, String str3, String str4, int i2) {
        String valueOf;
        IDynamicWindow a2;
        if (str2 == null || str3 == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            valueOf = String.valueOf(SystemClock.elapsedRealtimeNanos());
        } else {
            valueOf = String.valueOf(System.currentTimeMillis());
        }
        bundle.putString(RNConfig.WINDOW_ID, valueOf);
        if (str.contains("rndialog")) {
            bundle.putString("window", "1");
            if (activity instanceof FragmentActivity) {
                a2 = DynmamicWindowSupportFragment.a(str, str2, str3, str4, i2, bundle, valueOf);
            } else {
                a2 = w.a(str, str2, str3, str4, i2, bundle, valueOf);
            }
            f20286a.add(a2);
            a2.showWindow(activity);
        } else {
            y yVar = new y(activity);
            yVar.a(str, str2, str3, str4, i2, bundle);
            yVar.a(valueOf);
            f20286a.add(yVar);
            yVar.showWindow(activity);
        }
        return valueOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IDynamicWindow f(String str) {
        if (TextUtils.isEmpty(str)) {
            if (f20286a.size() > 0) {
                List<IDynamicWindow> list = f20286a;
                return list.get(list.size() - 1);
            }
            return null;
        }
        for (IDynamicWindow iDynamicWindow : f20286a) {
            if (str.equals(iDynamicWindow.getWindowToken())) {
                return iDynamicWindow;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IDynamicWindow g(String str) {
        IDynamicWindow f2 = f(str);
        if (f2 != null) {
            f20286a.remove(f2);
            return f2;
        }
        return null;
    }

    public static IDynamicWindow a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (IDynamicWindow iDynamicWindow : f20286a) {
            if (str.equals(iDynamicWindow.getWindowToken())) {
                return iDynamicWindow;
            }
        }
        return null;
    }

    public static void a(IDynamicWindow iDynamicWindow) {
        f20286a.remove(iDynamicWindow);
    }

    public static void b(IDynamicWindow iDynamicWindow) {
        if (a(iDynamicWindow.getWindowToken()) == null) {
            f20286a.add(iDynamicWindow);
        }
    }

    public static void a(String str, String str2, String str3) {
        IDynamicWindow f2 = f(str3);
        if (f2 != null) {
            f2.sentMessageToRN(str, str2);
        }
    }

    public static boolean b(String str) {
        String rNUrl;
        if (!TextUtils.isEmpty(str) && f20286a != null) {
            for (IDynamicWindow iDynamicWindow : a()) {
                if (iDynamicWindow != null && (rNUrl = iDynamicWindow.getRNUrl()) != null && rNUrl.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean c(String str) {
        if (!TextUtils.isEmpty(str) && f20286a != null) {
            for (IDynamicWindow iDynamicWindow : a()) {
                if (iDynamicWindow != null && str.equals(iDynamicWindow.getRNUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(Activity activity, final String str, final a aVar) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: gsdk.impl.dynamic.DEFAULT.x.1
                @Override // java.lang.Runnable
                public void run() {
                    IDynamicWindow g2 = x.g(str);
                    if (g2 != null) {
                        g2.close();
                        a aVar2 = aVar;
                        if (aVar2 != null) {
                            aVar2.a(true);
                            return;
                        }
                        return;
                    }
                    a aVar3 = aVar;
                    if (aVar3 != null) {
                        aVar3.a(false);
                    }
                }
            });
            return true;
        }
        if (aVar != null) {
            aVar.a(false);
        }
        return false;
    }

    public static boolean b(Activity activity, final String str, final a aVar) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: gsdk.impl.dynamic.DEFAULT.x.2
                @Override // java.lang.Runnable
                public void run() {
                    IDynamicWindow f2 = x.f(str);
                    if (f2 != null) {
                        boolean show = f2.show();
                        a aVar2 = aVar;
                        if (aVar2 != null) {
                            aVar2.a(show);
                            return;
                        }
                        return;
                    }
                    a aVar3 = aVar;
                    if (aVar3 != null) {
                        aVar3.a(false);
                    }
                }
            });
            return true;
        }
        if (aVar != null) {
            aVar.a(false);
        }
        return false;
    }

    public static boolean c(Activity activity, final String str, final a aVar) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: gsdk.impl.dynamic.DEFAULT.x.3
                @Override // java.lang.Runnable
                public void run() {
                    IDynamicWindow f2 = x.f(str);
                    if (f2 != null) {
                        boolean hide = f2.hide();
                        a aVar2 = aVar;
                        if (aVar2 != null) {
                            aVar2.a(hide);
                            return;
                        }
                        return;
                    }
                    a aVar3 = aVar;
                    if (aVar3 != null) {
                        aVar3.a(false);
                    }
                }
            });
            return true;
        }
        if (aVar != null) {
            aVar.a(false);
        }
        return false;
    }

    public static List<IDynamicWindow> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(f20286a);
        return arrayList;
    }
}
