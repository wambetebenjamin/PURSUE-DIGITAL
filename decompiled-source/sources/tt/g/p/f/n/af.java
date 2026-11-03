package tt.g.p.f.n;

import java.nio.charset.Charset;
/* loaded from: classes10.dex */
public class af {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f21142a = Charset.forName("UTF-8");

    public static byte[] a(String str) {
        return a(str, f21142a);
    }

    private static byte[] a(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }
}
