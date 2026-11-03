package com.oasis.ui.floatingwindow;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public class FloatingWindowAgent implements ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static FloatingWindowAgent f17711a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17712b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17713c = true;

    public static FloatingWindowAgent createInstance(Context context) {
        if (f17713c || f17711a == null) {
            try {
                String string = context.getString(R.string.oasis_floating_window);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("FloatingWindowAgent", "Create the FloatingWindowAgent:" + string);
                    f17711a = (FloatingWindowAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("FloatingWindowAgent", "Create FloatingWindowAgent failed.", e2);
            }
            if (f17711a == null) {
                Logger.i("FloatingWindowAgent", "Create default FloatingWindowAgent.");
                f17711a = new FloatingWindowAgent();
                f17712b = true;
            }
            return f17711a;
        }
        throw new AssertionError();
    }

    public static FloatingWindowAgent getInstance() {
        if (f17713c || f17711a != null) {
            return f17711a;
        }
        throw new AssertionError();
    }

    public boolean hideAgeLevelFloatingWindow() {
        Logger.d("DefaultFloatingWindowAgent", "hideAgeLevelFloatingWindow: ");
        return true;
    }

    public boolean isSupportAgeLevelFloatingWindow() {
        Logger.d("DefaultFloatingWindowAgent", "isSupportAgeLevelFloatingWindow.");
        return false;
    }

    public boolean isUseDefault() {
        Logger.d("DefaultFloatingWindowAgent", "isUseDefault: " + f17712b);
        return f17712b;
    }

    public boolean showAgeLevelFloatingWindow(String str) {
        Logger.d("DefaultFloatingWindowAgent", "showAgeLevelFloatingWindow: " + str);
        return false;
    }
}
