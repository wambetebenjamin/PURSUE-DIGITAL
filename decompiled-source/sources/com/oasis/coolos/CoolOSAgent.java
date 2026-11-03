package com.oasis.coolos;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class CoolOSAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static CoolOSAgent f17517a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17518b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17519c = true;

    public CoolOSAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static CoolOSAgent createInstance(Context context) {
        if (f17519c || f17517a == null) {
            try {
                String string = context.getString(R.string.coolos_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("CoolOSAgent", "Create the CoolOSAgent:" + string);
                    f17517a = (CoolOSAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("CoolOSAgent", "Create CoolOSAgent failed.", e2);
            }
            if (f17517a == null) {
                Logger.i("CoolOSAgent", "Create default CoolOSAgent.");
                f17517a = new DefaultCoolOSAgent();
                f17518b = true;
            }
            return f17517a;
        }
        throw new AssertionError();
    }

    public static CoolOSAgent getInstance() {
        if (f17519c || f17517a != null) {
            return f17517a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        OasisApplication.unregisterListener(this);
        ActivityManager.unregisterListener(this);
    }

    public abstract void getOAID(DeviceIdListener deviceIdListener);

    public boolean isUseDefault() {
        return f17518b;
    }
}
