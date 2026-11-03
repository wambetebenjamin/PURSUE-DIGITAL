package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig;
import com.bytedance.ttgame.module.share.api.depend.ITTShareConfig;
import com.bytedance.ttgame.module.share.api.depend.ITTShareKeyConfig;
import com.bytedance.ttgame.module.share.api.entity.TTShareConfig;
/* loaded from: classes10.dex */
public class l implements ITTShareConfig {

    /* renamed from: a  reason: collision with root package name */
    private TTShareConfig f20754a;

    public l(TTShareConfig tTShareConfig) {
        this.f20754a = tTShareConfig;
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareConfig
    public ITTShareAppConfig getAppConfig() {
        return new k(this.f20754a);
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareConfig
    public ITTShareKeyConfig getKeyConfig() {
        return new o(this.f20754a);
    }
}
