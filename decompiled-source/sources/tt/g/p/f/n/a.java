package tt.g.p.f.n;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.bytedance.librarian.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes10.dex */
public class a {
    public static String a(Context context) {
        try {
            return h(context).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String b(Context context) {
        if (context.getFilesDir() != null) {
            return context.getFilesDir().getAbsolutePath();
        }
        File dir = context.getDir("gpfiles", 0);
        if (dir != null) {
            return dir.getAbsolutePath();
        }
        String str = "/data/data/" + context.getApplicationInfo().packageName + c.a.SEPARATOR + "gpfiles";
        new File(str).mkdirs();
        return str;
    }

    public static int c(Context context) {
        try {
            return h(context).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static String d(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String e(Context context) {
        try {
            return String.valueOf(h(context).firstInstallTime);
        } catch (PackageManager.NameNotFoundException unused) {
            return "0";
        }
    }

    public static String f(Context context) {
        try {
            return String.valueOf(h(context).lastUpdateTime);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "0";
        }
    }

    public static String g(Context context) {
        try {
            File file = new File(b(context), "launch_ts.txt");
            if (file.exists()) {
                byte[] bArr = new byte[(int) file.length()];
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bArr);
                fileInputStream.close();
                return new String(bArr);
            }
            String valueOf = String.valueOf(System.currentTimeMillis());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(valueOf.getBytes());
            fileOutputStream.close();
            return valueOf;
        } catch (IOException e2) {
            e2.printStackTrace();
            return "0";
        }
    }

    private static PackageInfo h(Context context) {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    }
}
