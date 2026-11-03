package com.bytedance.ttgame.module.account.api;
/* loaded from: classes3.dex */
public class ChannelUserInfo {
    private int age;
    private String anti_addiction_tips;
    private String avatarUrl;
    public String awemeName;
    public String awemeSecPlatformUid;
    public boolean canBindVisitor;
    private String email;
    private String encryptedNickname;
    public boolean hasPwd;
    private boolean isBound;
    private boolean isPay;
    private boolean isVerified;
    private long loginTime;
    public String mobile;
    private String nickname;
    private String sdkOpenId;
    private String token;
    public String toutiaoName;
    private long userId;
    private int userType;

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

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int i2) {
        this.userType = i2;
    }

    public boolean isPay() {
        return this.isPay;
    }

    public void setPay(boolean z) {
        this.isPay = z;
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

    public String getSdkOpenId() {
        return this.sdkOpenId;
    }

    public void setSdkOpenId(String str) {
        this.sdkOpenId = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getAwemeName() {
        return this.awemeName;
    }

    public void setAwemeName(String str) {
        this.awemeName = str;
    }

    public String getToutiaoName() {
        return this.toutiaoName;
    }

    public void setToutiaoName(String str) {
        this.toutiaoName = str;
    }

    public boolean isCanBindVisitor() {
        return this.canBindVisitor;
    }

    public void setCanBindVisitor(boolean z) {
        this.canBindVisitor = z;
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

    public String getAwemeSecPlatformUid() {
        return this.awemeSecPlatformUid;
    }

    public String toString() {
        return "UserInfoData{userId=" + this.userId + ", nickname='" + this.nickname + "', encryptedNickname='" + this.encryptedNickname + "', avatarUrl='" + this.avatarUrl + "', token='" + this.token + "', isVerified=" + this.isVerified + ", userType=" + this.userType + ", isPay=" + this.isPay + ", isBound=" + this.isBound + ", loginTime=" + this.loginTime + ", userUniqueId='" + this.sdkOpenId + "', loginTime=" + this.loginTime + ", email='" + this.email + "', toutiaoName='" + this.toutiaoName + "', awemeName='" + this.awemeName + "', canBindVisitor='" + this.canBindVisitor + "', age='" + this.age + "', anti_addiction_tips='" + this.anti_addiction_tips + "'}";
    }
}
