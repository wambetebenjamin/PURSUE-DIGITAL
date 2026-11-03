package com.bytedance.push.notification;

import android.os.Handler;
import android.os.Looper;
/* compiled from: AsyncSoundDownloaderWrapper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final com.bytedance.push.sound.a f11553a;

    /* renamed from: b  reason: collision with root package name */
    private Handler f11554b;

    public b(com.bytedance.push.sound.a aVar) {
        this.f11553a = aVar;
    }

    public void downloadSound(final String str, final String str2, final j jVar) {
        if (this.f11554b == null) {
            this.f11554b = new Handler(Looper.getMainLooper());
        }
        com.bytedance.common.push.d.a(new Runnable() { // from class: com.bytedance.push.notification.b.1
            @Override // java.lang.Runnable
            public void run() {
                final boolean downLoadSound = b.this.f11553a.downLoadSound(str, str2);
                if (jVar == null) {
                    return;
                }
                b.this.f11554b.post(new Runnable() { // from class: com.bytedance.push.notification.b.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (downLoadSound) {
                            jVar.onSuccess(str2);
                        } else {
                            jVar.onFailed();
                        }
                    }
                });
            }
        });
    }
}
