package gsdk.impl.share.DEFAULT;

import com.bytedance.ttgame.module.share.ShareService;
import com.bytedance.ttgame.module.share.api.depend.ITTShareKeyConfig;
import com.bytedance.ttgame.module.share.api.entity.TTShareConfig;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class o implements ITTShareKeyConfig {

    /* renamed from: a  reason: collision with root package name */
    private TTShareConfig f20759a;

    public o(TTShareConfig tTShareConfig) {
        this.f20759a = tTShareConfig;
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareKeyConfig
    public JSONObject getKeys() {
        if (this.f20759a != null) {
            try {
                return new JSONObject(this.f20759a.keys.toString());
            } catch (JSONException e2) {
                ShareService.logService.e("gsdk_share_service", e2.getMessage());
                return null;
            }
        }
        return null;
    }
}
