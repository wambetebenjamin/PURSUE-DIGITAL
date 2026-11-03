package com.bytedance.applog.params;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¨\u0006\u0005"}, d2 = {"mainThreadRun", "", "f", "Lkotlin/Function0;", "noMainThreadRun", "params_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class f {
    public static final void a(final Function0<Unit> f2) {
        Intrinsics.checkNotNullParameter(f2, "f");
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.bytedance.applog.params.f$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    f.c(Function0.this);
                }
            });
        } else {
            f2.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(Function0 f2) {
        Intrinsics.checkNotNullParameter(f2, "$f");
        f2.invoke();
    }

    public static final void b(final Function0<Unit> f2) {
        Intrinsics.checkNotNullParameter(f2, "f");
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.applog.params.f$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    f.d(Function0.this);
                }
            });
        } else {
            f2.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(Function0 f2) {
        Intrinsics.checkNotNullParameter(f2, "$f");
        f2.invoke();
    }
}
