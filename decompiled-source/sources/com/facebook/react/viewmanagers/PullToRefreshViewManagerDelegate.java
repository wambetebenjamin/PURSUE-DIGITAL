package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.PullToRefreshViewManagerInterface;
/* loaded from: classes8.dex */
public class PullToRefreshViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & PullToRefreshViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public PullToRefreshViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1799367701:
                if (str.equals("titleColor")) {
                    c2 = 0;
                    break;
                }
                break;
            case -321826009:
                if (str.equals("refreshing")) {
                    c2 = 1;
                    break;
                }
                break;
            case 110371416:
                if (str.equals("title")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((PullToRefreshViewManagerInterface) this.mViewManager).setTitleColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 1:
                ((PullToRefreshViewManagerInterface) this.mViewManager).setRefreshing(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 2:
                ((PullToRefreshViewManagerInterface) this.mViewManager).setTitle(t, obj != null ? (String) obj : null);
                return;
            case 3:
                ((PullToRefreshViewManagerInterface) this.mViewManager).setTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }

    public void receiveCommand(PullToRefreshViewManagerInterface<T> pullToRefreshViewManagerInterface, T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setNativeRefreshing")) {
            pullToRefreshViewManagerInterface.setNativeRefreshing(t, readableArray.getBoolean(0));
        }
    }
}
