package com.bytedance.ttgame.module.compliance.api.protocol;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.module.compliance.api.protocol.pojo.ProtocolAddressData;
import kotlin.Metadata;
/* compiled from: IProtocolService.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0012\u0010\f\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH'J\u001c\u0010\f\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\"\u0010\r\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fH&J\u001a\u0010\u0011\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0005H&J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H'J\b\u0010\u0014\u001a\u00020\u0007H&J \u0010\u0015\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH&J \u0010\u0017\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH&¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/protocol/IProtocolService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "checkProtocolUpdateType", "Lcom/bytedance/ttgame/module/compliance/api/protocol/LicenseUpdateType;", "checkProtocolVersion", "", "openPrivacyPanel", "", "activity", "Landroid/app/Activity;", "panelCallback", "Lcom/bytedance/ttgame/module/compliance/api/protocol/IProtocolPanelCallback;", "openProtocolPanel", "protocolAddress", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/framework/module/callback/ICallback;", "Lcom/bytedance/ttgame/module/compliance/api/protocol/pojo/ProtocolAddressData;", "setAgreeConsent", "isAgree", "setOpenPrivacyPanel", "setUserHasAgreedProtocolsUpdate", "showLicenseUpdateGuide", "iCallback", "showLicenseUpdateGuideSkipExit", "Companion", "compliance_api_protocol_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface IProtocolService extends IModuleApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String IS_NEED_BACK = "is_need_back";
    public static final String IS_NEED_CLOSE_EVENT = "is_need_close_event";
    public static final int OPT_NET_MOBILE = 4;
    public static final int OPT_NET_TELECOM = 5;
    public static final int OPT_NET_UNICOM = 6;
    public static final int OPT_PRIVACY = 2;
    public static final int OPT_PROTOCOL = 1;
    public static final int OPT_PROTOCOL_PRIVACY = 3;
    public static final int OPT_PROTOCOL_PRIVACY_WITH_BTN = 7;
    public static final String OPT_TYPE = "opt_type";

    LicenseUpdateType checkProtocolUpdateType();

    boolean checkProtocolVersion();

    void openPrivacyPanel(Activity activity, IProtocolPanelCallback iProtocolPanelCallback);

    @Deprecated
    void openProtocolPanel(Activity activity);

    void openProtocolPanel(Activity activity, IProtocolPanelCallback iProtocolPanelCallback);

    void protocolAddress(Activity activity, ICallback<ProtocolAddressData> iCallback);

    void setAgreeConsent(Activity activity, boolean z);

    @InternalApi
    void setOpenPrivacyPanel(boolean z);

    void setUserHasAgreedProtocolsUpdate();

    void showLicenseUpdateGuide(Activity activity, ICallback<Boolean> iCallback);

    void showLicenseUpdateGuideSkipExit(Activity activity, ICallback<Boolean> iCallback);

    /* compiled from: IProtocolService.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/protocol/IProtocolService$Companion;", "", "()V", "IS_NEED_BACK", "", "IS_NEED_CLOSE_EVENT", "OPT_NET_MOBILE", "", "OPT_NET_TELECOM", "OPT_NET_UNICOM", "OPT_PRIVACY", "OPT_PROTOCOL", "OPT_PROTOCOL_PRIVACY", "OPT_PROTOCOL_PRIVACY_WITH_BTN", "OPT_TYPE", "compliance_api_protocol_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String IS_NEED_BACK = "is_need_back";
        public static final String IS_NEED_CLOSE_EVENT = "is_need_close_event";
        public static final int OPT_NET_MOBILE = 4;
        public static final int OPT_NET_TELECOM = 5;
        public static final int OPT_NET_UNICOM = 6;
        public static final int OPT_PRIVACY = 2;
        public static final int OPT_PROTOCOL = 1;
        public static final int OPT_PROTOCOL_PRIVACY = 3;
        public static final int OPT_PROTOCOL_PRIVACY_WITH_BTN = 7;
        public static final String OPT_TYPE = "opt_type";

        private Companion() {
        }
    }
}
