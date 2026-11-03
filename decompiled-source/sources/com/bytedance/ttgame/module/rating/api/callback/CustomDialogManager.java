package com.bytedance.ttgame.module.rating.api.callback;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CustomDialogManager.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/rating/api/callback/CustomDialogManager;", "", "()V", "iPraiseDialogCallback", "Lcom/bytedance/ttgame/module/rating/api/callback/IRatingDialogCallback;", "getIPraiseDialogCallback", "()Lcom/bytedance/ttgame/module/rating/api/callback/IRatingDialogCallback;", "setIPraiseDialogCallback", "(Lcom/bytedance/ttgame/module/rating/api/callback/IRatingDialogCallback;)V", "Companion", "rating_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CustomDialogManager {
    public static final Companion Companion = new Companion(null);
    private static CustomDialogManager instance = new CustomDialogManager();
    public IRatingDialogCallback iPraiseDialogCallback;

    public final IRatingDialogCallback getIPraiseDialogCallback() {
        IRatingDialogCallback iRatingDialogCallback = this.iPraiseDialogCallback;
        if (iRatingDialogCallback != null) {
            return iRatingDialogCallback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("iPraiseDialogCallback");
        return null;
    }

    public final void setIPraiseDialogCallback(IRatingDialogCallback iRatingDialogCallback) {
        Intrinsics.checkNotNullParameter(iRatingDialogCallback, "<set-?>");
        this.iPraiseDialogCallback = iRatingDialogCallback;
    }

    /* compiled from: CustomDialogManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/rating/api/callback/CustomDialogManager$Companion;", "", "()V", "instance", "Lcom/bytedance/ttgame/module/rating/api/callback/CustomDialogManager;", "getInstance", "()Lcom/bytedance/ttgame/module/rating/api/callback/CustomDialogManager;", "setInstance", "(Lcom/bytedance/ttgame/module/rating/api/callback/CustomDialogManager;)V", "rating_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CustomDialogManager getInstance() {
            return CustomDialogManager.instance;
        }

        public final void setInstance(CustomDialogManager customDialogManager) {
            Intrinsics.checkNotNullParameter(customDialogManager, "<set-?>");
            CustomDialogManager.instance = customDialogManager;
        }
    }
}
