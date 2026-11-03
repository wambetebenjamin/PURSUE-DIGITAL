package com.bytedance.ttgame.module.gameinfo;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.ExtraInfo;
import com.bytedance.retrofit2.http.GET;
import com.bytedance.retrofit2.http.Query;
import com.bytedance.ttgame.module.gameinfo.api.RoleList;
import com.bytedance.ttgame.module.gameinfo.api.ServerList;
import com.bytedance.ttgame.module.gameinfo.api.ServerRoleList;
import kotlin.Metadata;
/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0001\u0010\b\u001a\u00020\u0001H'JB\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00072\b\b\u0001\u0010\b\u001a\u00020\u0001H'JB\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00072\b\b\u0001\u0010\b\u001a\u00020\u0001H'¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/Request;", "", "getRoleList", "Lcom/bytedance/retrofit2/Call;", "Lcom/bytedance/ttgame/module/gameinfo/GameInfoResponse;", "Lcom/bytedance/ttgame/module/gameinfo/api/RoleList;", "sdkOpenId", "", "extraInfo", "getServerAndRoleList", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerRoleList;", "gameVersion", "extraInfoString", "getServerList", "Lcom/bytedance/ttgame/module/gameinfo/api/ServerList;", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface Request {
    @GET("/gsdk/game/role_list")
    Call<GameInfoResponse<RoleList>> getRoleList(@Query("sdk_open_id") String str, @ExtraInfo Object obj);

    @GET("/gsdk/game/server_role_list")
    Call<GameInfoResponse<ServerRoleList>> getServerAndRoleList(@Query("game_version") String str, @Query("sdk_open_id") String str2, @Query("extra_info") String str3, @ExtraInfo Object obj);

    @GET("/gsdk/game/server_list")
    Call<GameInfoResponse<ServerList>> getServerList(@Query("game_version") String str, @Query("sdk_open_id") String str2, @Query("extra_info") String str3, @ExtraInfo Object obj);
}
