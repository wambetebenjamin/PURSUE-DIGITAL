package com.oasis.upgrade;

import android.app.Activity;
import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.ICommonCallbackWithJudge;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public abstract class UpgradeAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static UpgradeAgent f17762a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17763b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17764c = true;
    protected static WeakReference<Context> context = null;
    public static boolean useCustomUI = false;

    public UpgradeAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static UpgradeAgent createInstance(Context context2) {
        if (f17764c || f17762a == null) {
            context = new WeakReference<>(context2);
            try {
                String string = context2.getString(R.string.upgrade_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("UpgradeAgent", "Create the UpgradeAgent:" + string);
                    f17762a = (UpgradeAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("UpgradeAgent", "Create Upgrade agent failed.", e2);
            }
            if (f17762a == null) {
                Logger.i("UpgradeAgent", "Create default UpgradeAgent.");
                f17762a = new DefaultUpgradeAgent();
                f17763b = true;
            }
            useCustomUI = context2.getResources().getBoolean(R.bool.upgrade_use_custom_ui);
            return f17762a;
        }
        throw new AssertionError();
    }

    public static UpgradeAgent getInstance() {
        if (f17764c || f17762a != null) {
            return f17762a;
        }
        throw new AssertionError();
    }

    public abstract void cancelExecution(long j2);

    public abstract void cancelUpgrade();

    public abstract void continueExecution(long j2);

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17763b;
    }

    public abstract void overwriteInstallApk(String str);

    public abstract void queryForOwnRule();

    public abstract void restartApp();

    public abstract void setCustomUIUpgradeListener(CustomUIUpgradeListener customUIUpgradeListener);

    public abstract void setOwnRuleRequestCallback(ICommonCallbackWithJudge iCommonCallbackWithJudge);

    public abstract void setUpgradeCallback(UpgradeListener upgradeListener);

    public abstract void setUpgradeCallbackV2(UpgradeListenerV2 upgradeListenerV2);

    public abstract void setUpgradeForOwnRuleCallback(IUpgradeForOwnRuleCallback iUpgradeForOwnRuleCallback, ITaskProgressListener iTaskProgressListener);

    public abstract void startForceUpgrade();

    public abstract void startForceUpgradeV2();

    public abstract void startUpgradeForOwnRule(Activity activity, String str);

    public abstract void startUpgradeForOwnRule(String str);

    public abstract void tryForceUpgrade();

    public abstract void tryForceUpgradeV2();
}
