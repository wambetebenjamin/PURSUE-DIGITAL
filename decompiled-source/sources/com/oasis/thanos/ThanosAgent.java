package com.oasis.thanos;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class ThanosAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static ThanosAgent f17625a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17626b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17627c = true;

    public ThanosAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static ThanosAgent createInstance(Context context) {
        if (f17627c || f17625a == null) {
            try {
                String string = context.getString(R.string.Thanos_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("ThanosAgent", "Create the ThanosAgent:" + string);
                    f17625a = (ThanosAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("ThanosAgent", "Create Thanos agent failed.", e2);
            }
            if (f17625a == null) {
                Logger.i("ThanosAgent", "Create default ThanosAgent.");
                f17625a = new DefaultThanosAgent();
                f17626b = true;
            }
            return f17625a;
        }
        throw new AssertionError();
    }

    public static ThanosAgent getInstance() {
        if (f17627c || f17625a != null) {
            return f17625a;
        }
        throw new AssertionError();
    }

    public void download(boolean z, boolean z2, String str, ThanosDownloadListener thanosDownloadListener) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17626b;
    }

    public void killAppDuringThanos() {
    }

    public void queryForOwnRule(ThanosCommonListener thanosCommonListener) {
    }

    public void replaceInstall(String str) {
    }

    public void restartApp() {
    }
}
