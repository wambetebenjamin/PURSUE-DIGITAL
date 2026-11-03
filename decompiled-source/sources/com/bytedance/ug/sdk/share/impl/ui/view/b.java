package com.bytedance.ug.sdk.share.impl.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.bytedance.share_ui.R;
import com.bytedance.ug.sdk.share.impl.ui.panel.SSDialog;
/* compiled from: DownloadProgressDialog.java */
/* loaded from: classes8.dex */
public class b extends SSDialog implements com.bytedance.ug.sdk.share.api.ui.b {

    /* renamed from: a  reason: collision with root package name */
    private DownloadProgressView f16167a;

    /* renamed from: b  reason: collision with root package name */
    private int f16168b;

    public b(Activity activity) {
        super(activity, R.style.share_sdk_token_dialog);
        this.f16168b = 0;
        setCancelable(com.bytedance.ug.sdk.share.impl.config.a.getInstance().k());
        setCanceledOnTouchOutside(com.bytedance.ug.sdk.share.impl.config.a.getInstance().l());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = View.inflate(getContext(), R.layout.share_sdk_download_progress_dlg, null);
        this.f16167a = (DownloadProgressView) inflate.findViewById(R.id.share_download_pv);
        a();
        setContentView(inflate);
    }

    @Override // com.bytedance.ug.sdk.share.api.ui.b
    public void setProgress(int i2) {
        if (this.f16168b != i2) {
            this.f16168b = i2;
        }
        a();
    }

    private void a() {
        if (isShowing()) {
            this.f16167a.setProgress(this.f16168b);
        }
    }
}
