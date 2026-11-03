package com.bytedance.ttgame.sdk.module.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class RTLUtils {
    public static RTLUtils sInstance;
    private List<String> rtlLanguages = new ArrayList();

    public static RTLUtils getInstance() {
        if (sInstance == null) {
            synchronized (RTLUtils.class) {
                if (sInstance == null) {
                    sInstance = new RTLUtils();
                }
            }
        }
        return sInstance;
    }

    private RTLUtils() {
        initRtlLanguageList();
    }

    public static boolean isRTL(Context context) {
        return context != null && Build.VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public boolean isRTLLanguage(String str) {
        List<String> list;
        if (TextUtils.isEmpty(str) || (list = this.rtlLanguages) == null || list.isEmpty()) {
            return false;
        }
        return this.rtlLanguages.contains(str);
    }

    private void initRtlLanguageList() {
        this.rtlLanguages.add("ar");
    }
}
