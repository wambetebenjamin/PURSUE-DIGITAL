package com.bytedance.sdk.account.user;

import com.bytedance.sdk.account.constants.a;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AccountShareInfo.java */
/* loaded from: classes2.dex */
public class a {
    public String accountExtra;
    public int accountType;
    public String errMsg;
    public String fromInstallId;
    public int isLogin;
    public String secUserId;
    public String userAvatar;
    public String userName;
    public String userSession;

    public a() {
    }

    public a(String str) {
        this.errMsg = str;
    }

    public static a fromJsonString(String str) {
        try {
            a aVar = new a();
            JSONObject jSONObject = new JSONObject(str);
            aVar.fromInstallId = jSONObject.optString(a.InterfaceC0185a.FROM_INSTALL_ID);
            aVar.userAvatar = jSONObject.optString(a.InterfaceC0185a.USER_AVATAR);
            aVar.userSession = jSONObject.optString(a.InterfaceC0185a.USER_SESSION);
            aVar.userName = jSONObject.optString("user_name");
            aVar.secUserId = jSONObject.optString(a.InterfaceC0185a.SEC_USER_ID);
            aVar.isLogin = jSONObject.optInt(a.InterfaceC0185a.ACCOUNT_ONLINE);
            aVar.accountExtra = jSONObject.optString(a.InterfaceC0185a.ACCOUNT_EXTRA);
            aVar.accountType = jSONObject.optInt("account_type");
            return aVar;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
