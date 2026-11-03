package com.bytedance.pipo.iap.common.ability;

import com.bytedance.pipo.iap.common.ability.model.api.entity.ErrorGuideForUser;
import com.bytedance.pipo.iap.common.ability.model.api.entity.VerifyStatus;
import com.bytedance.pipo.iap.model.AbsResult;
import com.google.android.gms.common.GoogleApiAvailability;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: IapResult.java */
/* loaded from: classes2.dex */
public class e extends AbsResult {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10615a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f10616b = -1000;

    /* renamed from: c  reason: collision with root package name */
    public int f10617c;

    /* renamed from: d  reason: collision with root package name */
    private String f10618d;

    /* renamed from: e  reason: collision with root package name */
    private ErrorGuideForUser f10619e;

    /* compiled from: IapResult.java */
    /* loaded from: classes2.dex */
    public @interface a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10620a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10621b = 401;
    }

    /* compiled from: IapResult.java */
    /* loaded from: classes2.dex */
    public @interface b {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10622a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10623b = 4011;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10624c = 4012;
    }

    /* compiled from: IapResult.java */
    /* loaded from: classes2.dex */
    public @interface c {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10625a = 0;

        /* renamed from: d  reason: collision with root package name */
        public static final int f10626d = 203;
        @Deprecated

        /* renamed from: e  reason: collision with root package name */
        public static final int f10627e = 204;

        /* renamed from: f  reason: collision with root package name */
        public static final int f10628f = 206;
        @Deprecated

        /* renamed from: g  reason: collision with root package name */
        public static final int f10629g = -200;
        @Deprecated

        /* renamed from: h  reason: collision with root package name */
        public static final int f10630h = -202;

        /* renamed from: i  reason: collision with root package name */
        public static final int f10631i = -203;

        /* renamed from: j  reason: collision with root package name */
        public static final int f10632j = 207;
        public static final int m = 211;
        public static final int n = 212;
        public static final int o = 20000;
        public static final int p = 214;
        public static final int q = 216;
        public static final int r = 217;
        public static final int s = 218;
        public static final int t = 219;
    }

    /* compiled from: IapResult.java */
    /* loaded from: classes2.dex */
    public @interface d {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10633a = 0;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10634c = 2021;

        /* renamed from: e  reason: collision with root package name */
        public static final int f10635e = 2032;

        /* renamed from: f  reason: collision with root package name */
        public static final int f10636f = 2033;

        /* renamed from: g  reason: collision with root package name */
        public static final int f10637g = 2034;

        /* renamed from: h  reason: collision with root package name */
        public static final int f10638h = 2141;

        /* renamed from: i  reason: collision with root package name */
        public static final int f10639i = 2142;

        /* renamed from: j  reason: collision with root package name */
        public static final int f10640j = 2171;
        public static final int k = 2180;
        public static final int l = 2181;
        public static final int m = 2182;
        public static final int n = 2183;
    }

    /* compiled from: IapResult.java */
    /* renamed from: com.bytedance.pipo.iap.common.ability.e$e  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public @interface InterfaceC0151e {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10641a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10642b = 301;
    }

    /* compiled from: IapResult.java */
    /* loaded from: classes2.dex */
    public @interface f {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10643a = 3011;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10644b = 3012;
    }

    public static e a() {
        return new e(0);
    }

    public e(int i2, int i3, String str) {
        this(i2, String.valueOf(i3), str);
    }

    public e(int i2, String str, String str2) {
        this.f10617c = 0;
        this.mCode = i2;
        this.mDetailCode = str;
        this.mMessage = str2;
        if (i2 != 0) {
            try {
                this.f10617c = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(com.bytedance.pipo.payment.common.lib.support.a.a().h().b());
            } catch (Exception unused) {
                this.f10617c = -1;
            }
        }
    }

    public e(e eVar) {
        this.f10617c = 0;
        this.mCode = eVar.getCode();
        this.mDetailCode = eVar.getDetailCode();
        this.mMessage = eVar.getMessage();
        this.f10617c = eVar.f10617c;
        this.f10618d = eVar.f10618d;
        this.f10619e = com.bytedance.pipo.iap.common.ability.util.a.a(this);
    }

    public e() {
        this(-1000, -1000, "");
    }

    public e(int i2) {
        this(i2, -1000, "");
    }

    public e(int i2, String str) {
        this(i2, -1000, str);
    }

    public static e a(AbsResult absResult) {
        if (absResult instanceof e) {
            return (e) absResult;
        }
        return new e(absResult.getCode(), absResult.getDetailCode(), absResult.getMessage());
    }

    @Override // com.bytedance.pipo.iap.model.AbsResult
    /* renamed from: a */
    public e withErrorCode(int i2) {
        this.mCode = i2;
        return this;
    }

    public e a(String str) {
        this.f10618d = str;
        return this;
    }

    public ErrorGuideForUser f() {
        return this.f10619e;
    }

    public String b() {
        return this.f10618d;
    }

    public boolean c() {
        String str;
        int parseInt;
        try {
            parseInt = Integer.parseInt(this.mDetailCode);
        } catch (Exception unused) {
        }
        if (parseInt == 2021 || parseInt == 2142) {
            return true;
        }
        if (parseInt == 2141) {
            return false;
        }
        return VerifyStatus.UNKNOWN.name().equals(this.f10618d) || VerifyStatus.DIRECT_RETRY.name().equals(this.f10618d) || (str = this.f10618d) == null || str.length() == 0;
    }

    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", this.mCode);
            jSONObject.put("detailCode", this.mDetailCode);
            jSONObject.put("message", this.mMessage);
            ErrorGuideForUser errorGuideForUser = this.f10619e;
            if (errorGuideForUser != null) {
                jSONObject.put("error_guide_for_user", errorGuideForUser.toJson());
            } else {
                jSONObject.put("error_guide_for_user", new JSONObject());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public int e() {
        return this.f10617c;
    }

    @Override // com.bytedance.pipo.iap.model.AbsResult
    public String toString() {
        return "IapResult{mCode=" + this.mCode + ", mDetailCode=" + this.mDetailCode + ", mMessage='" + this.mMessage + '}';
    }
}
