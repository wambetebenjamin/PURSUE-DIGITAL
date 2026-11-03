package com.oasis.floatingwindow;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class OasisFloatingWindowOnClickListener implements View.OnTouchListener {
    public static String Tag = "OasisFloatingWindowOnClickListener";
    WindowManager.LayoutParams layoutParams;
    WindowManager windowManager;
    private int x;
    private int y;

    public OasisFloatingWindowOnClickListener(WindowManager.LayoutParams layoutParams, WindowManager windowManager) {
        this.layoutParams = layoutParams;
        this.windowManager = windowManager;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.layoutParams == null || this.windowManager == null) {
            Logger.e(Tag, "onTouch (" + view + "): layoutParams or windowManager is null!!!!!!!!!!!!!!!!!!");
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.x = (int) motionEvent.getRawX();
            this.y = (int) motionEvent.getRawY();
        } else if (action == 2) {
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            int i2 = rawX - this.x;
            int i3 = rawY - this.y;
            this.x = rawX;
            this.y = rawY;
            this.layoutParams.x += i2;
            this.layoutParams.y += i3;
            this.windowManager.updateViewLayout(view, this.layoutParams);
        }
        return false;
    }
}
