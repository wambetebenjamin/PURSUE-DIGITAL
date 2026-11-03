package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import androidx.core.util.Pools;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.SoftAssertions;
/* loaded from: classes8.dex */
public class TouchEvent extends Event<TouchEvent> {
    private static final Pools.SynchronizedPool<TouchEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private static final int TOUCH_EVENTS_POOL_SIZE = 3;
    public static final long UNSET = Long.MIN_VALUE;
    private short mCoalescingKey;
    private MotionEvent mMotionEvent;
    private TouchEventType mTouchEventType;
    private float mViewX;
    private float mViewY;

    public static TouchEvent obtain(int i2, TouchEventType touchEventType, MotionEvent motionEvent, long j2, float f2, float f3, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        TouchEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new TouchEvent();
        }
        acquire.init(i2, touchEventType, motionEvent, j2, f2, f3, touchEventCoalescingKeyHelper);
        return acquire;
    }

    private TouchEvent() {
    }

    private void init(int i2, TouchEventType touchEventType, MotionEvent motionEvent, long j2, float f2, float f3, TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        super.init(i2);
        short s = 0;
        SoftAssertions.assertCondition(j2 != Long.MIN_VALUE, "Gesture start time must be initialized");
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            touchEventCoalescingKeyHelper.addCoalescingKey(j2);
        } else if (action == 1) {
            touchEventCoalescingKeyHelper.removeCoalescingKey(j2);
        } else if (action == 2) {
            s = touchEventCoalescingKeyHelper.getCoalescingKey(j2);
        } else if (action == 3) {
            touchEventCoalescingKeyHelper.removeCoalescingKey(j2);
        } else if (action == 5 || action == 6) {
            touchEventCoalescingKeyHelper.incrementCoalescingKey(j2);
        } else {
            throw new RuntimeException("Unhandled MotionEvent action: " + action);
        }
        this.mTouchEventType = touchEventType;
        this.mMotionEvent = MotionEvent.obtain(motionEvent);
        this.mCoalescingKey = s;
        this.mViewX = f2;
        this.mViewY = f3;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        ((MotionEvent) Assertions.assertNotNull(this.mMotionEvent)).recycle();
        this.mMotionEvent = null;
        EVENTS_POOL.release(this);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return TouchEventType.getJSEventName((TouchEventType) Assertions.assertNotNull(this.mTouchEventType));
    }

    /* renamed from: com.facebook.react.uimanager.events.TouchEvent$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$events$TouchEventType;

        static {
            int[] iArr = new int[TouchEventType.values().length];
            $SwitchMap$com$facebook$react$uimanager$events$TouchEventType = iArr;
            try {
                iArr[TouchEventType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.CANCEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$events$TouchEventType[TouchEventType.MOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$uimanager$events$TouchEventType[((TouchEventType) Assertions.assertNotNull(this.mTouchEventType)).ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            return false;
        }
        if (i2 == 4) {
            return true;
        }
        throw new RuntimeException("Unknown touch event type: " + this.mTouchEventType);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        TouchesHelper.sendTouchEvent(rCTEventEmitter, (TouchEventType) Assertions.assertNotNull(this.mTouchEventType), getViewTag(), this);
    }

    public MotionEvent getMotionEvent() {
        Assertions.assertNotNull(this.mMotionEvent);
        return this.mMotionEvent;
    }

    public float getViewX() {
        return this.mViewX;
    }

    public float getViewY() {
        return this.mViewY;
    }
}
