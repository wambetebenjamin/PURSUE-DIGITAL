package com.bytedance.crash.anr;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LooperMonitor.java */
@Deprecated
/* loaded from: classes2.dex */
public class m {
    private static MessageQueue s;
    private static Field t;
    private static Field u;

    private static Message a(MessageQueue messageQueue) {
        Field field = t;
        if (field == null) {
            try {
                Field declaredField = Class.forName("android.os.MessageQueue").getDeclaredField("mMessages");
                t = declaredField;
                declaredField.setAccessible(true);
                return (Message) t.get(messageQueue);
            } catch (Exception unused) {
                return null;
            }
        }
        try {
            return (Message) field.get(messageQueue);
        } catch (Exception unused2) {
            return null;
        }
    }

    public static MessageQueue getMainMessageQueue() {
        if (s == null && Looper.getMainLooper() != null) {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == Looper.myLooper()) {
                s = Looper.myQueue();
            } else if (Build.VERSION.SDK_INT >= 23) {
                s = mainLooper.getQueue();
            } else {
                try {
                    Field declaredField = mainLooper.getClass().getDeclaredField("mQueue");
                    declaredField.setAccessible(true);
                    s = (MessageQueue) declaredField.get(mainLooper);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return s;
    }

    public static List<Message> dumpPendingMessages(int i2) {
        MessageQueue mainMessageQueue = getMainMessageQueue();
        if (mainMessageQueue == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        synchronized (mainMessageQueue) {
            Message a2 = a(mainMessageQueue);
            if (a2 == null) {
                return null;
            }
            while (a2 != null) {
                int i4 = i3 + 1;
                if (i3 >= i2) {
                    break;
                }
                arrayList.add(a2);
                a2 = a(a2);
                i3 = i4;
            }
            return arrayList;
        }
    }

    private static Message a(Message message) {
        Field field = u;
        if (field == null) {
            try {
                Field declaredField = Class.forName("android.os.Message").getDeclaredField(Constants.BUTTON_NEXT);
                u = declaredField;
                declaredField.setAccessible(true);
                return (Message) u.get(message);
            } catch (Exception unused) {
                return null;
            }
        }
        try {
            return (Message) field.get(message);
        } catch (Exception unused2) {
            return null;
        }
    }
}
