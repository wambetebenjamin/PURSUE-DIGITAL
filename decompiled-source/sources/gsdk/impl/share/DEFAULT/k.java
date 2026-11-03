package gsdk.impl.share.DEFAULT;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.ttgame.library.module_manager.Component;
import com.bytedance.ttgame.library.module_manager.ImplModule;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.module.share.ShareService;
import com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig;
import com.bytedance.ttgame.module.share.api.entity.TTShareConfig;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class k extends ITTShareAppConfig {

    /* renamed from: a  reason: collision with root package name */
    private TTShareConfig f20753a;

    public k(TTShareConfig tTShareConfig) {
        this.f20753a = tTShareConfig;
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig
    public void openPage(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig
    public String getAppId() {
        TTShareConfig tTShareConfig = this.f20753a;
        return tTShareConfig != null ? tTShareConfig.appId : "";
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig
    public String getDeviceId() {
        return ((IMainInternalService) ModuleManager.INSTANCE.getService(IMainInternalService.class)).getDeviceId();
    }

    @Override // com.bytedance.ttgame.module.share.api.depend.ITTShareAppConfig
    public JSONObject getExtraConfig() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_TOKEN, false);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_GET_SHARE_INFO, true);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_DOWNLOAD_DIALOG_CANCEL, true);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_DOWNLOAD_DIALOG_CANCEL_TOUCH_OUTSIDE, false);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_HIDDEN_WATERMARK, true);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_CHECK_ALBUM_IMAGE_NUM, 5);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_HIDE_SAVE_IMAGE_PREVIEW_DIALOG, true);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_TEXT_TOKEN, false);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_DEFAULT_PANEL_LIST, a());
            jSONObject.put("fb_sharedialog_timeout", this.f20753a.fbTimeout);
            jSONObject.put(com.bytedance.ug.sdk.share.impl.constant.b.KEY_ENABLE_ALBUM_PARSE, false);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private String a() {
        Component component = ModuleManager.INSTANCE.getComponent("share");
        if (component != null) {
            Map<String, ImplModule> impls = component.getImpls();
            HashSet hashSet = new HashSet();
            hashSet.addAll(impls.keySet());
            hashSet.remove("DEFAULT");
            if (hashSet.contains("image_share")) {
                hashSet.remove("image_share");
            }
            hashSet.add("sys_share");
            JSONArray jSONArray = new JSONArray((Collection) hashSet);
            String str = "[{\"channel_list\":" + jSONArray.toString() + ",\"panel_id\":\"" + ShareService.shareConfig.panelId + "\"}]";
            ShareService.logService.d("gsdk_share_service", str);
            return str;
        }
        return null;
    }
}
