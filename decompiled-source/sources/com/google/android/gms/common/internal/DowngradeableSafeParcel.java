package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zza = new Object();
    private boolean zzb = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean canUnparcelSafely(String str) {
        synchronized (zza) {
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Integer getUnparcelClientVersion() {
        synchronized (zza) {
        }
        return null;
    }

    protected abstract boolean prepareForClientVersion(int i2);

    public void setShouldDowngrade(boolean z) {
        this.zzb = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean shouldDowngrade() {
        return this.zzb;
    }
}
