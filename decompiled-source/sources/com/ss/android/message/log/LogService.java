package com.ss.android.message.log;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.push.utils.h;
import com.ss.android.message.d;
import com.ss.android.pushmanager.app.a;
import org.json.JSONArray;
/* loaded from: classes9.dex */
public class LogService extends Service implements WeakHandler.IHandler {
    public static final int MSG_END_LOG = 2;
    public static final int MSG_GET_LOG = 0;
    public static final int MSG_LOG_HANDLED = 1;
    public static final int PAGE_COUNT = 5;
    private Messenger mMessenger;

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        h.d("PushLog", "onUnBind " + this);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        h.d("PushLog", "onBind " + this);
        return this.mMessenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        h.d("PushLog", "onCreate " + this);
        try {
            a.InterfaceC0334a initHook = com.ss.android.pushmanager.app.a.getInitHook();
            if (initHook != null) {
                initHook.tryInit(this);
            }
        } catch (Throwable unused) {
        }
        this.mMessenger = new Messenger(new WeakHandler(d.inst().getLooper(), this));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        int onStartCommand = super.onStartCommand(intent, i2, i3);
        a.b pushHook = com.ss.android.pushmanager.app.a.getPushHook();
        if (pushHook == null || !pushHook.needControlFlares()) {
            return onStartCommand;
        }
        return 2;
    }

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        a aVar;
        try {
            try {
                aVar = a.getInstance(this);
            } catch (Exception e2) {
                e2.printStackTrace();
                aVar = null;
            }
            h.d("PushLog", "handleMsg msg.what = " + message.what);
            int i2 = message.what;
            if (i2 == 0) {
                if (message.replyTo != null) {
                    Messenger messenger = message.replyTo;
                    long j2 = ((Bundle) message.obj).getLong("start_id", 0L);
                    if (aVar == null) {
                        messenger.send(Message.obtain((Handler) null, 2));
                        return;
                    }
                    JSONArray events = aVar.getEvents(j2, 5);
                    if (events == null) {
                        messenger.send(Message.obtain((Handler) null, 2));
                        return;
                    }
                    Message obtain = Message.obtain((Handler) null, 0);
                    Bundle bundle = new Bundle();
                    bundle.putString(com.bytedance.applog.forward.b.f2687d, events.toString());
                    if (events.length() >= 5) {
                        bundle.putBoolean("has_more", true);
                    } else {
                        bundle.putBoolean("has_more", false);
                    }
                    h.d("PushLog", "events = " + bundle.get(com.bytedance.applog.forward.b.f2687d) + " has_more = " + bundle.get("has_more"));
                    obtain.obj = bundle;
                    messenger.send(obtain);
                }
            } else if (i2 == 1 && message.replyTo != null) {
                Messenger messenger2 = message.replyTo;
                long[] longArray = ((Bundle) message.obj).getLongArray("event_ids");
                if (longArray != null) {
                    h.d("PushLog", "event_ids = " + com.bytedance.common.utility.collection.a.toList(longArray));
                    if (aVar == null) {
                        messenger2.send(Message.obtain((Handler) null, 2));
                        return;
                    }
                    for (long j3 : longArray) {
                        aVar.deleteEvent(j3);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
