package com.oasis.ug;

import android.app.Application;
import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import h.a;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class UGAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static UGAgent f17634a = null;

    /* renamed from: b  reason: collision with root package name */
    private static String[] f17635b = null;

    /* renamed from: c  reason: collision with root package name */
    private static String[] f17636c = null;

    /* renamed from: d  reason: collision with root package name */
    private static String f17637d = null;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f17638e = false;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f17639f = true;

    /* renamed from: g  reason: collision with root package name */
    private static String f17640g = null;

    /* renamed from: h  reason: collision with root package name */
    private static boolean f17641h = false;

    /* renamed from: i  reason: collision with root package name */
    static final /* synthetic */ boolean f17642i = true;

    public UGAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static UGAgent createInstance(Context context) {
        if (f17642i || f17634a == null) {
            try {
                String string = context.getString(R.string.ug_agent_class);
                f17635b = context.getResources().getStringArray(R.array.ug_scheme);
                f17636c = context.getResources().getStringArray(R.array.app_link);
                f17640g = context.getResources().getString(R.string.base_url);
                f17638e = context.getResources().getString(R.string.ug_auto_check).equals("1");
                f17639f = context.getResources().getString(R.string.ug_clear_clipboard).equals("1");
                f17637d = context.getString(R.string.appId);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("UGAgent", "Create the UGAgent:" + string);
                    f17634a = (UGAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("UGAgent", "Create ug agent failed.", e2);
            }
            if (f17634a == null) {
                Logger.i("UGAgent", "Create default UGAgent.");
                f17634a = new a();
                f17641h = true;
            }
            return f17634a;
        }
        throw new AssertionError();
    }

    public static String getAppId() {
        return f17637d;
    }

    public static List<String> getAppLinks() {
        ArrayList arrayList = new ArrayList();
        for (String str : f17636c) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static boolean getAutoCheck() {
        return f17638e;
    }

    public static String getBaseUrl() {
        return f17640g;
    }

    public static boolean getClearClipboard() {
        return f17639f;
    }

    public static UGAgent getInstance() {
        if (f17642i || f17634a != null) {
            return f17634a;
        }
        throw new AssertionError();
    }

    public static List<String> getSchemes() {
        ArrayList arrayList = new ArrayList();
        for (String str : f17635b) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static void initialize() {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17641h;
    }

    public void setUgInitListener(UGInitListener uGInitListener) {
    }

    public void ugInit(Application application) {
    }
}
