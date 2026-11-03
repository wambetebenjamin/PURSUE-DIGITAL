package com.bytedance.ttgame.module.database.api;

import android.text.TextUtils;
import com.bytedance.sdk.account.save.database.a;
import com.bytedance.ttgame.library.luffy.db.PrimaryKeyName;
import com.bytedance.ttgame.library.luffy.db.TableName;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@TableName("user_info")
@NeedEncryption
/* loaded from: classes5.dex */
public class UserInfoData implements Serializable, Cloneable {
    public static final long serialVersionUID = -2338496156989908732L;
    @SerializedName("account_code")
    public String accountCode;
    @SerializedName(a.FIELD_AVATAR_URL)
    @NeedEncryption
    public String avatarUrl;
    @NeedEncryption
    public String awemeName;
    @NeedEncryption
    public String awemeSecPlatformUid;
    @SerializedName("conflict_connect_cnt")
    public int conflictConnectCnt;
    @SerializedName("conflict_nickname")
    public String conflictNickname;
    @SerializedName("conflict_pass_nickname")
    public String conflictPassNickName;
    @SerializedName("conflict_user_id")
    public long conflictUserId;
    @SerializedName("connect_infos_old")
    @NeedEncryption
    public List<ConnectInfoData> connect_infos;
    @SerializedName("connect_infos")
    @NeedEncryption
    public List<ConnectInfoDataNew> connect_infos_new;
    @NeedEncryption
    public String email;
    @SerializedName("encrypted_nickname")
    @NeedEncryption
    public String encryptedNickname;
    @SerializedName("eternal_avatar")
    @NeedEncryption
    public String eternalAvatar;
    @SerializedName("has_pwd")
    public boolean hasPwd;
    @SerializedName("huoshan_name")
    @NeedEncryption
    public String huoshanName;
    @SerializedName("is_bound")
    public boolean isBound;
    @SerializedName(Constants.IS_CLOUD_GAME_LOGIN)
    public int isCloudGameLogin;
    @SerializedName("is_pay")
    public boolean isPay;
    public int isScLogin;
    @SerializedName("is_verified")
    @Deprecated
    public boolean isVerified;
    @SerializedName("login_time")
    public long loginTime;
    public int loginWay;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("multi_bind_status")
    public boolean multiBindStatus;
    @SerializedName("need_parent_verify")
    @Deprecated
    public boolean needParentVerify;
    @SerializedName("nickname")
    @NeedEncryption
    public String nickname;
    @SerializedName("pass_info")
    public PassInfoData passInfoData;
    @SerializedName("sdk_open_id")
    public String sdkOpenId;
    @SerializedName("taptap_name")
    @NeedEncryption
    public String taptapName;
    @SerializedName("token")
    @NeedEncryption
    public String token;
    @NeedEncryption
    public String toutiaoName;
    @NeedEncryption
    public long ttUserId;
    @PrimaryKeyName
    @SerializedName("user_id")
    public long userId;
    @SerializedName("user_type")
    public int userType;
    @SerializedName("client_uuid")
    @NeedEncryption
    public String uuid;
    @SerializedName("xigua_name")
    @NeedEncryption
    public String xiguaName;
    public boolean canBindVisitor = true;
    @SerializedName("encrypted")
    @EncryptionMark
    public boolean encrypted = false;
    @SerializedName("cancel_logoff")
    public boolean cancelLogoff = false;
    public boolean isAwemeNew = true;
    public boolean isToutiaoNew = true;
    @SerializedName("third_logoff")
    public boolean thirdLogoff = false;

    public String toString() {
        return "UserInfoData{userId=" + this.userId + "', nickname='" + this.nickname + "', encryptedNickname='" + this.encryptedNickname + "', avatarUrl='" + this.avatarUrl + "', eternalAvatar='" + this.eternalAvatar + "', token='" + this.token + "', isVerified=" + this.isVerified + "', userType=" + this.userType + ", isPay=" + this.isPay + ", isBound=" + this.isBound + ", loginTime=" + this.loginTime + ", hasPwd=" + this.hasPwd + ", mobile='" + this.mobile + "', sdkOpenId='" + this.sdkOpenId + "', ttUserId=" + this.ttUserId + ", email='" + this.email + "', canBindVisitor=" + this.canBindVisitor + ", connect_infos=" + this.connect_infos + ",connect_infos_new=" + this.connect_infos_new + ", awemeSecPlatformUid='" + this.awemeSecPlatformUid + "', uuid='" + this.uuid + "', multiBindStatus=" + this.multiBindStatus + ", conflictUserId=" + this.conflictUserId + ", isScLogin=" + this.isScLogin + ", accountCode='" + this.accountCode + "', encrypted=" + this.encrypted + ", cancelLogoff=" + this.cancelLogoff + ", isAwemeNew=" + this.isAwemeNew + ", isToutiaoNew=" + this.isToutiaoNew + ", needParentVerify=" + this.needParentVerify + ", passInfoData=" + this.passInfoData + ", thirdLogoff=" + this.thirdLogoff + ", conflictNickname=" + this.conflictNickname + ", conflictConnectCnt=" + this.conflictConnectCnt + '}';
    }

