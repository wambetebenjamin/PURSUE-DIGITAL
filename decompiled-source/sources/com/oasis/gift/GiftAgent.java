package com.oasis.gift;

import android.content.Context;
import b.a;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class GiftAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static GiftAgent f17533a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17534b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17535c = true;

    public GiftAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static GiftAgent createInstance(Context context) {
        if (f17535c || f17533a == null) {
            try {
                String string = context.getString(R.string.gift_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("GiftAgent", "Create the GiftAgent:" + string);
                    f17533a = (GiftAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("GiftAgent", "Create notice agent failed.", e2);
            }
            if (f17533a == null) {
                Logger.i("GiftAgent", "Create default GiftAgent.");
                f17533a = new a();
                f17534b = true;
            }
            return f17533a;
        }
        throw new AssertionError();
    }

    public static GiftAgent getInstance() {
        if (f17535c || f17533a != null) {
            return f17533a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public void getCDKeyGift(String str, String str2, String str3, GiftListener giftListener) {
    }

    public void getLinkGift(String str, String str2, GiftListener giftListener) {
    }

    public boolean isUseDefault() {
        return f17534b;
    }
}
