package gsdk.impl.pay.DEFAULT;

import android.content.Context;
import android.content.pm.PackageManager;
/* compiled from: PayEnvironmentCheck.java */
/* loaded from: classes10.dex */
public class o {
    public static boolean a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
