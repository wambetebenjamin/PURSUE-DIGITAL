package com.oasis.listenermanager.senders;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.listenermanager.listeners.BaseOasisApplicationEventsListener;
import com.oasis.listenermanager.protocol.IOasisApplicationEvents;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class OasisApplicationEventsSender extends BaseSender<BaseOasisApplicationEventsListener> implements IOasisApplicationEvents {
    public OasisApplicationEventsSender() {
        super(BaseOasisApplicationEventsListener.class);
    }

    @Override // com.oasis.listenermanager.protocol.IOasisApplicationEvents
    public void onAttachBaseContext(WeakReference<Context> weakReference) {
        for (BaseOasisApplicationEventsListener baseOasisApplicationEventsListener : getListener()) {
            try {
                baseOasisApplicationEventsListener.onAttachBaseContext(weakReference);
            } catch (Exception e2) {
                Logger.e(this.Tag, "Sending attachBaseContext event has an exception: " + e2.toString());
            }
        }
    }
}
