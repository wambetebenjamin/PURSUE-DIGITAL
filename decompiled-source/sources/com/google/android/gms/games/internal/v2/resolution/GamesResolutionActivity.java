package com.google.android.gms.games.internal.v2.resolution;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.bytedance.sdk.account.platform.base.i;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.games_v2.zzfu;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
public final class GamesResolutionActivity extends Activity {
    private ResultReceiver zza;
    private boolean zzb;

    private final void zza(int i2, Intent intent) {
        Bundle bundle;
        if (this.zzb) {
            return;
        }
        this.zzb = true;
        ResultReceiver resultReceiver = this.zza;
        if (resultReceiver != null) {
            if (intent == null) {
                bundle = new Bundle();
            } else {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable(i.CU_KEY_RESULT_DATA, intent);
                bundle = bundle2;
            }
            resultReceiver.send(i2, bundle);
        }
    }

    @Override // android.app.Activity
    protected final void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(i2).length() + 25);
            sb.append("Unexpected request code: ");
            sb.append(i2);
            zzfu.zzg("ResultActivity", sb.toString());
            zza(0, intent);
            finish();
            return;
        }
        zza(i3, intent);
        finish();
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zza = (ResultReceiver) Preconditions.checkNotNull((ResultReceiver) bundle.getParcelable("resultReceiver"));
            return;
        }
        this.zza = (ResultReceiver) Preconditions.checkNotNull((ResultReceiver) getIntent().getParcelableExtra("resultReceiver"));
        try {
            startIntentSenderForResult(((PendingIntent) Preconditions.checkNotNull((PendingIntent) getIntent().getParcelableExtra(BaseGmsClient.KEY_PENDING_INTENT))).getIntentSender(), 0, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e2) {
            zzfu.zzh("ResultActivity", "Failed to launch", e2);
            zza(0, null);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        if (!isChangingConfigurations()) {
            zza(0, null);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("resultReceiver", this.zza);
    }
}
