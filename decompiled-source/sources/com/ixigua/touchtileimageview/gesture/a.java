package com.ixigua.touchtileimageview.gesture;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.ixigua.touchtileimageview.gesture.c;
/* compiled from: AppGestureDetector.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private c f17135a;

    /* renamed from: b  reason: collision with root package name */
    private GestureDetector f17136b;

    /* renamed from: c  reason: collision with root package name */
    private final GestureDetector.OnGestureListener f17137c;

    /* renamed from: d  reason: collision with root package name */
    private final c.a f17138d;

    /* renamed from: e  reason: collision with root package name */
    private GestureDetector$OnDoubleTapListenerC0308a f17139e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f17140f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17141g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f17142h;

    /* renamed from: i  reason: collision with root package name */
    private MotionEvent f17143i;

    /* compiled from: AppGestureDetector.java */
    /* renamed from: com.ixigua.touchtileimageview.gesture.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class GestureDetector$OnDoubleTapListenerC0308a implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, c.a {
        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        public void onFingerUp(int i2) {
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return false;
        }

        public void onGestureEnd() {
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public boolean onScale(c cVar) {
            return false;
        }

        public boolean onScaleBegin(c cVar) {
            return false;
        }

        public void onScaleEnd(c cVar) {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    public a(Context context, GestureDetector$OnDoubleTapListenerC0308a gestureDetector$OnDoubleTapListenerC0308a) {
        GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() { // from class: com.ixigua.touchtileimageview.gesture.a.1
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return a.this.f17139e.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
                a.this.f17139e.onShowPress(motionEvent);
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return a.this.f17139e.onSingleTapUp(motionEvent);
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                return a.this.f17139e.onScroll(motionEvent, motionEvent2, f2, f3);
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                a.this.f17139e.onLongPress(motionEvent);
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                return a.this.f17139e.onFling(motionEvent, motionEvent2, f2, f3);
            }
        };
        this.f17137c = onGestureListener;
        c.a aVar = new c.a() { // from class: com.ixigua.touchtileimageview.gesture.a.2
            @Override // com.ixigua.touchtileimageview.gesture.c.a
            public boolean onScale(c cVar) {
                return a.this.f17139e.onScale(cVar);
            }

            @Override // com.ixigua.touchtileimageview.gesture.c.a
            public boolean onScaleBegin(c cVar) {
                if (a.this.f17143i == null || a.this.f17143i.getPointerCount() > 1) {
                    return a.this.f17139e.onScaleBegin(cVar);
                }
                return false;
            }

            @Override // com.ixigua.touchtileimageview.gesture.c.a
            public void onScaleEnd(c cVar) {
                a.this.f17139e.onScaleEnd(cVar);
            }
        };
        this.f17138d = aVar;
        this.f17142h = false;
        this.f17143i = null;
        this.f17139e = gestureDetector$OnDoubleTapListenerC0308a;
        GestureDetector gestureDetector = new GestureDetector(context, onGestureListener);
        this.f17136b = gestureDetector;
        gestureDetector.setOnDoubleTapListener(gestureDetector$OnDoubleTapListenerC0308a);
        this.f17135a = a(context, aVar);
    }

    private c a(Context context, c.a aVar) {
        if (Build.VERSION.SDK_INT > 28) {
            return new e(context, aVar);
        }
        return new d(context, aVar);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f17141g) {
            return false;
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f17140f = false;
            this.f17142h = false;
        }
        if (this.f17140f) {
            if (!this.f17142h) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f17136b.onTouchEvent(obtain);
                this.f17135a.onTouchEvent(obtain);
                this.f17142h = true;
            }
            return false;
        }
        boolean onTouchEvent = this.f17136b.onTouchEvent(motionEvent);
        this.f17143i = MotionEvent.obtain(motionEvent);
        this.f17135a.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            this.f17139e.onGestureEnd();
        }
        if (motionEvent.getActionMasked() == 6) {
            this.f17139e.onFingerUp(motionEvent.getPointerId(0));
        }
        return onTouchEvent;
    }

    public void cancelAllTouchEventUntilNextDownEvent() {
        this.f17140f = true;
    }

    public void cancelAllTouchEventForever() {
        this.f17141g = true;
    }
}
