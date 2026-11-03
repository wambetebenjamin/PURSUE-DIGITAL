package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.SegmentedControlManagerInterface;
/* loaded from: classes8.dex */
public class SegmentedControlManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SegmentedControlManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SegmentedControlManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
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
            case -823812830:
                if (str.equals("values")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1436069623:
                if (str.equals("selectedIndex")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1684715624:
                if (str.equals("momentary")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((SegmentedControlManagerInterface) this.mViewManager).setEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 1:
                ((SegmentedControlManagerInterface) this.mViewManager).setValues(t, (ReadableArray) obj);
                return;
            case 2:
                ((SegmentedControlManagerInterface) this.mViewManager).setTintColor(t, obj == null ? null : Integer.valueOf(((Double) obj).intValue()));
                return;
            case 3:
                ((SegmentedControlManagerInterface) this.mViewManager).setSelectedIndex(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 4:
                ((SegmentedControlManagerInterface) this.mViewManager).setMomentary(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
