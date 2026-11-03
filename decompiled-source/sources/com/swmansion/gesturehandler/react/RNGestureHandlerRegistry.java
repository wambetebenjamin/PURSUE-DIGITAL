package com.swmansion.gesturehandler.react;

import android.util.SparseArray;
import android.view.View;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerRegistry;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class RNGestureHandlerRegistry implements GestureHandlerRegistry {
    private final SparseArray<GestureHandler> mHandlers = new SparseArray<>();
    private final SparseArray<Integer> mAttachedTo = new SparseArray<>();
    private final SparseArray<ArrayList<GestureHandler>> mHandlersForView = new SparseArray<>();

    public synchronized void registerHandler(GestureHandler gestureHandler) {
        this.mHandlers.put(gestureHandler.getTag(), gestureHandler);
    }

    public synchronized GestureHandler getHandler(int i2) {
        return this.mHandlers.get(i2);
    }

    public synchronized boolean attachHandlerToView(int i2, int i3) {
        GestureHandler gestureHandler = this.mHandlers.get(i2);
        if (gestureHandler != null) {
            detachHandler(gestureHandler);
            registerHandlerForViewWithTag(i3, gestureHandler);
            return true;
        }
        return false;
    }

    private synchronized void registerHandlerForViewWithTag(int i2, GestureHandler gestureHandler) {
        if (this.mAttachedTo.get(gestureHandler.getTag()) != null) {
            throw new IllegalStateException("Handler " + gestureHandler + " already attached");
        }
        this.mAttachedTo.put(gestureHandler.getTag(), Integer.valueOf(i2));
        ArrayList<GestureHandler> arrayList = this.mHandlersForView.get(i2);
        if (arrayList == null) {
            ArrayList<GestureHandler> arrayList2 = new ArrayList<>(1);
            arrayList2.add(gestureHandler);
            this.mHandlersForView.put(i2, arrayList2);
        } else {
            arrayList.add(gestureHandler);
        }
    }

    private synchronized void detachHandler(GestureHandler gestureHandler) {
        Integer num = this.mAttachedTo.get(gestureHandler.getTag());
        if (num != null) {
            this.mAttachedTo.remove(gestureHandler.getTag());
            ArrayList<GestureHandler> arrayList = this.mHandlersForView.get(num.intValue());
            if (arrayList != null) {
                arrayList.remove(gestureHandler);
                if (arrayList.size() == 0) {
                    this.mHandlersForView.remove(num.intValue());
                }
            }
        }
        if (gestureHandler.getView() != null) {
            gestureHandler.cancel();
        }
    }

    public synchronized void dropHandler(int i2) {
        GestureHandler gestureHandler = this.mHandlers.get(i2);
        if (gestureHandler != null) {
            detachHandler(gestureHandler);
            this.mHandlers.remove(i2);
        }
    }

    public synchronized void dropAllHandlers() {
        this.mHandlers.clear();
        this.mAttachedTo.clear();
        this.mHandlersForView.clear();
    }

    public synchronized ArrayList<GestureHandler> getHandlersForViewWithTag(int i2) {
        return this.mHandlersForView.get(i2);
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerRegistry
    public synchronized ArrayList<GestureHandler> getHandlersForView(View view) {
        return getHandlersForViewWithTag(view.getId());
    }
}
