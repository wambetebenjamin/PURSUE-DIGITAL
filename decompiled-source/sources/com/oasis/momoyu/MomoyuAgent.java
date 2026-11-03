package com.oasis.momoyu;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class MomoyuAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static MomoyuAgent f17549a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17550b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17551c = true;

    public MomoyuAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static MomoyuAgent createInstance(Context context) {
        if (f17551c || f17549a == null) {
            try {
                String string = context.getString(R.string.momoyu_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("MomoyuAgent", "Create the MomoyuAgent:" + string);
                    f17549a = (MomoyuAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("MomoyuAgent", "Create MomoyuAgent failed.", e2);
            }
            if (f17549a == null) {
                Logger.i("MomoyuAgent", "Create default MomoyuAgent.");
                f17549a = new DefaultMomoyuAgent();
                f17550b = true;
            }
            return f17549a;
        }
        throw new AssertionError();
    }

    public static MomoyuAgent getInstance() {
        if (f17551c || f17549a != null) {
            return f17549a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        OasisApplication.unregisterListener(this);
        ActivityManager.unregisterListener(this);
    }

    public abstract void init(InitListener initListener);

    public boolean isUseDefault() {
        return f17550b;
    }

    public abstract String pay(String str, PayListener payListener);
}
