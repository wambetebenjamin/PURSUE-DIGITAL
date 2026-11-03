package com.ss.android.deviceregister;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.ss.android.common.applog.NetUtil;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: AbsDeviceParamsProvider.java */
/* loaded from: classes9.dex */
public abstract class a implements com.ss.android.deviceregister.core.cache.a {

    /* renamed from: d  reason: collision with root package name */
    private static final String f18127d = "_local";

    /* renamed from: g  reason: collision with root package name */
    private static String f18128g = null;

    /* renamed from: h  reason: collision with root package name */
    private static String f18129h = null;

    /* renamed from: i  reason: collision with root package name */
    private static volatile String f18130i = null;

    /* renamed from: j  reason: collision with root package name */
    private static final String f18131j = "DeviceParamsProvider";

    /* renamed from: a  reason: collision with root package name */
    protected final Context f18132a;

    /* renamed from: b  reason: collision with root package name */
    protected final boolean f18133b;

    /* renamed from: c  reason: collision with root package name */
    protected final String f18134c;

    /* renamed from: e  reason: collision with root package name */
    private com.ss.android.deviceregister.core.cache.internal.b f18135e;

    /* renamed from: f  reason: collision with root package name */
    private final com.ss.android.deviceregister.core.cache.internal.a f18136f;

    private String a(boolean z) {
        return z ? f18127d : "";
    }

