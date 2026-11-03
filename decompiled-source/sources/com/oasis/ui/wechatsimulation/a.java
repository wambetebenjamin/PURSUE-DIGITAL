package com.oasis.ui.wechatsimulation;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.oasis.android.R;
/* loaded from: classes9.dex */
class a extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private final Button f17754a;

    /* renamed from: b  reason: collision with root package name */
    private final Button f17755b;

    /* renamed from: c  reason: collision with root package name */
    private final TextView f17756c;

    public a(Context context) {
        super(context, R.style.OasisTheme_WeChatSimulation_DialogStyle_TipsDialog);
        Window window = getWindow();
        window.requestFeature(1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.oasis_ui_wechat_simulation_tip_dialog, (ViewGroup) null);
        setContentView(inflate);
        setCanceledOnTouchOutside(false);
        this.f17756c = (TextView) inflate.findViewById(R.id.title);
        this.f17754a = (Button) inflate.findViewById(R.id.btn_left);
        this.f17755b = (Button) inflate.findViewById(R.id.btn_right);
    }

    public a a(View.OnClickListener onClickListener) {
        this.f17754a.setOnClickListener(onClickListener);
        return this;
    }

    public a a(String str) {
        this.f17756c.setText(str);
        return this;
    }

    public a a(String str, int i2) {
        this.f17754a.setText(str);
        this.f17754a.setTextColor(i2);
        return this;
    }

    public a b(View.OnClickListener onClickListener) {
        this.f17755b.setOnClickListener(onClickListener);
        return this;
    }

    public a b(String str, int i2) {
        this.f17755b.setText(str);
        this.f17755b.setTextColor(i2);
        return this;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
    }
}
