package gsdk.impl.share.DEFAULT;

import android.graphics.Bitmap;
import com.bytedance.ttgame.module.share.api.callback.TTGetImageCallback;
/* loaded from: classes10.dex */
public class g implements TTGetImageCallback {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.ug.sdk.share.api.callback.c f20744a;

    public g(com.bytedance.ug.sdk.share.api.callback.c cVar) {
        this.f20744a = cVar;
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.TTGetImageCallback
    public void onSuccess(Bitmap bitmap) {
        com.bytedance.ug.sdk.share.api.callback.c cVar = this.f20744a;
        if (cVar != null) {
            cVar.onSuccess(bitmap);
        }
    }

    @Override // com.bytedance.ttgame.module.share.api.callback.TTGetImageCallback
    public void onFailed() {
        com.bytedance.ug.sdk.share.api.callback.c cVar = this.f20744a;
        if (cVar != null) {
            cVar.onFailed();
        }
    }
}
