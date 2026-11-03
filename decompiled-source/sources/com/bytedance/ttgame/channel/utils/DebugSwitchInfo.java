package com.bytedance.ttgame.channel.utils;

import android.content.Context;
import com.bytedance.ttgame.channel.configs.SharedPrefsKey;
import com.bytedance.ttgame.framework.module.util.SpUtil;
/* loaded from: classes3.dex */
public class DebugSwitchInfo {
    private static final String SHARED_PREFS_DORAEMON = "shared_prefs_doraemon";

    public static boolean isDebugFlagOpen(Context context) {
        return SpUtil.getSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.DEBUG_FLAG_OPEN, context, false);
    }

    public static void setDebugFlagOpen(Context context, boolean z) {
        SpUtil.setSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.DEBUG_FLAG_OPEN, z, context);
    }

    public static boolean isBoeFlagOpen(Context context) {
        return SpUtil.getSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.BOE_FLAG_OPEN, context, false);
    }

    public static void setBoeFlagOpen(Context context, boolean z) {
        SpUtil.setSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.BOE_FLAG_OPEN, z, context);
    }

    public static void setAppLogFlagOpen(Context context, boolean z) {
        SpUtil.setSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.APPLOG_FLAG_OPEN, z, context);
    }

    public static boolean isSandBoxFlagOpen(Context context) {
        return SpUtil.getSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.SANDBOX_FLAG_OPEN, context, false);
    }

    public static void setSandBoxFlagOpen(Context context, boolean z) {
        SpUtil.setSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.SANDBOX_FLAG_OPEN, z, context);
    }

    public static void setBoeHeader(Context context, String str) {
        SpUtil.setSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.BOE_HEADER, str, context);
    }

    public static void setRnFlagOpen(Context context, boolean z) {
        SpUtil.setSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.RN_FLAG_OPEN, z, context);
    }

    public static boolean isRnDebugFlagOpen(Context context) {
        return SpUtil.getSharedPreferences(SHARED_PREFS_DORAEMON, SharedPrefsKey.RN_FLAG_OPEN, context, false);
    }
}
