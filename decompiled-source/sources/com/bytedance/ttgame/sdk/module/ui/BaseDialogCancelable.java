package com.bytedance.ttgame.sdk.module.ui;

import android.app.Activity;
import android.content.DialogInterface;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BaseDialogCancelable.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/BaseDialogCancelable;", "Lcom/bytedance/ttgame/sdk/module/ui/AbstractBaseDialogCancelable;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "isWarning", "", "(Landroid/app/Activity;Z)V", "themeResId", "", "(Landroid/app/Activity;ZI)V", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class BaseDialogCancelable extends AbstractBaseDialogCancelable {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDialogCancelable(Activity activity) {
        super(activity, false, 0, 6, null);
        Intrinsics.checkNotNullParameter(activity, "activity");
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.bytedance.ttgame.sdk.module.ui.BaseDialogCancelable$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                BaseDialogCancelable.m272_init_$lambda0(BaseDialogCancelable.this, dialogInterface);
            }
        });
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDialogCancelable(Activity activity, boolean z) {
        super(activity, z, 0, 4, null);
        Intrinsics.checkNotNullParameter(activity, "activity");
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.bytedance.ttgame.sdk.module.ui.BaseDialogCancelable$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                BaseDialogCancelable.m272_init_$lambda0(BaseDialogCancelable.this, dialogInterface);
            }
        });
    }

    public /* synthetic */ BaseDialogCancelable(Activity activity, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i2 & 2) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDialogCancelable(Activity activity, boolean z, int i2) {
        super(activity, z, i2);
        Intrinsics.checkNotNullParameter(activity, "activity");
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.bytedance.ttgame.sdk.module.ui.BaseDialogCancelable$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                BaseDialogCancelable.m272_init_$lambda0(BaseDialogCancelable.this, dialogInterface);
            }
        });
    }

    public /* synthetic */ BaseDialogCancelable(Activity activity, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i3 & 2) != 0 ? false : z, (i3 & 4) != 0 ? R.style.gsdk_base_dialog_style : i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m272_init_$lambda0(BaseDialogCancelable this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setCancelable(true);
    }
}
