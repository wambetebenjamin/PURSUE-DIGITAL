package com.bytedance.router;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* compiled from: RoutesConfig.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private String f12197a;

    /* renamed from: b  reason: collision with root package name */
    private String[] f12198b;

    /* renamed from: c  reason: collision with root package name */
    private Set<String> f12199c;

    /* renamed from: d  reason: collision with root package name */
    private Map<String, Class<? extends com.bytedance.router.route.b>> f12200d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(String str) {
        this.f12200d = null;
        this.f12197a = str;
        HashMap hashMap = new HashMap();
        this.f12200d = hashMap;
        hashMap.put(f.HOST_SERVICE, com.bytedance.router.route.f.class);
        this.f12200d.put(f.HOST_BROADCAST, com.bytedance.router.route.c.class);
    }

    public g withOtherSchemes(String[] strArr) {
        this.f12198b = strArr;
        if (strArr == null || strArr.length == 0) {
            this.f12199c = null;
        } else {
            Set<String> set = this.f12199c;
            if (set != null) {
                set.clear();
            } else {
                this.f12199c = new HashSet();
            }
            this.f12199c.addAll(Arrays.asList(strArr));
        }
        return this;
    }

    public g withSupportRoute(String str, Class<? extends com.bytedance.router.route.b> cls) {
        if (TextUtils.isEmpty(str) || cls == null) {
            com.bytedance.router.util.b.w("RoutesConfig#withHostRoute() host or routeClass is null!!!");
            return this;
        }
        if (this.f12200d == null) {
            this.f12200d = new HashMap();
        }
        this.f12200d.put(str, cls);
        return this;
    }

    public String getScheme() {
        return this.f12197a;
    }

    public String[] getOtherSchemes() {
        return this.f12198b;
    }

    public boolean supportScheme(String str) {
        if (str == null || str.length() == 0 || str.equals(this.f12197a)) {
            return true;
        }
        Set<String> set = this.f12199c;
        return set != null && set.contains(str);
    }

    public Class<? extends com.bytedance.router.route.b> getRouteClass(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f12200d.get(str);
    }

    public String toString() {
        StringBuilder append = new StringBuilder("scheme:").append(this.f12197a).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        append.append("other schemes: ");
        String[] strArr = this.f12198b;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                append.append(str).append(',');
            }
            append.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        Map<String, Class<? extends com.bytedance.router.route.b>> map = this.f12200d;
        int size = map != null ? map.size() : 0;
        append.append(String.format("other supported routes: %s routes\n", Integer.valueOf(size)));
        if (size > 0) {
            for (Map.Entry<String, Class<? extends com.bytedance.router.route.b>> entry : this.f12200d.entrySet()) {
                append.append("\t").append(entry.getKey()).append(CertificateUtil.DELIMITER).append(entry.getValue().getName()).append('\n');
            }
        }
        return append.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g a() {
        return new g(f.SCHEME).withOtherSchemes(f.DEFAULT_HISTORY_SCHEME).withSupportRoute(f.HOST_SERVICE, com.bytedance.router.route.f.class).withSupportRoute(f.HOST_BROADCAST, com.bytedance.router.route.c.class);
    }
}
