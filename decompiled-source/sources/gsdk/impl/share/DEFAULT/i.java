package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.api.callback.TTRequestPermissionCallback;
/* loaded from: classes10.dex */
public class i implements TTRequestPermissionCallback {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.ug.sdk.share.api.callback.k f20747a;

    public i(com.bytedance.ug.sdk.share.api.callback.k kVar) {
        this.f20747a = kVar;
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.TTRequestPermissionCallback
    public void onGranted() {
        com.bytedance.ug.sdk.share.api.callback.k kVar = this.f20747a;
        if (kVar != null) {
            kVar.onGranted();
        }
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.TTRequestPermissionCallback
    public void onDenied(String str) {
        com.bytedance.ug.sdk.share.api.callback.k kVar = this.f20747a;
        if (kVar != null) {
            kVar.onDenied(str);
        }
    }
}
