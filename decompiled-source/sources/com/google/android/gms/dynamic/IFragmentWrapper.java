package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzc;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public interface IFragmentWrapper extends IInterface {

    /* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    /* loaded from: classes4.dex */
    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements IFragmentWrapper {
        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static IFragmentWrapper asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return queryLocalInterface instanceof IFragmentWrapper ? (IFragmentWrapper) queryLocalInterface : new zza(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            switch (i2) {
                case 2:
                    IObjectWrapper zzb = zzb();
                    parcel2.writeNoException();
                    zzc.zze(parcel2, zzb);
                    return true;
                case 3:
                    Bundle zzc = zzc();
                    parcel2.writeNoException();
                    zzc.zzd(parcel2, zzc);
                    return true;
                case 4:
                    int zzd = zzd();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzd);
                    return true;
                case 5:
                    IFragmentWrapper zze = zze();
                    parcel2.writeNoException();
                    zzc.zze(parcel2, zze);
                    return true;
                case 6:
                    IObjectWrapper zzf = zzf();
                    parcel2.writeNoException();
                    zzc.zze(parcel2, zzf);
                    return true;
                case 7:
                    boolean zzg = zzg();
                    parcel2.writeNoException();
                    int i4 = zzc.zza;
                    parcel2.writeInt(zzg ? 1 : 0);
                    return true;
                case 8:
                    String zzh = zzh();
                    parcel2.writeNoException();
                    parcel2.writeString(zzh);
                    return true;
                case 9:
                    IFragmentWrapper zzi = zzi();
                    parcel2.writeNoException();
                    zzc.zze(parcel2, zzi);
                    return true;
                case 10:
                    int zzj = zzj();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzj);
                    return true;
                case 11:
                    boolean zzk = zzk();
                    parcel2.writeNoException();
                    int i5 = zzc.zza;
                    parcel2.writeInt(zzk ? 1 : 0);
                    return true;
                case 12:
                    IObjectWrapper zzl = zzl();
                    parcel2.writeNoException();
                    zzc.zze(parcel2, zzl);
                    return true;
                case 13:
                    boolean zzm = zzm();
                    parcel2.writeNoException();
                    int i6 = zzc.zza;
                    parcel2.writeInt(zzm ? 1 : 0);
                    return true;
                case 14:
                    boolean zzn = zzn();
                    parcel2.writeNoException();
                    int i7 = zzc.zza;
                    parcel2.writeInt(zzn ? 1 : 0);
                    return true;
                case 15:
                    boolean zzo = zzo();
                    parcel2.writeNoException();
                    int i8 = zzc.zza;
                    parcel2.writeInt(zzo ? 1 : 0);
                    return true;
                case 16:
                    boolean zzp = zzp();
                    parcel2.writeNoException();
                    int i9 = zzc.zza;
                    parcel2.writeInt(zzp ? 1 : 0);
                    return true;
                case 17:
                    boolean zzq = zzq();
                    parcel2.writeNoException();
                    int i10 = zzc.zza;
                    parcel2.writeInt(zzq ? 1 : 0);
                    return true;
                case 18:
                    boolean zzr = zzr();
                    parcel2.writeNoException();
                    int i11 = zzc.zza;
                    parcel2.writeInt(zzr ? 1 : 0);
                    return true;
                case 19:
                    boolean zzs = zzs();
                    parcel2.writeNoException();
                    int i12 = zzc.zza;
                    parcel2.writeInt(zzs ? 1 : 0);
                    return true;
                case 20:
                    IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzc.zzf(parcel);
                    zzt(asInterface);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    boolean zza = zzc.zza(parcel);
                    zzc.zzf(parcel);
                    zzu(zza);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    boolean zza2 = zzc.zza(parcel);
                    zzc.zzf(parcel);
                    zzv(zza2);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    boolean zza3 = zzc.zza(parcel);
                    zzc.zzf(parcel);
                    zzw(zza3);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    boolean zza4 = zzc.zza(parcel);
                    zzc.zzf(parcel);
                    zzx(zza4);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    zzc.zzf(parcel);
                    zzy((Intent) zzc.zzb(parcel, Intent.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    int readInt = parcel.readInt();
                    zzc.zzf(parcel);
                    zzz((Intent) zzc.zzb(parcel, Intent.CREATOR), readInt);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzc.zzf(parcel);
                    zzA(asInterface2);
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }
    }

    void zzA(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzb() throws RemoteException;

    Bundle zzc() throws RemoteException;

    int zzd() throws RemoteException;

    IFragmentWrapper zze() throws RemoteException;

    IObjectWrapper zzf() throws RemoteException;

    boolean zzg() throws RemoteException;

    String zzh() throws RemoteException;

    IFragmentWrapper zzi() throws RemoteException;

    int zzj() throws RemoteException;

    boolean zzk() throws RemoteException;

    IObjectWrapper zzl() throws RemoteException;

    boolean zzm() throws RemoteException;

    boolean zzn() throws RemoteException;

    boolean zzo() throws RemoteException;

    boolean zzp() throws RemoteException;

    boolean zzq() throws RemoteException;

    boolean zzr() throws RemoteException;

    boolean zzs() throws RemoteException;

    void zzt(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzu(boolean z) throws RemoteException;

    void zzv(boolean z) throws RemoteException;

    void zzw(boolean z) throws RemoteException;

    void zzx(boolean z) throws RemoteException;

    void zzy(Intent intent) throws RemoteException;

    void zzz(Intent intent, int i2) throws RemoteException;
}
