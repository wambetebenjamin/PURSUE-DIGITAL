package com.bytedance.push.starter;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.push.i;
import com.bytedance.push.third.f;
import com.ss.android.common.applog.AppLog;
import com.ss.android.deviceregister.base.Oaid;
import com.ss.android.pushmanager.e;
import java.util.HashMap;
import java.util.Map;
/* compiled from: PushStarter.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11926a = false;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f11927b = false;
    public static boolean mEnableAutoInit;
    public static boolean mEnableAutoStart;

    public static void startPush() {
        Log.d("PushStarter", "startPush, mEnableAutoStart is " + mEnableAutoStart);
        if (mEnableAutoStart) {
            HashMap hashMap = new HashMap();
            if (f11926a) {
                AppLog.getSSIDs(hashMap);
            }
            if (f11927b && hashMap.isEmpty()) {
                com.bytedance.applog.AppLog.a((Map<String, String>) hashMap);
            }
            if (!f11926a && !f11927b) {
                throw new RuntimeException("no device_register or bdtracker find for push auto start, please set PushStarter.mEnableAutoStart=false and call BDPush.getPushService().start()");
            }
            try {
                Application application = i.get().getConfiguration().mApplication;
                if (f.inst(application).isLocalSupportChannel(23)) {
                    String str = "";
                    if (f11926a) {
                        str = Oaid.instance(application).getOaidId();
                    } else if (f11927b) {
                        str = com.bytedance.bdinstall.oaid.Oaid.instance(application).getOaidId();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        hashMap.put(e.f18492a, str);
                    }
                }
            } catch (Throwable unused) {
            }
            com.bytedance.push.b.getPushService().start(hashMap, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void initPushOnApplication(android.app.Application r10) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.push.starter.c.initPushOnApplication(android.app.Application):void");
    }
}
