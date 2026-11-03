package com.bytedance.ttgame.module.compliance.impl.protocol;

import gsdk.impl.compliance.protocol.b;
import gsdk.impl.compliance.protocol.j;
import java.io.Serializable;
/* loaded from: classes5.dex */
public class ProtocolManageEvent implements Serializable {
    public static final int CLOSE_PROTOCOL_TYPE = 4;
    public static final int FAILED = 1;
    public static final int OPEN_LICENSE_TYPE = 6;
    public static final int OPEN_USERCENTER_TYPE = 5;
    public static final int SUCCESS = 0;
    private int eventType;
    public boolean hasAgreed;
    private b result;

    public b getResult() {
        return this.result;
    }

    public void setResult(b bVar) {
        this.result = bVar;
    }

    public int getEventType() {
        j.b("protocol", "getEventType:" + this.eventType);
        return this.eventType;
    }

    public void setEventType(int i2) {
        this.eventType = i2;
    }

    public ProtocolManageEvent(b bVar, int i2) {
        this.result = null;
        this.result = bVar;
        this.eventType = i2;
    }

    public ProtocolManageEvent(boolean z, int i2) {
        this.result = null;
        this.hasAgreed = z;
        this.eventType = i2;
    }

    public String toString() {
        return "AccountManageEvent{result=" + this.result + ", eventType=" + this.eventType + '}';
    }
}
