package com.bytedance.ttgame.sdk.module.ui;

import com.bytedance.ttgame.sdk.module.ui.NBDialog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NBDialog.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"NBDialog", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialog;", "initializer", "Lkotlin/Function1;", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialog$Builder;", "", "Lkotlin/ExtensionFunctionType;", "android_gsdk_base_common_ui_i18nRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class NBDialogKt {
    public static final /* synthetic */ NBDialog NBDialog(Function1 initializer) {
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        NBDialog.Builder builder = new NBDialog.Builder();
        initializer.invoke(builder);
        return builder.build();
    }
}
