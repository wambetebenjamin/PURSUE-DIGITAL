package gsdk.impl.dynamic.DEFAULT;

import android.text.TextUtils;
import com.bytedance.ttgame.module.dynamic.api.IDynamicListener;
import com.bytedance.ttgame.module.rn.api.model.UnityMessage;
import java.util.HashMap;
/* compiled from: DynamicMessageHandler.java */
/* loaded from: classes10.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private static p f20181a;

    /* renamed from: b  reason: collision with root package name */
    private HashMap<String, IDynamicListener> f20182b = new HashMap<>();

    private p() {
    }

    public static p a() {
        if (f20181a == null) {
            f20181a = new p();
        }
        return f20181a;
    }

    public void a(String str, IDynamicListener iDynamicListener) {
        if (TextUtils.isEmpty(str) || iDynamicListener == null) {
            return;
        }
        this.f20182b.put(str, iDynamicListener);
    }

    public boolean a(String str, String str2, HashMap hashMap, String str3) {
        IDynamicListener iDynamicListener;
        if (!this.f20182b.containsKey(str) || (iDynamicListener = this.f20182b.get(str)) == null) {
            return false;
        }
        UnityMessage unityMessage = new UnityMessage();
        unityMessage.setAction(str2);
        unityMessage.setParams(hashMap);
        unityMessage.setMessage(str3);
        iDynamicListener.onMessage(unityMessage);
        return true;
    }

    public void a(String str) {
        this.f20182b.remove(str);
    }
}
