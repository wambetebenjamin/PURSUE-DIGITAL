package com.google.android.gms.games.internal.v2.resolution;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.ResultReceiver;
import com.bytedance.sdk.account.platform.base.i;
import com.google.android.gms.internal.games_v2.zzfy;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zza extends ResultReceiver {
    private final TaskCompletionSource zza;

    public zza() {
        super(new zzfy(Looper.getMainLooper()));
        this.zza = new TaskCompletionSource();
    }

    @Override // android.os.ResultReceiver
    protected final void onReceiveResult(int i2, Bundle bundle) {
        Intent intent;
        super.onReceiveResult(i2, bundle);
        Boolean valueOf = Boolean.valueOf(i2 == -1);
        if (bundle == null) {
            intent = new Intent();
        } else {
            intent = (Intent) bundle.getParcelable(i.CU_KEY_RESULT_DATA);
            if (intent == null) {
                intent = new Intent();
            }
        }
        this.zza.trySetResult(valueOf.booleanValue() ? zzc.zza(intent) : zzc.zzb(intent));
    }

    public final Task zza() {
        return this.zza.getTask();
    }
}
