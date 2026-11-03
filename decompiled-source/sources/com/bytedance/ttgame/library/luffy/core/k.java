package com.bytedance.ttgame.library.luffy.core;

import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import gsdk.impl.account.toutiao.dh;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Logger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\nJ\u001f\u0010\u000b\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004J\u001f\u0010\u000e\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\nJ\u001f\u0010\u000f\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/Logger;", "", "()V", "logger", "Lcom/bytedance/ttgame/library/luffy/adapter/ILogger;", "d", "", com.bytedance.common.process.cross.a.COL_ARGS, "", "", "([Ljava/lang/String;)V", com.bytedance.frameworks.baselib.network.e.f7575a, dh.z, "impl", BaseSwitches.V, DownloadFileUtils.MODE_WRITE, "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class k {
    public static final k INSTANCE = new k();

    /* renamed from: a  reason: collision with root package name */
    private static com.bytedance.ttgame.library.luffy.adapter.b f14200a = new com.bytedance.ttgame.library.luffy.impl.a();

    private k() {
    }

    public final void d(String... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        f14200a.d(ArraysKt.joinToString$default(args, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }

    public final void e(String... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        f14200a.e(ArraysKt.joinToString$default(args, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }

    public final void w(String... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        f14200a.w(ArraysKt.joinToString$default(args, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }

    public final void v(String... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        f14200a.v(ArraysKt.joinToString$default(args, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }

    public final void set(com.bytedance.ttgame.library.luffy.adapter.b impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        f14200a = impl;
    }
}
