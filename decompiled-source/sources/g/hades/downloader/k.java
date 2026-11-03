package g.hades.downloader;

import android.app.NotificationChannel;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import java.text.DecimalFormat;
/* loaded from: classes10.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    public static NotificationChannel f19089a;

    /* renamed from: b  reason: collision with root package name */
    public static NotificationChannel f19090b;

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r0 != null) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(android.content.Context r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 20
            if (r0 <= r2) goto L38
            if (r6 != 0) goto La
            goto L38
        La:
            r0 = 0
            int r2 = com.bytedance.hades.downloader.R.color.download_notification_title_color     // Catch: java.lang.Throwable -> L33
            android.content.res.Resources r3 = r6.getResources()     // Catch: java.lang.Throwable -> L33
            int r2 = r3.getColor(r2)     // Catch: java.lang.Throwable -> L33
            r3 = 16842904(0x1010098, float:2.3693984E-38)
            r4 = 16842901(0x1010095, float:2.3693976E-38)
            r5 = 2
            int[] r5 = new int[r5]     // Catch: java.lang.Throwable -> L33
            r5[r1] = r3     // Catch: java.lang.Throwable -> L33
            r3 = 1
            r5[r3] = r4     // Catch: java.lang.Throwable -> L33
            int r4 = com.bytedance.hades.downloader.R.style.download_style_notification_title     // Catch: java.lang.Throwable -> L33
            android.content.res.TypedArray r0 = r6.obtainStyledAttributes(r4, r5)     // Catch: java.lang.Throwable -> L33
            int r6 = r0.getColor(r1, r1)     // Catch: java.lang.Throwable -> L33
            if (r2 != r6) goto L35
            r0.recycle()     // Catch: java.lang.Throwable -> L32
        L32:
            return r3
        L33:
            if (r0 == 0) goto L38
        L35:
            r0.recycle()     // Catch: java.lang.Throwable -> L38
        L38:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: g.hades.downloader.k.a(android.content.Context):boolean");
    }

    public static String a(long j2, boolean z) {
        long[] jArr = {DownloadConstants.TB, 1073741824, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j2 < 1) {
            return "0 " + strArr[4];
        }
        for (int i2 = 0; i2 < 5; i2++) {
            long j3 = jArr[i2];
            if (j2 >= j3) {
                String str = strArr[i2];
                double d2 = j2;
                if (j3 > 1) {
                    d2 /= j3;
                }
                if (!z && !"GB".equals(str) && !"TB".equals(str)) {
                    return new DecimalFormat("#").format(d2) + " " + str;
                }
                return new DecimalFormat("#.##").format(d2) + " " + str;
            }
        }
        return null;
    }
}
