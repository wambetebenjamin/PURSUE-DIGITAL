package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.ApplicationStatus;
import com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect;
/* loaded from: classes9.dex */
public class RegistrationPolicyApplicationStatus extends NetworkChangeNotifierAutoDetect.RegistrationPolicy implements ApplicationStatus.ApplicationStateListener {
    private boolean mDestroyed;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    public void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        super.init(networkChangeNotifierAutoDetect);
        ApplicationStatus.registerApplicationStateListener(this);
        onApplicationStateChange(0);
    }

    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void destroy() {
        if (this.mDestroyed) {
            return;
        }
        ApplicationStatus.unregisterApplicationStateListener(this);
        this.mDestroyed = true;
    }

    @Override // com.ttnet.org.chromium.base.ApplicationStatus.ApplicationStateListener
    public void onApplicationStateChange(int i2) {
        if (ApplicationStatus.hasVisibleActivities()) {
            register();
        } else {
            unregister();
        }
    }
}
