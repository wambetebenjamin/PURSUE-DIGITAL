package gsdk.impl.share.DEFAULT;

import android.content.Context;
import com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class r implements com.bytedance.ug.sdk.share.api.depend.a {

    /* renamed from: a  reason: collision with root package name */
    private ITTShareAppConfig f20763a;

    public r(ITTShareAppConfig iTTShareAppConfig) {
        if (iTTShareAppConfig != null) {
            this.f20763a = iTTShareAppConfig;
        }
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.a
    public String getAppId() {
        ITTShareAppConfig iTTShareAppConfig = this.f20763a;
        if (iTTShareAppConfig != null) {
            return iTTShareAppConfig.getAppId();
        }
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.a
    public String getDeviceId() {
        ITTShareAppConfig iTTShareAppConfig = this.f20763a;
        if (iTTShareAppConfig != null) {
            return iTTShareAppConfig.getDeviceId();
        }
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.a
    public JSONObject getExtraConfig() {
        ITTShareAppConfig iTTShareAppConfig = this.f20763a;
        if (iTTShareAppConfig != null) {
            return iTTShareAppConfig.getExtraConfig();
        }
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.api.depend.a
    public void openPage(Context context, String str) {
        ITTShareAppConfig iTTShareAppConfig = this.f20763a;
        if (iTTShareAppConfig != null) {
            iTTShareAppConfig.openPage(context, str);
        }
    }
}
