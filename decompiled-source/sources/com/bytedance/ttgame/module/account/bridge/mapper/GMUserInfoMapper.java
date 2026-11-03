package com.bytedance.ttgame.module.account.bridge.mapper;

import com.bytedance.ttgame.module.account.bridge.model.GMUserInfo;
import com.bytedance.ttgame.rocketapi.account.ExtraData;
import com.bytedance.ttgame.rocketapi.account.GameUserInfo;
/* loaded from: classes4.dex */
public class GMUserInfoMapper {
    private GMUserInfoMapper() {
    }

    public static GMUserInfo transform(ExtraData extraData) {
        if (extraData == null) {
            return null;
        }
        GMUserInfo gMUserInfo = new GMUserInfo();
        gMUserInfo.setGuest(extraData.isGuest()).setUserType(extraData.getUserType()).setVerified(extraData.isVerified()).setSdkOpenId(String.valueOf(extraData.getSdkOpenId())).setUserId(String.valueOf(extraData.getUserId())).setChannel(extraData.getChannel()).setChannelId(extraData.getChannelId()).setConnectInfos(extraData.getI18nBindData()).setConflictUserId(String.valueOf(extraData.getConflictUserId())).setConflictNickname(extraData.getConflictNickname()).setConflictConnectCnt(extraData.getConflictConnectCnt()).setAccountCode(extraData.getAccountCode()).setCancelLogoff(extraData.isCancelLogoff()).setLoginTime(String.valueOf(extraData.getLoginTime())).setNickName(extraData.getNickname()).setEncryptedNickname(extraData.getEncryptedNickname()).setAvatarUrl(extraData.getAvatarUrl()).setEternalAvatar(extraData.getEternalAvatar()).setDeleteAccountStatus(extraData.getDeleteAccountStatus()).setRegister(extraData.isRegister()).setPassInfo(extraData.getPassInfo()).setThirdLogoff(extraData.isThirdLogoff());
        return gMUserInfo;
    }

    public static GMUserInfo transform(GameUserInfo gameUserInfo) {
        if (gameUserInfo == null) {
            return null;
        }
        GMUserInfo gMUserInfo = new GMUserInfo();
        gMUserInfo.setGuest(gameUserInfo.isGuest).setUserType(gameUserInfo.userType).setVerified(gameUserInfo.isVerified).setUserId(gameUserInfo.userId).setChannel(gameUserInfo.channel).setChannelId(gameUserInfo.channelId).setConnectInfos(gameUserInfo.connect_infos).setConflictUserId(gameUserInfo.conflictUserId).setAccountCode(gameUserInfo.accountCode).setLoginTime(String.valueOf(gameUserInfo.loginTime));
        return gMUserInfo;
    }
}
