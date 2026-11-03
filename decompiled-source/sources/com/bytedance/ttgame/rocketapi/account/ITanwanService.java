package com.bytedance.ttgame.rocketapi.account;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.util.HashMap;
/* loaded from: classes7.dex */
public interface ITanwanService extends IModuleApi {
    public static final String GAME_CREATE_ROLE = "game_create_role";
    public static final String GAME_ENTER = "game_enter";
    public static final String GAME_LEVEL_UP = "game_level_up";
    public static final String GAME_LOGOUT = "game_logout";
    public static final String GAME_SELECT_SERVER = "game_select_server";
    public static final String GAME_TUTORIAL_COMPLETION = "game_tutorial_completion";

    void eventReport(Activity activity, String str, HashMap<String, Object> hashMap);

    void eventTrack(Activity activity, String str, HashMap<String, Object> hashMap);

    void sendGameEventLog(String str, String str2, String str3, String str4, String str5, String str6, int i2);

    void ttEventReport(Activity activity, String str, HashMap<String, Object> hashMap);
}
