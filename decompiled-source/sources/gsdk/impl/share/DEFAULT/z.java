package gsdk.impl.share.DEFAULT;

import android.app.Activity;
import android.os.Bundle;
import com.bytedance.ttgame.module.share.impl.R;
/* loaded from: classes10.dex */
public class z extends com.bytedance.ug.sdk.share.impl.ui.view.b {
    public z(Activity activity) {
        super(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.ug.sdk.share.impl.ui.view.b, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        findViewById(R.id.share_download_tv).setVisibility(8);
    }
}
