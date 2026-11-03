package com.bytedance.ug.sdk.share.impl.ui.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bytedance.share_ui.R;
import com.bytedance.ug.sdk.share.api.ui.e;
import com.bytedance.ug.sdk.share.impl.utils.j;
/* compiled from: ShareProgressDialog.java */
/* loaded from: classes8.dex */
public class c implements e {

    /* renamed from: a  reason: collision with root package name */
    private ProgressDialog f16169a;

    /* renamed from: b  reason: collision with root package name */
    private TextView f16170b;

    /* renamed from: c  reason: collision with root package name */
    private Activity f16171c;

    public c(Activity activity) {
        this.f16171c = activity;
    }

    @Override // com.bytedance.ug.sdk.share.api.ui.e
    public void show() {
        if (this.f16171c == null) {
            this.f16169a = null;
            return;
        }
        ProgressDialog progressDialog = this.f16169a;
        if (progressDialog == null || !progressDialog.isShowing()) {
            if (this.f16169a == null) {
                this.f16169a = new ProgressDialog(this.f16171c);
            }
            this.f16169a.setCanceledOnTouchOutside(false);
            this.f16169a.setCancelable(true);
            try {
                this.f16169a.show();
                this.f16169a.setContentView(R.layout.share_sdk_ss_progress_dialog);
                this.f16169a.getWindow().setBackgroundDrawable(this.f16171c.getResources().getDrawable(R.drawable.share_sdk_transparent));
                Resources resources = this.f16171c.getResources();
                View findViewById = this.f16169a.findViewById(R.id.container);
                this.f16170b = (TextView) this.f16169a.findViewById(R.id.loading);
                com.bytedance.ug.sdk.share.impl.ui.utils.b.setViewBackgroundWithPadding(findViewById, resources.getDrawable(R.drawable.share_sdk_bg_ss_progress_dialog));
                ((ProgressBar) this.f16169a.findViewById(R.id.progress)).setIndeterminateDrawable(new a(resources.getDrawable(R.drawable.share_sdk_icon_loading)));
                this.f16170b.setTextColor(resources.getColor(R.color.share_sdk_loading_text));
                this.f16170b.setText(R.string.share_sdk_token_loading_tips);
            } catch (Exception e2) {
                j.e(e2.toString());
            }
        }
    }

    @Override // com.bytedance.ug.sdk.share.api.ui.e
    public void dismiss() {
        ProgressDialog progressDialog = this.f16169a;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override // com.bytedance.ug.sdk.share.api.ui.e
    public boolean isShowing() {
        ProgressDialog progressDialog = this.f16169a;
        return progressDialog != null && progressDialog.isShowing();
    }
}
