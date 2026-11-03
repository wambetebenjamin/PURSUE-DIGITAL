package com.bytedance.geckox.listener;

import android.util.Pair;
import com.bytedance.geckox.model.UpdatePackage;
import com.bytedance.geckox.utils.q;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* compiled from: UIThreadGeckoUpdateListener.java */
/* loaded from: classes2.dex */
public final class d extends a {

    /* renamed from: a  reason: collision with root package name */
    private a f9164a;

    public d(a aVar) {
        Objects.requireNonNull(aVar, "listener == null");
        this.f9164a = aVar;
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckServerVersionSuccess(final Map<String, List<Pair<String, Long>>> map, final Map<String, List<UpdatePackage>> map2) {
        super.onCheckServerVersionSuccess(map, map2);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.1
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onCheckServerVersionSuccess(map, map2);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onCheckServerVersionFail(final Map<String, List<Pair<String, Long>>> map, final Throwable th) {
        super.onCheckServerVersionFail(map, th);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.4
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onCheckServerVersionFail(map, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onActivateSuccess(final UpdatePackage updatePackage) {
        super.onActivateSuccess(updatePackage);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.5
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onActivateSuccess(updatePackage);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onActivateFail(final UpdatePackage updatePackage, final Throwable th) {
        super.onActivateFail(updatePackage, th);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.6
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onActivateFail(updatePackage, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onDownloadSuccess(final UpdatePackage updatePackage) {
        super.onDownloadSuccess(updatePackage);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.7
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onDownloadSuccess(updatePackage);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onDownloadFail(final UpdatePackage updatePackage, final Throwable th) {
        super.onDownloadFail(updatePackage, th);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.8
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onDownloadFail(updatePackage, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateStart(final UpdatePackage updatePackage) {
        super.onUpdateStart(updatePackage);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.9
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onUpdateStart(updatePackage);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdating(final String str) {
        super.onUpdating(str);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.10
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onUpdating(str);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateSuccess(final String str, final long j2) {
        super.onUpdateSuccess(str, j2);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.11
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onUpdateSuccess(str, j2);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateFailed(final String str, final Throwable th) {
        super.onUpdateFailed(str, th);
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.2
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onUpdateFailed(str, th);
            }
        });
    }

    @Override // com.bytedance.geckox.listener.a
    public void onUpdateFinish() {
        super.onUpdateFinish();
        q.a(new Runnable() { // from class: com.bytedance.geckox.listener.d.3
            @Override // java.lang.Runnable
            public void run() {
                d.this.f9164a.onUpdateFinish();
            }
        });
    }
}
