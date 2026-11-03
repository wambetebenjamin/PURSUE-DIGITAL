package com.bytedance.praisedialoglib.request;

import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import org.json.JSONObject;
/* compiled from: GetDialogEnableThread.java */
/* loaded from: classes2.dex */
public class a implements Runnable {
    public static final String PATH_GET_DIALOG_ENABLE = "/zebra/praise";

    /* renamed from: a  reason: collision with root package name */
    private static final String f11172a = "GetDialogEnableThread";

    /* renamed from: c  reason: collision with root package name */
    private static final int f11173c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static final int f11174d = -1;

    /* renamed from: e  reason: collision with root package name */
    private String f11175e;

    /* renamed from: f  reason: collision with root package name */
    private InterfaceC0167a f11176f;

    /* compiled from: GetDialogEnableThread.java */
    /* renamed from: com.bytedance.praisedialoglib.request.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0167a {
        void onFailed(int i2, String str);

        void onSucceed(String str);
    }

    public a(String str, InterfaceC0167a interfaceC0167a) {
        this.f11175e = str;
        this.f11176f = interfaceC0167a;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("uid", this.f11175e);
            String a2 = com.bytedance.praisedialoglib.manager.a.getInstance().a();
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            String executePost = com.bytedance.praisedialoglib.manager.a.getInstance().executePost(20480, a2 + PATH_GET_DIALOG_ENABLE, hashMap);
            Log.d(f11172a, "response is " + executePost);
            if (TextUtils.isEmpty(executePost)) {
                this.f11176f.onFailed(-1, "http response is null");
                return;
            }
            JSONObject jSONObject = new JSONObject(executePost);
            int optInt = jSONObject.optInt("errCode");
            jSONObject.optString("message");
            String optString = jSONObject.optString("data");
            if (jSONObject.optInt("dialogShow", -1) == 0) {
                this.f11176f.onSucceed(optString);
            } else {
                this.f11176f.onFailed(optInt, optString);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            this.f11176f.onFailed(-1, "meet exception");
        }
    }
}
