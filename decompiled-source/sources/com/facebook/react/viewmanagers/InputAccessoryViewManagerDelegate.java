package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.InputAccessoryViewManagerInterface;
/* loaded from: classes8.dex */
public class InputAccessoryViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & InputAccessoryViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public InputAccessoryViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals(ViewProps.BACKGROUND_COLOR)) {
            ((InputAccessoryViewManagerInterface) this.mViewManager).setBackgroundColor(t, obj == null ? null : Integer.valueOf(((Double) obj).intValue()));
        } else {
            super.setProperty(t, str, obj);
        }
    }
}
