package com.bytedance.ttgame.module.account.bridge.mapper;

import com.bytedance.ttgame.module.account.api.LoginLogger;
import com.bytedance.ttgame.module.account.bridge.model.GMSeamlessLoginInfo;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo;
/* loaded from: classes4.dex */
public class GMSeamlessMapper {
    public static GMSeamlessLoginInfo transform(SeamlessLoginInfo seamlessLoginInfo) {
        if (seamlessLoginInfo == null) {
            LoginLogger.d("GMSeamlessMapper", "seamlessLoginInfo is null");
            return null;
        }
        GMSeamlessLoginInfo gMSeamlessLoginInfo = new GMSeamlessLoginInfo();
        gMSeamlessLoginInfo.userId = seamlessLoginInfo.userId + "";
        gMSeamlessLoginInfo.nickname = seamlessLoginInfo.nickname;
        gMSeamlessLoginInfo.avatarUrl = seamlessLoginInfo.avatarUrl;
        gMSeamlessLoginInfo.token = seamlessLoginInfo.token;
        gMSeamlessLoginInfo.userType = seamlessLoginInfo.userType;
        LoginLogger.d("GMSeamlessMapper", "seamlessLoginInfo transform:" + gMSeamlessLoginInfo.toString());
        return gMSeamlessLoginInfo;
    }
}
