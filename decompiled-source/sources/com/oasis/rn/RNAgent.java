package com.oasis.rn;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class RNAgent {

    /* renamed from: a  reason: collision with root package name */
    private static RNAgent f17604a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17605b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17606c = true;

    public static RNAgent createInstance(Context context) {
        if (f17606c || f17604a == null) {
            try {
                String string = context.getString(R.string.rn_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("RNAgent", "Create the RNAgent:" + string);
                    f17604a = (RNAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("RNAgent", "Create RNAgent failed.", e2);
            }
            if (f17604a == null) {
                Logger.i("RNAgent", "Create default RNAgent.");
                f17604a = new DefaultRNAgent();
                f17605b = true;
            }
            return f17604a;
        }
        throw new AssertionError();
    }

    public static RNAgent getInstance() {
        if (f17606c || f17604a != null) {
            return f17604a;
        }
        throw new AssertionError();
    }

    public abstract void closeWindow(String str, WindowOperationListener windowOperationListener);

    public void finalize() {
    }

    public abstract String getOpenedWindowList();

    public abstract boolean getRNDebug();

    public abstract void hideWindow(String str, WindowOperationListener windowOperationListener);

    public boolean isUseDefault() {
        return f17605b;
    }

    public abstract void openFaceVerify(String str, OpenFaceVerifyListener openFaceVerifyListener);

    public abstract boolean openMarketUrl(String str, OpenMarketUrlListener openMarketUrlListener);

    public abstract void popAllWindow();

    public abstract void queryActivityNotifyById(String str, QueryNotifyListener queryNotifyListener);

    public abstract void queryActivityNotifyByType(String str, QueryNotifyListener queryNotifyListener);

    public abstract void queryConfigValueByKey(String str, ConfigLoadListener configLoadListener);

    public abstract void registerNotificationListener(NotificationListener notificationListener);

    public abstract void sendEventToRN(String str, String str2, String str3);

    public abstract void setRNDebug(boolean z);

    public abstract void showTestPage();

    public abstract void showWindow(String str, WindowOperationListener windowOperationListener);

    public abstract void updateGameConfig(String str, UpdateConfigListener updateConfigListener);
}
