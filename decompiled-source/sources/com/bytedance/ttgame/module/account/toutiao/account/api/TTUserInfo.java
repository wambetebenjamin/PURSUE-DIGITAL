package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.ttgame.module.database.api.ConnectInfoDataNew;
import com.bytedance.ttgame.module.database.api.PassInfoData;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class TTUserInfo {
    public static final int USER_TYPE_GUEST = 1;
    public String accountCode;
    private int age;
    private String anti_addiction_tips;
    private String avatarUrl;
    public String awemeSecPlatformUid;
    private int bindStatus;
    private int bindType;
    public boolean canBindVisitor;
    private boolean cancelLogoff = false;
    private int conflictConnectCnt;
    private String conflictNickname;
    private long conflictUserId;
    private List<TTBindInfo> connect_infos;
    private String email;
    private String encryptedNickname;
    private String eternalAvatar;
    public boolean hasPwd;
    private boolean isBound;
    private boolean isPay;
    private boolean isVerified;
    private long loginTime;
    public String mobile;
    private String nickname;
    private long passportUid;
    private String sdkOpenId;
    private boolean thirdLogoff;
    private String token;
    private TTPassInfo ttPassInfo;
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

    public List<TTBindInfo> getConnect_infos() {
        return this.connect_infos;
    }

    public void setConnectInfos(List<ConnectInfoDataNew> list) {
        if (list == null || list.isEmpty()) {
            this.connect_infos = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) != null) {
                TTBindInfo tTBindInfo = new TTBindInfo();
                tTBindInfo.user_type = list.get(i2).user_type;
                tTBindInfo.userId = list.get(i2).puid;
                tTBindInfo.nickname = list.get(i2).nickname;
                tTBindInfo.avatar_url = list.get(i2).avatar_url;
                tTBindInfo.eternalAvatar = list.get(i2).eternalAvatar;
                tTBindInfo.encryptedNickname = list.get(i2).encryptedNickname;
                arrayList.add(tTBindInfo);
            }
        }
        this.connect_infos = arrayList;
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

    public long getConflictUserId() {
        return this.conflictUserId;
    }

    public void setConflictUserId(long j2) {
        this.conflictUserId = j2;
    }

    public void setConflictNickname(String str) {
        this.conflictNickname = str;
    }

    public String getConflictNickname() {
        return this.conflictNickname;
    }

    public void setConflictConnectCnt(int i2) {
        this.conflictConnectCnt = i2;
    }

    public int getConflictConnectCnt() {
        return this.conflictConnectCnt;
    }

    public long getPassportUid() {
        return this.passportUid;
    }

    public void setPassportUid(long j2) {
        this.passportUid = j2;
    }

    public boolean isCancelLogoff() {
        return this.cancelLogoff;
    }

    public void setCancelLogoff(boolean z) {
        this.cancelLogoff = z;
    }

    public void setPassInfo(PassInfoData passInfoData) {
        if (passInfoData == null) {
            this.ttPassInfo = null;
            return;
        }
        TTPassInfo tTPassInfo = new TTPassInfo();
        tTPassInfo.passId = passInfoData.passId;
        tTPassInfo.nickname = passInfoData.nickname;
        tTPassInfo.email = passInfoData.email;
        tTPassInfo.subStatus = passInfoData.subStatus;
        tTPassInfo.logoffStatus = passInfoData.logoffStatus;
        tTPassInfo.avatar = passInfoData.avatar;
        this.ttPassInfo = tTPassInfo;
    }

    public TTPassInfo getPassInfo() {
        return this.ttPassInfo;
    }

    public boolean isThirdLogoff() {
        return this.thirdLogoff;
    }

    public void setThirdLogoff(boolean z) {
        this.thirdLogoff = z;
    }

    public List<ConnectInfoDataNew> parseConnectInfos(List<TTBindInfo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            ConnectInfoDataNew connectInfoDataNew = new ConnectInfoDataNew();
            TTBindInfo tTBindInfo = list.get(i2);
            if (tTBindInfo != null) {
                connectInfoDataNew.user_type = tTBindInfo.user_type;
                connectInfoDataNew.puid = tTBindInfo.userId;
                connectInfoDataNew.nickname = tTBindInfo.nickname;
                connectInfoDataNew.avatar_url = tTBindInfo.avatar_url;
                connectInfoDataNew.eternalAvatar = tTBindInfo.eternalAvatar;
                connectInfoDataNew.encryptedNickname = tTBindInfo.encryptedNickname;
                arrayList.add(connectInfoDataNew);
            }
        }
        return arrayList;
    }

    public String toString() {
        return "UserInfoData{userId=" + this.userId + ", nickname='" + this.nickname + "', encryptedNickname='" + this.encryptedNickname + "', avatarUrl='" + this.avatarUrl + "', eternalAvatar='" + this.eternalAvatar + "', token='" + this.token + "', isVerified=" + this.isVerified + ", userType=" + this.userType + ", isPay=" + this.isPay + ", isBound=" + this.isBound + ", loginTime=" + this.loginTime + ", userUniqueId='" + this.sdkOpenId + "', loginTime=" + this.loginTime + ", email='" + this.email + "', canBindVisitor='" + this.canBindVisitor + "', age='" + this.age + "', anti_addiction_tips='" + this.anti_addiction_tips + "', connect_infos='" + this.connect_infos + "', accountCode='" + this.accountCode + "', cancelLogoff='" + this.cancelLogoff + "', thirdLogoff='" + this.thirdLogoff + "'}";
    }
}
