package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.SafeAreaViewManagerInterface;
/* loaded from: classes8.dex */
public class SafeAreaViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SafeAreaViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SafeAreaViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals("emulateUnlessSupported")) {
            ((SafeAreaViewManagerInterface) this.mViewManager).setEmulateUnlessSupported(t, obj == null ? false : ((Boolean) obj).booleanValue());
        } else {
            super.setProperty(t, str, obj);
        }
    }
}
