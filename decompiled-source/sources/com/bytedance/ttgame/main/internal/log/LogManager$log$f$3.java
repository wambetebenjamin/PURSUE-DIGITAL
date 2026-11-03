package com.bytedance.ttgame.main.internal.log;

import com.ss.android.agilelogger.a;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LogManager.kt */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public /* synthetic */ class LogManager$log$f$3 extends FunctionReferenceImpl implements Function2<String, String, Unit> {
    public static final LogManager$log$f$3 INSTANCE = new LogManager$log$f$3();

    LogManager$log$f$3() {
        super(2, a.class, DownloadFileUtils.MODE_WRITE, "w(Ljava/lang/String;Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
        invoke2(str, str2);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String str, String str2) {
        a.w(str, str2);
    }
}
