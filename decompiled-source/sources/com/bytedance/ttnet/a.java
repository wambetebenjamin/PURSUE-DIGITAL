package com.bytedance.ttnet;

import com.bytedance.retrofit2.AppLaunchTimeStampProvider;
import java.util.Map;
/* compiled from: AbsOptionalTTNetDepend.java */
/* loaded from: classes8.dex */
public abstract class a implements AppLaunchTimeStampProvider {
    public Map<String, String> a() {
        return null;
    }

    public int b() {
        return 0;
    }

    public String[] c() {
        return null;
    }

    public String[] d() {
        return null;
    }

    public boolean e() {
        return true;
    }

    public boolean f() {
        return false;
    }

    @Override // com.bytedance.retrofit2.AppLaunchTimeStampProvider
    public long getAppStartCPUTimeStamp() {
        return -1L;
    }

    @Override // com.bytedance.retrofit2.AppLaunchTimeStampProvider
    public long getAppStartWallClockTimeStamp() {
        return -1L;
    }
}
