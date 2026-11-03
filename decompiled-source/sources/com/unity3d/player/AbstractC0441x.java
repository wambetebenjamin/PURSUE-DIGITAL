package com.unity3d.player;
/* renamed from: com.unity3d.player.x  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
abstract class AbstractC0441x {
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0031, code lost:
        if (r0 == false) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.app.Activity r5) {
        /*
            if (r5 == 0) goto L53
            android.view.Window r0 = r5.getWindow()
            if (r0 == 0) goto L53
            boolean r0 = com.unity3d.player.PlatformSupport.PIE_SUPPORT
            if (r0 == 0) goto L53
            boolean r0 = com.unity3d.player.PlatformSupport.VANILLA_ICE_CREAM_SUPPORT
            r1 = 0
            if (r0 == 0) goto L12
            goto L33
        L12:
            boolean r0 = com.unity3d.player.PlatformSupport.RED_VELVET_CAKE_SUPPORT
            java.lang.String r2 = "unity.render-outside-safearea"
            r3 = 128(0x80, float:1.8E-43)
            if (r0 == 0) goto L35
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch: java.lang.Exception -> L30
            java.lang.String r4 = r5.getPackageName()     // Catch: java.lang.Exception -> L30
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r4, r3)     // Catch: java.lang.Exception -> L30
            if (r0 == 0) goto L30
            android.os.Bundle r0 = r0.metaData     // Catch: java.lang.Exception -> L30
            boolean r0 = r0.getBoolean(r2)     // Catch: java.lang.Exception -> L30
            goto L31
        L30:
            r0 = r1
        L31:
            if (r0 == 0) goto L49
        L33:
            r1 = 3
            goto L49
        L35:
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch: java.lang.Exception -> L49
            java.lang.String r4 = r5.getPackageName()     // Catch: java.lang.Exception -> L49
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r4, r3)     // Catch: java.lang.Exception -> L49
            if (r0 == 0) goto L49
            android.os.Bundle r0 = r0.metaData     // Catch: java.lang.Exception -> L49
            boolean r1 = r0.getBoolean(r2)     // Catch: java.lang.Exception -> L49
        L49:
            android.view.Window r5 = r5.getWindow()
            android.view.WindowManager$LayoutParams r5 = r5.getAttributes()
            r5.layoutInDisplayCutoutMode = r1
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.AbstractC0441x.a(android.app.Activity):void");
    }
}
