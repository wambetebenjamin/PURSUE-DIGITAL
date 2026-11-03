package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
/* loaded from: classes10.dex */
final class P extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    protected Button f18866a;

    /* renamed from: b  reason: collision with root package name */
    protected EditText f18867b;

    /* renamed from: c  reason: collision with root package name */
    protected Context f18868c;

    /* renamed from: d  reason: collision with root package name */
    protected Rect f18869d;

    /* renamed from: e  reason: collision with root package name */
    protected Rect f18870e;

    public P(Context context, EditText editText) {
        super(context);
        this.f18867b = editText;
        this.f18868c = context;
        this.f18869d = new Rect(16, 16, 16, 16);
        this.f18870e = new Rect(0, 0, 0, 0);
        createUI();
        setBackgroundColor(-1);
    }

    protected void createUI() {
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Button button = new Button(this.f18868c);
        this.f18866a = button;
        button.setId(View.generateViewId());
        this.f18866a.setText(this.f18868c.getResources().getIdentifier("ok", "string", "android"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        this.f18866a.setLayoutParams(layoutParams);
        this.f18866a.setBackgroundColor(0);
        addView(this.f18866a);
        this.f18867b.setId(View.generateViewId());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(0, this.f18866a.getId());
        this.f18867b.setLayoutParams(layoutParams2);
        addView(this.f18867b);
        Rect rect = this.f18869d;
        setPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.f18866a.setOnClickListener(onClickListener);
    }
}
