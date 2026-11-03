package com.bytedance.ttgame.module.account.bridge.mapper;

import com.bytedance.ttgame.module.account.bridge.model.GMOSAccountData;
import com.bytedance.ttgame.rocketapi.account.pojo.OSAccountData;
import com.bytedance.ttgame.rocketapi.account.pojo.OSAccountInfo;
import com.bytedance.ttgame.rocketapi.account.pojo.OSType;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class GMOSAccountMapper {
    public static List<GMOSAccountData> transform(OSAccountData oSAccountData) {
        if (oSAccountData == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (OSAccountInfo oSAccountInfo : oSAccountData.osAccountInfos) {
            GMOSAccountData gMOSAccountData = new GMOSAccountData();
            if (oSAccountInfo.osType == OSType.OSTypeIOS) {
                gMOSAccountData.OSType = 0;
            } else if (oSAccountInfo.osType == OSType.OSTypeAndroid) {
                gMOSAccountData.OSType = 1;
            } else if (oSAccountInfo.osType == OSType.OSTypeWindows) {
                gMOSAccountData.OSType = 2;
            } else {
                gMOSAccountData.OSType = 3;
            }
            gMOSAccountData.sdkOpenId = oSAccountInfo.sdkOpenId;
            gMOSAccountData.currentLogin = oSAccountInfo.isCurrentLogin;
            gMOSAccountData.roleId = oSAccountInfo.roleId;
            gMOSAccountData.roleName = oSAccountInfo.roleName;
            gMOSAccountData.roleLevel = oSAccountInfo.roleLevel;
            gMOSAccountData.roleAvatar = oSAccountInfo.roleAvatar;
            gMOSAccountData.serverId = oSAccountInfo.serverId;
            gMOSAccountData.serverName = oSAccountInfo.serverName;
            arrayList.add(gMOSAccountData);
        }
        return arrayList;
    }
}
