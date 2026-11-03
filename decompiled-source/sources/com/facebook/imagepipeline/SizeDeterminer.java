package com.facebook.imagepipeline;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes8.dex */
public class SizeDeterminer {
    private static final int PENDING_SIZE = 0;
    private static final int SIZE_ORIGINAL = Integer.MIN_VALUE;
    private static final String TAG = "SizeDeterminer";
    static Integer maxDisplayLength;
    private final List<SizeReadyCallback> cbs = new ArrayList();
    private SizeDeterminerLayoutListener layoutListener;
    boolean waitForLayout;
    private final WeakReference<View> weakRef;

    private boolean isDimensionValid(int i2) {
        return i2 > 0 || i2 == Integer.MIN_VALUE;
    }

    public SizeDeterminer(View view) {
        this.weakRef = new WeakReference<>(view);
    }

    private static int getMaxDisplayLength(Context context) {
        if (maxDisplayLength == null) {
            Display defaultDisplay = ((WindowManager) Preconditions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            maxDisplayLength = Integer.valueOf(Math.max(point.x, point.y));
        }
        return maxDisplayLength.intValue();
    }

    private void notifyCbs(int i2, int i3) {
        Iterator it = new ArrayList(this.cbs).iterator();
        while (it.hasNext()) {
            ((SizeReadyCallback) it.next()).onSizeReady(i2, i3);
        }
    }

    void checkCurrentDimens() {
        View view;
        if (this.cbs.isEmpty() || (view = getView()) == null) {
            return;
        }
        int targetWidth = getTargetWidth(view);
        int targetHeight = getTargetHeight(view);
        if (isViewStateAndSizeValid(targetWidth, targetHeight)) {
            notifyCbs(targetWidth, targetHeight);
            clearCallbacksAndListener();
        }
    }

    public View getView() {
        return this.weakRef.get();
    }

    public void getSize(SizeReadyCallback sizeReadyCallback) {
        View view = getView();
        if (view == null) {
            return;
        }
        int targetWidth = getTargetWidth(view);
        int targetHeight = getTargetHeight(view);
        if (isViewStateAndSizeValid(targetWidth, targetHeight)) {
            sizeReadyCallback.onSizeReady(targetWidth, targetHeight);
            return;
        }
        if (!this.cbs.contains(sizeReadyCallback)) {
            this.cbs.add(sizeReadyCallback);
        }
        if (this.layoutListener == null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            SizeDeterminerLayoutListener sizeDeterminerLayoutListener = new SizeDeterminerLayoutListener(this);
            this.layoutListener = sizeDeterminerLayoutListener;
            viewTreeObserver.addOnPreDrawListener(sizeDeterminerLayoutListener);
        }
    }

    public Pair<Integer, Integer> getSize() {
        View view = getView();
        if (view == null) {
            return null;
        }
        int targetWidth = getTargetWidth(view);
        int targetHeight = getTargetHeight(view);
        if (isViewStateAndSizeValid(targetWidth, targetHeight)) {
            return new Pair<>(Integer.valueOf(targetWidth), Integer.valueOf(targetHeight));
        }
        return null;
    }

    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
        this.cbs.remove(sizeReadyCallback);
    }

    public void clearCallbacksAndListener() {
        View view = getView();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.layoutListener);
            }
        }
        this.layoutListener = null;
        this.cbs.clear();
    }

    private boolean isViewStateAndSizeValid(int i2, int i3) {
        return isDimensionValid(i2) && isDimensionValid(i3);
    }

    private int getTargetHeight(View view) {
        int paddingTop = view.getPaddingTop() + view.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return getTargetDimen(view.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop, view);
    }

    private int getTargetWidth(View view) {
        int paddingLeft = view.getPaddingLeft() + view.getPaddingRight();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return getTargetDimen(view.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft, view);
    }

    private int getTargetDimen(int i2, int i3, int i4, View view) {
        int i5 = i3 - i4;
        if (i5 > 0) {
            return i5;
        }
        if (this.waitForLayout && view.isLayoutRequested()) {
            return 0;
        }
        int i6 = i2 - i4;
        if (i6 > 0) {
            return i6;
        }
        if (view.isLayoutRequested() || i3 != -2) {
            return 0;
        }
        if (Log.isLoggable(TAG, 4)) {
            Log.i(TAG, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
        }
        return getMaxDisplayLength(view.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static final class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {
        private final WeakReference<SizeDeterminer> sizeDeterminerRef;

        SizeDeterminerLayoutListener(SizeDeterminer sizeDeterminer) {
            this.sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (Log.isLoggable(SizeDeterminer.TAG, 2)) {
                Log.v(SizeDeterminer.TAG, "OnGlobalLayoutListener called attachStateListener=" + this);
            }
            SizeDeterminer sizeDeterminer = this.sizeDeterminerRef.get();
            if (sizeDeterminer != null) {
                sizeDeterminer.checkCurrentDimens();
                return true;
            }
            return true;
        }
    }
}
