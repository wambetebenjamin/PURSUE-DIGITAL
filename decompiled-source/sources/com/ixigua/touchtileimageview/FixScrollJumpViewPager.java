package com.ixigua.touchtileimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* loaded from: classes9.dex */
public class FixScrollJumpViewPager extends ViewGroup {
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    private com.ixigua.touchtileimageview.e mAdapter;
    private List<e> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private f mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<b> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private h mObserver;
    private int mOffscreenPageLimit;
    private f mOnPageChangeListener;
    private List<f> mOnPageChangeListeners;
    private int mPageMargin;
    private g mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final b mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private static final int[] LAYOUT_ATTRS = {16842931};
    private static final Comparator<b> COMPARATOR = new Comparator<b>() { // from class: com.ixigua.touchtileimageview.FixScrollJumpViewPager.1
        @Override // java.util.Comparator
        public int compare(b bVar, b bVar2) {
            return bVar.f16913b - bVar2.f16913b;
        }
    };
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.ixigua.touchtileimageview.FixScrollJumpViewPager.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    private static final k sPositionComparator = new k();

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes9.dex */
    public @interface a {
    }

    /* loaded from: classes9.dex */
    public interface e {
        void onAdapterChanged(FixScrollJumpViewPager fixScrollJumpViewPager, com.ixigua.touchtileimageview.e eVar, com.ixigua.touchtileimageview.e eVar2);
    }

    /* loaded from: classes9.dex */
    public interface f {
        void onPageScrollStateChanged(int i2);

        void onPageScrolled(int i2, float f2, int i3);

        void onPageSelected(int i2);
    }

    /* loaded from: classes9.dex */
    public interface g {
        void transformPage(View view, float f2);
    }

    /* loaded from: classes9.dex */
    public static class j implements f {
        @Override // com.ixigua.touchtileimageview.FixScrollJumpViewPager.f
        public void onPageScrollStateChanged(int i2) {
        }

        @Override // com.ixigua.touchtileimageview.FixScrollJumpViewPager.f
        public void onPageScrolled(int i2, float f2, int i3) {
        }

        @Override // com.ixigua.touchtileimageview.FixScrollJumpViewPager.f
        public void onPageSelected(int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        Object f16912a;

        /* renamed from: b  reason: collision with root package name */
        int f16913b;

        /* renamed from: c  reason: collision with root package name */
        boolean f16914c;

        /* renamed from: d  reason: collision with root package name */
        float f16915d;

        /* renamed from: e  reason: collision with root package name */
        float f16916e;

        b() {
        }
    }

    public FixScrollJumpViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new b();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: com.ixigua.touchtileimageview.FixScrollJumpViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                FixScrollJumpViewPager.this.setScrollState(0);
                FixScrollJumpViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }

