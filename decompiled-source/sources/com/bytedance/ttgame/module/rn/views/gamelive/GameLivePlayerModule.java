package com.bytedance.ttgame.module.rn.views.gamelive;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.ttgame.module.gamelivesdk.api.IGameLivePlayer;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GameLivePlayerModule.kt */
/* loaded from: classes6.dex */
public final class GameLivePlayerModule extends ReactContextBaseJavaModule {
    public static final GameLivePlayerModule INSTANCE = new GameLivePlayerModule();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static IGameLivePlayer player;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "GameLivePlayerModule";
    }

    private GameLivePlayerModule() {
    }

    public final IGameLivePlayer getPlayer() {
        return player;
    }

    public final void setPlayer(IGameLivePlayer iGameLivePlayer) {
        player = iGameLivePlayer;
    }

    @ReactMethod
    public final void loadUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.loadUrl(url);
        }
    }

    @ReactMethod
    public final void play() {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.play();
        }
    }

    @ReactMethod
    public final void pause() {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.pause();
        }
    }

    @ReactMethod
    public final void mute() {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.mute();
        }
    }

    @ReactMethod
    public final void unMute() {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.unMute();
        }
    }

    @ReactMethod
    public final void changePageType(final int i2, final Callback callback) {
        mainHandler.post(new Runnable() { // from class: com.bytedance.ttgame.module.rn.views.gamelive.GameLivePlayerModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameLivePlayerModule.m228changePageType$lambda0(i2, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: changePageType$lambda-0  reason: not valid java name */
    public static final void m228changePageType$lambda0(int i2, Callback callback) {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.changePageType(i2);
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public final void stash(final Callback callback) {
        mainHandler.post(new Runnable() { // from class: com.bytedance.ttgame.module.rn.views.gamelive.GameLivePlayerModule$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GameLivePlayerModule.m230stash$lambda1(Callback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: stash$lambda-1  reason: not valid java name */
    public static final void m230stash$lambda1(Callback callback) {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.stash();
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public final void destroy(final Callback callback) {
        mainHandler.post(new Runnable() { // from class: com.bytedance.ttgame.module.rn.views.gamelive.GameLivePlayerModule$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                GameLivePlayerModule.m229destroy$lambda2(Callback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: destroy$lambda-2  reason: not valid java name */
    public static final void m229destroy$lambda2(Callback callback) {
        IGameLivePlayer iGameLivePlayer = player;
        if (iGameLivePlayer != null) {
            iGameLivePlayer.destroy();
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
        player = null;
    }

    @ReactMethod
    public final void invokeJSMethod(String name, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (readableArray == null) {
            IGameLivePlayer iGameLivePlayer = player;
            if (iGameLivePlayer != null) {
                iGameLivePlayer.invokeJSMethod(name, new String[0]);
                return;
            }
            return;
        }
        IGameLivePlayer iGameLivePlayer2 = player;
        if (iGameLivePlayer2 != null) {
            ArrayList arrayList = new ArrayList();
            int size = readableArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                String string = readableArray.getString(i2);
                if (string == null) {
                    string = "";
                }
                arrayList.add(string);
            }
            Unit unit = Unit.INSTANCE;
            Object[] array = arrayList.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            iGameLivePlayer2.invokeJSMethod(name, (String[]) Arrays.copyOf(strArr, strArr.length));
        }
    }
}
