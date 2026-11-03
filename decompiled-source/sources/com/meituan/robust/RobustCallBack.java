package com.meituan.robust;

import java.util.List;
/* loaded from: classes9.dex */
public interface RobustCallBack {
    void exceptionNotify(Throwable th, String str);

    boolean isExceptPatch(Patch patch);

    boolean isPatchSoInstalledSuccess(Patch patch);

    void logNotify(String str, String str2);

    void onPatchApplied(boolean z, Patch patch, String str);

    void onPatchFetched(boolean z, boolean z2, Patch patch);

    void onPatchListFetched(boolean z, boolean z2, List<Patch> list);

    void onPatchLoadComplete();

    void onPatchVerified(boolean z, Patch patch);

    boolean shouldInterceptPatchApply(Patch patch);
}
