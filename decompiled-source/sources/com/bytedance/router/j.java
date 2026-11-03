package com.bytedance.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.bytedance.router.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
/* compiled from: SmartRoute.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private Context f12204a;

    /* renamed from: f  reason: collision with root package name */
    private Uri f12209f;

    /* renamed from: b  reason: collision with root package name */
    private String f12205b = "";

    /* renamed from: d  reason: collision with root package name */
    private int f12207d = -1;

    /* renamed from: e  reason: collision with root package name */
    private int f12208e = -1;

    /* renamed from: c  reason: collision with root package name */
    private Intent f12206c = new Intent();

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Context context) {
        this.f12204a = context;
    }

    public void open() {
        if (this.f12204a == null) {
            com.bytedance.router.util.b.e("SmartRoute#open context is null!!!");
        } else if (TextUtils.isEmpty(this.f12205b)) {
            com.bytedance.router.util.b.e("SmartRoute#url is null!!!");
        } else if (!com.bytedance.router.util.c.isLegalUrl(this.f12205b)) {
            com.bytedance.router.util.b.e("SmartRoute#url is illegal and url is " + this.f12205b);
        } else {
            d.a().open(this.f12204a, a());
        }
    }

    public void open(int i2) {
        if (i2 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("requestCode must not be Integer.MIN_VALUE:-2147483648 !!!");
        }
        if (this.f12204a == null) {
            com.bytedance.router.util.b.e("SmartRoute#open context is null!!!");
        } else if (TextUtils.isEmpty(this.f12205b)) {
            com.bytedance.router.util.b.e("SmartRoute#url is null!!!");
        } else if (!com.bytedance.router.util.c.isLegalUrl(this.f12205b)) {
            com.bytedance.router.util.b.e("SmartRoute#url is illegal and url is " + this.f12205b);
        } else if (!(this.f12204a instanceof Activity)) {
            com.bytedance.router.util.b.e("SmartRoute#context is not Activity!!!");
        } else {
            c a2 = a();
            a2.setRequestCode(i2);
            d.a().open(this.f12204a, a2);
        }
    }

    public Intent buildIntent() {
        if (TextUtils.isEmpty(this.f12205b)) {
            com.bytedance.router.util.b.e("SmartRoute#url is null!!!");
            return null;
        } else if (!com.bytedance.router.util.c.isLegalUrl(this.f12205b)) {
            com.bytedance.router.util.b.e("SmartRoute#url is illegal and url is " + this.f12205b);
            return null;
        } else {
            return d.a().a(this.f12204a, a());
        }
    }

    private c a() {
        return new c.a().withUrl(this.f12205b).withParam(this.f12206c).addFlags(this.f12206c.getFlags()).withAnimation(this.f12207d, this.f12208e).withData(this.f12209f).build();
    }

    public j withUrl(String str) {
        this.f12205b = str;
        return this;
    }

    public j withParam(String str, boolean z) {
        this.f12206c.putExtra(str, z);
        return this;
    }

    public j withParam(String str, boolean[] zArr) {
        this.f12206c.putExtra(str, zArr);
        return this;
    }

    public j withParam(String str, Bundle bundle) {
        this.f12206c.putExtra(str, bundle);
        return this;
    }

    public j withParam(String str, byte b2) {
        this.f12206c.putExtra(str, b2);
        return this;
    }

    public j withParam(String str, byte[] bArr) {
        this.f12206c.putExtra(str, bArr);
        return this;
    }

    public j withParam(String str, char c2) {
        this.f12206c.putExtra(str, c2);
        return this;
    }

    public j withParam(String str, char[] cArr) {
        this.f12206c.putExtra(str, cArr);
        return this;
    }

    public j withParam(String str, CharSequence charSequence) {
        this.f12206c.putExtra(str, charSequence);
        return this;
    }

    public j withParam(String str, CharSequence[] charSequenceArr) {
        this.f12206c.putExtra(str, charSequenceArr);
        return this;
    }

    public j withParamCharSequenceList(String str, ArrayList<CharSequence> arrayList) {
        this.f12206c.putCharSequenceArrayListExtra(str, arrayList);
        return this;
    }

    public j withParam(String str, double d2) {
        this.f12206c.putExtra(str, d2);
        return this;
    }

    public j withParam(String str, double[] dArr) {
        this.f12206c.putExtra(str, dArr);
        return this;
    }

    public j withParam(String str, float f2) {
        this.f12206c.putExtra(str, f2);
        return this;
    }

    public j withParam(String str, float[] fArr) {
        this.f12206c.putExtra(str, fArr);
        return this;
    }

    public j withParam(String str, int i2) {
        this.f12206c.putExtra(str, i2);
        return this;
    }

    public j withParam(String str, int[] iArr) {
        this.f12206c.putExtra(str, iArr);
        return this;
    }

    public j withParamIntegerList(String str, ArrayList<Integer> arrayList) {
        this.f12206c.putIntegerArrayListExtra(str, arrayList);
        return this;
    }

    public j withParam(String str, long j2) {
        this.f12206c.putExtra(str, j2);
        return this;
    }

    public j withParam(String str, long[] jArr) {
        this.f12206c.putExtra(str, jArr);
        return this;
    }

    public j withParam(String str, Parcelable parcelable) {
        this.f12206c.putExtra(str, parcelable);
        return this;
    }

    public j withParam(String str, Parcelable[] parcelableArr) {
        this.f12206c.putExtra(str, parcelableArr);
        return this;
    }

    public j withParamParcelableList(String str, ArrayList<? extends Parcelable> arrayList) {
        this.f12206c.putParcelableArrayListExtra(str, arrayList);
        return this;
    }

    public j withParam(String str, Serializable serializable) {
        this.f12206c.putExtra(str, serializable);
        return this;
    }

    public j withParam(String str, short s) {
        this.f12206c.putExtra(str, s);
        return this;
    }

    public j withParam(String str, short[] sArr) {
        this.f12206c.putExtra(str, sArr);
        return this;
    }

    public j withParam(String str, String str2) {
        this.f12206c.putExtra(str, str2);
        return this;
    }

    public j withParam(String str, String[] strArr) {
        this.f12206c.putExtra(str, strArr);
        return this;
    }

    public j withParamStringList(String str, ArrayList<String> arrayList) {
        this.f12206c.putStringArrayListExtra(str, arrayList);
        return this;
    }

    public j withParam(Bundle bundle) {
        this.f12206c.putExtras(bundle);
        return this;
    }

    public j withParam(Intent intent) {
        if (intent.getExtras() != null) {
            if (this.f12206c.getExtras() == null) {
                this.f12206c.putExtras(new Bundle());
            }
            this.f12206c.getExtras().putAll(intent.getExtras());
        }
        return this;
    }

    public j addFlags(int i2) {
        this.f12206c.addFlags(i2);
        return this;
    }

    public j withAnimation(int i2, int i3) {
        this.f12207d = i2;
        this.f12208e = i3;
        return this;
    }

    public j withParam(Uri uri) {
        this.f12209f = uri;
        return this;
    }

    private void a(Intent intent, String str) {
        Map<String, String> sliceUrlParams = com.bytedance.router.util.c.sliceUrlParams(str);
        if (sliceUrlParams == null || sliceUrlParams.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : sliceUrlParams.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }
    }
}
