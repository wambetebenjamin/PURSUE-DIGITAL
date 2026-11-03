package g.hades.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.hades.downloader.impl.DownloaderImpl;
/* loaded from: classes10.dex */
public class i extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        DownloaderImpl.a().notifyNetworkChange(j.a(context));
    }
}
