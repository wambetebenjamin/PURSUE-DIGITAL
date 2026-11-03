package tt.g.p.f.n;
/* loaded from: classes10.dex */
public class ao extends Thread {

    /* renamed from: a  reason: collision with root package name */
    as f21157a;

    /* renamed from: b  reason: collision with root package name */
    aq f21158b;

    public ao(as asVar, aq aqVar) {
        this.f21157a = asVar;
        this.f21158b = aqVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            this.f21157a.b();
            this.f21158b.a(this.f21157a.a());
        } catch (Exception e2) {
            this.f21158b.a(e2);
        }
    }
}
