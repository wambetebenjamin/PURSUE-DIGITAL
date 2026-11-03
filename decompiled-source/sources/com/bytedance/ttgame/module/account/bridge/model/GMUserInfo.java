package com.bytedance.ttgame.module.account.bridge.model;

import com.bytedance.ttgame.rocketapi.account.ExtraBindData;
import com.bytedance.ttgame.rocketapi.account.pojo.PassInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class GMUserInfo {
    private String accountCode;
    private String avatarUrl;
    private boolean cancelLogoff;
    private String channel;
    private String channelId;
    private int conflictConnectCnt;
    private String conflictNickname;
    private String conflictUserId;
    private List<GMConnectInfo> connectInfos;
    private int deleteAccountStatus;
    private String encryptedNickname;
    private String eternalAvatar;
    private boolean isGuest;
    private boolean isRegister;
    private boolean isVerified;
    private String loginTime;
    private String nickName;
    private GMPassInfo passInfo;
    private String sdkOpenId;
    private boolean thirdLogoff;
    private String userId;
    private int userType;

    /* loaded from: classes4.dex */
    public static class GMConnectInfo {
        public String avatar_url;
        public String encrypted_nickname;
        public String eternal_avatar;
        public String nickname;
        public long puid;
        public int user_type;
    }

    public String getNickName() {
        return this.nickName;
    }

    public GMUserInfo setNickName(String str) {
        this.nickName = str;
        return this;
    }

    public String getEncryptedNickname() {
        return this.encryptedNickname;
    }

    public GMUserInfo setEncryptedNickname(String str) {
        this.encryptedNickname = str;
        return this;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public GMUserInfo setAvatarUrl(String str) {
        this.avatarUrl = str;
        return this;
    }

    public String getEternalAvatar() {
        return this.eternalAvatar;
    }

    public GMUserInfo setEternalAvatar(String str) {
        this.eternalAvatar = str;
        return this;
    }

    public String getLoginTime() {
        return this.loginTime;
    }

    public GMUserInfo setLoginTime(String str) {
        this.loginTime = str;
        return this;
    }

    public boolean isGuest() {
        return this.isGuest;
    }

    public GMUserInfo setGuest(boolean z) {
        this.isGuest = z;
        return this;
    }

    public int getUserType() {
        return this.userType;
    }

    public GMUserInfo setUserType(int i2) {
        this.userType = i2;
        return this;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public GMUserInfo setVerified(boolean z) {
        this.isVerified = z;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public GMUserInfo setUserId(String str) {
        this.userId = str;
        return this;
    }

    public String getChannel() {
        return this.channel;
    }

    public GMUserInfo setChannel(String str) {
        this.channel = str;
        return this;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public GMUserInfo setChannelId(String str) {
        this.channelId = str;
        return this;
    }

    public List<GMConnectInfo> getConnectInfos() {
        return this.connectInfos;
    }

    public GMUserInfo setConnectInfos(List<ExtraBindData> list) {
        if (list == null || list.isEmpty()) {
            this.connectInfos = null;
        } else {
            this.connectInfos = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                GMConnectInfo gMConnectInfo = new GMConnectInfo();
                gMConnectInfo.user_type = list.get(i2).user_type;
                gMConnectInfo.puid = list.get(i2).userId;
                gMConnectInfo.avatar_url = list.get(i2).avatar_url;
                gMConnectInfo.nickname = list.get(i2).nickname;
                gMConnectInfo.eternal_avatar = list.get(i2).eternalAvatar;
                gMConnectInfo.encrypted_nickname = list.get(i2).encryptedNickname;
                this.connectInfos.add(gMConnectInfo);
            }
        }
        return this;
    }

    public String getConflictUserId() {
        return this.conflictUserId;
    }

    public GMUserInfo setConflictUserId(String str) {
        this.conflictUserId = str;
        return this;
    }

    public GMUserInfo setConflictNickname(String str) {
        this.conflictNickname = str;
        return this;
    }

    public String getConflictNickname() {
        return this.conflictNickname;
    }

    public int getConflictConnectCnt() {
        return this.conflictConnectCnt;
    }

    public GMUserInfo setConflictConnectCnt(int i2) {
        this.conflictConnectCnt = i2;
        return this;
    }

    public String getAccountCode() {
        return this.accountCode;
    }

    public GMUserInfo setAccountCode(String str) {
        this.accountCode = str;
        return this;
    }

    public boolean isCancelLogoff() {
        return this.cancelLogoff;
    }

    public GMUserInfo setCancelLogoff(boolean z) {
        this.cancelLogoff = z;
        return this;
    }

    public int getDeleteAccountStatus() {
        return this.deleteAccountStatus;
    }

    public GMUserInfo setDeleteAccountStatus(int i2) {
        this.deleteAccountStatus = i2;
        return this;
    }

    public boolean isRegister() {
        return this.isRegister;
    }

    public GMUserInfo setRegister(boolean z) {
        this.isRegister = z;
        return this;
    }

    public GMUserInfo setSdkOpenId(String str) {
        this.sdkOpenId = str;
        return this;
    }

    public String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public GMUserInfo setPassInfo(PassInfo passInfo) {
        if (passInfo != null) {
            GMPassInfo gMPassInfo = new GMPassInfo();
            gMPassInfo.passId = passInfo.passId + "";
            gMPassInfo.email = passInfo.email;
            gMPassInfo.logoffStatus = passInfo.logoffStatus;
            gMPassInfo.subStatus = passInfo.subStatus;
            gMPassInfo.nickname = passInfo.nickname;
            gMPassInfo.avatar = passInfo.avatar;
            this.passInfo = gMPassInfo;
        }
        return this;
    }

    public GMPassInfo getPassInfo() {
        return this.passInfo;
    }

    public GMUserInfo setThirdLogoff(boolean z) {
        this.thirdLogoff = z;
        return this;
    }

    public boolean isThirdLogoff() {
        return this.thirdLogoff;
    }
}
