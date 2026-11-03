package com.facebook.react.views.modal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.core.os.BuildCompat;
import androidx.core.view.InputDeviceCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.common.ContextUtils;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    private String mAnimationType;
    private ReactDialog mDialog;
    private boolean mHardwareAccelerated;
    private DialogRootViewGroup mHostView;
    private OnRequestCloseListener mOnRequestCloseListener;
    private DialogInterface.OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mShowNavigation;
    private boolean mStatusBarTranslucent;
    private boolean mTransparent;

    /* loaded from: classes8.dex */
    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialogInterface);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
    }

    public ReactModalHostView(Context context) {
        super(context);
        this.mShowNavigation = false;
        ((ReactContext) context).addLifecycleEventListener(this);
        this.mHostView = new DialogRootViewGroup(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.mHostView.dispatchProvideStructure(viewStructure);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.addView(view, i2);
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        return this.mHostView.getChildCount();
    }

    @Override // android.view.ViewGroup
    public View getChildAt(int i2) {
        return this.mHostView.getChildAt(i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i2) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(getChildAt(i2));
    }

    public void onDropInstance() {
        ((ReactContext) getContext()).removeLifecycleEventListener(this);
        dismiss();
    }

    private void dismiss() {
        Activity activity;
        UiThreadUtil.assertOnUiThread();
        ReactDialog reactDialog = this.mDialog;
        if (reactDialog != null) {
            if (reactDialog.isShowing() && ((activity = (Activity) ContextUtils.findContextOfType(this.mDialog.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
            ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
        this.mOnRequestCloseListener = onRequestCloseListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTransparent(boolean z) {
        this.mTransparent = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStatusBarTranslucent(boolean z) {
        this.mStatusBarTranslucent = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAnimationType(String str) {
        this.mAnimationType = str;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHardwareAccelerated(boolean z) {
        this.mHardwareAccelerated = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setShowNavigation(boolean z) {
        if (this.mShowNavigation != z) {
            this.mShowNavigation = z;
            this.mPropertyRequiresNewDialog = true;
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        showOrUpdate();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        onDropInstance();
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    private Activity getCurrentActivity() {
        return ((ReactContext) getContext()).getCurrentActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showOrUpdate() {
        UiThreadUtil.assertOnUiThread();
        if (this.mDialog != null) {
            if (this.mPropertyRequiresNewDialog) {
                dismiss();
            } else {
                updateProperties();
                return;
            }
        }
        this.mPropertyRequiresNewDialog = false;
        int i2 = R.style.Theme_FullScreenDialog;
        if (this.mAnimationType.equals("fade")) {
            i2 = R.style.Theme_FullScreenDialogAnimatedFade;
        } else if (this.mAnimationType.equals("slide")) {
            i2 = R.style.Theme_FullScreenDialogAnimatedSlide;
        }
        Activity currentActivity = getCurrentActivity();
        Context context = currentActivity == null ? getContext() : currentActivity;
        ReactDialog reactDialog = new ReactDialog(context, i2);
        this.mDialog = reactDialog;
        reactDialog.getWindow().setFlags(8, 8);
        this.mDialog.setContentView(getContentView());
        updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        if (BuildCompat.isAtLeastT()) {
            this.mDialog.setOncancellLisner(this.mOnRequestCloseListener);
        }
        this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.facebook.react.views.modal.ReactModalHostView.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1) {
                    if (i3 == 4) {
                        Assertions.assertNotNull(ReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                        ReactModalHostView.this.mOnRequestCloseListener.onRequestClose(dialogInterface);
                        return true;
                    }
                    Activity currentActivity2 = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
                    if (currentActivity2 != null) {
                        return currentActivity2.onKeyUp(i3, keyEvent);
                    }
                    return false;
                }
                return false;
            }
        });
        this.mDialog.getWindow().setSoftInputMode(16);
        if (this.mHardwareAccelerated) {
            this.mDialog.getWindow().addFlags(16777216);
        }
        if (currentActivity == null || currentActivity.isFinishing()) {
            return;
        }
        this.mDialog.getWindow().addFlags(8);
        this.mDialog.show();
        if (context instanceof Activity) {
            this.mDialog.getWindow().getDecorView().setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
        }
        WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        attributes.height = -1;
        if (Build.VERSION.SDK_INT >= 28) {
            attributes.layoutInDisplayCutoutMode = 1;
        }
        this.mDialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        this.mDialog.getWindow().setAttributes(attributes);
        this.mDialog.getWindow().clearFlags(8);
    }

    public void updateState(StateWrapper stateWrapper, int i2, int i3) {
        this.mHostView.updateState(stateWrapper, i2, i3);
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.mHostView);
        if (this.mStatusBarTranslucent) {
            frameLayout.setSystemUiVisibility(1024);
        } else {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    private void updateProperties() {
        Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        getCurrentActivity();
        this.mDialog.getWindow().addFlags(1024);
        if (!this.mShowNavigation) {
            WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
            attributes.systemUiVisibility = InputDeviceCompat.SOURCE_TOUCHSCREEN;
            this.mDialog.getWindow().setAttributes(attributes);
            if (Build.VERSION.SDK_INT >= 19) {
                this.mDialog.getWindow().getDecorView().setSystemUiVisibility(5894);
            } else if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
                this.mDialog.getWindow().getDecorView().setSystemUiVisibility(8);
            }
        } else {
            this.mDialog.getWindow().getDecorView().setSystemUiVisibility(1792);
            if (Build.VERSION.SDK_INT >= 21) {
                this.mDialog.getWindow().setStatusBarColor(0);
                this.mDialog.getWindow().setNavigationBarColor(0);
            }
        }
        if (this.mTransparent) {
            this.mDialog.getWindow().clearFlags(2);
            return;
        }
        this.mDialog.getWindow().setDimAmount(0.5f);
        this.mDialog.getWindow().setFlags(2, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class DialogRootViewGroup extends ReactViewGroup implements RootView {
        private boolean hasAdjustedSize;
        private final JSTouchDispatcher mJSTouchDispatcher;
        private StateWrapper mStateWrapper;
        private int viewHeight;
        private int viewWidth;

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        public DialogRootViewGroup(Context context) {
            super(context);
            this.hasAdjustedSize = false;
            this.mJSTouchDispatcher = new JSTouchDispatcher(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public void onSizeChanged(int i2, int i3, int i4, int i5) {
            super.onSizeChanged(i2, i3, i4, i5);
            this.viewWidth = i2;
            this.viewHeight = i3;
            updateFirstChildView();
        }

        private void updateFirstChildView() {
            if (getChildCount() > 0) {
                this.hasAdjustedSize = false;
                final int id = getChildAt(0).getId();
                StateWrapper stateWrapper = this.mStateWrapper;
                if (stateWrapper != null) {
                    updateState(stateWrapper, this.viewWidth, this.viewHeight);
                    return;
                }
                ReactContext reactContext = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext) { // from class: com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup.1
                    @Override // com.facebook.react.bridge.GuardedRunnable
                    public void runGuarded() {
                        ((UIManagerModule) DialogRootViewGroup.this.getReactContext().getNativeModule(UIManagerModule.class)).updateNodeSize(id, DialogRootViewGroup.this.viewWidth, DialogRootViewGroup.this.viewHeight);
                    }
                });
                return;
            }
            this.hasAdjustedSize = true;
        }

        public void updateState(StateWrapper stateWrapper, int i2, int i3) {
            this.mStateWrapper = stateWrapper;
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("screenWidth", PixelUtil.toDIPFromPixel(i2));
            writableNativeMap.putDouble("screenHeight", PixelUtil.toDIPFromPixel(i3));
            stateWrapper.updateState(writableNativeMap);
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
            super.addView(view, i2, layoutParams);
            if (this.hasAdjustedSize) {
                updateFirstChildView();
            }
        }

        @Override // com.facebook.react.uimanager.RootView
        public void handleException(Throwable th) {
            getReactContext().handleException(new RuntimeException(th));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ReactContext getReactContext() {
            return (ReactContext) getContext();
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            EventDispatcher eventDispatcher = getEventDispatcher();
            if (eventDispatcher != null) {
                this.mJSTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher);
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            EventDispatcher eventDispatcher = getEventDispatcher();
            if (eventDispatcher != null) {
                this.mJSTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher);
            }
            super.onTouchEvent(motionEvent);
            return true;
        }

        @Override // com.facebook.react.uimanager.RootView
        public void onChildStartedNativeGesture(MotionEvent motionEvent) {
            EventDispatcher eventDispatcher = getEventDispatcher();
            if (eventDispatcher != null) {
                this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, eventDispatcher);
            }
        }

        private EventDispatcher getEventDispatcher() {
            ReactContext reactContext = getReactContext();
            if (reactContext == null || reactContext.getNativeModule(UIManagerModule.class) == null) {
                return null;
            }
            return ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }
    }
}
