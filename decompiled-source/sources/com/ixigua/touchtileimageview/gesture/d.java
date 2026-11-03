package com.ixigua.touchtileimageview.gesture;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.ixigua.touchtileimageview.gesture.c;
import java.lang.reflect.Field;
/* compiled from: ScaleGestureDetectorCompatBase.java */
/* loaded from: classes9.dex */
public class d extends c {

    /* renamed from: a  reason: collision with root package name */
    private ScaleGestureDetector f17157a;

    public d(Context context, final c.a aVar) {
        this.f17157a = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.ixigua.touchtileimageview.gesture.d.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                return aVar.onScale(d.this);
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return aVar.onScaleBegin(d.this);
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                aVar.onScaleEnd(d.this);
            }
        });
        if (Build.VERSION.SDK_INT >= 19) {
            this.f17157a.setQuickScaleEnabled(false);
        }
        try {
            Field declaredField = ScaleGestureDetector.class.getDeclaredField("mMinSpan");
            declaredField.setAccessible(true);
            declaredField.set(this.f17157a, 1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f17157a.onTouchEvent(motionEvent);
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public float getScaleFactor() {
        return this.f17157a.getScaleFactor();
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public float getFocusX() {
        return this.f17157a.getFocusX();
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public float getFocusY() {
        return this.f17157a.getFocusY();
    }
}
