package com.bytedance.ttgame.sdk.module.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.os.BuildCompat;
import com.bytedance.apm.constant.a;
import com.bytedance.push.interfaze.ah;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import com.bytedance.ttgame.sdk.module.skin.SkinManager;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.ttnet.org.chromium.base.BaseSwitches;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* compiled from: NBDialog.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u001e\u0018\u00002\u00020\u00012\u00020\u0002:\u0001LB-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010@\u001a\u00020\bH\u0016J\u0006\u0010A\u001a\u00020\bJ\b\u0010B\u001a\u00020\bH\u0002J\u0006\u0010C\u001a\u00020\u0013J\b\u0010D\u001a\u00020\bH\u0016J\b\u0010E\u001a\u00020\bH\u0016J\u0012\u00104\u001a\u00020\b2\b\u0010F\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010G\u001a\u00020\b2\b\u0010H\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010I\u001a\u00020\b2\u0006\u0010J\u001a\u00020/H\u0016J\b\u0010K\u001a\u00020\bH\u0016R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R(\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u0010-\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R*\u00104\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010$\"\u0004\b6\u0010&R(\u00107\u001a\u0010\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010$\"\u0004\b9\u0010&R\"\u0010:\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00101\"\u0004\b<\u00103R\u001c\u0010=\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u001f\"\u0004\b?\u0010!¨\u0006M"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/NBDialog;", "Landroid/app/Dialog;", "Landroid/view/View$OnClickListener;", "ele", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", ViewHierarchyConstants.VIEW_KEY, "Lkotlin/Function1;", "Landroid/view/View;", "", RNConfig.ORIENTATION_KEY, "", "(Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "setBundle", "(Landroid/os/Bundle;)V", "element", "getElement", "()Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", "setElement", "(Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;)V", "nbScreenOrientation", "getNbScreenOrientation", "()Ljava/lang/String;", "setNbScreenOrientation", "(Ljava/lang/String;)V", "nbView", "getNbView", "()Lkotlin/jvm/functions/Function1;", "setNbView", "(Lkotlin/jvm/functions/Function1;)V", "onBackInvokedCallback", "Landroid/window/OnBackInvokedCallback;", "getOnBackInvokedCallback", "()Landroid/window/OnBackInvokedCallback;", "setOnBackInvokedCallback", "(Landroid/window/OnBackInvokedCallback;)V", "onBackPressed", "Lkotlin/Function0;", "", "getOnBackPressed", "()Lkotlin/jvm/functions/Function0;", "setOnBackPressed", "(Lkotlin/jvm/functions/Function0;)V", "onClick", "getOnClick", "setOnClick", "onDialogShow", "getOnDialogShow", "setOnDialogShow", "onDismiss", "getOnDismiss", "setOnDismiss", "tag", "getTag", "setTag", "dismiss", "dississNBDialog", "forceWidthHeight", "getArguments", "hide", "onAttachedToWindow", BaseSwitches.V, "onCreate", "savedInstanceState", a.ON_WINDOW_FOCUS_CHANGED, "hasFocus", ah.f11396a, "Builder", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class NBDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private Bundle bundle;
    private NBDialogElement element;
    private String nbScreenOrientation;
    private Function1<? super View, Unit> nbView;
    private OnBackInvokedCallback onBackInvokedCallback;
    private Function0<Boolean> onBackPressed;
    private Function1<? super View, Unit> onClick;
    private Function1<? super Boolean, Unit> onDialogShow;
    private Function0<Unit> onDismiss;
    private String tag;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NBDialog(com.bytedance.ttgame.sdk.module.ui.NBDialogElement r3, kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> r4, java.lang.String r5) {
        /*
            r2 = this;
            java.lang.String r0 = "ele"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            android.content.Context r0 = r3.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r1 = r3.getStyle()
            r2.<init>(r0, r1)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r2.bundle = r0
            r2.element = r3
            r2.nbView = r4
            android.os.Bundle r4 = r3.getBundle()
            r2.bundle = r4
            android.content.Context r4 = r3.getContext()
            android.app.Activity r4 = (android.app.Activity) r4
            r2.activity = r4
            java.lang.String r3 = r3.getTag()
            r2.tag = r3
            r2.nbScreenOrientation = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.sdk.module.ui.NBDialog.<init>(com.bytedance.ttgame.sdk.module.ui.NBDialogElement, kotlin.jvm.functions.Function1, java.lang.String):void");
    }

    public final Function1<Boolean, Unit> getOnDialogShow() {
        return this.onDialogShow;
    }

    public final void setOnDialogShow(Function1<? super Boolean, Unit> function1) {
        this.onDialogShow = function1;
    }

    public final Function1<View, Unit> getOnClick() {
        return this.onClick;
    }

    public final void setOnClick(Function1<? super View, Unit> function1) {
        this.onClick = function1;
    }

    public final Function0<Unit> getOnDismiss() {
        return this.onDismiss;
    }

    public final void setOnDismiss(Function0<Unit> function0) {
        this.onDismiss = function0;
    }

    public final Function0<Boolean> getOnBackPressed() {
        return this.onBackPressed;
    }

    public final void setOnBackPressed(Function0<Boolean> function0) {
        this.onBackPressed = function0;
    }

    public final Bundle getBundle() {
        return this.bundle;
    }

    public final void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final void setActivity(Activity activity) {
        this.activity = activity;
    }

    public final NBDialogElement getElement() {
        return this.element;
    }

    public final void setElement(NBDialogElement nBDialogElement) {
        Intrinsics.checkNotNullParameter(nBDialogElement, "<set-?>");
        this.element = nBDialogElement;
    }

    public final Function1<View, Unit> getNbView() {
        return this.nbView;
    }

    public final void setNbView(Function1<? super View, Unit> function1) {
        this.nbView = function1;
    }

    public final String getTag() {
        return this.tag;
    }

    public final void setTag(String str) {
        this.tag = str;
    }

    public final String getNbScreenOrientation() {
        return this.nbScreenOrientation;
    }

    public final void setNbScreenOrientation(String str) {
        this.nbScreenOrientation = str;
    }

    public final OnBackInvokedCallback getOnBackInvokedCallback() {
        return this.onBackInvokedCallback;
    }

    public final void setOnBackInvokedCallback(OnBackInvokedCallback onBackInvokedCallback) {
        this.onBackInvokedCallback = onBackInvokedCallback;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View rootView = LayoutInflater.from(getContext()).inflate(this.element.getLayout(), (ViewGroup) null);
        Function1<? super View, Unit> function1 = this.nbView;
        if (function1 != null) {
            Intrinsics.checkNotNullExpressionValue(rootView, "rootView");
            function1.invoke(rootView);
        }
        setContentView(rootView);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setWindowAnimations(R.style.gsdk_account_nbdialog_style);
        }
        SkinManager skinManager = SkinManager.INSTANCE;
        Activity activity = this.activity;
        Window window3 = getWindow();
        skinManager.registerDialogView(activity, window3 != null ? window3.getDecorView() : null, R.id.login_panel_background);
        Timber.tag("NBDialog").d("NBDialog(" + hashCode() + "):onCreate", new Object[0]);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.bytedance.ttgame.sdk.module.ui.NBDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                NBDialog.m284onCreate$lambda0(NBDialog.this, dialogInterface);
            }
        });
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.bytedance.ttgame.sdk.module.ui.NBDialog$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                NBDialog.m285onCreate$lambda1(NBDialog.this, dialogInterface);
            }
        });
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.bytedance.ttgame.sdk.module.ui.NBDialog$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                boolean m286onCreate$lambda2;
                m286onCreate$lambda2 = NBDialog.m286onCreate$lambda2(NBDialog.this, dialogInterface, i2, keyEvent);
                return m286onCreate$lambda2;
            }
        });
        setCanceledOnTouchOutside(false);
        forceWidthHeight();
        Window window4 = getWindow();
        if (window4 == null) {
            return;
        }
        window4.setFlags(16777216, 16777216);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m284onCreate$lambda0(NBDialog this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0<Unit> function0 = this$0.onDismiss;
        if (function0 != null) {
            function0.invoke();
        }
        Timber.tag("NBDialog").d("NBDialog(" + this$0.hashCode() + "):onDismiss", new Object[0]);
        NBDialogManagerKt.removeDialog(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m285onCreate$lambda1(NBDialog this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NBDialogManagerKt.addDialog$default(this$0, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final boolean m286onCreate$lambda2(NBDialog this$0, DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Function0<Boolean> function0;
        Boolean invoke;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i2 == 4 && keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0 && (function0 = this$0.onBackPressed) != null && (invoke = function0.invoke()) != null) {
            return invoke.booleanValue();
        }
        return false;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
            if (this.onBackInvokedCallback != null) {
                Timber.tag("NBDialog").d("onBackInvokedDispatcher:unregisterOnBackInvokedCallback", new Object[0]);
                OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
                OnBackInvokedCallback onBackInvokedCallback = this.onBackInvokedCallback;
                Intrinsics.checkNotNull(onBackInvokedCallback);
                onBackInvokedDispatcher.unregisterOnBackInvokedCallback(onBackInvokedCallback);
            }
        } catch (Exception e2) {
            Timber.tag("NBDialog").e("NBDialog(" + hashCode() + "):" + ((Object) e2.getLocalizedMessage()), new Object[0]);
        }
    }

    @Override // android.app.Dialog
    public void hide() {
        try {
            super.hide();
        } catch (Exception e2) {
            Timber.tag("NBDialog").e("NBDialog(" + hashCode() + "):" + ((Object) e2.getLocalizedMessage()), new Object[0]);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        View decorView;
        try {
            Window window = getWindow();
            if (window != null) {
                window.setFlags(8, 8);
            }
            super.show();
            NBDialogManagerKt.setFullScreen(getWindow());
            Window window2 = getWindow();
            if (window2 != null && (decorView = window2.getDecorView()) != null) {
                decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.bytedance.ttgame.sdk.module.ui.NBDialog$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public final void onSystemUiVisibilityChange(int i2) {
                        NBDialog.m287show$lambda3(NBDialog.this, i2);
                    }
                });
            }
            Window window3 = getWindow();
            if (window3 == null) {
                return;
            }
            window3.clearFlags(8);
        } catch (Exception e2) {
            Timber.tag("NBDialog").e("NBDialog(" + hashCode() + "):" + ((Object) e2.getLocalizedMessage()), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: show$lambda-3  reason: not valid java name */
    public static final void m287show$lambda3(NBDialog this$0, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NBDialogManagerKt.setFullScreen(this$0.getWindow());
    }

    public final void dississNBDialog() {
        try {
            dismiss();
            SkinManager skinManager = SkinManager.INSTANCE;
            Window window = getWindow();
            skinManager.unregisterDialogView(window == null ? null : window.getDecorView());
        } catch (Exception e2) {
            Timber.tag("NBDialog dississNBDialog").e(e2);
        }
    }

    public final Bundle getArguments() {
        Bundle bundle = this.bundle;
        return bundle == null ? new Bundle() : bundle;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        Function1<? super Boolean, Unit> function1 = this.onDialogShow;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(z));
        }
        Timber.tag("NBDialog").e("NBDialog(" + hashCode() + "):onWindowFocusChanged", new Object[0]);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            Timber.tag("NBDialog").d(Intrinsics.stringPlus("isAtLeastT:", Boolean.valueOf(BuildCompat.isAtLeastT())), new Object[0]);
            if (BuildCompat.isAtLeastT()) {
                this.onBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.bytedance.ttgame.sdk.module.ui.NBDialog$onAttachedToWindow$1
                    public void onBackInvoked() {
                        Timber.tag("NBDialog").d("onBackInvokedDispatcher:onBackInvoked", new Object[0]);
                        Function0<Boolean> onBackPressed = NBDialog.this.getOnBackPressed();
                        if (onBackPressed != null) {
                            onBackPressed.invoke();
                        }
                        NBDialog.this.dismiss();
                    }
                };
                OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
                OnBackInvokedCallback onBackInvokedCallback = this.onBackInvokedCallback;
                Intrinsics.checkNotNull(onBackInvokedCallback);
                onBackInvokedDispatcher.registerOnBackInvokedCallback(0, onBackInvokedCallback);
            }
        } catch (Exception e2) {
            Timber.tag("NBDialog").e(e2);
        }
    }

    /* compiled from: NBDialog.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010'\u001a\u00020(R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R(\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000fR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R(\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017¨\u0006)"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/NBDialog$Builder;", "", "()V", "element", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", "getElement", "()Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", "setElement", "(Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;)V", "onBackPressedBuilder", "Lkotlin/Function0;", "", "getOnBackPressedBuilder", "()Lkotlin/jvm/functions/Function0;", "setOnBackPressedBuilder", "(Lkotlin/jvm/functions/Function0;)V", "onClickBuilder", "Lkotlin/Function1;", "Landroid/view/View;", "", "getOnClickBuilder", "()Lkotlin/jvm/functions/Function1;", "setOnClickBuilder", "(Lkotlin/jvm/functions/Function1;)V", "onDialogShowBuilder", "getOnDialogShowBuilder", "setOnDialogShowBuilder", "onDismissBuilder", "getOnDismissBuilder", "setOnDismissBuilder", "screenOrientationBuilder", "", "getScreenOrientationBuilder", "()Ljava/lang/String;", "setScreenOrientationBuilder", "(Ljava/lang/String;)V", ViewHierarchyConstants.VIEW_KEY, "getView", "setView", "build", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialog;", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes8.dex */
    public static final class Builder {
        private NBDialogElement element;
        private Function0<Boolean> onBackPressedBuilder;
        private Function1<? super View, Unit> onClickBuilder;
        private Function1<? super Boolean, Unit> onDialogShowBuilder;
        private Function0<Unit> onDismissBuilder;
        private String screenOrientationBuilder;
        private Function1<? super View, Unit> view;

        public final NBDialogElement getElement() {
            return this.element;
        }

        public final void setElement(NBDialogElement nBDialogElement) {
            this.element = nBDialogElement;
        }

        public final Function1<View, Unit> getView() {
            return this.view;
        }

        public final void setView(Function1<? super View, Unit> function1) {
            this.view = function1;
        }

        public final Function1<Boolean, Unit> getOnDialogShowBuilder() {
            return this.onDialogShowBuilder;
        }

        public final void setOnDialogShowBuilder(Function1<? super Boolean, Unit> function1) {
            this.onDialogShowBuilder = function1;
        }

        public final Function1<View, Unit> getOnClickBuilder() {
            return this.onClickBuilder;
        }

        public final void setOnClickBuilder(Function1<? super View, Unit> function1) {
            this.onClickBuilder = function1;
        }

        public final Function0<Unit> getOnDismissBuilder() {
            return this.onDismissBuilder;
        }

        public final void setOnDismissBuilder(Function0<Unit> function0) {
            this.onDismissBuilder = function0;
        }

        public final Function0<Boolean> getOnBackPressedBuilder() {
            return this.onBackPressedBuilder;
        }

        public final void setOnBackPressedBuilder(Function0<Boolean> function0) {
            this.onBackPressedBuilder = function0;
        }

        public final String getScreenOrientationBuilder() {
            return this.screenOrientationBuilder;
        }

        public final void setScreenOrientationBuilder(String str) {
            this.screenOrientationBuilder = str;
        }

        public final NBDialog build() {
            NBDialogElement nBDialogElement = this.element;
            if (nBDialogElement != null) {
                NBDialog nBDialog = new NBDialog(nBDialogElement, this.view, this.screenOrientationBuilder);
                nBDialog.setOnDialogShow(getOnDialogShowBuilder());
                nBDialog.setOnClick(getOnClickBuilder());
                nBDialog.setOnDismiss(getOnDismissBuilder());
                nBDialog.setOnBackPressed(getOnBackPressedBuilder());
                NBDialogManagerKt.setScreenOrien(getScreenOrientationBuilder());
                return nBDialog;
            }
            throw new IllegalStateException("NBDialog element was null.".toString());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Function1<? super View, Unit> function1 = this.onClick;
        if (function1 == null) {
            return;
        }
        function1.invoke(view);
    }

    private final void forceWidthHeight() {
        if (this.element.getForceWidthHeight() && getContext().getResources().getConfiguration().orientation == 2) {
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window == null ? null : window.getAttributes();
            if (attributes != null) {
                attributes.height = getContext().getResources().getDimensionPixelSize(R.dimen.gsdk_base_layout_landscape_height);
            }
            if (attributes != null) {
                attributes.width = getContext().getResources().getDimensionPixelSize(R.dimen.gsdk_base_layout_landscape_width);
            }
            Window window2 = getWindow();
            if (window2 == null) {
                return;
            }
            window2.setAttributes(attributes);
        }
    }
}
