package com.google.android.gms.internal.play_billing;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public class zzo implements zzdc {
    static final zzd zzb;
    public static final /* synthetic */ int zzf = 0;
    private static final Object zzh;
    volatile Object zzc;
    volatile zzh zzd;
    volatile zzm zze;
    static final boolean zza = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Logger zzg = Logger.getLogger(zzo.class.getName());

    static {
        zzd zzlVar;
        try {
            zzlVar = new zzj(AtomicReferenceFieldUpdater.newUpdater(zzm.class, Thread.class, "zzb"), AtomicReferenceFieldUpdater.newUpdater(zzm.class, zzm.class, "zzc"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, zzm.class, "zze"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, zzh.class, "zzd"), AtomicReferenceFieldUpdater.newUpdater(zzo.class, Object.class, "zzc"));
            th = null;
        } catch (Throwable th) {
            th = th;
            zzlVar = new zzl();
        }
        Throwable th2 = th;
        zzb = zzlVar;
        if (th2 != null) {
            zzg.logp(Level.SEVERE, "com.android.billingclient.util.concurrent.AbstractResolvableFuture", "<clinit>", "SafeAtomicHelper is broken!", th2);
        }
        zzh = new Object();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(zzo zzoVar) {
        zzm zzmVar;
        zzd zzdVar;
        zzh zzhVar;
        do {
            zzmVar = zzoVar.zze;
            zzdVar = zzb;
        } while (!zzdVar.zze(zzoVar, zzmVar, zzm.zza));
        while (zzmVar != null) {
            Thread thread = zzmVar.zzb;
            if (thread != null) {
                zzmVar.zzb = null;
                LockSupport.unpark(thread);
            }
            zzmVar = zzmVar.zzc;
        }
        do {
            zzhVar = zzoVar.zzd;
        } while (!zzdVar.zzc(zzoVar, zzhVar, zzh.zza));
        zzh zzhVar2 = null;
        while (zzhVar != null) {
            zzh zzhVar3 = zzhVar.zzd;
            zzhVar.zzd = zzhVar2;
            zzhVar2 = zzhVar;
            zzhVar = zzhVar3;
        }
        while (zzhVar2 != null) {
            Runnable runnable = zzhVar2.zzb;
            zzh zzhVar4 = zzhVar2.zzd;
            if (!(runnable instanceof zzk)) {
                zzf(runnable, zzhVar2.zzc);
                zzhVar2 = zzhVar4;
            } else {
                zzo zzoVar2 = ((zzk) runnable).zza;
                throw null;
            }
        }
    }

    private final void zze(StringBuilder sb) {
        V v;
        boolean z = false;
        while (true) {
            try {
                try {
                    v = get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            } catch (CancellationException unused2) {
                sb.append("CANCELLED");
                return;
            } catch (RuntimeException e2) {
                sb.append("UNKNOWN, cause=[");
                sb.append(e2.getClass());
                sb.append(" thrown from get()]");
                return;
            } catch (ExecutionException e3) {
                sb.append("FAILURE, cause=[");
                sb.append(e3.getCause());
                sb.append("]");
                return;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        sb.append("SUCCESS, result=[");
        sb.append(v == this ? "this future" : String.valueOf(v));
        sb.append("]");
    }

    private static void zzf(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            Logger logger = zzg;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            logger.logp(level, "com.android.billingclient.util.concurrent.AbstractResolvableFuture", "executeListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, (Throwable) e2);
        }
    }

    private final void zzg(zzm zzmVar) {
        zzmVar.zzb = null;
        while (true) {
            zzm zzmVar2 = this.zze;
            if (zzmVar2 != zzm.zza) {
                zzm zzmVar3 = null;
                while (zzmVar2 != null) {
                    zzm zzmVar4 = zzmVar2.zzc;
                    if (zzmVar2.zzb != null) {
                        zzmVar3 = zzmVar2;
                    } else if (zzmVar3 != null) {
                        zzmVar3.zzc = zzmVar4;
                        if (zzmVar3.zzb == null) {
                            break;
                        }
                    } else if (!zzb.zze(this, zzmVar2, zzmVar4)) {
                        break;
                    }
                    zzmVar2 = zzmVar4;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzh(Object obj) throws ExecutionException {
        if (obj instanceof zze) {
            Throwable th = ((zze) obj).zzc;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzg) {
            throw new ExecutionException(((zzg) obj).zza);
        } else {
            if (obj == zzh) {
                return null;
            }
            return obj;
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        zze zzeVar;
        Object obj = this.zzc;
        if (!(obj instanceof zzk) && !(obj == null)) {
            return false;
        }
        if (zza) {
            zzeVar = new zze(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            zzeVar = zze.zza;
        } else {
            zzeVar = zze.zzb;
        }
        while (!zzb.zzd(this, obj, zzeVar)) {
            obj = this.zzc;
            if (!(obj instanceof zzk)) {
                return false;
            }
        }
        zzc(this);
        if (obj instanceof zzk) {
            zzdc zzdcVar = ((zzk) obj).zzb;
            throw null;
        }
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.zzc;
            if ((obj2 != null) && (!(obj2 instanceof zzk))) {
                return zzh(obj2);
            }
            zzm zzmVar = this.zze;
            zzm zzmVar2 = zzm.zza;
            if (zzmVar != zzmVar2) {
                zzm zzmVar3 = new zzm();
                do {
                    zzd zzdVar = zzb;
                    zzdVar.zza(zzmVar3, zzmVar);
                    if (zzdVar.zze(this, zzmVar, zzmVar3)) {
                        do {
                            LockSupport.park(this);
                            if (Thread.interrupted()) {
                                zzg(zzmVar3);
                                throw new InterruptedException();
                            }
                            obj = this.zzc;
                        } while (!((obj != null) & (!(obj instanceof zzk))));
                        return zzh(obj);
                    }
                    zzmVar = this.zze;
                } while (zzmVar != zzmVar2);
                return zzh(this.zzc);
            }
            return zzh(this.zzc);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzc instanceof zze;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.zzc;
        return (obj != null) & (!(obj instanceof zzk));
    }

    public final String toString() {
        String concat;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.zzc instanceof zze) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zze(sb);
        } else {
            try {
                concat = zza();
            } catch (RuntimeException e2) {
                concat = "Exception thrown from implementation: ".concat(String.valueOf(String.valueOf(e2.getClass())));
            }
            if (concat != null && !concat.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(concat);
                sb.append("]");
            } else if (isDone()) {
                zze(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    protected String zza() {
        Object obj = this.zzc;
        if (obj instanceof zzk) {
            zzdc zzdcVar = ((zzk) obj).zzb;
            return "setFuture=[null]";
        } else if (this instanceof ScheduledFuture) {
            long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
            return "remaining delay=[" + delay + " ms]";
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean zzd(Object obj) {
        if (obj == null) {
            obj = zzh;
        }
        if (zzb.zzd(this, null, obj)) {
            zzc(this);
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final void zzb(Runnable runnable, Executor executor) {
        Objects.requireNonNull(executor);
        zzh zzhVar = this.zzd;
        zzh zzhVar2 = zzh.zza;
        if (zzhVar != zzhVar2) {
            zzh zzhVar3 = new zzh(runnable, executor);
            do {
                zzhVar3.zzd = zzhVar;
                if (zzb.zzc(this, zzhVar, zzhVar3)) {
                    return;
                }
                zzhVar = this.zzd;
            } while (zzhVar != zzhVar2);
            zzf(runnable, executor);
        }
        zzf(runnable, executor);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x00a6 -> B:56:0x00ac). Please submit an issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object get(long r19, java.util.concurrent.TimeUnit r21) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException, java.util.concurrent.ExecutionException {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzo.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }
}
