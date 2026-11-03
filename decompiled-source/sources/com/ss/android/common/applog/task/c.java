package com.ss.android.common.applog.task;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.ss.android.common.applog.TeaThread;
import com.ss.android.common.applog.TeaUtils;
import com.ss.android.common.util.c;
import java.util.ArrayList;
import java.util.List;
/* compiled from: TaskPresenter.java */
/* loaded from: classes9.dex */
public class c {

    /* renamed from: e  reason: collision with root package name */
    private static volatile c f18044e = null;

    /* renamed from: g  reason: collision with root package name */
    private static final long f18045g = 30000;

    /* renamed from: h  reason: collision with root package name */
    private static final long f18046h = 15000;

    /* renamed from: i  reason: collision with root package name */
    private static final long f18047i = 5000;

    /* renamed from: j  reason: collision with root package name */
    private static final int f18048j = 1;
    private static volatile a r = null;
    private static final String s = "TaskPresenter";

    /* renamed from: f  reason: collision with root package name */
    private d f18053f;
    private Handler k;
    private Context l;
    private b m;

    /* renamed from: a  reason: collision with root package name */
    private boolean f18049a = false;

    /* renamed from: b  reason: collision with root package name */
    private volatile boolean f18050b = false;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18051c = true;

    /* renamed from: d  reason: collision with root package name */
    private long f18052d = TeaUtils.now();
    private final List<d> n = new ArrayList();
    private boolean o = false;
    private final Runnable p = new Runnable() { // from class: com.ss.android.common.applog.task.c.3
        @Override // java.lang.Runnable
        public void run() {
            c.a.d("closeCurrentSession currentSession is null : " + (c.this.f18053f == null));
            if (c.this.f18053f != null) {
                if (c.this.o) {
                    c.a.i("is fired : so save session to Db");
                    c.this.m.saveTaskSessionToDb(c.this.f18053f);
                } else {
                    c.a.i("is not fired : so save session in pendingSessions");
                    c.this.n.add(c.this.f18053f);
                }
                c.this.f18053f = null;
                c.this.m.clearSessionSp();
                return;
            }
            c.a.w("closeCurrentSession found that currentSession is null");
        }
    };
    private final Runnable q = new Runnable() { // from class: com.ss.android.common.applog.task.c.4
        @Override // java.lang.Runnable
        public void run() {
            c.this.o = true;
            c.a.i("fire pending Sessions");
            for (d dVar : new ArrayList(c.this.n)) {
                c.this.m.saveTaskSessionToDb(dVar);
            }
            c.this.n.clear();
        }
    };

