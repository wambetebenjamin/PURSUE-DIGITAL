package com.bytedance.bdturing;

import android.util.Log;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LogUtil.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\u001c\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007J\u001c\u0010\u000b\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007J\u001c\u0010\f\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0006H\u0007J\u0014\u0010\u0010\u001a\u00020\u00062\n\u0010\u000b\u001a\u00060\u0011j\u0002`\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0006H\u0002J\u001a\u0010\u0014\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u001c\u0010\u0015\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/bytedance/bdturing/LogUtil;", "", "()V", "LOG_LEVEL", "", "closeLog", "", "d", "tag", "", "msg", com.bytedance.frameworks.baselib.network.e.f7575a, com.bytedance.apm.util.i.TAG, "isDebug", "", "openLog", "printException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "printStack", BaseSwitches.V, DownloadFileUtils.MODE_WRITE, "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class h {
    public static final h INSTANCE = new h();

    /* renamed from: a  reason: collision with root package name */
    private static int f4185a = 1;

    private h() {
    }

    @JvmStatic
    public static final void openLog() {
        f4185a = 7;
    }

    @JvmStatic
    public static final void closeLog() {
        f4185a = 1;
    }

    @JvmStatic
    public static final boolean isDebug() {
        return f4185a > 6;
    }

    @JvmStatic
    public static final void i(String str, String str2) {
        if (f4185a < 4) {
            return;
        }
        Log.i(str, String.valueOf(str2));
    }

    @JvmStatic
    public static final void w(String str, String str2) {
        if (f4185a < 5) {
            return;
        }
        Log.w(str, String.valueOf(str2));
    }

    @JvmStatic
    public static final void e(String str, String str2) {
        if (f4185a < 6) {
            return;
        }
        Log.e(str, String.valueOf(str2));
    }

    @JvmStatic
    public static final void d(String str, String str2) {
        if (f4185a < 3) {
            return;
        }
        Log.d(str, String.valueOf(str2));
    }

    public final void v(String str, String str2) {
        if (f4185a < 2) {
            return;
        }
        Log.v(str, String.valueOf(str2));
    }

    @JvmStatic
    public static final void printException(Exception e2) {
        Intrinsics.checkNotNullParameter(e2, "e");
        if (f4185a < 3) {
            e2.printStackTrace();
        }
    }

    private final void a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length - 1;
        if (length < 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            System.out.println(stackTrace[i2]);
            if (i3 > length) {
                return;
            }
            i2 = i3;
        }
    }
}
