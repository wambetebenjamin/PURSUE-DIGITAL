package com.bytedance.common.jato.memory.LeakRepair;

import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.common.jato.memory.LeakRepair.debug.b;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class RenderNodeReclaimer {

    /* renamed from: h  reason: collision with root package name */
    private static RenderNodeReclaimer f4803h;

    /* renamed from: a  reason: collision with root package name */
    private Field f4804a;

    /* renamed from: b  reason: collision with root package name */
    private Field f4805b;

    /* renamed from: c  reason: collision with root package name */
    private Constructor<RenderNode> f4806c;

    /* renamed from: d  reason: collision with root package name */
    private Method f4807d;

    /* renamed from: e  reason: collision with root package name */
    private Method f4808e;

    /* renamed from: f  reason: collision with root package name */
    private RenderNode f4809f;

    /* renamed from: g  reason: collision with root package name */
    private long f4810g;

    private static native boolean nativeInitRenderNodeReclaim(long j2, Method method, boolean z);

    static {
        System.loadLibrary("jato");
        f4803h = null;
    }

    public static synchronized RenderNodeReclaimer a() {
        RenderNodeReclaimer renderNodeReclaimer;
        synchronized (RenderNodeReclaimer.class) {
            if (f4803h == null) {
                f4803h = new RenderNodeReclaimer();
            }
            renderNodeReclaimer = f4803h;
        }
        return renderNodeReclaimer;
    }

    private RenderNodeReclaimer() {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        long j2 = -1;
        try {
            Field a2 = com.bytedance.common.jato.util.c.a((Class<?>) View.class, "mRenderNode");
            this.f4804a = a2;
            a2.setAccessible(true);
            Field a3 = com.bytedance.common.jato.util.c.a((Class<?>) RenderNode.class, "mNativeRenderNode");
            this.f4805b = a3;
            a3.setAccessible(true);
            Method a4 = com.bytedance.common.jato.util.c.a(RenderNode.class, "nIsValid", Long.TYPE);
            this.f4807d = a4;
            a4.setAccessible(true);
            Constructor<RenderNode> declaredConstructor = RenderNode.class.getDeclaredConstructor(Long.TYPE);
            this.f4806c = declaredConstructor;
            declaredConstructor.setAccessible(true);
            RenderNode renderNode = new RenderNode("ReplaceEmptyNode");
            this.f4809f = renderNode;
            j2 = ((Long) this.f4805b.get(renderNode)).longValue();
            Method a5 = com.bytedance.common.jato.util.c.a(RecordingCanvas.class, "nDrawRenderNode", Long.TYPE, Long.TYPE);
            this.f4808e = a5;
            a5.setAccessible(true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (j2 == 0 || !nativeInitRenderNodeReclaim(j2, this.f4808e, d.f4834b)) {
            return;
        }
        this.f4810g = j2;
    }

    private boolean b() {
        return this.f4810g != 0;
    }

    public int a(View view, String str) {
        int i2 = 0;
        if (!b() || view == null) {
            return 0;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int i3 = 0;
            while (i2 < viewGroup.getChildCount()) {
                i3 += a(viewGroup.getChildAt(i2), str);
                i2++;
            }
            i2 = i3;
        }
        return i2 + b(view, str);
    }

    private int b(View view, String str) {
        int i2 = 0;
        if (b() && view != null) {
            try {
                long longValue = ((Long) this.f4805b.get((RenderNode) this.f4804a.get(view))).longValue();
                boolean z = longValue != this.f4810g;
                if (z && a(view)) {
                    i2 = 1;
                }
                if (d.f4834b) {
                    b.a aVar = new b.a();
                    aVar.f4856b = view.getClass().getName();
                    aVar.f4862h = view.getId();
                    aVar.f4858d = view.getHeight();
                    aVar.f4859e = view.getWidth();
                    aVar.f4860f = view.getTop();
                    aVar.f4861g = view.getLeft();
                    aVar.f4857c = longValue;
                    aVar.f4855a = str;
                    a.a().b().a(aVar);
                    if (!z) {
                        Log.e(d.f4833a, "view has been reclaimed: " + aVar.f4856b);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return i2;
    }

    private boolean a(View view) {
        if (b() && view != null) {
            try {
                this.f4804a.set(view, this.f4806c.newInstance(Long.valueOf(this.f4810g)));
                return true;
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
                Log.e(d.f4833a, " replaceRenderNode error!! " + e2.getMessage());
            }
        }
        return false;
    }
}
