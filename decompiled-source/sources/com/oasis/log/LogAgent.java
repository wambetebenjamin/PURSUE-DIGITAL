package com.oasis.log;

import android.content.Context;
import c.a;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class LogAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static LogAgent f17547a = null;

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17548b = true;

    public LogAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static LogAgent createInstance(Context context) {
        if (f17548b || f17547a == null) {
            try {
                String string = context.getString(R.string.log_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("LoggerAgent", "Create the LogAgent:" + string);
                    f17547a = (LogAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("LogAgent", "Create notice agent failed.", e2);
            }
            if (f17547a == null) {
                Logger.i("LogAgent", "Create default LogAgent.");
                f17547a = new a();
            }
            return f17547a;
        }
        throw new AssertionError();
    }

    public static LogAgent getInstance() {
        if (f17548b || f17547a != null) {
            return f17547a;
        }
        throw new AssertionError();
    }

    public abstract void Log(int i2, String str);

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }
}
