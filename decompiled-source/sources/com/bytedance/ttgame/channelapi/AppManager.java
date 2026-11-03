package com.bytedance.ttgame.channelapi;

import com.bytedance.ttgame.main.internal.net.IRetrofit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
/* compiled from: AppManager.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR&\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/channelapi/AppManager;", "", "()V", "eventListener", "Lcom/bytedance/ttgame/channelapi/IEventListener;", "getEventListener$annotations", "getEventListener", "()Lcom/bytedance/ttgame/channelapi/IEventListener;", "setEventListener", "(Lcom/bytedance/ttgame/channelapi/IEventListener;)V", "retrofit", "Lcom/bytedance/ttgame/main/internal/net/IRetrofit;", "getRetrofit$annotations", "getRetrofit", "()Lcom/bytedance/ttgame/main/internal/net/IRetrofit;", "setRetrofit", "(Lcom/bytedance/ttgame/main/internal/net/IRetrofit;)V", "channel_api_i18n_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AppManager {
    public static final AppManager INSTANCE = new AppManager();
    private static IEventListener eventListener;
    private static IRetrofit retrofit;

    @JvmStatic
    public static /* synthetic */ void getEventListener$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getRetrofit$annotations() {
    }

    private AppManager() {
    }

    public static final IEventListener getEventListener() {
        return eventListener;
    }

    public static final void setEventListener(IEventListener iEventListener) {
        eventListener = iEventListener;
    }

    public static final IRetrofit getRetrofit() {
        return retrofit;
    }

    public static final void setRetrofit(IRetrofit iRetrofit) {
        retrofit = iRetrofit;
    }
}
