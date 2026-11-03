package com.bytedance.react.framework.utils.notchutil;

import android.content.Context;
import android.graphics.Rect;
import android.view.Window;
import java.util.List;
/* loaded from: classes2.dex */
public interface INotchScreen {

    /* loaded from: classes2.dex */
    public interface NotchScreenCallback {
        void onResult(NotchScreenInfo notchScreenInfo);
    }

    List<Rect> getNotchRect(Context context, Window window);

    boolean hasNotch(Context context);

    boolean isShowInNotch(Window window);

    void setDisplayInNotch(Window window);

    /* loaded from: classes2.dex */
    public static class NotchScreenInfo {
        public boolean hasNotch;
        public boolean hasShowInNotch;
        public List<Rect> notchRects;

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof NotchScreenInfo)) {
                NotchScreenInfo notchScreenInfo = (NotchScreenInfo) obj;
                return this.hasNotch == notchScreenInfo.hasNotch && this.hasShowInNotch == notchScreenInfo.hasShowInNotch && compareNotchRects(notchScreenInfo.notchRects);
            }
            return false;
        }

        public boolean compareNotchRects(List<Rect> list) {
            if (list == null && this.notchRects == null) {
                return true;
            }
            if (list == null || this.notchRects == null || list.size() <= 0 || this.notchRects.size() <= 0) {
                return false;
            }
            Rect rect = this.notchRects.get(0);
            Rect rect2 = list.get(0);
            return rect.top == rect2.top && rect.left == rect2.left && rect.bottom == rect2.bottom && rect.right == rect2.right;
        }
    }
}
