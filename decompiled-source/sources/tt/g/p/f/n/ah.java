package tt.g.p.f.n;
/* loaded from: classes10.dex */
public class ah {
    public static String a(String str) {
        byte length = (byte) str.length();
        byte[] bytes = str.getBytes();
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bytes[i2] = (byte) (bytes[i2] ^ length);
        }
        return new String(bytes, 0, bytes.length);
    }
}
