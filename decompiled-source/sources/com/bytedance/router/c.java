package com.bytedance.router;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
/* compiled from: RouteIntent.java */
/* loaded from: classes2.dex */
public class c {
    public static final int DEFAULT_REQUEST_CODE = Integer.MIN_VALUE;

    /* renamed from: a  reason: collision with root package name */
    private String f12158a;

    /* renamed from: b  reason: collision with root package name */
    private Intent f12159b;

    /* renamed from: c  reason: collision with root package name */
    private String f12160c;

    /* renamed from: d  reason: collision with root package name */
    private Uri f12161d;

    /* renamed from: e  reason: collision with root package name */
    private String f12162e;

    /* renamed from: f  reason: collision with root package name */
    private String f12163f;

    /* renamed from: g  reason: collision with root package name */
    private String f12164g;

    /* renamed from: h  reason: collision with root package name */
    private int f12165h;

    /* renamed from: i  reason: collision with root package name */
    private int f12166i;

    /* renamed from: j  reason: collision with root package name */
    private int f12167j;
    private Uri k;

    private c() {
        this.f12158a = "";
        this.f12159b = null;
        this.f12160c = "";
        this.f12161d = null;
        this.f12162e = "";
        this.f12163f = "";
        this.f12164g = "";
        this.f12165h = -1;
        this.f12166i = -1;
        this.f12167j = Integer.MIN_VALUE;
    }

    public String getUrl() {
        return this.f12160c;
    }

    public void setUrl(String str) {
        if (str == null) {
            return;
        }
        if (!com.bytedance.router.util.c.isLegalUrl(str)) {
            throw new IllegalArgumentException("url is illegal!!!");
        }
        if (str.equals(this.f12160c)) {
            return;
        }
        this.f12160c = str;
        a();
        a(this.f12159b, this.f12160c, true);
    }

    public void setData(Uri uri) {
        this.k = uri;
    }

    public Uri getData() {
        return this.k;
    }

    public Uri getUri() {
        return this.f12161d;
    }

    public String getScheme() {
        return this.f12162e;
    }

    public String getHost() {
        return this.f12163f;
    }

    public String getPath() {
        return this.f12164g;
    }

    public String getOriginUrl() {
        return this.f12158a;
    }

    public int getEnterAnim() {
        return this.f12165h;
    }

    public int getExitAnim() {
        return this.f12166i;
    }

    public void setRequestCode(int i2) {
        this.f12167j = i2;
    }

    public int getRequestCode() {
        return this.f12167j;
    }

    public boolean hasRequestCode() {
        return this.f12167j != Integer.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.f12158a = str;
        if (this.f12160c.equals(str)) {
            return;
        }
        this.f12160c = this.f12158a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3) {
        this.f12165h = i2;
        this.f12166i = i3;
    }

    void a(Intent intent) {
        this.f12159b = intent;
    }

    public Intent getExtra() {
        return this.f12159b;
    }

    void a() {
        if (TextUtils.isEmpty(this.f12160c)) {
            return;
        }
        Uri parse = Uri.parse(this.f12160c);
        this.f12161d = parse;
        this.f12159b.setData(parse);
        this.f12162e = this.f12161d.getScheme();
        this.f12163f = this.f12161d.getHost();
        String path = this.f12161d.getPath();
        this.f12164g = path;
        if (this.f12162e == null) {
            this.f12162e = "";
        }
        if (this.f12163f == null) {
            this.f12163f = "";
        }
        if (path == null) {
            this.f12164g = "";
        }
    }

