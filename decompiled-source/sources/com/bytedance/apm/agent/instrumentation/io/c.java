package com.bytedance.apm.agent.instrumentation.io;

import java.util.EventObject;
/* compiled from: StreamCompleteEvent.java */
/* loaded from: classes.dex */
public final class c extends EventObject {

    /* renamed from: a  reason: collision with root package name */
    private static final long f249a = 1;

    /* renamed from: b  reason: collision with root package name */
    private final long f250b;

    /* renamed from: c  reason: collision with root package name */
    private final Exception f251c;

    public c(Object obj, long j2, Exception exc) {
        super(obj);
        this.f250b = j2;
        this.f251c = exc;
    }

    public c(Object obj, long j2) {
        this(obj, j2, null);
    }

    public long getBytes() {
        return this.f250b;
    }

    public Exception getException() {
        return this.f251c;
    }

    public boolean isError() {
        return this.f251c != null;
    }
}
