package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Window;
import com.facebook.internal.security.CertificateUtil;
import java.util.ArrayList;
import java.util.List;
/* compiled from: OppoNotchScreen.java */
/* loaded from: classes10.dex */
public class ao implements aj {
    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Window window) {
        return true;
    }

    private static String a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls.newInstance(), "ro.oppo.screen.heteromorphism");
        } catch (Throwable unused) {
            return "";
        }
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public List<Rect> a(Context context, Window window) {
        int intValue;
        int i2;
        int i3;
        int i4;
        try {
            String a2 = a();
            if (!TextUtils.isEmpty(a2)) {
                String[] split = a2.split(CertificateUtil.DELIMITER);
                String[] split2 = split[0].split(",");
                String[] split3 = split[1].split(",");
                if (aq.a(context)) {
                    i4 = Integer.valueOf(split2[0]).intValue();
                    i2 = Integer.valueOf(split2[1]).intValue();
                    i3 = Integer.valueOf(split3[0]).intValue();
                    intValue = Integer.valueOf(split3[1]).intValue();
                } else {
                    int c2 = aq.c(context);
                    int intValue2 = Integer.valueOf(split2[1]).intValue();
                    int intValue3 = Integer.valueOf(split2[0]).intValue();
                    int intValue4 = Integer.valueOf(split3[1]).intValue();
                    intValue = Integer.valueOf(split3[0]).intValue();
                    if (c2 == 3) {
                        i3 = aq.b(context)[0];
                        i4 = i3 - (intValue4 - intValue2);
                        i2 = intValue3;
                    } else {
                        i2 = intValue3;
                        i3 = intValue4;
                        i4 = intValue2;
                    }
                }
                Rect rect = new Rect(i4, i2, i3, intValue);
                ArrayList arrayList = new ArrayList();
                arrayList.add(rect);
                return arrayList;
            }
        } catch (Throwable unused) {
        }
        return null;
    }
}
