package com.bytedance.common.jato.memory.LeakRepair;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
/* compiled from: LeakRepair2.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4833a = "ActivityLeakDetector";

    /* renamed from: b  reason: collision with root package name */
    public static boolean f4834b = false;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4835c = 1;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4836d = 2;

    /* renamed from: e  reason: collision with root package name */
    public static final int f4837e = 4;

    /* renamed from: f  reason: collision with root package name */
    private int f4838f = 0;

    public void a(int i2) {
        this.f4838f = i2;
    }

    public com.bytedance.common.jato.memory.LeakRepair.debug.b a() {
        return a.a().b();
    }

    public void a(f fVar) {
        Object obj = fVar.b().get();
        if (obj == null) {
            Log.e(f4833a, "LeakRepair Failed: origin object is null");
            return;
        }
        if (obj instanceof View) {
            View view = (View) obj;
            if ((this.f4838f & 1) == 1) {
                long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                int a2 = RenderNodeReclaimer.a().a(view, fVar.f());
                long elapsedRealtimeNanos2 = SystemClock.elapsedRealtimeNanos();
                if (f4834b) {
                    Log.d(f4833a, String.format("ReclaimActivity act:%s, view_count:%s usetime:%s", fVar.f(), Integer.valueOf(a2), Long.valueOf(elapsedRealtimeNanos2 - elapsedRealtimeNanos)));
                }
                a().a(fVar.f(), a2);
            }
            if ((this.f4838f & 4) == 4) {
                a(view);
            }
        }
        if (obj instanceof Fragment) {
            Fragment fragment = (Fragment) obj;
            if ((this.f4838f & 2) == 2) {
                e.a(fragment);
            }
        }
        fVar.g();
    }

    private static void a(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                a(viewGroup.getChildAt(i2));
            }
            if (view instanceof AdapterView) {
                return;
            }
            try {
                ((ViewGroup) view).removeAllViews();
            } catch (Throwable unused) {
            }
        }
    }
}
