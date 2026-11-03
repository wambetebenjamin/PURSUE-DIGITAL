package com.bytedance.ttgame.module.gamelivesdk.api;

import android.view.View;
import com.bytedance.sdk.bridge.monitor.a;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: IGameLivePlayer.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\f\bf\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0003H&J)\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0010\"\u00020\u000eH&¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000eH&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&J\b\u0010\u0016\u001a\u00020\u0003H&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0018\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\u0003H&¨\u0006\u001c"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer;", "", "addEventListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer$EventListener;", "asView", "Landroid/view/View;", "changePageType", "type", "", "destroy", "invokeJSMethod", "name", "", "params", "", "(Ljava/lang/String;[Ljava/lang/String;)V", a.STATUS_MSG_JS_LOAD_URL, "url", "mute", "pause", "play", "removeEventListener", "stash", "unMute", "Companion", "EventListener", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IGameLivePlayer {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String EVENT_CHANGE_PAGE_TYPE = "change_page_type";
    public static final String EVENT_CLOSE_PAGE = "close_page";
    public static final String EVENT_CURRENT_SECOND = "current_second";
    public static final String EVENT_ERROR = "error";
    public static final String EVENT_LOAD_URL = "load_url";
    public static final String EVENT_MUTE = "mute";
    public static final String EVENT_PAGE_READY = "page_ready";
    public static final String EVENT_PLAYER_READY = "player_ready";
    public static final String EVENT_STATE_CHANGE = "state_change";

    /* compiled from: IGameLivePlayer.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer$EventListener;", "", "onEvent", "", "name", "", "params", "Lorg/json/JSONObject;", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public interface EventListener {
        void onEvent(String str, JSONObject jSONObject);
    }

    void addEventListener(EventListener eventListener);

    View asView();

    void changePageType(int i2);

    void destroy();

    void invokeJSMethod(String str, String... strArr);

    void loadUrl(String str);

    void mute();

    void pause();

    void play();

    void removeEventListener(EventListener eventListener);

    void stash();

    void unMute();

    /* compiled from: IGameLivePlayer.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer$Companion;", "", "()V", "EVENT_CHANGE_PAGE_TYPE", "", "EVENT_CLOSE_PAGE", "EVENT_CURRENT_SECOND", "EVENT_ERROR", "EVENT_LOAD_URL", "EVENT_MUTE", "EVENT_PAGE_READY", "EVENT_PLAYER_READY", "EVENT_STATE_CHANGE", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String EVENT_CHANGE_PAGE_TYPE = "change_page_type";
        public static final String EVENT_CLOSE_PAGE = "close_page";
        public static final String EVENT_CURRENT_SECOND = "current_second";
        public static final String EVENT_ERROR = "error";
        public static final String EVENT_LOAD_URL = "load_url";
        public static final String EVENT_MUTE = "mute";
        public static final String EVENT_PAGE_READY = "page_ready";
        public static final String EVENT_PLAYER_READY = "player_ready";
        public static final String EVENT_STATE_CHANGE = "state_change";

        private Companion() {
        }
    }
}
