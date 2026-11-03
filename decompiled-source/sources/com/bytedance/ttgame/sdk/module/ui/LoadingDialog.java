package com.bytedance.ttgame.sdk.module.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
/* loaded from: classes8.dex */
public class LoadingDialog extends LifecycleDialog {
    public LoadingDialog(Activity activity, int i2) {
        super(activity, i2);
        initView(activity);
    }

    private void initView(Context context) {
        setContentView(LayoutInflater.from(this.mActivity).inflate(R.layout.view_loading, (ViewGroup) null, false));
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.LifecycleDialog
    public int getRootResId() {
        return R.layout.view_loading;
    }

    public void onShow() {
        showLifecycleDialog();
    }

    public void onDismiss() {
        dismissLifecycleDialog();
    }
}
