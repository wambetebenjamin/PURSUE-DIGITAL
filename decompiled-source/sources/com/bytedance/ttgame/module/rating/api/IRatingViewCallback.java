package com.bytedance.ttgame.module.rating.api;

import kotlin.Metadata;
/* compiled from: IRatingViewCallback.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/module/rating/api/IRatingViewCallback;", "", "onCancelBtnClick", "", "onDismiss", "onShow", "onSubmitBtnClick", "rating_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IRatingViewCallback {

    /* compiled from: IRatingViewCallback.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static void onDismiss(IRatingViewCallback iRatingViewCallback) {
        }

        public static void onShow(IRatingViewCallback iRatingViewCallback) {
        }
    }

    void onCancelBtnClick();

    void onDismiss();

    void onShow();

    void onSubmitBtnClick();
}
