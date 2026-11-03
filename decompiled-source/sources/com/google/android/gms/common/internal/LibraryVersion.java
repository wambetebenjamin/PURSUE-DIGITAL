package com.google.android.gms.common.internal;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
@Deprecated
/* loaded from: classes4.dex */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static final LibraryVersion zzb = new LibraryVersion();
    private final ConcurrentHashMap zzc = new ConcurrentHashMap();

    protected LibraryVersion() {
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getVersion(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "LibraryVersion"
            java.lang.String r1 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9, r1)
            java.lang.String r1 = " version is "
            java.lang.String r2 = "Failed to get app version for libraryName: "
            java.util.concurrent.ConcurrentHashMap r3 = r8.zzc
            boolean r4 = r3.containsKey(r9)
            if (r4 == 0) goto L1a
            java.lang.Object r9 = r3.get(r9)
            java.lang.String r9 = (java.lang.String) r9
            return r9
        L1a:
            java.util.Properties r3 = new java.util.Properties
            r3.<init>()
            r4 = 0
            java.lang.String r5 = "/%s.properties"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
            r7 = 0
            r6[r7] = r9     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r6 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r5 = r6.getResourceAsStream(r5)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
            if (r5 == 0) goto L68
            r3.load(r5)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            java.lang.String r6 = "version"
            java.lang.String r4 = r3.getProperty(r6, r4)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            com.google.android.gms.common.internal.GmsLogger r3 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            java.lang.String r6 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            int r6 = r6.length()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            int r6 = r6 + 12
            java.lang.String r7 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            int r7 = r7.length()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            int r6 = r6 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r7.append(r9)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r7.append(r1)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r7.append(r4)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            java.lang.String r1 = r7.toString()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r3.v(r0, r1)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            goto Lb2
        L68:
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            int r3 = r3.length()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            int r3 = r3 + 43
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r6.append(r2)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r6.append(r9)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            java.lang.String r3 = r6.toString()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            r1.w(r0, r3)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8a
            goto Lb2
        L87:
            r9 = move-exception
            r4 = r5
            goto Lc8
        L8a:
            r1 = move-exception
            r3 = r4
            r4 = r5
            goto L92
        L8e:
            r9 = move-exception
            goto Lc8
        L90:
            r1 = move-exception
            r3 = r4
        L92:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.android.gms.common.internal.LibraryVersion.zza     // Catch: java.lang.Throwable -> L8e
            java.lang.String r6 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L8e
            int r6 = r6.length()     // Catch: java.lang.Throwable -> L8e
            int r6 = r6 + 43
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L8e
            r7.append(r2)     // Catch: java.lang.Throwable -> L8e
            r7.append(r9)     // Catch: java.lang.Throwable -> L8e
            java.lang.String r2 = r7.toString()     // Catch: java.lang.Throwable -> L8e
            r5.e(r0, r2, r1)     // Catch: java.lang.Throwable -> L8e
            r5 = r4
            r4 = r3
        Lb2:
            if (r5 == 0) goto Lb7
            com.google.android.gms.common.util.IOUtils.closeQuietly(r5)
        Lb7:
            if (r4 != 0) goto Lc2
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.android.gms.common.internal.LibraryVersion.zza
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r1.d(r0, r2)
            java.lang.String r4 = "UNKNOWN"
        Lc2:
            java.util.concurrent.ConcurrentHashMap r0 = r8.zzc
            r0.put(r9, r4)
            return r4
        Lc8:
            if (r4 == 0) goto Lcd
            com.google.android.gms.common.util.IOUtils.closeQuietly(r4)
        Lcd:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
