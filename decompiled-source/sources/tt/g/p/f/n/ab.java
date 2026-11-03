package tt.g.p.f.n;

import android.content.Context;
import com.bytedance.librarian.c;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class ab {
    public static String a() {
        return "";
    }

    private static String a(int i2) {
        int i3 = i2 >>> 8;
        int i4 = i3 >>> 8;
        return (i2 & 255) + c.a.DOT + (i3 & 255) + c.a.DOT + (i4 & 255) + c.a.DOT + ((i4 >>> 8) & 255);
    }

    public static Map<String, String> a(Context context) {
        return new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> b(android.content.Context r4) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            boolean r1 = tt.g.p.f.n.w.b()
            r2 = 0
            if (r1 == 0) goto L2d
            android.content.Context r4 = r4.getApplicationContext()     // Catch: java.lang.Exception -> L2d
            java.lang.String r1 = "wifi"
            java.lang.Object r4 = r4.getSystemService(r1)     // Catch: java.lang.Exception -> L2d
            android.net.wifi.WifiManager r4 = (android.net.wifi.WifiManager) r4     // Catch: java.lang.Exception -> L2d
            if (r4 == 0) goto L2d
            android.net.DhcpInfo r4 = r4.getDhcpInfo()     // Catch: java.lang.Exception -> L2d
            int r1 = r4.dns1     // Catch: java.lang.Exception -> L2d
            java.lang.String r1 = a(r1)     // Catch: java.lang.Exception -> L2d
            int r4 = r4.dns2     // Catch: java.lang.Exception -> L2a
            java.lang.String r2 = a(r4)     // Catch: java.lang.Exception -> L2a
        L2a:
            r4 = r2
            r2 = r1
            goto L2e
        L2d:
            r4 = r2
        L2e:
            java.lang.String r1 = ""
            if (r2 != 0) goto L33
            r2 = r1
        L33:
            java.lang.String r3 = "dns1"
            r0.put(r3, r2)
            if (r4 != 0) goto L3b
            r4 = r1
        L3b:
            java.lang.String r1 = "dns2"
            r0.put(r1, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tt.g.p.f.n.ab.b(android.content.Context):java.util.Map");
    }
}
