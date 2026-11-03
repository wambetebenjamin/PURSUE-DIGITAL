package com.bytedance.ttgame.module.account.bridge.mapper;

import com.bytedance.ttgame.module.account.bridge.model.GMAccountBindInfo;
import com.bytedance.ttgame.rocketapi.account.pojo.AccountBindInfo;
/* loaded from: classes4.dex */
public class GMBindInfoMapper {
    public static GMAccountBindInfo transform(AccountBindInfo accountBindInfo) {
        if (accountBindInfo == null) {
            return null;
        }
        GMAccountBindInfo gMAccountBindInfo = new GMAccountBindInfo();
        gMAccountBindInfo.puid = accountBindInfo.puid;
        gMAccountBindInfo.userType = accountBindInfo.userType;
        gMAccountBindInfo.nickname = accountBindInfo.nickname;
        gMAccountBindInfo.avatarUrl = accountBindInfo.avatarUrl;
        gMAccountBindInfo.mobile = accountBindInfo.mobile;
        gMAccountBindInfo.hasPwd = accountBindInfo.hasPwd;
        gMAccountBindInfo.createTime = accountBindInfo.createTime;
        gMAccountBindInfo.encryptedNickname = accountBindInfo.encryptedNickname;
        return gMAccountBindInfo;
    }
}
