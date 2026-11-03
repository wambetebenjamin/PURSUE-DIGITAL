package com.bytedance.applog.priority;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Log.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\b\"\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010\nJ-\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\b\"\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Lcom/bytedance/applog/priority/EmptyLogger;", "Lcom/bytedance/applog/priority/Logger;", "()V", NotificationCompat.CATEGORY_ERROR, "", "formater", "", com.bytedance.common.process.cross.a.COL_ARGS, "", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "info", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class b implements f {
    @Override // com.bytedance.applog.priority.f
    public void a(String formater, Object... args) {
        Intrinsics.checkNotNullParameter(formater, "formater");
        Intrinsics.checkNotNullParameter(args, "args");
    }

    @Override // com.bytedance.applog.priority.f
    public void b(String formater, Object... args) {
        Intrinsics.checkNotNullParameter(formater, "formater");
        Intrinsics.checkNotNullParameter(args, "args");
    }
}
