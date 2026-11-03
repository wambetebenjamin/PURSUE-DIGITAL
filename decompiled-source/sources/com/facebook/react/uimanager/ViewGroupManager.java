package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.List;
import java.util.WeakHashMap;
/* loaded from: classes8.dex */
public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, LayoutShadowNode> implements IViewManagerWithChildren {
    private static WeakHashMap<View, Integer> mZIndexHash = new WeakHashMap<>();

    @Override // com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public boolean shouldPromoteGrandchildren() {
        return false;
    }

    public void updateExtraData(T t, Object obj) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewManager
    public /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        updateExtraData((ViewGroupManager<T>) ((ViewGroup) view), obj);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    public void addView(T t, View view, int i2) {
        if (t == null || !(t instanceof ScrollView) || t.getChildCount() < 1) {
            t.addView(view, i2);
        }
    }

    public void addViews(T t, List<View> list) {
        UiThreadUtil.assertOnUiThread();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            addView(t, list.get(i2), i2);
        }
    }

    public static void setViewZIndex(View view, int i2) {
        mZIndexHash.put(view, Integer.valueOf(i2));
    }

    public static Integer getViewZIndex(View view) {
        return mZIndexHash.get(view);
    }

    public int getChildCount(T t) {
        return t.getChildCount();
    }

    public View getChildAt(T t, int i2) {
        return t.getChildAt(i2);
    }

    public void removeViewAt(T t, int i2) {
        UiThreadUtil.assertOnUiThread();
        t.removeViewAt(i2);
    }

    public void removeView(T t, View view) {
        UiThreadUtil.assertOnUiThread();
        for (int i2 = 0; i2 < getChildCount(t); i2++) {
            if (getChildAt(t, i2) == view) {
                removeViewAt(t, i2);
                return;
            }
        }
    }

    public void removeAllViews(T t) {
        UiThreadUtil.assertOnUiThread();
        for (int childCount = getChildCount(t) - 1; childCount >= 0; childCount--) {
            removeViewAt(t, childCount);
        }
    }
}
