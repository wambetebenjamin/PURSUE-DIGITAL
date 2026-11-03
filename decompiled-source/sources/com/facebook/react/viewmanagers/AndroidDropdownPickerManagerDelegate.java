package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.AndroidDropdownPickerManagerInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes8.dex */
public class AndroidDropdownPickerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidDropdownPickerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidDropdownPickerManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
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
            case -979805852:
                if (str.equals("prompt")) {
                    c2 = 1;
                    break;
                }
                break;
            case 94842723:
                if (str.equals(ViewProps.COLOR)) {
                    c2 = 2;
                    break;
                }
                break;
            case 100526016:
                if (str.equals(FirebaseAnalytics.Param.ITEMS)) {
                    c2 = 3;
                    break;
                }
                break;
            case 1191572123:
                if (str.equals("selected")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((AndroidDropdownPickerManagerInterface) this.mViewManager).setEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 1:
                ((AndroidDropdownPickerManagerInterface) this.mViewManager).setPrompt(t, obj == null ? "" : (String) obj);
                return;
            case 2:
                ((AndroidDropdownPickerManagerInterface) this.mViewManager).setColor(t, obj == null ? null : Integer.valueOf(((Double) obj).intValue()));
                return;
            case 3:
                ((AndroidDropdownPickerManagerInterface) this.mViewManager).setItems(t, (ReadableArray) obj);
                return;
            case 4:
                ((AndroidDropdownPickerManagerInterface) this.mViewManager).setSelected(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
