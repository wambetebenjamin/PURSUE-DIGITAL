package com.swmansion.gesturehandler;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/* loaded from: classes9.dex */
public class GestureHandlerOrchestrator {
    private static final float DEFAULT_MIN_ALPHA_FOR_TRAVERSAL = 0.0f;
    private static final int SIMULTANEOUS_GESTURE_HANDLER_LIMIT = 20;
    private final GestureHandlerRegistry mHandlerRegistry;
    private final ViewConfigurationHelper mViewConfigHelper;
    private final ViewGroup mWrapperView;
    private static final PointF sTempPoint = new PointF();
    private static final float[] sMatrixTransformCoords = new float[2];
    private static final Matrix sInverseMatrix = new Matrix();
    private static final float[] sTempCoords = new float[2];
    private static final Comparator<GestureHandler> sHandlersComparator = new Comparator<GestureHandler>() { // from class: com.swmansion.gesturehandler.GestureHandlerOrchestrator.1
        @Override // java.util.Comparator
        public int compare(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            if ((gestureHandler.mIsActive && gestureHandler2.mIsActive) || (gestureHandler.mIsAwaiting && gestureHandler2.mIsAwaiting)) {
                return Integer.signum(gestureHandler2.mActivationIndex - gestureHandler.mActivationIndex);
            }
            if (gestureHandler.mIsActive) {
                return -1;
            }
            if (gestureHandler2.mIsActive) {
                return 1;
            }
            if (gestureHandler.mIsAwaiting) {
                return -1;
            }
            return gestureHandler2.mIsAwaiting ? 1 : 0;
        }
    };
    private final GestureHandler[] mGestureHandlers = new GestureHandler[20];
    private final GestureHandler[] mAwaitingHandlers = new GestureHandler[20];
    private final GestureHandler[] mPreparedHandlers = new GestureHandler[20];
    private final GestureHandler[] mHandlersToCancel = new GestureHandler[20];
    private int mGestureHandlersCount = 0;
    private int mAwaitingHandlersCount = 0;
    private boolean mIsHandlingTouch = false;
    private int mHandlingChangeSemaphore = 0;
    private boolean mFinishedHandlersCleanupScheduled = false;
    private int mActivationIndex = 0;
    private float mMinAlphaForTraversal = 0.0f;

    private static boolean isFinished(int i2) {
        return i2 == 3 || i2 == 1 || i2 == 5;
    }

    public GestureHandlerOrchestrator(ViewGroup viewGroup, GestureHandlerRegistry gestureHandlerRegistry, ViewConfigurationHelper viewConfigurationHelper) {
        this.mWrapperView = viewGroup;
        this.mHandlerRegistry = gestureHandlerRegistry;
        this.mViewConfigHelper = viewConfigurationHelper;
    }

