package tt.g.p.f.n;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static Context f21221a;

    public static HashMap<String, String> a(String str) {
        Signature[] a2;
        Context context = f21221a;
        return (context == null || (a2 = a(context, str)) == null || a2.length <= 0) ? new HashMap<>() : a(a2[0].toByteArray());
    }

    private static HashMap<String, String> a(byte[] bArr) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            hashMap.put("algorithm", x509Certificate.getSigAlgName());
            hashMap.put("issuer", x509Certificate.getIssuerDN().toString());
            hashMap.put("subject", x509Certificate.getSubjectDN().toString());
            hashMap.put("not_before", String.valueOf(x509Certificate.getNotBefore().getTime()));
            hashMap.put("not_after", String.valueOf(x509Certificate.getNotAfter().getTime()));
            hashMap.put("version", String.valueOf(x509Certificate.getVersion()));
            hashMap.put("serial", x509Certificate.getSerialNumber().toString(16));
            hashMap.put("sha1_hash", b(bArr));
            hashMap.put("xx_hash", j.a(bArr));
        } catch (CertificateException unused) {
        }
        return hashMap;
    }

    public static void a(Context context) {
        f21221a = context.getApplicationContext();
    }

    private static Signature[] a(Context context, String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 28 ? b(context, str) : c(context, str);
    }

    public static String b(String str) {
        Signature[] a2;
        return (str.isEmpty() || (a2 = a(f21221a, str)) == null || a2.length <= 0) ? "" : z.b(a2[0].toByteArray());
    }

    private static String b(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(com.bytedance.common.utility.n.SHA_1);
            messageDigest.update(bArr);
            return ak.a(messageDigest.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    private static Signature[] b(Context context, String str) {
        SigningInfo signingInfo;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 134217792);
            if (packageInfo == null || (signingInfo = packageInfo.signingInfo) == null) {
                return null;
            }
            return signingInfo.getApkContentsSigners();
        } catch (Exception unused) {
            return null;
        }
    }

    private static Signature[] c(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                return packageInfo.signatures;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
