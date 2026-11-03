package gsdk.impl.share.facebook;

import android.content.Intent;
import com.facebook.CallbackManager;
import kotlin.Metadata;
/* compiled from: FacebookResultHandler.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/share/FacebookResultHandler;", "", "()V", "handleShareResult", "", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "share_impl_facebook_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class c {
    public final void a(int i2, int i3, Intent intent) {
        CallbackManager a2 = a.f20772a.a();
        if (a2 != null) {
            a2.onActivityResult(i2, i3, intent);
        }
    }
}
