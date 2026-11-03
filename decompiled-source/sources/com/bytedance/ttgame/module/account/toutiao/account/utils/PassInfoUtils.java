package com.bytedance.ttgame.module.account.toutiao.account.utils;

import com.bytedance.ttgame.module.account.toutiao.account.api.TTPassInfo;
import com.bytedance.ttgame.module.database.api.PassInfoData;
import com.bytedance.ttgame.rocketapi.account.pojo.PassInfo;
/* loaded from: classes4.dex */
public class PassInfoUtils {
    public static PassInfo TTPassInfoToPassInfo(TTPassInfo tTPassInfo) {
        if (tTPassInfo != null) {
            PassInfo passInfo = new PassInfo();
            passInfo.passId = tTPassInfo.passId;
            passInfo.nickname = tTPassInfo.nickname;
            passInfo.email = tTPassInfo.email;
            passInfo.avatar = tTPassInfo.avatar;
            passInfo.subStatus = tTPassInfo.subStatus;
            passInfo.logoffStatus = tTPassInfo.logoffStatus;
            return passInfo;
        }
        return null;
    }

    public static PassInfo PassInfoDataToPassInfo(PassInfoData passInfoData) {
        if (passInfoData != null) {
            PassInfo passInfo = new PassInfo();
            passInfo.passId = passInfoData.passId;
            passInfo.nickname = passInfoData.nickname;
            passInfo.email = passInfoData.email;
            passInfo.avatar = passInfoData.avatar;
            passInfo.subStatus = passInfoData.subStatus;
            passInfo.logoffStatus = passInfoData.logoffStatus;
            return passInfo;
        }
        return null;
    }

    public static PassInfoData ttPassInfoToPassInfoData(TTPassInfo tTPassInfo) {
        if (tTPassInfo == null) {
            return null;
        }
        PassInfoData passInfoData = new PassInfoData();
        passInfoData.passId = tTPassInfo.passId;
        passInfoData.nickname = tTPassInfo.nickname;
        passInfoData.email = tTPassInfo.email;
        passInfoData.subStatus = tTPassInfo.subStatus;
        passInfoData.logoffStatus = tTPassInfo.logoffStatus;
        passInfoData.avatar = tTPassInfo.avatar;
        return passInfoData;
    }

    public static PassInfo ttPassInfoToPassInfo(TTPassInfo tTPassInfo) {
        if (tTPassInfo == null) {
            return null;
        }
        PassInfo passInfo = new PassInfo();
        passInfo.passId = tTPassInfo.passId;
        passInfo.nickname = tTPassInfo.nickname;
        passInfo.email = tTPassInfo.email;
        passInfo.subStatus = tTPassInfo.subStatus;
        passInfo.logoffStatus = tTPassInfo.logoffStatus;
        passInfo.avatar = tTPassInfo.avatar;
        return passInfo;
    }
}
