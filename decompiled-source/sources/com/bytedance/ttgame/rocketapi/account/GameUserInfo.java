package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.rocketapi.account.pojo.PassInfo;
import java.util.List;
/* loaded from: classes7.dex */
public class GameUserInfo {
    public String accountCode;
    public String channel;
    public String channelId;
    public String conflictUserId;
    public List<ExtraBindData> connect_infos;
    public boolean isGuest;
    public boolean isVerified;
    public long loginTime;
    public PassInfo passInfo;
    public String userId;
    public int userType;

    public String toString() {
        return "GameUserInfo{userType=" + this.userType + ", isGuest=" + this.isGuest + ", isVerified=" + this.isVerified + ", userId='" + this.userId + "', channel='" + this.channel + "', channelId='" + this.channelId + "', connect_infos=" + this.connect_infos + ", conflictUserId='" + this.conflictUserId + "', accountCode='" + this.accountCode + "', passInfo='" + this.passInfo + "'}";
    }
}
