package com.bytedance.sdk.account.api;

import java.util.HashMap;
import java.util.List;
/* compiled from: IBDAccount.java */
/* loaded from: classes2.dex */
public interface h {
    void a(String str, int i2, boolean z);

    void a(List<String> list);

    void addHasUpdateSecUids(String str);

    void addListener(e eVar);

    void clearMultiSid();

    String getAvatarUrl();

    String getBgImgUrl();

    int getCanFoundByPhone();

    int getCanSyncShare();

    int getCountryCode();

    int getDisplayOcrEntrance();

    int getFollowersCount();

    int getFollowingCount();

    boolean getHasPassword();

    long getMediaId();

    String getMultiSid();

    int getOdinUserType();

    String getPgcAvatarUrl();

    long getPgcMediaId();

    String getPgcName();

    com.ss.android.account.model2.a getPlatformByName(String str);

    String getRecommendHintMessage();

    String getScreenName();

    String getSecUserId();

    String getSessionKey();

    int getShareShowIcon();

    String getUserArea();

    String getUserAuth();

    String getUserBirthday();

    String getUserDecoration();

    String getUserDescription();

    String getUserEmail();

    int getUserGender();

    long getUserId();

    String getUserIndustry();

    com.bytedance.sdk.account.user.c getUserInfo();

    int getUserIsBlock();

    int getUserIsBlocking();

    String getUserMobile();

    String getUserName();

    int getUserPrivacyExtend();

    String getVerifiedContent();

    int getVisitorsCount();

    String getXttSessionSign();

    boolean hasUpdateSecUid(String str);

    void invalidateSession(boolean z);

    boolean isKidsMode();

    boolean isLogin();

    boolean isRecommendAllowed();

    boolean isUserToutiao();

    boolean isUserVerified();

    boolean isVisitorAccount();

    void notifyBDAccountEvent(d dVar);

    void onReceiveLongConnectionMessage(String str);

    void onUserInfoRefreshed(com.bytedance.sdk.account.user.c cVar, boolean z);

    void removeListener(e eVar);

    void saveData();

    void setAvatarUrl(String str);

    void setBgImgUrl(String str);

    void setCanFoundByPhone(int i2);

    void setCanSyncShare(int i2);

    void setCountryCode(int i2);

    void setFollowersCount(int i2);

    void setFollowingCount(int i2);

    void setHasPassword(boolean z);

    void setLogin(boolean z);

    void setMediaId(long j2);

    void setOdinUserType(int i2);

    void setPgcAvatarUrl(String str);

    void setPgcMediaId(long j2);

    void setPgcName(String str);

    void setPlatformMap(HashMap<String, com.ss.android.account.model2.a> hashMap);

    void setRecommendAllowed(boolean z);

    void setRecommendHintMessage(String str);

    void setScreenName(String str);

    void setSecUserId(String str);

    void setSessionKey(String str);

    void setShareShowIcon(int i2);

    void setUserArea(String str);

    void setUserAuth(String str);

    void setUserBirthday(String str);

    void setUserDecoration(String str);

    void setUserDescription(String str);

    void setUserEmail(String str);

    void setUserGender(int i2);

    void setUserId(long j2);

    void setUserIndustry(String str);

    void setUserIsBlock(int i2);

    void setUserIsBlocking(int i2);

    void setUserMobile(String str);

    void setUserName(String str);

    void setUserPrivacyExtend(int i2);

    void setUserToutiao(boolean z);

    void setUserVerified(boolean z);

    void setVerifiedContent(String str);

    void setVisitorAccount(boolean z);

    void setVisitorsCount(int i2);

    void setXttSessionSign(String str);

    void stopUpdateUserInfo();

    void tryUpdateUserInfo(String str);

    void updateMultiSid(String str);

    void updateUserInfo(com.bytedance.sdk.account.user.c cVar);

    void wapLoginSync();
}
