package gsdk.impl.dynamic.DEFAULT;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.react.framework.RNConfig;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import java.util.HashMap;
/* compiled from: DynamicExceptionHandle.java */
/* loaded from: classes10.dex */
public class m implements NativeModuleCallExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public boolean f20168a = false;

    /* renamed from: b  reason: collision with root package name */
    public Exception f20169b;

    /* renamed from: c  reason: collision with root package name */
    private HashMap<String, String> f20170c;

    /* renamed from: d  reason: collision with root package name */
    private a f20171d;

    /* compiled from: DynamicExceptionHandle.java */
    /* loaded from: classes10.dex */
    public interface a {
        void a(Exception exc);
    }

    public m(HashMap<String, String> hashMap) {
        this.f20170c = hashMap;
    }

    public void a(a aVar) {
        this.f20171d = aVar;
    }

    public void a(String str, String str2) {
        if (this.f20170c != null) {
            if (TextUtils.isEmpty(str)) {
                this.f20170c.put(RNConfig.MODULE_VERSION, str);
            }
            if (TextUtils.isEmpty(str2)) {
                this.f20170c.put(RNConfig.MODULE_NAME, str2);
            }
        }
    }

    public void a() {
        this.f20171d = null;
        this.f20170c.clear();
    }

    @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
    public void handleException(Exception exc) {
        if (exc != null) {
            try {
                HashMap<String, String> hashMap = this.f20170c;
                if (hashMap != null) {
                    if (hashMap.containsKey(RNConfig.MODULE_NAME)) {
                        ah.a().a(exc, this.f20170c);
                    }
                } else {
                    ah.a().a(exc, (HashMap) null);
                }
                Log.e(RNConfig.RN_ASSET_PATH, "dyanmic exception happend exception: " + exc.toString() + " cause: " + exc.getCause());
                a aVar = this.f20171d;
                if (aVar != null) {
                    aVar.a(exc);
                    return;
                }
                this.f20168a = true;
                this.f20169b = exc;
            } catch (Exception unused) {
            }
        }
    }
}
