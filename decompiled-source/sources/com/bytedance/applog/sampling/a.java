package com.bytedance.applog.sampling;

import android.text.TextUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
/* compiled from: AbsSamplingStrategy.java */
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3345a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f3346b = 10000;

    /* renamed from: c  reason: collision with root package name */
    final int f3347c;

    /* renamed from: d  reason: collision with root package name */
    private final Set<String> f3348d;

    /* renamed from: e  reason: collision with root package name */
    private final Map<String, List<Set<String>>> f3349e;

    /* renamed from: f  reason: collision with root package name */
    private final Map<String, List<Map<String, Object>>> f3350f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean a(b bVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(int i2, Set<String> set, Map<String, List<Set<String>>> map, Map<String, List<Map<String, Object>>> map2) {
        this.f3347c = i2;
        this.f3348d = set;
        this.f3349e = map;
        this.f3350f = map2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int i2 = this.f3347c;
        if (i2 <= 0) {
            return -1;
        }
        return i2 >= 10000 ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Set<String> set = this.f3348d;
        if (set == null || !set.contains(str)) {
            if (jSONObject == null) {
                return false;
            }
            return b(str, jSONObject);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(String str, String str2) {
        Map<String, List<Map<String, Object>>> map;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Set<String> set = this.f3348d;
        if (set == null || !set.contains(str)) {
            if (TextUtils.isEmpty(str2)) {
                return false;
            }
            Map<String, List<Set<String>>> map2 = this.f3349e;
            if ((map2 != null && map2.containsKey(str)) || ((map = this.f3350f) != null && map.containsKey(str))) {
                try {
                    return b(str, new JSONObject(str2));
                } catch (Throwable unused) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(java.lang.String r7, org.json.JSONObject r8) {
        /*
            r6 = this;
            java.util.Map<java.lang.String, java.util.List<java.util.Set<java.lang.String>>> r0 = r6.f3349e
            r1 = 1
            if (r0 == 0) goto L3a
            boolean r0 = r0.containsKey(r7)
            if (r0 == 0) goto L3a
            java.util.Map<java.lang.String, java.util.List<java.util.Set<java.lang.String>>> r0 = r6.f3349e
            java.lang.Object r0 = r0.get(r7)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L3a
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L3a
            java.util.Iterator r0 = r0.iterator()
        L1f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L3a
            java.lang.Object r2 = r0.next()
            java.util.Set r2 = (java.util.Set) r2
            if (r2 == 0) goto L1f
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L1f
            boolean r2 = r6.a(r8, r2)
            if (r2 == 0) goto L1f
            return r1
        L3a:
            java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r0 = r6.f3350f
            r2 = 0
            if (r0 == 0) goto Lad
            boolean r0 = r0.containsKey(r7)
            if (r0 == 0) goto Lad
            java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r0 = r6.f3350f
            java.lang.Object r7 = r0.get(r7)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto Lad
            boolean r0 = r7.isEmpty()
            if (r0 != 0) goto Lad
            java.util.Iterator r7 = r7.iterator()
        L59:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto Lad
            java.lang.Object r0 = r7.next()
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L59
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L59
            java.util.Set r3 = r0.keySet()
            boolean r3 = r6.a(r8, r3)
            if (r3 == 0) goto L59
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L7f:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto La9
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.getValue()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto La7
            if (r3 == 0) goto La7
            java.lang.Object r4 = r8.opt(r4)
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L7f
        La7:
            r0 = r2
            goto Laa
        La9:
            r0 = r1
        Laa:
            if (r0 == 0) goto L59
            return r1
        Lad:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.sampling.a.b(java.lang.String, org.json.JSONObject):boolean");
    }

    private boolean a(JSONObject jSONObject, Set<String> set) {
        for (String str : set) {
            if (!jSONObject.has(str)) {
                return false;
            }
        }
        return true;
    }
}
