package com.bytedance.ttgame.sdk.module.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import com.bytedance.ttgame.module.common.impl.R;
/* loaded from: classes8.dex */
public class LoadingView extends Dialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private RelativeLayout mRelativeLayoutLoading;

    public LoadingView(Context context, int i2) {
        super(context, i2);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.view_loading, (ViewGroup) null);
        setContentView(inflate);
        this.mRelativeLayoutLoading = (RelativeLayout) inflate.findViewById(R.id.rl_progress_bar);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.mRelativeLayoutLoading.setVisibility(8);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        this.mRelativeLayoutLoading.setVisibility(0);
    }

    @Override // android.app.Dialog
    public void show() {
        try {
            getWindow().setFlags(8, 8);
            super.show();
            setFullScreen(getWindow());
            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.bytedance.ttgame.sdk.module.ui.widget.LoadingView.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i2) {
                    LoadingView loadingView = LoadingView.this;
                    loadingView.setFullScreen(loadingView.getWindow());
                }
            });
            getWindow().clearFlags(8);
        } catch (Exception e2) {
            Log.e("LoadingView", "LoadingView(" + hashCode() + "):" + e2.getLocalizedMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFullScreen(Window window) {
        window.setFlags(1024, 1024);
        window.getDecorView().setSystemUiVisibility(5894);
    }
}
