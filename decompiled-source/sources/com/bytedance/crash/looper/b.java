package com.bytedance.crash.looper;

import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import com.brentvatne.react.ReactVideoView;
import com.bytedance.apm.constant.r;
import com.bytedance.bdturing.methods.l;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.crash.GlobalSwitch;
import com.bytedance.crash.jni.NativeBridge;
import com.bytedance.crash.util.m;
import com.bytedance.crash.util.q;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: LooperMessageManager.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6818a = "no message running";

    /* renamed from: b  reason: collision with root package name */
    private static final String f6819b = "message is running";

    /* renamed from: c  reason: collision with root package name */
    private static final long f6820c = 200;

    /* renamed from: d  reason: collision with root package name */
    private static final int f6821d = 50;

    /* renamed from: e  reason: collision with root package name */
    private static final int f6822e = 30;

    /* renamed from: f  reason: collision with root package name */
    private static final int f6823f = 200;

    /* renamed from: g  reason: collision with root package name */
    private static final int f6824g = 3000;

    /* renamed from: h  reason: collision with root package name */
    private volatile c f6825h;

    /* renamed from: i  reason: collision with root package name */
    private volatile a f6826i;

    /* renamed from: j  reason: collision with root package name */
    private long f6827j;
    private long k;
    private long l;
    private boolean m;
    private JSONArray n;
    private final Runnable o = new Runnable() { // from class: com.bytedance.crash.looper.b.1
        @Override // java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            String e2 = NativeBridge.e(true);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.bytedance.sdk.account.save.database.a.FIELD_TIME, currentTimeMillis);
                jSONObject.put(r.STATS_KEY_TRACE, e2);
                b.this.n.put(jSONObject);
                if (b.this.n.length() >= 5) {
                    b.this.n.remove(0);
                }
            } catch (Throwable unused) {
            }
        }
    };

    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    private interface a {
        a a(Message message, long j2, long j3, long j4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        c cVar = new c();
        this.f6825h = cVar;
        this.f6826i = cVar;
        this.n = new JSONArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f6826i = null;
    }

    public synchronized void a(Message message) {
        if (this.f6826i == null) {
            return;
        }
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        long currentTimeMillis = System.currentTimeMillis();
        long uptimeMillis = SystemClock.uptimeMillis();
        long j2 = this.f6827j;
        if (j2 != 0) {
            a a2 = this.f6826i.a(message, currentTimeMillis - j2, currentThreadTimeMillis - this.k, this.l);
            if (GlobalSwitch.d()) {
                if (message == null) {
                    com.bytedance.crash.runtime.b.a(this.o, WsConstants.EXIT_DELAY_TIME);
                } else {
                    com.bytedance.crash.runtime.b.c(this.o);
                }
            }
            if (a2 == null) {
                a2 = this.f6825h;
            }
            this.f6826i = a2;
        }
        this.m = message != null;
        this.f6827j = currentTimeMillis;
        this.k = currentThreadTimeMillis;
        this.l = uptimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(File file) {
        boolean z;
        long j2;
        long j3;
        c cVar;
        C0096b c0096b;
        long currentTimeMillis = System.currentTimeMillis();
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            if (this.f6826i != null) {
                if (this.f6827j != 0 && (this.f6826i instanceof c)) {
                    cVar = (c) this.f6826i;
                    C0096b c0096b2 = new C0096b();
                    long j4 = this.f6827j;
                    long j5 = this.k;
                    z = this.m;
                    this.f6826i = c0096b2;
                    c0096b = c0096b2;
                    j3 = j5;
                    j2 = j4;
                }
                return;
            }
            z = false;
            j2 = 0;
            j3 = 0;
            cVar = null;
            c0096b = null;
            if (c0096b != null) {
                c0096b.a(file, currentTimeMillis - j2, z);
            }
            if (cVar != null) {
                cVar.a(file, currentTimeMillis, uptimeMillis, j2, j3);
            }
            b(file);
            d dVar = new d();
            dVar.a(uptimeMillis);
            synchronized (this) {
                if (!(this.f6826i instanceof d)) {
                    if ((this.f6826i instanceof C0096b) && c0096b != null) {
                        c0096b.c();
                    }
                } else {
                    dVar.a((d) this.f6826i);
                    this.f6826i = this.f6825h;
                }
            }
            dVar.a(file);
        }
    }

    private void b(File file) {
        if (this.n.length() != 0) {
            JSONObject jSONObject = new JSONObject();
            q.a(jSONObject, "block_stack", this.n);
            m.writeFile(com.bytedance.crash.looper.a.d(file), jSONObject, false);
        }
    }

    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    private static class d implements a {

        /* renamed from: a  reason: collision with root package name */
        private final LinkedList<j> f6842a;

        /* renamed from: b  reason: collision with root package name */
        private LinkedList<e> f6843b;

        private d() {
            this.f6842a = new LinkedList<>();
            this.f6843b = new LinkedList<>();
        }

        @Override // com.bytedance.crash.looper.b.a
        public a a(Message message, long j2, long j3, long j4) {
            if (message != null) {
                e eVar = new e();
                eVar.b(message, j4);
                eVar.b(j2, j3);
                this.f6843b.add(eVar);
            }
            return this;
        }

        private static MessageQueue a() {
            if (Looper.getMainLooper() != null) {
                Looper mainLooper = Looper.getMainLooper();
                if (mainLooper == Looper.myLooper()) {
                    return Looper.myQueue();
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    return mainLooper.getQueue();
                }
                try {
                    Field declaredField = mainLooper.getClass().getDeclaredField("mQueue");
                    declaredField.setAccessible(true);
                    return (MessageQueue) declaredField.get(mainLooper);
                } catch (Throwable unused) {
                }
            }
            return null;
        }

        private static Field a(String str, String str2) {
            try {
                Field declaredField = Class.forName(str).getDeclaredField(str2);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Exception unused) {
                return null;
            }
        }

        private static Object a(Field field, Object obj) {
            try {
                return field.get(obj);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(long j2) {
            MessageQueue a2 = a();
            Field a3 = a("android.os.MessageQueue", "mMessages");
            Field a4 = a("android.os.Message", Constants.BUTTON_NEXT);
            if (a2 == null || a3 == null || a4 == null) {
                return;
            }
            try {
                synchronized (a2) {
                    Message message = (Message) a(a3, a2);
                    int i2 = 0;
                    while (message != null) {
                        int i3 = i2 + 1;
                        if (i2 >= 200) {
                            break;
                        }
                        j jVar = new j();
                        jVar.b(message, j2);
                        this.f6842a.add(jVar);
                        message = (Message) a(a4, message);
                        i2 = i3;
                    }
                }
            } catch (Throwable unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(d dVar) {
            this.f6843b = dVar.f6843b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(File file) {
            JSONArray jSONArray = new JSONArray();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < 200) {
                if (i3 < this.f6843b.size()) {
                    int i5 = i3 + 1;
                    e eVar = this.f6843b.get(i3);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        eVar.a(jSONObject);
                        int i6 = i2 + 1;
                        try {
                            jSONObject.put("id", i2);
                            jSONArray.put(jSONObject);
                        } catch (JSONException unused) {
                        }
                        i2 = i6;
                    } catch (JSONException unused2) {
                    }
                    if (i4 < this.f6842a.size() && eVar.b(this.f6842a.get(i4))) {
                        i4++;
                    }
                    i3 = i5;
                } else if (i4 >= this.f6842a.size()) {
                    break;
                } else {
                    int i7 = i4 + 1;
                    j jVar = this.f6842a.get(i4);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jVar.a(jSONObject2);
                        int i8 = i2 + 1;
                        try {
                            jSONObject2.put("id", i2);
                            jSONArray.put(jSONObject2);
                        } catch (JSONException unused3) {
                        }
                        i2 = i8;
                    } catch (JSONException unused4) {
                    }
                    i4 = i7;
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("pending_messages", jSONArray);
            } catch (JSONException unused5) {
            }
            m.writeFile(com.bytedance.crash.looper.a.c(file), jSONObject3, false);
        }
    }

    /* compiled from: LooperMessageManager.java */
    /* renamed from: com.bytedance.crash.looper.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0096b implements a {

        /* renamed from: a  reason: collision with root package name */
        private File f6829a;

        /* renamed from: b  reason: collision with root package name */
        private e f6830b;

        /* renamed from: c  reason: collision with root package name */
        private final JSONObject f6831c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f6832d;

        private C0096b() {
            this.f6831c = new JSONObject();
        }

        @Override // com.bytedance.crash.looper.b.a
        public a a(Message message, long j2, long j3, long j4) {
            e eVar = new e();
            this.f6830b = eVar;
            eVar.b(j2, j3);
            this.f6830b.b(message, j4);
            a();
            if (this.f6832d) {
                return null;
            }
            return new d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(File file, long j2, boolean z) {
            synchronized (this.f6831c) {
                try {
                    JSONObject optJSONObject = this.f6831c.optJSONObject("current_message");
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                        this.f6831c.put("current_message", optJSONObject);
                        optJSONObject.put("message", z ? b.f6819b : "no message running");
                    }
                    optJSONObject.put("currentMessageCost", j2);
                    optJSONObject.put("currentMessageCpu", -1);
                    this.f6829a = file;
                    b();
                } catch (JSONException unused) {
                }
            }
        }

        private void a() {
            synchronized (this.f6831c) {
                try {
                    JSONObject jSONObject = (JSONObject) this.f6831c.opt("current_message");
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                        this.f6831c.put("current_message", jSONObject);
                    }
                    jSONObject.put("message", this.f6830b.b());
                    jSONObject.put("currentMessageLastCost", this.f6830b.f6844a);
                    jSONObject.put("currentMessageLastCpu", this.f6830b.f6845b);
                    b();
                } catch (JSONException unused) {
                }
            }
        }

        private void b() {
            File file = this.f6829a;
            if (file != null) {
                m.writeFile(com.bytedance.crash.looper.a.a(file), this.f6831c, false);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            this.f6832d = true;
        }
    }

    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    private static class c implements a {

        /* renamed from: a  reason: collision with root package name */
        private final a f6833a;

        /* renamed from: b  reason: collision with root package name */
        private final C0097b f6834b;

        private c() {
            a aVar = new a(50);
            this.f6833a = aVar;
            this.f6834b = new C0097b(aVar, 30);
        }

        @Override // com.bytedance.crash.looper.b.a
        public a a(Message message, long j2, long j3, long j4) {
            if (message == null) {
                this.f6834b.a(j2, j3);
            } else {
                this.f6834b.b(message, j2, j3, j4);
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(File file, long j2, long j3, long j4, long j5) {
            this.f6834b.a(true);
            JSONArray jSONArray = new JSONArray();
            i b2 = this.f6833a.b();
            while (b2 != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    b2.a(jSONObject);
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                }
                b2 = this.f6833a.c();
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("history_message", jSONArray);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("dump_wall_time", j2);
                jSONObject3.put("dump_up_time", j3);
                jSONObject3.put("last_wall_time", j4);
                jSONObject3.put("last_cpu_time", j5);
                jSONObject2.put("history_details", jSONObject3);
            } catch (JSONException unused2) {
            }
            m.writeFile(com.bytedance.crash.looper.a.b(file), jSONObject2, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: LooperMessageManager.java */
        /* loaded from: classes2.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            private final i[] f6835a;

            /* renamed from: b  reason: collision with root package name */
            private final int f6836b;

            /* renamed from: c  reason: collision with root package name */
            private int f6837c;

            /* renamed from: d  reason: collision with root package name */
            private int f6838d;

            a(int i2) {
                this.f6835a = new i[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    this.f6835a[i3] = new i();
                }
                this.f6836b = i2;
                this.f6837c = -1;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public i a() {
                int i2 = this.f6837c + 1;
                this.f6837c = i2;
                if (i2 == this.f6836b) {
                    this.f6837c = 0;
                }
                return this.f6835a[this.f6837c];
            }

            /* JADX INFO: Access modifiers changed from: private */
            public i b() {
                int i2 = this.f6837c;
                if (i2 == -1) {
                    return null;
                }
                int i3 = i2 + 1;
                this.f6838d = i3;
                if (i3 == this.f6836b || this.f6835a[i3].f6844a == 0) {
                    this.f6838d = 0;
                }
                return this.f6835a[this.f6838d];
            }

            /* JADX INFO: Access modifiers changed from: private */
            public i c() {
                int i2 = this.f6838d + 1;
                this.f6838d = i2;
                if (i2 == this.f6837c + 1) {
                    return null;
                }
                if (i2 == this.f6836b) {
                    this.f6838d = 0;
                }
                return this.f6835a[this.f6838d];
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: LooperMessageManager.java */
        /* renamed from: com.bytedance.crash.looper.b$c$b  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0097b extends k {

            /* renamed from: h  reason: collision with root package name */
            private final HashMap<String, g> f6839h;

            /* renamed from: i  reason: collision with root package name */
            private final h f6840i;

            /* renamed from: j  reason: collision with root package name */
            private final a f6841j;
            private f k;

            C0097b(a aVar, int i2) {
                super();
                this.f6839h = new HashMap<>(i2);
                this.f6840i = new h(i2);
                this.f6841j = aVar;
            }

            @Override // com.bytedance.crash.looper.b.k, com.bytedance.crash.looper.b.f, com.bytedance.crash.looper.b.e, com.bytedance.crash.looper.b.j
            protected void a() {
                super.a();
                this.k = null;
                for (g gVar : this.f6839h.values()) {
                    if (gVar != null) {
                        this.f6840i.a(gVar);
                    }
                }
                this.f6839h.clear();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(boolean z) {
                if (this.f6844a == 0 && this.f6846c == 0) {
                    return;
                }
                if (z || this.f6844a > b.f6820c) {
                    i a2 = this.f6841j.a();
                    a2.a();
                    a2.f6846c = this.f6846c;
                    a2.f6844a = this.f6844a;
                    a2.f6845b = this.f6845b;
                    a2.f6855f = this.f6855f;
                    a2.a((k) this);
                    a2.a(this.k);
                    a2.a((j) this.k);
                    a();
                }
            }

            @Override // com.bytedance.crash.looper.b.k
            protected void a(long j2, long j3) {
                if (j2 > b.f6820c) {
                    a(true);
                }
                super.a(j2, j3);
                this.f6844a += j2;
                this.f6845b += j3;
                a(false);
            }

            private void a(Message message, long j2, long j3, long j4) {
                this.f6846c++;
                if (this.f6855f == Long.MAX_VALUE) {
                    long a2 = a(message, j4);
                    if (a2 <= 0) {
                        if (this.f6844a + a2 > 0) {
                            this.f6855f = 0L;
                        } else {
                            this.f6855f = a2 + this.f6844a;
                        }
                    }
                }
                this.f6844a += j2;
                this.f6845b += j3;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void b(Message message, long j2, long j3, long j4) {
                if (j2 > b.f6820c) {
                    a(true);
                }
                String b2 = b(message);
                g gVar = this.f6839h.get(b2);
                if (gVar == null) {
                    gVar = this.f6840i.a();
                    if (gVar == null) {
                        a(true);
                        g a2 = this.f6840i.a();
                        if (a2 == null) {
                            return;
                        }
                        gVar = a2;
                    }
                    this.f6839h.put(b2, gVar);
                    gVar.b(message, j4);
                }
                g gVar2 = gVar;
                gVar2.a(message, j2, j3, j4);
                a(message, j2, j3, j4);
                if (this.k == null || gVar2.f6844a > this.k.f6844a) {
                    this.k = gVar2;
                }
                a(false);
            }

            private String b(Message message) {
                if (message.getCallback() != null) {
                    return message.getCallback().getClass().getName();
                }
                return message.getTarget() != null ? message.getTarget().getClass().getName() : "unknown";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        private g f6848a;

        private h(int i2) {
            while (i2 > 0) {
                a(new g());
                i2--;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(g gVar) {
            gVar.f6847h = this.f6848a;
            this.f6848a = gVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public g a() {
            g gVar = this.f6848a;
            if (gVar != null) {
                this.f6848a = gVar.f6847h;
                gVar.f6847h = null;
                gVar.a();
                return gVar;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class g extends f {

        /* renamed from: h  reason: collision with root package name */
        private g f6847h;

        private g() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Message message, long j2, long j3, long j4) {
            this.f6844a += j2;
            this.f6845b += j3;
            this.f6846c++;
            long a2 = a(message, j4);
            if (this.f6855f > a2) {
                this.f6855f = a2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class i extends k {

        /* renamed from: h  reason: collision with root package name */
        private int f6849h;

        /* renamed from: i  reason: collision with root package name */
        private long f6850i;

        /* renamed from: j  reason: collision with root package name */
        private long f6851j;
        private long k;

        private i() {
            super();
        }

        @Override // com.bytedance.crash.looper.b.k, com.bytedance.crash.looper.b.f, com.bytedance.crash.looper.b.e, com.bytedance.crash.looper.b.j
        protected void a() {
            super.a();
            this.f6849h = 0;
            this.f6850i = 0L;
            this.f6851j = 0L;
        }

        protected void a(f fVar) {
            if (fVar == null) {
                return;
            }
            this.f6849h = fVar.f6846c;
            this.f6850i = fVar.f6844a;
            this.f6851j = fVar.f6845b;
            this.k = fVar.f6855f;
        }

        @Override // com.bytedance.crash.looper.b.k, com.bytedance.crash.looper.b.e, com.bytedance.crash.looper.b.j
        protected void a(JSONObject jSONObject) {
            super.a(jSONObject);
            int d2 = d();
            if (d2 == 9) {
                jSONObject.put("LongestGroupMessageCount", this.f6849h);
                jSONObject.put("LongestGroupDuration", this.f6850i);
                jSONObject.put("LongestGroupCpuDuration", this.f6851j);
                jSONObject.put("LongestGroupDelay", this.k);
                jSONObject.put("messageCount", this.f6846c);
            }
            jSONObject.put("msg", b());
            jSONObject.put("type", d2);
        }

        private int d() {
            if (this.f6846c == 0) {
                return 1;
            }
            return c() ? 8 : 9;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class k extends f {

        /* renamed from: h  reason: collision with root package name */
        private static final int f6857h = 1;

        /* renamed from: i  reason: collision with root package name */
        private long f6858i;

        /* renamed from: j  reason: collision with root package name */
        private long f6859j;
        private int k;

        private k() {
            super();
        }

        @Override // com.bytedance.crash.looper.b.f, com.bytedance.crash.looper.b.e, com.bytedance.crash.looper.b.j
        protected void a() {
            super.a();
            this.f6858i = 0L;
            this.f6859j = 0L;
            this.k = 0;
        }

        protected boolean c() {
            return this.f6846c == 1 && this.f6844a - this.f6858i > b.f6820c;
        }

        protected void a(long j2, long j3) {
            this.f6858i += j2;
            this.f6859j += j3;
            if (j2 > 1) {
                this.k++;
            }
        }

        protected void a(k kVar) {
            this.k = kVar.k;
            this.f6858i = kVar.f6858i;
            this.f6859j = kVar.f6859j;
        }

        @Override // com.bytedance.crash.looper.b.e, com.bytedance.crash.looper.b.j
        protected void a(JSONObject jSONObject) {
            super.a(jSONObject);
            if (this.k != 0) {
                jSONObject.put("idleDuration", this.f6858i);
                jSONObject.put("idleCpuDuration", this.f6859j);
                jSONObject.put("idleCount", this.k);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class f extends e {

        /* renamed from: c  reason: collision with root package name */
        protected int f6846c;

        private f() {
            super();
        }

        @Override // com.bytedance.crash.looper.b.e, com.bytedance.crash.looper.b.j
        protected void a() {
            super.a();
            this.f6846c = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class e extends j {

        /* renamed from: a  reason: collision with root package name */
        protected long f6844a;

        /* renamed from: b  reason: collision with root package name */
        protected long f6845b;

        private e() {
            super();
        }

        @Override // com.bytedance.crash.looper.b.j
        protected void a() {
            super.a();
            b(0L, 0L);
        }

        protected void b(long j2, long j3) {
            this.f6844a = j2;
            this.f6845b = j3;
        }

        @Override // com.bytedance.crash.looper.b.j
        protected void a(JSONObject jSONObject) {
            super.a(jSONObject);
            jSONObject.put("duration", this.f6844a);
            jSONObject.put("cpuDuration", this.f6845b);
        }

        protected String b() {
            if (this.f6854e != null) {
                return this.f6854e;
            }
            return this.f6853d != null ? this.f6853d : "no message running";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperMessageManager.java */
    /* loaded from: classes2.dex */
    public static class j {

        /* renamed from: a  reason: collision with root package name */
        private static final int f6852a = 100;

        /* renamed from: d  reason: collision with root package name */
        protected String f6853d;

        /* renamed from: e  reason: collision with root package name */
        protected String f6854e;

        /* renamed from: f  reason: collision with root package name */
        protected long f6855f;

        /* renamed from: g  reason: collision with root package name */
        protected int f6856g;

        private j() {
            this.f6855f = Long.MAX_VALUE;
        }

        protected static long a(Message message, long j2) {
            if (a(message)) {
                return message.getWhen() - j2;
            }
            return Long.MAX_VALUE;
        }

        protected static boolean a(Message message) {
            return message.getWhen() > 100;
        }

        protected void b(Message message, long j2) {
            if (message == null) {
                return;
            }
            if (message.getTarget() != null) {
                this.f6853d = message.getTarget().getClass().getName();
            }
            if (message.getCallback() != null) {
                String name = message.getCallback().getClass().getName();
                this.f6854e = name;
                if (name.startsWith("Y.ARunnable")) {
                    String obj = message.getCallback().toString();
                    if (obj.charAt(0) >= '0' && obj.charAt(0) <= '9') {
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.f6854e);
                        sb.append(".run$");
                        for (int i2 = 0; i2 < obj.length() && obj.charAt(i2) != '_'; i2++) {
                            sb.append(obj.charAt(i2));
                        }
                        this.f6854e = sb.toString();
                    }
                }
            }
            this.f6855f = a(message, j2);
            this.f6856g = message.what;
        }

        protected void a(j jVar) {
            if (jVar == null) {
                return;
            }
            this.f6853d = jVar.f6853d;
            this.f6854e = jVar.f6854e;
            this.f6856g = jVar.f6856g;
        }

        protected void a() {
            this.f6853d = null;
            this.f6854e = null;
            this.f6855f = Long.MAX_VALUE;
            this.f6856g = -1;
        }

        protected void a(JSONObject jSONObject) {
            String str = this.f6854e;
            if (str != null) {
                jSONObject.put(l.VALUE_CALLBACK, str);
            }
            String str2 = this.f6853d;
            if (str2 != null) {
                jSONObject.put("target", str2);
            }
            int i2 = this.f6856g;
            if (i2 != -1) {
                jSONObject.put(ReactVideoView.EVENT_PROP_WHAT, i2);
            }
            long j2 = this.f6855f;
            if (j2 != Long.MAX_VALUE) {
                jSONObject.put("when", j2);
            }
        }

        protected boolean b(j jVar) {
            return this.f6855f == jVar.f6855f && this.f6856g == jVar.f6856g && this.f6853d == jVar.f6853d && this.f6854e == jVar.f6854e;
        }
    }
}
