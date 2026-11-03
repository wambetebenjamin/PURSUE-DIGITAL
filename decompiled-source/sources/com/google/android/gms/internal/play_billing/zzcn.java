package com.google.android.gms.internal.play_billing;

import com.bytedance.bdturing.utils.b;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import com.google.android.gms.internal.play_billing.zzcm;
import com.google.android.gms.internal.play_billing.zzcn;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzcn<V> extends zzdi implements zzdc<V> {
    static final Object zza = new Object();
    static final zzdb zzb = new zzdb(zzcm.class);
    static final boolean zzc;
    private static final zza zzd;
    volatile zzcm.zzd listenersField;
    volatile Object valueField;
    volatile zze waitersField;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public abstract class zza {
        /* synthetic */ zza(zzcr zzcrVar) {
        }

        abstract zzcm.zzd zza(zzcn zzcnVar, zzcm.zzd zzdVar);

        abstract zze zzb(zzcn zzcnVar, zze zzeVar);

        abstract void zzc(zze zzeVar, zze zzeVar2);

        abstract void zzd(zze zzeVar, Thread thread);

        abstract boolean zze(zzcn zzcnVar, zzcm.zzd zzdVar, zzcm.zzd zzdVar2);

        abstract boolean zzf(zzcn zzcnVar, Object obj, Object obj2);

        abstract boolean zzg(zzcn zzcnVar, zze zzeVar, zze zzeVar2);
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    final class zzb extends zza {
        private static final AtomicReferenceFieldUpdater<zze, Thread> zza = AtomicReferenceFieldUpdater.newUpdater(zze.class, Thread.class, "thread");
        private static final AtomicReferenceFieldUpdater<zze, zze> zzb = AtomicReferenceFieldUpdater.newUpdater(zze.class, zze.class, Constants.BUTTON_NEXT);
        private static final AtomicReferenceFieldUpdater<? super zzcn<?>, zze> zzc = AtomicReferenceFieldUpdater.newUpdater(zzcn.class, zze.class, "waitersField");
        private static final AtomicReferenceFieldUpdater<? super zzcn<?>, zzcm.zzd> zzd = AtomicReferenceFieldUpdater.newUpdater(zzcn.class, zzcm.zzd.class, "listenersField");
        private static final AtomicReferenceFieldUpdater<? super zzcn<?>, Object> zze = AtomicReferenceFieldUpdater.newUpdater(zzcn.class, Object.class, "valueField");

        private zzb() {
            throw null;
        }

        /* synthetic */ zzb(zzcr zzcrVar) {
            super(null);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final zzcm.zzd zza(zzcn zzcnVar, zzcm.zzd zzdVar) {
            return zzd.getAndSet(zzcnVar, zzdVar);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final zze zzb(zzcn zzcnVar, zze zzeVar) {
            return zzc.getAndSet(zzcnVar, zzeVar);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final void zzc(zze zzeVar, zze zzeVar2) {
            zzb.lazySet(zzeVar, zzeVar2);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final void zzd(zze zzeVar, Thread thread) {
            zza.lazySet(zzeVar, thread);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zze(zzcn zzcnVar, zzcm.zzd zzdVar, zzcm.zzd zzdVar2) {
            return zzco.zza(zzd, zzcnVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zzf(zzcn zzcnVar, Object obj, Object obj2) {
            return zzco.zza(zze, zzcnVar, obj, obj2);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zzg(zzcn zzcnVar, zze zzeVar, zze zzeVar2) {
            return zzco.zza(zzc, zzcnVar, zzeVar, zzeVar2);
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    final class zzc extends zza {
        private zzc() {
            throw null;
        }

        /* synthetic */ zzc(zzcr zzcrVar) {
            super(null);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final zzcm.zzd zza(zzcn zzcnVar, zzcm.zzd zzdVar) {
            zzcm.zzd zzdVar2;
            synchronized (zzcnVar) {
                zzdVar2 = zzcnVar.listenersField;
                if (zzdVar2 != zzdVar) {
                    zzcnVar.listenersField = zzdVar;
                }
            }
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final zze zzb(zzcn zzcnVar, zze zzeVar) {
            zze zzeVar2;
            synchronized (zzcnVar) {
                zzeVar2 = zzcnVar.waitersField;
                if (zzeVar2 != zzeVar) {
                    zzcnVar.waitersField = zzeVar;
                }
            }
            return zzeVar2;
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final void zzc(zze zzeVar, zze zzeVar2) {
            zzeVar.next = zzeVar2;
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final void zzd(zze zzeVar, Thread thread) {
            zzeVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zze(zzcn zzcnVar, zzcm.zzd zzdVar, zzcm.zzd zzdVar2) {
            synchronized (zzcnVar) {
                if (zzcnVar.listenersField == zzdVar) {
                    zzcnVar.listenersField = zzdVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zzf(zzcn zzcnVar, Object obj, Object obj2) {
            synchronized (zzcnVar) {
                if (zzcnVar.valueField == obj) {
                    zzcnVar.valueField = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zzg(zzcn zzcnVar, zze zzeVar, zze zzeVar2) {
            synchronized (zzcnVar) {
                if (zzcnVar.waitersField == zzeVar) {
                    zzcnVar.waitersField = zzeVar2;
                    return true;
                }
                return false;
            }
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    final class zzd extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;
        public static final /* synthetic */ int zzg = 0;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: com.google.android.gms.internal.play_billing.zzcq
                    @Override // java.security.PrivilegedExceptionAction
                    public final Object run() {
                        Field[] declaredFields;
                        int i2 = zzcn.zzd.zzg;
                        for (Field field : Unsafe.class.getDeclaredFields()) {
                            field.setAccessible(true);
                            Object obj = field.get(null);
                            if (Unsafe.class.isInstance(obj)) {
                                return (Unsafe) Unsafe.class.cast(obj);
                            }
                        }
                        throw new NoSuchFieldError("the Unsafe");
                    }
                });
            }
            try {
                zzc = unsafe.objectFieldOffset(zzcn.class.getDeclaredField("waitersField"));
                zzb = unsafe.objectFieldOffset(zzcn.class.getDeclaredField("listenersField"));
                zzd = unsafe.objectFieldOffset(zzcn.class.getDeclaredField("valueField"));
                zze = unsafe.objectFieldOffset(zze.class.getDeclaredField("thread"));
                zzf = unsafe.objectFieldOffset(zze.class.getDeclaredField(Constants.BUTTON_NEXT));
                zza = unsafe;
            } catch (NoSuchFieldException e3) {
                throw new RuntimeException(e3);
            }
        }

        private zzd() {
            throw null;
        }

        /* synthetic */ zzd(zzcr zzcrVar) {
            super(null);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final zzcm.zzd zza(zzcn zzcnVar, zzcm.zzd zzdVar) {
            zzcm.zzd zzdVar2;
            do {
                zzdVar2 = zzcnVar.listenersField;
                if (zzdVar == zzdVar2) {
                    break;
                }
            } while (!zze(zzcnVar, zzdVar2, zzdVar));
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final zze zzb(zzcn zzcnVar, zze zzeVar) {
            zze zzeVar2;
            do {
                zzeVar2 = zzcnVar.waitersField;
                if (zzeVar == zzeVar2) {
                    break;
                }
            } while (!zzg(zzcnVar, zzeVar2, zzeVar));
            return zzeVar2;
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final void zzc(zze zzeVar, zze zzeVar2) {
            zza.putObject(zzeVar, zzf, zzeVar2);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final void zzd(zze zzeVar, Thread thread) {
            zza.putObject(zzeVar, zze, thread);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zze(zzcn zzcnVar, zzcm.zzd zzdVar, zzcm.zzd zzdVar2) {
            return zzcp.zza(zza, zzcnVar, zzb, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zzf(zzcn zzcnVar, Object obj, Object obj2) {
            return zzcp.zza(zza, zzcnVar, zzd, obj, obj2);
        }

        @Override // com.google.android.gms.internal.play_billing.zzcn.zza
        final boolean zzg(zzcn zzcnVar, zze zzeVar, zze zzeVar2) {
            return zzcp.zza(zza, zzcnVar, zzc, zzeVar, zzeVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public final class zze {
        static final zze zza = new zze(false);
        volatile zze next;
        volatile Thread thread;

        zze() {
            zzcn.zzn(this, Thread.currentThread());
        }

        zze(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zzcVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        zzc = z;
        String property = System.getProperty("java.runtime.name", "");
        if (property != null && !property.contains(b.f4370d)) {
            try {
                zzcVar = new zzb(null);
            } catch (NoClassDefFoundError unused2) {
                zzcVar = new zzc(null);
            }
        } else {
            try {
                zzcVar = new zzd(null);
            } catch (Error | Exception e2) {
                try {
                    th = e2;
                    th2 = null;
                    zzcVar = new zzb(null);
                } catch (Error | Exception e3) {
                    th = e2;
                    th2 = e3;
                    zzcVar = new zzc(null);
                }
            }
        }
        th2 = null;
        th = null;
        zzd = zzcVar;
        if (th2 != null) {
            zzdb zzdbVar = zzb;
            zzdbVar.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "UnsafeAtomicHelper is broken!", th);
            zzdbVar.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "AtomicReferenceFieldUpdaterAtomicHelper is broken!", th2);
        }
    }

    private final void zza(zze zzeVar) {
        zzeVar.thread = null;
        while (true) {
            zze zzeVar2 = this.waitersField;
            if (zzeVar2 != zze.zza) {
                zze zzeVar3 = null;
                while (zzeVar2 != null) {
                    zze zzeVar4 = zzeVar2.next;
                    if (zzeVar2.thread != null) {
                        zzeVar3 = zzeVar2;
                    } else if (zzeVar3 != null) {
                        zzeVar3.next = zzeVar4;
                        if (zzeVar3.thread == null) {
                            break;
                        }
                    } else if (!zzd.zzg(this, zzeVar2, zzeVar4)) {
                        break;
                    }
                    zzeVar2 = zzeVar4;
                }
                return;
            }
            return;
        }
    }

    static /* synthetic */ void zzn(zze zzeVar, Thread thread) {
        zzd.zzd(zzeVar, thread);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzq(zzcn zzcnVar, Object obj, Object obj2) {
        return zzd.zzf(zzcnVar, obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzcm.zzd zzk(zzcm.zzd zzdVar) {
        return zzd.zza(this, zzdVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzl() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.valueField;
            if ((obj2 != null) && zzcm.zzh(obj2)) {
                return zzcm.zzc(obj2);
            }
            zze zzeVar = this.waitersField;
            if (zzeVar != zze.zza) {
                zze zzeVar2 = new zze();
                do {
                    zza zzaVar = zzd;
                    zzaVar.zzc(zzeVar2, zzeVar);
                    if (zzaVar.zzg(this, zzeVar, zzeVar2)) {
                        do {
                            LockSupport.park(this);
                            if (Thread.interrupted()) {
                                zza(zzeVar2);
                                throw new InterruptedException();
                            }
                            obj = this.valueField;
                        } while (!((obj != null) & zzcm.zzh(obj)));
                        return zzcm.zzc(obj);
                    }
                    zzeVar = this.waitersField;
                } while (zzeVar != zze.zza);
                return zzcm.zzc(Objects.requireNonNull(this.valueField));
            }
            return zzcm.zzc(Objects.requireNonNull(this.valueField));
        }
        throw new InterruptedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object zzm(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.valueField;
            boolean z = true;
            if ((obj != null) & zzcm.zzh(obj)) {
                return zzcm.zzc(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                zze zzeVar = this.waitersField;
                if (zzeVar != zze.zza) {
                    zze zzeVar2 = new zze();
                    do {
                        zza zzaVar = zzd;
                        zzaVar.zzc(zzeVar2, zzeVar);
                        if (zzaVar.zzg(this, zzeVar, zzeVar2)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (Thread.interrupted()) {
                                    zza(zzeVar2);
                                    throw new InterruptedException();
                                }
                                Object obj2 = this.valueField;
                                if (!((obj2 != null) & zzcm.zzh(obj2))) {
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    return zzcm.zzc(obj2);
                                }
                            } while (nanos >= 1000);
                            zza(zzeVar2);
                        } else {
                            zzeVar = this.waitersField;
                        }
                    } while (zzeVar != zze.zza);
                    return zzcm.zzc(Objects.requireNonNull(this.valueField));
                }
                return zzcm.zzc(Objects.requireNonNull(this.valueField));
            }
            while (nanos > 0) {
                Object obj3 = this.valueField;
                if (!((obj3 != null) & zzcm.zzh(obj3))) {
                    if (!Thread.interrupted()) {
                        nanos = nanoTime - System.nanoTime();
                    } else {
                        throw new InterruptedException();
                    }
                } else {
                    return zzcm.zzc(obj3);
                }
            }
            String obj4 = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(Locale.ROOT);
            if (nanos + 1000 < 0) {
                String concat = str.concat(" (plus ");
                long j3 = -nanos;
                long convert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit.toNanos(convert);
                int i2 = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
                if (i2 != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (i2 > 0) {
                    String str2 = concat + convert + " " + lowerCase;
                    if (z) {
                        str2 = str2.concat(",");
                    }
                    concat = str2.concat(" ");
                }
                if (z) {
                    concat = concat + nanos2 + " nanoseconds ";
                }
                str = concat.concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(str.concat(" but future completed as timeout expired"));
            }
            throw new TimeoutException(str + " for " + obj4);
        }
        throw new InterruptedException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo() {
        for (zze zzb2 = zzd.zzb(this, zze.zza); zzb2 != null; zzb2 = zzb2.next) {
            Thread thread = zzb2.thread;
            if (thread != null) {
                zzb2.thread = null;
                LockSupport.unpark(thread);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(zzcm.zzd zzdVar, zzcm.zzd zzdVar2) {
        return zzd.zze(this, zzdVar, zzdVar2);
    }
}
