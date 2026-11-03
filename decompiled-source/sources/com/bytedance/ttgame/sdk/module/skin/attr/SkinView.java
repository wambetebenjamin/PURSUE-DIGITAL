package com.bytedance.ttgame.sdk.module.skin.attr;

import android.view.View;
import java.util.List;
/* loaded from: classes8.dex */
public class SkinView {
    public List<SkinAttr> attrs;
    public View view;

    public SkinView(View view, List<SkinAttr> list) {
        this.view = view;
        this.attrs = list;
    }

    public void apply() {
        if (this.view == null) {
            return;
        }
        for (SkinAttr skinAttr : this.attrs) {
            skinAttr.apply(this.view);
        }
    }
}
