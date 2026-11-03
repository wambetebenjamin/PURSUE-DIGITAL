package com.google.android.play.core.assetpacks.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public class a implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    private final IBinder f16804a;

    /* renamed from: b  reason: collision with root package name */
    private final String f16805b;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(IBinder iBinder, String str) {
        this.f16804a = iBinder;
        this.f16805b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Parcel a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f16805b);
        return obtain;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f16804a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(int i2, Parcel parcel) throws RemoteException {
        try {
            this.f16804a.transact(i2, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
