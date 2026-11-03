package com.bytedance.ttgame.sdk.module.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.push.interfaze.ah;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* compiled from: BaseDialogCancelable.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0014\u001a\u00020\u0007H\u0016J \u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ(\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ*\u0010\u0015\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ2\u0010\u0015\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ4\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ<\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJK\u0010\u0015\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u001fJ>\u0010\u0015\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/AbstractBaseDialogCancelable;", "Lcom/bytedance/ttgame/sdk/module/ui/LifecycleDialog;", "activity", "Landroid/app/Activity;", "isWarning", "", "themeResId", "", "(Landroid/app/Activity;ZI)V", "TAG", "", "btnCancel", "Landroid/widget/Button;", "btnOk", "cancelListener", "Lcom/bytedance/ttgame/sdk/module/ui/OnClickListener;", "tvAdditionalContent", "Landroid/widget/TextView;", "tvCdid", "tvContent", "getRootResId", ah.f11396a, "", "content", "okListener", "isNeedShowCdid", "title", "okText", "cancelText", "richText", "richColor", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ZLcom/bytedance/ttgame/sdk/module/ui/OnClickListener;Lcom/bytedance/ttgame/sdk/module/ui/OnClickListener;)V", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public abstract class AbstractBaseDialogCancelable extends LifecycleDialog {
    private final String TAG;
    private Button btnCancel;
    private Button btnOk;
    private OnClickListener cancelListener;
    private TextView tvAdditionalContent;
    private TextView tvCdid;
    private TextView tvContent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractBaseDialogCancelable(Activity activity, boolean z, int i2) {
        super(activity, i2);
        String cDid;
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.TAG = "BaseDialogCancelable";
        TextView textView = null;
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.gsdk_base_dialog_cancelable, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        View findViewById = inflate.findViewById(R.id.tv_content);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.tv_content)");
        this.tvContent = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.tv_additional_content);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.tv_additional_content)");
        this.tvAdditionalContent = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.btn_ok);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.btn_ok)");
        Button button = (Button) findViewById3;
        this.btnOk = button;
        if (z) {
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnOk");
                button = null;
            }
            button.setBackground(activity.getResources().getDrawable(R.drawable.selector_button_ok_warning));
        }
        View findViewById4 = inflate.findViewById(R.id.btn_cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.btn_cancel)");
        this.btnCancel = (Button) findViewById4;
        View findViewById5 = inflate.findViewById(R.id.tv_cdid);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.tv_cdid)");
        this.tvCdid = (TextView) findViewById5;
        IMainInternalService iMainInternalService = (IMainInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        TextView textView2 = this.tvCdid;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvCdid");
        } else {
            textView = textView2;
        }
        textView.setText((iMainInternalService == null || (cDid = iMainInternalService.getCDid(iMainInternalService.getAppContext())) == null) ? "" : cDid);
        setContentView(inflate);
        setContextViewForAnimation(inflate);
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.bytedance.ttgame.sdk.module.ui.AbstractBaseDialogCancelable$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                AbstractBaseDialogCancelable.m260_init_$lambda0(AbstractBaseDialogCancelable.this, dialogInterface);
            }
        });
    }

    public /* synthetic */ AbstractBaseDialogCancelable(Activity activity, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i3 & 2) != 0 ? false : z, (i3 & 4) != 0 ? R.style.gsdk_base_dialog_style : i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m260_init_$lambda0(AbstractBaseDialogCancelable this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Timber.tag(this$0.TAG).d("setOnCancelListener onCancel", new Object[0]);
        OnClickListener onClickListener = this$0.cancelListener;
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClicked();
    }

    public final void show(String str, OnClickListener okListener, OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        show(str, false, okListener, cancelListener);
    }

    public final void show(String str, boolean z, final OnClickListener okListener, final OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        this.cancelListener = cancelListener;
        TextView textView = this.tvContent;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvContent");
            textView = null;
        }
        textView.setText(str);
        Button button = this.btnOk;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnOk");
            button = null;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ttgame.sdk.module.ui.AbstractBaseDialogCancelable$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AbstractBaseDialogCancelable.m261show$lambda1(AbstractBaseDialogCancelable.this, okListener, view);
            }
        });
        Button button2 = this.btnCancel;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnCancel");
            button2 = null;
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.ttgame.sdk.module.ui.AbstractBaseDialogCancelable$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AbstractBaseDialogCancelable.m262show$lambda2(AbstractBaseDialogCancelable.this, cancelListener, view);
            }
        });
        if (z) {
            TextView textView3 = this.tvCdid;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvCdid");
            } else {
                textView2 = textView3;
            }
            textView2.setVisibility(0);
        } else {
            TextView textView4 = this.tvCdid;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tvCdid");
            } else {
                textView2 = textView4;
            }
            textView2.setVisibility(8);
        }
        showLifecycleDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-1  reason: not valid java name */
    public static final void m261show$lambda1(AbstractBaseDialogCancelable this$0, OnClickListener okListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(okListener, "$okListener");
        this$0.dismissLifecycleDialog();
        okListener.onClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-2  reason: not valid java name */
    public static final void m262show$lambda2(AbstractBaseDialogCancelable this$0, OnClickListener cancelListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cancelListener, "$cancelListener");
        this$0.dismissLifecycleDialog();
        cancelListener.onClicked();
    }

    public final void show(String str, String str2, OnClickListener okListener, OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        show(str, str2, false, okListener, cancelListener);
    }

    public final void show(String str, String str2, boolean z, OnClickListener okListener, OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        show(str, str2, "", null, z, okListener, cancelListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void show(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.Integer r21, boolean r22, final com.bytedance.ttgame.sdk.module.ui.OnClickListener r23, final com.bytedance.ttgame.sdk.module.ui.OnClickListener r24) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.sdk.module.ui.AbstractBaseDialogCancelable.show(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, boolean, com.bytedance.ttgame.sdk.module.ui.OnClickListener, com.bytedance.ttgame.sdk.module.ui.OnClickListener):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-4  reason: not valid java name */
    public static final void m263show$lambda4(AbstractBaseDialogCancelable this$0, OnClickListener okListener, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(okListener, "$okListener");
        this$0.dismissLifecycleDialog();
        okListener.onClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-5  reason: not valid java name */
    public static final void m264show$lambda5(AbstractBaseDialogCancelable this$0, OnClickListener cancelListener, View view) {
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

    public final void show(String str, String str2, String str3, String str4, OnClickListener okListener, OnClickListener cancelListener) {
        Intrinsics.checkNotNullParameter(okListener, "okListener");
        Intrinsics.checkNotNullParameter(cancelListener, "cancelListener");
        Button button = null;
        if (str3 != null) {
            Button button2 = this.btnOk;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnOk");
                button2 = null;
            }
            button2.setText(str3);
        }
        if (str4 != null) {
            Button button3 = this.btnCancel;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btnCancel");
            } else {
                button = button3;
            }
            button.setText(str4);
        }
        show(str, str2, okListener, cancelListener);
    }

    public final void show(String str, String str2, String str3, boolean z, OnClickListener okListener, OnClickListener cancelListener) {
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
        show(str, z, okListener, cancelListener);
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.LifecycleDialog
    public int getRootResId() {
        return R.id.gsdk_base_dialog_background;
    }
}
