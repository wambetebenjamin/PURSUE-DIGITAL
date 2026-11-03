package gsdk.impl.dynamic.DEFAULT;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import java.util.List;
/* compiled from: INotchScreen.java */
/* loaded from: classes10.dex */
public interface aj {
    List<Rect> a(Context context, Window window);

    boolean a(Context context);

    boolean a(Window window);

    /* compiled from: INotchScreen.java */
    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f20101a;

        /* renamed from: b  reason: collision with root package name */
        public boolean f20102b;

        /* renamed from: c  reason: collision with root package name */
        public List<Rect> f20103c;

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof a)) {
                a aVar = (a) obj;
                return this.f20101a == aVar.f20101a && this.f20102b == aVar.f20102b && a(aVar.f20103c);
            }
            return false;
        }

        public boolean a(List<Rect> list) {
            if (list == null && this.f20103c == null) {
                return true;
            }
            if (list == null || this.f20103c == null || list.size() <= 0 || this.f20103c.size() <= 0) {
                return false;
            }
            Rect rect = this.f20103c.get(0);
            Rect rect2 = list.get(0);
            return rect.top == rect2.top && rect.left == rect2.left && rect.bottom == rect2.bottom && rect.right == rect2.right;
        }
    }
}
