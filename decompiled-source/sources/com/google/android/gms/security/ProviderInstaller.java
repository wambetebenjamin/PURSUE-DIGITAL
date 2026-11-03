package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.common.zzh;
import com.google.android.gms.internal.common.zzi;
import com.google.android.gms.internal.common.zzj;
import java.lang.reflect.Method;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public class ProviderInstaller {
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final GoogleApiAvailabilityLight zza = GoogleApiAvailabilityLight.getInstance();
    private static final Object zzb = new Object();
    private static Method zzc = null;
    private static boolean zzd = false;

    /* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    /* loaded from: classes5.dex */
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i2, Intent intent);

        void onProviderInstalled();
    }

    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        String str;
        Context context2;
        Preconditions.checkNotNull(context, "Context must not be null");
        zza.verifyGooglePlayServicesIsAvailable(context, 11925000);
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (zzb) {
            Context context3 = null;
            if (!zzd) {
                try {
                    context2 = DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.providerinstaller.dynamite").getModuleContext();
                } catch (DynamiteModule.LoadingException e2) {
                    String message = e2.getMessage();
                    String.valueOf(message);
                    Log.w("ProviderInstaller", str.concat(String.valueOf(message)));
                    context2 = null;
                }
                if (context2 != null) {
                    zzb(context2, context, "com.google.android.gms.providerinstaller.ProviderInstallerImpl");
                    return;
                }
            }
            boolean z = !zzd;
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext != null) {
                zzd = true;
                if (z) {
                    try {
                        zzj.zzb("com.google.android.gms.common.security.ProviderInstallerImpl", "reportRequestStats2", remoteContext.getClassLoader(), zzi.zzb(Context.class, context), zzh.zza(uptimeMillis), zzh.zza(SystemClock.uptimeMillis()));
                    } catch (Exception e3) {
                        Log.w("ProviderInstaller", "Failed to report request stats: ".concat(e3.toString()));
                    }
                }
                context3 = remoteContext;
            }
            if (context3 != null) {
                zzb(context3, context, "com.google.android.gms.common.security.ProviderInstallerImpl");
            } else {
                Log.e("ProviderInstaller", "Failed to get remote context");
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener providerInstallListener) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(providerInstallListener, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context, providerInstallListener).execute(new Void[0]);
    }

    private static void zzb(Context context, Context context2, String str) throws GooglePlayServicesNotAvailableException {
        try {
            if (zzc == null) {
                zzc = context.getClassLoader().loadClass(str).getMethod("insertProvider", Context.class);
            }
            zzc.invoke(null, context);
        } catch (Exception e2) {
            Throwable cause = e2.getCause();
            if (Log.isLoggable("ProviderInstaller", 6)) {
                String exc = cause == null ? e2.toString() : cause.toString();
                String.valueOf(exc);
                Log.e("ProviderInstaller", "Failed to install provider: ".concat(String.valueOf(exc)));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
