package com.oasis.android;

import android.content.Context;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public abstract class AttributionAgent {

    /* renamed from: a  reason: collision with root package name */
    private static AttributionAgent f17356a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17357b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17358c = true;

    public static AttributionAgent createInstance(Context context) {
        if (f17358c || f17356a == null) {
            try {
                String string = context.getString(R.string.attribution_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("AttributionAgent", "Create the AttributionAgent: " + string);
                    f17356a = (AttributionAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("AttributionAgent", "Create AttributionAgent failed.", e2);
            }
            if (f17356a == null) {
                Logger.i("AttributionAgent", "Create default AttributionAgent.");
                f17356a = new b();
                f17357b = true;
            }
            return f17356a;
        }
        throw new AssertionError();
    }

    public static AttributionAgent getInstance() {
        if (f17358c || f17356a != null) {
            return f17356a;
        }
        throw new AssertionError();
    }

    public abstract String getHumeChannel();

    public abstract String getHumeSDKVersion();

    public abstract String getOpenExtraInfo();

    public boolean isUseDefault() {
        return f17357b;
    }
}
