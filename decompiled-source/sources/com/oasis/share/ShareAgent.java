package com.oasis.share;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class ShareAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static ShareAgent f17622a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17623b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17624c = true;

    public ShareAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static ShareAgent createInstance(Context context) {
        if (f17624c || f17622a == null) {
            try {
                Logger.i("ShareAgent", "R.string.share_agent_class == " + R.string.share_agent_class);
                String string = context.getString(R.string.share_agent_class);
                Logger.i("ShareAgent", "className == " + string);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("ShareAgent", "Create the ShareAgent:" + string);
                    f17622a = (ShareAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("ShareAgent", "Create share agent failed.", e2);
            }
            if (f17622a == null) {
                Logger.i("ShareAgent", "Create default ShareAgent.");
                f17622a = new a();
                f17623b = true;
            }
            return f17622a;
        }
        throw new AssertionError();
    }

    public static ShareAgent getInstance() {
        if (f17624c || f17622a != null) {
            return f17622a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17623b;
    }

    public void shareContentSilentV2(String str, boolean z, ShareListener shareListener) {
    }

    public abstract void shareContentV2(String str, boolean z, ShareListener shareListener, PanelListener panelListener);
}
