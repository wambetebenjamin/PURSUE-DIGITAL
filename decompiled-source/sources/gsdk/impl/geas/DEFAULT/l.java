package gsdk.impl.geas.DEFAULT;

import java.io.Closeable;
/* compiled from: CommonUtil.java */
/* loaded from: classes10.dex */
public class l {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
