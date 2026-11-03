package com.ss.android.account;

import org.json.JSONObject;
/* compiled from: AbsAccountUserEntity.java */
/* loaded from: classes9.dex */
public class a extends com.bytedance.sdk.account.user.c {
    public String avatarUrl;
    public String description;
    public String userName;

    public String getDescription() {
        return this.description;
    }

    public a() {
    }

    public a(JSONObject jSONObject) {
        super(jSONObject);
    }

    public a(JSONObject jSONObject, JSONObject jSONObject2) {
        super(jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.user.c
    public void extract() {
        super.extract();
        b(this, getUserData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(a aVar, JSONObject jSONObject) {
        if (jSONObject.has("name")) {
            aVar.userName = jSONObject.optString("name");
        } else if (jSONObject.has("username")) {
            aVar.userName = jSONObject.optString("username");
        }
        aVar.avatarUrl = jSONObject.optString(com.bytedance.sdk.account.save.database.a.FIELD_AVATAR_URL);
        aVar.description = jSONObject.optString("description");
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    /* compiled from: AbsAccountUserEntity.java */
    /* renamed from: com.ss.android.account.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0324a implements com.bytedance.sdk.account.user.b {
        @Override // com.bytedance.sdk.account.user.b
        public com.bytedance.sdk.account.user.c parseUserInfo(JSONObject jSONObject) {
            a aVar = new a(jSONObject);
            a.b(aVar, jSONObject);
            return aVar;
        }

        @Override // com.bytedance.sdk.account.user.b
        public com.bytedance.sdk.account.user.c parseUserInfo(JSONObject jSONObject, JSONObject jSONObject2) {
            a aVar = new a(jSONObject, jSONObject2);
            a.b(aVar, jSONObject);
            return aVar;
        }
    }
}
