package gsdk.impl.geas.DEFAULT;
/* compiled from: CPUUtil.java */
/* loaded from: classes10.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f20373a = false;

    /* renamed from: b  reason: collision with root package name */
    private static String f20374b = "unknown";

    public static void a() {
        f20373a = true;
        b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
        gsdk.impl.geas.DEFAULT.k.f20374b = r0.substring(r0.indexOf(58) + 1).trim();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L4e
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L4e
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L40
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L40
        Ld:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
            if (r0 == 0) goto L35
            java.lang.String r3 = "Hardware"
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
            if (r3 == 0) goto Ld
            java.lang.String r3 = ":"
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
            if (r3 == 0) goto Ld
            r3 = 58
            int r3 = r0.indexOf(r3)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
            int r3 = r3 + 1
            java.lang.String r0 = r0.substring(r3)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
            gsdk.impl.geas.DEFAULT.k.f20374b = r0     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L41
        L35:
            gsdk.impl.geas.DEFAULT.l.a(r1)
            goto L52
        L39:
            r0 = move-exception
            goto L47
        L3b:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L47
        L40:
            r2 = r0
        L41:
            r0 = r1
            goto L4f
        L43:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L47:
            gsdk.impl.geas.DEFAULT.l.a(r1)
            gsdk.impl.geas.DEFAULT.l.a(r2)
            throw r0
        L4e:
            r2 = r0
        L4f:
            gsdk.impl.geas.DEFAULT.l.a(r0)
        L52:
            gsdk.impl.geas.DEFAULT.l.a(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gsdk.impl.geas.DEFAULT.k.b():void");
    }

    public static String c() {
        if (!f20373a) {
            a();
        }
        return f20374b;
    }
}
