package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.impl.CronetFrontierClient;
/* loaded from: classes9.dex */
public class TTServiceInfo {
    public boolean connected;
    public int identity;
    public boolean lazy;
    public CronetFrontierClient.IServiceMessageReceiver listener;
    public boolean sequence = true;
    public ServicePriority priority = ServicePriority.Low;

    /* loaded from: classes9.dex */
    public enum ServicePriority {
        Low(0),
        Medium(1),
        High(2);
        
        final int priority;

        ServicePriority(int i2) {
            this.priority = i2;
        }

        public int getValue() {
            return this.priority;
        }
    }

    public TTServiceInfo(int i2, CronetFrontierClient.IServiceMessageReceiver iServiceMessageReceiver) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Service identity must be set greater than 0.");
        }
        if (iServiceMessageReceiver == null) {
            throw new IllegalArgumentException("Service listener must not be null.");
        }
        this.identity = i2;
        this.listener = iServiceMessageReceiver;
    }
}
