package tt.g.p.f.n;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.gameprotect.IlIlllIiI;
/* loaded from: classes10.dex */
public class j {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class b extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ v f21225a;

        private b(v vVar) {
            this.f21225a = vVar;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            v vVar;
            boolean z;
            if (o.b(context)) {
                vVar = this.f21225a;
                z = true;
            } else {
                vVar = this.f21225a;
                z = false;
            }
            vVar.a(z);
        }
    }

    public static Object a(int i2, long j2, Object... objArr) {
        try {
            return IlIlllIiI.a(i2, j2, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        Object a2 = a(8, 0L, bArr);
        return (a2 == null || !(a2 instanceof String)) ? "" : (String) a2;
    }

    public static void a() {
        a(19, 0L, new Object[0]);
    }

    public static void a(int i2) {
        a(21, i2, new Object[0]);
    }

    public static void a(int i2, String str) {
        a(7, i2, str);
    }

    public static void a(int i2, String str, String str2) {
        a(9, i2, str, str2);
    }

    public static void a(long j2) {
        a(5, j2, new Object[0]);
    }

    public static void a(long j2, String str) {
        a(16, j2, str);
    }

    public static void a(long j2, String str, byte[] bArr) {
        a(1, j2, str, bArr);
    }

    public static void a(Object obj) {
        a(13, 0L, obj);
    }

    public static String b() {
        return (String) a(18, 0L, new Object[0]);
    }

    public static void b(int i2) {
        a(4, 0L, String.valueOf(i2));
    }

    public static void b(long j2, String str) {
        a(2, j2, str);
    }

    public static void c() {
        a(20, 0L, new Object[0]);
    }
}
