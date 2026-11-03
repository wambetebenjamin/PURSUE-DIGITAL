package com.oasis.pk;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class PKAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static PKAgent f17584a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17585b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17586c = true;

    public PKAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static PKAgent createInstance(Context context) {
        if (f17586c || f17584a == null) {
            try {
                String string = context.getString(R.string.pk_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("PKAgent", "Create the PKAgent:" + string);
                    f17584a = (PKAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("PKAgent", "Create PKAgent failed.", e2);
            }
            if (f17584a == null) {
                Logger.i("PKAgent", "Create default PKAgent.");
                f17584a = new DefaultPKAgent();
                f17585b = true;
            }
            return f17584a;
        }
        throw new AssertionError();
    }

    public static PKAgent getInstance() {
        if (f17586c || f17584a != null) {
            return f17584a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        OasisApplication.unregisterListener(this);
        ActivityManager.registerListener(this);
    }

    public abstract void init(InitListener initListener);

    public boolean isUseDefault() {
        return f17585b;
    }

    public abstract void login();

    public abstract void onGameLogout();

    public abstract void pay(String str);

    public abstract void submitRoleInfo(String str);
}
