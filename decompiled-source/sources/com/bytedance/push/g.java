package com.bytedance.push;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.bytedance.android.service.manager.alliance.IAllianceService;
import com.bytedance.android.service.manager.push.notification.NotificationBody;
import com.bytedance.ttgame.module.push.PushService;
import org.json.JSONObject;
/* compiled from: PushBody.java */
/* loaded from: classes2.dex */
public class g implements Parcelable {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() { // from class: com.bytedance.push.g.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public g[] newArray(int i2) {
            return new g[i2];
        }
    };
    public static final String KEY_PASS_THROUGH = "pass_through";
    public static final int TYPE_IMAGE_BIG = 1;
    public static final int TYPE_IMAGE_NONE = 0;
    public static final int TYPE_IMAGE_SMALL = 2;

    /* renamed from: a  reason: collision with root package name */
    public String f11322a;
    public String androidGroup;

    /* renamed from: b  reason: collision with root package name */
    public boolean f11323b;
    public int badge;
    public String businessType;

    /* renamed from: c  reason: collision with root package name */
    public long f11324c;

    /* renamed from: d  reason: collision with root package name */
    public int f11325d;

    /* renamed from: e  reason: collision with root package name */
    public int f11326e;
    public JSONObject eventExtra;
    public String extra;

    /* renamed from: f  reason: collision with root package name */
    public int f11327f;

    /* renamed from: g  reason: collision with root package name */
    public int f11328g;
    public int groupFoldNum;
    public final String groupId;

    /* renamed from: h  reason: collision with root package name */
    public boolean f11329h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f11330i;
    public long id;
    public int imageType;
    public String imageUrl;

    /* renamed from: j  reason: collision with root package name */
    public boolean f11331j;
    public String k;
    public int l;
    public boolean mIsPassThough;
    public String mNotificationChannelId;
    public int messageType;
    public JSONObject msgData;
    public final boolean needFilterMsgByUid;
    public String open_url;
    public int pushShowType;
    public long revokeId;
    public long rid64;
    public final boolean showWhen;
    public String sign;
    public final String targetSecUid;
    public String text;
    public String title;
    public boolean useLED;
    public boolean useSound;
    public boolean useVibrator;
    public String voipParams;

    /* compiled from: PushBody.java */
    /* loaded from: classes2.dex */
    public @interface a {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public g(JSONObject jSONObject) {
        this.msgData = jSONObject;
        this.open_url = jSONObject.optString("open_url");
        this.text = jSONObject.optString("text");
        this.title = jSONObject.optString("title");
        this.imageUrl = jSONObject.optString("image_url");
        this.id = jSONObject.optLong("id", 0L);
        this.rid64 = jSONObject.optLong(com.ss.android.pushmanager.e.f18494c, 0L);
        this.useLED = a(jSONObject, "use_led", false);
        this.useSound = a(jSONObject, "sound", false);
        this.useVibrator = a(jSONObject, "use_vibrator", false);
        this.imageType = jSONObject.optInt("image_type", 0);
        this.mIsPassThough = jSONObject.optInt(KEY_PASS_THROUGH, 1) > 0;
        this.mNotificationChannelId = jSONObject.optString("notify_channel");
        this.messageType = jSONObject.optInt(com.ss.android.pushmanager.d.MSG_FROM);
        this.groupId = jSONObject.optString("group_id_str");
        this.showWhen = jSONObject.optInt("st", 1) > 0;
        this.targetSecUid = jSONObject.optString(com.ss.android.pushmanager.e.KEY_TARGET_SEC_UID);
        this.needFilterMsgByUid = jSONObject.optInt("ttpush_need_filter_uid", 0) > 0;
        this.revokeId = jSONObject.optLong("revoke_id");
        this.extra = jSONObject.optString(PushService.KEY_EXTRA_STR);
        this.f11322a = jSONObject.optString("bdpush_str");
        this.sign = jSONObject.optString("sign");
        this.eventExtra = jSONObject.optJSONObject(com.ss.android.pushmanager.e.KEY_TTPUSH_EVENT_EXTRA);
        this.pushShowType = jSONObject.optInt(com.ss.android.pushmanager.e.KEY_PUSH_SHOW_TYPE, 0);
        this.businessType = jSONObject.optString("business_type", "");
        this.badge = jSONObject.optInt(IAllianceService.CONTENT_TYPE_BADGE);
        this.pushShowType = jSONObject.optInt(com.ss.android.pushmanager.e.KEY_PUSH_SHOW_TYPE, 0);
        this.businessType = jSONObject.optString("business_type", "");
        this.voipParams = jSONObject.optString("voip_params", "");
        this.androidGroup = jSONObject.optString("android_group", "");
        this.groupFoldNum = jSONObject.optInt("group_fold_num", 3);
        this.f11323b = jSONObject.optBoolean("client_intelligent");
        this.f11324c = jSONObject.optLong("message_expire_time", -1L);
        this.f11325d = jSONObject.optInt("client_intelligence_push_show_mode", 1);
        this.f11326e = jSONObject.optInt("client_intelligence_push_show_sub_mode", 0);
        this.f11329h = jSONObject.optBoolean("allow_intercept_in_black_time_window", true);
        this.k = jSONObject.optString("category", "");
        this.l = jSONObject.optInt("importance", -1);
        long j2 = this.f11324c;
        if (j2 == -1) {
            com.bytedance.push.utils.h.w("PushBody", "expiredTime is None so force set useClientIntelligenceShow and allowInterceptInBlackTimeWindow to false");
            this.f11323b = false;
            this.f11329h = false;
        } else {
            this.f11324c = j2 * 1000;
        }
        this.f11327f = jSONObject.optInt("min_display_interval_from_last_msg", -1);
        this.f11328g = jSONObject.optInt("min_display_interval_from_foreground", -1);
        this.f11330i = jSONObject.optBoolean("additional_arrive_callback", false);
        this.f11331j = jSONObject.optBoolean("additional_click_callback", false);
    }

    private static boolean a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return z;
        }
        int optInt = jSONObject.optInt(str, -1);
        if (optInt >= 1) {
            return true;
        }
        if (optInt == 0) {
            return false;
        }
        return jSONObject.optBoolean(str, z);
    }

    public String getNotificationChannel() {
        return this.mNotificationChannelId;
    }

    public String getOriginData() {
        JSONObject jSONObject = this.msgData;
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        return null;
    }

    public String getExtra() {
        return this.extra;
    }

    public boolean checkValid() {
        return (this.id <= 0 || TextUtils.isEmpty(this.open_url) || TextUtils.isEmpty(this.groupId)) ? false : true;
    }

    public String toString() {
        return "PushBody{groupId='" + this.groupId + "', extra='" + this.extra + "', mNotificationChannelId='" + this.mNotificationChannelId + "', mIsPassThough=" + this.mIsPassThough + ", msgData=" + this.msgData + ", text='" + this.text + "', title='" + this.title + "', imageUrl='" + this.imageUrl + "', imageType=" + this.imageType + ", id=" + this.id + ", open_url='" + this.open_url + "', useLED=" + this.useLED + ", useSound=" + this.useSound + ", useVibrator=" + this.useVibrator + ", messageType=" + this.messageType + ", androidGroup=" + this.androidGroup + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.rid64);
        parcel.writeString(this.groupId);
        parcel.writeByte(this.showWhen ? (byte) 1 : (byte) 0);
        parcel.writeString(this.targetSecUid);
        parcel.writeByte(this.needFilterMsgByUid ? (byte) 1 : (byte) 0);
        parcel.writeString(this.extra);
        parcel.writeString(this.f11322a);
        parcel.writeString(this.mNotificationChannelId);
        parcel.writeByte(this.mIsPassThough ? (byte) 1 : (byte) 0);
        parcel.writeString(this.msgData.toString());
        parcel.writeString(this.text);
        parcel.writeString(this.title);
        parcel.writeString(this.imageUrl);
        parcel.writeInt(this.imageType);
        parcel.writeString(this.open_url);
        parcel.writeByte(this.useLED ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.useSound ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.useVibrator ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.messageType);
        parcel.writeLong(this.revokeId);
        parcel.writeString(this.sign);
        JSONObject jSONObject = this.eventExtra;
        parcel.writeString(jSONObject == null ? "" : jSONObject.toString());
    }

    protected g(Parcel parcel) {
        this.id = parcel.readLong();
        this.rid64 = parcel.readLong();
        this.groupId = parcel.readString();
        this.showWhen = parcel.readByte() != 0;
        this.targetSecUid = parcel.readString();
        this.needFilterMsgByUid = parcel.readByte() != 0;
        this.extra = parcel.readString();
        this.f11322a = parcel.readString();
        this.mNotificationChannelId = parcel.readString();
        this.mIsPassThough = parcel.readByte() != 0;
        try {
            this.msgData = new JSONObject(parcel.readString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.text = parcel.readString();
        this.title = parcel.readString();
        this.imageUrl = parcel.readString();
        this.imageType = parcel.readInt();
        this.open_url = parcel.readString();
        this.useLED = parcel.readByte() != 0;
        this.useSound = parcel.readByte() != 0;
        this.useVibrator = parcel.readByte() != 0;
        this.messageType = parcel.readInt();
        this.revokeId = parcel.readLong();
        this.sign = parcel.readString();
        try {
            this.eventExtra = new JSONObject(parcel.readString());
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public int getProxyOriginAPP() {
        JSONObject optJSONObject;
        JSONObject jSONObject = this.msgData;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("proxy_notification_info")) == null) {
            return -1;
        }
        return optJSONObject.optInt(com.ss.android.pushmanager.e.KEY_ORIGIN_APP, -1);
    }

    public int getProxyTargetAPP() {
        JSONObject optJSONObject;
        JSONObject jSONObject = this.msgData;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("proxy_notification_info")) == null) {
            return -1;
        }
        return optJSONObject.optInt("target_app", -1);
    }

    public int getProxySender() {
        JSONObject optJSONObject;
        JSONObject jSONObject = this.msgData;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("proxy_notification_info")) == null) {
            return -1;
        }
        return optJSONObject.optInt("sender", -1);
    }

    public NotificationBody a() {
        return new NotificationBody.Builder().id(this.id).showWhen(this.showWhen).title(this.title).content(this.text).groupId(this.groupId).channelId(this.mNotificationChannelId).redBadgeNum(this.badge).imageUrl(this.imageUrl).imageType(this.imageType).useLED(this.useLED).useSound(this.useSound).useVibrator(this.useVibrator).androidGroup(this.androidGroup).groupFoldNum(this.groupFoldNum).setBdPushStr(this.f11322a).setOpenUrl(this.open_url).setEventExtra(this.eventExtra).setChannelImportance(this.l).setNotificationCategory(this.k).build();
    }
}
