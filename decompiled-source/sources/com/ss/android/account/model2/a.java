package com.ss.android.account.model2;

import android.text.TextUtils;
import com.bytedance.sdk.account.impl.h;
import com.bytedance.sdk.account.utils.l;
/* compiled from: BDAccountPlatformEntity.java */
/* loaded from: classes9.dex */
public class a implements Cloneable {
    public static final String PLAT_NAME_EMAIL = "email";
    public static final String PLAT_NAME_FB = "facebook";
    public static final String PLAT_NAME_GOOGLE = "google";
    public static final String PLAT_NAME_INSTAGRAM = "instagram";
    public static final String PLAT_NAME_LINE = "line";
    public static final String PLAT_NAME_MOBILE = "mobile";
    public static final String PLAT_NAME_TIKTOK = "tiktok";
    public static final String PLAT_NAME_TWITTER = "twitter";
    public static final String PLAT_NAME_VK = "vk";

    /* renamed from: f  reason: collision with root package name */
    private static final int f17910f = 6;

    /* renamed from: a  reason: collision with root package name */
    public long f17911a;

    /* renamed from: b  reason: collision with root package name */
    public String f17912b;

    /* renamed from: c  reason: collision with root package name */
    public String f17913c;

    /* renamed from: d  reason: collision with root package name */
    public String f17914d;

    /* renamed from: e  reason: collision with root package name */
    public String f17915e;

    /* renamed from: g  reason: collision with root package name */
    public long f17916g;

    /* renamed from: h  reason: collision with root package name */
    public long f17917h;
    public String mCreateTIme;
    public long mExpire;
    public long mExpireIn;
    public long mModifyTime;
    public final String mName;
    public String mSecPlatformUid;
    public long mUserId;
    public static final String PLAT_NAME_KAKAO = "kakaotalk";
    public static String[] ALL_PLATFORM = {"mobile", "email", "google", "facebook", "twitter", "instagram", "line", PLAT_NAME_KAKAO, "vk", "tiktok"};
    public boolean mLogin = false;
    public String mNickname = "";
    public String mAvatar = null;
    public String mPlatformUid = "";

    public static a create(String str) {
        return new a(str);
    }

    public String c() {
        if (b(this.f17912b)) {
            return null;
        }
        return this.f17912b;
    }

    public String d() {
        if (b(this.f17913c)) {
            return null;
        }
        return this.f17913c;
    }

    public String e() {
        if (b(this.f17914d)) {
            return null;
        }
        return this.f17914d;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str) || !com.bytedance.sdk.account.settings.a.a() || System.currentTimeMillis() <= this.mExpire) {
            return false;
        }
        h.a().a(this.mName, (int) this.f17911a, false);
        return true;
    }

    /* renamed from: a */
    public a clone() {
        return (a) super.clone();
    }

    public a(String str) {
        this.mName = str;
    }

    public void invalidate() {
        this.mLogin = false;
        this.mNickname = "";
        this.mAvatar = null;
        this.mPlatformUid = "";
        this.mSecPlatformUid = "";
        this.mExpire = 0L;
        this.mExpireIn = 0L;
        this.mUserId = 0L;
        this.mModifyTime = 0L;
        this.f17911a = 0L;
        this.f17912b = null;
        this.f17913c = null;
        this.f17914d = null;
        this.f17915e = null;
    }

    public String toString() {
        return "BDAccountPlatformEntity{mName='" + this.mName + "', mLogin=" + this.mLogin + ", mNickname='" + this.mNickname + "', mAvatar='" + this.mAvatar + "', mPlatformUid='" + this.mPlatformUid + "', mSecPlatformUid='" + this.mSecPlatformUid + "', mCreateTIme='" + this.mCreateTIme + "', mModifyTime=" + this.mModifyTime + ", mExpire=" + this.mExpire + "(" + l.a(this.mExpire) + "), mExpireIn=" + this.mExpireIn + ", mUserId=" + this.mUserId + ", mPlatformId=" + this.f17911a + ", mAccessToken='" + this.f17912b + "', mOpenId='" + this.f17913c + "', mScope='" + this.f17914d + "', mExtra='" + this.f17915e + "', refreshAt=" + this.f17916g + "(" + l.a(this.f17916g) + "), refreshTime=" + this.f17917h + '}';
    }

    public String b() {
        return "BDAccountPlatformEntity{mName='" + this.mName + "', mPlatformId=" + this.f17911a + ", mAccessToken='" + a(this.f17912b) + "', mOpenId='" + a(this.f17913c) + "', mScope='" + this.f17914d + "', mExpire=" + this.mExpire + "(" + l.a(this.mExpire) + "), refreshAt=" + this.f17916g + "(" + l.a(this.f17916g) + ")}";
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.length() <= 12) {
            return "***";
        }
        String substring = str.substring(0, 6);
        return substring + "***" + str.substring(str.length() - 6);
    }
}
