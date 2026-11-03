package com.google.android.gms.games.internal;

import com.google.android.gms.games.GamesStatusUtils;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzn extends zza {
    private final TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.games.internal.zza, com.google.android.gms.games.internal.zzaj
    public final void zzd(int i2, String str) {
        if (i2 == 0 || i2 == 3003) {
            this.zza.setResult(Boolean.valueOf(i2 == 3003));
        } else {
            GamesStatusUtils.zzb(this.zza, i2);
        }
    }
}
