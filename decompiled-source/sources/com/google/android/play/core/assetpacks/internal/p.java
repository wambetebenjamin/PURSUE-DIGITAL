package com.google.android.play.core.assetpacks.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final TaskCompletionSource f16828a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p() {
        this.f16828a = null;
    }

    public p(TaskCompletionSource taskCompletionSource) {
        this.f16828a = taskCompletionSource;
    }

    protected abstract void a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TaskCompletionSource b() {
        return this.f16828a;
    }

    public final void c(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.f16828a;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Exception e2) {
            c(e2);
        }
    }
}
