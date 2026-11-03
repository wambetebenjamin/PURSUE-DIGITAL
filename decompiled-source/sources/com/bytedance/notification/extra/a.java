package com.bytedance.notification.extra;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.ss.android.pushmanager.e;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ProxyNotificationExtra.java */
/* loaded from: classes2.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.bytedance.notification.extra.a.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i2) {
            return new a[i2];
        }
    };
    public static final int PROXY_TYPE_NO_PROXY = 0;
    public static final int PROXY_TYPE_NO_WAKE_UP_PROXY = 2;
    public static final int PROXY_TYPE_WAKE_UP_PROXY = 1;

    /* renamed from: a  reason: collision with root package name */
    private String f9865a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f9866b;
    public JSONObject mExtras;
    public boolean mForceUpdateIcon;
    public String mOpPkg;
    public int mOriginApp;
    public String mPkg;
    public String mProxyIconUrl;
    public int mProxyType;
    public int mSender;
    public int mTargetApp;
    public String mTargetAppName;
    public String mTargetPkg;
    public JSONObject partnerInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public a(String str) {
        this.f9865a = str;
        if (!TextUtils.isEmpty(str)) {
            try {
                this.f9866b = new JSONObject(str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        a();
    }

    public a(JSONObject jSONObject) {
        this.f9866b = jSONObject;
        if (jSONObject != null) {
            this.f9865a = jSONObject.toString();
        }
        a();
    }

    protected a(Parcel parcel) {
        this.f9865a = parcel.readString();
        this.mForceUpdateIcon = parcel.readInt() == 1;
        this.mProxyIconUrl = parcel.readString();
        try {
            this.mExtras = new JSONObject(parcel.readString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.mTargetPkg = parcel.readString();
        this.mTargetAppName = parcel.readString();
        this.mPkg = parcel.readString();
        this.mOpPkg = parcel.readString();
        this.mOriginApp = parcel.readInt();
        this.mTargetApp = parcel.readInt();
        this.mSender = parcel.readInt();
    }

    private void a() {
        JSONObject jSONObject = this.f9866b;
        if (jSONObject == null) {
            return;
        }
        this.mForceUpdateIcon = jSONObject.optBoolean("force_update_icon");
        this.mOriginApp = this.f9866b.optInt(e.KEY_ORIGIN_APP, -1);
        this.mTargetApp = this.f9866b.optInt("target_app", -1);
        this.mSender = this.f9866b.optInt("sender", -1);
        this.mProxyIconUrl = this.f9866b.optString("icon_url");
        JSONObject optJSONObject = this.f9866b.optJSONObject("extras");
        this.mExtras = optJSONObject;
        if (optJSONObject == null) {
            this.mExtras = new JSONObject();
        }
        this.mTargetPkg = this.f9866b.optString("target_pkg");
        this.mTargetAppName = this.f9866b.optString("target_app_name");
        this.mPkg = this.f9866b.optString("pkg");
        this.mOpPkg = this.f9866b.optString("op_pkg");
        this.mProxyType = this.f9866b.optInt("proxy_type", 0);
        this.partnerInfo = this.f9866b.optJSONObject("partner");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f9865a);
        parcel.writeInt(this.mForceUpdateIcon ? 1 : 0);
        parcel.writeString(this.mProxyIconUrl);
        parcel.writeString(this.mExtras.toString());
        parcel.writeString(this.mTargetPkg);
        parcel.writeString(this.mTargetAppName);
        parcel.writeString(this.mPkg);
        parcel.writeString(this.mOpPkg);
        parcel.writeInt(this.mOriginApp);
        parcel.writeInt(this.mTargetApp);
        parcel.writeInt(this.mSender);
    }

    public boolean isValidWakeUpProxyMessage() {
        return this.mProxyType == 1 && this.partnerInfo != null;
    }
}
