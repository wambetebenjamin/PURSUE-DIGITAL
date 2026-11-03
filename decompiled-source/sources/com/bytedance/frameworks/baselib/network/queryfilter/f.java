package com.bytedance.frameworks.baselib.network.queryfilter;

import com.bytedance.retrofit2.client.Request;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: QueryRemoveAction.java */
/* loaded from: classes2.dex */
public class f extends c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8422a = "f";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8423g = "protect_background_request";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8424h = "add_background_rm_mark";

    /* renamed from: i  reason: collision with root package name */
    private static final String f8425i = "bgrm";

    /* renamed from: c  reason: collision with root package name */
    private List<String> f8426c;

    /* renamed from: d  reason: collision with root package name */
    private List<String> f8427d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f8428e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f8429f;

    public f(int i2) {
        super(i2);
        this.f8426c = new ArrayList();
        this.f8427d = new ArrayList();
        this.f8428e = false;
        this.f8429f = false;
    }

    @Override // com.bytedance.frameworks.baselib.network.queryfilter.c
    protected void a(JSONObject jSONObject) {
        b(jSONObject);
        d.a(jSONObject.optJSONArray("keep_list"), this.f8426c);
        d.a(jSONObject.optJSONArray("remove_list"), this.f8427d);
        this.f8406b = jSONObject.optInt(f8423g, 0) > 0;
        this.f8429f = jSONObject.optInt(f8424h, 0) > 0;
        if (this.f8426c.isEmpty() || this.f8427d.isEmpty()) {
            return;
        }
        this.f8428e = true;
    }

    @Override // com.bytedance.frameworks.baselib.network.queryfilter.c
    protected boolean a(Request request, Map<String, List<String>> map) {
        if (this.f8428e || !a(request) || map.isEmpty()) {
            return false;
        }
        if (!this.f8426c.isEmpty()) {
            Map<? extends String, ? extends List<String>> linkedHashMap = new LinkedHashMap<>();
            if (map.containsKey("x-tt-encrypt-queries")) {
                linkedHashMap.put("x-tt-encrypt-queries", map.get("x-tt-encrypt-queries"));
            }
            for (String str : this.f8426c) {
                if (map.containsKey(str)) {
                    linkedHashMap.put(str, map.get(str));
                }
            }
            map.clear();
            map.putAll(linkedHashMap);
            return true;
        } else if (this.f8427d.isEmpty()) {
            return true;
        } else {
            for (String str2 : this.f8427d) {
                map.remove(str2);
            }
            String url = request.getUrl() != null ? request.getUrl() : "";
            if (!this.f8429f || url.contains(f8425i)) {
                return true;
            }
            for (String str3 : this.f8427d) {
                if (url.contains(str3)) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("1");
                    map.put(f8425i, arrayList);
                    return true;
                }
            }
            return true;
        }
    }
}
