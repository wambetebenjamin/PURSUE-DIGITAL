package com.ixigua.touchtileimageview.gesture;

import android.content.Context;
import android.view.MotionEvent;
import com.ixigua.touchtileimageview.gesture.b;
import com.ixigua.touchtileimageview.gesture.c;
/* compiled from: ScaleGestureDetectorCompatQ.java */
/* loaded from: classes9.dex */
public class e extends c {

    /* renamed from: a  reason: collision with root package name */
    private b f17160a;

    public e(Context context, final c.a aVar) {
        b bVar = new b(context, new b.a() { // from class: com.ixigua.touchtileimageview.gesture.e.1
            @Override // com.ixigua.touchtileimageview.gesture.b.a
            public boolean onScale(b bVar2) {
                return aVar.onScale(e.this);
            }

            @Override // com.ixigua.touchtileimageview.gesture.b.a
            public boolean onScaleBegin(b bVar2) {
                return aVar.onScaleBegin(e.this);
            }

            @Override // com.ixigua.touchtileimageview.gesture.b.a
            public void onScaleEnd(b bVar2) {
                aVar.onScaleEnd(e.this);
            }
        });
        this.f17160a = bVar;
        bVar.setQuickScaleEnabled(false);
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f17160a.onTouchEvent(motionEvent);
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public float getScaleFactor() {
        return this.f17160a.getScaleFactor();
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public float getFocusX() {
        return this.f17160a.getFocusX();
    }

    @Override // com.ixigua.touchtileimageview.gesture.c
    public float getFocusY() {
        return this.f17160a.getFocusY();
    }
}
