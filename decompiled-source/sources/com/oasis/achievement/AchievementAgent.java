package com.oasis.achievement;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class AchievementAgent implements ApplicationListener, ActivityListener {
    protected static final String TAG = "AchievementAgent";

    /* renamed from: a  reason: collision with root package name */
    private static AchievementAgent f17341a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17342b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17343c = true;
    protected static Context context;

    public AchievementAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static AchievementAgent createInstance(Context context2) {
        if (f17343c || f17341a == null) {
            context = context2;
            try {
                String string = context2.getString(R.string.achievement_agent_class);
                Logger.i(TAG, "Start to create the AchievementAgent: " + string);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i(TAG, "Create the AchievementAgent: " + string);
                    f17341a = (AchievementAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e(TAG, "Create achievement agent failed.", e2);
            }
            if (f17341a == null) {
                Logger.i(TAG, "Create default AchievementAgent.");
                f17341a = new a();
                f17342b = true;
            }
            return f17341a;
        }
        throw new AssertionError();
    }

    public static AchievementAgent getInstance() {
        if (f17343c || f17341a != null) {
            return f17341a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public void increaseAchievementProgress(String str, int i2, AchievementsActionListener achievementsActionListener) {
    }

    public boolean isUseDefault() {
        return f17342b;
    }

    public void showAchievements(AchievementsActionListener achievementsActionListener) {
    }

    public void unlockAchievement(String str, AchievementsActionListener achievementsActionListener) {
    }
}
