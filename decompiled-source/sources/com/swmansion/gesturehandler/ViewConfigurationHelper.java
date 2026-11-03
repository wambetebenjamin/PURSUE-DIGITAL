package com.swmansion.gesturehandler;

import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes9.dex */
public interface ViewConfigurationHelper {
    View getChildInDrawingOrderAtIndex(ViewGroup viewGroup, int i2);

    PointerEventsConfig getPointerEventsConfigForView(View view);

    boolean isViewClippingChildren(ViewGroup viewGroup);
}
