package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidViewPagerManagerInterface;
/* loaded from: classes8.dex */
public class AndroidViewPagerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidViewPagerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidViewPagerManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1151046732:
                if (str.equals("scrollEnabled")) {
                    c2 = 0;
                    break;
                }
                break;
            case -764307226:
                if (str.equals("keyboardDismissMode")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1097821469:
                if (str.equals("pageMargin")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1233251315:
                if (str.equals("initialPage")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1919780198:
                if (str.equals("peekEnabled")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((AndroidViewPagerManagerInterface) this.mViewManager).setScrollEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 1:
                ((AndroidViewPagerManagerInterface) this.mViewManager).setKeyboardDismissMode(t, (String) obj);
                return;
            case 2:
                ((AndroidViewPagerManagerInterface) this.mViewManager).setPageMargin(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 3:
                ((AndroidViewPagerManagerInterface) this.mViewManager).setInitialPage(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 4:
                ((AndroidViewPagerManagerInterface) this.mViewManager).setPeekEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }

    public void receiveCommand(AndroidViewPagerManagerInterface<T> androidViewPagerManagerInterface, T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setPageWithoutAnimation")) {
            androidViewPagerManagerInterface.setPageWithoutAnimation(t, readableArray.getInt(0));
        } else if (str.equals("setPage")) {
            androidViewPagerManagerInterface.setPage(t, readableArray.getInt(0));
        }
    }
}
