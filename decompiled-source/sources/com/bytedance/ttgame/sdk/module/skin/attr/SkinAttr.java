package com.bytedance.ttgame.sdk.module.skin.attr;

import android.view.View;
/* loaded from: classes8.dex */
public class SkinAttr {
    public SkinAttrType attrType;
    public String resName;

    public SkinAttr(SkinAttrType skinAttrType, String str) {
        this.resName = str;
        this.attrType = skinAttrType;
    }

    public void apply(View view) {
        this.attrType.apply(view, this.resName);
    }
}
