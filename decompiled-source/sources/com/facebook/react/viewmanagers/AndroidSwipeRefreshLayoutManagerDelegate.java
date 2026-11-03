package com.facebook.react.viewmanagers;

import android.view.View;
import com.bytedance.apm.constant.i;
import com.bytedance.react.framework.LinearGradient.LinearGradientManager;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.AndroidSwipeRefreshLayoutManagerInterface;
/* loaded from: classes8.dex */
public class AndroidSwipeRefreshLayoutManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidSwipeRefreshLayoutManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidSwipeRefreshLayoutManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1609594047:
                if (str.equals(ViewProps.ENABLED)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1354842768:
                if (str.equals(LinearGradientManager.PROP_COLORS)) {
                    c2 = 1;
                    break;
                }
                break;
            case -885150488:
                if (str.equals("progressBackgroundColor")) {
                    c2 = 2;
                    break;
                }
                break;
            case -416037467:
                if (str.equals("progressViewOffset")) {
                    c2 = 3;
                    break;
                }
                break;
            case -321826009:
                if (str.equals("refreshing")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3530753:
                if (str.equals(i.PERF_DISK_FILE_SIZE)) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 1:
                ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setColors(t, (ReadableArray) obj);
                return;
            case 2:
                ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setProgressBackgroundColor(t, obj == null ? null : Integer.valueOf(((Double) obj).intValue()));
                return;
            case 3:
                ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setProgressViewOffset(t, obj == null ? 0.0f : ((Double) obj).floatValue());
                return;
            case 4:
                ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setRefreshing(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 5:
                ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setSize(t, obj != null ? ((Double) obj).intValue() : 1);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }

    public void receiveCommand(AndroidSwipeRefreshLayoutManagerInterface<T> androidSwipeRefreshLayoutManagerInterface, T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setNativeRefreshing")) {
            androidSwipeRefreshLayoutManagerInterface.setNativeRefreshing(t, readableArray.getBoolean(0));
        }
    }
}
