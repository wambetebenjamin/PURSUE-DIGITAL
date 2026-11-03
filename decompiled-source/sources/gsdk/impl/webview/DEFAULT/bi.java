package gsdk.impl.webview.DEFAULT;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bytedance.ttgame.framework.module.util.SpUtil;
import com.bytedance.ttgame.module.compliance.impl.realname.RealNameService;
import java.util.Locale;
/* compiled from: LanguageManager.java */
/* loaded from: classes10.dex */
public class bi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20961a = "language_config";

    /* renamed from: b  reason: collision with root package name */
    private static final String f20962b = "LanguageManager";

    public static Context a(Context context, String str) {
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
                return a(context, 2);
            case 1:
                return a(context, 4);
            case 2:
                return a(context, 7);
            case 3:
                return a(context, 14);
            case 4:
                return a(context, 1);
            case 5:
                return a(context, 9);
            case 6:
                return a(context, 15);
            case 7:
                return a(context, 8);
            case '\b':
                return a(context, 16);
            case '\t':
                return a(context, 5);
            case '\n':
                return a(context, 6);
            case 11:
                return a(context, 17);
            case '\f':
                return a(context, 18);
            case '\r':
                return a(context, 10);
            case 14:
                return a(context, 12);
            case 15:
                return a(context, 3);
            case 16:
                return a(context, 13);
            case 17:
                return a(context, 11);
            default:
                return a(context, 0);
        }
    }

    public static Context a(Context context, int i2) {
        Locale a2 = a(i2);
        Locale.setDefault(a2);
        if (!(context instanceof Application)) {
            a(context.getApplicationContext(), a2, i2);
        }
        return a(context, a2, i2);
    }

    private static Context a(Context context, Locale locale, int i2) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocale(locale);
            context = context.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, displayMetrics);
        SpUtil.setSharedPreferences(f20961a, i2, context);
        return context;
    }

    public static Context a(Context context) {
        int sharedPreferences = SpUtil.getSharedPreferences(f20961a, context, 0);
        Log.d(f20962b, "changeContextLocale: " + sharedPreferences);
        return a(context, sharedPreferences);
    }

    private static Locale a(int i2) {
        switch (i2) {
            case 0:
                return a(Resources.getSystem().getConfiguration());
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
                return new Locale(com.bytedance.bdturing.c.v, "ID");
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
                return a(Resources.getSystem().getConfiguration());
        }
    }

    private static Locale a(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static String a() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        return ("zh".equalsIgnoreCase(language) && ("tw".equalsIgnoreCase(country) || "hk".equalsIgnoreCase(country) || "mo".equalsIgnoreCase(country))) ? "zh-Hant" : com.bytedance.bdturing.c.v.equalsIgnoreCase(language) ? "id" : language;
    }
}
