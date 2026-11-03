package com.appsflyer;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import com.appsflyer.internal.AFb1qSDK;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import javax.security.auth.x500.X500Principal;
/* loaded from: classes.dex */
public class AFKeystoreWrapper {
    public KeyStore AFKeystoreWrapper;
    private Context values;
    public final Object AFInAppEventType = new Object();
    public String AFInAppEventParameterName = "";
    public int valueOf = 0;

    public AFKeystoreWrapper(Context context) {
        this.values = context;
        AFLogger.afInfoLog("Initialising KeyStore..");
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            this.AFKeystoreWrapper = keyStore;
            keyStore.load(null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
            AFLogger.afErrorLog("Couldn't load keystore instance of type: AndroidKeyStore", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        r1 = r4.split(",");
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0029, code lost:
        if (r1.length != 3) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
        com.appsflyer.AFLogger.afInfoLog("Found a matching AF key with alias:\n".concat(java.lang.String.valueOf(r4)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
        r3 = r1[1].trim().split("=");
        r1 = r1[2].trim().split("=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
        if (r3.length != 2) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
        if (r1.length != 2) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0057, code lost:
        r7.AFInAppEventParameterName = r3[1].trim();
        r7.valueOf = java.lang.Integer.parseInt(r1[1].trim());
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006c, code lost:
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0070, code lost:
        com.appsflyer.AFLogger.afErrorLog(new java.lang.StringBuilder("Couldn't list KeyStore Aliases: ").append(r1.getClass().getName()).toString(), r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean values() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.AFInAppEventType
            monitor-enter(r0)
            java.security.KeyStore r1 = r7.AFKeystoreWrapper     // Catch: java.lang.Throwable -> L8e
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L8b
            java.util.Enumeration r1 = r1.aliases()     // Catch: java.lang.Throwable -> L6e
        Ld:
            boolean r4 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L6e
            if (r4 == 0) goto L8b
            java.lang.Object r4 = r1.nextElement()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L6e
            if (r4 == 0) goto Ld
            boolean r5 = valueOf(r4)     // Catch: java.lang.Throwable -> L6e
            if (r5 == 0) goto Ld
            java.lang.String r1 = ","
            java.lang.String[] r1 = r4.split(r1)     // Catch: java.lang.Throwable -> L6e
            int r5 = r1.length     // Catch: java.lang.Throwable -> L6e
            r6 = 3
            if (r5 != r6) goto L8b
            java.lang.String r5 = "Found a matching AF key with alias:\n"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r4 = r5.concat(r4)     // Catch: java.lang.Throwable -> L6e
            com.appsflyer.AFLogger.afInfoLog(r4)     // Catch: java.lang.Throwable -> L6e
            r3 = r1[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r4 = "="
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L6c
            r4 = 2
            r1 = r1[r4]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r5 = "="
            java.lang.String[] r1 = r1.split(r5)     // Catch: java.lang.Throwable -> L6c
            int r5 = r3.length     // Catch: java.lang.Throwable -> L6c
            if (r5 != r4) goto L8c
            int r5 = r1.length     // Catch: java.lang.Throwable -> L6c
            if (r5 != r4) goto L8c
            r3 = r3[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L6c
            r7.AFInAppEventParameterName = r3     // Catch: java.lang.Throwable -> L6c
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L6c
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L6c
            r7.valueOf = r1     // Catch: java.lang.Throwable -> L6c
            goto L8c
        L6c:
            r1 = move-exception
            goto L70
        L6e:
            r1 = move-exception
            r2 = r3
        L70:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e
            java.lang.String r4 = "Couldn't list KeyStore Aliases: "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L8e
            java.lang.Class r4 = r1.getClass()     // Catch: java.lang.Throwable -> L8e
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L8e
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L8e
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L8e
            com.appsflyer.AFLogger.afErrorLog(r3, r1)     // Catch: java.lang.Throwable -> L8e
            goto L8c
        L8b:
            r2 = r3
        L8c:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8e
            return r2
        L8e:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AFKeystoreWrapper.values():boolean");
    }

    public final void values(String str) {
        AFLogger.afInfoLog("Creating a new key with alias: ".concat(String.valueOf(str)));
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 5);
            AlgorithmParameterSpec algorithmParameterSpec = null;
            synchronized (this.AFInAppEventType) {
                if (!this.AFKeystoreWrapper.containsAlias(str)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        algorithmParameterSpec = new KeyGenParameterSpec.Builder(str, 3).setCertificateSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
                    } else if (Build.VERSION.SDK_INT >= 18 && !AFb1qSDK.AFKeystoreWrapper()) {
                        algorithmParameterSpec = new KeyPairGeneratorSpec.Builder(this.values).setAlias(str).setSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setSerialNumber(BigInteger.ONE).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
                    }
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    keyPairGenerator.initialize(algorithmParameterSpec);
                    keyPairGenerator.generateKeyPair();
                } else {
                    AFLogger.afInfoLog("Alias already exists: ".concat(String.valueOf(str)));
                }
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog(new StringBuilder("Exception ").append(th.getMessage()).append(" occurred").toString(), th);
        }
    }

    private static boolean valueOf(String str) {
        return str.startsWith("com.appsflyer");
    }

    public final String AFKeystoreWrapper() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.appsflyer,");
        synchronized (this.AFInAppEventType) {
            sb.append("KSAppsFlyerId=").append(this.AFInAppEventParameterName).append(",");
            sb.append("KSAppsFlyerRICounter=").append(this.valueOf);
        }
        return sb.toString();
    }

    public final String AFInAppEventParameterName() {
        String str;
        synchronized (this.AFInAppEventType) {
            str = this.AFInAppEventParameterName;
        }
        return str;
    }

    public final int valueOf() {
        int i2;
        synchronized (this.AFInAppEventType) {
            i2 = this.valueOf;
        }
        return i2;
    }
}
