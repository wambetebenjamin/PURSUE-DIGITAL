package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import android.view.View;
import com.swmansion.gesturehandler.GestureHandler;
import java.util.Arrays;
/* loaded from: classes9.dex */
public class GestureHandler<T extends GestureHandler> {
    public static final int DIRECTION_DOWN = 8;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 4;
    private static final int HIT_SLOP_BOTTOM_IDX = 3;
    private static final int HIT_SLOP_HEIGHT_IDX = 5;
    private static final int HIT_SLOP_LEFT_IDX = 0;
    public static final float HIT_SLOP_NONE = Float.NaN;
    private static final int HIT_SLOP_RIGHT_IDX = 2;
    private static final int HIT_SLOP_TOP_IDX = 1;
    private static final int HIT_SLOP_WIDTH_IDX = 4;
    private static int MAX_POINTERS_COUNT = 11;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_BEGAN = 2;
    public static final int STATE_CANCELLED = 3;
    public static final int STATE_END = 5;
    public static final int STATE_FAILED = 1;
    public static final int STATE_UNDETERMINED = 0;
    private static short sNextEventCoalescingKey;
    private static MotionEvent.PointerCoords[] sPointerCoords;
    private static MotionEvent.PointerProperties[] sPointerProps;
    int mActivationIndex;
    private short mEventCoalescingKey;
    private float[] mHitSlop;
    private GestureHandlerInteractionController mInteractionController;
    boolean mIsActive;
    boolean mIsAwaiting;
    private float mLastEventOffsetX;
    private float mLastEventOffsetY;
    private float mLastX;
    private float mLastY;
    private OnTouchEventListener<T> mListener;
    private GestureHandlerOrchestrator mOrchestrator;
    private boolean mShouldCancelWhenOutside;
    private int mTag;
    private View mView;
    private boolean mWithinBounds;
    private float mX;
    private float mY;
    private final int[] mTrackedPointerIDs = new int[MAX_POINTERS_COUNT];
    private int mTrackedPointersCount = 0;
    private int mState = 0;
    private boolean mEnabled = true;
    private int mNumberOfPointers = 0;

