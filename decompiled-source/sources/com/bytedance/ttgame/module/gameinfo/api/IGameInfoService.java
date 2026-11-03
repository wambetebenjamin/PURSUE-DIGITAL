package com.bytedance.ttgame.module.gameinfo.api;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import gsdk.impl.main.DEFAULT.g;
import java.util.List;
import kotlin.Metadata;
/* compiled from: IGameInfoService.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0019J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J \u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\"\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0005H&J*\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH&J4\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH&J,\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000bH&J\"\u0010\u000e\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0005H&J*\u0010\u000e\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH&J4\u0010\u000e\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH&J,\u0010\u000e\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000bH&J \u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u0013H&J\"\u0010\u0014\u001a\u00020\u00032\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\b\u0010\u0004\u001a\u0004\u0018\u00010\u0018H&J*\u0010\u0014\u001a\u00020\u00032\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00162\b\u0010\u0004\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u001a"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/IGameInfoService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getRoleList", "", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/gameinfo/api/IGameInfoService$GetGameInfoCallback;", "Lcom/bytedance/ttgame/module/gameinfo/api/RoleList;", "timeout", "", "getServerAndRoleList", "gameVersion", "", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerRoleList;", "extraInfo", "getServerList", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerList;", g.b.f20443c, "domain", "count", "", "pingServerList", "serverList", "", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerInfo;", "Lcom/bytedance/ttgame/module/gameinfo/api/IPingServerCallback;", "GetGameInfoCallback", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IGameInfoService extends IModuleApi {

    /* compiled from: IGameInfoService.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u0017\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/api/IGameInfoService$GetGameInfoCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "onFail", "", "error", "Lcom/bytedance/ttgame/base/GSDKError;", "onSuccess", "gameInfo", "(Ljava/lang/Object;)V", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes6.dex */
    public interface GetGameInfoCallback<T> {
        void onFail(GSDKError gSDKError);

        void onSuccess(T t);
    }

    void getRoleList(GetGameInfoCallback<RoleList> getGameInfoCallback);

    void getRoleList(GetGameInfoCallback<RoleList> getGameInfoCallback, double d2);

    void getServerAndRoleList(String str, GetGameInfoCallback<ServerRoleList> getGameInfoCallback);

    void getServerAndRoleList(String str, GetGameInfoCallback<ServerRoleList> getGameInfoCallback, double d2);

    void getServerAndRoleList(String str, GetGameInfoCallback<ServerRoleList> getGameInfoCallback, double d2, String str2);

    void getServerAndRoleList(String str, GetGameInfoCallback<ServerRoleList> getGameInfoCallback, String str2);

    void getServerList(String str, GetGameInfoCallback<ServerList> getGameInfoCallback);

    void getServerList(String str, GetGameInfoCallback<ServerList> getGameInfoCallback, double d2);

    void getServerList(String str, GetGameInfoCallback<ServerList> getGameInfoCallback, double d2, String str2);

    void getServerList(String str, GetGameInfoCallback<ServerList> getGameInfoCallback, String str2);

    String pingMethod(String str, int i2, int i3);

    void pingServerList(List<ServerInfo> list, IPingServerCallback iPingServerCallback);

    void pingServerList(List<ServerInfo> list, IPingServerCallback iPingServerCallback, double d2);
}
