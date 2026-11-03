package tt.g.p.f.n;

import android.os.IBinder;
import com.bytedance.JProtect;
import java.lang.reflect.InvocationHandler;
@JProtect
/* loaded from: classes10.dex */
public class f implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    IBinder f21220a;

    public f(IBinder iBinder) {
        this.f21220a = iBinder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.os.Parcel] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [int] */
    @Override // java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object invoke(java.lang.Object r4, java.lang.reflect.Method r5, java.lang.Object[] r6) {
        /*
            r3 = this;
            java.lang.String r4 = r5.getName()
            java.lang.String r0 = "transact"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L54
            r4 = 0
            r0 = r6[r4]
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r1 = 1
            r2 = r6[r1]
            android.os.Parcel r2 = (android.os.Parcel) r2
            if (r0 == r1) goto L36
            r1 = 9
            if (r0 == r1) goto L21
            goto L54
        L21:
            int r0 = r2.dataSize()     // Catch: java.lang.Throwable -> L4f
            r2.setDataPosition(r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r4 = tt.g.p.f.n.bf.f21188d     // Catch: java.lang.Throwable -> L4e
            r2.enforceInterface(r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r4 = r2.readString()     // Catch: java.lang.Throwable -> L4e
        L31:
            java.lang.String r1 = tt.g.p.f.n.bl.a()     // Catch: java.lang.Throwable -> L4e
            goto L47
        L36:
            int r0 = r2.dataSize()     // Catch: java.lang.Throwable -> L4f
            r2.setDataPosition(r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r4 = tt.g.p.f.n.bf.f21188d     // Catch: java.lang.Throwable -> L4e
            r2.enforceInterface(r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r4 = r2.readString()     // Catch: java.lang.Throwable -> L4e
            goto L31
        L47:
            tt.g.p.f.n.c.a(r4, r1)     // Catch: java.lang.Throwable -> L4e
            r2.setDataPosition(r0)     // Catch: java.lang.Throwable -> L4e
            goto L54
        L4e:
            r4 = r0
        L4f:
            if (r4 <= 0) goto L54
            r2.setDataPosition(r4)
        L54:
            android.os.IBinder r4 = r3.f21220a
            java.lang.Object r4 = r5.invoke(r4, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: tt.g.p.f.n.f.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}
