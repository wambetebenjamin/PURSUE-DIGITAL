package com.bytedance.react.framework.window;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.bytedance.react.framework.core.BRNWindowManager;
import com.bytedance.react.framework.core.IBRNWindow;
import java.util.List;
/* loaded from: classes2.dex */
public class RNWindowCallback implements KeyEvent.Callback, Window.Callback {
    private Window.Callback callback;
    private String callbackId = "" + System.nanoTime();
    private KeyEvent.DispatcherState dispatcherState = new KeyEvent.DispatcherState();

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i2, int i3, KeyEvent keyEvent) {
        return false;
    }

    public RNWindowCallback(Window.Callback callback) {
        this.callback = callback;
    }

    private IBRNWindow getBRNWindow() {
        List<IBRNWindow> openWindowList = BRNWindowManager.getOpenWindowList();
        for (int size = openWindowList.size() - 1; size >= 0; size--) {
            IBRNWindow iBRNWindow = openWindowList.get(size);
            if (iBRNWindow.isShow() && this.callbackId.equals(iBRNWindow.getParentWindowCallbackID())) {
                return iBRNWindow;
            }
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        IBRNWindow bRNWindow = getBRNWindow();
        if (bRNWindow != null && keyEvent.getKeyCode() == 4 && keyEvent.dispatch(this, this.dispatcherState, bRNWindow)) {
            return true;
        }
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.dispatchKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.dispatchKeyShortcutEvent(keyEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.dispatchTrackballEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.dispatchGenericMotionEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i2) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onCreatePanelView(i2);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onCreatePanelMenu(i2, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onPreparePanel(i2, view, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onMenuOpened(i2, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onMenuItemSelected(i2, menuItem);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onWindowAttributesChanged(layoutParams);
        }
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onWindowFocusChanged(z);
        }
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onAttachedToWindow();
        }
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onDetachedFromWindow();
        }
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onPanelClosed(i2, menu);
        }
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onSearchRequested();
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            return callback.onSearchRequested(searchEvent);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Window.Callback callback2 = this.callback;
        if (callback2 != null) {
            return callback2.onWindowStartingActionMode(callback);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        Window.Callback callback2 = this.callback;
        if (callback2 != null) {
            return callback2.onWindowStartingActionMode(callback, i2);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onActionModeStarted(actionMode);
        }
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        Window.Callback callback = this.callback;
        if (callback != null) {
            callback.onActionModeFinished(actionMode);
        }
    }

    public String getWindowCallbackId() {
        return this.callbackId;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || getBRNWindow() == null) {
            return false;
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 4 && keyEvent.isTracking() && !keyEvent.isCanceled()) {
            IBRNWindow bRNWindow = getBRNWindow();
            if (bRNWindow != null) {
                bRNWindow.onBackPress();
                return true;
            }
            return true;
        }
        return false;
    }
}
