package com.oasis.listenermanager.senders;

import com.oasis.listenermanager.ListenerController;
import com.oasis.listenermanager.listeners.BaseEventsListener;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class BaseSender<T extends BaseEventsListener> {
    protected final Class<T> ListenerClass;
    protected final String ListenerTypeName;
    protected final String Tag;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseSender(Class<T> cls) {
        this.ListenerTypeName = cls.getName();
        this.ListenerClass = cls;
        this.Tag = cls.getName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<T> getListener() {
        return ListenerController.getListeners(this.ListenerClass);
    }
}
