package gsdk.impl.account.toutiao;

import com.bytedance.ttgame.framework.module.util.FlavorUtilKt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes10.dex */
public class bs {
    public static String a(long j2, Locale locale) {
        if (FlavorUtilKt.isI18nFlavor()) {
            return new SimpleDateFormat("yyyy.MM.dd", locale).format(new Date(j2 * 1000));
        }
        return new SimpleDateFormat("yyyy-MM-dd", locale).format(new Date(j2 * 1000));
    }

    public static String a(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(j2 * 1000));
    }

    public static String b(long j2, Locale locale) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", locale).format(new Date(j2 * 1000));
    }

    public static String a(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            date = null;
        }
        return String.valueOf(date.getTime() / 1000);
    }
}
