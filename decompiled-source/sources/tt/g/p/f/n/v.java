package tt.g.p.f.n;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
import java.util.ArrayList;
import java.util.List;
import tt.g.p.f.n.j;
import tt.g.p.f.n.m;
/* loaded from: classes10.dex */
public class v {

    /* renamed from: a  reason: collision with root package name */
    private List<m.b> f21241a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    private b f21242b = null;

    /* renamed from: c  reason: collision with root package name */
    private j.b f21243c = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class b extends ConnectivityManager.NetworkCallback {
        private b() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            v.this.a(true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            v.this.a(false);
        }
    }

    public v(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            b(context);
        } else {
            a(context);
        }
    }

    private void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkChangeManager.ACTION);
        j.b bVar = new j.b(this);
        this.f21243c = bVar;
        context.registerReceiver(bVar, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        j.a(z ? 2L : 3L);
        for (m.b bVar : this.f21241a) {
            if (z) {
                bVar.a();
            } else {
                bVar.b();
            }
        }
    }

    private void b(Context context) {
        this.f21242b = new b();
        NetworkRequest build = new NetworkRequest.Builder().build();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            try {
                connectivityManager.registerNetworkCallback(build, this.f21242b);
            } catch (Exception unused) {
            }
        }
    }
}