    private Handler a() {
        return new Handler(TeaThread.getInst().getLooper()) { // from class: com.ss.android.common.applog.task.c.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                boolean z = true;
                if (message.what != 1) {
                    return;
                }
                if (c.this.f18053f != null) {
                    Object obj = message.obj;
                    if (obj instanceof String) {
                        String str = (String) obj;
                        boolean equals = TeaUtils.equals(str, c.this.f18053f.getSessionId());
                        z = (c.this.f18051c && c.this.f18050b) ? false : false;
                        if (equals && z) {
                            c.this.f18053f.setLatestEndTime(System.currentTimeMillis());
                            c.this.m.saveTaskSessionToSp(c.this.f18053f);
                            c.this.a(str);
                            c.this.c();
                            return;
                        }
                    }
                }
                c.this.m.clearSessionSp();
                c.this.c();
            }
        };
    }

    private Handler b() {
        if (this.k == null) {
            synchronized (this) {
                if (this.k == null) {
                    this.k = a();
                }
            }
        }
        return this.k;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        boolean isTaskRunning;
        a aVar = r;
        if (aVar == null || this.f18050b == (isTaskRunning = aVar.isTaskRunning())) {
            return;
        }
        c.a.i("tryCorrectTaskState newIsTaskRunning : " + isTaskRunning);
        if (isTaskRunning) {
            onTaskResume();
        } else {
            onTaskPause();
        }
    }

    private c(Context context) {
        this.l = context.getApplicationContext();
        this.m = new b(context);
    }

    public static c inst(Context context) {
        if (f18044e == null) {
            synchronized (c.class) {
                if (f18044e == null) {
                    f18044e = new c(context.getApplicationContext());
                }
            }
        }
        return f18044e;
    }

    public void onExitBg(final long j2, final String str) {
        TeaThread.getInst().ensureTeaThread(new Runnable() { // from class: com.ss.android.common.applog.task.c.2
            @Override // java.lang.Runnable
            public void run() {
                c.a.d("onExitBg");
                if (c.this.f18051c) {
                    c.this.f18051c = false;
                    TeaThread.getInst().removeCallbacks(c.this.q);
                    TeaThread.getInst().removeCallbacks(c.this.p);
                    c.this.e();
                    c.this.m.clearSessionSp();
                    if (c.this.f18049a) {
                        if (j2 - c.this.f18052d > 30000) {
                            if (c.this.f18053f != null) {
                                c.a.i("close current session");
                                if (c.this.f18050b) {
                                    c.this.f18053f.setEndSessionId(str);
                                    c.this.f18053f.setLatestEndTime(j2);
                                }
                                c.this.m.saveTaskSessionToDb(c.this.f18053f);
                                c.this.f18053f = null;
                            }
                            c.this.q.run();
                        } else {
                            c.a.i("time diff is less than 30000 , so clear current session");
                            c.this.n.clear();
                            c.this.f18053f = null;
                        }
                    }
                    c.this.d();
                }
            }
        });
    }

    public void onTaskPause() {
        final long now = TeaUtils.now();
        TeaThread.getInst().ensureTeaThread(new Runnable() { // from class: com.ss.android.common.applog.task.c.5
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f18050b) {
                    c.a.d("onTaskPause");
                    c.this.f18050b = false;
                    if (c.this.f18051c) {
                        if (c.this.f18053f != null) {
                            c.a.i("wait 15000 to close current session");
                            c.this.f18053f.setLatestEndTime(now);
                            TeaThread.getInst().repost(c.this.p, 15000L);
                            c.this.m.saveTaskSessionToSp(c.this.f18053f);
                            c.this.e();
                            return;
                        }
                        c.a.w("onTaskPause when bg, but no session available");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f18049a = false;
        this.n.clear();
        this.o = false;
    }

    public void onEnterBg(final long j2, final String str) {
        TeaThread.getInst().ensureTeaThread(new Runnable() { // from class: com.ss.android.common.applog.task.c.6
            @Override // java.lang.Runnable
            public void run() {
                c.a.d("onEnterBg");
                if (c.this.f18051c) {
                    return;
                }
                c.this.d();
                TeaThread.getInst().repost(c.this.q, 30010L);
                c.this.f18052d = j2;
                c.this.f18051c = true;
                if (c.this.f18050b) {
                    c.this.f18049a = true;
                    if (c.this.f18053f != null) {
                        c.a.w("enter bg , bug there is already a bg task is running");
                    }
                    c.a.i("task is running , so create a new task session");
                    c.this.f18053f = new d(j2);
                    c.this.f18053f.setFrontSessionId(str);
                    c cVar = c.this;
                    cVar.a(cVar.f18053f.getSessionId());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Handler b2 = b();
        b2.removeMessages(1);
        b2.sendMessageDelayed(Message.obtain(b2, 1, str), 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        b().removeMessages(1);
    }

    public void onTaskResume() {
        final long now = TeaUtils.now();
        TeaThread.getInst().ensureTeaThread(new Runnable() { // from class: com.ss.android.common.applog.task.c.7
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f18050b) {
                    return;
                }
                c.a.d("onTaskResume");
                c.this.f18050b = true;
                if (c.this.f18051c) {
                    c.this.f18049a = true;
                    if (c.this.f18053f != null) {
                        long latestEndTime = now - c.this.f18053f.getLatestEndTime();
                        if (latestEndTime <= 15000) {
                            c.a.i("task time diff " + latestEndTime + " , is less than 15000 so , merge in previous session");
                            TeaThread.getInst().removeCallbacks(c.this.p);
                            c.this.f18053f.addNonTaskTime(latestEndTime);
                            c.this.f18053f.setLatestEndTime(now);
                            c.this.m.saveTaskSessionToSp(c.this.f18053f);
                            c cVar = c.this;
                            cVar.a(cVar.f18053f.getSessionId());
                            return;
                        }
                        c.a.i("task time diff " + latestEndTime + " , is bigger than 15000 so close current session and create new session");
                        TeaThread.getInst().removeCallbacks(c.this.p);
                        c.this.p.run();
                        c.this.f18053f = new d(now);
                        c.this.m.clearSessionSp();
                        c cVar2 = c.this;
                        cVar2.a(cVar2.f18053f.getSessionId());
                        return;
                    }
                    c.a.i("pure bg launch , so create a new task session");
                    c.this.f18053f = new d(now);
                    c.this.m.clearSessionSp();
                    c cVar3 = c.this;
                    cVar3.a(cVar3.f18053f.getSessionId());
                }
            }
        });
    }

    public static void registerTaskCallback(a aVar) {
        r = aVar;
    }
}
