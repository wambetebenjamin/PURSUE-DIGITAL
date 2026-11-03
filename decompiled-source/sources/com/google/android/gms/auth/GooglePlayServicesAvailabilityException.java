package com.google.android.gms.auth;

import android.content.Intent;
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes4.dex */
public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GooglePlayServicesAvailabilityException(int i2, String str, Intent intent) {
        super(str, intent);
        this.zza = i2;
    }

    public int getConnectionStatusCode() {
        return this.zza;
    }
}