    static void a(Intent intent, String str, boolean z) {
        Map<String, String> sliceUrlParams;
        if (intent == null || (sliceUrlParams = com.bytedance.router.util.c.sliceUrlParams(str)) == null || sliceUrlParams.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : sliceUrlParams.entrySet()) {
            if (z) {
                intent.putExtra(entry.getKey(), entry.getValue());
            } else if (!intent.hasExtra(entry.getKey())) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
    }

    /* compiled from: RouteIntent.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private String f12168a;

        /* renamed from: b  reason: collision with root package name */
        private Intent f12169b;

        /* renamed from: c  reason: collision with root package name */
        private int f12170c;

        /* renamed from: d  reason: collision with root package name */
        private int f12171d;

        /* renamed from: e  reason: collision with root package name */
        private Uri f12172e;

        public a(String str) {
            this.f12168a = "";
            this.f12169b = new Intent();
            this.f12170c = -1;
            this.f12171d = -1;
            this.f12168a = str;
        }

        public a() {
            this.f12168a = "";
            this.f12169b = new Intent();
            this.f12170c = -1;
            this.f12171d = -1;
        }

        public c build() {
            c cVar = new c();
            com.bytedance.router.util.b.d("Build RouteIntent url: " + this.f12168a);
            c.a(this.f12169b, this.f12168a, false);
            cVar.a(this.f12168a);
            cVar.a(this.f12169b);
            cVar.a(this.f12170c, this.f12171d);
            cVar.setData(this.f12172e);
            cVar.a();
            return cVar;
        }

        public a withUrl(String str) {
            this.f12168a = str;
            return this;
        }

        public a withParam(String str, boolean z) {
            this.f12169b.putExtra(str, z);
            return this;
        }

        public a withParam(String str, boolean[] zArr) {
            this.f12169b.putExtra(str, zArr);
            return this;
        }

        public a withParam(String str, Bundle bundle) {
            this.f12169b.putExtra(str, bundle);
            return this;
        }

        public a withParam(String str, byte b2) {
            this.f12169b.putExtra(str, b2);
            return this;
        }

        public a withParam(String str, byte[] bArr) {
            this.f12169b.putExtra(str, bArr);
            return this;
        }

        public a withParam(String str, char c2) {
            this.f12169b.putExtra(str, c2);
            return this;
        }

        public a withParam(String str, char[] cArr) {
            this.f12169b.putExtra(str, cArr);
            return this;
        }

        public a withParam(String str, CharSequence charSequence) {
            this.f12169b.putExtra(str, charSequence);
            return this;
        }

        public a withParam(String str, CharSequence[] charSequenceArr) {
            this.f12169b.putExtra(str, charSequenceArr);
            return this;
        }

        public a withParamCharSequenceList(String str, ArrayList<CharSequence> arrayList) {
            this.f12169b.putExtra(str, arrayList);
            return this;
        }

        public a withParam(String str, double d2) {
            this.f12169b.putExtra(str, d2);
            return this;
        }

        public a withParam(String str, double[] dArr) {
            this.f12169b.putExtra(str, dArr);
            return this;
        }

        public a withParam(String str, float f2) {
            this.f12169b.putExtra(str, f2);
            return this;
        }

        public a withParam(String str, float[] fArr) {
            this.f12169b.putExtra(str, fArr);
            return this;
        }

        public a withParam(String str, int i2) {
            this.f12169b.putExtra(str, i2);
            return this;
        }

        public a withParam(String str, int[] iArr) {
            this.f12169b.putExtra(str, iArr);
            return this;
        }

        public a withParamIntegerList(String str, ArrayList<Integer> arrayList) {
            this.f12169b.putExtra(str, arrayList);
            return this;
        }

        public a withParam(String str, long j2) {
            this.f12169b.putExtra(str, j2);
            return this;
        }

        public a withParam(String str, long[] jArr) {
            this.f12169b.putExtra(str, jArr);
            return this;
        }

        public a withParam(String str, Parcelable parcelable) {
            this.f12169b.putExtra(str, parcelable);
            return this;
        }

        public a withParam(String str, Parcelable[] parcelableArr) {
            this.f12169b.putExtra(str, parcelableArr);
            return this;
        }

        public a withParamParcelableList(String str, ArrayList<Parcelable> arrayList) {
            this.f12169b.putExtra(str, arrayList);
            return this;
        }

        public a withParam(String str, Serializable serializable) {
            this.f12169b.putExtra(str, serializable);
            return this;
        }

        public a withParam(String str, short s) {
            this.f12169b.putExtra(str, s);
            return this;
        }

        public a withParam(String str, short[] sArr) {
            this.f12169b.putExtra(str, sArr);
            return this;
        }

        public a withParam(String str, String str2) {
            this.f12169b.putExtra(str, str2);
            return this;
        }

        public a withParam(String str, String[] strArr) {
            this.f12169b.putExtra(str, strArr);
            return this;
        }

        public a withParamStringList(String str, ArrayList<String> arrayList) {
            this.f12169b.putExtra(str, arrayList);
            return this;
        }

        public a withParam(Bundle bundle) {
            this.f12169b.putExtras(bundle);
            return this;
        }

        public a withParam(Intent intent) {
            this.f12169b.putExtras(intent);
            return this;
        }

        public a addFlags(int i2) {
            this.f12169b.addFlags(i2);
            return this;
        }

        public a withAnimation(int i2, int i3) {
            this.f12170c = i2;
            this.f12171d = i3;
            return this;
        }

        public a withData(Uri uri) {
            this.f12172e = uri;
            return this;
        }
    }
}
