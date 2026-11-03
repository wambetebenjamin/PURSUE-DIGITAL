package com.bytedance.react.framework.core;

import android.view.Window;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.react.framework.floatingview.FloatingView;
import com.bytedance.react.framework.floatingview.MagnetViewListener;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class FloatingViewManager {
    private static volatile FloatingViewManager INSTANCE;
    private ConcurrentHashMap<String, FloatingView> map;

    public static FloatingViewManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FloatingViewManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FloatingViewManager();
                }
            }
        }
        return INSTANCE;
    }

    private FloatingViewManager() {
        if (canUseFloatingView()) {
            this.map = new ConcurrentHashMap<>();
        }
    }

    public void attach(String str, Window window) {
        if (!canUseFloatingView() || this.map.get(str) == null) {
            return;
        }
        this.map.get(str).attach(window);
    }

    public void detach(String str, Window window) {
        if (!canUseFloatingView() || this.map.get(str) == null) {
            return;
        }
        this.map.get(str).detach(window);
    }

    public void add(String str) {
        if (canUseFloatingView()) {
            if (this.map.get(str) == null) {
                this.map.put(str, new FloatingView());
            }
            this.map.get(str).add();
        }
    }

    public void remove(String str) {
        if (!canUseFloatingView() || this.map.get(str) == null) {
            return;
        }
        this.map.get(str).remove();
        this.map.remove(str);
    }

    public void setListener(String str, MagnetViewListener magnetViewListener) {
        if (!canUseFloatingView() || this.map.get(str) == null) {
            return;
        }
        this.map.get(str).listener(magnetViewListener);
    }

    public void initFloatingView(String str, Window window, MagnetViewListener magnetViewListener) {
        if (canUseFloatingView()) {
            add(str);
            setListener(str, magnetViewListener);
            attach(str, window);
        }
    }

    public void destroyFloatingView(String str, Window window) {
        if (canUseFloatingView()) {
            detach(str, window);
            setListener(str, null);
            remove(str);
        }
    }

    public void release() {
        ConcurrentHashMap<String, FloatingView> concurrentHashMap = this.map;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        this.map = null;
    }

    public static boolean canUseFloatingView() {
        return !RNConfig.USE_RELEASE_BUNDLE;
    }
}
