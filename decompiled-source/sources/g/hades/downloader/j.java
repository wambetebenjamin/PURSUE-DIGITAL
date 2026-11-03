package g.hades.downloader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/* loaded from: classes10.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static ConnectivityManager f19088a;

    public static char a(Context context) {
        try {
            ConnectivityManager connectivityManager = f19088a;
            if (connectivityManager == null) {
                connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                f19088a = connectivityManager;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                return 1 == activeNetworkInfo.getType() ? (char) 1 : (char) 2;
            }
        } catch (Throwable unused) {
        }
        return (char) 0;
    }
}
