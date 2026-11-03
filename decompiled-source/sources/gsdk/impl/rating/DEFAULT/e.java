package gsdk.impl.rating.DEFAULT;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RatingApi.kt */
/* loaded from: classes10.dex */
public final class e {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private int f20695a;
    @SerializedName("message")

    /* renamed from: b  reason: collision with root package name */
    private String f20696b = "";
    @SerializedName("data")

    /* renamed from: c  reason: collision with root package name */
    private d f20697c;

    public final d a() {
        return this.f20697c;
    }

    public final boolean b() {
        return this.f20695a == 0 && Intrinsics.areEqual(this.f20696b, "success");
    }
}
