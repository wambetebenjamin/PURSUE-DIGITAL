package com.bytedance.ttgame.gecko;

import android.util.Pair;
import com.bytedance.geckox.listener.a;
import com.bytedance.geckox.model.UpdatePackage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class UIThreadGeckoUpdateListener extends a {
    private a mListener;

    public UIThreadGeckoUpdateListener(a aVar) {
        Objects.requireNonNull(aVar, "listener == null");
        this.mListener = aVar;
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckServerVersionSuccess(final Map<String, List<Pair<String, Long>>> map, final Map<String, List<UpdatePackage>> map2) {
        super.onCheckServerVersionSuccess(map, map2);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.1
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onCheckServerVersionSuccess(map, map2);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckServerVersionFail(final Map<String, List<Pair<String, Long>>> map, final Throwable th) {
        super.onCheckServerVersionFail(map, th);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.2
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onCheckServerVersionFail(map, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onActivateSuccess(final UpdatePackage updatePackage) {
        super.onActivateSuccess(updatePackage);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.3
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onActivateSuccess(updatePackage);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onActivateFail(final UpdatePackage updatePackage, final Throwable th) {
        super.onActivateFail(updatePackage, th);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.4
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onActivateFail(updatePackage, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onDownloadSuccess(final UpdatePackage updatePackage) {
        super.onDownloadSuccess(updatePackage);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.5
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onDownloadSuccess(updatePackage);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onDownloadFail(final UpdatePackage updatePackage, final Throwable th) {
        super.onDownloadFail(updatePackage, th);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.6
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onDownloadFail(updatePackage, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateStart(final UpdatePackage updatePackage) {
        super.onUpdateStart(updatePackage);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.7
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onUpdateStart(updatePackage);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdating(final String str) {
        super.onUpdating(str);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.8
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onUpdating(str);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateSuccess(final String str, final long j2) {
        super.onUpdateSuccess(str, j2);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.9
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onUpdateSuccess(str, j2);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateFailed(final String str, final Throwable th) {
        super.onUpdateFailed(str, th);
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.10
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onUpdateFailed(str, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateFinish() {
        super.onUpdateFinish();
        UIThreadTool.runOnUIThread(new Runnable() { // from class: com.bytedance.ttgame.gecko.UIThreadGeckoUpdateListener.11
            @Override // java.lang.Runnable
            public void run() {
                UIThreadGeckoUpdateListener.this.mListener.onUpdateFinish();
            }
        });
    }
}
