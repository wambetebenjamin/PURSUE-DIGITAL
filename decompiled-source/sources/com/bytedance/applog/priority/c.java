package com.bytedance.applog.priority;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
/* compiled from: EventPriority.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3101a = -1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f3102b = 0;

    /* renamed from: f  reason: collision with root package name */
    Map<String, Integer> f3106f;

    /* renamed from: h  reason: collision with root package name */
    private Map<Integer, d> f3108h;

    /* renamed from: j  reason: collision with root package name */
    private JSONArray f3110j;

    /* renamed from: c  reason: collision with root package name */
    int f3103c = 0;

    /* renamed from: d  reason: collision with root package name */
    int f3104d = 5;

    /* renamed from: e  reason: collision with root package name */
    int f3105e = -1;

    /* renamed from: i  reason: collision with root package name */
    private final Set<String> f3109i = new HashSet();

    /* renamed from: g  reason: collision with root package name */
    boolean f3107g = false;

    public void a(JSONArray jSONArray) {
        this.f3110j = jSONArray;
    }

    public JSONArray a() {
        return this.f3110j;
    }

    public int b() {
        return this.f3103c;
    }

    public int c() {
        return this.f3104d;
    }

    public boolean d() {
        Map<Integer, d> map;
        d dVar;
        return this.f3103c >= 0 && this.f3104d > 0 && (map = this.f3108h) != null && !map.isEmpty() && map.containsKey(-1) && (dVar = map.get(-1)) != null && dVar.f3114d > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(java.lang.String r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.priority.c.a(java.lang.String, java.lang.String):int");
    }

    public d a(int i2) {
        Map<Integer, d> map = this.f3108h;
        if (map != null) {
            return map.get(Integer.valueOf(i2));
        }
        return null;
    }

    public void a(Map<Integer, d> map) {
        d value;
        this.f3108h = map;
        this.f3109i.clear();
        Map<Integer, d> map2 = this.f3108h;
        if (map2 == null) {
            return;
        }
        for (Map.Entry<Integer, d> entry : map2.entrySet()) {
            if (-1 != entry.getKey().intValue() && (value = entry.getValue()) != null) {
                if (value.f3116f != null && !value.f3116f.isEmpty()) {
                    this.f3109i.addAll(value.f3116f.keySet());
                }
                if (value.f3117g != null && !value.f3117g.isEmpty()) {
                    this.f3109i.addAll(value.f3117g.keySet());
                }
            }
        }
    }

    public Map<Integer, d> e() {
        return this.f3108h;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Map<String, Integer> map = this.f3106f;
        if (map == null || !map.containsKey(str)) {
            return this.f3109i.contains(str);
        }
        return true;
    }

    public boolean f() {
        return this.f3107g;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.bytedance.applog.priority.c r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != 0) goto L4
            return r0
        L4:
            int r1 = r7.f3104d
            int r2 = r6.f3104d
            if (r1 == r2) goto Lb
            return r0
        Lb:
            java.util.Map r1 = r6.e()
            java.util.Map r7 = r7.e()
            if (r1 == 0) goto L67
            if (r7 != 0) goto L18
            goto L67
        L18:
            int r2 = r1.size()
            int r3 = r7.size()
            if (r2 == r3) goto L23
            return r0
        L23:
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L2b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L65
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.Object r2 = r2.getValue()
            com.bytedance.applog.priority.d r2 = (com.bytedance.applog.priority.d) r2
            if (r3 == 0) goto L64
            if (r2 != 0) goto L48
            goto L64
        L48:
            boolean r4 = r7.containsKey(r3)
            if (r4 != 0) goto L4f
            return r0
        L4f:
            java.lang.Object r3 = r7.get(r3)
            com.bytedance.applog.priority.d r3 = (com.bytedance.applog.priority.d) r3
            if (r3 != 0) goto L58
            return r0
        L58:
            long r4 = r2.d()
            long r2 = r3.d()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L2b
        L64:
            return r0
        L65:
            r7 = 0
            return r7
        L67:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.priority.c.a(com.bytedance.applog.priority.c):boolean");
    }
}
