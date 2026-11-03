package com.oasis.notice;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class NoticeAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static NoticeAgent f17556a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17557b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17558c = true;

    public NoticeAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static NoticeAgent createInstance(Context context) {
        if (f17558c || f17556a == null) {
            try {
                String string = context.getString(R.string.notice_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("NoticeAgent", "Create the NoticeAgent:" + string);
                    f17556a = (NoticeAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("NoticeAgent", "Create notice agent failed.", e2);
            }
            if (f17556a == null) {
                Logger.i("NoticeAgent", "Create default NoticeAgent.");
                f17556a = new a();
                f17557b = true;
            }
            return f17556a;
        }
        throw new AssertionError();
    }

    public static NoticeAgent getInstance() {
        if (f17558c || f17556a != null) {
            return f17556a;
        }
        throw new AssertionError();
    }

    public abstract void fetchNotice(String str, NoticeListener noticeListener);

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17557b;
    }
}
