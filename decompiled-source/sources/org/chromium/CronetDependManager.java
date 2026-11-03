package org.chromium;

import com.bytedance.frameworks.baselib.network.http.cronet.ICronetDepend;
/* loaded from: classes10.dex */
public class CronetDependManager implements ICronetDepend {
    private static volatile CronetDependManager sCronetDependManager;
    private ICronetDepend mCronetDependAdapter;

    private CronetDependManager() {
    }

    public static CronetDependManager inst() {
        if (sCronetDependManager == null) {
            synchronized (CronetDependManager.class) {
                if (sCronetDependManager == null) {
                    sCronetDependManager = new CronetDependManager();
                }
            }
        }
        return sCronetDependManager;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.cronet.ICronetDepend
    public void setAdapter(ICronetDepend iCronetDepend) {
        this.mCronetDependAdapter = iCronetDepend;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.cronet.ICronetDepend
    public boolean loggerDebug() {
        ICronetDepend iCronetDepend = this.mCronetDependAdapter;
        if (iCronetDepend != null) {
            return iCronetDepend.loggerDebug();
        }
        return false;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.cronet.ICronetDepend
    public void loggerD(String str, String str2) {
        ICronetDepend iCronetDepend = this.mCronetDependAdapter;
        if (iCronetDepend != null) {
            iCronetDepend.loggerD(str, str2);
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.cronet.ICronetDepend
    public String getSsCookieKey() {
        ICronetDepend iCronetDepend = this.mCronetDependAdapter;
        return iCronetDepend != null ? iCronetDepend.getSsCookieKey() : "X-SS-Cookie";
    }
}
