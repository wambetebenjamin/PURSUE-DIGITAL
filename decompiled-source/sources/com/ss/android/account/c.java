package com.ss.android.account;

import com.facebook.share.internal.ShareConstants;
import org.json.JSONObject;
/* compiled from: BDAccountUserEntity.java */
/* loaded from: classes9.dex */
public class c extends com.ss.android.account.a {
    public String area;
    public String birthday;
    public int canFoundByPhone;
    public int canSyncShare;

    /* renamed from: f  reason: collision with root package name */
    public JSONObject f17894f;
    public int gender;
    public boolean hasPassword;
    public String industry;
    public int isBlocked;
    public int isBlocking;
    public boolean isGenerated;
    public boolean isRecommendAllowed;
    public boolean isToutiao;
    public int mAppId;
    public String mBgImgUrl;
    public int mDisplayOcrEntrance;
    public int mFollowersCount;
    public int mFollowingCount;
    public long mMediaId;
    public int mVisitorsCount;
    public String pgcAvatarUrl;
    public long pgcMediaId;
    public String pgcName;
    public String recommendHintMessage;
    public String screenName;
    public int shareShowIcon;
    public int userPrivacyExtend;
    public String user_auth_info;
    public String user_decoration;
    public boolean user_verified;
    public String verifiedContent;

    public c() {
        this.mDisplayOcrEntrance = 0;
    }

    public c(JSONObject jSONObject) {
        super(jSONObject);
        this.mDisplayOcrEntrance = 0;
    }

    public c(JSONObject jSONObject, JSONObject jSONObject2) {
        super(jSONObject, jSONObject2);
        this.mDisplayOcrEntrance = 0;
    }

    @Override // com.ss.android.account.a, com.bytedance.sdk.account.user.c
    public void extract() {
        super.extract();
        innerParseUserInfo(this, getUserData());
    }

    public static void innerParseUserInfo(c cVar, JSONObject jSONObject) {
        cVar.canFoundByPhone = jSONObject.optInt("can_be_found_by_phone");
        cVar.canSyncShare = jSONObject.optInt("share_to_repost", -1);
        cVar.shareShowIcon = jSONObject.optInt("user_privacy_extend") & 1;
        cVar.userPrivacyExtend = jSONObject.optInt("user_privacy_extend");
        cVar.gender = jSONObject.optInt("gender");
        cVar.screenName = jSONObject.optString("screen_name");
        cVar.verifiedContent = jSONObject.optString("verified_content");
        cVar.isGenerated = jSONObject.optBoolean("is_generated");
        cVar.user_verified = jSONObject.optBoolean("user_verified");
        cVar.isRecommendAllowed = jSONObject.optInt("is_recommend_allowed") != 0;
        cVar.recommendHintMessage = jSONObject.optString("recommend_hint_message");
        cVar.user_decoration = jSONObject.optString("user_decoration");
        cVar.user_auth_info = jSONObject.optString("user_auth_info");
        cVar.birthday = jSONObject.optString("birthday");
        cVar.area = jSONObject.optString("area");
        cVar.industry = jSONObject.optString("industry");
        cVar.isBlocked = jSONObject.optInt("is_blocked");
        cVar.isBlocking = jSONObject.optInt("is_blocking");
        cVar.isToutiao = jSONObject.optBoolean("is_toutiao");
        cVar.hasPassword = jSONObject.optInt("has_password") != 0;
        JSONObject optJSONObject = jSONObject.optJSONObject(ShareConstants.WEB_DIALOG_PARAM_MEDIA);
        if (optJSONObject != null) {
            cVar.pgcAvatarUrl = optJSONObject.optString(com.bytedance.sdk.account.save.database.a.FIELD_AVATAR_URL);
            cVar.pgcMediaId = optJSONObject.optLong("id");
            cVar.pgcName = optJSONObject.optString("name");
            cVar.mDisplayOcrEntrance = optJSONObject.optInt("display_app_ocr_entrance", 0);
        }
        cVar.mFollowingCount = jSONObject.optInt("followings_count");
        cVar.mFollowersCount = jSONObject.optInt("followers_count");
        cVar.mVisitorsCount = jSONObject.optInt("visit_count_recent");
        cVar.mMediaId = jSONObject.optLong("media_id");
        cVar.mBgImgUrl = jSONObject.optString("bg_img_url");
        cVar.mAppId = jSONObject.optInt("app_id");
        cVar.f17894f = jSONObject.optJSONObject("expend_attrs");
    }

    /* compiled from: BDAccountUserEntity.java */
    /* loaded from: classes9.dex */
    public static class a implements com.bytedance.sdk.account.user.b<c> {
        @Override // com.bytedance.sdk.account.user.b
        public c parseUserInfo(JSONObject jSONObject) {
            c cVar = new c(jSONObject);
            cVar.extract();
            return cVar;
        }

        @Override // com.bytedance.sdk.account.user.b
        public c parseUserInfo(JSONObject jSONObject, JSONObject jSONObject2) {
            c cVar = new c(jSONObject, jSONObject2);
            cVar.extract();
            return cVar;
        }
    }
}
