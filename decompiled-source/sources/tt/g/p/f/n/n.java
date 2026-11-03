package tt.g.p.f.n;

import com.bytedance.ttnet.TTNetInit;
/* loaded from: classes10.dex */
class n {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        try {
            Class.forName(TTNetInit.class.getName());
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
