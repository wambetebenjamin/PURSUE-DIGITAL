package com.bytedance.ttgame.gecko;

import android.util.Pair;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public abstract class GsdkGeckoSyncListener {
    public void onActivateFail(GsdkGeckoUpdatePackage gsdkGeckoUpdatePackage, Throwable th) {
    }

    public void onActivateSuccess(GsdkGeckoUpdatePackage gsdkGeckoUpdatePackage) {
    }

    public void onCheckServerVersionFail(Map<String, List<Pair<String, Long>>> map, Throwable th) {
    }

    public void onCheckServerVersionSuccess(Map<String, List<Pair<String, Long>>> map, Map<String, List<GsdkGeckoUpdatePackage>> map2) {
    }

    public void onDownloadFail(GsdkGeckoUpdatePackage gsdkGeckoUpdatePackage, Throwable th) {
    }

    public void onDownloadSuccess(GsdkGeckoUpdatePackage gsdkGeckoUpdatePackage) {
    }

    public void onUpdateFailed(GsdkGeckoUpdatePackage gsdkGeckoUpdatePackage, Throwable th) {
    }

    public void onUpdateFinish() {
    }

    public void onUpdateStart(GsdkGeckoUpdatePackage gsdkGeckoUpdatePackage) {
    }

    public void onUpdateSuccess(String str, long j2) {
    }
}
