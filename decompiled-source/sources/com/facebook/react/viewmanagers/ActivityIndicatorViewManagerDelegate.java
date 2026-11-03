package com.facebook.react.viewmanagers;

import android.view.View;
import com.bytedance.apm.constant.i;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.ActivityIndicatorViewManagerInterface;
/* loaded from: classes8.dex */
public class ActivityIndicatorViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & ActivityIndicatorViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public ActivityIndicatorViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case 3530753:
                if (str.equals(i.PERF_DISK_FILE_SIZE)) {
                    c2 = 0;
                    break;
                }
                break;
            case 94842723:
                if (str.equals(ViewProps.COLOR)) {
                    c2 = 1;
                    break;
                }
                break;
            case 865748226:
                if (str.equals("hidesWhenStopped")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1118509918:
                if (str.equals("animating")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((ActivityIndicatorViewManagerInterface) this.mViewManager).setSize(t, (String) obj);
                return;
            case 1:
                ((ActivityIndicatorViewManagerInterface) this.mViewManager).setColor(t, obj == null ? null : Integer.valueOf(((Double) obj).intValue()));
                return;
            case 2:
                ((ActivityIndicatorViewManagerInterface) this.mViewManager).setHidesWhenStopped(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 3:
                ((ActivityIndicatorViewManagerInterface) this.mViewManager).setAnimating(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
