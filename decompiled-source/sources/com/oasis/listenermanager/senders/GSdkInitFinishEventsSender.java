package com.oasis.listenermanager.senders;

import com.oasis.Logger.Logger;
import com.oasis.listenermanager.listeners.BaseGSdkInitFinishEventsListener;
import com.oasis.listenermanager.protocol.IGSdkInitFinishEvents;
/* loaded from: classes9.dex */
public class GSdkInitFinishEventsSender extends BaseSender<BaseGSdkInitFinishEventsListener> implements IGSdkInitFinishEvents {
    public GSdkInitFinishEventsSender() {
        super(BaseGSdkInitFinishEventsListener.class);
    }

    @Override // com.oasis.listenermanager.protocol.IGSdkInitFinishEvents
    public final void onGSdkInitFinish(boolean z) {
        for (BaseGSdkInitFinishEventsListener baseGSdkInitFinishEventsListener : getListener()) {
            try {
                baseGSdkInitFinishEventsListener.onGSdkInitFinish(z);
            } catch (Exception e2) {
                Logger.e(this.Tag, "Sending onGSdkInitFinish event has an exception: " + e2.toString());
            }
        }
    }
}
