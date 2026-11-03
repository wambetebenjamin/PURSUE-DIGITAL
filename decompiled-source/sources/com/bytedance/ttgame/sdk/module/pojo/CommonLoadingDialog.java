package com.bytedance.ttgame.sdk.module.pojo;

import android.app.Activity;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import com.bytedance.ttgame.sdk.module.pojo.CommonLoadingDialog;
import com.bytedance.ttgame.sdk.module.ui.LoadingDialog;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* compiled from: CommonLoadingDialog.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/pojo/CommonLoadingDialog;", "", "()V", "Companion", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class CommonLoadingDialog {
    public static final Companion Companion = new Companion(null);
    private static volatile LoadingDialog mProgressDialog;

    @JvmStatic
    public static final void dissmissDialog(Activity activity) {
        Companion.dissmissDialog(activity);
    }

    @JvmStatic
    public static final void showDialog(Activity activity) {
        Companion.showDialog(activity);
    }

    /* compiled from: CommonLoadingDialog.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/pojo/CommonLoadingDialog$Companion;", "", "()V", "mProgressDialog", "Lcom/bytedance/ttgame/sdk/module/ui/LoadingDialog;", "dissmissDialog", "", "activity", "Landroid/app/Activity;", "showDialog", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes8.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void showDialog(final Activity activity) {
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.bytedance.ttgame.sdk.module.pojo.CommonLoadingDialog$Companion$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CommonLoadingDialog.Companion.m254showDialog$lambda0(activity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: showDialog$lambda-0  reason: not valid java name */
        public static final void m254showDialog$lambda0(Activity activity) {
            if (CommonLoadingDialog.mProgressDialog == null) {
                LoadingDialog loadingDialog = new LoadingDialog(activity, R.style.loading_material_dialog_style);
                loadingDialog.setCancelable(true);
                Companion companion = CommonLoadingDialog.Companion;
                CommonLoadingDialog.mProgressDialog = loadingDialog;
            }
            LoadingDialog loadingDialog2 = CommonLoadingDialog.mProgressDialog;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.setCanceledOnTouchOutside(true);
            LoadingDialog loadingDialog3 = CommonLoadingDialog.mProgressDialog;
            Intrinsics.checkNotNull(loadingDialog3);
            if (loadingDialog3.isShowing() || activity.isFinishing()) {
                return;
            }
            try {
                LoadingDialog loadingDialog4 = CommonLoadingDialog.mProgressDialog;
                Intrinsics.checkNotNull(loadingDialog4);
                loadingDialog4.show();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @JvmStatic
        public final void dissmissDialog(final Activity activity) {
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.bytedance.ttgame.sdk.module.pojo.CommonLoadingDialog$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CommonLoadingDialog.Companion.m253dissmissDialog$lambda1(activity);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: dissmissDialog$lambda-1  reason: not valid java name */
        public static final void m253dissmissDialog$lambda1(Activity activity) {
            try {
                if (activity.isFinishing() || CommonLoadingDialog.mProgressDialog == null) {
                    return;
                }
                LoadingDialog loadingDialog = CommonLoadingDialog.mProgressDialog;
                Intrinsics.checkNotNull(loadingDialog);
                if (loadingDialog.isShowing()) {
                    LoadingDialog loadingDialog2 = CommonLoadingDialog.mProgressDialog;
                    Intrinsics.checkNotNull(loadingDialog2);
                    loadingDialog2.dismiss();
                    Companion companion = CommonLoadingDialog.Companion;
                    CommonLoadingDialog.mProgressDialog = null;
                }
            } catch (Exception e2) {
                Timber.tag("gsdk").e(e2);
            }
        }
    }
}
