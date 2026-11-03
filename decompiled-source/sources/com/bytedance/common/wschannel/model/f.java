package com.bytedance.common.wschannel.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.bytedance.common.wschannel.WsConstants;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: SocketState.java */
/* loaded from: classes2.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new Parcelable.Creator<f>() { // from class: com.bytedance.common.wschannel.model.f.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public f[] newArray(int i2) {
            return new f[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f5801a;

    /* renamed from: b  reason: collision with root package name */
    public int f5802b;

    /* renamed from: c  reason: collision with root package name */
    public String f5803c;

    /* renamed from: d  reason: collision with root package name */
    public int f5804d;

    /* renamed from: e  reason: collision with root package name */
    public int f5805e;

    /* renamed from: f  reason: collision with root package name */
    public String f5806f;

    /* renamed from: g  reason: collision with root package name */
    public int f5807g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f5808h;

    /* renamed from: i  reason: collision with root package name */
    private String f5809i;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public f() {
    }

    public static f a(JSONObject jSONObject) {
        f fVar = new f();
        fVar.f5804d = jSONObject.optInt(WsConstants.KEY_CHANNEL_ID, Integer.MIN_VALUE);
        fVar.f5801a = jSONObject.optInt("type", -1);
        fVar.f5802b = jSONObject.optInt("state", -1);
        fVar.f5803c = jSONObject.optString("url", "");
        fVar.f5805e = jSONObject.optInt(WsConstants.KEY_CHANNEL_TYPE);
        fVar.f5806f = jSONObject.optString("error", "");
        fVar.f5807g = jSONObject.optInt("error_code");
        fVar.f5808h = jSONObject.optInt(WsConstants.KEY_PRIVATE_PROTOCOL_ENABLE, 0) > 0;
        fVar.f5809i = jSONObject.optString(WsConstants.KEY_CONNECTION_EXTRA_INFO);
        return fVar;
    }

    public String toString() {
        return "SocketState{connectionType=" + this.f5801a + ", connectionState=" + this.f5802b + ", connectionUrl='" + this.f5803c + "', channelId=" + this.f5804d + ", channelType=" + this.f5805e + ", error='" + this.f5806f + "', privateProtocol=" + this.f5808h + "', connectionExtraInfo='" + this.f5809i + "'}";
    }

    public int a() {
        return this.f5801a;
    }

    public void a(int i2) {
        this.f5801a = i2;
    }

    public int b() {
        return this.f5802b;
    }

    public void b(int i2) {
        this.f5802b = i2;
    }

    public String c() {
        return this.f5803c;
    }

    public void a(String str) {
        this.f5803c = str;
    }

    public int d() {
        return this.f5804d;
    }

    public void c(int i2) {
        this.f5804d = i2;
    }

    public boolean e() {
        return this.f5808h;
    }

    public JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(WsConstants.KEY_CHANNEL_ID, this.f5804d);
            jSONObject.put("type", this.f5801a);
            jSONObject.put("state", this.f5802b);
            jSONObject.put("url", this.f5803c);
            jSONObject.put(WsConstants.KEY_CHANNEL_TYPE, this.f5805e);
            jSONObject.put("error", this.f5806f);
            jSONObject.put("error_code", this.f5807g);
            jSONObject.put(WsConstants.KEY_PRIVATE_PROTOCOL_ENABLE, this.f5808h ? 1 : 0);
            if (!TextUtils.isEmpty(this.f5809i)) {
                jSONObject.put(WsConstants.KEY_CONNECTION_EXTRA_INFO, this.f5809i);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public int g() {
        return this.f5807g;
    }

    public void d(int i2) {
        this.f5807g = i2;
    }

    public com.bytedance.common.wschannel.event.a h() {
        return com.bytedance.common.wschannel.event.a.a(this.f5805e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f5801a);
        parcel.writeInt(this.f5802b);
        parcel.writeString(this.f5803c);
        parcel.writeInt(this.f5804d);
        parcel.writeInt(this.f5805e);
        parcel.writeString(this.f5806f);
        parcel.writeInt(this.f5807g);
        parcel.writeInt(this.f5808h ? 1 : 0);
        parcel.writeString(this.f5809i);
    }

    protected f(Parcel parcel) {
        this.f5801a = parcel.readInt();
        this.f5802b = parcel.readInt();
        this.f5803c = parcel.readString();
        this.f5804d = parcel.readInt();
        this.f5805e = parcel.readInt();
        this.f5806f = parcel.readString();
        this.f5807g = parcel.readInt();
        this.f5808h = parcel.readInt() > 0;
        this.f5809i = parcel.readString();
    }
}
