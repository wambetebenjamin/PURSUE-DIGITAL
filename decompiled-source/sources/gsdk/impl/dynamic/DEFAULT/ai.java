package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.Window;
import gsdk.impl.dynamic.DEFAULT.aj;
import java.util.List;
/* compiled from: DynamicScreenManager.java */
/* loaded from: classes10.dex */
public class ai {

    /* renamed from: a  reason: collision with root package name */
    private static final ai f20099a = new ai();

    /* renamed from: b  reason: collision with root package name */
    private final aj f20100b = b();

    private ai() {
    }

    public static ai a() {
        return f20099a;
    }

    public aj.a a(Context context, Window window) {
        List<Rect> a2;
        aj.a aVar = new aj.a();
        aj ajVar = this.f20100b;
        if (ajVar != null && ajVar.a(context) && (a2 = this.f20100b.a(context, window)) != null && a2.size() > 0) {
            aVar.f20101a = true;
            aVar.f20103c = a2;
            if (window != null) {
                aVar.f20102b = this.f20100b.a(window);
            } else {
                aVar.f20102b = true;
            }
        }
        return aVar;
    }

    private aj b() {
        if (Build.VERSION.SDK_INT >= 28) {
            return new al();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (ap.a()) {
                return new am();
            }
            if (ap.d()) {
                return new ao();
            }
            if (ap.b()) {
                return new am();
            }
            if (ap.c()) {
                return new an();
            }
        }
        return null;
    }
}
