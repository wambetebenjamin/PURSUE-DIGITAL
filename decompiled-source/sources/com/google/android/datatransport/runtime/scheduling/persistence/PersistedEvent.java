package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
/* loaded from: classes4.dex */
public abstract class PersistedEvent {
    public abstract EventInternal getEvent();

    public abstract long getId();

    public abstract TransportContext getTransportContext();

    public static PersistedEvent create(long j2, TransportContext transportContext, EventInternal eventInternal) {
        return new AutoValue_PersistedEvent(j2, transportContext, eventInternal);
    }
}
