package com.bytedance.ttgame.library.module_manager.servicemap;

import com.bytedance.ttgame.module.main.bridge.OptionalModuleCompat;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public class __GSDKServiceMap__ {
    public static final Map<String, String[]> serviceMap;

    static {
        HashMap hashMap = new HashMap();
        serviceMap = hashMap;
        hashMap.put("com.bytedance.ttgame.main.event.IEventService", new String[]{"com.bytedance.ttgame.core.event.Proxy__EventService"});
        hashMap.put("com.bytedance.ttgame.main.internal.applog.IAppLogService", new String[]{"com.bytedance.ttgame.core.applog.Proxy__ApplogService"});
        hashMap.put("com.bytedance.ttgame.main.internal.cache.ICacheService", new String[]{"com.bytedance.ttgame.core.cache.Proxy__CacheService"});
        hashMap.put("com.bytedance.ttgame.main.internal.ICoreInternalService", new String[]{"com.bytedance.ttgame.core.coreservice.Proxy__CoreInternalService"});
        hashMap.put("com.bytedance.ttgame.main.internal.IGameSdkConfigService", new String[]{"com.bytedance.ttgame.core.gamesdkconfig.Proxy__GameSdkConfigService"});
        hashMap.put("com.bytedance.ttgame.main.internal.IMainInternalService", new String[]{"com.bytedance.ttgame.main.internal.Proxy__MainInternalService"});
        hashMap.put("com.bytedance.ttgame.main.internal.INetService", new String[]{"com.bytedance.ttgame.core.net.Proxy__NetService"});
        hashMap.put("com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", new String[]{"com.bytedance.ttgame.core.lifecycle.Proxy__LifecycleService"});
        hashMap.put("com.bytedance.ttgame.main.internal.log.IGLogService", new String[]{"com.bytedance.ttgame.core.log.Proxy__GLogService"});
        hashMap.put("com.bytedance.ttgame.main.internal.net.INetworkCommonParam", new String[]{"com.bytedance.ttgame.main.internal.net.Proxy__NetworkCommonParam"});
        hashMap.put("com.bytedance.ttgame.main.internal.net.IRetrofitService", new String[]{"com.bytedance.ttgame.core.net.Proxy__RetrofitService"});
        hashMap.put("com.bytedance.ttgame.main.internal.net.TTHttpApi", new String[]{"com.bytedance.ttgame.core.net.Proxy__TTHttpApiImpl"});
        hashMap.put("com.bytedance.ttgame.main.internal.realtime.IRealtimeEventService", new String[]{"com.bytedance.ttgame.core.realtime.Proxy__RealtimeEventService"});
        hashMap.put("com.bytedance.ttgame.main.internal.sdkmonitor.ISdkMonitorLogService", new String[]{"com.bytedance.ttgame.core.sdkmonitor.Proxy__SdkMonitorLogService"});
        hashMap.put("com.bytedance.ttgame.main.module.log.api.ILogService", new String[]{"com.bytedance.ttgame.module.log.impl.Proxy__LogService"});
        hashMap.put("com.bytedance.ttgame.module.abtest.api.IABTestService", new String[]{"com.bytedance.ttgame.module.abtest.impl.Proxy__ABTestService"});
        hashMap.put("com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer", new String[]{"com.bytedance.ttgame.module.account.Proxy__AccountAppLogTransfer"});
        hashMap.put("com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", new String[]{"com.bytedance.ttgame.module.account.channel.toutiao.Proxy__AccountErrorHandleService"});
        hashMap.put("com.bytedance.ttgame.module.account.passport.IBdTuringService", new String[]{"com.bytedance.ttgame.sdk.module.account.turing.Proxy__BdTruingImpl"});
        hashMap.put("com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer", new String[]{"com.bytedance.ttgame.sdk.module.account.Proxy__AppLogTransferService"});
        hashMap.put("com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountErrorHandleService", new String[]{"com.bytedance.ttgame.sdk.module.account.utils.Proxy__TTAccountErrorHandleService"});
        hashMap.put("com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", new String[]{"com.bytedance.ttgame.sdk.module.account.Proxy__TTAccountInternalService"});
        hashMap.put("com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGamePayService", new String[]{"cloudgame.Proxy__TTCloudGamePayService"});
        hashMap.put("com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", new String[]{"com.bytedance.ttgame.channel.cloudgame.Proxy__TTCloudGameService"});
        hashMap.put(OptionalModuleCompat.SERVICE_APPSFLYER, new String[]{"com.bytedance.ttgame.module.appsflyer.Proxy__AppsFlyerService"});
        hashMap.put("com.bytedance.ttgame.module.boost.api.IBoostService", new String[]{"com.bytedance.ttgame.module.boost.Proxy__BoostService"});
        hashMap.put("com.bytedance.ttgame.module.cdkey.api.ICDKeyService", new String[]{"com.bytedance.ttgame.module.cdkey.Proxy__CDKeyService"});
        hashMap.put("com.bytedance.ttgame.module.cloud.api.ICloudService", new String[]{"com.bytedance.ttgame.module.cloud.Proxy__CloudService"});
        hashMap.put("com.bytedance.ttgame.module.compliance.api.agegate.IAgeGateService", new String[]{"com.bytedance.ttgame.module.compliance.impl.agegate.Proxy__AgeGateService"});
        hashMap.put("com.bytedance.ttgame.module.compliance.api.cookie.ICookieService", new String[]{"com.bytedance.ttgame.module.compliance.impl.cookie.Proxy__CookieService"});
        hashMap.put("com.bytedance.ttgame.module.compliance.api.permission.IPermissionService", new String[]{"com.bytedance.ttgame.module.compliance.impl.permission.Proxy__PermissionService"});
        hashMap.put("com.bytedance.ttgame.module.compliance.api.protocol.IProtocolService", new String[]{"com.bytedance.ttgame.module.compliance.impl.protocol.Proxy__ProtocolService"});
        hashMap.put("com.bytedance.ttgame.module.compliance.api.realname.IRealNameService", new String[]{"com.bytedance.ttgame.module.compliance.impl.realname.Proxy__RealNameService"});
        hashMap.put("com.bytedance.ttgame.module.crash.api.ICrashService", new String[]{"com.bytedance.ttgame.module.crash.Proxy__CrashService"});
        hashMap.put("com.bytedance.ttgame.module.database.api.IDatabaseService", new String[]{"com.bytedance.ttgame.module.database.Proxy__DatabaseService"});
        hashMap.put("com.bytedance.ttgame.module.database.api.IFusionUserInfoService", new String[]{"com.bytedance.ttgame.module.database.impl.Proxy__FusionUserInfoService"});
        hashMap.put("com.bytedance.ttgame.module.database.api.ILuffySettingsService", new String[]{"com.bytedance.ttgame.module.database.impl.Proxy__LuffySettingsService"});
        hashMap.put("com.bytedance.ttgame.module.database.api.IUserInfoService", new String[]{"com.bytedance.ttgame.module.database.impl.Proxy__UserInfoService"});
        hashMap.put("com.bytedance.ttgame.module.dynamic.api.IDynamicService", new String[]{"com.bytedance.ttgame.module.dynamic.Proxy__DynamicService"});
        hashMap.put("com.bytedance.ttgame.module.fileupload.api.IFileUploadService", new String[]{"com.bytedance.ttgame.module.fileupload.impl.Proxy__FileUploadService"});
        hashMap.put("com.bytedance.ttgame.module.firebase.api.IFirebaseService", new String[]{"com.bytedance.ttgame.module.firebase.Proxy__FirebaseService"});
        hashMap.put("com.bytedance.ttgame.module.gameinfo.api.IGameInfoService", new String[]{"com.bytedance.ttgame.module.gameinfo.Proxy__GameInfoService"});
        hashMap.put("com.bytedance.ttgame.module.gameprotect.api.ISecureService", new String[]{"com.bytedance.ttgame.module.gameprotect.impl.Proxy__SecureService"});
        hashMap.put(OptionalModuleCompat.SERVICE_GEAS, new String[]{"com.bytedance.ttgame.module.geas.Proxy__GeasService"});
        hashMap.put("com.bytedance.ttgame.module.godzilla.api.IGodzillaService", new String[]{"com.bytedance.ttgame.module.godzilla.impl.Proxy__GodzillaService"});
        hashMap.put("com.bytedance.ttgame.module.gpm.api.IGPMService", new String[]{"com.bytedance.ttgame.module.gpm.Proxy__GPMService"});
        hashMap.put("com.bytedance.ttgame.module.loccom.api.ILocationCommonService", new String[]{"com.bytedance.ttgame.module.loccom.impl.Proxy__LocationCommonService"});
        hashMap.put("com.bytedance.ttgame.module.monitor.api.commonmonitor.ICommonMonitorService", new String[]{"com.bytedance.ttgame.module.monitor.impl.commonmonitor.Proxy__CommonMonitorService"});
        hashMap.put("com.bytedance.ttgame.module.monitor.api.ttnet.ITTNetService", new String[]{"com.bytedance.ttgame.module.monitor.impl.ttnet.Proxy__TTNetService"});
        hashMap.put("com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService", new String[]{"com.bytedance.ttgame.module.netdiagnose.Proxy__NetDiagnoseService"});
        hashMap.put("com.bytedance.ttgame.module.notice.api.INoticeService", new String[]{"com.bytedance.ttgame.module.notice.Proxy__NoticeService"});
        hashMap.put("com.bytedance.ttgame.module.pay.api.IPayService", new String[]{"com.bytedance.ttgame.module.pay.Proxy__PayService"});
        hashMap.put(OptionalModuleCompat.SERVICE_PUSH, new String[]{"com.bytedance.ttgame.module.push.Proxy__PushService"});
        hashMap.put("com.bytedance.ttgame.module.rating.api.IRatingService", new String[]{"com.bytedance.ttgame.module.rating.Proxy__RatingService"});
        hashMap.put("com.bytedance.ttgame.module.rn.api.IGumihoEngineService", new String[]{"com.bytedance.ttgame.module.rn.Proxy__GumihoEngineService"});
        hashMap.put("com.bytedance.ttgame.module.rn.api.IRNEngineService", new String[]{"com.bytedance.ttgame.module.rn.Proxy__RNEngineService"});
        hashMap.put("com.bytedance.ttgame.module.share.api.IShareService", new String[]{"com.bytedance.ttgame.module.share.Proxy__ShareService"});
        hashMap.put("com.bytedance.ttgame.module.share.api.ISystemShareService", new String[]{"com.bytedance.ttgame.module.share.Proxy__SystemShareService"});
        hashMap.put("com.bytedance.ttgame.module.share.IFacebookShareService", new String[]{"com.bytedance.ttgame.module.share.Proxy__FacebookShareService"});
        hashMap.put("com.bytedance.ttgame.module.share.ITwitterShareService", new String[]{"com.bytedance.ttgame.module.share.Proxy__TwitterShareService"});
        hashMap.put(OptionalModuleCompat.SERVICE_WEBVIEW, new String[]{"com.bytedance.ttgame.module.webview.Proxy__WebViewService"});
        hashMap.put("com.bytedance.ttgame.rocketapi.account.IAccountService", new String[]{"com.bytedance.ttgame.module.account.Proxy__AccountService"});
        hashMap.put("com.bytedance.ttgame.rocketapi.account.ITanwanService", new String[]{"com.bytedance.ttgame.module.account.Proxy__TanwanService"});
        hashMap.put("com.bytedance.ttgame.rocketapi.account.IZlongService", new String[]{"com.bytedance.ttgame.module.account.Proxy__ZlongService"});
        hashMap.put("com.bytedance.ttgame.rocketapi.IRocketApi", new String[]{"com.bytedance.ttgame.channel.Proxy__Channel"});
        hashMap.put("com.bytedance.ttgame.rocketapi.IRocketMiniApi", new String[]{"com.bytedance.ttgame.channel.Proxy__RocketMiniChannel"});
        hashMap.put("com.bytedance.ttgame.rocketapi.seamless.ISeamlessLoginService", new String[]{"com.bytedance.ttgame.module.seamless.Proxy__SeamlessLoginService"});
        hashMap.put("com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService", new String[]{"com.bytedance.ttgame.module.usercenter.Proxy__UserCenterService"});
        hashMap.put("com.bytedance.ttgame.sdk.module.account.api.ITTAccountService", new String[]{"com.bytedance.ttgame.sdk.module.account.Proxy__TTAccountService"});
        hashMap.put("com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", new String[]{"com.bytedance.ttgame.sdk.module.account.Proxy__TTSeamlessLoginService"});
        hashMap.put("com.bytedance.ttgame.sdk.module.account.platform.api.IThirdAuthorizeService", new String[]{"com.bytedance.ttgame.sdk.module.account.platform.facebook.Proxy__FacebookAuthorizeService", "com.bytedance.ttgame.sdk.module.account.platform.google.Proxy__GoogleAuthorizeService", "com.bytedance.ttgame.sdk.module.account.platform.google_play.Proxy__GooglePlayAuthorizeService", "com.bytedance.ttgame.sdk.module.account.platform.line.Proxy__LineAuthorizeService", "com.bytedance.ttgame.sdk.module.account.platform.pass.Proxy__PassAuthorizeService", "com.bytedance.ttgame.sdk.module.account.platform.twitter.Proxy__TwitterAuthorizeService", "com.bytedance.ttgame.sdk.module.account.platform.vk.Proxy__VkAuthorizeService"});
        hashMap.put("com.bytedance.ttgame.sdk.module.account.platform.api.IThirdSeamlessService", new String[]{"com.bytedance.ttgame.sdk.module.account.platform.google_play.Proxy__GooglePlaySeamlessService"});
    }

    private __GSDKServiceMap__() {
    }
}
