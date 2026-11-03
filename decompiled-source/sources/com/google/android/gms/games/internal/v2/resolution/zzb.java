package com.google.android.gms.games.internal.v2.resolution;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class zzb {
    zzb() {
    }

    public static zzb zza() {
        return new zzb();
    }

    public static final Task zzb(Activity activity, PendingIntent pendingIntent) {
        zza zzaVar = new zza();
        Intent intent = new Intent(activity, GamesResolutionActivity.class);
        intent.putExtra(BaseGmsClient.KEY_PENDING_INTENT, pendingIntent);
        intent.putExtra("resultReceiver", zzaVar);
        activity.startActivity(intent);
        return zzaVar.zza();
    }
}
