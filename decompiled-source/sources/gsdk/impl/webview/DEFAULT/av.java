package gsdk.impl.webview.DEFAULT;

import android.content.Context;
import android.os.RemoteException;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.webview.api.aidl.IWebAidlResultCallback;
import gsdk.impl.webview.DEFAULT.ag;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;
/* compiled from: MainProWebAidlInterface.java */
/* loaded from: classes10.dex */
public class av extends ag.b {

    /* renamed from: e  reason: collision with root package name */
    private static final String f20920e = "MainProAidlInterface";

    /* renamed from: d  reason: collision with root package name */
    private Context f20921d;

    public av(Context context) {
        this.f20921d = context;
    }

    @Override // gsdk.impl.webview.DEFAULT.ag
    public void a(int i2, String str, Map map, af afVar) {
        try {
            b(i2, str, map, afVar);
        } catch (Exception e2) {
            Timber.tag(f20920e).d(e2.toString(), new Object[0]);
        }
    }

    @Override // gsdk.impl.webview.DEFAULT.ag
    public Map a(int i2, String str, Map map) {
        return b(i2, str, map);
    }

    private Map b(int i2, String str, Map map) {
        return aq.a().a(this.f20921d, i2, str, map);
    }

    private void b(int i2, String str, Map map, final af afVar) {
        aq.a().a(this.f20921d, i2, str, map, new IWebAidlResultCallback() { // from class: gsdk.impl.webview.DEFAULT.av.1
            @Override // com.bytedance.ttgame.module.webview.api.aidl.IWebAidlResultCallback
            public void onResult(String str2, Object obj) {
                if (afVar != null) {
                    try {
                        Timber.tag(av.f20920e).d(obj.toString(), new Object[0]);
                        afVar.a(true, str2, (Map) obj);
                    } catch (RemoteException e2) {
                        Timber.tag(av.f20920e).e(e2.toString(), new Object[0]);
                    }
                }
            }

            @Override // com.bytedance.ttgame.module.webview.api.aidl.IWebAidlResultCallback
            public void onError(String str2, GSDKError gSDKError) {
                if (afVar != null) {
                    try {
                        HashMap hashMap = new HashMap();
                        if (gSDKError != null) {
                            hashMap.put("error_msg", gSDKError.getMessage());
                        }
                        Timber.tag(av.f20920e).d(gSDKError.toString(), new Object[0]);
                        afVar.a(false, str2, hashMap);
                    } catch (RemoteException e2) {
                        Timber.tag(av.f20920e).e(e2.toString(), new Object[0]);
                    }
                }
            }
        });
    }
}
