package com.facebook.react.viewmanagers;

import android.view.View;
/* loaded from: classes8.dex */
public interface AndroidProgressBarManagerInterface<T extends View> {
    void setAnimating(T t, boolean z);

    void setColor(T t, Integer num);

    void setIndeterminate(T t, boolean z);

    void setProgress(T t, double d2);

    void setStyleAttr(T t, String str);

    void setTestID(T t, String str);

    void setTypeAttr(T t, String str);
}
