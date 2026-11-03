package com.vk.api.sdk.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.vk.api.sdk.R;
import com.vk.api.sdk.VKScheduler;
import com.vk.api.sdk.utils.VKValidationLocker;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VKConfirmationActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0004H\u0014¨\u0006\n"}, d2 = {"Lcom/vk/api/sdk/ui/VKConfirmationActivity;", "Landroid/app/Activity;", "()V", "finish", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKConfirmationActivity extends Activity {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_MESSAGE = "key_message";
    private static boolean result;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        VKConfirmationActivity vKConfirmationActivity = this;
        setContentView(new FrameLayout(vKConfirmationActivity));
        new AlertDialog.Builder(vKConfirmationActivity, 5).setTitle(R.string.vk_confirm).setMessage(getIntent().getStringExtra(KEY_MESSAGE)).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.vk.api.sdk.ui.VKConfirmationActivity$onCreate$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                VKConfirmationActivity.Companion.setResult(true);
                VKConfirmationActivity.this.finish();
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener() { // from class: com.vk.api.sdk.ui.VKConfirmationActivity$onCreate$2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                VKConfirmationActivity.Companion.setResult(false);
                VKConfirmationActivity.this.finish();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.vk.api.sdk.ui.VKConfirmationActivity$onCreate$3
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                VKConfirmationActivity.Companion.setResult(false);
                VKConfirmationActivity.this.finish();
            }
        }).show();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        VKValidationLocker.INSTANCE.signal();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        VKValidationLocker.INSTANCE.signal();
        overridePendingTransition(0, 0);
    }

    /* compiled from: VKConfirmationActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/vk/api/sdk/ui/VKConfirmationActivity$Companion;", "", "()V", "KEY_MESSAGE", "", "result", "", "getResult", "()Z", "setResult", "(Z)V", "start", "", "context", "Landroid/content/Context;", "message", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getResult() {
            return VKConfirmationActivity.result;
        }

        public final void setResult(boolean z) {
            VKConfirmationActivity.result = z;
        }

        public final void start(final Context context, final String message) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(message, "message");
            VKScheduler.runOnMainThread$default(new Runnable() { // from class: com.vk.api.sdk.ui.VKConfirmationActivity$Companion$start$1
                @Override // java.lang.Runnable
                public final void run() {
                    context.startActivity(new Intent(context, VKConfirmationActivity.class).addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP).putExtra("key_message", message));
                }
            }, 0L, 2, null);
        }
    }
}
