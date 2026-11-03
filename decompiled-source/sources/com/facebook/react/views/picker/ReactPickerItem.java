package com.facebook.react.views.picker;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
class ReactPickerItem {
    public final Integer color;
    public final String label;

    public ReactPickerItem(ReadableMap readableMap) {
        this.label = readableMap.getString("label");
        if (readableMap.hasKey(ViewProps.COLOR) && !readableMap.isNull(ViewProps.COLOR)) {
            this.color = Integer.valueOf(readableMap.getInt(ViewProps.COLOR));
        } else {
            this.color = null;
        }
    }

    public static List<ReactPickerItem> createFromJsArrayMap(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            arrayList.add(new ReactPickerItem(readableArray.getMap(i2)));
        }
        return arrayList;
    }
}
