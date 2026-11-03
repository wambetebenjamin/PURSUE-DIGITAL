package com.oasis.crash;

import com.oasis.Logger.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class a extends CrashAgent {
    @Override // com.oasis.crash.CrashAgent
    public void debug(String str, String str2) {
        Logger.d(str, str2);
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.debug(str, str2);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void error(String str, String str2) {
        Logger.e(str, str2);
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.error(str, str2);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void info(String str, String str2) {
        Logger.i(str, str2);
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.info(str, str2);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void postException(String str, String str2, String str3) {
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.postException(str, str2, str3);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void postExceptionWithParam(String str, String str2, String str3, String str4, String str5) {
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.postExceptionWithParam(str, str2, str3, str4, str5);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void putUserData(String str, String str2) {
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.putUserData(str, str2);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void removeUserData(String str) {
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.removeUserData(str);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void setUserID(String str) {
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.setUserID(str);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void setUserSceneTag(int i2) {
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.setUserSceneTag(i2);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void verbose(String str, String str2) {
        Logger.v(str, str2);
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.verbose(str, str2);
        }
    }

    @Override // com.oasis.crash.CrashAgent
    public void warn(String str, String str2) {
        Logger.w(str, str2);
        CrashAgent crashAgent = this.nextAgent;
        if (crashAgent != null) {
            crashAgent.warn(str, str2);
        }
    }
}
