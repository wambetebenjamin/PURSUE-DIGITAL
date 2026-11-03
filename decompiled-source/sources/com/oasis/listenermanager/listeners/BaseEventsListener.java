package com.oasis.listenermanager.listeners;
/* loaded from: classes9.dex */
public abstract class BaseEventsListener {
    protected final String ListenerTypeName;
    protected final String Tag;

    public <T> BaseEventsListener(Class<T> cls) {
        String name = cls.getName();
        this.ListenerTypeName = name;
        this.Tag = name;
    }

    public final String getListenerTypeName() {
        return this.ListenerTypeName;
    }
}
