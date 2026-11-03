package com.google.android.gms.common;

import android.content.Intent;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zza;

    public GooglePlayServicesRepairableException(int i2, String str, Intent intent) {
        super(str, intent);
        this.zza = i2;
    }

    public int getConnectionStatusCode() {
        return this.zza;
    }
}
