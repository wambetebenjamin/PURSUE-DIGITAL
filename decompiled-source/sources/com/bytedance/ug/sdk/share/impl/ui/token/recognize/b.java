package com.bytedance.ug.sdk.share.impl.ui.token.recognize;

import android.app.Activity;
import com.bytedance.share_ui.R;
/* compiled from: ArticleNoPicTokenDialog.java */
/* loaded from: classes8.dex */
public class b extends a {
    @Override // com.bytedance.ug.sdk.share.impl.ui.token.recognize.a
    public void initActions() {
    }

    public b(Activity activity) {
        super(activity);
    }

    @Override // com.bytedance.ug.sdk.share.impl.ui.token.recognize.a
    public int getLayoutId() {
        return R.layout.share_sdk_article_nopic_login_token_dialog;
    }

    @Override // com.bytedance.ug.sdk.share.impl.ui.token.recognize.a
    public void initViews() {
        if (this.f16051c != null) {
            this.f16051c.setMaxLines(3);
        }
    }
}
