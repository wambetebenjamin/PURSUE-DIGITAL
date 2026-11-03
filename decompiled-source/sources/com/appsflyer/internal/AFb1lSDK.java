package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import java.io.File;
import java.security.SecureRandom;
/* loaded from: classes.dex */
public final class AFb1lSDK {
    private static String AFInAppEventParameterName;

    /* JADX WARN: Removed duplicated region for block: B:27:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String valueOf(java.io.File r6) {
        /*
            java.lang.String r0 = "Exception while trying to close the InstallationFile"
            r1 = 0
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            java.lang.String r3 = "r"
            r2.<init>(r6, r3)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a
            long r3 = r2.length()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            int r6 = (int) r3     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            byte[] r1 = new byte[r6]     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.readFully(r1)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.close()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            r2.close()     // Catch: java.io.IOException -> L1b
            goto L3c
        L1b:
            r6 = move-exception
            com.appsflyer.AFLogger.afErrorLog(r0, r6)
            goto L3c
        L20:
            r6 = move-exception
            r1 = r2
            goto L4c
        L23:
            r6 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L2c
        L28:
            r6 = move-exception
            goto L4c
        L2a:
            r6 = move-exception
            r2 = r1
        L2c:
            java.lang.String r3 = "Exception while reading InstallationFile: "
            com.appsflyer.AFLogger.afErrorLog(r3, r6)     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L3b
            r1.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r6 = move-exception
            com.appsflyer.AFLogger.afErrorLog(r0, r6)
        L3b:
            r1 = r2
        L3c:
            java.lang.String r6 = new java.lang.String
            if (r1 == 0) goto L41
            goto L44
        L41:
            r0 = 0
            byte[] r1 = new byte[r0]
        L44:
            java.nio.charset.Charset r0 = java.nio.charset.Charset.defaultCharset()
            r6.<init>(r1, r0)
            return r6
        L4c:
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.io.IOException -> L52
            goto L56
        L52:
            r1 = move-exception
            com.appsflyer.AFLogger.afErrorLog(r0, r1)
        L56:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFb1lSDK.valueOf(java.io.File):java.lang.String");
    }

    public static synchronized String values(AFd1lSDK aFd1lSDK, AFd1xSDK aFd1xSDK) {
        synchronized (AFb1lSDK.class) {
            if (aFd1lSDK.AFInAppEventParameterName == null) {
                return AFInAppEventParameterName;
            }
            if (AFInAppEventParameterName == null) {
                String valueOf = aFd1xSDK.valueOf("AF_INSTALLATION", (String) null);
                if (valueOf != null) {
                    AFInAppEventParameterName = valueOf;
                } else {
                    try {
                        File file = new File(aFd1lSDK.AFInAppEventParameterName.getFilesDir(), "AF_INSTALLATION");
                        if (file.exists()) {
                            AFInAppEventParameterName = valueOf(file);
                            file.delete();
                        } else {
                            AFInAppEventParameterName = new StringBuilder().append(System.currentTimeMillis()).append("-").append(Math.abs(new SecureRandom().nextLong())).toString();
                        }
                        aFd1xSDK.values("AF_INSTALLATION", AFInAppEventParameterName);
                    } catch (Exception e2) {
                        AFLogger.afErrorLog("Error getting AF unique ID", e2);
                    }
                }
                if (AFInAppEventParameterName != null) {
                    AppsFlyerProperties.getInstance().set("uid", AFInAppEventParameterName);
                }
            }
            return AFInAppEventParameterName;
        }
    }
}
