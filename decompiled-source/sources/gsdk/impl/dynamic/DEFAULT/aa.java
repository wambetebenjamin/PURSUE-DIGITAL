package gsdk.impl.dynamic.DEFAULT;

import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.dynamic.api.IDynamicListener;
import com.bytedance.ttgame.module.rn.api.model.UnityMessage;
import com.facebook.react.bridge.Promise;
import java.util.HashMap;
/* compiled from: RNWindowModule.java */
/* loaded from: classes10.dex */
public class aa implements IDynamicListener {

    /* renamed from: a  reason: collision with root package name */
    private Promise f20058a;

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicListener
    public void onMessage(UnityMessage unityMessage) {
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicListener
    public void onPageCreated() {
    }

    public aa(Promise promise) {
        this.f20058a = promise;
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicListener
    public void onWindowCreated(String str) {
        Promise promise = this.f20058a;
        if (promise != null) {
            promise.resolve(str);
            this.f20058a = null;
        }
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicListener
    public void onFailed(String str) {
        Promise promise = this.f20058a;
        if (promise != null) {
            promise.reject(i.a.ERROR_CODE_UNKNOW);
            this.f20058a = null;
        }
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicListener
    public void onDegrade(HashMap<String, Object> hashMap) {
        Promise promise = this.f20058a;
        if (promise != null) {
            promise.reject(i.a.ERROR_CODE_UNKNOW);
            this.f20058a = null;
        }
    }

    @Override // com.bytedance.ttgame.module.dynamic.api.IDynamicListener
    public void onError(GSDKError gSDKError) {
        Promise promise = this.f20058a;
        if (promise != null) {
            promise.reject(i.a.ERROR_CODE_UNKNOW);
            this.f20058a = null;
        }
    }
}
