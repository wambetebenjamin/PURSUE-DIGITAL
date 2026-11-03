package com.google.android.gms.games.internal;

import com.google.android.gms.games.GamesStatusUtils;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzr extends zza {
    private final TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzaj
    public final void zzp(int i2, String str) {
        if (i2 == 0) {
            this.zza.setResult(str);
        } else {
            GamesStatusUtils.zzb(this.zza, i2);
        }
    }
}
