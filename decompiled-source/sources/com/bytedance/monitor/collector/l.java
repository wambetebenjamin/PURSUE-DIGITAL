package com.bytedance.monitor.collector;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import java.lang.reflect.Field;
/* compiled from: LooperUtil.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private static MessageQueue f9600a;

    /* renamed from: b  reason: collision with root package name */
    private static Field f9601b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f9602c;

    public static MessageQueue getMainMessageQueue() {
        if (f9600a == null && Looper.getMainLooper() != null) {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == Looper.myLooper()) {
                f9600a = Looper.myQueue();
            } else if (Build.VERSION.SDK_INT >= 23) {
                f9600a = mainLooper.getQueue();
            } else {
                try {
                    Field declaredField = mainLooper.getClass().getDeclaredField("mQueue");
                    declaredField.setAccessible(true);
                    f9600a = (MessageQueue) declaredField.get(mainLooper);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return f9600a;
    }

    public static Message getMessageObject(MessageQueue messageQueue) {
        Field field = f9601b;
        if (field == null) {
            try {
                Field declaredField = Class.forName("android.os.MessageQueue").getDeclaredField("mMessages");
                f9601b = declaredField;
                declaredField.setAccessible(true);
                return (Message) f9601b.get(messageQueue);
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

    /* JADX INFO: Access modifiers changed from: protected */
    public static Message a(Message message) {
        Field field = f9602c;
        if (field == null) {
            try {
                Field declaredField = Class.forName("android.os.Message").getDeclaredField(Constants.BUTTON_NEXT);
                f9602c = declaredField;
                declaredField.setAccessible(true);
                return (Message) f9602c.get(message);
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
