package com.bytedance.ttnet.diagnosis;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsConstants;
/* loaded from: classes8.dex */
public class TTGameDiagnosisService {
    private static final String TAG = "TTGameDiagnosisService";
    private static volatile TTGameDiagnosisService sInstance;
    private IDiagnosisRequest mRequest = null;
    private volatile boolean mIsMonitoring = false;
    private IDiagnosisCallback mDiagnosisCallback = new IDiagnosisCallback() { // from class: com.bytedance.ttnet.diagnosis.TTGameDiagnosisService$$ExternalSyntheticLambda0
        @Override // com.bytedance.ttnet.diagnosis.IDiagnosisCallback
        public final void onDiagnosisComplete(String str) {
            TTGameDiagnosisService.lambda$new$0(str);
        }
    };

    public static TTGameDiagnosisService inst() {
        if (sInstance == null) {
            synchronized (TTGameDiagnosisService.class) {
                if (sInstance == null) {
                    sInstance = new TTGameDiagnosisService();
                }
            }
        }
        return sInstance;
    }

    public void monitorBegin(String str, String str2) {
        synchronized (this) {
            if (this.mIsMonitoring) {
                return;
            }
            IDiagnosisRequest createRequest = TTNetDiagnosisService.createRequest(4, str, 0, Integer.MAX_VALUE);
            this.mRequest = createRequest;
            createRequest.start(this.mDiagnosisCallback);
            this.mRequest.doExtraCommand(WsConstants.KEY_CONNECTION_EXTRA_INFO, str2);
            this.mIsMonitoring = true;
        }
    }

    public void monitorEnd() {
        monitorEnd(null);
    }

    public void monitorEnd(String str) {
        synchronized (this) {
            if (this.mIsMonitoring) {
                if (str != null) {
                    this.mRequest.doExtraCommand(WsConstants.KEY_CONNECTION_EXTRA_INFO, str);
                }
                this.mRequest.doExtraCommand("finish", "");
                this.mIsMonitoring = false;
            }
        }
    }

    public void doDiagnosisDuringGaming(String str) {
        synchronized (this) {
            if (this.mIsMonitoring) {
                this.mRequest.doExtraCommand("diagnosis", str);
            }
        }
    }

    public boolean isMonitoring() {
        return this.mIsMonitoring;
    }

    private TTGameDiagnosisService() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$0(String str) {
        if (Logger.debug()) {
            Logger.d(TAG, "onDiagnosisComplete: " + str);
        }
    }
}
