package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
/* loaded from: classes4.dex */
final class zzq {
    private final Messenger zza;
    private final zzd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzq(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if (Objects.equals(interfaceDescriptor, "android.os.IMessenger")) {
            this.zza = new Messenger(iBinder);
            this.zzb = null;
        } else if (Objects.equals(interfaceDescriptor, IMessengerCompat.DESCRIPTOR)) {
            this.zzb = new zzd(iBinder);
            this.zza = null;
        } else {
            Log.w("MessengerIpcClient", "Invalid interface descriptor: ".concat(String.valueOf(interfaceDescriptor)));
            throw new RemoteException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(Message message) throws RemoteException {
        Messenger messenger = this.zza;
        if (messenger != null) {
            messenger.send(message);
            return;
        }
        zzd zzdVar = this.zzb;
        if (zzdVar != null) {
            zzdVar.zzb(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
