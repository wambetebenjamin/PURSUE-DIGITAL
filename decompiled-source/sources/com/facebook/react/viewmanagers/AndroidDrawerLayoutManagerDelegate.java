package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface;
/* loaded from: classes8.dex */
public class AndroidDrawerLayoutManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidDrawerLayoutManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidDrawerLayoutManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2082382380:
                if (str.equals("statusBarBackgroundColor")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1233873500:
                if (str.equals("drawerBackgroundColor")) {
                    c2 = 1;
                    break;
                }
                break;
            case -764307226:
                if (str.equals("keyboardDismissMode")) {
                    c2 = 2;
                    break;
                }
                break;
            case 268251989:
                if (str.equals("drawerWidth")) {
                    c2 = 3;
                    break;
                }
                break;
            case 695891258:
                if (str.equals("drawerPosition")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1857208703:
                if (str.equals("drawerLockMode")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setStatusBarBackgroundColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 1:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerBackgroundColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 2:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setKeyboardDismissMode(t, (String) obj);
                return;
            case 3:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerWidth(t, obj != null ? Float.valueOf(((Double) obj).floatValue()) : null);
                return;
            case 4:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerPosition(t, (String) obj);
                return;
            case 5:
                ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerLockMode(t, (String) obj);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }

    public void receiveCommand(AndroidDrawerLayoutManagerInterface<T> androidDrawerLayoutManagerInterface, T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("closeDrawer")) {
            androidDrawerLayoutManagerInterface.closeDrawer(t);
        } else if (str.equals("openDrawer")) {
            androidDrawerLayoutManagerInterface.openDrawer(t);
        }
    }
}
