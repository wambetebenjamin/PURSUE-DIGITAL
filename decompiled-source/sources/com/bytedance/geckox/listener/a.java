package com.bytedance.geckox.listener;

import android.util.Pair;
import com.bytedance.geckox.model.LocalPackageModel;
import com.bytedance.geckox.model.UpdatePackage;
import java.util.List;
import java.util.Map;
/* compiled from: GeckoUpdateListener.java */
/* loaded from: classes2.dex */
public abstract class a {
    public void onActivateFail(UpdatePackage updatePackage, Throwable th) {
    }

    public void onActivateSuccess(UpdatePackage updatePackage) {
    }

    public void onCheckRequestIntercept(int i2, Map<String, List<Pair<String, Long>>> map, Throwable th) {
    }

    public void onCheckServerVersionFail(Map<String, List<Pair<String, Long>>> map, Throwable th) {
    }

    public void onCheckServerVersionSuccess(Map<String, List<Pair<String, Long>>> map, Map<String, List<UpdatePackage>> map2) {
    }

    public void onClean(String str) {
    }

    public void onDownloadFail(UpdatePackage updatePackage, Throwable th) {
    }

    public void onDownloadProgress(UpdatePackage updatePackage, long j2, long j3) {
    }

    public void onDownloadSuccess(UpdatePackage updatePackage) {
    }

    public void onLocalNewestVersion(LocalPackageModel localPackageModel) {
    }

    public void onUpdateFailed(UpdatePackage updatePackage, Throwable th) {
    }

    @Deprecated
    public void onUpdateFailed(String str, Throwable th) {
    }

    public void onUpdateFinish() {
    }

    public void onUpdateStart(UpdatePackage updatePackage) {
    }

    public void onUpdateSuccess(UpdatePackage updatePackage, long j2) {
    }

    @Deprecated
    public void onUpdateSuccess(String str, long j2) {
    }

    public void onUpdating(String str) {
    }
}
