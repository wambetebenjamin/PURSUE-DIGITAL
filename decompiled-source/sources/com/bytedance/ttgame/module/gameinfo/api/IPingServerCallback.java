package com.bytedance.ttgame.module.gameinfo.api;

import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
/* compiled from: IGameInfoService.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/IPingServerCallback;", "", "onUpdatePing", "", "serverInfo", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerInfo;", "error", "Lcom/bytedance/ttgame/base/GSDKError;", "finish", "", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IPingServerCallback {
    void onUpdatePing(ServerInfo serverInfo, GSDKError gSDKError, boolean z);
}
