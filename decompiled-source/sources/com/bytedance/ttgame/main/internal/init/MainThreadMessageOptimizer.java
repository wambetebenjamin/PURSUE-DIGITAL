package com.bytedance.ttgame.main.internal.init;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.util.Printer;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import timber.log.Timber;
/* loaded from: classes3.dex */
public class MainThreadMessageOptimizer {
    private static final int LAUNCH_ACTIVITY_TYPE = 1;
    private static final int PAUSE_ACTIVITY_TYPE = 0;
    private static final String TAG = "MainMsgOpt";
    private static volatile MainThreadMessageOptimizer sInstance;
    private int LAUNCH_ACTIVITY;
    private int PAUSE_ACTIVITY;
    private Class<?> activityThreadClass;
    private boolean available;
    private Class<?> handlerClass;
    private Handler mH;
    Field mMessagesField;
    private MessageQueue mainMessageQueue;
    private Class<?> messageClass;
    private Class<?> messageQueueClass;
    Field nextField;
    private volatile int optType = -1;
    private Printer msgListener = null;
    private final ConcurrentHashMap<Integer, Boolean> optMaps = new ConcurrentHashMap<>();

    public static MainThreadMessageOptimizer getInstance() {
        if (sInstance == null) {
            synchronized (MainThreadMessageOptimizer.class) {
                if (sInstance == null) {
                    sInstance = new MainThreadMessageOptimizer();
                }
            }
        }
        return sInstance;
    }

    public void start(int i2) {
        if (this.available) {
            if (this.msgListener == null) {
                setPrinter();
            }
            Log.e(TAG, "start " + i2);
            resetState(i2);
        }
    }

    private MainThreadMessageOptimizer() {
        this.activityThreadClass = null;
        this.handlerClass = null;
        this.messageQueueClass = null;
        this.messageClass = null;
        this.available = true;
        this.mH = null;
        this.mainMessageQueue = null;
        try {
            this.activityThreadClass = Class.forName("android.app.ActivityThread");
            this.handlerClass = Class.forName("android.os.Handler");
            this.messageQueueClass = Class.forName("android.os.MessageQueue");
            this.messageClass = Class.forName("android.os.Message");
            Handler hookActivityThreadHandler = hookActivityThreadHandler();
            this.mH = hookActivityThreadHandler;
            this.mainMessageQueue = hookMainMessageQueue(hookActivityThreadHandler);
            Field declaredField = this.mH.getClass().getDeclaredField("LAUNCH_ACTIVITY");
            declaredField.setAccessible(true);
            this.LAUNCH_ACTIVITY = ((Integer) declaredField.get(this.mH)).intValue();
            Field declaredField2 = this.mH.getClass().getDeclaredField("PAUSE_ACTIVITY");
            declaredField2.setAccessible(true);
            this.PAUSE_ACTIVITY = ((Integer) declaredField2.get(this.mH)).intValue();
            Field declaredField3 = this.messageQueueClass.getDeclaredField("mMessages");
            this.mMessagesField = declaredField3;
            declaredField3.setAccessible(true);
            Field declaredField4 = this.messageClass.getDeclaredField(Constants.BUTTON_NEXT);
            this.nextField = declaredField4;
            declaredField4.setAccessible(true);
            Log.d(TAG, "init main msg opt success");
        } catch (Throwable unused) {
            Log.e(TAG, "failed to get system class");
            this.available = false;
        }
    }

    private Handler hookActivityThreadHandler() {
        try {
            Object invoke = this.activityThreadClass.getDeclaredMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = this.activityThreadClass.getDeclaredField("mH");
            declaredField.setAccessible(true);
            return (Handler) declaredField.get(invoke);
        } catch (Throwable unused) {
            return null;
        }
    }

    private MessageQueue hookMainMessageQueue(Handler handler) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                return Looper.getMainLooper().getQueue();
            }
            Field declaredField = this.handlerClass.getDeclaredField("mQueue");
            declaredField.setAccessible(true);
            return (MessageQueue) declaredField.get(handler);
        } catch (Throwable th) {
            Timber.e(TAG, th.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean moveMessageToFront(Handler handler, MessageQueue messageQueue, int i2) {
        Field field;
        try {
            field = this.mMessagesField;
        } catch (Exception e2) {
            Timber.e(TAG, e2.getMessage());
        }
        if (field == null) {
            return false;
        }
        Message message = (Message) field.get(messageQueue);
        while (message != null) {
            if (message.what == i2 && message.getTarget() == handler) {
                Message obtain = Message.obtain(message);
                handler.removeMessages(message.what);
                handler.sendMessageAtFrontOfQueue(obtain);
                return true;
            }
            message = getNextMessage(message);
        }
        return false;
    }

    private Message getNextMessage(Message message) {
        Object obj;
        try {
            Field field = this.nextField;
            if (field != null && (obj = field.get(message)) != null) {
                return (Message) obj;
            }
        } catch (Exception e2) {
            Timber.e(TAG, e2.getMessage());
        }
        return null;
    }

    private void setPrinter() {
        this.msgListener = new Printer() { // from class: com.bytedance.ttgame.main.internal.init.MainThreadMessageOptimizer.1
            private boolean pauseOpt = false;

            @Override // android.util.Printer
            public void println(String str) {
                Boolean bool = (Boolean) MainThreadMessageOptimizer.this.optMaps.get(Integer.valueOf(MainThreadMessageOptimizer.this.optType));
                if (bool == null || bool.booleanValue()) {
                    return;
                }
                int i2 = MainThreadMessageOptimizer.this.optType;
                if (i2 != 0) {
                    if (i2 != 1) {
                        return;
                    }
                    MainThreadMessageOptimizer mainThreadMessageOptimizer = MainThreadMessageOptimizer.this;
                    if (mainThreadMessageOptimizer.moveMessageToFront(mainThreadMessageOptimizer.mH, MainThreadMessageOptimizer.this.mainMessageQueue, MainThreadMessageOptimizer.this.LAUNCH_ACTIVITY)) {
                        Log.e(MainThreadMessageOptimizer.TAG, "LAUNCH_ACTIVITY opt success");
                        MainThreadMessageOptimizer.this.optMaps.put(Integer.valueOf(MainThreadMessageOptimizer.this.optType), true);
                    }
                } else if (this.pauseOpt) {
                } else {
                    MainThreadMessageOptimizer mainThreadMessageOptimizer2 = MainThreadMessageOptimizer.this;
                    boolean moveMessageToFront = mainThreadMessageOptimizer2.moveMessageToFront(mainThreadMessageOptimizer2.mH, MainThreadMessageOptimizer.this.mainMessageQueue, MainThreadMessageOptimizer.this.PAUSE_ACTIVITY);
                    this.pauseOpt = moveMessageToFront;
                    if (moveMessageToFront) {
                        Log.e(MainThreadMessageOptimizer.TAG, "PAUSE_ACTIVITY opt success");
                        MainThreadMessageOptimizer.this.optMaps.put(Integer.valueOf(MainThreadMessageOptimizer.this.optType), true);
                    }
                }
            }
        };
        Looper.getMainLooper().setMessageLogging(this.msgListener);
    }

    public void resetState(int i2) {
        this.optType = i2;
        this.optMaps.put(Integer.valueOf(this.optType), false);
    }
}
