package g.hades.downloader;

import java.io.Closeable;
/* loaded from: classes10.dex */
public class h {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
