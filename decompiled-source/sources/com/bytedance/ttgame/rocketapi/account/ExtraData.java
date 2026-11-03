package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.rocketapi.account.pojo.PassInfo;
import java.util.List;
/* loaded from: classes7.dex */
public class ExtraData {
    private String accountCode;
    private int age;
    private String anti_addiction_tips;
    private String avatarUrl;
    private int bindStatus;
    private int bindType;
    private String channel;
    private String channelId;
    private int conflictConnectCnt;
    private String conflictNickname;
    private long conflictUserId;
    private int deleteAccountStatus;
    private String encryptedNickname;
    private String eternalAvatar;
    private boolean hasPwd;
    private int identityType;
    private boolean isBound;
    private boolean isGuest;
    private boolean isGuestEntrance;
    private boolean isRegister;
    private boolean isVerified;
    private long loginTime;
    private List<ExtraBindData> mI18nBindData;
    private String mobile;
    private String nickname;
    private PassInfo passInfo;
    private long sdkOpenId;
    private String token;
    private long ttUserId;
    private long userId;
    private int userType;
    private boolean thirdLogoff = false;
    private boolean cancelLogoff = false;

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String str) {
        this.channelId = str;
    }

    public boolean isGuestEntrance() {
        return this.isGuestEntrance;
    }

    public void setGuestEntrance(boolean z) {
        this.isGuestEntrance = z;
    }

    public int getIdentityType() {
        return this.identityType;
    }

    public void setIdentityType(int i2) {
        this.identityType = i2;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j2) {
        this.userId = j2;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getEncryptedNickname() {
        return this.encryptedNickname;
    }

    public void setEncryptedNickname(String str) {
        this.encryptedNickname = str;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.avatarUrl = str;
    }

    public String getEternalAvatar() {
        return this.eternalAvatar;
    }

    public void setEternalAvatar(String str) {
        this.eternalAvatar = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public void setVerified(boolean z) {
        this.isVerified = z;
    }

    public boolean isBound() {
        return this.isBound;
    }

    public void setBound(boolean z) {
        this.isBound = z;
    }

    public long getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(long j2) {
        this.loginTime = j2;
    }

    public boolean isHasPwd() {
        return this.hasPwd;
    }

    public void setHasPwd(boolean z) {
        this.hasPwd = z;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public long getTtUserId() {
        return this.ttUserId;
    }

    public void setTtUserId(long j2) {
        this.ttUserId = j2;
    }

    public boolean isGuest() {
        return this.isGuest;
    }

    public void setGuest(boolean z) {
        this.isGuest = z;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int i2) {
        this.userType = i2;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i2) {
        this.age = i2;
    }

    public String getAnti_addiction_tips() {
        return this.anti_addiction_tips;
    }

    public void setAnti_addiction_tips(String str) {
        this.anti_addiction_tips = str;
    }

    public int getBindType() {
        return this.bindType;
    }

    public void setBindType(int i2) {
        this.bindType = i2;
    }

    public int getBindStatus() {
        return this.bindStatus;
    }

    public void setBindStatus(int i2) {
        this.bindStatus = i2;
    }

    public List<ExtraBindData> getI18nBindData() {
        return this.mI18nBindData;
    }

    public void setI18nBindData(List<ExtraBindData> list) {
        this.mI18nBindData = list;
    }

    public long getConflictUserId() {
        return this.conflictUserId;
    }

    public void setConflictUserId(long j2) {
        this.conflictUserId = j2;
    }

    public String getConflictNickname() {
        return this.conflictNickname;
    }

    public void setConflictNickname(String str) {
        this.conflictNickname = str;
    }

    public int getConflictConnectCnt() {
        return this.conflictConnectCnt;
    }

    public void setConflictConnectCnt(int i2) {
        this.conflictConnectCnt = i2;
    }

    public String getAccountCode() {
        return this.accountCode;
    }

    public void setAccountCode(String str) {
        this.accountCode = str;
    }

    public boolean isCancelLogoff() {
        return this.cancelLogoff;
    }

    public void setCancelLogoff(boolean z) {
        this.cancelLogoff = z;
    }

    public int getDeleteAccountStatus() {
        return this.deleteAccountStatus;
    }

    public void setDeleteAccountStatus(int i2) {
        this.deleteAccountStatus = i2;
    }

    public void setSdkOpenId(long j2) {
        this.sdkOpenId = j2;
    }

    public long getSdkOpenId() {
        return this.sdkOpenId;
    }

    public boolean isRegister() {
        return this.isRegister;
    }

    public void setRegister(boolean z) {
        this.isRegister = z;
    }

    public PassInfo getPassInfo() {
        return this.passInfo;
    }

    public void setPassInfo(PassInfo passInfo) {
        this.passInfo = passInfo;
    }

    public void setThirdLogoff(boolean z) {
        this.thirdLogoff = z;
    }

    public boolean isThirdLogoff() {
        return this.thirdLogoff;
    }

    public String toString() {
        return "ExtraData{isGuest=" + this.isGuest + ", userType=" + this.userType + ", identityType=" + this.identityType + ", channel='" + this.channel + "', channelId='" + this.channelId + "', userId=" + this.userId + ", age=" + this.age + ", anti_addiction_tips='" + this.anti_addiction_tips + "', nickname='" + this.nickname + "', encryptedNickname='" + this.encryptedNickname + "', avatarUrl='" + this.avatarUrl + "', eternalAvatar='" + this.eternalAvatar + "', token='" + this.token + "', isVerified=" + this.isVerified + ", isBound=" + this.isBound + ", loginTime=" + this.loginTime + ", hasPwd=" + this.hasPwd + ", mobile='" + this.mobile + "', ttUserId=" + this.ttUserId + ", isGuestEntrance=" + this.isGuestEntrance + ", mI18nBindData=" + this.mI18nBindData + ", bindType=" + this.bindType + ", bindStatus=" + this.bindStatus + ", accountCode=" + this.accountCode + ", cancelLogoff=" + this.cancelLogoff + ",sdkOpenId=" + this.sdkOpenId + ", isRegister=" + this.isRegister + ", passInfo=" + this.passInfo + ", thirdLogoff=" + this.thirdLogoff + ", conflictNickname=" + this.conflictNickname + ", conflictUserId=" + this.conflictUserId + ", conflictConnectCnt=" + this.conflictConnectCnt + '}';
    }
}
