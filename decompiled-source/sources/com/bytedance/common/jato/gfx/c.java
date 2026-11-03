package com.bytedance.common.jato.gfx;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import java.lang.reflect.Field;
/* compiled from: LooperUtil.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static MessageQueue f4743a;

    /* renamed from: b  reason: collision with root package name */
    private static Field f4744b;

    /* renamed from: c  reason: collision with root package name */
    private static Field f4745c;

    public static MessageQueue a() {
        if (f4743a == null && Looper.getMainLooper() != null) {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == Looper.myLooper()) {
                f4743a = Looper.myQueue();
            } else if (Build.VERSION.SDK_INT >= 23) {
                f4743a = mainLooper.getQueue();
            } else {
                try {
                    Field declaredField = mainLooper.getClass().getDeclaredField("mQueue");
                    declaredField.setAccessible(true);
                    f4743a = (MessageQueue) declaredField.get(mainLooper);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return f4743a;
    }

    public static Message a(MessageQueue messageQueue) {
        Field field = f4744b;
        if (field == null) {
            try {
                Field declaredField = Class.forName("android.os.MessageQueue").getDeclaredField("mMessages");
                f4744b = declaredField;
                declaredField.setAccessible(true);
                return (Message) f4744b.get(messageQueue);
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

    protected static Message a(Message message) {
        Field field = f4745c;
        if (field == null) {
            try {
                Field declaredField = Class.forName("android.os.Message").getDeclaredField(Constants.BUTTON_NEXT);
                f4745c = declaredField;
                declaredField.setAccessible(true);
                return (Message) f4745c.get(message);
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
