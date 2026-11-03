package com.oasis.push;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.bytedance.push.interfaze.ae;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
import java.util.Arrays;
/* loaded from: classes9.dex */
public abstract class PushAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static PushAgent f17589a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17590b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17591c = true;

    public PushAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static PushAgent createInstance(Context context) {
        if (f17591c || f17589a == null) {
            try {
                String string = context.getString(R.string.push_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("PushAgent", "Create the PushAgent:" + string);
                    f17589a = (PushAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("PushAgent", "Create push agent failed.", e2);
            }
            if (f17589a == null) {
                Logger.i("PushAgent", "Create default PushAgent.");
                f17589a = new a();
                f17590b = true;
            }
            return f17589a;
        }
        throw new AssertionError();
    }

    public static PushAgent getInstance() {
        if (f17591c || f17589a != null) {
            return f17589a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17590b;
    }

    public void localNotifyAfter(String str, String str2, long j2, String str3, LocalPushListener localPushListener) {
    }

    public void localNotifyAt(String str, String str2, String str3, String str4, LocalPushListener localPushListener) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 == 1437204481) {
            Logger.i("PushAgent", "onRequestPermissionsResult: requestCode == " + i2 + ", permissions == " + Arrays.toString(strArr) + ", grantResults == " + Arrays.toString(iArr));
        }
    }

    public void removeAllLocalNotification() {
    }

    public void removeLocalNotification(String str) {
    }

    public void repeatDayLocalNotify(String str, String str2, String str3, String str4, LocalPushListener localPushListener) {
    }

    public void repeatLocalNotify(String str, String str2, long j2, String str3, LocalPushListener localPushListener) {
    }

    public void repeatMonthLocalNotify(int i2, String str, String str2, String str3, String str4, LocalPushListener localPushListener) {
    }

    public void repeatWeekLocalNotify(String str, String str2, String str3, String str4, LocalPushListener localPushListener) {
    }

    public boolean requestNotifyPermission() {
        try {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 33) {
                Logger.i("PushAgent", "requestNotifyPermission: don't need requestNotifyPermission, because Build.VERSION.SDK_INT=" + i2 + " < 33 !!!!!!!!!");
                return true;
            }
            Activity activity = ActivityManager.getActivity();
            if (activity == null) {
                Logger.e("PushAgent", "requestNotifyPermission error: not found GameActivity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                return false;
            } else if (ContextCompat.checkSelfPermission(activity, ae.f11392b) == 0) {
                Logger.i("PushAgent", "requestNotifyPermission: already granted POST_NOTIFICATIONS permission.");
                return true;
            } else {
                Logger.i("PushAgent", "requestNotifyPermission: requesting POST_NOTIFICATIONS permission in " + activity.getClass().getName() + " ...");
                ActivityCompat.requestPermissions(activity, new String[]{ae.f11392b}, 1437204481);
                return true;
            }
        } catch (Exception e2) {
            Logger.e("PushAgent", "requestNotifyPermission has an exception: " + e2);
            return false;
        }
    }

    public abstract void setPushCallback(PushListener pushListener);

    public abstract void tryEnableNotification(boolean z);
}