    public static String stateToString(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                return null;
                            }
                            return "END";
                        }
                        return "ACTIVE";
                    }
                    return "CANCELLED";
                }
                return "BEGIN";
            }
            return "FAILED";
        }
        return "UNDETERMINED";
    }

    protected void onCancel() {
    }

    protected void onReset() {
    }

    protected void onStateChange(int i2, int i3) {
    }

    private static void initPointerProps(int i2) {
        if (sPointerProps == null) {
            int i3 = MAX_POINTERS_COUNT;
            sPointerProps = new MotionEvent.PointerProperties[i3];
            sPointerCoords = new MotionEvent.PointerCoords[i3];
        }
        while (i2 > 0) {
            MotionEvent.PointerProperties[] pointerPropertiesArr = sPointerProps;
            int i4 = i2 - 1;
            if (pointerPropertiesArr[i4] != null) {
                return;
            }
            pointerPropertiesArr[i4] = new MotionEvent.PointerProperties();
            sPointerCoords[i4] = new MotionEvent.PointerCoords();
            i2--;
        }
    }

    private static boolean hitSlopSet(float f2) {
        return !Float.isNaN(f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchStateChange(int i2, int i3) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onStateChange(this, i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchTouchEvent(MotionEvent motionEvent) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onTouchEvent(this, motionEvent);
        }
    }

    public boolean hasCommonPointers(GestureHandler gestureHandler) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.mTrackedPointerIDs;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] != -1 && gestureHandler.mTrackedPointerIDs[i2] != -1) {
                return true;
            }
            i2++;
        }
    }

    public T setShouldCancelWhenOutside(boolean z) {
        this.mShouldCancelWhenOutside = z;
        return this;
    }

    public T setEnabled(boolean z) {
        if (this.mView != null) {
            cancel();
        }
        this.mEnabled = z;
        return this;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public T setHitSlop(float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.mHitSlop == null) {
            this.mHitSlop = new float[6];
        }
        float[] fArr = this.mHitSlop;
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[2] = f4;
        fArr[3] = f5;
        fArr[4] = f6;
        fArr[5] = f7;
        if (hitSlopSet(f6) && hitSlopSet(f2) && hitSlopSet(f4)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined");
        }
        if (hitSlopSet(f6) && !hitSlopSet(f2) && !hitSlopSet(f4)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined");
        }
        if (hitSlopSet(f7) && hitSlopSet(f5) && hitSlopSet(f3)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined");
        }
        if (!hitSlopSet(f7) || hitSlopSet(f5) || hitSlopSet(f3)) {
            return this;
        }
        throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined");
    }

    public T setHitSlop(float f2) {
        return setHitSlop(f2, f2, f2, f2, Float.NaN, Float.NaN);
    }

    public T setInteractionController(GestureHandlerInteractionController gestureHandlerInteractionController) {
        this.mInteractionController = gestureHandlerInteractionController;
        return this;
    }

    public void setTag(int i2) {
        this.mTag = i2;
    }

    public int getTag() {
        return this.mTag;
    }

    public View getView() {
        return this.mView;
    }

    public float getX() {
        return this.mX;
    }

    public float getY() {
        return this.mY;
    }

    public int getNumberOfPointers() {
        return this.mNumberOfPointers;
    }

    public boolean isWithinBounds() {
        return this.mWithinBounds;
    }

    public short getEventCoalescingKey() {
        return this.mEventCoalescingKey;
    }

    public final void prepare(View view, GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        if (this.mView != null || this.mOrchestrator != null) {
            throw new IllegalStateException("Already prepared or hasn't been reset");
        }
        Arrays.fill(this.mTrackedPointerIDs, -1);
        this.mTrackedPointersCount = 0;
        this.mState = 0;
        this.mView = view;
        this.mOrchestrator = gestureHandlerOrchestrator;
    }

    private int findNextLocalPointerId() {
        int[] iArr;
        int i2 = 0;
        while (i2 < this.mTrackedPointersCount) {
            int i3 = 0;
            while (true) {
                iArr = this.mTrackedPointerIDs;
                if (i3 >= iArr.length || iArr[i3] == i2) {
                    break;
                }
                i3++;
            }
            if (i3 == iArr.length) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    public void startTrackingPointer(int i2) {
        int[] iArr = this.mTrackedPointerIDs;
        if (iArr[i2] == -1) {
            iArr[i2] = findNextLocalPointerId();
            this.mTrackedPointersCount++;
        }
    }

    public void stopTrackingPointer(int i2) {
        int[] iArr = this.mTrackedPointerIDs;
        if (iArr[i2] != -1) {
            iArr[i2] = -1;
            this.mTrackedPointersCount--;
        }
    }

    private boolean needAdapt(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.mTrackedPointersCount) {
            return true;
        }
        int i2 = 0;
        while (true) {
            int[] iArr = this.mTrackedPointerIDs;
            if (i2 >= iArr.length) {
                return false;
            }
            if (iArr[i2] != -1 && iArr[i2] != i2) {
                return true;
            }
            i2++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.MotionEvent adaptEvent(android.view.MotionEvent r27) {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            boolean r2 = r26.needAdapt(r27)
            if (r2 != 0) goto Lb
            return r1
        Lb:
            int r2 = r27.getActionMasked()
            r3 = 2
            r4 = 5
            r5 = 0
            r6 = -1
            r7 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L19
            goto L37
        L19:
            r4 = 6
            if (r2 == r7) goto L21
            if (r2 != r4) goto L1f
            goto L21
        L1f:
            r3 = r6
            goto L50
        L21:
            int r2 = r27.getActionIndex()
            int r8 = r1.getPointerId(r2)
            int[] r9 = r0.mTrackedPointerIDs
            r8 = r9[r8]
            if (r8 == r6) goto L4b
            int r3 = r0.mTrackedPointersCount
            if (r3 != r7) goto L34
            r4 = r7
        L34:
            r3 = r2
            r2 = r4
            goto L50
        L37:
            int r2 = r27.getActionIndex()
            int r8 = r1.getPointerId(r2)
            int[] r9 = r0.mTrackedPointerIDs
            r8 = r9[r8]
            if (r8 == r6) goto L4b
            int r3 = r0.mTrackedPointersCount
            if (r3 != r7) goto L4a
            r4 = r5
        L4a:
            r3 = r4
        L4b:
            r25 = r3
            r3 = r2
            r2 = r25
        L50:
            int r4 = r0.mTrackedPointersCount
            initPointerProps(r4)
            float r4 = r27.getX()
            float r7 = r27.getY()
            float r8 = r27.getRawX()
            float r9 = r27.getRawY()
            r1.setLocation(r8, r9)
            int r8 = r27.getPointerCount()
            r13 = r2
            r14 = r5
        L6e:
            if (r5 >= r8) goto L9c
            int r2 = r1.getPointerId(r5)
            int[] r9 = r0.mTrackedPointerIDs
            r9 = r9[r2]
            if (r9 == r6) goto L99
            android.view.MotionEvent$PointerProperties[] r9 = com.swmansion.gesturehandler.GestureHandler.sPointerProps
            r9 = r9[r14]
            r1.getPointerProperties(r5, r9)
            android.view.MotionEvent$PointerProperties[] r9 = com.swmansion.gesturehandler.GestureHandler.sPointerProps
            r9 = r9[r14]
            int[] r10 = r0.mTrackedPointerIDs
            r2 = r10[r2]
            r9.id = r2
            android.view.MotionEvent$PointerCoords[] r2 = com.swmansion.gesturehandler.GestureHandler.sPointerCoords
            r2 = r2[r14]
            r1.getPointerCoords(r5, r2)
            if (r5 != r3) goto L97
            int r2 = r14 << 8
            r13 = r13 | r2
        L97:
            int r14 = r14 + 1
        L99:
            int r5 = r5 + 1
            goto L6e
        L9c:
            long r9 = r27.getDownTime()
            long r11 = r27.getEventTime()
            android.view.MotionEvent$PointerProperties[] r15 = com.swmansion.gesturehandler.GestureHandler.sPointerProps
            android.view.MotionEvent$PointerCoords[] r16 = com.swmansion.gesturehandler.GestureHandler.sPointerCoords
            int r17 = r27.getMetaState()
            int r18 = r27.getButtonState()
            float r19 = r27.getXPrecision()
            float r20 = r27.getYPrecision()
            int r21 = r27.getDeviceId()
            int r22 = r27.getEdgeFlags()
            int r23 = r27.getSource()
            int r24 = r27.getFlags()
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            r1.setLocation(r4, r7)
            r2.setLocation(r4, r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.GestureHandler.adaptEvent(android.view.MotionEvent):android.view.MotionEvent");
    }

    public final void handle(MotionEvent motionEvent) {
        int i2;
        if (!this.mEnabled || (i2 = this.mState) == 3 || i2 == 1 || i2 == 5 || this.mTrackedPointersCount < 1) {
            return;
        }
        MotionEvent adaptEvent = adaptEvent(motionEvent);
        this.mX = adaptEvent.getX();
        this.mY = adaptEvent.getY();
        this.mNumberOfPointers = adaptEvent.getPointerCount();
        boolean isWithinBounds = isWithinBounds(this.mView, this.mX, this.mY);
        this.mWithinBounds = isWithinBounds;
        if (this.mShouldCancelWhenOutside && !isWithinBounds) {
            int i3 = this.mState;
            if (i3 == 4) {
                cancel();
                return;
            } else if (i3 == 2) {
                fail();
                return;
            } else {
                return;
            }
        }
        this.mLastX = GestureUtils.getLastPointerX(adaptEvent, true);
        this.mLastY = GestureUtils.getLastPointerY(adaptEvent, true);
        this.mLastEventOffsetX = adaptEvent.getRawX() - adaptEvent.getX();
        this.mLastEventOffsetY = adaptEvent.getRawY() - adaptEvent.getY();
        onHandle(adaptEvent);
        if (adaptEvent != motionEvent) {
            adaptEvent.recycle();
        }
    }

    private void moveToState(int i2) {
        int i3 = this.mState;
        if (i3 == i2) {
            return;
        }
        this.mState = i2;
        if (i2 == 4) {
            short s = sNextEventCoalescingKey;
            sNextEventCoalescingKey = (short) (s + 1);
            this.mEventCoalescingKey = s;
        }
        this.mOrchestrator.onHandlerStateChange(this, i2, i3);
        onStateChange(i2, i3);
    }

    public boolean wantEvents() {
        int i2;
        return (!this.mEnabled || (i2 = this.mState) == 1 || i2 == 3 || i2 == 5 || this.mTrackedPointersCount <= 0) ? false : true;
    }

    public int getState() {
        return this.mState;
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, gestureHandler);
    }

    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, gestureHandler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        if (gestureHandler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.mInteractionController;
        if (gestureHandlerInteractionController != null) {
            return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, gestureHandler);
        }
        return false;
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        if (gestureHandler == this || (gestureHandlerInteractionController = this.mInteractionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, gestureHandler);
    }

    public boolean isWithinBounds(View view, float f2, float f3) {
        float f4;
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.mHitSlop;
        if (fArr != null) {
            float f5 = fArr[0];
            float f6 = fArr[1];
            float f7 = fArr[2];
            float f8 = fArr[3];
            float f9 = hitSlopSet(f5) ? 0.0f - f5 : 0.0f;
            r4 = hitSlopSet(f6) ? 0.0f - f8 : 0.0f;
            if (hitSlopSet(f7)) {
                width += f7;
            }
            if (hitSlopSet(f8)) {
                height += f8;
            }
            float[] fArr2 = this.mHitSlop;
            float f10 = fArr2[4];
            float f11 = fArr2[5];
            if (hitSlopSet(f10)) {
                if (!hitSlopSet(f5)) {
                    f9 = width - f10;
                } else if (!hitSlopSet(f7)) {
                    width = f10 + f9;
                }
            }
            if (hitSlopSet(f11)) {
                if (!hitSlopSet(r4)) {
                    r4 = height - f11;
                } else if (!hitSlopSet(height)) {
                    height = r4 + f11;
                }
            }
            f4 = r4;
            r4 = f9;
        } else {
            f4 = 0.0f;
        }
        return f2 >= r4 && f2 <= width && f3 >= f4 && f3 <= height;
    }

    public final void cancel() {
        int i2 = this.mState;
        if (i2 == 4 || i2 == 0 || i2 == 2) {
            onCancel();
            moveToState(3);
        }
    }

    public final void fail() {
        int i2 = this.mState;
        if (i2 == 4 || i2 == 0 || i2 == 2) {
            moveToState(1);
        }
    }

    public final void activate() {
        int i2 = this.mState;
        if (i2 == 0 || i2 == 2) {
            moveToState(4);
        }
    }

    public final void begin() {
        if (this.mState == 0) {
            moveToState(2);
        }
    }

    public final void end() {
        int i2 = this.mState;
        if (i2 == 2 || i2 == 4) {
            moveToState(5);
        }
    }

    protected void onHandle(MotionEvent motionEvent) {
        moveToState(1);
    }

    public final void reset() {
        this.mView = null;
        this.mOrchestrator = null;
        Arrays.fill(this.mTrackedPointerIDs, -1);
        this.mTrackedPointersCount = 0;
        onReset();
    }

    public GestureHandler setOnTouchEventListener(OnTouchEventListener<T> onTouchEventListener) {
        this.mListener = onTouchEventListener;
        return this;
    }

    public String toString() {
        View view = this.mView;
        return getClass().getSimpleName() + "@[" + this.mTag + "]:" + (view == null ? null : view.getClass().getSimpleName());
    }

    public float getLastAbsolutePositionX() {
        return this.mLastX;
    }

    public float getLastAbsolutePositionY() {
        return this.mLastY;
    }

    public float getLastRelativePositionX() {
        return this.mLastX - this.mLastEventOffsetX;
    }

    public float getLastRelativePositionY() {
        return this.mLastY - this.mLastEventOffsetY;
    }
}
