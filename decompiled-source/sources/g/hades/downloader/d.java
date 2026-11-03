package g.hades.downloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.bytedance.hades.downloader.impl.DownloaderImpl;
/* loaded from: classes10.dex */
public class d extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DownloaderImpl f19085a;

    public d(DownloaderImpl downloaderImpl) {
        this.f19085a = downloaderImpl;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d("Hades-J", "receive device id broadcast");
        this.f19085a.a(context, this);
    }
}
