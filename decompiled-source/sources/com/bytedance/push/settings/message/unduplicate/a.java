package com.bytedance.push.settings.message.unduplicate;

import com.bytedance.push.settings.c;
import com.bytedance.push.settings.d;
import org.json.JSONObject;
/* compiled from: UnDuplicateSettingsConverter.java */
/* loaded from: classes2.dex */
public class a implements c<b>, d<b> {

    /* renamed from: a  reason: collision with root package name */
    private final String f11796a = "enable_un_duplicate_message";

    /* renamed from: b  reason: collision with root package name */
    private final String f11797b = "max_cache_message";

    /* renamed from: c  reason: collision with root package name */
    private final String f11798c = "max_cache_time_in_hour";

    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public b create() {
        return new b();
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public b to(String str) {
        b create = create();
        try {
            JSONObject jSONObject = new JSONObject(str);
            create.f11799a = jSONObject.optBoolean("enable_un_duplicate_message");
            create.f11800b = jSONObject.optInt("max_cache_message", 200);
            create.f11801c = jSONObject.optLong("max_cache_time_in_hour", 24L);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return create;
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(b bVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("enable_un_duplicate_message", bVar.f11799a);
            jSONObject.put("max_cache_message", bVar.f11800b);
            jSONObject.put("max_cache_time_in_hour", bVar.f11801c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject.toString();
    }
}
