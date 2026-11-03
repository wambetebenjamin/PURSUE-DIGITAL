package com.ss.android.token;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: ITokenService.java */
/* loaded from: classes9.dex */
public interface k {

    /* compiled from: ITokenService.java */
    /* loaded from: classes9.dex */
    public interface a {
        void a(b bVar);

        void b(b bVar);
    }

    Context a();

    void a(String str, String str2);

    void a(String str, Map<String, String> map, Map<String, String> map2, boolean z, a aVar);

    void a(String str, JSONObject jSONObject);

    boolean b();

    String c();

    /* compiled from: ITokenService.java */
    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f18594a;

        /* renamed from: b  reason: collision with root package name */
        public int f18595b;

        /* renamed from: c  reason: collision with root package name */
        public String f18596c;

        /* renamed from: d  reason: collision with root package name */
        public String f18597d;

        /* renamed from: e  reason: collision with root package name */
        public JSONObject f18598e;

        public b(int i2, int i3, String str, String str2, JSONObject jSONObject) {
            this.f18594a = i2;
            this.f18595b = i3;
            this.f18596c = str;
            this.f18597d = str2;
            this.f18598e = jSONObject;
        }

        public String toString() {
            return "Response{errorCode=" + this.f18594a + ", detailErrorCode=" + this.f18595b + ", errorMessage='" + this.f18596c + "', detailErrorMessage='" + this.f18597d + "', data=" + this.f18598e + '}';
        }
    }
}
