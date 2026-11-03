package com.facebook.internal;

import android.content.Intent;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AppCall.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/facebook/internal/AppCall;", "", "requestCode", "", "callId", "Ljava/util/UUID;", "(ILjava/util/UUID;)V", "getCallId", "()Ljava/util/UUID;", "getRequestCode", "()I", "setRequestCode", "(I)V", "requestIntent", "Landroid/content/Intent;", "getRequestIntent", "()Landroid/content/Intent;", "setRequestIntent", "(Landroid/content/Intent;)V", "setPending", "", "Companion", "facebook-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class AppCall {
    public static final Companion Companion = new Companion(null);
    private static AppCall currentPendingCall;
    private final UUID callId;
    private int requestCode;
    private Intent requestIntent;

    public AppCall(int i2) {
        this(i2, null, 2, null);
    }

    @JvmStatic
    public static final synchronized AppCall finishPendingCall(UUID uuid, int i2) {
        synchronized (AppCall.class) {
            if (CrashShieldHandler.isObjectCrashing(AppCall.class)) {
                return null;
            }
            return Companion.finishPendingCall(uuid, i2);
        }
    }

    public AppCall(int i2, UUID callId) {
        Intrinsics.checkNotNullParameter(callId, "callId");
        this.requestCode = i2;
        this.callId = callId;
    }

    public static final /* synthetic */ AppCall access$getCurrentPendingCall$cp() {
        if (CrashShieldHandler.isObjectCrashing(AppCall.class)) {
            return null;
        }
        try {
            return currentPendingCall;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppCall.class);
            return null;
        }
    }

    public static final /* synthetic */ void access$setCurrentPendingCall$cp(AppCall appCall) {
        if (CrashShieldHandler.isObjectCrashing(AppCall.class)) {
            return;
        }
        try {
            currentPendingCall = appCall;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, AppCall.class);
        }
    }

    public final int getRequestCode() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return 0;
        }
        try {
            return this.requestCode;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return 0;
        }
    }

    public final void setRequestCode(int i2) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            this.requestCode = i2;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ AppCall(int r1, java.util.UUID r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r3 = r3 & 2
            if (r3 == 0) goto Ld
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r3 = "randomUUID()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        Ld:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AppCall.<init>(int, java.util.UUID, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final UUID getCallId() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.callId;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final Intent getRequestIntent() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return this.requestIntent;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final void setRequestIntent(Intent intent) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return;
        }
        try {
            this.requestIntent = intent;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
        }
    }

    public final boolean setPending() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return Companion.setCurrentPendingCall(this);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    /* compiled from: AppCall.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0002R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/facebook/internal/AppCall$Companion;", "", "()V", "<set-?>", "Lcom/facebook/internal/AppCall;", "currentPendingCall", "getCurrentPendingCall", "()Lcom/facebook/internal/AppCall;", "finishPendingCall", "callId", "Ljava/util/UUID;", "requestCode", "", "setCurrentPendingCall", "", "appCall", "facebook-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes8.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AppCall getCurrentPendingCall() {
            return AppCall.access$getCurrentPendingCall$cp();
        }

        @JvmStatic
        public final synchronized AppCall finishPendingCall(UUID callId, int i2) {
            Intrinsics.checkNotNullParameter(callId, "callId");
            AppCall currentPendingCall = getCurrentPendingCall();
            if (currentPendingCall != null && Intrinsics.areEqual(currentPendingCall.getCallId(), callId) && currentPendingCall.getRequestCode() == i2) {
                setCurrentPendingCall(null);
                return currentPendingCall;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized boolean setCurrentPendingCall(AppCall appCall) {
            AppCall currentPendingCall;
            currentPendingCall = getCurrentPendingCall();
            AppCall.access$setCurrentPendingCall$cp(appCall);
            return currentPendingCall != null;
        }
    }
}
