package com.ttnet.org.chromium.net;

import java.util.List;
/* loaded from: classes9.dex */
public abstract class TTNetDiagnosisRequest {

    /* loaded from: classes9.dex */
    public static abstract class Builder {
        public abstract TTNetDiagnosisRequest build();

        public abstract Builder setMultiNetAction(int i2);

        public abstract Builder setNetDetectType(int i2);

        public abstract Builder setRequestType(int i2);

        public abstract Builder setTargets(List<String> list);

        public abstract Builder setTimeout(int i2);
    }

    /* loaded from: classes9.dex */
    public interface Callback {
        void onNetDiagnosisRequestComplete(TTNetDiagnosisRequest tTNetDiagnosisRequest, String str);
    }

    public abstract void cancel();

    public abstract void doExtraCommand(String str, String str2);

    public abstract void start();
}
