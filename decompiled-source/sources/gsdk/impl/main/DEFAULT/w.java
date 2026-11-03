package gsdk.impl.main.DEFAULT;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GameInfoService.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/module/gameinfo/PingData;", "", com.bytedance.apm.constant.k.RESPONSE_DELAY, "", "error", "", "(ILjava/lang/String;)V", "getDelay", "()I", "getError", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class w {

    /* renamed from: a  reason: collision with root package name */
    private final int f20524a;

    /* renamed from: b  reason: collision with root package name */
    private final String f20525b;

    public static /* synthetic */ w a(w wVar, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = wVar.f20524a;
        }
        if ((i3 & 2) != 0) {
            str = wVar.f20525b;
        }
        return wVar.a(i2, str);
    }

    public final w a(int i2, String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new w(i2, error);
    }

    public final int c() {
        return this.f20524a;
    }

    public final String d() {
        return this.f20525b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof w) {
            w wVar = (w) obj;
            return this.f20524a == wVar.f20524a && Intrinsics.areEqual(this.f20525b, wVar.f20525b);
        }
        return false;
    }

    public int hashCode() {
        return (Integer.hashCode(this.f20524a) * 31) + this.f20525b.hashCode();
    }

    public String toString() {
        return "PingData(delay=" + this.f20524a + ", error=" + this.f20525b + ')';
    }

    public w(int i2, String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.f20524a = i2;
        this.f20525b = error;
    }

    public final int a() {
        return this.f20524a;
    }

    public final String b() {
        return this.f20525b;
    }
}
