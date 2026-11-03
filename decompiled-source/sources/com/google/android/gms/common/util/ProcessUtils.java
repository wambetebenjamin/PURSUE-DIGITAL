package com.google.android.gms.common.util;

import android.app.Application;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzi;
import com.google.android.gms.internal.common.zzj;
import com.google.android.gms.internal.common.zzx;
import com.google.android.gms.internal.common.zzy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public class ProcessUtils {
    @Nullable
    private static String zza;
    private static int zzb;
    @Nullable
    private static Boolean zzc;

    private ProcessUtils() {
    }

    public static boolean zza() {
        Boolean bool = zzc;
        if (bool == null) {
            if (PlatformVersion.isAtLeastP()) {
                bool = Boolean.valueOf(Process.isIsolated());
            } else {
                try {
                    Object zza2 = zzj.zza(Process.class, "isIsolated", new zzi[0]);
                    Object[] objArr = new Object[0];
                    if (zza2 == null) {
                        throw new zzy(zzx.zza("expected a non-null reference", objArr));
                    }
                    bool = (Boolean) zza2;
                } catch (ReflectiveOperationException unused) {
                    bool = false;
                }
            }
            zzc = bool;
        }
        return bool.booleanValue();
    }

    public static String getMyProcessName() {
        BufferedReader bufferedReader;
        if (zza == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                zza = Application.getProcessName();
            } else {
                int i2 = zzb;
                if (i2 == 0) {
                    i2 = Process.myPid();
                    zzb = i2;
                }
                String str = null;
                str = null;
                str = null;
                BufferedReader bufferedReader2 = null;
                if (i2 > 0) {
                    try {
                        StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 14);
                        sb.append("/proc/");
                        sb.append(i2);
                        sb.append("/cmdline");
                        String sb2 = sb.toString();
                        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        bufferedReader = new BufferedReader(new FileReader(sb2));
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    } catch (IOException unused) {
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        String readLine = bufferedReader.readLine();
                        Preconditions.checkNotNull(readLine);
                        String str2 = readLine;
                        str = readLine.trim();
                    } catch (IOException unused2) {
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        IOUtils.closeQuietly(bufferedReader2);
                        throw th;
                    }
                    IOUtils.closeQuietly(bufferedReader);
                }
                zza = str;
            }
        }
        return zza;
    }
}