    /* renamed from: clone */
    public UserInfoData m201clone() {
        UserInfoData userInfoData = (UserInfoData) super.clone();
        ArrayList arrayList = new ArrayList();
        List<ConnectInfoData> list = this.connect_infos;
        ArrayList arrayList2 = null;
        if (list != null) {
            for (ConnectInfoData connectInfoData : list) {
                arrayList.add(connectInfoData.m198clone());
            }
        } else {
            arrayList = null;
        }
        ArrayList arrayList3 = new ArrayList();
        List<ConnectInfoDataNew> list2 = this.connect_infos_new;
        if (list2 != null) {
            for (ConnectInfoDataNew connectInfoDataNew : list2) {
                arrayList3.add(connectInfoDataNew.m199clone());
            }
            arrayList2 = arrayList3;
        }
        userInfoData.connect_infos = arrayList;
        userInfoData.connect_infos_new = arrayList2;
        return userInfoData;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserInfoData userInfoData = (UserInfoData) obj;
        return this.userId == userInfoData.userId && this.isVerified == userInfoData.isVerified && this.userType == userInfoData.userType && this.isPay == userInfoData.isPay && this.isBound == userInfoData.isBound && this.loginTime == userInfoData.loginTime && this.hasPwd == userInfoData.hasPwd && this.ttUserId == userInfoData.ttUserId && this.canBindVisitor == userInfoData.canBindVisitor && this.multiBindStatus == userInfoData.multiBindStatus && this.conflictUserId == userInfoData.conflictUserId && this.isScLogin == userInfoData.isScLogin && this.encrypted == userInfoData.encrypted && this.cancelLogoff == userInfoData.cancelLogoff && this.isAwemeNew == userInfoData.isAwemeNew && this.isToutiaoNew == userInfoData.isToutiaoNew && this.needParentVerify == userInfoData.needParentVerify && TextUtils.equals(this.nickname, userInfoData.nickname) && TextUtils.equals(this.encryptedNickname, userInfoData.encryptedNickname) && TextUtils.equals(this.avatarUrl, userInfoData.avatarUrl) && TextUtils.equals(this.eternalAvatar, userInfoData.eternalAvatar) && TextUtils.equals(this.token, userInfoData.token) && TextUtils.equals(this.mobile, userInfoData.mobile) && TextUtils.equals(this.sdkOpenId, userInfoData.sdkOpenId) && TextUtils.equals(this.email, userInfoData.email) && isListEquals(this.connect_infos, userInfoData.connect_infos) && TextUtils.equals(this.awemeSecPlatformUid, userInfoData.awemeSecPlatformUid) && TextUtils.equals(this.uuid, userInfoData.uuid) && TextUtils.equals(this.accountCode, userInfoData.accountCode) && isListEqualsNew(this.connect_infos_new, userInfoData.connect_infos_new) && TextUtils.equals(this.conflictPassNickName, userInfoData.conflictPassNickName) && TextUtils.equals(this.conflictNickname, userInfoData.conflictNickname) && this.conflictConnectCnt == userInfoData.conflictConnectCnt;
    }

    private boolean isListEquals(List<ConnectInfoData> list, List<ConnectInfoData> list2) {
        if ((list == null || list.size() == 0) && (list2 == null || list2.size() == 0)) {
            return true;
        }
        return list.equals(list2);
    }

    private boolean isListEqualsNew(List<ConnectInfoDataNew> list, List<ConnectInfoDataNew> list2) {
        if ((list == null || list.size() == 0) && (list2 == null || list2.size() == 0)) {
            return true;
        }
        return list.equals(list2);
    }
}
