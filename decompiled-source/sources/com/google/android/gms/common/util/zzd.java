package com.google.android.gms.common.util;

import android.os.StrictMode;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class zzd {
    public static StrictMode.VmPolicy zza() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (PlatformVersion.isAtLeastS()) {
            StrictMode.setVmPolicy(zzc.zza(new StrictMode.VmPolicy.Builder(vmPolicy)).build());
        }
        return vmPolicy;
    }
}
