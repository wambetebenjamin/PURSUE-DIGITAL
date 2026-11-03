package tt.g.p.f.n;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class ac {

    /* renamed from: a  reason: collision with root package name */
    private static Context f21132a;

    public static Map<String, String> a(boolean z) {
        HashMap hashMap = new HashMap();
        PackageManager packageManager = f21132a.getPackageManager();
        if (packageManager == null) {
            return hashMap;
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(270532608);
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!z || !a(activityInfo)) {
                ApplicationInfo applicationInfo = activityInfo.applicationInfo;
                hashMap.put(applicationInfo.packageName, applicationInfo.sourceDir);
            }
        }
        return hashMap;
    }

    public static void a(Context context) {
        f21132a = context.getApplicationContext();
    }

    private static boolean a(ActivityInfo activityInfo) {
        int i2 = activityInfo.applicationInfo.flags;
        return ((i2 & 1) == 0 && (i2 & 128) == 0) ? false : true;
    }
}
