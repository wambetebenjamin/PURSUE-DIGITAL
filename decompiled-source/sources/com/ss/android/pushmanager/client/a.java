package com.ss.android.pushmanager.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.push.utils.h;
import com.ss.android.message.d;
import java.lang.ref.WeakReference;
import java.util.List;
/* compiled from: MessageLogClient.java */
/* loaded from: classes9.dex */
public class a implements WeakHandler.IHandler {

    /* renamed from: e  reason: collision with root package name */
    private static final String f18472e = "com.ss.android.message.log.LogService";

    /* renamed from: g  reason: collision with root package name */
    private static a f18473g;

    /* renamed from: b  reason: collision with root package name */
    private WeakReference<Context> f18475b;

    /* renamed from: c  reason: collision with root package name */
    private Handler f18476c;

    /* renamed from: d  reason: collision with root package name */
    private Messenger f18477d;

    /* renamed from: a  reason: collision with root package name */
    private Messenger f18474a = null;

    /* renamed from: f  reason: collision with root package name */
    private long f18478f = 0;

    /* renamed from: h  reason: collision with root package name */
    private ServiceConnection f18479h = new ServiceConnection() { // from class: com.ss.android.pushmanager.client.a.1
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            a.this.f18474a = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.this.f18474a = new Messenger(iBinder);
            a.this.d();
        }
    };

    public static void start(Context context) {
        if (context == null) {
            return;
        }
        a aVar = f18473g;
        if (aVar == null) {
            f18473g = new a(context);
        } else if (aVar.f18474a == null) {
            aVar.a();
        }
    }

    public static void end(Context context) {
        a aVar;
        if (context == null || (aVar = f18473g) == null) {
            return;
        }
        aVar.b();
    }

    private a(Context context) {
        this.f18475b = new WeakReference<>(context.getApplicationContext());
        Looper looper = d.inst().getLooper();
        this.f18476c = new WeakHandler(looper == null ? Looper.getMainLooper() : looper, this);
        this.f18477d = new Messenger(this.f18476c);
        a();
    }

    private void a() {
        WeakReference<Context> weakReference = this.f18475b;
        if (weakReference != null && weakReference.get() != null) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this.f18475b.get(), f18472e));
                this.f18475b.get().bindService(intent, this.f18479h, 1);
            } catch (Throwable unused) {
            }
        }
    }

    private void b() {
        try {
            if (this.f18474a == null || this.f18475b.get() == null) {
                return;
            }
            this.f18475b.get().unbindService(this.f18479h);
        } catch (Throwable unused) {
        }
    }

    private void c() {
        if (h.debug()) {
            h.d("PushLog", "hanldeEnd");
        }
        if (this.f18474a != null) {
            try {
                b();
                this.f18474a = null;
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00da A[Catch: all -> 0x0154, TryCatch #0 {all -> 0x0154, blocks: (B:3:0x0004, B:6:0x000c, B:7:0x0024, B:12:0x002d, B:14:0x0033, B:15:0x0038, B:16:0x003d, B:18:0x0043, B:20:0x0049, B:22:0x005f, B:23:0x007f, B:25:0x0085, B:27:0x0096, B:43:0x013b, B:30:0x00a0, B:32:0x00cd, B:35:0x00d4, B:37:0x00da, B:39:0x0113, B:41:0x0132, B:42:0x0134, B:44:0x0141, B:46:0x0147, B:48:0x014c, B:49:0x0150), top: B:55:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0132 A[Catch: all -> 0x0154, TryCatch #0 {all -> 0x0154, blocks: (B:3:0x0004, B:6:0x000c, B:7:0x0024, B:12:0x002d, B:14:0x0033, B:15:0x0038, B:16:0x003d, B:18:0x0043, B:20:0x0049, B:22:0x005f, B:23:0x007f, B:25:0x0085, B:27:0x0096, B:43:0x013b, B:30:0x00a0, B:32:0x00cd, B:35:0x00d4, B:37:0x00da, B:39:0x0113, B:41:0x0132, B:42:0x0134, B:44:0x0141, B:46:0x0147, B:48:0x014c, B:49:0x0150), top: B:55:0x0004 }] */
    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleMsg(android.os.Message r20) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.client.a.handleMsg(android.os.Message):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            Message obtain = Message.obtain((Handler) null, 0);
            obtain.replyTo = this.f18477d;
            Bundle bundle = new Bundle();
            bundle.putLong("start_id", this.f18478f);
            if (h.debug()) {
                h.d("PushLog", "start_id = " + this.f18478f);
            }
            obtain.obj = bundle;
            this.f18474a.send(obtain);
        } catch (Throwable th) {
            th.printStackTrace();
            c();
        }
    }

    private void a(List<Long> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        try {
            Message obtain = Message.obtain((Handler) null, 1);
            obtain.replyTo = this.f18477d;
            Bundle bundle = new Bundle();
            bundle.putLongArray("event_ids", com.bytedance.common.utility.collection.a.toLongArray(list));
            if (h.debug()) {
                h.d("PushLog", "event_ids = " + list);
            }
            obtain.obj = bundle;
            this.f18474a.send(obtain);
        } catch (Throwable th) {
            th.printStackTrace();
            c();
        }
    }
}
