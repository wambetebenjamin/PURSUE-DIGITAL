package com.bytedance.ttgame.main.internal.language;

import android.app.Application;
import android.app.LocaleManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import com.bytedance.bdturing.c;
import com.bytedance.ttgame.framework.module.util.SpUtil;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.module.compliance.impl.realname.RealNameService;
import java.util.Locale;
/* loaded from: classes3.dex */
public class LanguageManager {
    private static final String LANG_CONFIG = "language_config";

    public static void init(Application application) {
        changeContextLocale(application);
        application.registerComponentCallbacks(new LanguageComponentCallback(application));
    }

    public static Context changeLanguage(Context context, String str) {
        return changeLanguage(context, parseLanguage(str), true);
    }

    private static int parseLanguage(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -372468771:
                if (str.equals("zh-Hans")) {
                    c2 = 0;
                    break;
                }
                break;
            case -372468770:
                if (str.equals("zh-Hant")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3121:
                if (str.equals("ar")) {
                    c2 = 2;
                    break;
                }
                break;
            case 3201:
                if (str.equals("de")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3241:
                if (str.equals("en")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3246:
                if (str.equals("es")) {
                    c2 = 5;
                    break;
                }
                break;
            case 3276:
                if (str.equals("fr")) {
                    c2 = 6;
                    break;
                }
                break;
            case 3355:
                if (str.equals("id")) {
                    c2 = 7;
                    break;
                }
                break;
            case 3371:
                if (str.equals("it")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 3383:
                if (str.equals("ja")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 3428:
                if (str.equals("ko")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 3518:
                if (str.equals("nl")) {
                    c2 = 11;
                    break;
                }
                break;
            case 3580:
                if (str.equals("pl")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 3588:
                if (str.equals("pt")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 3651:
                if (str.equals("ru")) {
                    c2 = 14;
                    break;
                }
                break;
            case 3700:
                if (str.equals("th")) {
                    c2 = 15;
                    break;
                }
                break;
            case 3710:
                if (str.equals("tr")) {
                    c2 = 16;
                    break;
                }
                break;
            case 3763:
                if (str.equals("vi")) {
                    c2 = 17;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 2;
            case 1:
                return 4;
            case 2:
                return 7;
            case 3:
                return 14;
            case 4:
                return 1;
            case 5:
                return 9;
            case 6:
                return 15;
            case 7:
                return 8;
            case '\b':
                return 16;
            case '\t':
                return 5;
            case '\n':
                return 6;
            case 11:
                return 17;
            case '\f':
                return 18;
            case '\r':
                return 10;
            case 14:
                return 12;
            case 15:
                return 3;
            case 16:
                return 13;
            case 17:
                return 11;
            default:
                return 0;
        }
    }

    public static synchronized Context changeLanguage(Context context, int i2, boolean z) {
        Context updateConfiguration;
        synchronized (LanguageManager.class) {
            if (!z) {
                i2 = SpUtil.getSharedPreferences(LANG_CONFIG, context, 0);
            }
            Locale locale = getLocale(i2);
            Locale.setDefault(locale);
            if (!(context instanceof Application)) {
                updateConfiguration(context.getApplicationContext(), locale, i2);
            }
            updateConfiguration = updateConfiguration(context, locale, i2);
            if (z) {
                SpUtil.setSharedPreferences(LANG_CONFIG, i2, context);
            }
        }
        return updateConfiguration;
    }

    private static Context updateConfiguration(Context context, Locale locale, int i2) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= 33) {
            ((LocaleManager) context.getSystemService(LocaleManager.class)).setApplicationLocales(new LocaleList(locale));
        } else if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocale(locale);
            context = context.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, displayMetrics);
        return context;
    }

    public static Context changeContextLocale(Context context) {
        return changeLanguage(context, 0, false);
    }

    private static Locale getLocale(int i2) {
        switch (i2) {
            case 0:
                return getLocalWithVersion(Resources.getSystem().getConfiguration());
            case 1:
                return Locale.US;
            case 2:
                return Locale.SIMPLIFIED_CHINESE;
            case 3:
                return new Locale("th", "TH", "TH");
            case 4:
                return Locale.TRADITIONAL_CHINESE;
            case 5:
                return Locale.JAPAN;
            case 6:
                return Locale.KOREA;
            case 7:
                return new Locale("ar", "SA");
            case 8:
                return new Locale(c.v, "ID");
            case 9:
                return new Locale("es", "ES");
            case 10:
                return new Locale("pt", "PT");
            case 11:
                return new Locale("vi", RealNameService.LOCATION_VIETNAM);
            case 12:
                return new Locale("ru", "RU");
            case 13:
                return new Locale("tr", "TR");
            case 14:
                return new Locale("de", "DE");
            case 15:
                return new Locale("fr", "FR");
            case 16:
                return new Locale("it", "IT");
            case 17:
                return new Locale("nl", "NL");
            case 18:
                return new Locale("pl", "PL");
            default:
                return getLocalWithVersion(Resources.getSystem().getConfiguration());
        }
    }

    private static Locale getLocalWithVersion(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static String getCurrentLanguage() {
        Locale locale;
        Locale locale2 = Locale.getDefault();
        if (Build.VERSION.SDK_INT >= 33 && (locale = ((LocaleManager) ModuleManager.INSTANCE.getAppContext().getSystemService(LocaleManager.class)).getApplicationLocales().get(0)) != null) {
            locale2 = locale;
        }
        if (locale2 == null) {
            return "en";
        }
        String language = locale2.getLanguage();
        String country = locale2.getCountry();
        return ("zh".equalsIgnoreCase(language) && ("tw".equalsIgnoreCase(country) || "hk".equalsIgnoreCase(country) || "mo".equalsIgnoreCase(country))) ? "zh-Hant" : c.v.equalsIgnoreCase(language) ? "id" : language;
    }
}
