package com.google.android.gms.internal.play_billing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
final class zzii {
    static final long zza;
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final zzih zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0154  */
    static {
        /*
            Method dump skipped, instructions count: 344
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzii.<clinit>():void");
    }

    private zzii() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i2 = zzei.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC == null) {
            Field zzC2 = zzC(Buffer.class, "address");
            if (zzC2 == null || zzC2.getType() != Long.TYPE) {
                return null;
            }
            return zzC2;
        }
        return zzC;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzD(Object obj, long j2, byte b2) {
        Unsafe unsafe = zzf.zza;
        long j3 = (-4) & j2;
        int i2 = unsafe.getInt(obj, j3);
        int i3 = ((~((int) j2)) & 3) << 3;
        unsafe.putInt(obj, j3, ((255 & b2) << i3) | (i2 & (~(255 << i3))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzE(Object obj, long j2, byte b2) {
        Unsafe unsafe = zzf.zza;
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        unsafe.putInt(obj, j3, ((255 & b2) << i2) | (unsafe.getInt(obj, j3) & (~(255 << i2))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zza(Object obj, long j2) {
        return zzf.zza(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzb(Object obj, long j2) {
        return zzf.zzb(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(Object obj, long j2) {
        return zzf.zza.getInt(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzd(Object obj, long j2) {
        return zzf.zza.getLong(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzf(Object obj, long j2) {
        return zzf.zza.getObject(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzg() {
        Unsafe unsafe;
        try {
            unsafe = (Unsafe) AccessController.doPrivileged(new zzie());
        } catch (Throwable unused) {
            unsafe = null;
        }
        if (unsafe == null) {
            return null;
        }
        try {
            unsafe.arrayBaseOffset(byte[].class);
            return unsafe;
        } catch (Exception unused2) {
            Logger.getLogger(zzii.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "getUnsafe", "As part of the planned removal, sun.misc.Unsafe is available in the current environment but configured to throw on use. Protobuf will continue without using it, but with slightly reduced performance. --sun-misc-unsafe-memory-access=allow is likely available to opt back in if desired. A later Protobuf version release will stop using sun.misc.Unsafe entirely.");
            return null;
        }
    }

    static /* bridge */ /* synthetic */ void zzh(Throwable th) {
        Logger.getLogger(zzii.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzm(Object obj, long j2, boolean z) {
        zzf.zzc(obj, j2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzn(byte[] bArr, long j2, byte b2) {
        zzf.zzd(bArr, zza + j2, b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzo(Object obj, long j2, double d2) {
        zzf.zze(obj, j2, d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzp(Object obj, long j2, float f2) {
        zzf.zzf(obj, j2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzq(Object obj, long j2, int i2) {
        zzf.zza.putInt(obj, j2, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzr(Object obj, long j2, long j3) {
        zzf.zza.putLong(obj, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzs(Object obj, long j2, Object obj2) {
        zzf.zza.putObject(obj, j2, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j2) {
        return ((byte) ((zzf.zza.getInt(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j2) {
        return ((byte) ((zzf.zza.getInt(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean zzv(Class cls) {
        int i2 = zzei.zza;
        try {
            Class cls2 = zzd;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzw(Object obj, long j2) {
        return zzf.zzg(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzx() {
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }
}
