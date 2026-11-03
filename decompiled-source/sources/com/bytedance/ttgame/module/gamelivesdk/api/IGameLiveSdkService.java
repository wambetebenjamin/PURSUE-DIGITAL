package com.bytedance.ttgame.module.gamelivesdk.api;

import android.app.Activity;
import android.content.Context;
import com.bytedance.bdinstall.e;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import kotlin.Metadata;
/* compiled from: IGameLiveSdkService.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\bf\u0018\u0000 !2\u00020\u0001:\u0002!\"J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J\b\u0010\u000f\u001a\u00020\u000bH&J\u0012\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H&J \u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH&J\u0012\u0010\u001c\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J \u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001aH&¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLiveSdkService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "createRNTPlayerByType", "Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer;", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "type", "", "playerConfig", "", "floatingViewSize", "", "fullScreenViewSize", "preload", "registerVoiceCallback", "voiceCallback", "Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLiveSdkService$VoiceCallback;", "setApplicationContext", "setGameActivity", "setPlayerStateListener", "IPlayStateService", "Lcom/bytedance/ttgame/module/gamelivesdk/api/IPlayStateService;", "showGameLive", "url", "", e.z, "unregisterVoiceCallback", "updateRoleInfo", "roleID", "roleName", "serverID", "Companion", "VoiceCallback", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IGameLiveSdkService extends IModuleApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int PLAYER_TYPE_H5 = 1;
    public static final int PLAYER_TYPE_NATIVE = 2;

    /* compiled from: IGameLiveSdkService.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLiveSdkService$VoiceCallback;", "", "onVideoConfig", "", "shouldOpenVoice", "", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public interface VoiceCallback {
        void onVideoConfig(boolean z);
    }

    IGameLivePlayer createRNTPlayerByType(Context context, Activity activity, int i2);

    void playerConfig(float f2, float f3);

    void preload();

    void registerVoiceCallback(VoiceCallback voiceCallback);

    void setApplicationContext(Context context);

    void setGameActivity(Activity activity);

    void setPlayerStateListener(IPlayStateService iPlayStateService);

    void showGameLive(Activity activity, String str, String str2);

    void unregisterVoiceCallback(VoiceCallback voiceCallback);

    void updateRoleInfo(String str, String str2, String str3);

    /* compiled from: IGameLiveSdkService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLiveSdkService$Companion;", "", "()V", "PLAYER_TYPE_H5", "", "PLAYER_TYPE_NATIVE", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int PLAYER_TYPE_H5 = 1;
        public static final int PLAYER_TYPE_NATIVE = 2;

        private Companion() {
        }
    }
}
