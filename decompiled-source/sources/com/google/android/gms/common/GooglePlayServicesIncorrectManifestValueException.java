package com.google.android.gms.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class GooglePlayServicesIncorrectManifestValueException extends GooglePlayServicesManifestException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GooglePlayServicesIncorrectManifestValueException(int r4) {
        /*
            r3 = this;
            int r0 = com.google.android.gms.common.GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r1 = r1.length()
            java.lang.String r2 = java.lang.String.valueOf(r4)
            int r2 = r2.length()
            int r1 = r1 + 104
            int r1 = r1 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r1 = r1 + 194
            r2.<init>(r1)
            java.lang.String r1 = "The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected "
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = " but found "
            r2.append(r0)
            r2.append(r4)
            java.lang.String r0 = ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r3.<init>(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException.<init>(int):void");
    }
}
