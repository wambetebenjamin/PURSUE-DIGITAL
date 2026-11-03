package tt.g.p.f.n;

import java.util.Arrays;
/* loaded from: classes10.dex */
public class ad {
    public static String a() {
        return Arrays.toString(new Throwable().getStackTrace());
    }

    public static String b() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append(stackTraceElement.getClassName());
        }
        return sb.toString();
    }
}
