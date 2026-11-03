package com.oasis.realname;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class RealNameAgent implements ApplicationListener, ActivityListener {
    protected static String Tag = null;

    /* renamed from: a  reason: collision with root package name */
    private static RealNameAgent f17599a = null;

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17600b = true;
    protected static Context context;

    public RealNameAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static RealNameAgent createInstance(Context context2) {
        if (f17600b || f17599a == null) {
            context = context2;
            try {
                String string = context2.getString(R.string.real_name_agent_class);
                Logger.i("RealNameAgent", "Start to create the RealNameAgent: " + string);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("RealNameAgent", "Create the RealNameAgent: " + string);
                    f17599a = (RealNameAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("RealNameAgent", "Create channel agent failed.", e2);
            }
            if (f17599a == null) {
                Logger.i("RealNameAgent", "Create default RealNameAgent.");
                f17599a = new a();
            }
            Tag = f17599a.getClass().getName();
            return f17599a;
        }
        throw new AssertionError();
    }

    public static RealNameAgent getInstance() {
        if (f17600b || f17599a != null) {
            return f17599a;
        }
        throw new AssertionError();
    }

    public void checkRealNameInfo(RealNameListener realNameListener) {
    }

    public void checkRealNameResult(CheckRealNameListener checkRealNameListener) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public int getAgeType() {
        return -1;
    }

    public boolean isVerify() {
        return false;
    }

    public void realNameVerify(int i2, RealNameListener realNameListener) {
    }

    public void requestComplianceRealNameAuth(RealNameListener realNameListener) {
    }
}
