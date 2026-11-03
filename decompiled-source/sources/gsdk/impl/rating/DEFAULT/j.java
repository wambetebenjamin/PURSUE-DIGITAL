package gsdk.impl.rating.DEFAULT;

import com.google.gson.annotations.SerializedName;
/* compiled from: SettingsResult.java */
/* loaded from: classes10.dex */
public class j {
    @SerializedName("channel")

    /* renamed from: a  reason: collision with root package name */
    private String f20707a;
    @SerializedName("market")

    /* renamed from: b  reason: collision with root package name */
    private String f20708b;

    public String a() {
        return this.f20707a;
    }

    public String b() {
        return this.f20708b;
    }

    public String toString() {
        return "SettingsResult{channel='" + this.f20707a + "', market='" + this.f20708b + "'}";
    }
}
