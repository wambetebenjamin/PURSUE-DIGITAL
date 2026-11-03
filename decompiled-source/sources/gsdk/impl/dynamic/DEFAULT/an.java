package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import java.util.ArrayList;
import java.util.List;
/* compiled from: MiNotchScreen.java */
/* loaded from: classes10.dex */
public class an implements aj {
    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Window window) {
        return true;
    }

    private static boolean a() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, "ro.miui.notch", 0)).intValue() == 1;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int b(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int c(Context context) {
        int identifier = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Context context) {
        return a();
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public List<Rect> a(Context context, Window window) {
        Rect a2 = aq.a(context, c(context), b(context));
        ArrayList arrayList = new ArrayList();
        arrayList.add(a2);
        return arrayList;
    }
}
