package com.google.android.play.core.assetpacks.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class k extends a implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public k(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    public final void c(Bundle bundle) throws RemoteException {
        Parcel a2 = a();
        c.c(a2, bundle);
        b(4, a2);
    }

    public final void d(Bundle bundle) throws RemoteException {
        Parcel a2 = a();
        c.c(a2, bundle);
        b(3, a2);
    }

    public final void e(Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel a2 = a();
        c.c(a2, bundle);
        c.c(a2, bundle2);
        b(2, a2);
    }
}
