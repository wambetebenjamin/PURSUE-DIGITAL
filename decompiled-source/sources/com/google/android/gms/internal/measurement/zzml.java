package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
/* loaded from: classes5.dex */
public final class zzml {
    static final boolean zza;
    private static final Unsafe zzb;
    private static final Class<?> zzc;
    private static final boolean zzd;
    private static final boolean zze;
    private static final zzb zzf;
    private static final boolean zzg;
    private static final boolean zzh;
    private static final long zzi;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    /* loaded from: classes5.dex */
    private static final class zza extends zzb {
        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final double zza(Object obj, long j2) {
            return Double.longBitsToDouble(zze(obj, j2));
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final float zzb(Object obj, long j2) {
            return Float.intBitsToFloat(zzd(obj, j2));
        }

        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, boolean z) {
            if (zzml.zza) {
                zzml.zza(obj, j2, z);
            } else {
                zzml.zzb(obj, j2, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, byte b2) {
            if (zzml.zza) {
                zzml.zzc(obj, j2, b2);
            } else {
                zzml.zzd(obj, j2, b2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, double d2) {
            zza(obj, j2, Double.doubleToLongBits(d2));
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, float f2) {
            zza(obj, j2, Float.floatToIntBits(f2));
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final boolean zzc(Object obj, long j2) {
            if (zzml.zza) {
                return zzml.zzf(obj, j2);
            }
            return zzml.zzg(obj, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    /* loaded from: classes5.dex */
    public static abstract class zzb {
        Unsafe zza;

        public abstract double zza(Object obj, long j2);

        public abstract void zza(Object obj, long j2, byte b2);

        public abstract void zza(Object obj, long j2, double d2);

        public abstract void zza(Object obj, long j2, float f2);

        public abstract void zza(Object obj, long j2, boolean z);

        public abstract float zzb(Object obj, long j2);

        public abstract boolean zzc(Object obj, long j2);

        public final int zzd(Object obj, long j2) {
            return this.zza.getInt(obj, j2);
        }

        public final long zze(Object obj, long j2) {
            return this.zza.getLong(obj, j2);
        }

        zzb(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public final void zza(Object obj, long j2, int i2) {
            this.zza.putInt(obj, j2, i2);
        }

        public final void zza(Object obj, long j2, long j3) {
            this.zza.putLong(obj, j2, j3);
        }

        public final boolean zza() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                cls.getMethod("getInt", Object.class, Long.TYPE);
                cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                cls.getMethod("getObject", Object.class, Long.TYPE);
                cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                return true;
            } catch (Throwable th) {
                zzml.zza(th);
                return false;
            }
        }

        public final boolean zzb() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                return zzml.zza() != null;
            } catch (Throwable th) {
                zzml.zza(th);
                return false;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
    /* loaded from: classes5.dex */
    private static final class zzc extends zzb {
        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final double zza(Object obj, long j2) {
            return Double.longBitsToDouble(zze(obj, j2));
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final float zzb(Object obj, long j2) {
            return Float.intBitsToFloat(zzd(obj, j2));
        }

        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, boolean z) {
            if (zzml.zza) {
                zzml.zza(obj, j2, z);
            } else {
                zzml.zzb(obj, j2, z);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, byte b2) {
            if (zzml.zza) {
                zzml.zzc(obj, j2, b2);
            } else {
                zzml.zzd(obj, j2, b2);
            }
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, double d2) {
            zza(obj, j2, Double.doubleToLongBits(d2));
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final void zza(Object obj, long j2, float f2) {
            zza(obj, j2, Float.floatToIntBits(f2));
        }

        @Override // com.google.android.gms.internal.measurement.zzml.zzb
        public final boolean zzc(Object obj, long j2) {
            if (zzml.zza) {
                return zzml.zzf(obj, j2);
            }
            return zzml.zzg(obj, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zza(Object obj, long j2) {
        return zzf.zza(obj, j2);
    }

    static /* synthetic */ Field zza() {
        return zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzb(Object obj, long j2) {
        return zzf.zzb(obj, j2);
    }

    private static int zzb(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzc(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(Object obj, long j2) {
        return zzf.zzd(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzd(Object obj, long j2) {
        return zzf.zze(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zza(Class<T> cls) {
        try {
            return (T) zzb.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zze(Object obj, long j2) {
        return zzf.zza.getObject(obj, j2);
    }

    private static Field zze() {
        Field zza2 = zza(Buffer.class, "effectiveDirectAddress");
        if (zza2 != null) {
            return zza2;
        }
        Field zza3 = zza(Buffer.class, "address");
        if (zza3 == null || zza3.getType() != Long.TYPE) {
            return null;
        }
        return zza3;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzb() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzmn());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ void zza(Throwable th) {
        Logger.getLogger(zzml.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: " + String.valueOf(th));
    }

    static /* synthetic */ void zza(Object obj, long j2, boolean z) {
        zzc(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    static /* synthetic */ void zzb(Object obj, long j2, boolean z) {
        zzd(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0097  */
    static {
        /*
            java.lang.Class<double[]> r0 = double[].class
            java.lang.Class<float[]> r1 = float[].class
            java.lang.Class<long[]> r2 = long[].class
            java.lang.Class<int[]> r3 = int[].class
            java.lang.Class<boolean[]> r4 = boolean[].class
            sun.misc.Unsafe r5 = zzb()
            com.google.android.gms.internal.measurement.zzml.zzb = r5
            java.lang.Class r6 = com.google.android.gms.internal.measurement.zzih.zza()
            com.google.android.gms.internal.measurement.zzml.zzc = r6
            java.lang.Class r6 = java.lang.Long.TYPE
            boolean r6 = zzd(r6)
            com.google.android.gms.internal.measurement.zzml.zzd = r6
            java.lang.Class r7 = java.lang.Integer.TYPE
            boolean r7 = zzd(r7)
            com.google.android.gms.internal.measurement.zzml.zze = r7
            if (r5 == 0) goto L38
            if (r6 == 0) goto L30
            com.google.android.gms.internal.measurement.zzml$zzc r6 = new com.google.android.gms.internal.measurement.zzml$zzc
            r6.<init>(r5)
            goto L39
        L30:
            if (r7 == 0) goto L38
            com.google.android.gms.internal.measurement.zzml$zza r6 = new com.google.android.gms.internal.measurement.zzml$zza
            r6.<init>(r5)
            goto L39
        L38:
            r6 = 0
        L39:
            com.google.android.gms.internal.measurement.zzml.zzf = r6
            r5 = 0
            if (r6 != 0) goto L40
            r7 = r5
            goto L44
        L40:
            boolean r7 = r6.zzb()
        L44:
            com.google.android.gms.internal.measurement.zzml.zzg = r7
            if (r6 != 0) goto L4a
            r7 = r5
            goto L4e
        L4a:
            boolean r7 = r6.zza()
        L4e:
            com.google.android.gms.internal.measurement.zzml.zzh = r7
            java.lang.Class<byte[]> r7 = byte[].class
            int r7 = zzb(r7)
            long r7 = (long) r7
            com.google.android.gms.internal.measurement.zzml.zzi = r7
            zzb(r4)
            zzc(r4)
            zzb(r3)
            zzc(r3)
            zzb(r2)
            zzc(r2)
            zzb(r1)
            zzc(r1)
            zzb(r0)
            zzc(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzb(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzc(r0)
            java.lang.reflect.Field r0 = zze()
            if (r0 == 0) goto L8f
            if (r6 != 0) goto L8a
            goto L8f
        L8a:
            sun.misc.Unsafe r1 = r6.zza
            r1.objectFieldOffset(r0)
        L8f:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L98
            r5 = 1
        L98:
            com.google.android.gms.internal.measurement.zzml.zza = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzml.<clinit>():void");
    }

    private zzml() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(Object obj, long j2, boolean z) {
        zzf.zza(obj, j2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(byte[] bArr, long j2, byte b2) {
        zzf.zza((Object) bArr, zzi + j2, b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int zzc2 = zzc(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        zza(obj, j3, ((255 & b2) << i2) | (zzc2 & (~(255 << i2))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        zza(obj, j3, ((255 & b2) << i2) | (zzc(obj, j3) & (~(255 << i2))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j2, double d2) {
        zzf.zza(obj, j2, d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j2, float f2) {
        zzf.zza(obj, j2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j2, int i2) {
        zzf.zza(obj, j2, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j2, long j3) {
        zzf.zza(obj, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j2, Object obj2) {
        zzf.zza.putObject(obj, j2, obj2);
    }

    static /* synthetic */ boolean zzf(Object obj, long j2) {
        return ((byte) (zzc(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3)))) != 0;
    }

    static /* synthetic */ boolean zzg(Object obj, long j2) {
        return ((byte) (zzc(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3)))) != 0;
    }

    private static boolean zzd(Class<?> cls) {
        try {
            Class<?> cls2 = zzc;
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
    public static boolean zzh(Object obj, long j2) {
        return zzf.zzc(obj, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc() {
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd() {
        return zzg;
    }
}
