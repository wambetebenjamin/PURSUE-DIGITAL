package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.LocaleList;
import androidx.core.text.TextUtilsCompat;
import java.util.Locale;
/* loaded from: classes8.dex */
public class I18nUtil {
    private static boolean I18N_INIT = false;
    private static final String KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES = "RCTI18nUtil_makeRTLFlipLeftAndRightStyles";
    private static final String KEY_FOR_PREFS_ALLOWRTL = "RCTI18nUtil_allowRTL";
    private static final String KEY_FOR_PREFS_FORCERTL = "RCTI18nUtil_forceRTL";
    private static final String SHARED_PREFS_NAME = "com.facebook.react.modules.i18nmanager.I18nUtil";
    private static Locale currentLocal = null;
    private static boolean doLeftAndRightSwapInRTL = true;
    private static boolean isDevicePreferredLanguageRTL = false;
    private static boolean isRTLAllowed = true;
    private static boolean isRTLForced = false;
    private static I18nUtil sharedI18nUtilInstance;

    private I18nUtil() {
    }

    public static I18nUtil getInstance() {
        if (sharedI18nUtilInstance == null) {
            sharedI18nUtilInstance = new I18nUtil();
        }
        return sharedI18nUtilInstance;
    }

    private static Locale getLocale(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = context.getResources().getConfiguration().getLocales();
            locale = (locales == null || locales.size() <= 0) ? null : locales.get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    public static void initI18n(Context context) {
        try {
            isRTLAllowed = isPrefSet(context, KEY_FOR_PREFS_ALLOWRTL, true);
            isRTLForced = isPrefSet(context, KEY_FOR_PREFS_FORCERTL, false);
            doLeftAndRightSwapInRTL = isPrefSet(context, KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES, true);
            Locale locale = getLocale(context);
            currentLocal = locale;
            if (locale == null) {
                locale = Locale.getDefault();
            }
            isDevicePreferredLanguageRTL = TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
            I18N_INIT = true;
        } catch (Exception unused) {
        }
    }

    public static void initI18nForce(Context context) {
        try {
            Locale locale = getLocale(context);
            currentLocal = locale;
            if (locale == null) {
                locale = Locale.getDefault();
            }
            isDevicePreferredLanguageRTL = TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
            I18N_INIT = true;
        } catch (Exception unused) {
        }
    }

    public static Locale getCurrentLocal() {
        return currentLocal;
    }

    public boolean isRTL(Context context) {
        if (!I18N_INIT) {
            initI18n(context);
        }
        if (isRTLForced(context)) {
            return true;
        }
        return isRTLAllowed(context) && isDevicePreferredLanguageRTL();
    }

    private boolean isRTLAllowed(Context context) {
        if (!I18N_INIT) {
            initI18n(context);
        }
        return isRTLAllowed;
    }

    public void allowRTL(Context context, boolean z) {
        isRTLAllowed = z;
        setPref(context, KEY_FOR_PREFS_ALLOWRTL, z);
    }

    public boolean doLeftAndRightSwapInRTL(Context context) {
        if (!I18N_INIT) {
            initI18n(context);
        }
        return doLeftAndRightSwapInRTL;
    }

    public void swapLeftAndRightInRTL(Context context, boolean z) {
        doLeftAndRightSwapInRTL = z;
        setPref(context, KEY_FOR_PERFS_MAKE_RTL_FLIP_LEFT_AND_RIGHT_STYLES, z);
    }

    private boolean isRTLForced(Context context) {
        if (!I18N_INIT) {
            initI18n(context);
        }
        return isRTLForced;
    }

    public void forceRTL(Context context, boolean z) {
        isRTLForced = z;
        setPref(context, KEY_FOR_PREFS_FORCERTL, z);
    }

    private boolean isDevicePreferredLanguageRTL() {
        if (!I18N_INIT) {
            isDevicePreferredLanguageRTL = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
        return isDevicePreferredLanguageRTL;
    }

    private static boolean isPrefSet(Context context, String str, boolean z) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0).getBoolean(str, z);
    }

    private void setPref(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SHARED_PREFS_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }
}
