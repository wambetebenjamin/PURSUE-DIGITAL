package gsdk.impl.compliance.cookie;

import com.google.gson.annotations.SerializedName;
/* compiled from: CookieInfo.kt */
/* loaded from: classes10.dex */
public final class c {
    @SerializedName("cookie_switch")

    /* renamed from: a  reason: collision with root package name */
    private int f19923a;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && this.f19923a == ((c) obj).f19923a;
    }

    public int hashCode() {
        return Integer.hashCode(this.f19923a);
    }

    public String toString() {
        return "CookieData(status=" + this.f19923a + ')';
    }

    public c(int i2) {
        this.f19923a = i2;
    }

    public final int a() {
        return this.f19923a;
    }
}
