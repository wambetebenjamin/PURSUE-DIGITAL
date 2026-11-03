package com.bytedance.ttgame.framework.module.util;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bytedance.ttgame.gsdk_base_utils.R;
import com.vk.api.sdk.exceptions.VKApiCodes;
/* loaded from: classes3.dex */
public class GToast {
    public static final int LONG_TIME = 2000;
    public static final int SHORT_TIME = 1500;
    private Context context;
    private Dialog dialog;
    private int mDuration;
    private View rootView;

    public static GToast newBuilder(Context context, String str) {
        return new GToast(context, str, null);
    }

    public static GToast newBuilder(Context context, View view) {
        return new GToast(context, null, view);
    }

    private GToast(Context context, String str, View view) {
        this.context = context;
        init(str, view);
    }

    private void init(String str, View view) {
        this.dialog = new Dialog(this.context, R.style.GToastDialogStyle);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.layout_g_toast, (ViewGroup) null);
        this.rootView = inflate;
        this.dialog.setContentView(inflate);
        this.dialog.getWindow().setGravity(17);
        this.dialog.getWindow().getAttributes().windowAnimations = R.style.GToastWindowAnim;
        FrameLayout frameLayout = (FrameLayout) this.rootView.findViewById(R.id.fl_root_view);
        if (view != null) {
            frameLayout.removeAllViews();
            frameLayout.addView(view);
            return;
        }
        ((TextView) this.rootView.findViewById(R.id.tv_content)).setText(str);
    }

    public GToast showToast() {
        return showToast(1);
    }

    public GToast showToast(int i2) {
        show(i2);
        return this;
    }

    private void show(int i2) {
        try {
            this.mDuration = i2;
            Dialog dialog = this.dialog;
            if (dialog == null || dialog.isShowing()) {
                return;
            }
            try {
                this.dialog.getWindow().setFlags(8, 8);
                this.dialog.show();
                setFullScreen();
                this.dialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.bytedance.ttgame.framework.module.util.GToast.1
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public void onSystemUiVisibilityChange(int i3) {
                        GToast.this.setFullScreen();
                    }
                });
                this.dialog.getWindow().clearFlags(8);
            } catch (Exception e2) {
                Log.e("GToast", "GToast(" + hashCode() + "):" + e2.getLocalizedMessage());
            }
            viewAnim(true);
            new Handler().postDelayed(new Runnable() { // from class: com.bytedance.ttgame.framework.module.util.GToast.2
                @Override // java.lang.Runnable
                public void run() {
                    GToast.this.viewAnim(false);
                    new Handler().postDelayed(new Runnable() { // from class: com.bytedance.ttgame.framework.module.util.GToast.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (GToast.this.dialog != null) {
                                try {
                                    GToast.this.dialog.dismiss();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    }, 500L);
                }
            }, this.mDuration == 0 ? 1500 : 2000);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFullScreen() {
        this.dialog.getWindow().setFlags(1024, 1024);
        this.dialog.getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewAnim(final boolean z) {
        new Handler().postDelayed(new Runnable() { // from class: com.bytedance.ttgame.framework.module.util.GToast.3
            @Override // java.lang.Runnable
            public void run() {
                int i2 = z ? 250 : VKApiCodes.CODE_INVALID_TIMESTAMP;
                View view = GToast.this.rootView;
                float[] fArr = new float[2];
                boolean z2 = z;
                fArr[0] = z2 ? 0.0f : 1.0f;
                fArr[1] = z2 ? 1.0f : 0.0f;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", fArr);
                ofFloat.setDuration(i2);
                ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                ofFloat.start();
            }
        }, 5L);
    }
}
