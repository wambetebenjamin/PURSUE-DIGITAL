package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.ttgame.gecko.GsdkGeckoConfig;
import com.bytedance.ttgame.gecko.GsdkGeckoManager;
import java.io.File;
/* compiled from: DynamicConfig.java */
/* loaded from: classes10.dex */
public class af {

    /* renamed from: a  reason: collision with root package name */
    public static String f20086a = null;

    /* renamed from: b  reason: collision with root package name */
    public static String f20087b = "common";

    /* renamed from: c  reason: collision with root package name */
    public static String f20088c = "common.bundle";

    /* renamed from: d  reason: collision with root package name */
    public static boolean f20089d = true;

    /* renamed from: e  reason: collision with root package name */
    public static boolean f20090e = true;

    /* renamed from: f  reason: collision with root package name */
    private static String f20091f = "-load";

    public static String a(String str) {
        String b2 = b(str);
        if (TextUtils.isEmpty(b2) || !new File(b2).exists()) {
            Context b3 = ah.a().b();
            b2 = GsdkGeckoManager.getInstance(b3).getChannelPath(new File(GsdkGeckoConfig.offlineRootDir(b3)), str);
        }
        return b2 == null ? "" : b2;
    }

    public static String b(String str) {
        return a() + File.separator + GsdkGeckoConfig.getGeckoAdAccessKey() + File.separator + str;
    }

    public static String a() {
        if (TextUtils.isEmpty(f20086a)) {
            f20086a = GsdkGeckoConfig.offlineRootDir(ah.a().b()) + f20091f;
        }
        return f20086a;
    }
}
