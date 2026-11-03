package com.reactnativecommunity.webview.permissions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import com.facebook.react.modules.core.PermissionListener;
/* compiled from: WebViewPermissionReceiver.java */
/* loaded from: classes9.dex */
public class a extends ResultReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final String[] f17862a;

    /* renamed from: b  reason: collision with root package name */
    private int f17863b;

    /* renamed from: c  reason: collision with root package name */
    private PermissionListener f17864c;

    public a(int i2, String[] strArr, PermissionListener permissionListener) {
        super(new Handler(Looper.getMainLooper()));
        this.f17863b = i2;
        this.f17862a = strArr;
        this.f17864c = permissionListener;
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i2, Bundle bundle) {
        super.onReceiveResult(i2, bundle);
        int[] iArr = new int[this.f17862a.length];
        int i3 = 0;
        while (true) {
            String[] strArr = this.f17862a;
            if (i3 < strArr.length) {
                iArr[i3] = bundle.getInt(strArr[i3]);
                i3++;
            } else {
                this.f17864c.onRequestPermissionsResult(this.f17863b, strArr, iArr);
                return;
            }
        }
    }
}
