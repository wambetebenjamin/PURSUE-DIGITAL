package com.bytedance.push.model;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: LocalRevokeCache.java */
/* loaded from: classes2.dex */
public class a {
    public long receiveTimeStamp;
    public long revokeId;
    public long rid;
    public int sender;

    public static a parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        a aVar = new a();
        aVar.receiveTimeStamp = jSONObject.optLong("ts");
        aVar.rid = jSONObject.optLong(com.ss.android.pushmanager.e.f18493b);
        aVar.revokeId = jSONObject.optLong("revoke_id");
        aVar.sender = jSONObject.optInt("sender");
        return aVar;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.receiveTimeStamp);
            jSONObject.put(com.ss.android.pushmanager.e.f18493b, this.rid);
            jSONObject.put("revoke_id", this.revokeId);
            jSONObject.put("sender", this.sender);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
