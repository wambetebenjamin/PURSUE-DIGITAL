package tt.g.p.f.n;

import java.util.Map;
/* loaded from: classes10.dex */
public class ba {

    /* loaded from: classes10.dex */
    class a implements aq {
        a() {
        }

        @Override // tt.g.p.f.n.aq
        public void a(Exception exc) {
        }

        @Override // tt.g.p.f.n.aq
        public void a(ay ayVar) {
        }
    }

    public static int a() {
        return 15000;
    }

    public static ay a(String str, String str2, Map<String, Object> map, byte[] bArr, Map<String, Object> map2, Map<String, Object> map3) {
        as b2 = b(str, str2, map, bArr, map2, map3);
        b2.b();
        return b2.a();
    }

    public static void a(String str, String str2, Map<String, Object> map, byte[] bArr, Map<String, Object> map2, Map<String, Object> map3, aq aqVar) {
        if (aqVar == null) {
            aqVar = new a();
        }
        try {
            new ao(b(str, str2, map, bArr, map2, map3), aqVar).start();
        } catch (Exception e2) {
            aqVar.a(e2);
        }
    }

    public static int b() {
        return 15000;
    }

    private static as b(String str, String str2, Map<String, Object> map, byte[] bArr, Map<String, Object> map2, Map<String, Object> map3) {
        as asVar = new as();
        if (bArr != null) {
            asVar.a(str2, bArr, map, map3);
        } else {
            asVar.a(str, str2, map, map2, map3);
        }
        return asVar;
    }
}
