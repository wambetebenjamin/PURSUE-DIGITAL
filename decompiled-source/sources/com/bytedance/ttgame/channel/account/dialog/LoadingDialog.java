package com.bytedance.ttgame.channel.account.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.bytedance.ttgame.account.R;
import com.bytedance.ttgame.module.account.api.Constant;
import com.bytedance.ttgame.module.account.api.LoginLogger;
/* loaded from: classes3.dex */
public class LoadingDialog extends Dialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private RelativeLayout mRelativeLayoutLoading;

    public LoadingDialog(Context context, int i2) {
        super(context, i2);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.view_loading, (ViewGroup) null);
        setContentView(inflate);
        this.mRelativeLayoutLoading = (RelativeLayout) inflate.findViewById(R.id.rl_progress_bar);
    }

    @Override // android.app.Dialog
    public void show() {
        try {
            try {
                getWindow().setFlags(8, 8);
                super.show();
                setFullScreen();
                getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.bytedance.ttgame.channel.account.dialog.LoadingDialog.1
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public void onSystemUiVisibilityChange(int i2) {
                        LoadingDialog.this.setFullScreen();
                    }
                });
            } catch (Exception e2) {
                LoginLogger.e(Constant.TAG, "LoadingDialog: " + e2.getMessage());
            }
        } finally {
            getWindow().clearFlags(8);
        }
    }

    public void setFullScreen() {
        getWindow().setFlags(1024, 1024);
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.mRelativeLayoutLoading.setVisibility(8);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        this.mRelativeLayoutLoading.setVisibility(0);
    }
}
