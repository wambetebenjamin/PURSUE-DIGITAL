package com.oasis.coolos;
/* loaded from: classes9.dex */
public class DefaultCoolOSAgent extends CoolOSAgent {
    @Override // com.oasis.coolos.CoolOSAgent
    public void getOAID(DeviceIdListener deviceIdListener) {
        if (deviceIdListener != null) {
            deviceIdListener.onObtainSuccess(null);
        }
    }
}
