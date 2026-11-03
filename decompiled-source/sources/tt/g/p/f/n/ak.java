package tt.g.p.f.n;

import com.google.common.base.Ascii;
/* loaded from: classes10.dex */
public class ak {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f21154a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b  reason: collision with root package name */
    private static final char[] f21155b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    protected static int a(char c2, int i2) {
        int digit = Character.digit(c2, 16);
        if (digit != -1) {
            return digit;
        }
        throw new t("Illegal hexadecimal character " + c2 + " at index " + i2);
    }

    public static String a(byte[] bArr) {
        return new String(b(bArr));
    }

    public static byte[] a(String str) {
        return a(str.toCharArray());
    }

    public static byte[] a(char[] cArr) {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[length >> 1];
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                int i4 = i2 + 1;
                i2 = i4 + 1;
                bArr[i3] = (byte) (((a(cArr[i2], i2) << 4) | a(cArr[i4], i4)) & 255);
                i3++;
            }
            return bArr;
        }
        throw new t("Odd number of characters.");
    }

    public static char[] a(byte[] bArr, boolean z) {
        return a(bArr, z ? f21154a : f21155b);
    }

    protected static char[] a(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[bArr.length << 1];
        int i2 = 0;
        for (byte b2 : bArr) {
            int i3 = i2 + 1;
            cArr2[i2] = cArr[(b2 & 240) >>> 4];
            i2 = i3 + 1;
            cArr2[i3] = cArr[b2 & Ascii.SI];
        }
        return cArr2;
    }

    public static char[] b(byte[] bArr) {
        return a(bArr, true);
    }
}
