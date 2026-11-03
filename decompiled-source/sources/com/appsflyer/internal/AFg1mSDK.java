package com.appsflyer.internal;

import com.bytedance.apm.util.i;
import com.bytedance.frameworks.baselib.network.e;
import com.meituan.robust.Constants;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0016\b&\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007¢\u0006\u0004\b\u001f\u0010 J)\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJO\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0014\u0010\nJ)\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0015\u0010\nJ)\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\u0016\u0010\nJ\u001d\u0010\u0017\u001a\u00020\u0004*\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u0004*\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0019\u0010\u0018R\u001a\u0010\u001e\u001a\u00020\u00068\u0017X\u0096D¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d"}, d2 = {"Lcom/appsflyer/internal/AFg1mSDK;", "", "Lcom/appsflyer/internal/AFg1hSDK;", "p0", "", "p1", "", "p2", "", "d", "(Lcom/appsflyer/internal/AFg1hSDK;Ljava/lang/String;Z)V", "", "p3", "p4", "p5", "p6", e.f7575a, "(Lcom/appsflyer/internal/AFg1hSDK;Ljava/lang/String;Ljava/lang/Throwable;ZZZZ)V", "force", "(Lcom/appsflyer/internal/AFg1hSDK;Ljava/lang/String;)V", i.TAG, BaseSwitches.V, DownloadFileUtils.MODE_WRITE, "AFInAppEventType", "(Ljava/lang/String;Lcom/appsflyer/internal/AFg1hSDK;)Ljava/lang/String;", "withTag$SDK_prodRelease", "AFInAppEventParameterName", "Z", "getShouldExtendMsg", "()Z", "shouldExtendMsg", "<init>", "()V", "AFa1uSDK"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AFg1mSDK {
    private final boolean AFInAppEventParameterName;
    public static final AFa1uSDK AFa1uSDK = new AFa1uSDK(null);
    public static long AFInAppEventType = System.currentTimeMillis();

    public final void d(AFg1hSDK aFg1hSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        d$default(this, aFg1hSDK, str, false, 4, null);
    }

    public void d(AFg1hSDK aFg1hSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
    }

    public final void e(AFg1hSDK aFg1hSDK, String str, Throwable th) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1hSDK, str, th, false, false, false, false, 120, null);
    }

    public final void e(AFg1hSDK aFg1hSDK, String str, Throwable th, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1hSDK, str, th, z, false, false, false, 112, null);
    }

    public final void e(AFg1hSDK aFg1hSDK, String str, Throwable th, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1hSDK, str, th, z, z2, false, false, 96, null);
    }

    public final void e(AFg1hSDK aFg1hSDK, String str, Throwable th, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
        e$default(this, aFg1hSDK, str, th, z, z2, z3, false, 64, null);
    }

    public void e(AFg1hSDK aFg1hSDK, String str, Throwable th, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(th, "");
    }

    public void force(AFg1hSDK aFg1hSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
    }

    public final void i(AFg1hSDK aFg1hSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        i$default(this, aFg1hSDK, str, false, 4, null);
    }

    public void i(AFg1hSDK aFg1hSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
    }

    public final void v(AFg1hSDK aFg1hSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        v$default(this, aFg1hSDK, str, false, 4, null);
    }

    public void v(AFg1hSDK aFg1hSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
    }

    public final void w(AFg1hSDK aFg1hSDK, String str) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
        w$default(this, aFg1hSDK, str, false, 4, null);
    }

    public void w(AFg1hSDK aFg1hSDK, String str, boolean z) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        Intrinsics.checkNotNullParameter(str, "");
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0002X\u0006¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/appsflyer/internal/AFg1mSDK$AFa1uSDK;", "", "", "AFInAppEventType", "J", "values", "<init>", "()V"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class AFa1uSDK {
        public /* synthetic */ AFa1uSDK(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private AFa1uSDK() {
        }
    }

    public boolean getShouldExtendMsg() {
        return this.AFInAppEventParameterName;
    }

    public static /* synthetic */ void d$default(AFg1mSDK aFg1mSDK, AFg1hSDK aFg1hSDK, String str, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        aFg1mSDK.d(aFg1hSDK, str, z);
    }

    public static /* synthetic */ void e$default(AFg1mSDK aFg1mSDK, AFg1hSDK aFg1hSDK, String str, Throwable th, boolean z, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        aFg1mSDK.e(aFg1hSDK, str, th, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? true : z2, (i2 & 32) != 0 ? true : z3, (i2 & 64) != 0 ? true : z4);
    }

    public static /* synthetic */ void i$default(AFg1mSDK aFg1mSDK, AFg1hSDK aFg1hSDK, String str, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        aFg1mSDK.i(aFg1hSDK, str, z);
    }

    public static /* synthetic */ void w$default(AFg1mSDK aFg1mSDK, AFg1hSDK aFg1hSDK, String str, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        aFg1mSDK.w(aFg1hSDK, str, z);
    }

    public static /* synthetic */ void v$default(AFg1mSDK aFg1mSDK, AFg1hSDK aFg1hSDK, String str, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException();
        }
        if ((i2 & 4) != 0) {
            z = true;
        }
        aFg1mSDK.v(aFg1hSDK, str, z);
    }

    public final String withTag$SDK_prodRelease(String str, AFg1hSDK aFg1hSDK) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        return new StringBuilder(Constants.ARRAY_TYPE).append(aFg1hSDK.valueOf).append("] ").append(str).toString();
    }

    public final String AFInAppEventType(String str, AFg1hSDK aFg1hSDK) {
        Intrinsics.checkNotNullParameter(aFg1hSDK, "");
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            str = "null";
        }
        String withTag$SDK_prodRelease = withTag$SDK_prodRelease(str, aFg1hSDK);
        return getShouldExtendMsg() ? new StringBuilder("(").append(System.currentTimeMillis() - AFInAppEventType).append(") [").append(Thread.currentThread().getName()).append("] ").append(withTag$SDK_prodRelease).toString() : withTag$SDK_prodRelease;
    }
}
