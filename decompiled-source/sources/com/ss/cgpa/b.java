package com.ss.cgpa;

import android.util.Log;
import com.facebook.imageutils.JfifUtil;
import com.ttnet.org.chromium.net.NetError;
import java.security.MessageDigest;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import tt.g.p.f.n.ak;
import tt.g.p.f.n.z;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static String f18745a;

    /* renamed from: b  reason: collision with root package name */
    private String f18746b = a("c6ccd8");

    /* renamed from: c  reason: collision with root package name */
    private String f18747c = a("c6ccd8a6cacac9a6d7d2d8caaac9fafdf3f0f5fe");

    /* renamed from: d  reason: collision with root package name */
    private SecretKeySpec f18748d = null;

    /* renamed from: e  reason: collision with root package name */
    private String f18749e = a("c3ccd8");

    /* renamed from: f  reason: collision with root package name */
    private String f18750f = a("c3ccd8a6cacac9a6d7d2d8caaac9fafdf3f0f5fe");

    /* renamed from: g  reason: collision with root package name */
    private SecretKey f18751g = null;

    /* renamed from: h  reason: collision with root package name */
    private String f18752h = a("a2b9b3d1");

    /* renamed from: i  reason: collision with root package name */
    private String f18753i = a("d4c1caa4bdbcbd");

    /* renamed from: j  reason: collision with root package name */
    private String f18754j = getClass().getName();

    /* loaded from: classes9.dex */
    class a {
        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.cgpa.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C0344b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f18755a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f18756b;

        static {
            int[] iArr = new int[d.values().length];
            f18756b = iArr;
            try {
                iArr[d.DES56.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f18756b[d.DES64.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[c.values().length];
            f18755a = iArr2;
            try {
                iArr2[c.AES128.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f18755a[c.AES192.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f18755a[c.AES256.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum c {
        AES128(128),
        AES192(JfifUtil.MARKER_SOFn),
        AES256(256);
        

        /* renamed from: e  reason: collision with root package name */
        private final int f18761e;

        c(int i2) {
            this.f18761e = i2;
        }
    }

    /* loaded from: classes9.dex */
    public enum d {
        DES56(56),
        DES64(64);
        

        /* renamed from: d  reason: collision with root package name */
        private final int f18765d;

        d(int i2) {
            this.f18765d = i2;
        }
    }

    static {
        new a();
        f18745a = a.class.getName();
    }

    public static String a(String str) {
        try {
            return new String(a(ak.a(str)));
        } catch (Exception e2) {
            Log.e(f18745a, e2.toString());
            return null;
        }
    }

    private static byte[] a() {
        byte[] bArr = new byte[16];
        for (int i2 = 135; i2 < 151; i2++) {
            bArr[i2 + NetError.ERR_SSL_CLIENT_AUTH_CERT_NO_PRIVATE_KEY] = (byte) i2;
        }
        return bArr;
    }

    private byte[] a(c cVar, byte[] bArr) {
        StringBuilder append;
        String c2;
        String sb;
        int i2 = C0344b.f18755a[cVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return null;
                }
                if (32 != bArr.length) {
                    sb = d(bArr);
                    return sb.getBytes();
                }
                return bArr;
            } else if (24 == bArr.length) {
                return bArr;
            } else {
                append = new StringBuilder().append(c(bArr)).append(c(d(bArr).getBytes()));
                c2 = c(e(bArr).getBytes());
            }
        } else if (16 == bArr.length) {
            return bArr;
        } else {
            append = new StringBuilder().append(c(bArr));
            c2 = c(d(bArr).getBytes());
        }
        sb = append.append(c2).toString();
        return sb.getBytes();
    }

    private byte[] a(d dVar, byte[] bArr) {
        String c2;
        int i2 = C0344b.f18756b[dVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            if (bArr.length % 8 <= 0) {
                return bArr;
            }
            c2 = d(bArr);
        } else if (8 == bArr.length) {
            return bArr;
        } else {
            c2 = c(bArr);
        }
        return c2.getBytes();
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        byte[] a2 = a();
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2] = (byte) ((bArr[i2] ^ a2[i2 % a2.length]) ^ (i2 & 255));
        }
        return bArr2;
    }

    public byte[] a(byte[] bArr, c cVar, byte[] bArr2) {
        return b(bArr, new SecretKeySpec(a(cVar, bArr2), this.f18746b));
    }

    public byte[] a(byte[] bArr, SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance(this.f18747c);
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            Log.e(this.f18754j, e2.toString());
            return null;
        }
    }

    public String b(String str) {
        return z.c(str);
    }

    public void b(c cVar, byte[] bArr) {
        this.f18748d = new SecretKeySpec(a(cVar, bArr), this.f18746b);
    }

    public void b(d dVar, byte[] bArr) {
        try {
            this.f18751g = SecretKeyFactory.getInstance(this.f18749e).generateSecret(new DESKeySpec(a(dVar, bArr)));
        } catch (Exception e2) {
            Log.e(this.f18754j, e2.toString());
        }
    }

    public byte[] b(byte[] bArr) {
        return a(bArr, this.f18748d);
    }

    public byte[] b(byte[] bArr, SecretKeySpec secretKeySpec) {
        try {
            Cipher cipher = Cipher.getInstance(this.f18747c);
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            Log.e(this.f18754j, e2.toString());
            return null;
        }
    }

    public String c(byte[] bArr) {
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return String.format(this.f18752h, Long.valueOf(crc32.getValue()));
    }

    public String d(byte[] bArr) {
        return z.b(bArr);
    }

    public String e(byte[] bArr) {
        return z.c(bArr);
    }

    public String f(byte[] bArr) {
        try {
            return ak.a(MessageDigest.getInstance(this.f18753i).digest(bArr));
        } catch (Exception e2) {
            Log.e(this.f18754j, e2.toString());
            return null;
        }
    }

    public byte[] g(byte[] bArr) {
        return b(bArr, this.f18748d);
    }

    public byte[] h(byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(this.f18750f);
            cipher.init(1, this.f18751g);
            return cipher.doFinal(bArr);
        } catch (Exception e2) {
            Log.e(this.f18754j, e2.toString());
            return null;
        }
    }
}
