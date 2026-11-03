package com.google.android.gms.internal.p001authapi;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;
/* compiled from: com.google.android.gms:play-services-auth@@20.5.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zbae  reason: invalid package */
/* loaded from: classes5.dex */
public abstract class zbae extends zbb implements zbaf {
    public zbae() {
        super("com.google.android.gms.auth.api.identity.internal.ISaveAccountLinkingTokenCallback");
    }

    @Override // com.google.android.gms.internal.p001authapi.zbb
    protected final boolean zba(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        if (i2 == 1) {
            zbc.zbb(parcel);
            zbb((Status) zbc.zba(parcel, Status.CREATOR), (SaveAccountLinkingTokenResult) zbc.zba(parcel, SaveAccountLinkingTokenResult.CREATOR));
            return true;
        }
        return false;
    }
}
