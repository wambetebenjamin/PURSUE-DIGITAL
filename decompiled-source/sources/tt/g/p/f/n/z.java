package tt.g.p.f.n;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes10.dex */
public class z {
    public static MessageDigest a() {
        return a("MD5");
    }

    public static MessageDigest a(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static byte[] a(byte[] bArr) {
        return a().digest(bArr);
    }

    public static String b(byte[] bArr) {
        return ak.a(a(bArr));
    }

    public static MessageDigest b() {
        return a(com.bytedance.common.utility.n.SHA_1);
    }

    public static byte[] b(String str) {
        return a(af.a(str));
    }

    public static String c(String str) {
        return ak.a(b(str));
    }

    public static String c(byte[] bArr) {
        return ak.a(d(bArr));
    }

    public static byte[] d(byte[] bArr) {
        return b().digest(bArr);
    }
}
