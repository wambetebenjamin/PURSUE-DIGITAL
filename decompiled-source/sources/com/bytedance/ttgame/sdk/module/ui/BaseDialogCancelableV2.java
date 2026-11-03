package com.bytedance.ttgame.sdk.module.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.push.interfaze.ah;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseDialogCancelableV2.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u0006H\u0016J \u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J4\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/BaseDialogCancelableV2;", "Lcom/bytedance/ttgame/sdk/module/ui/LifecycleDialog;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "themeResId", "", "(Landroid/app/Activity;I)V", "btnCancel", "Landroid/widget/Button;", "btnOk", "tvContent", "Landroid/widget/TextView;", "getRootResId", ah.f11396a, "", "content", "", "okListener", "Lcom/bytedance/ttgame/sdk/module/ui/OnClickListener;", "cancelListener", "okText", "cancelText", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class BaseDialogCancelableV2 extends LifecycleDialog {
    private Button btnCancel;
    private Button btnOk;
    private TextView tvContent;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseDialogCancelableV2(Activity activity) {
        this(activity, R.style.gsdk_base_dialog_style);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDialogCancelableV2(Activity activity, int i2) {
        super(activity, i2);
        Intrinsics.checkNotNullParameter(activity, "activity");
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.gsdk_base_dialog_cancelable_v2, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        View findViewById = inflate.findViewById(R.id.tv_content);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.tv_content)");
        this.tvContent = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.btn_confirm);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.btn_confirm)");
        this.btnOk = (Button) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.btn_cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.btn_cancel)");
        this.btnCancel = (Button) findViewById3;
        setContentView(inflate);
        setContextViewForAnimation(inflate);
    }

    public final void show(String str, final OnClickListener okListener, final OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        TextView textView = this.tvContent;
        Button button = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvContent");
            textView = null;
        }
        textView.setText(str);
        Button button2 = this.btnOk;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnOk");
            button2 = null;
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ttgame.sdk.module.ui.BaseDialogCancelableV2$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseDialogCancelableV2.m273show$lambda0(BaseDialogCancelableV2.this, okListener, view);
            }
        });
        Button button3 = this.btnCancel;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnCancel");
        } else {
            button = button3;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ttgame.sdk.module.ui.BaseDialogCancelableV2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseDialogCancelableV2.m274show$lambda1(BaseDialogCancelableV2.this, cancelListener, view);
            }
        });
        showLifecycleDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-0  reason: not valid java name */
    public static final void m273show$lambda0(BaseDialogCancelableV2 this$0, OnClickListener okListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(okListener, "$okListener");
        this$0.dismissLifecycleDialog();
        okListener.onClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-1  reason: not valid java name */
    public static final void m274show$lambda1(BaseDialogCancelableV2 this$0, OnClickListener cancelListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cancelListener, "$cancelListener");
        this$0.dismissLifecycleDialog();
        cancelListener.onClicked();
    }

    public final void show(String str, String str2, String str3, OnClickListener okListener, OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        Button button = null;
        if (str2 != null) {
            Button button2 = this.btnOk;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnOk");
                button2 = null;
            }
            button2.setText(str2);
        }
        if (str3 != null) {
            Button button3 = this.btnCancel;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnCancel");
            } else {
                button = button3;
            }
            button.setText(str3);
        }
        show(str, okListener, cancelListener);
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.LifecycleDialog
    public int getRootResId() {
        return R.id.gsdk_base_dialog_background;
    }
}
