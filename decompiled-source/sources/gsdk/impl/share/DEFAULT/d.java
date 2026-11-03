package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener;
/* loaded from: classes10.dex */
public class d implements OnTTDownloadListener {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.ug.sdk.share.api.callback.h f20740a;

    public d(com.bytedance.ug.sdk.share.api.callback.h hVar) {
        this.f20740a = hVar;
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener
    public void onStart() {
        com.bytedance.ug.sdk.share.api.callback.h hVar = this.f20740a;
        if (hVar != null) {
            hVar.onStart();
        }
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener
    public void onProgress(int i2) {
        com.bytedance.ug.sdk.share.api.callback.h hVar = this.f20740a;
        if (hVar != null) {
            hVar.onProgress(i2);
        }
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener
    public void onSuccessed() {
        com.bytedance.ug.sdk.share.api.callback.h hVar = this.f20740a;
        if (hVar != null) {
            hVar.onSuccessed();
        }
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener
    public void onFailed(Throwable th) {
        com.bytedance.ug.sdk.share.api.callback.h hVar = this.f20740a;
        if (hVar != null) {
            hVar.onFailed(th);
        }
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.OnTTDownloadListener
    public void onCanceled() {
        com.bytedance.ug.sdk.share.api.callback.h hVar = this.f20740a;
        if (hVar != null) {
            hVar.onCanceled();
        }
    }
}
