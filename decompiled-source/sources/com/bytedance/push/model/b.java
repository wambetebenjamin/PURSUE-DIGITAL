package com.bytedance.push.model;

import android.app.NotificationChannel;
import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import org.json.JSONObject;
/* compiled from: PushNotificationChannel.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f11432a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f11433b;

    /* renamed from: c  reason: collision with root package name */
    private String f11434c;

    /* renamed from: d  reason: collision with root package name */
    private String f11435d;

    /* renamed from: e  reason: collision with root package name */
    private int f11436e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f11437f;

    /* renamed from: g  reason: collision with root package name */
    private int f11438g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f11439h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11440i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f11441j;
    private String k;
    private JSONObject l;

    public b(JSONObject jSONObject) {
        this.f11433b = true;
        this.f11434c = jSONObject.optString("id");
        this.f11435d = jSONObject.optString("name");
        this.f11436e = jSONObject.optInt("importance", 3);
        this.f11437f = jSONObject.optBoolean("bypassDnd", true);
        this.f11438g = jSONObject.optInt("lockscreenVisibility", -1);
        this.f11439h = jSONObject.optBoolean("lights", true);
        this.f11440i = jSONObject.optBoolean("vibration", true);
        this.f11441j = jSONObject.optBoolean("showBadge", true);
        this.f11433b = jSONObject.optBoolean("enable", true);
        this.f11432a = jSONObject.optString(ConstantKt.DESC);
        this.k = jSONObject.optString("sound");
        JSONObject optJSONObject = jSONObject.optJSONObject("channel_fields");
        this.l = optJSONObject;
        if (optJSONObject == null) {
            this.l = new JSONObject();
        }
    }

    public b(NotificationChannel notificationChannel) {
        this.f11433b = true;
        this.f11434c = notificationChannel.getId();
        this.f11435d = String.valueOf(notificationChannel.getName());
        this.f11436e = notificationChannel.getImportance();
        this.f11437f = notificationChannel.canBypassDnd();
        this.f11438g = notificationChannel.getLockscreenVisibility();
        this.f11439h = notificationChannel.shouldShowLights();
        this.f11440i = notificationChannel.shouldVibrate();
        this.f11441j = notificationChannel.canShowBadge();
        this.f11432a = notificationChannel.getDescription();
        this.l = new JSONObject();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", getId());
        jSONObject.put("name", getName());
        jSONObject.put("importance", getImportance());
        jSONObject.put("bypassDnd", canBypassDnd());
        jSONObject.put("lockscreenVisibility", getLockscreenVisibility());
        jSONObject.put("lights", shouldShowLights());
        jSONObject.put("vibration", shouldVibrate());
        jSONObject.put("showBadge", isShowBadge());
        jSONObject.put("enable", isEnable());
        jSONObject.put(ConstantKt.DESC, getDesc());
        jSONObject.put("channel_fields", getChannelFields());
        return jSONObject;
    }

    public String getId() {
        return this.f11434c;
    }

    public void setId(String str) {
        this.f11434c = str;
    }

    public String getName() {
        return this.f11435d;
    }

    public void setName(String str) {
        this.f11435d = str;
    }

    public int getImportance() {
        return this.f11436e;
    }

    public void setImportance(int i2) {
        this.f11436e = i2;
    }

    public int getLockscreenVisibility() {
        return this.f11438g;
    }

    public void setLockscreenVisibility(int i2) {
        this.f11438g = i2;
    }

    public boolean canBypassDnd() {
        return this.f11437f;
    }

    public void setBypassDnd(boolean z) {
        this.f11437f = z;
    }

    public boolean shouldShowLights() {
        return this.f11439h;
    }

    public void setLights(boolean z) {
        this.f11439h = z;
    }

    public boolean shouldVibrate() {
        return this.f11440i;
    }

    public void setVibration(boolean z) {
        this.f11440i = z;
    }

    public boolean isShowBadge() {
        return this.f11441j;
    }

    public void setShowBadge(boolean z) {
        this.f11441j = z;
    }

    public boolean isEnable() {
        return this.f11433b;
    }

    public void setEnable(boolean z) {
        this.f11433b = z;
    }

    public String getDesc() {
        return this.f11432a;
    }

    public void setDesc(String str) {
        this.f11432a = str;
    }

    public String getSound() {
        return this.k;
    }

    public b setSound(String str) {
        this.k = str;
        return this;
    }

    public JSONObject getChannelFields() {
        return this.l;
    }
}
