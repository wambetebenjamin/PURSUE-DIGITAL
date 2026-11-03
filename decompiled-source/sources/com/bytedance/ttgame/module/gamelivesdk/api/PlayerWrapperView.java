package com.bytedance.ttgame.module.gamelivesdk.api;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.bytedance.sdk.bridge.monitor.a;
import com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.twitter.sdk.android.core.internal.VineCardUtils;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PlayerWrapperView.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016J)\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00142\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140 \"\u00020\u0014H\u0016¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010#\u001a\u00020\u0016H\u0016J\b\u0010$\u001a\u00020\u0016H\u0016J\b\u0010\u0010\u001a\u00020\u0016H\u0016J\u0010\u0010%\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u000e\u0010&\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0002J\b\u0010'\u001a\u00020\u0016H\u0016J\b\u0010(\u001a\u00020\u0016H\u0016R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/PlayerWrapperView;", "Landroid/widget/FrameLayout;", "Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "listeners", "Ljava/util/ArrayList;", "Lcom/bytedance/ttgame/module/gamelivesdk/api/IGameLivePlayer$EventListener;", "Lkotlin/collections/ArrayList;", "play", "", VineCardUtils.PLAYER_CARD, "url", "", "addEventListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "asView", "Landroid/view/View;", "changePageType", "type", "destroy", "invokeJSMethod", "name", "params", "", "(Ljava/lang/String;[Ljava/lang/String;)V", a.STATUS_MSG_JS_LOAD_URL, "mute", "pause", "removeEventListener", "setPlayer", "stash", "unMute", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PlayerWrapperView extends FrameLayout implements IGameLivePlayer {
    private final ArrayList<IGameLivePlayer.EventListener> listeners;
    private boolean play;
    private IGameLivePlayer player;
    private String url;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerWrapperView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.listeners = new ArrayList<>();
        this.url = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerWrapperView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.listeners = new ArrayList<>();
        this.url = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerWrapperView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.listeners = new ArrayList<>();
        this.url = "";
    }

    public final void setPlayer(IGameLivePlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        if (this.player != null) {
            throw new IllegalStateException("player has been set");
        }
        this.player = player;
        addView(player.asView());
        for (IGameLivePlayer.EventListener eventListener : this.listeners) {
            player.addEventListener(eventListener);
        }
        this.listeners.clear();
        if (this.url.length() > 0) {
            player.loadUrl(this.url);
        }
        if (this.play) {
            player.play();
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void loadUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.loadUrl(url);
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void play() {
        Unit unit;
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.play();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            this.play = true;
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void pause() {
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.pause();
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void mute() {
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.mute();
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void unMute() {
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.unMute();
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void destroy() {
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.destroy();
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void stash() {
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.stash();
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void changePageType(int i2) {
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.changePageType(i2);
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void invokeJSMethod(String name, String... params) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(params, "params");
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.invokeJSMethod(name, (String[]) Arrays.copyOf(params, params.length));
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void addEventListener(IGameLivePlayer.EventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.addEventListener(listener);
        } else {
            this.listeners.add(listener);
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public void removeEventListener(IGameLivePlayer.EventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        IGameLivePlayer iGameLivePlayer = this.player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.removeEventListener(listener);
        } else {
            this.listeners.remove(listener);
        }
    }

    @Override // com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer
    public View asView() {
        return this;
    }
}
