package gsdk.impl.webview.DEFAULT;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContracts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PickMedia.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/webview/presenter/GetContent;", "Landroidx/activity/result/contract/ActivityResultContracts$GetContent;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class bk extends ActivityResultContracts.GetContent {
    @Override // androidx.activity.result.contract.ActivityResultContracts.GetContent, androidx.activity.result.contract.ActivityResultContract
    public Intent createIntent(Context context, String input) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(input, "input");
        Intent addFlags = super.createIntent(context, input).addFlags(3);
        Intrinsics.checkNotNullExpressionValue(addFlags, "super.createIntent(conte…ANT_WRITE_URI_PERMISSION)");
        return addFlags;
    }
}
