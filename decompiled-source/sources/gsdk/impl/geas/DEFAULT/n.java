package gsdk.impl.geas.DEFAULT;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
/* compiled from: TrafficUtils.java */
/* loaded from: classes10.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private static volatile int f20376a = -1;

    public static long a(Context context, long j2, long j3) {
        return a(context, j2, j3, 1) + a(context, j2, j3, 0);
    }

    public static long a(Context context, long j2, long j3, int i2) {
        NetworkStatsManager networkStatsManager;
        long j4 = 0;
        if (Build.VERSION.SDK_INT > 22 && (networkStatsManager = (NetworkStatsManager) context.getApplicationContext().getSystemService("netstats")) != null) {
            NetworkStats networkStats = null;
            NetworkStats.Bucket bucket = new NetworkStats.Bucket();
            try {
                networkStats = networkStatsManager.querySummary(i2, null, j2, j3);
            } catch (Exception unused) {
            }
            long j5 = 0;
            while (networkStats != null && networkStats.hasNextBucket()) {
                networkStats.getNextBucket(bucket);
                if (a(context) == bucket.getUid()) {
                    j4 += bucket.getRxPackets();
                    j5 += bucket.getTxPackets();
                }
            }
            if (networkStats != null) {
                networkStats.close();
            }
            return j4 + j5;
        }
        return 0L;
    }

    private static int a(Context context) {
        if (f20376a == -1) {
            try {
                PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 128);
                if (packageInfo != null) {
                    f20376a = packageInfo.applicationInfo.uid;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return f20376a;
    }
}
