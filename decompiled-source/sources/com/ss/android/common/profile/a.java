package com.ss.android.common.profile;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.common.utility.k;
import com.bytedance.common.utility.l;
import com.ss.android.common.applog.UserProfileHelper;
import java.util.HashMap;
/* compiled from: ReportUserProfile.java */
/* loaded from: classes9.dex */
public class a implements Runnable {

    /* renamed from: f  reason: collision with root package name */
    private static final Handler f18086f = new Handler(Looper.getMainLooper());

    /* renamed from: a  reason: collision with root package name */
    private String f18087a;

    /* renamed from: b  reason: collision with root package name */
    private String f18088b;

    /* renamed from: c  reason: collision with root package name */
    private String f18089c;

    /* renamed from: d  reason: collision with root package name */
    private final UserProfileHelper.UserProfileCallback f18090d;

    /* renamed from: e  reason: collision with root package name */
    private Context f18091e;

    public a(String str, String str2, String str3, UserProfileHelper.UserProfileRetryCallback userProfileRetryCallback, Context context) {
        this.f18087a = str;
        this.f18088b = str2;
        this.f18089c = str3;
        this.f18090d = userProfileRetryCallback;
        this.f18091e = context;
        userProfileRetryCallback.setRunnable(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!l.isNetworkAvailable(this.f18091e)) {
                a(0);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            hashMap.put("X-APIKEY", this.f18088b);
            k.a aVar = new k.a();
            aVar.addCommonParams = true;
            k.getDefault().post(this.f18087a, this.f18089c.getBytes(), hashMap, aVar);
            a();
        } catch (Throwable th) {
            th.printStackTrace();
            a(1);
        }
    }

    private void a(final int i2) {
        f18086f.post(new Runnable() { // from class: com.ss.android.common.profile.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.f18090d != null) {
                    a.this.f18090d.onFail(i2);
                }
            }
        });
    }

    private void a() {
        f18086f.post(new Runnable() { // from class: com.ss.android.common.profile.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.this.f18090d.onSuccess();
            }
        });
    }
}
