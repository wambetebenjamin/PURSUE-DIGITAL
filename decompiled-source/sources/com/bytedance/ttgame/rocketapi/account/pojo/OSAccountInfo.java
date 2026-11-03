package com.bytedance.ttgame.rocketapi.account.pojo;
/* loaded from: classes7.dex */
public class OSAccountInfo {
    public boolean isCurrentLogin;
    public OSType osType;
    public String roleAvatar;
    public String roleId;
    public String roleLevel;
    public String roleName;
    public String sdkOpenId;
    public int serverId;
    public String serverName;

    public String toString() {
        return "OSAccountInfo{osType=" + this.osType + ", sdkOpenId='" + this.sdkOpenId + "', isCurrentLogin=" + this.isCurrentLogin + ", roleId='" + this.roleId + "', roleName='" + this.roleName + "', roleLevel='" + this.roleLevel + "', roleAvatar='" + this.roleAvatar + "', serverId=" + this.serverId + ", serverName='" + this.serverName + "'}";
    }
}
