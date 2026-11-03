package com.facebook.react.viewmanagers;

import android.view.View;
import com.brentvatne.react.ReactVideoView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.ModalHostViewManagerInterface;
/* loaded from: classes8.dex */
public class ModalHostViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & ModalHostViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public ModalHostViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1851617609:
                if (str.equals("presentationStyle")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1850124175:
                if (str.equals("supportedOrientations")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1726194350:
                if (str.equals("transparent")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1618432855:
                if (str.equals(ReactVideoView.EVENT_PROP_METADATA_IDENTIFIER)) {
                    c2 = 3;
                    break;
                }
                break;
            case -1156137512:
                if (str.equals("statusBarTranslucent")) {
                    c2 = 4;
                    break;
                }
                break;
            case -795203165:
                if (str.equals("animated")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1195991583:
                if (str.equals("hardwareAccelerated")) {
                    c2 = 6;
                    break;
                }
                break;
            case 2031205598:
                if (str.equals("animationType")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ((ModalHostViewManagerInterface) this.mViewManager).setPresentationStyle(t, (String) obj);
                return;
            case 1:
                ((ModalHostViewManagerInterface) this.mViewManager).setSupportedOrientations(t, (ReadableArray) obj);
                return;
            case 2:
                ((ModalHostViewManagerInterface) this.mViewManager).setTransparent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 3:
                ((ModalHostViewManagerInterface) this.mViewManager).setIdentifier(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 4:
                ((ModalHostViewManagerInterface) this.mViewManager).setStatusBarTranslucent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 5:
                ((ModalHostViewManagerInterface) this.mViewManager).setAnimated(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 6:
                ((ModalHostViewManagerInterface) this.mViewManager).setHardwareAccelerated(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 7:
                ((ModalHostViewManagerInterface) this.mViewManager).setAnimationType(t, (String) obj);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
