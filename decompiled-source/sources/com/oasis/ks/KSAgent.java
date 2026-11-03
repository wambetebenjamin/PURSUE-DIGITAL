package com.oasis.ks;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class KSAgent implements ApplicationListener {

    /* renamed from: a  reason: collision with root package name */
    private static KSAgent f17536a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17537b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17538c = true;

    public KSAgent() {
        OasisApplication.registerListener(this);
    }

    public static KSAgent createInstance(Context context) {
        if (f17538c || f17536a == null) {
            try {
                String string = context.getString(R.string.ks_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("KSAgent", "Create the KSAgent:" + string);
                    f17536a = (KSAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("KSAgent", "Create KSAgent failed.", e2);
            }
            if (f17536a == null) {
                Logger.i("KSAgent", "Create default KSAgent.");
                f17536a = new DefaultKSAgent();
                f17537b = true;
            }
            return f17536a;
        }
        throw new AssertionError();
    }

    public static KSAgent getInstance() {
        if (f17538c || f17536a != null) {
            return f17536a;
        }
        throw new AssertionError();
    }

    protected void finalize() {
        OasisApplication.unregisterListener(this);
    }

    public abstract String getChannel();

    public boolean isUseDefault() {
        return f17537b;
    }
}
