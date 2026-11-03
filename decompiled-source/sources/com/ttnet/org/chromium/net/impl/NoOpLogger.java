package com.ttnet.org.chromium.net.impl;

import com.ttnet.org.chromium.net.impl.CronetLogger;
/* loaded from: classes9.dex */
public final class NoOpLogger extends CronetLogger {
    @Override // com.ttnet.org.chromium.net.impl.CronetLogger
    public void logCronetEngineCreation(int i2, CronetLogger.CronetEngineBuilderInfo cronetEngineBuilderInfo, CronetLogger.CronetVersion cronetVersion, CronetLogger.CronetSource cronetSource) {
    }

    @Override // com.ttnet.org.chromium.net.impl.CronetLogger
    public void logCronetTrafficInfo(int i2, CronetLogger.CronetTrafficInfo cronetTrafficInfo) {
    }
}
