package com.bytedance.sdk.account.induce;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* compiled from: TriggerCounter.java */
/* loaded from: classes2.dex */
class e {

    /* renamed from: a  reason: collision with root package name */
    private final c f12516a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, Long> f12517b;

    /* compiled from: TriggerCounter.java */
    /* loaded from: classes2.dex */
    interface a {
        public static final int FROM_X = 2;
        public static final int NONE = 0;
        public static final int PER_X = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(c cVar) {
        this.f12516a = cVar;
    }

    private void b() {
        if (this.f12517b != null) {
            return;
        }
        this.f12517b = new HashMap();
        Set<String> e2 = this.f12516a.e();
        if (e2.isEmpty()) {
            return;
        }
        for (String str : e2) {
            this.f12517b.put(str, Long.valueOf(this.f12516a.a(str)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r10 >= r6) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
        if (r10 <= r6) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(org.json.JSONObject r6, java.lang.String r7, long r8, long r10) {
        /*
            r5 = this;
            r0 = 0
            java.lang.Long r2 = java.lang.Long.valueOf(r0)
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r1 = 1
            if (r0 <= 0) goto L51
            java.lang.String r0 = "scenes"
            org.json.JSONObject r6 = r6.optJSONObject(r0)
            if (r6 == 0) goto L51
            org.json.JSONObject r6 = r6.optJSONObject(r7)
            if (r6 == 0) goto L51
            java.lang.String r0 = "trigger"
            org.json.JSONObject r6 = r6.optJSONObject(r0)
            if (r6 == 0) goto L51
            java.lang.String r0 = "progress"
            org.json.JSONObject r6 = r6.optJSONObject(r0)
            if (r6 == 0) goto L51
            java.lang.String r0 = "type"
            java.lang.String r0 = r6.optString(r0)
            java.lang.String r3 = "percent"
            int r6 = r6.optInt(r3)
            java.lang.String r3 = "lower"
            boolean r3 = android.text.TextUtils.equals(r0, r3)
            if (r3 == 0) goto L43
            long r3 = (long) r6
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 < 0) goto L43
            goto L52
        L43:
            java.lang.String r3 = "upper"
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 == 0) goto L51
            long r3 = (long) r6
            int r6 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r6 > 0) goto L51
            goto L52
        L51:
            r1 = 0
        L52:
            r5.b()
            java.util.Map<java.lang.String, java.lang.Long> r6 = r5.f12517b
            java.lang.Object r6 = r6.get(r7)
            java.lang.Long r6 = (java.lang.Long) r6
            if (r6 != 0) goto L60
            r6 = r2
        L60:
            if (r1 == 0) goto L63
            goto L6c
        L63:
            long r10 = r6.longValue()
            long r10 = r10 + r8
            java.lang.Long r2 = java.lang.Long.valueOf(r10)
        L6c:
            java.util.Map<java.lang.String, java.lang.Long> r6 = r5.f12517b
            r6.put(r7, r2)
            com.bytedance.sdk.account.induce.c r6 = r5.f12516a
            long r8 = r2.longValue()
            r6.a(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.induce.e.a(org.json.JSONObject, java.lang.String, long, long):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(String str) {
        b();
        Long l = this.f12517b.get(str);
        if (l == null) {
            l = 0L;
        }
        return l.longValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str, int i2, int i3, long j2) {
        Long l;
        b();
        if (i2 <= 0 || i3 < 0 || (l = this.f12517b.get(str)) == null || l.longValue() < j2) {
            return false;
        }
        if (i2 == 1) {
            if (j2 == 1) {
                return l.longValue() % ((long) i3) == 0;
            }
            long j3 = i3;
            if (j2 > j3 || ((l.longValue() - j2) % j3) + j2 >= j3) {
                return true;
            }
        }
        return i2 == 2 && l.longValue() >= ((long) i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Map<String, Long> map = this.f12517b;
        if (map != null) {
            map.clear();
        }
        this.f12516a.a();
    }
}
