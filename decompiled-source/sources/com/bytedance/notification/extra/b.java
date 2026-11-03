package com.bytedance.notification.extra;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.bytedance.push.helper.c;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PushNotificationExtra.java */
/* loaded from: classes2.dex */
public class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.bytedance.notification.extra.b.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public b[] newArray(int i2) {
            return new b[i2];
        }
    };

    /* renamed from: e  reason: collision with root package name */
    public static final int f9867e = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f9868f = 1;

    /* renamed from: a  reason: collision with root package name */
    public String f9869a;

    /* renamed from: b  reason: collision with root package name */
    public Bitmap f9870b;

    /* renamed from: c  reason: collision with root package name */
    public String f9871c;

    /* renamed from: d  reason: collision with root package name */
    public Bitmap f9872d;

    /* renamed from: g  reason: collision with root package name */
    public Bitmap f9873g;

    /* renamed from: h  reason: collision with root package name */
    public int f9874h;

    /* renamed from: i  reason: collision with root package name */
    public com.bytedance.notification.interfaze.a f9875i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f9876j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int mBannerColor;
    public int mBannerContentColor;
    public int mBannerHeaderColor;
    public double mBannerShowDuration;
    public int mBannerTitleColor;
    public JSONObject mBdPushObject;
    public String mBdPushStr;
    public boolean mEnableBannerHighLight;
    public boolean mEnableBannerShow;
    public boolean mEnableNotificationHighLight;
    public boolean mEnableOnTop;
    public boolean mEnableSticky;
    public JSONObject mExtras;
    public boolean mHandleBySdk;
    public int mNotificationColor;
    public int mNotificationContentColor;
    public int mNotificationHeaderColor;
    public int mNotificationTitleColor;
    public int mOnTopTime;
    public com.bytedance.notification.extra.a mProxyNotificationExtra;
    public boolean mResetAllTextToBlack;
    public int n;

    /* compiled from: PushNotificationExtra.java */
    /* loaded from: classes2.dex */
    public @interface a {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public b(String str) {
        this.mEnableNotificationHighLight = false;
        this.mNotificationColor = -1;
        this.mNotificationHeaderColor = 0;
        this.mNotificationTitleColor = 0;
        this.mNotificationContentColor = 0;
        this.mEnableSticky = false;
        this.mEnableOnTop = false;
        this.mOnTopTime = 2;
        this.mEnableBannerShow = false;
        this.f9874h = 0;
        this.mEnableBannerHighLight = false;
        this.mBannerColor = -1;
        this.mBannerHeaderColor = 0;
        this.mBannerTitleColor = 0;
        this.mBannerContentColor = 0;
        this.mBannerShowDuration = 1.0d;
        this.f9876j = false;
        this.n = -1;
        if (TextUtils.isEmpty(str)) {
            this.f9876j = true;
            return;
        }
        this.mBdPushStr = str;
        try {
            this.mBdPushObject = new JSONObject(this.mBdPushStr);
            a();
        } catch (Throwable unused) {
            this.f9876j = true;
        }
    }

    private void a() {
        JSONObject jSONObject = this.mBdPushObject;
        if (jSONObject == null) {
            this.f9876j = true;
            return;
        }
        try {
            this.mEnableNotificationHighLight = jSONObject.optBoolean("enable_notification_highlight", false);
            this.mNotificationColor = a(this.mBdPushObject, "notification_color", -1);
            this.f9869a = this.mBdPushObject.optString("notification_background_image");
            this.f9871c = this.mBdPushObject.optString("banner_background_image");
            this.mNotificationHeaderColor = a(this.mBdPushObject, "notification_header_color", 0);
            this.mNotificationTitleColor = a(this.mBdPushObject, "notification_title_color", 0);
            this.mNotificationContentColor = a(this.mBdPushObject, "notification_content_color", 0);
            this.mResetAllTextToBlack = this.mBdPushObject.optBoolean("reset_all_text_to_black", false);
            this.mEnableBannerShow = this.mBdPushObject.optBoolean("enable_banner_show", false);
            this.mEnableBannerHighLight = this.mBdPushObject.optBoolean("enable_banner_highlight", false);
            this.f9874h = this.mBdPushObject.optInt("banner_type", 0);
            this.mBannerColor = a(this.mBdPushObject, "banner_color", -1);
            this.mBannerHeaderColor = a(this.mBdPushObject, "banner_header_color", 0);
            this.mBannerTitleColor = a(this.mBdPushObject, "banner_title_color", 0);
            this.mBannerContentColor = a(this.mBdPushObject, "banner_content_color", 0);
            this.mBannerShowDuration = this.mBdPushObject.optDouble("banner_show_duration", 1.0d);
            this.mEnableSticky = this.mBdPushObject.optBoolean("enable_sticky", false);
            this.mEnableOnTop = this.mBdPushObject.optBoolean("enable_on_top", false);
            this.mOnTopTime = this.mBdPushObject.optInt("on_top_time", 2);
            this.mHandleBySdk = this.mBdPushObject.optBoolean(c.f11362h, true);
            if (this.mBdPushObject.optBoolean("handle_by_business", false)) {
                this.mHandleBySdk = false;
            }
            JSONObject optJSONObject = this.mBdPushObject.optJSONObject("proxy_notification_info");
            if (optJSONObject != null) {
                this.mProxyNotificationExtra = new com.bytedance.notification.extra.a(optJSONObject);
            }
            this.n = this.mBdPushObject.optInt("flags", -1);
            JSONObject optJSONObject2 = this.mBdPushObject.optJSONObject("extras");
            this.mExtras = optJSONObject2;
            if (optJSONObject2 == null) {
                this.mExtras = new JSONObject();
            }
            this.k = this.mBdPushObject.optBoolean("use_sound", false);
            this.l = this.mBdPushObject.optBoolean("use_vibration", false);
            this.m = this.mBdPushObject.optBoolean("bright_screen", false);
        } catch (Throwable unused) {
            this.f9876j = true;
        }
    }

    private int a(JSONObject jSONObject, String str, int i2) {
        try {
            String optString = jSONObject.optString(str);
            return !TextUtils.isEmpty(optString) ? Color.parseColor(optString) : i2;
        } catch (Throwable unused) {
            return i2;
        }
    }

    protected b(Parcel parcel) {
        this.mEnableNotificationHighLight = false;
        this.mNotificationColor = -1;
        this.mNotificationHeaderColor = 0;
        this.mNotificationTitleColor = 0;
        this.mNotificationContentColor = 0;
        this.mEnableSticky = false;
        this.mEnableOnTop = false;
        this.mOnTopTime = 2;
        this.mEnableBannerShow = false;
        this.f9874h = 0;
        this.mEnableBannerHighLight = false;
        this.mBannerColor = -1;
        this.mBannerHeaderColor = 0;
        this.mBannerTitleColor = 0;
        this.mBannerContentColor = 0;
        this.mBannerShowDuration = 1.0d;
        this.f9876j = false;
        this.n = -1;
        this.mBdPushStr = parcel.readString();
        try {
            this.mBdPushObject = new JSONObject(parcel.readString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.mEnableNotificationHighLight = parcel.readByte() != 0;
        this.mNotificationColor = parcel.readInt();
        this.mEnableSticky = parcel.readByte() != 0;
        this.mEnableOnTop = parcel.readByte() != 0;
        this.mOnTopTime = parcel.readInt();
        this.mEnableBannerShow = parcel.readByte() != 0;
        this.mEnableBannerHighLight = parcel.readByte() != 0;
        this.mBannerColor = parcel.readInt();
        this.mBannerShowDuration = parcel.readDouble();
        try {
            this.mExtras = new JSONObject(parcel.readString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.k = parcel.readByte() != 0;
        this.l = parcel.readByte() != 0;
        this.m = parcel.readByte() != 0;
    }

    public boolean b() {
        return this.k || this.l || this.m;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mBdPushStr);
        JSONObject jSONObject = this.mBdPushObject;
        parcel.writeString(jSONObject != null ? jSONObject.toString() : "");
        parcel.writeByte(this.mEnableNotificationHighLight ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mNotificationColor);
        parcel.writeByte(this.mEnableSticky ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mEnableOnTop ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mOnTopTime);
        parcel.writeByte(this.mEnableBannerShow ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mEnableBannerHighLight ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mBannerColor);
        parcel.writeDouble(this.mBannerShowDuration);
        JSONObject jSONObject2 = this.mExtras;
        parcel.writeString(jSONObject2 != null ? jSONObject2.toString() : "");
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
    }
}
