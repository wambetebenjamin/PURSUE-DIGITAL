package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.ChoreographerCompat;
import java.util.ArrayDeque;
/* loaded from: classes8.dex */
public class ReactChoreographer {
    private static ReactChoreographer sInstance;
    private volatile ChoreographerCompat mChoreographer;
    private final Object mCallbackQueuesLock = new Object();
    private int mTotalCallbacks = 0;
    private boolean mHasPostedCallback = false;
    private final ReactChoreographerDispatcher mReactChoreographerDispatcher = new ReactChoreographerDispatcher();
    private final ArrayDeque<ChoreographerCompat.FrameCallback>[] mCallbackQueues = new ArrayDeque[CallbackType.values().length];

    static /* synthetic */ int access$610(ReactChoreographer reactChoreographer) {
        int i2 = reactChoreographer.mTotalCallbacks;
        reactChoreographer.mTotalCallbacks = i2 - 1;
        return i2;
    }

    /* loaded from: classes8.dex */
    public enum CallbackType {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);
        
        private final int mOrder;

        CallbackType(int i2) {
            this.mOrder = i2;
        }

        int getOrder() {
            return this.mOrder;
        }
    }

    public static void initialize() {
        if (sInstance == null) {
            sInstance = new ReactChoreographer();
        }
    }

    public static ReactChoreographer getInstance() {
        return sInstance;
    }

    private ReactChoreographer() {
        int i2 = 0;
        while (true) {
            ArrayDeque<ChoreographerCompat.FrameCallback>[] arrayDequeArr = this.mCallbackQueues;
            if (i2 < arrayDequeArr.length) {
                arrayDequeArr[i2] = new ArrayDeque<>();
                i2++;
            } else {
                initializeChoreographer(null);
                return;
            }
        }
    }

    public void postFrameCallback(CallbackType callbackType, ChoreographerCompat.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueuesLock) {
            this.mCallbackQueues[callbackType.getOrder()].addLast(frameCallback);
            int i2 = this.mTotalCallbacks + 1;
            this.mTotalCallbacks = i2;
            if (i2 <= 0) {
                return;
            }
            if (!this.mHasPostedCallback) {
                if (this.mChoreographer == null) {
                    initializeChoreographer(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ReactChoreographer.this.postFrameCallbackOnChoreographer();
                        }
                    });
                } else {
                    postFrameCallbackOnChoreographer();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postFrameCallbackOnChoreographer() {
        this.mChoreographer.postFrameCallback(this.mReactChoreographerDispatcher);
        this.mHasPostedCallback = true;
    }

    public void initializeChoreographer(final Runnable runnable) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ReactChoreographer.class) {
                    if (ReactChoreographer.this.mChoreographer == null) {
                        ReactChoreographer.this.mChoreographer = ChoreographerCompat.getInstance();
                    }
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    public void removeFrameCallback(CallbackType callbackType, ChoreographerCompat.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueuesLock) {
            if (this.mCallbackQueues[callbackType.getOrder()].removeFirstOccurrence(frameCallback)) {
                this.mTotalCallbacks--;
                maybeRemoveFrameCallback();
            } else {
                FLog.e(ReactConstants.TAG, "Tried to remove non-existent frame callback");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeRemoveFrameCallback() {
        int i2 = this.mTotalCallbacks;
        if (i2 >= 0 && i2 == 0 && this.mHasPostedCallback) {
            if (this.mChoreographer != null) {
                this.mChoreographer.removeFrameCallback(this.mReactChoreographerDispatcher);
            }
            this.mHasPostedCallback = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class ReactChoreographerDispatcher extends ChoreographerCompat.FrameCallback {
        private ReactChoreographerDispatcher() {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j2) {
            synchronized (ReactChoreographer.this.mCallbackQueuesLock) {
                ReactChoreographer.this.mHasPostedCallback = false;
                for (int i2 = 0; i2 < ReactChoreographer.this.mCallbackQueues.length; i2++) {
                    ArrayDeque arrayDeque = ReactChoreographer.this.mCallbackQueues[i2];
                    int size = arrayDeque.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ChoreographerCompat.FrameCallback frameCallback = (ChoreographerCompat.FrameCallback) arrayDeque.pollFirst();
                        if (frameCallback != null) {
                            frameCallback.doFrame(j2);
                            ReactChoreographer.access$610(ReactChoreographer.this);
                        } else {
                            FLog.e(ReactConstants.TAG, "Tried to execute non-existent frame callback");
                        }
                    }
                }
                ReactChoreographer.this.maybeRemoveFrameCallback();
            }
        }
    }
}
