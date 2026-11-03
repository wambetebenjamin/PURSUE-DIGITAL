package com.bytedance.bdturing;

import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OnTouchReportListener.java */
/* loaded from: classes.dex */
public class i {

    /* renamed from: b  reason: collision with root package name */
    private static final double f4186b = 33.3333d;

    /* renamed from: d  reason: collision with root package name */
    private static int f4187d = 6000;

    /* renamed from: a  reason: collision with root package name */
    private List<a> f4188a = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f4189c = false;

    /* renamed from: e  reason: collision with root package name */
    private int f4190e;

    public i(int i2) {
        int i3 = f4187d;
        this.f4190e = i3;
        if (i2 > i3) {
            this.f4190e = i2;
        }
    }

    public void a(MotionEvent motionEvent) {
        int i2;
        if (this.f4189c) {
            return;
        }
        try {
            if (this.f4188a.size() < this.f4190e) {
                i2 = motionEvent.getAction();
                try {
                    a aVar = new a();
                    aVar.f4192a = motionEvent.getPressure();
                    aVar.f4193b = motionEvent.getTouchMajor();
                    aVar.f4194c = motionEvent.getX();
                    aVar.f4195d = motionEvent.getY();
                    aVar.f4196e = motionEvent.getEventTime();
                    aVar.f4197f = motionEvent.getAction();
                    this.f4188a.add(aVar);
                    h.d("onTouch", "events : " + this.f4188a.size());
                } catch (Exception unused) {
                    if (i2 != 1) {
                        return;
                    }
                    this.f4189c = true;
                    a();
                } catch (Throwable th) {
                    th = th;
                    if (i2 == 1) {
                        this.f4189c = true;
                        a();
                    }
                    throw th;
                }
            } else {
                i2 = 1;
            }
            if (i2 != 1) {
                return;
            }
        } catch (Exception unused2) {
            i2 = 1;
        } catch (Throwable th2) {
            th = th2;
            i2 = 1;
        }
        this.f4189c = true;
        a();
    }

    private void a() {
        try {
            k.getInstance().postRunnable(new Runnable() { // from class: com.bytedance.bdturing.i.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        int size = i.this.f4188a.size() - 1;
                        double d2 = ((a) i.this.f4188a.get(size)).f4196e;
                        while (size >= 0) {
                            a aVar = (a) i.this.f4188a.get(size);
                            if (d2 >= aVar.f4196e || size == 0) {
                                p.a(aVar.f4196e, aVar.f4192a, aVar.f4193b, aVar.f4197f, aVar.f4194c, aVar.f4195d);
                                d2 -= i.f4186b;
                                h.i("onTouch", "uploadEvent use " + aVar.f4196e + " " + aVar.f4197f);
                            } else {
                                h.i("onTouch", "uploadEvent abandon " + aVar.f4196e + " " + aVar.f4197f);
                            }
                            size--;
                        }
                        i.this.f4188a.clear();
                    } catch (Exception unused) {
                    } catch (Throwable th) {
                        i.this.f4189c = false;
                        throw th;
                    }
                    i.this.f4189c = false;
                }
            });
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: OnTouchReportListener.java */
    /* loaded from: classes.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        float f4192a;

        /* renamed from: b  reason: collision with root package name */
        float f4193b;

        /* renamed from: c  reason: collision with root package name */
        float f4194c;

        /* renamed from: d  reason: collision with root package name */
        float f4195d;

        /* renamed from: e  reason: collision with root package name */
        long f4196e;

        /* renamed from: f  reason: collision with root package name */
        int f4197f;

        a() {
        }
    }
}
