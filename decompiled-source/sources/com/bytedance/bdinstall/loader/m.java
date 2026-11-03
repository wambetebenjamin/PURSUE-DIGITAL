package com.bytedance.bdinstall.loader;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DisplayLoader.java */
/* loaded from: classes.dex */
public class m extends c {

    /* renamed from: e  reason: collision with root package name */
    private final Context f3917e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(Context context) {
        super(true, false);
        this.f3917e = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        DisplayMetrics displayMetrics = this.f3917e.getResources().getDisplayMetrics();
        b(jSONObject, displayMetrics);
        a(jSONObject, displayMetrics);
        return true;
    }

    private void a(JSONObject jSONObject, DisplayMetrics displayMetrics) {
        int i2 = displayMetrics.densityDpi;
        jSONObject.put("display_density_v2", i2 <= 120 ? "ldpi" : i2 <= 160 ? "mdpi" : i2 <= 240 ? "hdpi" : i2 <= 320 ? "xhdpi" : i2 <= 480 ? "xxhdpi" : i2 <= 640 ? "xxxhdpi" : "xxxxhdpi");
        int[] a2 = a();
        jSONObject.put("resolution_v2", a2[1] + WebViewConfig.LOCATION_X + a2[0]);
        if (com.bytedance.bdinstall.u.a()) {
            com.bytedance.bdinstall.u.a("resolution " + a2[1] + WebViewConfig.LOCATION_X + a2[0]);
        }
    }

    private void b(JSONObject jSONObject, DisplayMetrics displayMetrics) {
        try {
            int i2 = displayMetrics.densityDpi;
            String str = i2 != 120 ? i2 != 240 ? i2 != 320 ? "mdpi" : "xhdpi" : "hdpi" : "ldpi";
            jSONObject.put("density_dpi", i2);
            jSONObject.put("display_density", str);
            jSONObject.put("resolution", displayMetrics.heightPixels + WebViewConfig.LOCATION_X + displayMetrics.widthPixels);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private int[] a() {
        int i2;
        int i3;
        int i4;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = ((WindowManager) this.f3917e.getSystemService("window")).getDefaultDisplay();
            int i5 = Build.VERSION.SDK_INT;
            if (i5 >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
                i2 = displayMetrics.widthPixels;
                try {
                    i3 = displayMetrics.heightPixels;
                } catch (Throwable th) {
                    th = th;
                    th.printStackTrace();
                    i3 = 0;
                    return new int[]{i2, i3};
                }
            } else {
                try {
                    Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                    try {
                        i4 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                        try {
                            i3 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                        } catch (IllegalAccessException e2) {
                            e = e2;
                            e.printStackTrace();
                            i3 = 0;
                            i2 = i4;
                            return new int[]{i2, i3};
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                            e.printStackTrace();
                            i3 = 0;
                            i2 = i4;
                            return new int[]{i2, i3};
                        } catch (InvocationTargetException e4) {
                            e = e4;
                            e.printStackTrace();
                            i3 = 0;
                            i2 = i4;
                            return new int[]{i2, i3};
                        }
                    } catch (IllegalAccessException e5) {
                        e = e5;
                        i4 = 0;
                    } catch (IllegalArgumentException e6) {
                        e = e6;
                        i4 = 0;
                    } catch (InvocationTargetException e7) {
                        e = e7;
                        i4 = 0;
                    }
                    i2 = i4;
                } catch (Throwable th2) {
                    th = th2;
                    i2 = i5;
                    th.printStackTrace();
                    i3 = 0;
                    return new int[]{i2, i3};
                }
            }
        } catch (Throwable th3) {
            th = th3;
            i2 = 0;
        }
        return new int[]{i2, i3};
    }
}