    public FixScrollJumpViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList<>();
        this.mTempItem = new b();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() { // from class: com.ixigua.touchtileimageview.FixScrollJumpViewPager.3
            @Override // java.lang.Runnable
            public void run() {
                FixScrollJumpViewPager.this.setScrollState(0);
                FixScrollJumpViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }

    void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.mMinimumVelocity = (int) (400.0f * f2);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffectCompat(context);
        this.mRightEdge = new EdgeEffectCompat(context);
        this.mFlingDistance = (int) (25.0f * f2);
        this.mCloseEnough = (int) (2.0f * f2);
        this.mDefaultGutterSize = (int) (f2 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new d());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() { // from class: com.ixigua.touchtileimageview.FixScrollJumpViewPager.4

            /* renamed from: b  reason: collision with root package name */
            private final Rect f16911b = new Rect();

            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                if (onApplyWindowInsets.isConsumed()) {
                    return onApplyWindowInsets;
                }
                Rect rect = this.f16911b;
                rect.left = onApplyWindowInsets.getSystemWindowInsetLeft();
                rect.top = onApplyWindowInsets.getSystemWindowInsetTop();
                rect.right = onApplyWindowInsets.getSystemWindowInsetRight();
                rect.bottom = onApplyWindowInsets.getSystemWindowInsetBottom();
                int childCount = FixScrollJumpViewPager.this.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    WindowInsetsCompat dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(FixScrollJumpViewPager.this.getChildAt(i2), onApplyWindowInsets);
                    rect.left = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetLeft(), rect.left);
                    rect.top = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetTop(), rect.top);
                    rect.right = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetRight(), rect.right);
                    rect.bottom = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetBottom(), rect.bottom);
                }
                return onApplyWindowInsets.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        if (this.mPageTransformer != null) {
            enableLayers(i2 != 0);
        }
        dispatchOnScrollStateChanged(i2);
    }

    public void setAdapter(com.ixigua.touchtileimageview.e eVar) {
        com.ixigua.touchtileimageview.e eVar2 = this.mAdapter;
        if (eVar2 != null) {
            eVar2.a(null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                b bVar = this.mItems.get(i2);
                this.mAdapter.destroyItem((ViewGroup) this, bVar.f16913b, bVar.f16912a);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        com.ixigua.touchtileimageview.e eVar3 = this.mAdapter;
        this.mAdapter = eVar;
        this.mExpectedAdapterCount = 0;
        if (eVar != null) {
            if (this.mObserver == null) {
                this.mObserver = new h();
            }
            this.mAdapter.a(this.mObserver);
            this.mPopulatePending = false;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!z) {
                populate();
            } else {
                requestLayout();
            }
        }
        List<e> list = this.mAdapterChangeListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.mAdapterChangeListeners.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.mAdapterChangeListeners.get(i3).onAdapterChanged(this, eVar3, eVar);
        }
    }

    private void removeNonDecorViews() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((c) getChildAt(i2).getLayoutParams()).isDecor) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public com.ixigua.touchtileimageview.e getAdapter() {
        return this.mAdapter;
    }

    public void addOnAdapterChangeListener(e eVar) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(eVar);
    }

    public void removeOnAdapterChangeListener(e eVar) {
        List<e> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(eVar);
        }
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i2) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i2, !this.mFirstLayout, false);
    }

    public void setCurrentItem(int i2, boolean z) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i2, z, false);
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2) {
        setCurrentItemInternal(i2, z, z2, 0);
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2, int i3) {
        com.ixigua.touchtileimageview.e eVar = this.mAdapter;
        if (eVar == null || eVar.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (!z2 && this.mCurItem == i2 && this.mItems.size() != 0) {
            setScrollingCacheEnabled(false);
        } else {
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.mAdapter.getCount()) {
                i2 = this.mAdapter.getCount() - 1;
            }
            int i4 = this.mOffscreenPageLimit;
            int i5 = this.mCurItem;
            if (i2 > i5 + i4 || i2 < i5 - i4) {
                for (int i6 = 0; i6 < this.mItems.size(); i6++) {
                    this.mItems.get(i6).f16914c = true;
                }
            }
            boolean z3 = this.mCurItem != i2;
            if (this.mFirstLayout) {
                this.mCurItem = i2;
                if (z3) {
                    dispatchOnPageSelected(i2);
                }
                requestLayout();
                return;
            }
            populate(i2);
            scrollToItem(i2, z, i3, z3);
        }
    }

    private void scrollToItem(int i2, boolean z, int i3, boolean z2) {
        b infoForPosition = infoForPosition(i2);
        int clientWidth = infoForPosition != null ? (int) (getClientWidth() * Math.max(this.mFirstOffset, Math.min(infoForPosition.f16916e, this.mLastOffset))) : 0;
        if (z) {
            smoothScrollTo(clientWidth, 0, i3);
            if (z2) {
                dispatchOnPageSelected(i2);
                return;
            }
            return;
        }
        if (z2) {
            dispatchOnPageSelected(i2);
        }
        completeScroll(false);
        scrollTo(clientWidth, 0);
        pageScrolled(clientWidth);
    }

    @Deprecated
    public void setOnPageChangeListener(f fVar) {
        this.mOnPageChangeListener = fVar;
    }

    public void addOnPageChangeListener(f fVar) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(fVar);
    }

    public void removeOnPageChangeListener(f fVar) {
        List<f> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(fVar);
        }
    }

    public void clearOnPageChangeListeners() {
        List<f> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void setPageTransformer(boolean z, g gVar) {
        if (Build.VERSION.SDK_INT >= 11) {
            boolean z2 = gVar != null;
            boolean z3 = z2 != (this.mPageTransformer != null);
            this.mPageTransformer = gVar;
            setChildrenDrawingOrderEnabledCompat(z2);
            if (z2) {
                this.mDrawingOrder = z ? 2 : 1;
            } else {
                this.mDrawingOrder = 0;
            }
            if (z3) {
                populate();
            }
        }
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.mSetChildrenDrawingOrderEnabled == null) {
                try {
                    this.mSetChildrenDrawingOrderEnabled = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e2) {
                    Log.e(TAG, "Can't find setChildrenDrawingOrderEnabled", e2);
                }
            }
            try {
                this.mSetChildrenDrawingOrderEnabled.invoke(this, Boolean.valueOf(z));
            } catch (Exception e3) {
                Log.e(TAG, "Error changing children drawing order", e3);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.mDrawingOrder == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((c) this.mDrawingOrderedChildren.get(i3).getLayoutParams()).f16920d;
    }

    f setInternalPageChangeListener(f fVar) {
        f fVar2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = fVar;
        return fVar2;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w(TAG, "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i2;
            populate();
        }
    }

    public void setPageMargin(int i2) {
        int i3 = this.mPageMargin;
        this.mPageMargin = i2;
        int width = getWidth();
        recomputeScrollPosition(width, width, i2, i3);
        requestLayout();
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i2));
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    float distanceInfluenceForSnapDuration(float f2) {
        return (float) Math.sin((float) ((f2 - 0.5f) * 0.4712389167638204d));
    }

    void smoothScrollTo(int i2, int i3) {
        smoothScrollTo(i2, i3, 0);
    }

    void smoothScrollTo(int i2, int i3, int i4) {
        int scrollX;
        int abs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if ((scroller == null || scroller.isFinished()) ? false : true) {
            scrollX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        } else {
            scrollX = getScrollX();
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float distanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int abs2 = Math.abs(i4);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(distanceInfluenceForSnapDuration / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i6) / ((f2 * this.mAdapter.getPageWidth(this.mCurItem)) + this.mPageMargin)) + 1.0f) * 100.0f);
        }
        int min = Math.min(abs, 600);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(i5, scrollY, i6, i7, min);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    b addNewItem(int i2, int i3) {
        b bVar = new b();
        bVar.f16913b = i2;
        bVar.f16912a = this.mAdapter.instantiateItem((ViewGroup) this, i2);
        bVar.f16915d = this.mAdapter.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.mItems.size()) {
            this.mItems.add(bVar);
        } else {
            this.mItems.add(i3, bVar);
        }
        return bVar;
    }

    void dataSetChanged() {
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        boolean z = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < count;
        int i2 = this.mCurItem;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < this.mItems.size()) {
            b bVar = this.mItems.get(i3);
            int itemPosition = this.mAdapter.getItemPosition(bVar.f16912a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i3);
                    i3--;
                    if (!z2) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z2 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, bVar.f16913b, bVar.f16912a);
                    if (this.mCurItem == bVar.f16913b) {
                        i2 = Math.max(0, Math.min(this.mCurItem, count - 1));
                    }
                } else if (bVar.f16913b != itemPosition) {
                    if (bVar.f16913b == this.mCurItem) {
                        i2 = itemPosition;
                    }
                    bVar.f16913b = itemPosition;
                }
                z = true;
            }
            i3++;
        }
        if (z2) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                c cVar = (c) getChildAt(i4).getLayoutParams();
                if (!cVar.isDecor) {
                    cVar.f16917a = 0.0f;
                }
            }
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        }
    }

    void populate() {
        populate(this.mCurItem);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0064, code lost:
        if (r8.f16913b == r17.mCurItem) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
        r8 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void populate(int r18) {
        /*
            Method dump skipped, instructions count: 632
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ixigua.touchtileimageview.FixScrollJumpViewPager.populate(int):void");
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.mDrawingOrderedChildren.add(getChildAt(i2));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void calculatePageOffsets(b bVar, int i2, b bVar2) {
        b bVar3;
        b bVar4;
        int count = this.mAdapter.getCount();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        if (bVar2 != null) {
            int i3 = bVar2.f16913b;
            if (i3 < bVar.f16913b) {
                float f3 = bVar2.f16916e + bVar2.f16915d + f2;
                int i4 = i3 + 1;
                int i5 = 0;
                while (i4 <= bVar.f16913b && i5 < this.mItems.size()) {
                    b bVar5 = this.mItems.get(i5);
                    while (true) {
                        bVar4 = bVar5;
                        if (i4 <= bVar4.f16913b || i5 >= this.mItems.size() - 1) {
                            break;
                        }
                        i5++;
                        bVar5 = this.mItems.get(i5);
                    }
                    while (i4 < bVar4.f16913b) {
                        f3 += this.mAdapter.getPageWidth(i4) + f2;
                        i4++;
                    }
                    bVar4.f16916e = f3;
                    f3 += bVar4.f16915d + f2;
                    i4++;
                }
            } else if (i3 > bVar.f16913b) {
                int size = this.mItems.size() - 1;
                float f4 = bVar2.f16916e;
                while (true) {
                    i3--;
                    if (i3 < bVar.f16913b || size < 0) {
                        break;
                    }
                    b bVar6 = this.mItems.get(size);
                    while (true) {
                        bVar3 = bVar6;
                        if (i3 >= bVar3.f16913b || size <= 0) {
                            break;
                        }
                        size--;
                        bVar6 = this.mItems.get(size);
                    }
                    while (i3 > bVar3.f16913b) {
                        f4 -= this.mAdapter.getPageWidth(i3) + f2;
                        i3--;
                    }
                    f4 -= bVar3.f16915d + f2;
                    bVar3.f16916e = f4;
                }
            }
        }
        int size2 = this.mItems.size();
        float f5 = bVar.f16916e;
        int i6 = bVar.f16913b - 1;
        this.mFirstOffset = bVar.f16913b == 0 ? bVar.f16916e : -3.4028235E38f;
        int i7 = count - 1;
        this.mLastOffset = bVar.f16913b == i7 ? (bVar.f16916e + bVar.f16915d) - 1.0f : Float.MAX_VALUE;
        int i8 = i2 - 1;
        while (i8 >= 0) {
            b bVar7 = this.mItems.get(i8);
            while (i6 > bVar7.f16913b) {
                f5 -= this.mAdapter.getPageWidth(i6) + f2;
                i6--;
            }
            f5 -= bVar7.f16915d + f2;
            bVar7.f16916e = f5;
            if (bVar7.f16913b == 0) {
                this.mFirstOffset = f5;
            }
            i8--;
            i6--;
        }
        float f6 = bVar.f16916e + bVar.f16915d + f2;
        int i9 = bVar.f16913b + 1;
        int i10 = i2 + 1;
        while (i10 < size2) {
            b bVar8 = this.mItems.get(i10);
            while (i9 < bVar8.f16913b) {
                f6 += this.mAdapter.getPageWidth(i9) + f2;
                i9++;
            }
            if (bVar8.f16913b == i7) {
                this.mLastOffset = (bVar8.f16915d + f6) - 1.0f;
            }
            bVar8.f16916e = f6;
            f6 += bVar8.f16915d + f2;
            i10++;
            i9++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    /* loaded from: classes9.dex */
    public static class i extends AbsSavedState {
        public static final Parcelable.Creator<i> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<i>() { // from class: com.ixigua.touchtileimageview.FixScrollJumpViewPager.i.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.os.ParcelableCompatCreatorCallbacks
            public i createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new i(parcel, classLoader);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.core.os.ParcelableCompatCreatorCallbacks
            public i[] newArray(int i2) {
                return new i[i2];
            }
        });

        /* renamed from: a  reason: collision with root package name */
        int f16923a;

        /* renamed from: b  reason: collision with root package name */
        Parcelable f16924b;

        /* renamed from: c  reason: collision with root package name */
        ClassLoader f16925c;

        public i(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f16923a);
            parcel.writeParcelable(this.f16924b, i2);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f16923a + "}";
        }

        i(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f16923a = parcel.readInt();
            this.f16924b = parcel.readParcelable(classLoader);
            this.f16925c = classLoader;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        i iVar = new i(super.onSaveInstanceState());
        iVar.f16923a = this.mCurItem;
        com.ixigua.touchtileimageview.e eVar = this.mAdapter;
        if (eVar != null) {
            iVar.f16924b = eVar.saveState();
        }
        return iVar;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof i)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.getSuperState());
        com.ixigua.touchtileimageview.e eVar = this.mAdapter;
        if (eVar != null) {
            eVar.restoreState(iVar.f16924b, iVar.f16925c);
            setCurrentItemInternal(iVar.f16923a, false, true);
            return;
        }
        this.mRestoredCurItem = iVar.f16923a;
        this.mRestoredAdapterState = iVar.f16924b;
        this.mRestoredClassLoader = iVar.f16925c;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        c cVar = (c) layoutParams;
        cVar.isDecor |= isDecorView(view);
        if (this.mInLayout) {
            if (cVar != null && cVar.isDecor) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            cVar.f16918b = true;
            addViewInLayout(view, i2, layoutParams);
            return;
        }
        super.addView(view, i2, layoutParams);
    }

    private static boolean isDecorView(View view) {
        return view.getClass().getAnnotation(a.class) != null;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    b infoForChild(View view) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            b bVar = this.mItems.get(i2);
            if (this.mAdapter.isViewFromObject(view, bVar.f16912a)) {
                return bVar;
            }
        }
        return null;
    }

    b infoForAnyChild(View view) {
        while (true) {
            ViewParent parent = view.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
            } else {
                return infoForChild(view);
            }
        }
    }

    b infoForPosition(int i2) {
        for (int i3 = 0; i3 < this.mItems.size(); i3++) {
            b bVar = this.mItems.get(i3);
            if (bVar.f16913b == i2) {
                return bVar;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        c cVar;
        c cVar2;
        int i4;
        int i5;
        int i6;
        int i7;
        boolean z = false;
        setMeasuredDimension(getDefaultSize(0, i2), getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i8 = 0;
        while (true) {
            boolean z2 = true;
            int i9 = 1073741824;
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8 && (cVar2 = (c) childAt.getLayoutParams()) != null && cVar2.isDecor) {
                int i10 = cVar2.gravity & 7;
                int i11 = cVar2.gravity & 112;
                boolean z3 = (i11 == 48 || i11 == 80) ? true : z;
                if (i10 != 3 && i10 != 5) {
                    z2 = z;
                }
                int i12 = Integer.MIN_VALUE;
                if (z3) {
                    i4 = Integer.MIN_VALUE;
                    i12 = 1073741824;
                } else {
                    i4 = z2 ? 1073741824 : Integer.MIN_VALUE;
                }
                if (cVar2.width != -2) {
                    i6 = cVar2.width != -1 ? cVar2.width : paddingLeft;
                    i5 = 1073741824;
                } else {
                    i5 = i12;
                    i6 = paddingLeft;
                }
                if (cVar2.height != -2) {
                    i7 = cVar2.height != -1 ? cVar2.height : measuredHeight;
                } else {
                    i7 = measuredHeight;
                    i9 = i4;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i6, i5), View.MeasureSpec.makeMeasureSpec(i7, i9));
                if (z3) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z2) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i8++;
            z = false;
        }
        this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8 && ((cVar = (c) childAt2.getLayoutParams()) == null || !cVar.isDecor)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * cVar.f16917a), 1073741824), this.mChildHeightMeasureSpec);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.mPageMargin;
            recomputeScrollPosition(i2, i4, i6, i6);
        }
    }

    private void recomputeScrollPosition(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.mItems.isEmpty()) {
            if (!this.mScroller.isFinished()) {
                this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        b infoForPosition = infoForPosition(this.mCurItem);
        int min = (int) ((infoForPosition != null ? Math.min(infoForPosition.f16916e, this.mLastOffset) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (min != getScrollX()) {
            completeScroll(false);
            scrollTo(min, getScrollY());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ixigua.touchtileimageview.FixScrollJumpViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
                if (!pageScrolled(currX)) {
                    this.mScroller.abortAnimation();
                    scrollTo(0, currY);
                }
            }
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        completeScroll(true);
    }

    private boolean pageScrolled(int i2) {
        if (this.mItems.size() == 0) {
            if (this.mFirstLayout) {
                return false;
            }
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        b infoForCurrentScrollPosition = infoForCurrentScrollPosition();
        int clientWidth = getClientWidth();
        int i3 = this.mPageMargin;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = infoForCurrentScrollPosition.f16913b;
        float f3 = ((i2 / f2) - infoForCurrentScrollPosition.f16916e) / (infoForCurrentScrollPosition.f16915d + (i3 / f2));
        this.mCalledSuper = false;
        onPageScrolled(i5, f3, (int) (i4 * f3));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.mDecorChildCount
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6b
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r1
        L1b:
            if (r7 >= r6) goto L6b
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            com.ixigua.touchtileimageview.FixScrollJumpViewPager$c r9 = (com.ixigua.touchtileimageview.FixScrollJumpViewPager.c) r9
            boolean r10 = r9.isDecor
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.gravity
            r9 = r9 & 7
            if (r9 == r2) goto L4d
            r10 = 3
            if (r9 == r10) goto L47
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5c
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
            goto L59
        L47:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5c
        L4d:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
        L59:
            r11 = r9
            r9 = r3
            r3 = r11
        L5c:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L67
            r8.offsetLeftAndRight(r3)
        L67:
            r3 = r9
        L68:
            int r7 = r7 + 1
            goto L1b
        L6b:
            r12.dispatchOnPageScrolled(r13, r14, r15)
            com.ixigua.touchtileimageview.FixScrollJumpViewPager$g r13 = r12.mPageTransformer
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r1 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            com.ixigua.touchtileimageview.FixScrollJumpViewPager$c r0 = (com.ixigua.touchtileimageview.FixScrollJumpViewPager.c) r0
            boolean r0 = r0.isDecor
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            com.ixigua.touchtileimageview.FixScrollJumpViewPager$g r3 = r12.mPageTransformer
            r3.transformPage(r15, r0)
        L9c:
            int r1 = r1 + 1
            goto L7a
        L9f:
            r12.mCalledSuper = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ixigua.touchtileimageview.FixScrollJumpViewPager.onPageScrolled(int, float, int):void");
    }

    private void dispatchOnPageScrolled(int i2, float f2, int i3) {
        f fVar = this.mOnPageChangeListener;
        if (fVar != null) {
            fVar.onPageScrolled(i2, f2, i3);
        }
        List<f> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                f fVar2 = this.mOnPageChangeListeners.get(i4);
                if (fVar2 != null) {
                    fVar2.onPageScrolled(i2, f2, i3);
                }
            }
        }
        f fVar3 = this.mInternalPageChangeListener;
        if (fVar3 != null) {
            fVar3.onPageScrolled(i2, f2, i3);
        }
    }

    private void dispatchOnPageSelected(int i2) {
        f fVar = this.mOnPageChangeListener;
        if (fVar != null) {
            fVar.onPageSelected(i2);
        }
        List<f> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                f fVar2 = this.mOnPageChangeListeners.get(i3);
                if (fVar2 != null) {
                    fVar2.onPageSelected(i2);
                }
            }
        }
        f fVar3 = this.mInternalPageChangeListener;
        if (fVar3 != null) {
            fVar3.onPageSelected(i2);
        }
    }

    private void dispatchOnScrollStateChanged(int i2) {
        f fVar = this.mOnPageChangeListener;
        if (fVar != null) {
            fVar.onPageScrollStateChanged(i2);
        }
        List<f> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                f fVar2 = this.mOnPageChangeListeners.get(i3);
                if (fVar2 != null) {
                    fVar2.onPageScrollStateChanged(i2);
                }
            }
        }
        f fVar3 = this.mInternalPageChangeListener;
        if (fVar3 != null) {
            fVar3.onPageScrollStateChanged(i2);
        }
    }

    private void completeScroll(boolean z) {
        boolean z2 = this.mScrollState == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        pageScrolled(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            b bVar = this.mItems.get(i2);
            if (bVar.f16914c) {
                bVar.f16914c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
            } else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    private boolean isGutterDrag(float f2, float f3) {
        return (f2 < ((float) this.mGutterSize) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.mGutterSize)) && f3 < 0.0f);
    }

    private void enableLayers(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewCompat.setLayerType(getChildAt(i2), z ? 2 : 0, null);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            resetTouch();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            } else {
                completeScroll(false);
                this.mIsBeingDragged = false;
            }
        } else if (action == 2) {
            int i2 = this.mActivePointerId;
            if (i2 != -1) {
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i2);
                float x2 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                float f2 = x2 - this.mLastMotionX;
                float abs = Math.abs(f2);
                float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                float abs2 = Math.abs(y2 - this.mInitialMotionY);
                if (f2 != 0.0f && !isGutterDrag(this.mLastMotionX, f2) && canScroll(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.mLastMotionX = x2;
                    this.mLastMotionY = y2;
                    this.mIsUnableToDrag = true;
                    return false;
                }
                int i3 = this.mTouchSlop;
                if (abs > i3 && abs * 0.5f > abs2) {
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    this.mLastMotionX = x2;
                    this.mLastMotionY = y2;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > i3) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && performDrag(x2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        com.ixigua.touchtileimageview.e eVar;
        if (this.mFakeDragging) {
            return true;
        }
        boolean z = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (eVar = this.mAdapter) == null || eVar.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.mScroller.abortAnimation();
            this.mPopulatePending = false;
            populate();
            float x = motionEvent.getX();
            this.mInitialMotionX = x;
            this.mLastMotionX = x;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
        } else if (action != 1) {
            if (action == 2) {
                if (!this.mIsBeingDragged) {
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                    if (findPointerIndex == -1) {
                        z = resetTouch();
                    } else {
                        float x2 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                        float abs = Math.abs(x2 - this.mLastMotionX);
                        float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                        float abs2 = Math.abs(y2 - this.mLastMotionY);
                        if (abs > this.mTouchSlop && abs > abs2) {
                            this.mIsBeingDragged = true;
                            requestParentDisallowInterceptTouchEvent(true);
                            float f2 = this.mInitialMotionX;
                            this.mLastMotionX = x2 - f2 > 0.0f ? f2 + this.mTouchSlop : f2 - this.mTouchSlop;
                            this.mLastMotionY = y2;
                            setScrollState(1);
                            setScrollingCacheEnabled(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    z = false | performDrag(MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)));
                }
            } else if (action != 3) {
                if (action == 5) {
                    int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                    this.mLastMotionX = MotionEventCompat.getX(motionEvent, actionIndex);
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                } else if (action == 6) {
                    onSecondaryPointerUp(motionEvent);
                    this.mLastMotionX = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId));
                }
            } else if (this.mIsBeingDragged) {
                scrollToItem(this.mCurItem, true, 0, false);
                z = resetTouch();
            }
        } else if (this.mIsBeingDragged) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            b infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            float f3 = clientWidth;
            setCurrentItemInternal(determineTargetPage(infoForCurrentScrollPosition.f16913b, ((scrollX / f3) - infoForCurrentScrollPosition.f16916e) / (infoForCurrentScrollPosition.f16915d + (this.mPageMargin / f3)), xVelocity, (int) (MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId)) - this.mInitialMotionX)), true, true, xVelocity);
            z = resetTouch();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        return this.mLeftEdge.onRelease() | this.mRightEdge.onRelease();
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean performDrag(float f2) {
        boolean z;
        float f3 = this.mLastMotionX - f2;
        this.mLastMotionX = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.mFirstOffset * clientWidth;
        float f5 = this.mLastOffset * clientWidth;
        b bVar = this.mItems.get(0);
        ArrayList<b> arrayList = this.mItems;
        boolean z2 = true;
        b bVar2 = arrayList.get(arrayList.size() - 1);
        if (bVar.f16913b != 0) {
            f4 = bVar.f16916e * clientWidth;
            z = false;
        } else {
            z = true;
        }
        if (bVar2.f16913b != this.mAdapter.getCount() - 1) {
            f5 = bVar2.f16916e * clientWidth;
            z2 = false;
        }
        if (scrollX < f4) {
            r4 = z ? this.mLeftEdge.onPull(Math.abs(f4 - scrollX) / clientWidth) : false;
            scrollX = f4;
        } else if (scrollX > f5) {
            r4 = z2 ? this.mRightEdge.onPull(Math.abs(scrollX - f5) / clientWidth) : false;
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        pageScrolled(i2);
        return r4;
    }

    private b infoForCurrentScrollPosition() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        b bVar = null;
        int i3 = 0;
        int i4 = -1;
        boolean z = true;
        float f4 = 0.0f;
        while (i3 < this.mItems.size()) {
            b bVar2 = this.mItems.get(i3);
            if (!z && bVar2.f16913b != (i2 = i4 + 1)) {
                bVar2 = this.mTempItem;
                bVar2.f16916e = f2 + f4 + f3;
                bVar2.f16913b = i2;
                bVar2.f16915d = this.mAdapter.getPageWidth(bVar2.f16913b);
                i3--;
            }
            f2 = bVar2.f16916e;
            float f5 = bVar2.f16915d + f2 + f3;
            if (!z && scrollX < f2) {
                return bVar;
            }
            if (scrollX < f5 || i3 == this.mItems.size() - 1) {
                return bVar2;
            }
            i4 = bVar2.f16913b;
            f4 = bVar2.f16915d;
            i3++;
            z = false;
            bVar = bVar2;
        }
        return bVar;
    }

    private int determineTargetPage(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.mFlingDistance || Math.abs(i3) <= this.mMinimumVelocity) {
            i2 += (int) (f2 + (i2 >= this.mCurItem ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.mItems.size() > 0) {
            ArrayList<b> arrayList = this.mItems;
            return Math.max(this.mItems.get(0).f16913b, Math.min(i2, arrayList.get(arrayList.size() - 1).f16913b));
        }
        return i2;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        com.ixigua.touchtileimageview.e eVar;
        super.draw(canvas);
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (eVar = this.mAdapter) != null && eVar.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.mFirstOffset * width);
                this.mLeftEdge.setSize(height, width);
                z = false | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.mRightEdge.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * width2);
                this.mRightEdge.setSize(height2, width2);
                z |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        float f2;
        float f3;
        float f4;
        super.onDraw(canvas);
        if (this.mPageMargin <= 0 || this.mMarginDrawable == null || this.mItems.size() <= 0 || this.mAdapter == null) {
            return;
        }
        int scrollX = getScrollX();
        float width2 = getWidth();
        float f5 = this.mPageMargin / width2;
        int i2 = 0;
        b bVar = this.mItems.get(0);
        float f6 = bVar.f16916e;
        int size = this.mItems.size();
        int i3 = bVar.f16913b;
        int i4 = this.mItems.get(size - 1).f16913b;
        while (i3 < i4) {
            while (i3 > bVar.f16913b && i2 < size) {
                i2++;
                bVar = this.mItems.get(i2);
            }
            if (i3 == bVar.f16913b) {
                f3 = (bVar.f16916e + bVar.f16915d) * width2;
                f2 = bVar.f16916e + bVar.f16915d + f5;
            } else {
                float pageWidth = this.mAdapter.getPageWidth(i3);
                f2 = f6 + pageWidth + f5;
                f3 = (f6 + pageWidth) * width2;
            }
            if (this.mPageMargin + f3 > scrollX) {
                f4 = f5;
                this.mMarginDrawable.setBounds(Math.round(f3), this.mTopPageBounds, Math.round(this.mPageMargin + f3), this.mBottomPageBounds);
                this.mMarginDrawable.draw(canvas);
            } else {
                f4 = f5;
            }
            if (f3 > scrollX + width) {
                return;
            }
            i3++;
            f6 = f2;
            f5 = f4;
        }
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
        this.mFakeDragBeginTime = uptimeMillis;
        return true;
    }

    public void endFakeDrag() {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter != null) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int xVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            b infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            setCurrentItemInternal(determineTargetPage(infoForCurrentScrollPosition.f16913b, ((scrollX / clientWidth) - infoForCurrentScrollPosition.f16916e) / infoForCurrentScrollPosition.f16915d, xVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, xVelocity);
        }
        endDrag();
        this.mFakeDragging = false;
    }

    public void fakeDragBy(float f2) {
        ArrayList<b> arrayList;
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter == null) {
            return;
        }
        this.mLastMotionX += f2;
        float scrollX = getScrollX() - f2;
        float clientWidth = getClientWidth();
        float f3 = this.mFirstOffset * clientWidth;
        float f4 = this.mLastOffset * clientWidth;
        b bVar = this.mItems.get(0);
        b bVar2 = this.mItems.get(arrayList.size() - 1);
        if (bVar.f16913b != 0) {
            f3 = bVar.f16916e * clientWidth;
        }
        if (bVar2.f16913b != this.mAdapter.getCount() - 1) {
            f4 = bVar2.f16916e * clientWidth;
        }
        if (scrollX < f3) {
            scrollX = f3;
        } else if (scrollX > f4) {
            scrollX = f4;
        }
        int i2 = (int) scrollX;
        this.mLastMotionX += scrollX - i2;
        scrollTo(i2, getScrollY());
        pageScrolled(i2);
        MotionEvent obtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
        this.mVelocityTracker.addMovement(obtain);
        obtain.recycle();
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = MotionEventCompat.getX(motionEvent, i2);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.mFirstOffset)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.mLastOffset));
    }

    protected boolean canScroll(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && canScroll(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && ViewCompat.canScrollHorizontally(view, -i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return arrowScroll(17);
            }
            if (keyCode == 22) {
                return arrowScroll(66);
            }
            if (keyCode == 61) {
                int i2 = Build.VERSION.SDK_INT;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean arrowScroll(int r8) {
        /*
            r7 = this;
            android.view.View r0 = r7.findFocus()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != r7) goto Lb
        L9:
            r0 = r3
            goto L6c
        Lb:
            if (r0 == 0) goto L6c
            android.view.ViewParent r4 = r0.getParent()
        L11:
            boolean r5 = r4 instanceof android.view.ViewGroup
            if (r5 == 0) goto L1e
            if (r4 != r7) goto L19
            r4 = r1
            goto L1f
        L19:
            android.view.ViewParent r4 = r4.getParent()
            goto L11
        L1e:
            r4 = r2
        L1f:
            if (r4 != 0) goto L6c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
        L35:
            boolean r5 = r0 instanceof android.view.ViewGroup
            if (r5 == 0) goto L4f
            java.lang.String r5 = " => "
            java.lang.StringBuilder r5 = r4.append(r5)
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = r6.getSimpleName()
            r5.append(r6)
            android.view.ViewParent r0 = r0.getParent()
            goto L35
        L4f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "arrowScroll tried to find focus based on non-child current focused view "
            java.lang.StringBuilder r0 = r0.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = "ViewPager"
            android.util.Log.e(r4, r0)
            goto L9
        L6c:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            android.view.View r3 = r3.findNextFocus(r7, r0, r8)
            r4 = 66
            r5 = 17
            if (r3 == 0) goto Lbd
            if (r3 == r0) goto Lbd
            if (r8 != r5) goto L9d
            android.graphics.Rect r1 = r7.mTempRect
            android.graphics.Rect r1 = r7.getChildRectInPagerCoordinates(r1, r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r7.mTempRect
            android.graphics.Rect r2 = r7.getChildRectInPagerCoordinates(r2, r0)
            int r2 = r2.left
            if (r0 == 0) goto L97
            if (r1 < r2) goto L97
            boolean r0 = r7.pageLeft()
            goto L9b
        L97:
            boolean r0 = r3.requestFocus()
        L9b:
            r2 = r0
            goto Ld0
        L9d:
            if (r8 != r4) goto Ld0
            android.graphics.Rect r1 = r7.mTempRect
            android.graphics.Rect r1 = r7.getChildRectInPagerCoordinates(r1, r3)
            int r1 = r1.left
            android.graphics.Rect r2 = r7.mTempRect
            android.graphics.Rect r2 = r7.getChildRectInPagerCoordinates(r2, r0)
            int r2 = r2.left
            if (r0 == 0) goto Lb8
            if (r1 > r2) goto Lb8
            boolean r0 = r7.pageRight()
            goto L9b
        Lb8:
            boolean r0 = r3.requestFocus()
            goto L9b
        Lbd:
            if (r8 == r5) goto Lcc
            if (r8 != r1) goto Lc2
            goto Lcc
        Lc2:
            if (r8 == r4) goto Lc7
            r0 = 2
            if (r8 != r0) goto Ld0
        Lc7:
            boolean r2 = r7.pageRight()
            goto Ld0
        Lcc:
            boolean r2 = r7.pageLeft()
        Ld0:
            if (r2 == 0) goto Ld9
            int r8 = android.view.SoundEffectConstants.getContantForFocusDirection(r8)
            r7.playSoundEffect(r8)
        Ld9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ixigua.touchtileimageview.FixScrollJumpViewPager.arrowScroll(int):boolean");
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    boolean pageLeft() {
        int i2 = this.mCurItem;
        if (i2 > 0) {
            setCurrentItem(i2 - 1, true);
            return true;
        }
        return false;
    }

    boolean pageRight() {
        com.ixigua.touchtileimageview.e eVar = this.mAdapter;
        if (eVar == null || this.mCurItem >= eVar.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        b infoForChild;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.f16913b == this.mCurItem) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        b infoForChild;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.f16913b == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        b infoForChild;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i3 = 0;
            i4 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
        }
        while (i3 != i5) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.f16913b == this.mCurItem && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i4;
        }
        return false;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        b infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.f16913b == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof c) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class d extends AccessibilityDelegateCompat {
        d() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(FixScrollJumpViewPager.class.getName());
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            asRecord.setScrollable(a());
            if (accessibilityEvent.getEventType() != 4096 || FixScrollJumpViewPager.this.mAdapter == null) {
                return;
            }
            asRecord.setItemCount(FixScrollJumpViewPager.this.mAdapter.getCount());
            asRecord.setFromIndex(FixScrollJumpViewPager.this.mCurItem);
            asRecord.setToIndex(FixScrollJumpViewPager.this.mCurItem);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(FixScrollJumpViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(a());
            if (FixScrollJumpViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (FixScrollJumpViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            if (super.performAccessibilityAction(view, i2, bundle)) {
                return true;
            }
            if (i2 == 4096) {
                if (FixScrollJumpViewPager.this.canScrollHorizontally(1)) {
                    FixScrollJumpViewPager fixScrollJumpViewPager = FixScrollJumpViewPager.this;
                    fixScrollJumpViewPager.setCurrentItem(fixScrollJumpViewPager.mCurItem + 1);
                    return true;
                }
                return false;
            } else if (i2 == 8192 && FixScrollJumpViewPager.this.canScrollHorizontally(-1)) {
                FixScrollJumpViewPager fixScrollJumpViewPager2 = FixScrollJumpViewPager.this;
                fixScrollJumpViewPager2.setCurrentItem(fixScrollJumpViewPager2.mCurItem - 1);
                return true;
            } else {
                return false;
            }
        }

        private boolean a() {
            return FixScrollJumpViewPager.this.mAdapter != null && FixScrollJumpViewPager.this.mAdapter.getCount() > 1;
        }
    }

    /* loaded from: classes9.dex */
    private class h extends DataSetObserver {
        private h() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            FixScrollJumpViewPager.this.dataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            FixScrollJumpViewPager.this.dataSetChanged();
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends ViewGroup.LayoutParams {

        /* renamed from: a  reason: collision with root package name */
        float f16917a;

        /* renamed from: b  reason: collision with root package name */
        boolean f16918b;

        /* renamed from: c  reason: collision with root package name */
        int f16919c;

        /* renamed from: d  reason: collision with root package name */
        int f16920d;
        public int gravity;
        public boolean isDecor;

        public c() {
            super(-1, -1);
            this.f16917a = 0.0f;
        }

        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f16917a = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FixScrollJumpViewPager.LAYOUT_ATTRS);
            this.gravity = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class k implements Comparator<View> {
        k() {
        }

        @Override // java.util.Comparator
        public int compare(View view, View view2) {
            c cVar = (c) view.getLayoutParams();
            c cVar2 = (c) view2.getLayoutParams();
            if (cVar.isDecor != cVar2.isDecor) {
                return cVar.isDecor ? 1 : -1;
            }
            return cVar.f16919c - cVar2.f16919c;
        }
    }
}
