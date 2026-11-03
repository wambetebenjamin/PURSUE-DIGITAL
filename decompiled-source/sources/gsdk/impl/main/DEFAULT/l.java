package gsdk.impl.main.DEFAULT;

import com.bytedance.ttgame.sdk.module.utils.I18nUtils;
/* compiled from: UrlManager.java */
/* loaded from: classes10.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20463a = "https//act-boe.snssdk.com";

    /* renamed from: b  reason: collision with root package name */
    public static final String f20464b = "https://boe-act.bytedance.net";

    /* renamed from: c  reason: collision with root package name */
    private static final String f20465c = "https://act.vasnssdk.com";

    /* renamed from: d  reason: collision with root package name */
    private static final String f20466d = "https://act.sgsnssdk.com";

    /* renamed from: e  reason: collision with root package name */
    private static final String f20467e = "https://act-sandbox.vasnssdk.com";

    /* renamed from: f  reason: collision with root package name */
    private static final String f20468f = "https://act-sandbox.toutiaocloud.com";

    public static String a() {
        return I18nUtils.isAmerica() ? f20465c : f20466d;
    }

    public static String b() {
        return I18nUtils.isAmerica() ? f20467e : f20468f;
    }
}
