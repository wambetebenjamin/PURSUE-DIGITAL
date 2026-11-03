package com.bytedance.push.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: SwitcherStatus.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private List<a> f11442a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, Object> f11443b;

    public List<a> getSwitchers() {
        return this.f11442a;
    }

    public void setSwitchers(List<a> list) {
        this.f11442a = list;
    }

    public void setExtra(Map<String, Object> map) {
        this.f11443b = map;
    }

    public void setExtra(String str, Object obj) {
        if (this.f11443b == null) {
            this.f11443b = new HashMap();
        }
        this.f11443b.put(str, obj);
    }

    public Map<String, Object> getExtra() {
        return this.f11443b;
    }

    public void setSwitcher(a aVar) {
        if (aVar == null) {
            this.f11442a = null;
            return;
        }
        List<a> list = this.f11442a;
        if (list == null) {
            this.f11442a = Collections.singletonList(aVar);
        } else {
            list.add(aVar);
        }
    }

    public String extraToJson() {
        if (this.f11443b == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : this.f11443b.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String switcherToJson() {
        ArrayList<a> arrayList = this.f11442a == null ? null : new ArrayList(this.f11442a);
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (a aVar : arrayList) {
            if (aVar != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("is_close", aVar.f11445b ? 0 : 1);
                    jSONObject.put("name", aVar.f11444a);
                    JSONObject jSONObject2 = new JSONObject();
                    if (aVar.f11446c != null) {
                        for (Map.Entry entry : aVar.f11446c.entrySet()) {
                            jSONObject2.put((String) entry.getKey(), entry.getValue());
                        }
                    }
                    jSONObject.put("extra", jSONObject2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void checkValid() {
        /*
            r4 = this;
            java.util.List<com.bytedance.push.model.c$a> r0 = r4.f11442a
            if (r0 != 0) goto L6
            r0 = 0
            goto Ld
        L6:
            java.util.ArrayList r0 = new java.util.ArrayList
            java.util.List<com.bytedance.push.model.c$a> r1 = r4.f11442a
            r0.<init>(r1)
        Ld:
            if (r0 == 0) goto L46
            java.util.Iterator r0 = r0.iterator()
        L13:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L46
            java.lang.Object r1 = r0.next()
            com.bytedance.push.model.c$a r1 = (com.bytedance.push.model.c.a) r1
            if (r1 == 0) goto L2c
            java.lang.String r2 = com.bytedance.push.model.c.a.b(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L2c
            goto L13
        L2c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "wrong config : "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.push.model.c.checkValid():void");
    }

    /* compiled from: SwitcherStatus.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f11444a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f11445b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, Object> f11446c;

        public a(String str, boolean z, Map<String, Object> map) {
            this.f11444a = str;
            this.f11445b = z;
            this.f11446c = map;
        }

        public String getTag() {
            return this.f11444a;
        }

        public void setTag(String str) {
            this.f11444a = str;
        }

        public boolean isOpen() {
            return this.f11445b;
        }

        public void setOpen(boolean z) {
            this.f11445b = z;
        }

        public Map<String, Object> getExtra() {
            return this.f11446c;
        }

        public void setExtra(Map<String, Object> map) {
            this.f11446c = map;
        }

        public void setExtra(String str, Object obj) {
            if (this.f11446c == null) {
                this.f11446c = new HashMap();
            }
            this.f11446c.put(str, obj);
        }

        public String toString() {
            return "ChildSwitcher{tag='" + this.f11444a + "', isOpen=" + this.f11445b + ", extra=" + this.f11446c + '}';
        }
    }

    /* compiled from: SwitcherStatus.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private int f11447a;

        /* renamed from: b  reason: collision with root package name */
        private int f11448b;

        public b(int i2, int i3) {
            this.f11447a = i2;
            this.f11448b = i3;
        }

        public int getHour() {
            return this.f11447a;
        }

        public void setHour(int i2) {
            this.f11447a = i2;
        }

        public int getMinute() {
            return this.f11448b;
        }

        public void setMinute(int i2) {
            this.f11448b = i2;
        }

        String a() {
            return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(this.f11447a), Integer.valueOf(this.f11448b));
        }

        public String toString() {
            return a();
        }
    }
}
