package com.bytedance.common.wschannel.channel.impl.ok.policy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.meituan.robust.Constants;
import okhttp3.Response;
/* compiled from: RetryIntervalPolicy.java */
/* loaded from: classes2.dex */
public class a implements b {

    /* renamed from: b  reason: collision with root package name */
    private static final long f5511b = 5000;

    /* renamed from: c  reason: collision with root package name */
    private static final long f5512c = 120000;

    /* renamed from: d  reason: collision with root package name */
    private final Context f5513d;

    /* renamed from: e  reason: collision with root package name */
    private int f5514e = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        this.f5513d = context;
    }

    private static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public long a() {
        if (a(this.f5513d)) {
            int i2 = this.f5514e + 1;
            this.f5514e = i2;
            return (long) (Math.min((1 << i2) * 5000, 120000L) + (Math.random() * 10.0d * 1000.0d));
        }
        return -1L;
    }

    @Override // com.bytedance.common.wschannel.channel.impl.ok.policy.b
    public long b() {
        return (long) ((Math.random() * 4500.0d) + 500.0d);
    }

    @Override // com.bytedance.common.wschannel.channel.impl.ok.policy.b
    public void c() {
        this.f5514e = 0;
    }

    @Override // com.bytedance.common.wschannel.channel.impl.ok.policy.b
    public long a(Response response) {
        int code;
        String header;
        String[] split;
        long a2 = a();
        if (a2 == -1) {
            return -1L;
        }
        if (response != null) {
            try {
                if (!response.isSuccessful() && (((code = response.code()) == 414 || code == 511 || code == 512) && (header = response.header("Handshake-Options")) != null)) {
                    for (String str : header.split(Constants.PACKNAME_END)) {
                        if (!TextUtils.isEmpty(str)) {
                            String[] split2 = str.split("=");
                            if ("reconnect-interval".equals(split2[0])) {
                                String str2 = split2[1];
                                Logger.d(com.bytedance.common.wschannel.channel.impl.ok.b.f5415a, "override retry interval");
                                try {
                                    a2 = Long.parseLong(str2) * 1000;
                                    Logger.d(com.bytedance.common.wschannel.channel.impl.ok.b.f5415a, "read response hand shake option : reconnect-interval:" + a2 + " ms");
                                    break;
                                } catch (NumberFormatException unused) {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return a2;
    }
}
