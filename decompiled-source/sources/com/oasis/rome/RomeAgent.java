package com.oasis.rome;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class RomeAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static RomeAgent f17607a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17608b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17609c = true;

    public RomeAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static RomeAgent createInstance(Context context) {
        if (f17609c || f17607a == null) {
            try {
                String string = context.getString(R.string.rome_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("RomeAgent", "Create the RomeAgent:" + string);
                    f17607a = (RomeAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("RomeAgent", "Create RomeAgent failed.", e2);
            }
            if (f17607a == null) {
                Logger.i("RomeAgent", "Create default RomeAgent.");
                f17607a = new DefaultRomeAgent();
                f17608b = true;
            }
            return f17607a;
        }
        throw new AssertionError();
    }

    public static RomeAgent getInstance() {
        if (f17609c || f17607a != null) {
            return f17607a;
        }
        throw new AssertionError();
    }

    public abstract void activeReport(String str);

    public void finalize() {
        OasisApplication.unregisterListener(this);
        ActivityManager.registerListener(this);
    }

    public boolean isUseDefault() {
        return f17608b;
    }

    public abstract void loginReport(String str, LoginListener loginListener);

    public abstract void payReport(String str, String str2, String str3);

    public abstract void roleReport(String str, String str2, String str3);
}
