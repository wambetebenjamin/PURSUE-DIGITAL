package com.twitter.sdk.android.core.internal;

import android.app.Activity;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.internal.ActivityLifecycleManager;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
/* loaded from: classes10.dex */
public class SessionMonitor<T extends Session> {
    private final ExecutorService executorService;
    protected final MonitorState monitorState;
    private final SessionManager<T> sessionManager;
    private final SessionVerifier sessionVerifier;
    private final SystemCurrentTimeProvider time;

    public SessionMonitor(SessionManager<T> sessionManager, ExecutorService executorService, SessionVerifier<T> sessionVerifier) {
        this(sessionManager, new SystemCurrentTimeProvider(), executorService, new MonitorState(), sessionVerifier);
    }

    SessionMonitor(SessionManager<T> sessionManager, SystemCurrentTimeProvider systemCurrentTimeProvider, ExecutorService executorService, MonitorState monitorState, SessionVerifier sessionVerifier) {
        this.time = systemCurrentTimeProvider;
        this.sessionManager = sessionManager;
        this.executorService = executorService;
        this.monitorState = monitorState;
        this.sessionVerifier = sessionVerifier;
    }

    public void monitorActivityLifecycle(ActivityLifecycleManager activityLifecycleManager) {
        activityLifecycleManager.registerCallbacks(new ActivityLifecycleManager.Callbacks() { // from class: com.twitter.sdk.android.core.internal.SessionMonitor.1
            @Override // com.twitter.sdk.android.core.internal.ActivityLifecycleManager.Callbacks
            public void onActivityStarted(Activity activity) {
                SessionMonitor.this.triggerVerificationIfNecessary();
            }
        });
    }

    public void triggerVerificationIfNecessary() {
        if (this.sessionManager.getActiveSession() != null && this.monitorState.beginVerification(this.time.getCurrentTimeMillis())) {
            this.executorService.submit(new Runnable() { // from class: com.twitter.sdk.android.core.internal.SessionMonitor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SessionMonitor.this.m721x462fe479();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: verifyAll */
    public void m721x462fe479() {
        for (T t : this.sessionManager.getSessionMap().values()) {
            this.sessionVerifier.verifySession(t);
        }
        this.monitorState.endVerification(this.time.getCurrentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public static class MonitorState {
        private static final long TIME_THRESHOLD_IN_MILLIS = 21600000;
        public long lastVerification;
        private final Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        public boolean verifying;

        public synchronized boolean beginVerification(long j2) {
            long j3 = this.lastVerification;
            boolean z = j2 - j3 > TIME_THRESHOLD_IN_MILLIS;
            boolean z2 = !isOnSameDate(j2, j3);
            if (this.verifying || !(z || z2)) {
                return false;
            }
            this.verifying = true;
            return true;
        }

        public synchronized void endVerification(long j2) {
            this.verifying = false;
            this.lastVerification = j2;
        }

        private boolean isOnSameDate(long j2, long j3) {
            this.utcCalendar.setTimeInMillis(j2);
            int i2 = this.utcCalendar.get(6);
            int i3 = this.utcCalendar.get(1);
            this.utcCalendar.setTimeInMillis(j3);
            return i2 == this.utcCalendar.get(6) && i3 == this.utcCalendar.get(1);
        }
    }
}
