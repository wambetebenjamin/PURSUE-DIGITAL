package com.google.android.play.core.assetpacks.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class d extends a implements f {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void c(String str, List list, Bundle bundle, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        c.c(a2, bundle);
        a2.writeStrongBinder(hVar);
        b(14, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void d(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        c.c(a2, bundle2);
        a2.writeStrongBinder(hVar);
        b(11, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void e(String str, Bundle bundle, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        a2.writeStrongBinder(hVar);
        b(5, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void f(String str, Bundle bundle, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        a2.writeStrongBinder(hVar);
        b(10, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void g(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        c.c(a2, bundle2);
        a2.writeStrongBinder(hVar);
        b(6, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void h(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        c.c(a2, bundle2);
        a2.writeStrongBinder(hVar);
        b(7, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void i(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        c.c(a2, bundle2);
        a2.writeStrongBinder(hVar);
        b(9, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void j(String str, Bundle bundle, Bundle bundle2, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        c.c(a2, bundle);
        c.c(a2, bundle2);
        a2.writeStrongBinder(hVar);
        b(13, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void k(String str, List list, Bundle bundle, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        c.c(a2, bundle);
        a2.writeStrongBinder(hVar);
        b(12, a2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.core.assetpacks.internal.f
    public final void l(String str, List list, Bundle bundle, h hVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        c.c(a2, bundle);
        a2.writeStrongBinder(hVar);
        b(2, a2);
    }
}
