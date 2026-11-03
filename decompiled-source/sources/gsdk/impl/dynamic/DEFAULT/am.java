package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import java.util.ArrayList;
import java.util.List;
/* compiled from: HuaweiNotchScreen.java */
/* loaded from: classes10.dex */
public class am implements aj {
    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Window window) {
        return true;
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public boolean a(Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // gsdk.impl.dynamic.DEFAULT.aj
    public List<Rect> a(Context context, Window window) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            int[] iArr = (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(aq.a(context, iArr[0], iArr[1]));
            return arrayList;
        } catch (Throwable unused) {
            return null;
        }
    }
}
