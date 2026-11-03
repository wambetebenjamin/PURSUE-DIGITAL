package com.bytedance.apm.launch;

import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import java.lang.reflect.Field;
import java.util.List;
/* compiled from: LaunchSceneHelper.java */
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1210a = "LaunchSceneHelper";

    public static Intent getFirstComponentIntent() {
        Intent d2;
        Intent d3;
        try {
            MessageQueue messageQueue = (MessageQueue) com.bytedance.monitor.util.b.readField(Looper.getMainLooper(), "mQueue");
            synchronized (messageQueue) {
                Message message = (Message) com.bytedance.monitor.util.b.readField(messageQueue, "mMessages");
                if (message == null || message.getTarget() != com.bytedance.monitor.util.a.getHInActivityThread() || !com.bytedance.monitor.util.a.isMessageNeedInterrupt(message) || (d3 = d(message)) == null) {
                    Field field = com.bytedance.monitor.util.b.getField(Message.class, Constants.BUTTON_NEXT);
                    field.setAccessible(true);
                    while (message != null) {
                        message = (Message) com.bytedance.monitor.util.b.readField(field, message);
                        if (message != null && message.getTarget() == com.bytedance.monitor.util.a.getHInActivityThread() && com.bytedance.monitor.util.a.isMessageNeedInterrupt(message) && (d2 = d(message)) != null) {
                            return d2;
                        }
                    }
                    return null;
                }
                return d3;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static Intent getFirstActivityIntent() {
        int i2;
        int i3;
        Intent d2;
        try {
            MessageQueue messageQueue = (MessageQueue) com.bytedance.monitor.util.b.readField(Looper.getMainLooper(), "mQueue");
            synchronized (messageQueue) {
                Message message = (Message) com.bytedance.monitor.util.b.readField(messageQueue, "mMessages");
                if (message != null && message.getTarget() == com.bytedance.monitor.util.a.getHInActivityThread() && com.bytedance.monitor.util.a.isMessageNeedInterrupt(message) && (((i3 = message.what) == 100 || i3 == 159) && (d2 = d(message)) != null)) {
                    return d2;
                }
                Field field = com.bytedance.monitor.util.b.getField(Message.class, Constants.BUTTON_NEXT);
                field.setAccessible(true);
                while (message != null) {
                    message = (Message) com.bytedance.monitor.util.b.readField(field, message);
                    if (message != null && message.getTarget() == com.bytedance.monitor.util.a.getHInActivityThread() && com.bytedance.monitor.util.a.isMessageNeedInterrupt(message) && ((i2 = message.what) == 100 || i2 == 159)) {
                        Intent d3 = d(message);
                        if (d3 != null) {
                            return d3;
                        }
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static Intent d(Message message) {
        int i2 = message.what;
        if (i2 != 100) {
            if (i2 != 121) {
                if (i2 != 159) {
                    switch (i2) {
                        case 113:
                            return c(message);
                        case 114:
                        case 115:
                            break;
                        default:
                            return null;
                    }
                }
            }
            return b(message);
        }
        return a(message);
    }

    static Intent a(Message message) {
        Intent intent;
        Object obj = message.obj;
        if (Build.VERSION.SDK_INT < 28 && message.what == 100) {
            try {
                return (Intent) com.bytedance.monitor.util.b.readField(obj, "intent");
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            }
        } else if (Build.VERSION.SDK_INT < 28 || message.what != 159) {
            return null;
        } else {
            try {
                List list = (List) com.bytedance.monitor.util.b.readField(obj, "mActivityCallbacks");
                if (list != null) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Object obj2 = list.get(i2);
                        if (obj2 != null && (intent = (Intent) com.bytedance.monitor.util.b.readField(obj2, "mIntent")) != null) {
                            return intent;
                        }
                    }
                    return null;
                }
                return null;
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    static Intent b(Message message) {
        String str;
        if (message.what == 114 || message.what == 121) {
            str = "intent";
        } else {
            str = message.what == 115 ? com.bytedance.common.process.cross.a.COL_ARGS : "";
        }
        if (str.isEmpty() || message.obj == null) {
            return null;
        }
        try {
            return (Intent) com.bytedance.monitor.util.b.readField(message.obj, str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static Intent c(Message message) {
        String str = message.what == 113 ? "intent" : "";
        if (str.isEmpty() || message.obj == null) {
            return null;
        }
        try {
            return (Intent) com.bytedance.monitor.util.b.readField(message.obj, str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
