package com.google.android.play.core.assetpacks.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class g extends b implements h {
    public g() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
    }

    @Override // com.google.android.play.core.assetpacks.internal.b
    protected final boolean a(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
        switch (i2) {
            case 2:
                c.b(parcel);
                n(parcel.readInt(), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                c.b(parcel);
                b(parcel.readInt(), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 4:
                c.b(parcel);
                f(parcel.readInt(), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 5:
                ArrayList createTypedArrayList = parcel.createTypedArrayList(Bundle.CREATOR);
                c.b(parcel);
                g(createTypedArrayList);
                return true;
            case 6:
                c.b(parcel);
                i((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                c.b(parcel);
                d((Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 8:
                c.b(parcel);
                j((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 9:
            default:
                return false;
            case 10:
                c.b(parcel);
                k((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 11:
                c.b(parcel);
                h((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 12:
                c.b(parcel);
                e((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                c.b(parcel);
                m((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 14:
                c.b(parcel);
                l((Bundle) c.a(parcel, Bundle.CREATOR), (Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
            case 15:
                c.b(parcel);
                c((Bundle) c.a(parcel, Bundle.CREATOR));
                return true;
        }
    }
}
