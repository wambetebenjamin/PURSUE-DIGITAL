package com.bytedance.ttgame.module.gamelivesdk.api;

import kotlin.Metadata;
/* compiled from: IPlayStateService.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H&¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/module/gamelivesdk/api/IPlayStateService;", "", "onChangePageType", "", "type", "", "onClosePage", "onCurrentSecond", "second", "", "onError", "error", "onMute", "isMute", "", "onPageReady", "onPlayerReady", "onStateChange", "state", "unitelive_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IPlayStateService {
    void onChangePageType(int i2);

    void onClosePage();

    void onCurrentSecond(float f2);

    void onError(int i2);

    void onMute(boolean z);

    void onPageReady();

    void onPlayerReady();

    void onStateChange(int i2);
}
