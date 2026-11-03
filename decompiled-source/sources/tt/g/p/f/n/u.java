package tt.g.p.f.n;

import org.json.JSONObject;
/* loaded from: classes10.dex */
public class u {

    /* loaded from: classes10.dex */
    public class b implements q {

        /* renamed from: b  reason: collision with root package name */
        private final String f21237b = Integer.toString(s.a());

        public b() {
        }

        @Override // tt.g.p.f.n.q
        public void a(int i2, JSONObject jSONObject) {
            if (jSONObject != null) {
                j.a(i2, this.f21237b, jSONObject.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum c {
        INSTANCE;
        

        /* renamed from: c  reason: collision with root package name */
        private final u f21240c = new u();

        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public u a() {
            return this.f21240c;
        }
    }

    private u() {
        try {
            s.a(new b());
        } catch (Exception unused) {
        }
    }

    public static u a() {
        return c.INSTANCE.a();
    }
}
