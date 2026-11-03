package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
/* compiled from: com.google.android.gms:play-services-auth@@20.5.0 */
/* loaded from: classes4.dex */
public abstract class zbo extends com.google.android.gms.internal.p001authapi.zbb implements zbp {
    public zbo() {
        super("com.google.android.gms.auth.api.signin.internal.IRevocationService");
    }

    @Override // com.google.android.gms.internal.p001authapi.zbb
    protected final boolean zba(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            zbc();
        } else if (i2 != 2) {
            return false;
        } else {
            zbb();
        }
        return true;
    }
}
