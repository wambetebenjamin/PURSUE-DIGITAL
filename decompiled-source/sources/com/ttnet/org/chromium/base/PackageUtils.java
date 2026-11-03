package com.ttnet.org.chromium.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.google.common.base.Ascii;
import com.oasis.android.AppTool;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class PackageUtils {
    private static final char[] HEX_CHAR_LOOKUP = "0123456789ABCDEF".toCharArray();
    private static final String TAG = "PackageUtils";

    public static int getPackageVersion(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static boolean isPackageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static List<String> getCertificateSHA256FingerprintForPackage(PackageManager packageManager, String str) {
        String str2;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (packageInfo == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                try {
                    str2 = byteArrayToHexString(MessageDigest.getInstance(AppTool.SHA256).digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()))).getEncoded()));
                } catch (NoSuchAlgorithmException | CertificateException unused) {
                    str2 = null;
                } catch (CertificateEncodingException unused2) {
                    Log.w(TAG, "Certificate type X509 encoding failed", new Object[0]);
                    return null;
                }
                arrayList.add(str2);
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException unused3) {
            return null;
        }
    }

    static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder((bArr.length * 3) - 1);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            char[] cArr = HEX_CHAR_LOOKUP;
            sb.append(cArr[(bArr[i2] & 240) >>> 4]);
            sb.append(cArr[bArr[i2] & Ascii.SI]);
            if (i2 < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    private PackageUtils() {
    }
}
