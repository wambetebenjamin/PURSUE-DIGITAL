package gsdk.impl.rating.DEFAULT;

import com.google.gson.annotations.SerializedName;
/* compiled from: SettingData.java */
/* loaded from: classes10.dex */
public class i {
    @SerializedName("app_market_order")

    /* renamed from: a  reason: collision with root package name */
    private String f20704a;
    @SerializedName("market_feedback_url")

    /* renamed from: b  reason: collision with root package name */
    private String f20705b;
    @SerializedName("market_feedback_dialog_enable")

    /* renamed from: c  reason: collision with root package name */
    private int f20706c;

    public i(String str, String str2, int i2) {
        this.f20704a = str;
        this.f20705b = str2;
        this.f20706c = i2;
    }

    public String a() {
        return this.f20704a;
    }

    public String b() {
        return this.f20705b;
    }
}