    protected void a(e eVar, com.ss.android.deviceregister.core.cache.internal.b bVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, boolean z) {
        this.f18134c = a(z);
        this.f18133b = z;
        Context applicationContext = context.getApplicationContext();
        this.f18132a = applicationContext;
        e eVar = new e(context, com.ss.android.deviceregister.base.a.isAnonymous());
        this.f18135e = new com.ss.android.deviceregister.core.cache.internal.d(applicationContext);
        com.ss.android.deviceregister.core.cache.internal.a aVar = new com.ss.android.deviceregister.core.cache.internal.a(applicationContext);
        this.f18136f = aVar;
        this.f18135e.setSuccessor(aVar);
        if ((!com.ss.android.deviceregister.utils.e.isAndroidROrHigher() || !com.ss.android.deviceregister.utils.e.isTargetROrHigher(context)) && DeviceRegisterManager.isNeedSharedStorage()) {
            a(eVar, aVar);
        }
        if (!DeviceRegisterManager.isDeleteSharedStorage() || DeviceRegisterManager.isNeedSharedStorage()) {
            return;
        }
        eVar.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
        if (r0.length() >= 13) goto L24;
     */
    @Override // com.ss.android.deviceregister.core.cache.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getOpenUdid(boolean r8) {
        /*
            r7 = this;
            java.lang.String r8 = "openudid"
            java.lang.String r0 = com.ss.android.deviceregister.a.f18128g
            boolean r0 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r0 != 0) goto Ld
            java.lang.String r8 = com.ss.android.deviceregister.a.f18128g
            return r8
        Ld:
            android.content.Context r0 = r7.f18132a
            java.lang.String r0 = com.ss.android.deviceregister.utils.d.getSecureAndroidId(r0)
            r1 = 13
            if (r0 == 0) goto L25
            java.lang.String r2 = "9774d56d682e549c"
            boolean r2 = r0.equals(r2)     // Catch: java.lang.Exception -> L83
            if (r2 != 0) goto L25
            int r2 = r0.length()     // Catch: java.lang.Exception -> L83
            if (r2 >= r1) goto L89
        L25:
            android.content.Context r2 = r7.f18132a     // Catch: java.lang.Exception -> L83
            java.lang.String r3 = com.ss.android.deviceregister.base.a.getDeviceParamsSpName()     // Catch: java.lang.Exception -> L83
            r4 = 0
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r4)     // Catch: java.lang.Exception -> L83
            r3 = 0
            java.lang.String r3 = r2.getString(r8, r3)     // Catch: java.lang.Exception -> L83
            boolean r5 = com.ss.android.deviceregister.utils.b.isValidUDID(r3)     // Catch: java.lang.Exception -> L83
            if (r5 != 0) goto L81
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch: java.lang.Exception -> L83
            r3.<init>()     // Catch: java.lang.Exception -> L83
            java.math.BigInteger r5 = new java.math.BigInteger     // Catch: java.lang.Exception -> L83
            r6 = 80
            r5.<init>(r6, r3)     // Catch: java.lang.Exception -> L83
            r3 = 16
            java.lang.String r3 = r5.toString(r3)     // Catch: java.lang.Exception -> L83
            char r4 = r3.charAt(r4)     // Catch: java.lang.Exception -> L83
            r5 = 45
            if (r4 != r5) goto L5a
            r4 = 1
            java.lang.String r3 = r3.substring(r4)     // Catch: java.lang.Exception -> L83
        L5a:
            int r4 = r3.length()     // Catch: java.lang.Exception -> L83
            int r1 = r1 - r4
            if (r1 <= 0) goto L77
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L83
            r4.<init>()     // Catch: java.lang.Exception -> L83
        L66:
            if (r1 <= 0) goto L70
            r5 = 70
            r4.append(r5)     // Catch: java.lang.Exception -> L83
            int r1 = r1 + (-1)
            goto L66
        L70:
            r4.append(r3)     // Catch: java.lang.Exception -> L83
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Exception -> L83
        L77:
            android.content.SharedPreferences$Editor r1 = r2.edit()     // Catch: java.lang.Exception -> L83
            r1.putString(r8, r3)     // Catch: java.lang.Exception -> L83
            r1.commit()     // Catch: java.lang.Exception -> L83
        L81:
            r0 = r3
            goto L89
        L83:
            r8 = move-exception
            java.lang.String r1 = "DeviceParamsProviderexception when making openudid: "
            com.ss.android.common.util.b.e(r1, r8)
        L89:
            boolean r8 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r8 != 0) goto La2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.String r0 = r7.f18134c
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.String r0 = r8.toString()
        La2:
            boolean r8 = com.bytedance.common.utility.o.isEmpty(r0)
            if (r8 != 0) goto Laa
            com.ss.android.deviceregister.a.f18128g = r0
        Laa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.deviceregister.a.getOpenUdid(boolean):java.lang.String");
    }

    @Override // com.ss.android.deviceregister.core.cache.a
    public String getClientUDID() {
        if (!TextUtils.isEmpty(f18129h)) {
            return f18129h;
        }
        try {
            SharedPreferences sharedPreferences = this.f18132a.getSharedPreferences(com.ss.android.deviceregister.base.a.getDeviceParamsSpName(), 0);
            String string = sharedPreferences.getString("clientudid", null);
            if (!com.ss.android.deviceregister.utils.b.isValidUDID(string)) {
                string = UUID.randomUUID().toString();
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("clientudid", string);
                edit.commit();
            }
            if (!com.bytedance.common.utility.o.isEmpty(string)) {
                string = string + this.f18134c;
            }
            f18129h = string;
            return string;
        } catch (Exception e2) {
            com.ss.android.common.util.b.w("DeviceParamsProvider exception when making client_udid: ", e2);
            return "";
        }
    }

    protected void a(JSONArray jSONArray, String str) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("id");
                if (!TextUtils.isEmpty(optString)) {
                    optJSONObject.remove("id");
                    optJSONObject.put("id", optString + str);
                }
            }
        }
    }

    @Override // com.ss.android.deviceregister.core.cache.a
    public String getDeviceId() {
        synchronized (this) {
            if (!com.bytedance.common.utility.o.isEmpty(f18130i)) {
                return f18130i;
            }
            f18130i = this.f18135e.loadDeviceId("", "");
            return f18130i;
        }
    }

    @Override // com.ss.android.deviceregister.core.cache.a
    public void updateDeviceId(String str) {
        if (NetUtil.isBadId(str) || com.bytedance.common.utility.o.equal(str, f18130i)) {
            return;
        }
        f18130i = this.f18135e.loadDeviceId(str, f18130i);
    }

    @Override // com.ss.android.deviceregister.core.cache.a
    public void clear(String str) {
        if (TextUtils.equals(str, "device_id")) {
            f18130i = null;
        }
        if (TextUtils.equals(str, "openudid")) {
            f18128g = null;
        }
        if (TextUtils.equals(str, "clientudid")) {
            f18129h = null;
        }
        this.f18135e.clear(str);
    }

    public void setAccount(Account account) {
        this.f18136f.setAccount(account);
    }

    public void clearDidAndIid(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f18130i = null;
        String str2 = "clear_key_prefix" + str;
        SharedPreferences applogStatsSp = com.ss.android.deviceregister.base.a.getApplogStatsSp(context);
        if (!applogStatsSp.getBoolean(str2, false)) {
            SharedPreferences.Editor edit = applogStatsSp.edit();
            edit.putBoolean(str2, true);
            if (applogStatsSp.contains("device_id")) {
                edit.remove("device_id");
            }
            if (applogStatsSp.contains("install_id")) {
                edit.remove("install_id");
            }
            edit.commit();
            this.f18135e.clear("device_id");
            if (Logger.debug()) {
                com.ss.android.common.util.b.w("DeviceParamsProvider clearKey : " + str + " :clear installId and deviceId finish");
            }
        } else if (Logger.debug()) {
            com.ss.android.common.util.b.w("DeviceParamsProvider clearKey : " + str + " : is already cleared");
        }
        u.b(u.f18267a, "DeviceParamsProvider#clear mCacheHandler.loadDeviceId()=" + this.f18135e.loadDeviceId("", ""));
    }

    static String a(Context context) {
        String str = context.getString(R.string.key_external_derectory_device_parameter) + com.ss.android.deviceregister.core.cache.internal.c.getBytedanceString();
        return new String(new char[]{str.charAt(1), str.charAt(3), str.charAt(1), str.charAt(11), str.charAt(15), str.charAt(10), str.charAt(8), str.charAt(17), str.charAt(9), str.charAt(9), str.charAt(12), str.charAt(13), str.charAt(13), str.charAt(5), str.charAt(18), str.charAt(13), str.charAt(14), str.charAt(11), str.charAt(4), str.charAt(19), str.charAt(7)}).substring(2, 18);
    }
}
