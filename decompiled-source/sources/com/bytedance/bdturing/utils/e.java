package com.bytedance.bdturing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.bytedance.bdturing.f;
import com.bytedance.bdturing.h;
import com.bytedance.common.utility.l;
import com.bytedance.platform.godzilla.common.c;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;
/* compiled from: Utils.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\u001a\u001a\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\t\u001a\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b\u001a\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\t\u001a$\u0010\u0013\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u001a\"\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\r\u001a$\u0010\u0016\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004¨\u0006\u0017"}, d2 = {"appendUrlParams", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "url", "", "params", "Lorg/json/JSONObject;", "convertJson2Form", "findActivity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "getCurrentOrientation", "", "activity", "getDensity", "", "getManifestVersionCode", "getResolution", "appendFirstParam", "key", "value", "appendParams", "turing_i18nRelease"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class e {
    public static final StringBuilder a(StringBuilder sb, String key, int i2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        return a(sb, key, String.valueOf(i2));
    }

    public static final StringBuilder a(StringBuilder sb, String key, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (str == null) {
            return sb;
        }
        try {
            sb.append("&").append(key).append("=").append(URLEncoder.encode(str, c.CHARSET_UTF_8));
        } catch (UnsupportedEncodingException e2) {
            h.printException(e2);
        }
        return sb;
    }

    public static final StringBuilder b(StringBuilder sb, String key, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (str == null) {
            return sb;
        }
        try {
            sb.append(key).append("=").append(URLEncoder.encode(str, c.CHARSET_UTF_8));
        } catch (UnsupportedEncodingException e2) {
            h.printException(e2);
        }
        return sb;
    }

    public static final Activity a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!(context instanceof ContextWrapper)) {
            return null;
        }
        while (true) {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            if (!(contextWrapper instanceof Activity)) {
                context = contextWrapper.getBaseContext();
                if (!(context instanceof ContextWrapper)) {
                    return null;
                }
            } else {
                return (Activity) contextWrapper;
            }
        }
    }

    public static final StringBuilder a(String url, JSONObject params) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(params, "params");
        StringBuilder sb = new StringBuilder(url);
        if (StringsKt.indexOf$default((CharSequence) url, '?', 0, false, 6, (Object) null) < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = params.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String str = null;
            try {
                str = params.getString(next);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            arrayList.add(new Pair(next, str));
        }
        sb.append(l.format(arrayList, "UTF-8"));
        return sb;
    }

    public static final StringBuilder a(JSONObject params) {
        Intrinsics.checkNotNullParameter(params, "params");
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = params.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String str = null;
            try {
                str = params.getString(next);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            arrayList.add(new Pair(next, str));
        }
        sb.append(l.format(arrayList, "UTF-8"));
        return sb;
    }

    public static final int a(Activity activity) {
        if (activity == null) {
            return 0;
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        h.i("AbstractRequest", Intrinsics.stringPlus("rotation current ", Integer.valueOf(rotation)));
        int i2 = 2;
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation != 2) {
                    if (rotation != 3) {
                        return 0;
                    }
                }
            }
            i2 = 1;
        }
        f.statisticOrientation(i2);
        return i2;
    }

    public static final String b(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        int i2 = displayMetrics.widthPixels;
        return new StringBuilder().append(i2).append('*').append(displayMetrics.heightPixels).toString();
    }

    public static final float b(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Method declaredMethod = DisplayMetrics.class.getDeclaredMethod("getDeviceDensity", null);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke != null) {
                return ((Integer) invoke).intValue() / 160.0f;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        } catch (Exception unused) {
            return context.getApplicationContext().getResources().getDisplayMetrics().density;
        }
    }

    public static final int c(Context context) {
        PackageInfo packageInfo;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return 0;
        }
        return packageInfo.versionCode;
    }
}
