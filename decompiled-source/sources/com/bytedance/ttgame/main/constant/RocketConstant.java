package com.bytedance.ttgame.main.constant;

import kotlin.Metadata;
/* compiled from: RocketConstant.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/main/constant/RocketConstant;", "", "()V", "applicationStartTime", "", "getApplicationStartTime", "()J", "setApplicationStartTime", "(J)V", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RocketConstant {
    public static final RocketConstant INSTANCE = new RocketConstant();
    private static long applicationStartTime;

    private RocketConstant() {
    }

    public final long getApplicationStartTime() {
        return applicationStartTime;
    }

    public final void setApplicationStartTime(long j2) {
        applicationStartTime = j2;
    }
}
