package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int i2, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zzb(int i2, Bundle bundle) throws RemoteException;

    void zzc(int i2, IBinder iBinder, zzj zzjVar) throws RemoteException;
}
