package com.bytedance.ttgame.sdk.module.account.login.ui.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.module.account.impl.R;
import com.bytedance.ttgame.sdk.module.ui.LifecycleDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\u000eR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/login/ui/dialog/PassRegisterDialog;", "Lcom/bytedance/ttgame/sdk/module/ui/LifecycleDialog;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "btnConfirm", "Landroid/widget/Button;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/sdk/module/account/login/ui/dialog/PassRegisterDialog$ICallback;", "cbAgree", "Landroid/widget/CheckBox;", "getRootResId", "", "setCallback", "", "showDialog", "ICallback", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PassRegisterDialog extends LifecycleDialog {

    /* renamed from: a  reason: collision with root package name */
    private CheckBox f14841a;

    /* renamed from: b  reason: collision with root package name */
    private Button f14842b;

    /* renamed from: c  reason: collision with root package name */
    private a f14843c;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/login/ui/dialog/PassRegisterDialog$ICallback;", "", "onResult", "", "isAgree", "", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface a {
        void a(boolean z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PassRegisterDialog(Activity activity) {
        super(activity, R.style.gsdk_base_dialog_style);
        Intrinsics.checkNotNullParameter(activity, "activity");
        View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_pass_register_dialog, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        setContentView(inflate);
        setContextViewForAnimation(inflate);
        this.f14841a = (CheckBox) inflate.findViewById(R.id.cb_agree);
        Button button = (Button) inflate.findViewById(R.id.btn_confirm);
        this.f14842b = button;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ttgame.sdk.module.account.login.ui.dialog.PassRegisterDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PassRegisterDialog.this.dismissLifecycleDialog();
                    a aVar = PassRegisterDialog.this.f14843c;
                    if (aVar != null) {
                        CheckBox checkBox = PassRegisterDialog.this.f14841a;
                        aVar.a(checkBox != null ? checkBox.isChecked() : true);
                    }
                }
            });
        }
    }

    public final void a() {
        showLifecycleDialog();
    }

    public final void a(a callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f14843c = callback;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.LifecycleDialog
    public int getRootResId() {
        return R.id.dialog_pass_register;
    }
}