    public void setMinimumAlphaForTraversal(float f2) {
        this.mMinAlphaForTraversal = f2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mIsHandlingTouch = true;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            extractGestureHandlers(motionEvent);
        } else if (actionMasked == 3) {
            cancelAll();
        }
        deliverEventToGestureHandlers(motionEvent);
        this.mIsHandlingTouch = false;
        if (this.mFinishedHandlersCleanupScheduled && this.mHandlingChangeSemaphore == 0) {
            cleanupFinishedHandlers();
        }
        return true;
    }

    private void scheduleFinishedHandlersCleanup() {
        if (this.mIsHandlingTouch || this.mHandlingChangeSemaphore != 0) {
            this.mFinishedHandlersCleanupScheduled = true;
        } else {
            cleanupFinishedHandlers();
        }
    }

    private void cleanupFinishedHandlers() {
        boolean z = false;
        for (int i2 = this.mGestureHandlersCount - 1; i2 >= 0; i2--) {
            GestureHandler gestureHandler = this.mGestureHandlers[i2];
            if (isFinished(gestureHandler.getState()) && !gestureHandler.mIsAwaiting) {
                this.mGestureHandlers[i2] = null;
                gestureHandler.reset();
                gestureHandler.mIsActive = false;
                gestureHandler.mIsAwaiting = false;
                gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                z = true;
            }
        }
        if (z) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.mGestureHandlersCount; i4++) {
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                if (gestureHandlerArr[i4] != null) {
                    gestureHandlerArr[i3] = gestureHandlerArr[i4];
                    i3++;
                }
            }
            this.mGestureHandlersCount = i3;
        }
        this.mFinishedHandlersCleanupScheduled = false;
    }

    private boolean hasOtherHandlerToWaitFor(GestureHandler gestureHandler) {
        for (int i2 = 0; i2 < this.mGestureHandlersCount; i2++) {
            GestureHandler gestureHandler2 = this.mGestureHandlers[i2];
            if (!isFinished(gestureHandler2.getState()) && shouldHandlerWaitForOther(gestureHandler, gestureHandler2)) {
                return true;
            }
        }
        return false;
    }

    private void tryActivate(GestureHandler gestureHandler) {
        if (hasOtherHandlerToWaitFor(gestureHandler)) {
            addAwaitingHandler(gestureHandler);
            return;
        }
        makeActive(gestureHandler);
        gestureHandler.mIsAwaiting = false;
    }

    private void cleanupAwaitingHandlers() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.mAwaitingHandlersCount; i3++) {
            if (this.mAwaitingHandlers[i3].mIsAwaiting) {
                GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
                gestureHandlerArr[i2] = gestureHandlerArr[i3];
                i2++;
            }
        }
        this.mAwaitingHandlersCount = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onHandlerStateChange(GestureHandler gestureHandler, int i2, int i3) {
        this.mHandlingChangeSemaphore++;
        if (isFinished(i2)) {
            for (int i4 = 0; i4 < this.mAwaitingHandlersCount; i4++) {
                GestureHandler gestureHandler2 = this.mAwaitingHandlers[i4];
                if (shouldHandlerWaitForOther(gestureHandler2, gestureHandler)) {
                    if (i2 == 5) {
                        gestureHandler2.cancel();
                        gestureHandler2.mIsAwaiting = false;
                    } else {
                        tryActivate(gestureHandler2);
                    }
                }
            }
            cleanupAwaitingHandlers();
        }
        if (i2 == 4) {
            tryActivate(gestureHandler);
        } else if (i3 == 4 || i3 == 5) {
            if (gestureHandler.mIsActive) {
                gestureHandler.dispatchStateChange(i2, i3);
            }
        } else {
            gestureHandler.dispatchStateChange(i2, i3);
        }
        this.mHandlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    private void makeActive(GestureHandler gestureHandler) {
        int state = gestureHandler.getState();
        gestureHandler.mIsAwaiting = false;
        gestureHandler.mIsActive = true;
        int i2 = this.mActivationIndex;
        this.mActivationIndex = i2 + 1;
        gestureHandler.mActivationIndex = i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.mGestureHandlersCount; i4++) {
            GestureHandler gestureHandler2 = this.mGestureHandlers[i4];
            if (shouldHandlerBeCancelledBy(gestureHandler2, gestureHandler)) {
                this.mHandlersToCancel[i3] = gestureHandler2;
                i3++;
            }
        }
        for (int i5 = i3 - 1; i5 >= 0; i5--) {
            this.mHandlersToCancel[i5].cancel();
        }
        for (int i6 = this.mAwaitingHandlersCount - 1; i6 >= 0; i6--) {
            GestureHandler gestureHandler3 = this.mAwaitingHandlers[i6];
            if (shouldHandlerBeCancelledBy(gestureHandler3, gestureHandler)) {
                gestureHandler3.cancel();
                gestureHandler3.mIsAwaiting = false;
            }
        }
        cleanupAwaitingHandlers();
        gestureHandler.dispatchStateChange(4, 2);
        if (state != 4) {
            gestureHandler.dispatchStateChange(5, 4);
            if (state != 5) {
                gestureHandler.dispatchStateChange(0, 5);
            }
        }
    }

    public void deliverEventToGestureHandlers(MotionEvent motionEvent) {
        int i2 = this.mGestureHandlersCount;
        System.arraycopy(this.mGestureHandlers, 0, this.mPreparedHandlers, 0, i2);
        Arrays.sort(this.mPreparedHandlers, 0, i2, sHandlersComparator);
        for (int i3 = 0; i3 < i2; i3++) {
            deliverEventToGestureHandler(this.mPreparedHandlers[i3], motionEvent);
        }
    }

    private void cancelAll() {
        for (int i2 = this.mAwaitingHandlersCount - 1; i2 >= 0; i2--) {
            this.mAwaitingHandlers[i2].cancel();
        }
        int i3 = this.mGestureHandlersCount;
        for (int i4 = 0; i4 < i3; i4++) {
            this.mPreparedHandlers[i4] = this.mGestureHandlers[i4];
        }
        for (int i5 = i3 - 1; i5 >= 0; i5--) {
            this.mPreparedHandlers[i5].cancel();
        }
    }

    private void deliverEventToGestureHandler(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (!isViewAttachedUnderWrapper(gestureHandler.getView())) {
            gestureHandler.cancel();
        } else if (gestureHandler.wantEvents()) {
            int actionMasked = motionEvent.getActionMasked();
            if (gestureHandler.mIsAwaiting && actionMasked == 2) {
                return;
            }
            float[] fArr = sTempCoords;
            extractCoordsForView(gestureHandler.getView(), motionEvent, fArr);
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            motionEvent.setLocation(fArr[0], fArr[1]);
            gestureHandler.handle(motionEvent);
            if (gestureHandler.mIsActive) {
                gestureHandler.dispatchTouchEvent(motionEvent);
            }
            motionEvent.setLocation(x, y);
            if (actionMasked == 1 || actionMasked == 6) {
                gestureHandler.stopTrackingPointer(motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
    }

    private boolean isViewAttachedUnderWrapper(View view) {
        if (view == null) {
            return false;
        }
        if (view == this.mWrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.mWrapperView) {
            parent = parent.getParent();
        }
        return parent == this.mWrapperView;
    }

    private void extractCoordsForView(View view, MotionEvent motionEvent, float[] fArr) {
        if (view == this.mWrapperView) {
            fArr[0] = motionEvent.getX();
            fArr[1] = motionEvent.getY();
        } else if (view == null || !(view.getParent() instanceof ViewGroup)) {
            throw new IllegalArgumentException("Parent is null? View is no longer in the tree");
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            extractCoordsForView(viewGroup, motionEvent, fArr);
            PointF pointF = sTempPoint;
            transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, view, pointF);
            fArr[0] = pointF.x;
            fArr[1] = pointF.y;
        }
    }

    private void addAwaitingHandler(GestureHandler gestureHandler) {
        int i2 = 0;
        while (true) {
            int i3 = this.mAwaitingHandlersCount;
            if (i2 < i3) {
                if (this.mAwaitingHandlers[i2] == gestureHandler) {
                    return;
                }
                i2++;
            } else {
                GestureHandler[] gestureHandlerArr = this.mAwaitingHandlers;
                if (i3 >= gestureHandlerArr.length) {
                    throw new IllegalStateException("Too many recognizers");
                }
                this.mAwaitingHandlersCount = i3 + 1;
                gestureHandlerArr[i3] = gestureHandler;
                gestureHandler.mIsAwaiting = true;
                int i4 = this.mActivationIndex;
                this.mActivationIndex = i4 + 1;
                gestureHandler.mActivationIndex = i4;
                return;
            }
        }
    }

    private void recordHandlerIfNotPresent(GestureHandler gestureHandler, View view) {
        int i2 = 0;
        while (true) {
            int i3 = this.mGestureHandlersCount;
            if (i2 < i3) {
                if (this.mGestureHandlers[i2] == gestureHandler) {
                    return;
                }
                i2++;
            } else {
                GestureHandler[] gestureHandlerArr = this.mGestureHandlers;
                if (i3 >= gestureHandlerArr.length) {
                    throw new IllegalStateException("Too many recognizers");
                }
                this.mGestureHandlersCount = i3 + 1;
                gestureHandlerArr[i3] = gestureHandler;
                gestureHandler.mIsActive = false;
                gestureHandler.mIsAwaiting = false;
                gestureHandler.mActivationIndex = Integer.MAX_VALUE;
                gestureHandler.prepare(view, this);
                return;
            }
        }
    }

    private boolean recordViewHandlersForPointer(View view, float[] fArr, int i2) {
        ArrayList<GestureHandler> handlersForView = this.mHandlerRegistry.getHandlersForView(view);
        if (handlersForView != null) {
            int size = handlersForView.size();
            boolean z = false;
            for (int i3 = 0; i3 < size; i3++) {
                GestureHandler gestureHandler = handlersForView.get(i3);
                if (gestureHandler.isEnabled() && gestureHandler.isWithinBounds(view, fArr[0], fArr[1])) {
                    recordHandlerIfNotPresent(gestureHandler, view);
                    gestureHandler.startTrackingPointer(i2);
                    z = true;
                }
            }
            return z;
        }
        return false;
    }

    private void extractGestureHandlers(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float[] fArr = sTempCoords;
        fArr[0] = motionEvent.getX(actionIndex);
        fArr[1] = motionEvent.getY(actionIndex);
        traverseWithPointerEvents(this.mWrapperView, fArr, pointerId);
        extractGestureHandlers(this.mWrapperView, fArr, pointerId);
    }

    private boolean extractGestureHandlers(ViewGroup viewGroup, float[] fArr, int i2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childInDrawingOrderAtIndex = this.mViewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, childCount);
            if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                PointF pointF = sTempPoint;
                transformTouchPointToViewCoords(fArr[0], fArr[1], viewGroup, childInDrawingOrderAtIndex, pointF);
                float f2 = fArr[0];
                float f3 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                boolean traverseWithPointerEvents = (!isClipping(childInDrawingOrderAtIndex) || isTransformedTouchPointInView(fArr[0], fArr[1], childInDrawingOrderAtIndex)) ? traverseWithPointerEvents(childInDrawingOrderAtIndex, fArr, i2) : false;
                fArr[0] = f2;
                fArr[1] = f3;
                if (traverseWithPointerEvents) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] fArr) {
        return (!(view instanceof ViewGroup) || view.getBackground() != null) && isTransformedTouchPointInView(fArr[0], fArr[1], view);
    }

    private boolean traverseWithPointerEvents(View view, float[] fArr, int i2) {
        PointerEventsConfig pointerEventsConfigForView = this.mViewConfigHelper.getPointerEventsConfigForView(view);
        if (pointerEventsConfigForView == PointerEventsConfig.NONE) {
            return false;
        }
        if (pointerEventsConfigForView == PointerEventsConfig.BOX_ONLY) {
            return recordViewHandlersForPointer(view, fArr, i2) || shouldHandlerlessViewBecomeTouchTarget(view, fArr);
        } else if (pointerEventsConfigForView == PointerEventsConfig.BOX_NONE) {
            if (view instanceof ViewGroup) {
                return extractGestureHandlers((ViewGroup) view, fArr, i2);
            }
            return false;
        } else if (pointerEventsConfigForView == PointerEventsConfig.AUTO) {
            return recordViewHandlersForPointer(view, fArr, i2) || (view instanceof ViewGroup ? extractGestureHandlers((ViewGroup) view, fArr, i2) : false) || shouldHandlerlessViewBecomeTouchTarget(view, fArr);
        } else {
            throw new IllegalArgumentException("Unknown pointer event type: " + pointerEventsConfigForView.toString());
        }
    }

    private boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.mMinAlphaForTraversal;
    }

    private static void transformTouchPointToViewCoords(float f2, float f3, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f2 + viewGroup.getScrollX()) - view.getLeft();
        float scrollY = (f3 + viewGroup.getScrollY()) - view.getTop();
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            float[] fArr = sMatrixTransformCoords;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = sInverseMatrix;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            float f4 = fArr[0];
            scrollY = fArr[1];
            scrollX = f4;
        }
        pointF.set(scrollX, scrollY);
    }

    private boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.mViewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    private static boolean isTransformedTouchPointInView(float f2, float f3, View view) {
        return f2 >= 0.0f && f2 <= ((float) view.getWidth()) && f3 >= 0.0f && f3 < ((float) view.getHeight());
    }

    private static boolean shouldHandlerWaitForOther(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler != gestureHandler2 && (gestureHandler.shouldWaitForHandlerFailure(gestureHandler2) || gestureHandler2.shouldRequireToWaitForFailure(gestureHandler));
    }

    private static boolean canRunSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler == gestureHandler2 || gestureHandler.shouldRecognizeSimultaneously(gestureHandler2) || gestureHandler2.shouldRecognizeSimultaneously(gestureHandler);
    }

    private static boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if (gestureHandler.hasCommonPointers(gestureHandler2) && !canRunSimultaneously(gestureHandler, gestureHandler2)) {
            if (gestureHandler != gestureHandler2) {
                if (gestureHandler.mIsAwaiting || gestureHandler.getState() == 4) {
                    return gestureHandler.shouldBeCancelledBy(gestureHandler2);
                }
                return true;
            }
            return true;
        }
        return false;
    }
}
