package com.google.android.gms.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Process;
import androidx.core.util.Pair;
import com.google.android.gms.common.util.PlatformVersion;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public class PackageManagerWrapper {
    protected final Context zza;

    public PackageManagerWrapper(Context context) {
        this.zza = context;
    }

    public int checkCallingOrSelfPermission(String str) {
        return this.zza.checkCallingOrSelfPermission(str);
    }

    public int checkPermission(String str, String str2) {
        return this.zza.getPackageManager().checkPermission(str, str2);
    }

    public ApplicationInfo getApplicationInfo(String str, int i2) throws PackageManager.NameNotFoundException {
        return this.zza.getPackageManager().getApplicationInfo(str, i2);
    }

    public CharSequence getApplicationLabel(String str) throws PackageManager.NameNotFoundException {
        Context context = this.zza;
        return context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(str, 0));
    }

    public Pair<CharSequence, Drawable> getApplicationLabelAndIcon(String str) throws PackageManager.NameNotFoundException {
        Context context = this.zza;
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
        return Pair.create(context.getPackageManager().getApplicationLabel(applicationInfo), context.getPackageManager().getApplicationIcon(applicationInfo));
    }

    public PackageInfo getPackageInfo(String str, int i2) throws PackageManager.NameNotFoundException {
        return this.zza.getPackageManager().getPackageInfo(str, i2);
    }

    public String[] getPackagesForUid(int i2) {
        return this.zza.getPackageManager().getPackagesForUid(i2);
    }

    public boolean isCallerInstantApp() {
        if (Binder.getCallingUid() == Process.myUid()) {
            return InstantApps.isInstantApp(this.zza);
        }
        if (PlatformVersion.isAtLeastO()) {
            Context context = this.zza;
            String nameForUid = context.getPackageManager().getNameForUid(Binder.getCallingUid());
            if (nameForUid != null) {
                return context.getPackageManager().isInstantApp(nameForUid);
            }
            return false;
        }
        return false;
    }

    public final boolean zza(int i2, String str) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) this.zza.getSystemService("appops");
            if (appOpsManager == null) {
                throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");
            }
            appOpsManager.checkPackage(i2, str);
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }
}
