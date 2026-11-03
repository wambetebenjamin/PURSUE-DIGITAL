package com.facebook.react.viewmanagers;

import android.view.View;
/* loaded from: classes8.dex */
public interface AndroidViewPagerManagerInterface<T extends View> {
    void setInitialPage(T t, int i2);

    void setKeyboardDismissMode(T t, String str);

    void setPage(T t, int i2);

    void setPageMargin(T t, int i2);

    void setPageWithoutAnimation(T t, int i2);

    void setPeekEnabled(T t, boolean z);

    void setScrollEnabled(T t, boolean z);
}
