package com.bytedance.ttgame.module.main.bridge.model;

import com.bytedance.ttgame.rocketapi.account.ExtraBindData;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class GMUserInfo {
    private String accountCode;
    private boolean cancelLogoff;
    private String channel;
    private String channelId;
    private String conflictUserId;
    private List<GMConnectInfo> connectInfos;
    private boolean isGuest;
    private boolean isVerified;
    private String loginTime;
    private String userId;
    private int userType;

    /* loaded from: classes6.dex */
    public static class GMConnectInfo {
        public String avatar_url;
        public String encrypted_nickname;
        public String eternal_avatar;
        public String nickname;
        public long puid;
        public int user_type;
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
}
