package com.ss.android.newmedia.redbadge.setting;

import android.content.Context;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
/* compiled from: RedbadgeSetting.java */
/* loaded from: classes9.dex */
public class a {
    public static final String DESKTOP_RED_BADGE_ARGS = "desktop_red_badge_args";
    public static final String IS_DESKTOP_RED_BADGE_SHOW = "is_desktop_red_badge_show";
    public static final String RED_BADGE_LAST_LAST_TIME_PARAS = "red_badge_last_last_time_paras";
    public static final String RED_BADGE_LAST_REQUEST_TIME = "red_badge_last_request_time";
    public static final String RED_BADGE_LAST_TIME_PARAS = "red_badge_last_time_paras";
    public static final String RED_BADGE_LAUNCH_TIMES = "red_badge_launch_times";
    public static final String RED_BADGE_NEXT_QUERY_INTERVAL = "red_badge_next_query_interval";
    public static final String RED_BADGE_SHOW_TIMES = "red_badge_show_times";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18454a = "red_badge_last_valid_response";

    /* renamed from: b  reason: collision with root package name */
    private static final String f18455b = "red_badge_is_use_last_valid_response";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18456c = "red_badge_show_history";

    /* renamed from: d  reason: collision with root package name */
    private static volatile a f18457d;

    /* renamed from: e  reason: collision with root package name */
    private PushMultiProcessSharedProvider.b f18458e;

    /* renamed from: f  reason: collision with root package name */
    private Context f18459f;

    public static synchronized a getInstance(Context context) {
        a aVar;
        synchronized (a.class) {
            if (f18457d == null) {
                f18457d = new a(context);
            }
            aVar = f18457d;
        }
        return aVar;
    }

    private a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f18459f = applicationContext;
        this.f18458e = PushMultiProcessSharedProvider.getMultiprocessShared(applicationContext);
    }

    public void setIsDesktopRedBadgeShow(boolean z) {
        this.f18458e.edit().putBoolean(IS_DESKTOP_RED_BADGE_SHOW, z).apply();
    }

    public boolean isDesktopRedBadgeShow() {
        return this.f18458e.getBoolean(IS_DESKTOP_RED_BADGE_SHOW, false);
    }

    public void setDesktopRedBadgeArgs(String str) {
        this.f18458e.edit().putString(DESKTOP_RED_BADGE_ARGS, str).apply();
    }

    public String getDesktopRedBadgeArgs() {
        return this.f18458e.getString(DESKTOP_RED_BADGE_ARGS, "");
    }

    public void setRedBadgeLastRequestTime(long j2) {
        this.f18458e.edit().putLong(RED_BADGE_LAST_REQUEST_TIME, j2).apply();
    }

    public long getRedBadgeLastRequestTime() {
        return this.f18458e.getLong(RED_BADGE_LAST_REQUEST_TIME, 0L);
    }

    public void setRedBadgeNextQueryInterval(int i2) {
        this.f18458e.edit().putInt(RED_BADGE_NEXT_QUERY_INTERVAL, i2).apply();
    }

    public int getRedBadgeNextQueryInterval() {
        return this.f18458e.getInt(RED_BADGE_NEXT_QUERY_INTERVAL, 600);
    }

    public void setRedBadgeLastTimeParas(String str) {
        this.f18458e.edit().putString(RED_BADGE_LAST_TIME_PARAS, str).apply();
    }

    public String getRedBadgeLastTimeParas() {
        return this.f18458e.getString(RED_BADGE_LAST_TIME_PARAS, "");
    }

    public void setRedBadgeLastLastTimeParas(String str) {
        this.f18458e.edit().putString(RED_BADGE_LAST_LAST_TIME_PARAS, str).apply();
    }

    public String getRedBadgeLastLastTimeParas() {
        return this.f18458e.getString(RED_BADGE_LAST_LAST_TIME_PARAS, "");
    }

    public void setRedBadgeLaunchTimes(int i2) {
        this.f18458e.edit().putInt(RED_BADGE_LAUNCH_TIMES, i2).apply();
    }

    public int getRedBadgeLaunchTimes() {
        return this.f18458e.getInt(RED_BADGE_LAUNCH_TIMES, 0);
    }

    public void setRedBadgeBadgeShowTimes(int i2) {
        this.f18458e.edit().putInt(RED_BADGE_SHOW_TIMES, i2).apply();
    }

    public int getRedBadgeBadgeShowTimes() {
        return this.f18458e.getInt(RED_BADGE_SHOW_TIMES, 0);
    }

    public void setRedBadgeLastValidResponse(String str) {
        this.f18458e.edit().putString(f18454a, str).apply();
    }

    public String getRedBadgeLastValidResponse() {
        return this.f18458e.getString(f18454a, "");
    }

    public void setRedBadgeIsUseLastValidResponse(boolean z) {
        this.f18458e.edit().putBoolean(f18455b, z).apply();
    }

    public boolean isUseRedBadgeLastValidResponse() {
        return this.f18458e.getBoolean(f18455b, true);
    }

    public void setRedBadgeShowHistory(String str) {
        this.f18458e.edit().putString(f18456c, str).apply();
    }

    public String getRedBadgeShowHistory() {
        return this.f18458e.getString(f18456c, "");
    }
}
