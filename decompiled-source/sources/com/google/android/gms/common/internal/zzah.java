package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzah {
    private static final Uri zza = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent zza(Context context, zzn zznVar) throws zzaf {
        Bundle bundle;
        ContentProviderClient acquireUnstableContentProviderClient;
        String zza2 = zznVar.zza();
        if (zza2 == null) {
            return new Intent().setComponent(zznVar.zzc());
        }
        Intent intent = null;
        if (zznVar.zzd()) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("serviceActionBundleKey", zza2);
            try {
                acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(zza);
            } catch (RemoteException | IllegalArgumentException e2) {
                Log.w("ServiceBindIntentUtils", "Dynamic intent resolution failed: ".concat(e2.toString()));
                bundle = null;
            }
            if (acquireUnstableContentProviderClient == null) {
                throw new RemoteException("Failed to acquire ContentProviderClient");
            }
            try {
                bundle = acquireUnstableContentProviderClient.call("serviceIntentCall", null, bundle2);
                acquireUnstableContentProviderClient.release();
                if (bundle != null) {
                    Intent intent2 = (Intent) bundle.getParcelable("serviceResponseIntentKey");
                    if (intent2 != null) {
                        intent = intent2;
                    } else {
                        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("serviceMissingResolutionIntentKey");
                        if (pendingIntent != null) {
                            StringBuilder sb = new StringBuilder(zza2.length() + 72);
                            sb.append("Dynamic lookup for intent failed for action ");
                            sb.append(zza2);
                            sb.append(" but has possible resolution");
                            Log.w("ServiceBindIntentUtils", sb.toString());
                            throw new zzaf(new ConnectionResult(25, pendingIntent));
                        }
                    }
                }
                if (intent == null) {
                    Log.w("ServiceBindIntentUtils", "Dynamic lookup for intent failed for action: ".concat(zza2));
                }
            } catch (Throwable th) {
                acquireUnstableContentProviderClient.release();
                throw th;
            }
        }
        return intent == null ? new Intent(zza2).setPackage(zznVar.zzb()) : intent;
    }
}
