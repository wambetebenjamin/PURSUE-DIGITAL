package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.api.depend.ITTShareImageConfig;
/* loaded from: classes10.dex */
public class u implements com.bytedance.ug.sdk.share.api.depend.f {

    /* renamed from: a  reason: collision with root package name */
    private ITTShareImageConfig f20767a;

    public u(ITTShareImageConfig iTTShareImageConfig) {
        this.f20767a = iTTShareImageConfig;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.f
    public void getImageBitmap(String str, com.bytedance.ug.sdk.share.api.callback.c cVar) {
        ITTShareImageConfig iTTShareImageConfig = this.f20767a;
        if (iTTShareImageConfig != null) {
            iTTShareImageConfig.getImageBitmap(str, new g(cVar));
        }
    }
}
