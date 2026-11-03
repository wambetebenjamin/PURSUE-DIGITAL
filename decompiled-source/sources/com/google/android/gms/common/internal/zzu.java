package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzu extends com.google.android.gms.internal.common.zza implements ICancelToken {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICancelToken");
    }

    @Override // com.google.android.gms.common.internal.ICancelToken
    public final void cancel() throws RemoteException {
        zzC(2, zza());
    }
}
