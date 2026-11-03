package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public class GoogleSignatureVerifier {
    @Nullable
    private static GoogleSignatureVerifier zza;
    @Nullable
    private static volatile Set zzd;
    @Nullable
    private static volatile Set zze;
    private final Context zzb;
    private volatile String zzc;

    public GoogleSignatureVerifier(Context context) {
        this.zzb = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zza == null) {
                zzo.zza(context);
                zza = new GoogleSignatureVerifier(context);
            }
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c5, code lost:
        r5 = r9;
     */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean zza(android.content.pm.PackageInfo r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zza(android.content.pm.PackageInfo, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c1  */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r8v6, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.google.android.gms.common.zzy zzb(@javax.annotation.Nullable java.lang.String r6, boolean r7, boolean r8) {
        /*
            r5 = this;
            java.lang.String r7 = "null pkg"
            if (r6 != 0) goto L9
            com.google.android.gms.common.zzy r6 = com.google.android.gms.common.zzy.zzc(r7)
            return r6
        L9:
            java.lang.String r8 = r5.zzc
            boolean r8 = r6.equals(r8)
            if (r8 != 0) goto Ld4
            int r8 = com.google.android.gms.common.zzo.zzh
            android.os.StrictMode$ThreadPolicy r8 = android.os.StrictMode.allowThreadDiskReads()
            r0 = 1
            com.google.android.gms.common.zzo.zzb()     // Catch: java.lang.Throwable -> L45 android.os.RemoteException -> L48 com.google.android.gms.dynamite.DynamiteModule.LoadingException -> L4a
            com.google.android.gms.common.internal.zzad r1 = com.google.android.gms.common.zzo.zzg     // Catch: java.lang.Throwable -> L45 android.os.RemoteException -> L48 com.google.android.gms.dynamite.DynamiteModule.LoadingException -> L4a
            boolean r1 = r1.zzg()     // Catch: java.lang.Throwable -> L45 android.os.RemoteException -> L48 com.google.android.gms.dynamite.DynamiteModule.LoadingException -> L4a
            android.os.StrictMode.setThreadPolicy(r8)
            if (r1 == 0) goto L55
            com.google.android.gms.common.zzv r7 = new com.google.android.gms.common.zzv
            r8 = 0
            r7.<init>(r8)
            r7.zza(r6)
            android.content.Context r8 = r5.zzb
            boolean r8 = com.google.android.gms.common.GooglePlayServicesUtilLight.honorsDebugCertificates(r8)
            r7.zzb(r8)
            r7.zzc(r0)
            com.google.android.gms.common.zzw r7 = r7.zzd()
            com.google.android.gms.common.zzy r7 = com.google.android.gms.common.zzo.zzc(r7)
            goto Lbd
        L45:
            r6 = move-exception
            goto Ld0
        L48:
            r1 = move-exception
            goto L4b
        L4a:
            r1 = move-exception
        L4b:
            java.lang.String r2 = "GoogleCertificates"
            java.lang.String r3 = "Failed to get Google certificates from remote"
            android.util.Log.e(r2, r3, r1)     // Catch: java.lang.Throwable -> L45
            android.os.StrictMode.setThreadPolicy(r8)
        L55:
            int r8 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r8 < r1) goto L5f
            r8 = 134217792(0x8000040, float:3.8518893E-34)
            goto L61
        L5f:
            r8 = 64
        L61:
            android.content.Context r1 = r5.zzb     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lc4
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lc4
            android.content.pm.PackageInfo r8 = r1.getPackageInfo(r6, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lc4
            android.content.Context r1 = r5.zzb
            boolean r1 = com.google.android.gms.common.GooglePlayServicesUtilLight.honorsDebugCertificates(r1)
            if (r8 != 0) goto L78
            com.google.android.gms.common.zzy r7 = com.google.android.gms.common.zzy.zzc(r7)
            goto Lbd
        L78:
            android.content.pm.Signature[] r7 = r8.signatures
            if (r7 == 0) goto Lb7
            android.content.pm.Signature[] r7 = r8.signatures
            int r7 = r7.length
            if (r7 == r0) goto L82
            goto Lb7
        L82:
            com.google.android.gms.common.zzk r7 = new com.google.android.gms.common.zzk
            android.content.pm.Signature[] r2 = r8.signatures
            r3 = 0
            r2 = r2[r3]
            byte[] r2 = r2.toByteArray()
            r7.<init>(r2)
            java.lang.String r2 = r8.packageName
            com.google.android.gms.common.zzy r1 = com.google.android.gms.common.zzo.zzd(r2, r7, r1, r3)
            boolean r4 = r1.zza
            if (r4 == 0) goto Lb5
            android.content.pm.ApplicationInfo r4 = r8.applicationInfo
            if (r4 == 0) goto Lb5
            android.content.pm.ApplicationInfo r8 = r8.applicationInfo
            int r8 = r8.flags
            r8 = r8 & 2
            if (r8 == 0) goto Lb5
            com.google.android.gms.common.zzy r7 = com.google.android.gms.common.zzo.zzd(r2, r7, r3, r0)
            boolean r7 = r7.zza
            if (r7 == 0) goto Lb5
            java.lang.String r7 = "debuggable release cert app rejected"
            com.google.android.gms.common.zzy r7 = com.google.android.gms.common.zzy.zzc(r7)
            goto Lbd
        Lb5:
            r7 = r1
            goto Lbd
        Lb7:
            java.lang.String r7 = "single cert required"
            com.google.android.gms.common.zzy r7 = com.google.android.gms.common.zzy.zzc(r7)
        Lbd:
            boolean r8 = r7.zza
            if (r8 == 0) goto Lc3
            r5.zzc = r6
        Lc3:
            return r7
        Lc4:
            r7 = move-exception
            java.lang.String r8 = "no pkg "
            java.lang.String r6 = r8.concat(r6)
            com.google.android.gms.common.zzy r6 = com.google.android.gms.common.zzy.zzd(r6, r7)
            return r6
        Ld0:
            android.os.StrictMode.setThreadPolicy(r8)
            throw r6
        Ld4:
            com.google.android.gms.common.zzy r6 = com.google.android.gms.common.zzy.zzb()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zzb(java.lang.String, boolean, boolean):com.google.android.gms.common.zzy");
    }

    @Nullable
    private static zzj zzc(PackageInfo packageInfo, zzj... zzjVarArr) {
        if (packageInfo.signatures != null) {
            if (packageInfo.signatures.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            zzk zzkVar = new zzk(packageInfo.signatures[0].toByteArray());
            for (int i2 = 0; i2 < zzjVarArr.length; i2++) {
                if (zzjVarArr[i2].equals(zzkVar)) {
                    return zzjVarArr[i2];
                }
            }
        }
        return null;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    public boolean isPackageGoogleSigned(@Nullable String str) {
        zzy zzb = zzb(str, false, false);
        zzb.zze();
        return zzb.zza;
    }

    public boolean isUidGoogleSigned(int i2) {
        zzy zzc;
        int length;
        String[] packagesForUid = this.zzb.getPackageManager().getPackagesForUid(i2);
        if (packagesForUid != null && (length = packagesForUid.length) != 0) {
            zzc = null;
            int i3 = 0;
            while (true) {
                if (i3 < length) {
                    zzc = zzb(packagesForUid[i3], false, false);
                    if (zzc.zza) {
                        break;
                    }
                    i3++;
                } else {
                    Preconditions.checkNotNull(zzc);
                    break;
                }
            }
        } else {
            zzc = zzy.zzc("no pkgs");
        }
        zzc.zze();
        return zzc.zza;
    }
}
