package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.SwitchManagerInterface;
/* loaded from: classes8.dex */
public class SwitchManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SwitchManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SwitchManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1742453971:
                if (str.equals("thumbColor")) {
                    c2 = 0;
                    break;
                }
                break;
            case 111972721:
                if (str.equals("value")) {
                    c2 = 1;
                    break;
                }
                break;
            case 270940796:
                if (str.equals("disabled")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1084662482:
                if (str.equals("trackColorForFalse")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1296813577:
                if (str.equals("onTintColor")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1912319986:
                if (str.equals("thumbTintColor")) {
                    c2 = 6;
                    break;
                }
                break;
            case 2113632767:
                if (str.equals("trackColorForTrue")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((SwitchManagerInterface) this.mViewManager).setThumbColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 1:
                ((SwitchManagerInterface) this.mViewManager).setValue(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 2:
                ((SwitchManagerInterface) this.mViewManager).setDisabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 3:
                ((SwitchManagerInterface) this.mViewManager).setTrackColorForFalse(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 4:
                ((SwitchManagerInterface) this.mViewManager).setOnTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 5:
                ((SwitchManagerInterface) this.mViewManager).setTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 6:
                ((SwitchManagerInterface) this.mViewManager).setThumbTintColor(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            case 7:
                ((SwitchManagerInterface) this.mViewManager).setTrackColorForTrue(t, obj != null ? Integer.valueOf(((Double) obj).intValue()) : null);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
