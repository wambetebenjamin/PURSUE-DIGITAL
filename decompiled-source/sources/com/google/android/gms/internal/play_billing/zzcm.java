package com.google.android.gms.internal.play_billing;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes5.dex */
public abstract class zzcm<V> extends zzcn<V> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public final class zza {
        static final zza zza;
        static final zza zzb;
        final boolean zzc;
        final Throwable zzd;

        static {
            if (zzcn.zzc) {
                zzb = null;
                zza = null;
                return;
            }
            zzb = new zza(false, null);
            zza = new zza(true, null);
        }

        zza(boolean z, Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public final class zzb<V> implements Runnable {
        final zzcm<V> zza;
        final zzdc<? extends V> zzb;

        zzb(zzcm zzcmVar, zzdc zzdcVar) {
            this.zza = zzcmVar;
            this.zzb = zzdcVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.zza.valueField != this) {
                return;
            }
            if (zzcn.zzq(this.zza, this, zzcm.zzr(this.zzb))) {
                zzcm.zzu(this.zza, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.play_billing.zzcm.zzc.1
            {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            Objects.requireNonNull(th);
            this.zzb = th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public final class zzd {
        static final zzd zza = new zzd();
        zzd next;
        final Runnable zzb;
        final Executor zzc;

        zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes5.dex */
    public interface zze<V> extends zzdc<V> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzc(Object obj) throws ExecutionException {
        if (obj instanceof zza) {
            Throwable th = ((zza) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (!(obj instanceof zzc)) {
            if (obj == zza) {
                return null;
            }
            return obj;
        } else {
            throw new ExecutionException(((zzc) obj).zzb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzh(Object obj) {
        return !(obj instanceof zzb);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object zzr(zzdc zzdcVar) {
        Throwable zze2;
        if (zzdcVar instanceof zze) {
            Object obj = ((zzcm) zzdcVar).valueField;
            if (obj instanceof zza) {
                zza zzaVar = (zza) obj;
                if (zzaVar.zzc) {
                    Throwable th = zzaVar.zzd;
                    if (th != null) {
                        obj = new zza(false, th);
                    } else {
                        obj = zza.zzb;
                    }
                }
            }
            return Objects.requireNonNull(obj);
        } else if ((zzdcVar instanceof zzdi) && (zze2 = ((zzdi) zzdcVar).zze()) != null) {
            return new zzc(zze2);
        } else {
            boolean isCancelled = zzdcVar.isCancelled();
            if ((!zzc) & isCancelled) {
                return Objects.requireNonNull(zza.zzb);
            }
            try {
                Object zzs = zzs(zzdcVar);
                if (!isCancelled) {
                    return zzs == null ? zza : zzs;
                }
                String valueOf = String.valueOf(zzdcVar);
                return new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + valueOf));
            } catch (Error e2) {
                e = e2;
                return new zzc(e);
            } catch (CancellationException e3) {
                if (!isCancelled) {
                    return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(String.valueOf(zzdcVar))), e3));
                }
                return new zza(false, e3);
            } catch (ExecutionException e4) {
                if (isCancelled) {
                    return new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(String.valueOf(zzdcVar))), e4));
                }
                return new zzc(e4.getCause());
            } catch (Exception e5) {
                e = e5;
                return new zzc(e);
            }
        }
    }

    private static Object zzs(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzt(StringBuilder sb) {
        try {
            Object zzs = zzs(this);
            sb.append("SUCCESS, result=[");
            if (zzs == null) {
                sb.append("null");
            } else if (zzs == this) {
                sb.append("this future");
            } else {
                sb.append(zzs.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzs)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        } catch (Exception e3) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e3.getClass());
            sb.append(" thrown from get()]");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzu(zzcm zzcmVar, boolean z) {
        zzd zzdVar;
        zzd zzdVar2 = null;
        while (true) {
            zzcmVar.zzo();
            zzcmVar.zzg();
            zzd zzdVar3 = zzdVar2;
            zzd zzk = zzcmVar.zzk(zzd.zza);
            zzd zzdVar4 = zzdVar3;
            while (zzk != null) {
                zzd zzdVar5 = zzk.next;
                zzk.next = zzdVar4;
                zzdVar4 = zzk;
                zzk = zzdVar5;
            }
            while (zzdVar4 != null) {
                Runnable runnable = zzdVar4.zzb;
                zzdVar = zzdVar4.next;
                Runnable runnable2 = (Runnable) Objects.requireNonNull(runnable);
                if (runnable2 instanceof zzb) {
                    zzb zzbVar = (zzb) runnable2;
                    zzcmVar = zzbVar.zza;
                    if (zzcmVar.valueField == zzbVar && zzq(zzcmVar, zzbVar, zzr(zzbVar.zzb))) {
                        break;
                    }
                } else {
                    zzv(runnable2, (Executor) Objects.requireNonNull(zzdVar4.zzc));
                }
                zzdVar4 = zzdVar;
            }
            return;
            zzdVar2 = zzdVar;
        }
    }

    private static void zzv(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e2) {
            Logger zza2 = zzb.zza();
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            zza2.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, (Throwable) e2);
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        zza zzaVar;
        Object requireNonNull;
        Object obj = this.valueField;
        if ((obj instanceof zzb) || (obj == null)) {
            if (zzc) {
                requireNonNull = new zza(z, new CancellationException("Future.cancel() was called."));
            } else {
                if (z) {
                    zzaVar = zza.zza;
                } else {
                    zzaVar = zza.zzb;
                }
                requireNonNull = Objects.requireNonNull(zzaVar);
            }
            zzcm<V> zzcmVar = this;
            boolean z2 = false;
            while (true) {
                if (zzq(zzcmVar, obj, requireNonNull)) {
                    zzu(zzcmVar, z);
                    if (!(obj instanceof zzb)) {
                        break;
                    }
                    zzdc<? extends V> zzdcVar = ((zzb) obj).zzb;
                    if (zzdcVar instanceof zze) {
                        zzcmVar = (zzcm) zzdcVar;
                        obj = zzcmVar.valueField;
                        if (!(obj == null) && !(obj instanceof zzb)) {
                            break;
                        }
                        z2 = true;
                    } else {
                        zzdcVar.cancel(z);
                        break;
                    }
                } else {
                    obj = zzcmVar.valueField;
                    if (zzh(obj)) {
                        return z2;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException, ExecutionException {
        return zzl();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.valueField instanceof zza;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.valueField;
        return (obj != null) & zzh(obj);
    }

    public final String toString() {
        String concat;
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.valueField instanceof zza) {
            sb.append("CANCELLED");
        } else if (!isDone()) {
            int length = sb.length();
            sb.append("PENDING");
            Object obj = this.valueField;
            if (obj instanceof zzb) {
                sb.append(", setFuture=[");
                zzdc<? extends V> zzdcVar = ((zzb) obj).zzb;
                try {
                    if (zzdcVar == this) {
                        sb.append("this future");
                    } else {
                        sb.append(zzdcVar);
                    }
                } catch (Throwable th) {
                    zzdd.zza(th);
                    sb.append("Exception thrown from implementation: ");
                    sb.append(th.getClass());
                }
                sb.append("]");
            } else {
                try {
                    concat = zzbm.zza(zzd());
                } catch (Throwable th2) {
                    zzdd.zza(th2);
                    concat = "Exception thrown from implementation: ".concat(String.valueOf(String.valueOf(th2.getClass())));
                }
                if (concat != null) {
                    sb.append(", info=[");
                    sb.append(concat);
                    sb.append("]");
                }
            }
            if (isDone()) {
                sb.delete(length, sb.length());
                zzt(sb);
            }
        } else {
            zzt(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdc
    public final void zzb(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzbj.zzc(executor, "Executor was null.");
        if (isDone() || (zzdVar = this.listenersField) == zzd.zza) {
            zzv(runnable, executor);
        }
        zzd zzdVar2 = new zzd(runnable, executor);
        do {
            zzdVar2.next = zzdVar;
            if (zzp(zzdVar, zzdVar2)) {
                return;
            }
            zzdVar = this.listenersField;
        } while (zzdVar != zzd.zza);
        zzv(runnable, executor);
    }

    protected String zzd() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.play_billing.zzdi
    public final Throwable zze() {
        if (this instanceof zze) {
            Object obj = this.valueField;
            if (obj instanceof zzc) {
                return ((zzc) obj).zzb;
            }
            return null;
        }
        return null;
    }

    protected void zzg() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzi(Throwable th) {
        if (zzq(this, null, new zzc(th))) {
            zzu(this, false);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzj(zzdc zzdcVar) {
        zzc zzcVar;
        Object obj = this.valueField;
        if (obj == null) {
            if (zzdcVar.isDone()) {
                if (zzq(this, null, zzr(zzdcVar))) {
                    zzu(this, false);
                    return true;
                }
                return false;
            }
            zzb zzbVar = new zzb(this, zzdcVar);
            if (zzq(this, null, zzbVar)) {
                try {
                    zzdcVar.zzb(zzbVar, zzcs.INSTANCE);
                } catch (Throwable th) {
                    try {
                        zzcVar = new zzc(th);
                    } catch (Error | Exception unused) {
                        zzcVar = zzc.zza;
                    }
                    zzq(this, zzbVar, zzcVar);
                }
                return true;
            }
            obj = this.valueField;
        }
        if (obj instanceof zza) {
            zzdcVar.cancel(((zza) obj).zzc);
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        return zzm(j2, timeUnit);
    }
}
