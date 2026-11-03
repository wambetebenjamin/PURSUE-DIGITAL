package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.api.depend.ITTShareNetworkConfig;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class w implements com.bytedance.ug.sdk.share.api.depend.k {

    /* renamed from: a  reason: collision with root package name */
    private ITTShareNetworkConfig f20769a;

    public w(ITTShareNetworkConfig iTTShareNetworkConfig) {
        this.f20769a = iTTShareNetworkConfig;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.k
    public String getHost() {
        ITTShareNetworkConfig iTTShareNetworkConfig = this.f20769a;
        if (iTTShareNetworkConfig != null) {
            return iTTShareNetworkConfig.getHost();
        }
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.k
    public String executeGet(int i2, String str) throws Exception {
        ITTShareNetworkConfig iTTShareNetworkConfig = this.f20769a;
        if (iTTShareNetworkConfig != null) {
            return iTTShareNetworkConfig.executeGet(i2, str);
        }
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.k
    public String executePost(int i2, String str, JSONObject jSONObject) throws Exception {
        ITTShareNetworkConfig iTTShareNetworkConfig = this.f20769a;
        if (iTTShareNetworkConfig != null) {
            return iTTShareNetworkConfig.executePost(i2, str, jSONObject);
        }
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.k
    public int checkResponseException(Throwable th) {
        ITTShareNetworkConfig iTTShareNetworkConfig = this.f20769a;
        if (iTTShareNetworkConfig != null) {
            return iTTShareNetworkConfig.checkResponseException(th);
        }
        return 0;
    }
}
