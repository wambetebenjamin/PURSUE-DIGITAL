package com.google.android.gms.internal.games_v2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Objects;
/* compiled from: com.google.android.gms:play-services-games-v2@@22.0.0 */
/* loaded from: classes5.dex */
final class zzat {
    static final zzat zza = new zzat();
    private boolean zzb;
    private boolean zzc;

    zzat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Activity activity) {
        Bundle bundle;
        boolean z;
        Bundle bundle2;
        ApplicationInfo applicationInfo;
        if (!this.zzc) {
            ActivityInfo activityInfo = null;
            try {
                applicationInfo = Wrappers.packageManager(activity).getApplicationInfo(activity.getPackageName(), 128);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (applicationInfo != null) {
                bundle = applicationInfo.metaData;
                z = false;
                if (bundle != null && bundle.getString("com.epicgames.unreal.GameActivity.EngineVersion", "").startsWith("5.")) {
                    try {
                        activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, "com.epicgames.unreal.GameActivity"), 128);
                    } catch (PackageManager.NameNotFoundException unused2) {
                    }
                    if (activityInfo != null && (bundle2 = activityInfo.metaData) != null) {
                        z = Objects.equals(bundle2.getString("android.app.lib_name", ""), "Unreal");
                    }
                }
                this.zzb = z;
                this.zzc = true;
                return z;
            }
            bundle = null;
            z = false;
            if (bundle != null) {
                activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, "com.epicgames.unreal.GameActivity"), 128);
                if (activityInfo != null) {
                    z = Objects.equals(bundle2.getString("android.app.lib_name", ""), "Unreal");
                }
            }
            this.zzb = z;
            this.zzc = true;
            return z;
        }
        return this.zzb;
    }
}
