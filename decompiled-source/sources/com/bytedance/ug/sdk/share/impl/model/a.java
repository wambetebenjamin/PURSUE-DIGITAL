package com.bytedance.ug.sdk.share.impl.model;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/* compiled from: BaseShareItem.java */
/* loaded from: classes8.dex */
public abstract class a implements com.bytedance.ug.sdk.share.api.panel.a {

    /* renamed from: a  reason: collision with root package name */
    protected String f15834a;

    /* renamed from: b  reason: collision with root package name */
    protected int f15835b;

    public void setItemTextStr(String str) {
        this.f15834a = str;
    }

    public void setItemIconId(int i2) {
        this.f15835b = i2;
    }

    @Override // com.bytedance.ug.sdk.share.api.panel.a
    public void setItemView(final View view, ImageView imageView, TextView textView) {
        if (view == null) {
            return;
        }
        final float alpha = view.getAlpha();
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.bytedance.ug.sdk.share.impl.model.a.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    view.setAlpha(alpha * 0.5f);
                    return false;
                } else if (action == 1 || action == 3) {
                    view.setAlpha(alpha);
                    return false;
                } else {
                    return false;
                }
            }
        });
    }
}
