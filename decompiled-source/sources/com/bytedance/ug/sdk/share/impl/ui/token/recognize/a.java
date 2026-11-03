package com.bytedance.ug.sdk.share.impl.ui.token.recognize;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.share_ui.R;
import com.bytedance.ug.sdk.share.api.entity.f;
import com.bytedance.ug.sdk.share.api.entity.q;
import com.bytedance.ug.sdk.share.api.ui.d;
import com.bytedance.ug.sdk.share.impl.ui.panel.SSDialog;
/* compiled from: AbsTokenDialog.java */
/* loaded from: classes8.dex */
public abstract class a extends SSDialog implements com.bytedance.ug.sdk.share.api.ui.d {

    /* renamed from: a  reason: collision with root package name */
    protected final String f16049a;

    /* renamed from: b  reason: collision with root package name */
    protected Activity f16050b;

    /* renamed from: c  reason: collision with root package name */
    protected TextView f16051c;

    /* renamed from: d  reason: collision with root package name */
    protected q f16052d;

    /* renamed from: e  reason: collision with root package name */
    protected Dialog f16053e;

    /* renamed from: f  reason: collision with root package name */
    private ImageView f16054f;

    /* renamed from: g  reason: collision with root package name */
    private Button f16055g;

    /* renamed from: h  reason: collision with root package name */
    private d.a f16056h;

    public abstract int getLayoutId();

    public abstract void initActions();

    public abstract void initViews();

    public a(Activity activity) {
        super(activity, R.style.share_sdk_token_dialog);
        this.f16049a = "此分享来自";
        this.f16050b = activity;
        this.f16053e = this;
    }

    @Override // com.bytedance.ug.sdk.share.api.ui.d
    public void initTokenDialog(q qVar, d.a aVar) {
        this.f16052d = qVar;
        this.f16056h = aVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        window.setLayout(-1, -2);
        window.setGravity(17);
        this.f16051c = (TextView) findViewById(R.id.title);
        this.f16054f = (ImageView) findViewById(R.id.close_icon);
        this.f16055g = (Button) findViewById(R.id.token_btn);
        if (!TextUtils.isEmpty(this.f16052d.getButtonText())) {
            this.f16055g.setText(this.f16052d.getButtonText());
        }
        this.f16051c.setText(this.f16052d.getTitle());
        this.f16054f.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ug.sdk.share.impl.ui.token.recognize.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.a();
            }
        });
        this.f16055g.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ug.sdk.share.impl.ui.token.recognize.a.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.b();
            }
        });
        ((GradientDrawable) this.f16055g.getBackground()).setColor(com.bytedance.ug.sdk.share.impl.config.a.getInstance().m());
        this.f16055g.setTextColor(com.bytedance.ug.sdk.share.impl.config.a.getInstance().n());
        this.f16051c.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ug.sdk.share.impl.ui.token.recognize.a.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.b();
            }
        });
        c();
        initViews();
        initActions();
    }

    protected void a() {
        d.a aVar = this.f16056h;
        if (aVar != null) {
            aVar.onClick(true, f.CLICK_TYPE_CLOSE, this.f16052d);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        d.a aVar = this.f16056h;
        if (aVar != null) {
            aVar.onClick(true, f.CLICK_TYPE_DETAIL, this.f16052d);
        }
    }

    protected void c() {
        TextView textView = (TextView) findViewById(R.id.share_user_info);
        View findViewById = findViewById(R.id.share_user_info_layout);
        if (this.f16052d.getShareUserInfo() != null && !TextUtils.isEmpty(this.f16052d.getShareUserInfo().getName())) {
            com.bytedance.ug.sdk.share.impl.ui.utils.b.setViewVisibility(textView, 0);
            textView.setText("此分享来自" + this.f16052d.getShareUserInfo().getName());
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ug.sdk.share.impl.ui.token.recognize.a.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (a.this.f16056h != null) {
                        a.this.f16056h.onClick(true, f.CLICK_TYPE_USER_DETAIL, a.this.f16052d);
                    }
                }
            });
            return;
        }
        com.bytedance.ug.sdk.share.impl.ui.utils.b.setViewVisibility(findViewById, 8);
    }

    @Override // com.bytedance.ug.sdk.share.impl.ui.panel.SSDialog, android.app.Dialog, android.content.DialogInterface, com.bytedance.ug.sdk.share.impl.ui.panel.c
    public void dismiss() {
        super.dismiss();
        d.a aVar = this.f16056h;
        if (aVar != null) {
            aVar.onDismiss();
        }
    }
}
