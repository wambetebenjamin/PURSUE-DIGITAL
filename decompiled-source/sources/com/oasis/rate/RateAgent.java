package com.oasis.rate;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public class RateAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static RateAgent f17596a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17597b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17598c = true;

    public RateAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static RateAgent createInstance(Context context) {
        if (f17598c || f17596a == null) {
            try {
                String string = context.getString(R.string.rate_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("RateAgent", "Create the RateAgent:" + string);
                    f17596a = (RateAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("RateAgent", "Create RateAgent failed.", e2);
            }
            if (f17596a == null) {
                Logger.i("RateAgent", "Create default RateAgent.");
                f17596a = new a();
                f17597b = true;
            }
            return f17596a;
        }
        throw new AssertionError();
    }

    public static RateAgent getInstance() {
        if (f17598c || f17596a != null) {
            return f17596a;
        }
        throw new AssertionError();
    }

    public static void initialize() {
    }

    public void customViewCancel() {
    }

    public void customViewDismiss() {
    }

    public void customViewFeedback() {
    }

    public void customViewPraise() {
    }

    public void customViewShow() {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17597b;
    }

    public void showRatingView(String str, FeedbackListener feedbackListener) {
    }
}
