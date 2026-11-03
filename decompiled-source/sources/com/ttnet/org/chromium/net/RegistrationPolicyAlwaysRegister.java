package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect;
/* loaded from: classes9.dex */
public class RegistrationPolicyAlwaysRegister extends NetworkChangeNotifierAutoDetect.RegistrationPolicy {
    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    protected void destroy() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ttnet.org.chromium.net.NetworkChangeNotifierAutoDetect.RegistrationPolicy
    public void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        super.init(networkChangeNotifierAutoDetect);
        register();
    }
}
