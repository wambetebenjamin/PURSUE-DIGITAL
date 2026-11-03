package com.oasis.abtest;

import a.a;
import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public class ABTestAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static ABTestAgent f17338a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17339b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17340c = true;

    public ABTestAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static ABTestAgent createInstance(Context context) {
        if (f17340c || f17338a == null) {
            try {
                String string = context.getString(R.string.abtest_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("ABTestAgent", "Create the ABTestAgent:" + string);
                    f17338a = (ABTestAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("ABTestAgent", "Create ABTest agent failed.", e2);
            }
            if (f17338a == null) {
                Logger.i("ABTestAgent", "Create default ABTestAgent.");
                f17338a = new a();
                f17339b = true;
            }
            return f17338a;
        }
        throw new AssertionError();
    }

    public static ABTestAgent getInstance() {
        if (f17340c || f17338a != null) {
            return f17338a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public String getExperimentValue(String str, String str2, String str3, String str4) {
        return "";
    }

    public boolean isUseDefault() {
        return f17339b;
    }

    public void registerExperiment(String str, String str2, String str3, String str4) {
    }

    public void setABTestListener(ABTestListener aBTestListener) {
    }
}
