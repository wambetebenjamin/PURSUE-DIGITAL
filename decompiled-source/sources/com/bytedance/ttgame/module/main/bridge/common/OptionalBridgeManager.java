package com.bytedance.ttgame.module.main.bridge.common;

import android.text.TextUtils;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.IBridgeModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
/* loaded from: classes6.dex */
public class OptionalBridgeManager {
    public static final String ADIRON_CLASS_NAME = "com.bytedance.ttgame.module.adironsource.bridge.AdIronSourceModule";
    public static final String ADPANGLE_CLASS_NAME = "com.bytedance.ttgame.module.adpangle.bridge.AdPangleModule";
    public static final String AD_CLASS_NAME = "com.bytedance.ttgame.module.ad.bridge.AdModule";
    public static final String AD_REWARDVIDEO_CLASS_NAME = "com.bytedance.ttgame.module.rewardvideo.bridge.RewardVideoModule";
    public static final String AGEGATE_CLASS_NAME = "com.bytedance.ttgame.module.compliance.bridge.agegate.AgeGateModule";
    public static final String ANTIADDICTION_CLASS_NAME = "com.bytedance.ttgame.module.compliance.bridge.antiaddiction.AntiAddictionModule";
    public static final String APPSFLYER_CLASS_NAME = "com.bytedance.ttgame.module.appsflyer.bridge.AppsFlyerModule";
    public static final String ASR_CLASS_NAME = "com.bytedance.ttgame.module.asr.bridge.AsrModule";
    public static final String CLOUDGAME_CLASS_NAME = "com.bytedance.ttgame.module.cloudgame.bridge.CloudGameModule";
    public static final String COOKIE_CLASS_NAME = "com.bytedance.ttgame.module.compliance.bridge.cookie.CookieModule";
    public static final String CRASH_CLASS_NAME = "com.bytedance.ttgame.module.crash.bridge.CrashModule";
    public static final String DEBUG_CLASS_NAME = "com.bytedance.ttgame.module.debug.bridge.DebugSdkModule";
    public static final String DEEPLINK_CLASS_NAME = "com.bytedance.ttgame.module.deeplink.bridge.DeeplinkModule";
    public static final String DOWNLOAD_CLASS_NAME = "com.bytedance.ttgame.module.download.bridge.DownloadModule";
    public static final String EMOTICON_CLASS_NAME = "com.bytedance.ttgame.module.emoticon.bridge.EmoticonModule";
    public static final String FIREBASE_CLASS_NAME = "com.bytedance.ttgame.module.firebase.bridge.FirebaseModule";
    public static final String FRIEND_CHAIN_CLASS_NAME = "com.bytedance.ttgame.module.friendchain.bridge.FriendChainModule";
    public static final String GAMEROOM_CLASS_NAME = "com.bytedance.ttgame.module.gameroom.bridge.GameRoomModule";
    public static final String GAME_LIVE_CLASS_NAME = "com.bytedance.ttgame.module.gamelivesdk.bridge.GameLiveModule";
    public static final String GAME_NET_DIAGNOSIS_CLASS_NAME = "com.bytedance.ttgame.module.gna.bridge.GameNetDiagnosisModule";
    public static final String GAME_RTC_CLASS_NAME = "com.bytedance.ttgame.module.rtc.bridge.GameRTCModule";
    public static final String GDTACTION_CLASS_NAME = "com.bytedance.ttgame.module.gdtaction.bridge.GDTActionModule";
    public static final String GEAS_CLASS_NAME = "com.bytedance.ttgame.module.geas.bridge.GeasModule";
    public static final String GNA_CLIENT_CLASS_NAME = "com.bytedance.ttgame.module.gna.bridge.GnaClientModule";
    public static final String IM_CLASS_NAME = "com.bytedance.ttgame.module.im.bridge.ImModule";
    public static final String LIVE_CLASS_NAME = "com.bytedance.ttgame.module.live.bridge.LiveModule";
    public static final String LOCATION_CLASS_NAME = "com.bytedance.ttgame.module.location.bridge.LocationModule";
    public static final String MARKETING_CLASS_NAME = "com.bytedance.ttgame.module.marketing.bridge.MarketingModule";
    public static final String MEDIAUPLOAD_CLASS_NAME = "com.bytedance.ttgame.module.mediaupload.bridge.MediaUploadModule";
    public static final String NET_EXPERIENCE_CLASS_NAME = "com.bytedance.ttgame.module.gna.bridge.NetExperienceModule";
    public static final String NET_MNA_CLASS_NAME = "com.bytedance.ttgame.module.gna.bridge.NetMnaModule";
    public static final String NET_MPA_CLASS_NAME = "com.bytedance.ttgame.module.gna.bridge.NetMpaModule";
    public static final String OSDK_CLASS_NAME = "com.bytedance.ttgame.module.osdk.bridge.OsdkBridgeModule";
    public static final String PREDOWNLOAD_CLASS_NAME = "com.bytedance.ttgame.module.predownload.bridge.PreDownloadModule";
    public static final String PRIVACY_CLASS_NAME = "com.bytedance.ttgame.module.compliance.bridge.privacy.PrivacyModule";
    public static final String PROTOCOL_CLASS_NAME = "com.bytedance.ttgame.module.compliance.bridge.protocol.ProtocolModule";
    public static final String PUSH_CLASS_NAME = "com.bytedance.ttgame.module.push.bridge.PushModule";
    public static final String QRCODE_CLASS_NAME = "com.bytedance.ttgame.module.qrcode.bridge.QRCodeModule";
    public static final String RATING_CLASS_NAME = "com.bytedance.ttgame.module.rating.bridge.RatingModule";
    public static final String REAL_NAME_CLASS_NAME = "com.bytedance.ttgame.module.compliance.bridge.realname.RealNameModule";
    public static final String RN_CLASS_NAME = "com.bytedance.ttgame.module.rn.bridge.ReactNativeModule";
    public static final String RTC_CLASS_NAME = "com.bytedance.ttgame.module.rtc.bridge.RTCModule";
    public static final String SCREEN_RECORD_CLASS_NAME = "com.bytedance.ttgame.module.screenrecord.bridge.ScreenRecordModule";
    public static final String SHARE_CLASS_NAME = "com.bytedance.ttgame.module.share.bridge.ShareModule";
    public static final String THANOS_CLASS_NAME = "com.bytedance.ttgame.module.thanos.bridge.ThanosModule";
    public static final String TRANSLATE_CLASS_NAME = "com.bytedance.ttgame.module.translate.bridge.TranslateModule";
    public static final String UNITE_LIVE_CLASS_NAME = "com.bytedance.ttgame.module.unitelive.bridge.UniteLiveModule";
    public static final String UPGRADE_CLASS_NAME = "com.bytedance.ttgame.module.upgrade.bridge.UpgradeModule";
    public static final String VIDEOUPLOAD_CLASS_NAME = "com.bytedance.ttgame.module.videoupload.bridge.VideoUploadModule";
    public static final String VIDEO_PLAYER_CLASS_NAME = "com.bytedance.ttgame.module.gamevideoplayer.bridge.GameVideoPlayerModule";
    public static final String VOICE_CLASS_NAME = "com.bytedance.ttgame.module.voice.bridge.VoiceModule";
    public static final String WEBVIEW_CLASS_NAME = "com.bytedance.ttgame.module.webview.bridge.WebViewModule";

    public static IBridgeModule getBridgeInstance(String str, String str2, IApplicationProvider iApplicationProvider) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (IBridgeModule) Class.forName(str).getConstructor(String.class, IApplicationProvider.class).newInstance(str2, iApplicationProvider);
        } catch (Exception e2) {
            e2.printStackTrace();
            SdkLog.e("getBridgeInstance", e2.getMessage());
            return null;
        }
    }
}
