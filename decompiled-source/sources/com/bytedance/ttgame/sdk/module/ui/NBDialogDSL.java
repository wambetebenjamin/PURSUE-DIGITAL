package com.bytedance.ttgame.sdk.module.ui;

import android.view.View;
import com.bytedance.react.framework.RNConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
/* compiled from: NBDialogDSL.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J!\u0010 \u001a\u00020\f2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b#H&R \u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR(\u0010\t\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0018\u00010\nX¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R \u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR&\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001bX¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/NBDialogDSL;", "", "onBackPressed", "Lkotlin/Function0;", "", "getOnBackPressed", "()Lkotlin/jvm/functions/Function0;", "setOnBackPressed", "(Lkotlin/jvm/functions/Function0;)V", "onClick", "Lkotlin/Function1;", "Landroid/view/View;", "", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "setOnClick", "(Lkotlin/jvm/functions/Function1;)V", "onDialogShow", "getOnDialogShow", "setOnDialogShow", "onDismiss", "getOnDismiss", "setOnDismiss", "onViewShow", "getOnViewShow", "setOnViewShow", RNConfig.ORIENTATION_KEY, "", "getScreenOrientation", "()Ljava/lang/String;", "setScreenOrientation", "(Ljava/lang/String;)V", "element", "content", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", "Lkotlin/ExtensionFunctionType;", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public interface NBDialogDSL {
    void element(Function1<? super NBDialogElement, Unit> function1);

    Function0<Boolean> getOnBackPressed();

    Function1<View, Unit> getOnClick();

    Function1<Boolean, Unit> getOnDialogShow();

    Function0<Unit> getOnDismiss();

    Function1<View, Unit> getOnViewShow();

    String getScreenOrientation();

    void setOnBackPressed(Function0<Boolean> function0);

    void setOnClick(Function1<? super View, Unit> function1);

    void setOnDialogShow(Function1<? super Boolean, Unit> function1);

    void setOnDismiss(Function0<Unit> function0);

    void setOnViewShow(Function1<? super View, Unit> function1);

    void setScreenOrientation(String str);
}
