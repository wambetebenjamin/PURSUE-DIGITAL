package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.api.internal.LifecycleFragment;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public abstract class zag implements DialogInterface.OnClickListener {
    public static zag zab(Activity activity, Intent intent, int i2) {
        return new zad(intent, activity, i2);
    }

    public static zag zac(Fragment fragment, Intent intent, int i2) {
        return new zae(intent, fragment, i2);
    }

    public static zag zad(LifecycleFragment lifecycleFragment, Intent intent, int i2) {
        return new zaf(intent, lifecycleFragment, 2);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        try {
            try {
                zaa();
            } catch (ActivityNotFoundException e2) {
                Log.e("DialogRedirect", true == Build.FINGERPRINT.contains("generic") ? "Failed to start resolution intent. This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store." : "Failed to start resolution intent.", e2);
            }
        } finally {
            dialogInterface.dismiss();
        }
    }

    protected abstract void zaa();
}
