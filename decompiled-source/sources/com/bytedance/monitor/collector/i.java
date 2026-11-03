package com.bytedance.monitor.collector;

import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import com.brentvatne.react.ReactVideoView;
import com.bytedance.monitor.collector.f;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: LooperDispatchMonitor.java */
/* loaded from: classes2.dex */
public class i extends com.bytedance.monitor.collector.b {
    private static volatile h D = null;
    private static int K = 0;
    public static final String NO_MESSAGE_RUNNING = "no message running";

    /* renamed from: g  reason: collision with root package name */
    private static final String f9534g = "LooperDispatchMonitor";

    /* renamed from: h  reason: collision with root package name */
    private static final int f9535h = 9;

    /* renamed from: i  reason: collision with root package name */
    private static final int f9536i = 1;

    /* renamed from: j  reason: collision with root package name */
    private static final int f9537j = 8;
    private static final int k = 10;
    private static final int l = 100;
    private static int m = 0;
    private static int n = 0;
    private static final int o = 300;
    private static final int p = 1000000;
    private String A;
    private String B;
    private com.bytedance.monitor.collector.a C;
    private volatile boolean E;
    private boolean F;
    private final com.bytedance.apm.thread.d G;
    private volatile boolean H;
    private Runnable L;

    /* renamed from: f  reason: collision with root package name */
    c f9538f;
    private int q;
    private volatile int r;
    private int s;
    private int t;
    private f u;
    private b v;
    private long w;
    private long x;
    private int y;
    private long z;
    private static int[] I = {600, 300};

    /* renamed from: J  reason: collision with root package name */
    private static int[] f9533J = {200, 100};
    public static boolean sNeedCheckTime = false;
    public static boolean sCheckTimeLevelSwitch = false;
    public static boolean dumpStackTimeSwitch = false;

    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    public interface c {
        void doFrame(long[] jArr);
    }

    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        long f9562a;

        /* renamed from: b  reason: collision with root package name */
        long f9563b;

        /* renamed from: c  reason: collision with root package name */
        long f9564c;

        /* renamed from: d  reason: collision with root package name */
        long f9565d;

        /* renamed from: e  reason: collision with root package name */
        long f9566e;
    }

    @Override // com.bytedance.monitor.collector.b
    protected void a(int i2) {
    }

    static /* synthetic */ int i(i iVar) {
        int i2 = iVar.q;
        iVar.q = i2 + 1;
        return i2;
    }

    public i(int i2) {
        this(i2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public h g() {
        return D;
    }

    public static void setStackTracer(h hVar) {
        D = hVar;
    }

    public i(int i2, boolean z) {
        super(i2, "block_looper_info");
        this.q = 0;
        this.r = 0;
        this.s = 100;
        this.t = 200;
        this.w = -1L;
        this.x = -1L;
        this.y = -1;
        this.z = -1L;
        this.E = false;
        this.F = false;
        this.H = false;
        this.L = new Runnable() { // from class: com.bytedance.monitor.collector.i.2

            /* renamed from: c  reason: collision with root package name */
            private long f9542c;

            /* renamed from: b  reason: collision with root package name */
            private long f9541b = 0;

            /* renamed from: d  reason: collision with root package name */
            private int f9543d = -1;

            /* renamed from: e  reason: collision with root package name */
            private int f9544e = 0;

            /* renamed from: f  reason: collision with root package name */
            private int f9545f = 0;

            @Override // java.lang.Runnable
            public void run() {
                long uptimeMillis = SystemClock.uptimeMillis();
                a a2 = i.this.v.a();
                if (this.f9543d == i.this.r) {
                    this.f9544e++;
                } else {
                    this.f9544e = 0;
                    this.f9545f = 0;
                    this.f9542c = uptimeMillis;
                }
                this.f9543d = i.this.r;
                int i3 = this.f9544e;
                if (i3 > 0 && i3 - this.f9545f >= i.K && this.f9541b != 0 && uptimeMillis - this.f9542c >= i.n && i.this.H) {
                    if (i.D != null) {
                        a2.f9557f = i.this.g().getStackTrace(Looper.getMainLooper().getThread());
                    } else {
                        a2.f9557f = s.stackToString(Looper.getMainLooper().getThread().getStackTrace());
                    }
                    this.f9545f = this.f9544e;
                }
                a2.f9555d = i.this.H;
                a2.f9554c = (uptimeMillis - this.f9541b) - i.m;
                a2.f9552a = uptimeMillis;
                long uptimeMillis2 = SystemClock.uptimeMillis();
                this.f9541b = uptimeMillis2;
                a2.f9553b = uptimeMillis2 - uptimeMillis;
                a2.f9556e = i.this.r;
                i.this.G.postDelayed(i.this.L, i.m);
                i.this.v.a(a2);
            }
        };
        com.bytedance.monitor.collector.service.d.getInstance().start();
        this.f9538f = new c() { // from class: com.bytedance.monitor.collector.i.1
            @Override // com.bytedance.monitor.collector.i.c
            public void doFrame(long[] jArr) {
                e a2;
                if (i.this.F && i.this.u != null && (a2 = i.this.u.a()) != null && a2.f9569c == 8) {
                    d dVar = new d();
                    if (jArr != null) {
                        dVar.f9562a = jArr[1];
                        dVar.f9563b = jArr[5];
                        dVar.f9564c = jArr[6];
                        dVar.f9565d = jArr[7];
                        dVar.f9566e = jArr[8];
                    }
                    a2.m = dVar;
                }
            }
        };
        if (z || sNeedCheckTime) {
            com.bytedance.apm.thread.d dVar = new com.bytedance.apm.thread.d("looper_monitor__");
            this.G = dVar;
            dVar.start();
            h();
            this.v = new b(300);
            dVar.postDelayed(this.L, m);
            return;
        }
        this.G = null;
    }

    private void h() {
        boolean z = dumpStackTimeSwitch;
        if (!z && sCheckTimeLevelSwitch) {
            int i2 = f9533J[1];
            m = i2;
            int i3 = I[0];
            n = i3;
            K = i3 / i2;
        } else if (!z && !sCheckTimeLevelSwitch) {
            int i4 = f9533J[0];
            m = i4;
            int i5 = I[0];
            n = i5;
            K = i5 / i4;
        } else if (z && sCheckTimeLevelSwitch) {
            int i6 = f9533J[1];
            m = i6;
            int i7 = I[1];
            n = i7;
            K = i7 / i6;
        } else if (!z || sCheckTimeLevelSwitch) {
        } else {
            int i8 = f9533J[0];
            m = i8;
            int i9 = I[1];
            n = i9;
            K = i9 / i8;
        }
    }

    public static void setDumpStackTicks(int i2) {
        K = i2;
    }

    @Override // com.bytedance.monitor.collector.b
    void a() {
        super.a();
    }

    @Override // com.bytedance.monitor.collector.b
    void b() {
        super.b();
    }

    private void i() {
        int i2 = this.f9520c;
        if (i2 == 0 || i2 == 1) {
            this.s = 100;
            this.t = 300;
        } else if (i2 == 2 || i2 == 3) {
            this.s = 300;
            this.t = 200;
        }
    }

    public void startDispatchMonitor() {
        if (this.E) {
            return;
        }
        this.E = true;
        i();
        this.u = new f(this.s);
        com.bytedance.monitor.collector.a aVar = new com.bytedance.monitor.collector.a() { // from class: com.bytedance.monitor.collector.i.3
            @Override // com.bytedance.monitor.collector.a
            public boolean isValid() {
                return true;
            }

            @Override // com.bytedance.monitor.collector.a
            public void dispatchStart(String str) {
                i.this.H = true;
                i.this.B = str;
                super.dispatchStart(str);
                i.this.a(true, com.bytedance.monitor.collector.a.uptime);
            }

            @Override // com.bytedance.monitor.collector.a
            public void a(String str, Message message) {
                super.a(str, message);
                i.i(i.this);
                i.this.a(false, com.bytedance.monitor.collector.a.uptime);
                i iVar = i.this;
                iVar.A = iVar.B;
                i.this.B = i.NO_MESSAGE_RUNNING;
                i.this.H = false;
            }
        };
        this.C = aVar;
        j.register(aVar);
        l.getMessageObject(l.getMainMessageQueue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final long j2) {
        int i2 = this.r + 1;
        this.r = i2;
        this.r = i2 & 65535;
        this.F = false;
        if (this.w < 0) {
            this.w = j2;
        }
        if (this.x < 0) {
            this.x = j2;
        }
        if (this.y < 0) {
            this.y = Process.myTid();
            this.z = SystemClock.currentThreadTimeMillis();
        }
        com.bytedance.monitor.collector.service.c currentService = com.bytedance.monitor.collector.service.d.getCurrentService();
        com.bytedance.monitor.collector.service.d.clearCurrentService();
        int i3 = this.t;
        if (j2 - this.w > i3 || currentService != null) {
            long j3 = this.x;
            if (j2 - j3 <= i3 && currentService == null) {
                a(9, j2, this.B);
            } else if (z) {
                if (this.q == 0) {
                    a(1, j2, NO_MESSAGE_RUNNING);
                } else {
                    a(9, j3, this.A);
                    a(1, j2, NO_MESSAGE_RUNNING, false, null);
                }
            } else if (this.q == 0) {
                a(8, j2, this.B, true, currentService);
            } else {
                a(9, j3, this.A, false, null);
                a(8, j2, this.B, true, currentService);
            }
        }
        this.x = j2;
        final String str = this.B;
        if (this.f9521d) {
            final long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            o.getInstance().f9623b.post(new Runnable() { // from class: com.bytedance.monitor.collector.i.4
                @Override // java.lang.Runnable
                public void run() {
                    f.a logInstance = o.getInstance().getLogInstance();
                    if (logInstance == null) {
                        return;
                    }
                    if (z) {
                        logInstance.i(i.this.f9518a, j2 + "," + currentThreadTimeMillis + ",B|" + s.parseMessageKey(str));
                    } else {
                        logInstance.i(i.this.f9518a, j2 + "," + currentThreadTimeMillis + ",E");
                    }
                }
            });
        }
    }

    private void a(int i2, long j2, String str) {
        a(i2, j2, str, true, null);
    }

    private void a(int i2, long j2, String str, boolean z, com.bytedance.monitor.collector.service.c cVar) {
        this.F = true;
        e a2 = this.u.a(i2);
        a2.f9571e = j2 - this.w;
        if (z) {
            long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            a2.f9572f = currentThreadTimeMillis - this.z;
            this.z = currentThreadTimeMillis;
        } else {
            a2.f9572f = -1L;
        }
        a2.f9570d = this.q;
        a2.f9574h = str;
        a2.mLastScheduleMsg = this.A;
        a2.startTime = this.w;
        a2.f9567a = j2;
        a2.f9568b = this.x;
        if (cVar != null) {
            a2.f9573g = cVar;
        }
        this.u.a(a2);
        this.q = 0;
        this.w = j2;
    }

    public e getLastItem() {
        f fVar = this.u;
        if (fVar != null && this.F && fVar.a().f9569c == 8) {
            return this.u.a();
        }
        return null;
    }

    @Override // com.bytedance.monitor.collector.b
    void start() {
        super.start();
        startDispatchMonitor();
    }

    @Override // com.bytedance.monitor.collector.b
    Pair<String, ?> dumpInfo() {
        return new Pair<>(this.f9518a, dumpMessages());
    }

    @Override // com.bytedance.monitor.collector.b
    Pair<String, ?> dumpInfosRange(long j2, long j3) {
        try {
            return new Pair<>(this.f9518a, dumpMessages());
        } catch (Exception unused) {
            return null;
        }
    }

    public JSONObject dumpMessages() {
        long uptimeMillis = SystemClock.uptimeMillis();
        JSONObject jSONObject = new JSONObject();
        JSONArray dumpHistoryMsg = dumpHistoryMsg();
        JSONObject a2 = a(uptimeMillis);
        JSONArray a3 = a(100, uptimeMillis);
        try {
            jSONObject.put("history_message", dumpHistoryMsg);
            jSONObject.put("current_message", a2);
            jSONObject.put("pending_messages", a3);
            jSONObject.put("check_time_info", dumpCheckTime());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public List<e> dumpHistoryMsgItem() {
        f fVar = this.u;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    public JSONArray dumpCheckTime() {
        b bVar = this.v;
        if (bVar != null) {
            return bVar.c();
        }
        return null;
    }

    public JSONArray dumpHistoryMsg() {
        List<e> b2;
        JSONArray jSONArray = new JSONArray();
        try {
            b2 = this.u.b();
        } catch (Throwable unused) {
        }
        if (b2 == null) {
            return jSONArray;
        }
        int i2 = 0;
        for (e eVar : b2) {
            if (eVar != null) {
                i2++;
                jSONArray.put(eVar.toJson().put("id", i2));
            }
        }
        return jSONArray;
    }

    public e dumpCurrentMsgItem(long j2) {
        e eVar = new e();
        eVar.f9574h = this.B;
        eVar.mLastScheduleMsg = this.A;
        eVar.f9571e = j2 - this.x;
        eVar.f9572f = p.getCpuTime(this.y) - this.z;
        eVar.f9570d = this.q;
        return eVar;
    }

    private JSONObject a(long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", this.B);
            jSONObject.put("currentMessageCost", j2 - this.x);
            jSONObject.put("currentMessageCpu", p.getCpuTime(this.y) - this.z);
            jSONObject.put("messageCount", this.q);
            jSONObject.put("start", this.x);
            jSONObject.put("end", j2);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    private JSONArray a(int i2, long j2) {
        MessageQueue mainMessageQueue = l.getMainMessageQueue();
        JSONArray jSONArray = new JSONArray();
        if (mainMessageQueue == null) {
            return jSONArray;
        }
        try {
            synchronized (mainMessageQueue) {
                Message messageObject = l.getMessageObject(mainMessageQueue);
                if (messageObject == null) {
                    return jSONArray;
                }
                int i3 = 0;
                int i4 = 0;
                while (messageObject != null && i3 < i2) {
                    i3++;
                    i4++;
                    JSONObject a2 = a(messageObject, j2);
                    try {
                        a2.put("id", i4);
                    } catch (JSONException unused) {
                    }
                    jSONArray.put(a2);
                    messageObject = l.a(messageObject);
                }
                return jSONArray;
            }
        } catch (Throwable unused2) {
            return jSONArray;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        int f9577a;

        /* renamed from: b  reason: collision with root package name */
        int f9578b;

        /* renamed from: c  reason: collision with root package name */
        e f9579c;

        /* renamed from: d  reason: collision with root package name */
        List<e> f9580d = new ArrayList();

        f(int i2) {
            this.f9577a = i2;
        }

        e a(int i2) {
            e eVar = this.f9579c;
            if (eVar != null) {
                eVar.f9569c = i2;
                e eVar2 = this.f9579c;
                this.f9579c = null;
                return eVar2;
            }
            e eVar3 = new e();
            eVar3.f9569c = i2;
            return eVar3;
        }

        void a(e eVar) {
            int size = this.f9580d.size();
            int i2 = this.f9577a;
            if (size < i2) {
                this.f9580d.add(eVar);
                this.f9578b = this.f9580d.size();
            } else {
                int i3 = this.f9578b % i2;
                this.f9578b = i3;
                e eVar2 = this.f9580d.set(i3, eVar);
                eVar2.b();
                this.f9579c = eVar2;
                this.f9578b++;
            }
            if (com.bytedance.apm.logging.a.isFeedbackALogEnabled() && eVar.a()) {
                final String log = eVar.toLog();
                com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.monitor.collector.i.f.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.bytedance.apm.logging.a.feedbackI("block_looper_info", log);
                    }
                });
            }
        }

        e a() {
            int i2 = this.f9578b;
            if (i2 <= 0) {
                return null;
            }
            return this.f9580d.get(i2 - 1);
        }

        List<e> b() {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            if (this.f9580d.size() == this.f9577a) {
                for (int i3 = this.f9578b - 1; i3 < this.f9580d.size(); i3++) {
                    arrayList.add(this.f9580d.get(i3));
                }
                while (i2 < this.f9578b - 1) {
                    arrayList.add(this.f9580d.get(i2));
                    i2++;
                }
            } else {
                while (i2 < this.f9580d.size()) {
                    arrayList.add(this.f9580d.get(i2));
                    i2++;
                }
            }
            return arrayList;
        }
    }

    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        long f9567a;

        /* renamed from: b  reason: collision with root package name */
        long f9568b;

        /* renamed from: c  reason: collision with root package name */
        int f9569c;

        /* renamed from: d  reason: collision with root package name */
        int f9570d;

        /* renamed from: e  reason: collision with root package name */
        long f9571e;

        /* renamed from: f  reason: collision with root package name */
        long f9572f;

        /* renamed from: g  reason: collision with root package name */
        com.bytedance.monitor.collector.service.c f9573g;

        /* renamed from: h  reason: collision with root package name */
        String f9574h;

        /* renamed from: i  reason: collision with root package name */
        StackTraceElement[] f9575i;

        /* renamed from: j  reason: collision with root package name */
        StackTraceElement[] f9576j;
        String k;
        String l;
        d m;
        public String mLastScheduleMsg;
        public long startTime;

        public void updateBlockInfo(String str, StackTraceElement[] stackTraceElementArr, StackTraceElement[] stackTraceElementArr2, String str2) {
            if (!TextUtils.isEmpty(str)) {
                this.l = str;
            }
            if (stackTraceElementArr != null) {
                this.f9575i = stackTraceElementArr;
            }
            if (stackTraceElementArr2 != null) {
                this.f9576j = stackTraceElementArr2;
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.k = str2;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg", s.parseMessageKey(this.f9574h));
                jSONObject.put("cpuDuration", this.f9572f);
                jSONObject.put("duration", this.f9571e);
                jSONObject.put("type", this.f9569c);
                jSONObject.put("messageCount", this.f9570d);
                jSONObject.put("lastDuration", this.f9567a - this.f9568b);
                jSONObject.put("start", this.startTime);
                jSONObject.put("end", this.f9567a);
                a(jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        public String toLog() {
            return "msg:" + s.parseMessageKey(this.f9574h) + ",cpuDuration:" + this.f9572f + ",duration:" + this.f9571e + ",type:" + this.f9569c + ",messageCount:" + this.f9570d + ",lastDuration:" + (this.f9567a - this.f9568b) + ",start:" + this.startTime + ",end:" + this.f9567a;
        }

        boolean a() {
            int i2;
            return this.f9567a - this.f9568b > 17 || this.f9571e > 400 || (i2 = this.f9570d) > 300 || i2 < 20 || this.f9569c == 1 || this.f9572f < 20;
        }

        private void a(JSONObject jSONObject) {
            StackTraceElement[] stackTraceElementArr = this.f9575i;
            if (stackTraceElementArr != null) {
                jSONObject.put("block_stack", s.stackToString(stackTraceElementArr));
            }
            jSONObject.put("block_uuid", this.l);
            StackTraceElement[] stackTraceElementArr2 = this.f9576j;
            if (stackTraceElementArr2 != null) {
                jSONObject.put("sblock_stack", s.stackToString(stackTraceElementArr2));
            }
            jSONObject.put("sblock_uuid", this.l);
            if (TextUtils.isEmpty(this.k)) {
                jSONObject.put("evil_msg", this.k);
            }
            jSONObject.put("belong_frame", this.m != null);
            d dVar = this.m;
            if (dVar != null) {
                jSONObject.put("vsyncDelayTime", this.f9568b - (dVar.f9562a / 1000000));
                jSONObject.put("doFrameTime", (this.m.f9563b / 1000000) - this.f9568b);
                jSONObject.put("inputHandlingTime", (this.m.f9564c / 1000000) - (this.m.f9563b / 1000000));
                jSONObject.put("animationsTime", (this.m.f9565d / 1000000) - (this.m.f9564c / 1000000));
                jSONObject.put("performTraversalsTime", (this.m.f9566e / 1000000) - (this.m.f9565d / 1000000));
                jSONObject.put("drawTime", this.f9567a - (this.m.f9566e / 1000000));
            }
            com.bytedance.monitor.collector.service.c cVar = this.f9573g;
            if (cVar != null) {
                jSONObject.put("service_name", cVar.mServiceName);
                jSONObject.put("service_what", this.f9573g.mWhat);
                jSONObject.put("service_time", this.f9573g.mTimeStamp);
                jSONObject.put("service_thread", this.f9573g.mThreadName);
                jSONObject.put("service_token", this.f9573g.mToken);
            }
        }

        void b() {
            this.f9569c = -1;
            this.f9570d = -1;
            this.f9571e = -1L;
            this.f9574h = null;
            this.f9575i = null;
            this.f9576j = null;
            this.k = null;
            this.l = null;
            this.m = null;
            this.f9573g = null;
            this.mLastScheduleMsg = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        final int f9558a;

        /* renamed from: b  reason: collision with root package name */
        a f9559b;

        /* renamed from: c  reason: collision with root package name */
        final List<a> f9560c;

        /* renamed from: d  reason: collision with root package name */
        private int f9561d = 0;

        public b(int i2) {
            this.f9558a = i2;
            this.f9560c = new ArrayList(i2);
        }

        a a() {
            a aVar = this.f9559b;
            if (aVar != null) {
                this.f9559b = null;
                return aVar;
            }
            return new a();
        }

        void a(a aVar) {
            int size = this.f9560c.size();
            int i2 = this.f9558a;
            if (size < i2) {
                this.f9560c.add(aVar);
                this.f9561d = this.f9560c.size();
                return;
            }
            int i3 = this.f9561d % i2;
            this.f9561d = i3;
            a aVar2 = this.f9560c.set(i3, aVar);
            aVar2.a();
            this.f9559b = aVar2;
            this.f9561d++;
        }

        a b() {
            int i2 = this.f9561d;
            if (i2 > 0) {
                return this.f9560c.get(i2 - 1);
            }
            if (i2 == 0) {
                List<a> list = this.f9560c;
                return list.get(list.size() - 1);
            }
            return null;
        }

        JSONArray c() {
            JSONArray jSONArray = new JSONArray();
            int i2 = 0;
            if (this.f9560c.size() == this.f9558a) {
                for (int i3 = this.f9561d - 1; i3 < this.f9560c.size(); i3++) {
                    jSONArray.put(this.f9560c.get(i3).toJson());
                }
                while (i2 < this.f9561d - 1) {
                    jSONArray.put(this.f9560c.get(i2).toJson());
                    i2++;
                }
            } else {
                while (i2 < this.f9560c.size()) {
                    jSONArray.put(this.f9560c.get(i2).toJson());
                    i2++;
                }
            }
            return jSONArray;
        }

        List<a> d() {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            if (this.f9560c.size() == this.f9558a) {
                for (int i3 = this.f9561d - 1; i3 < this.f9560c.size(); i3++) {
                    arrayList.add(this.f9560c.get(i3).b());
                }
                while (i2 < this.f9561d - 1) {
                    arrayList.add(this.f9560c.get(i2).b());
                    i2++;
                }
            } else {
                while (i2 < this.f9560c.size()) {
                    arrayList.add(this.f9560c.get(i2).b());
                    i2++;
                }
            }
            return arrayList;
        }
    }

    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    private static class g {

        /* renamed from: a  reason: collision with root package name */
        StackTraceElement f9583a;

        /* renamed from: b  reason: collision with root package name */
        boolean f9584b;

        /* renamed from: c  reason: collision with root package name */
        long f9585c;

        private g() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LooperDispatchMonitor.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        long f9552a;

        /* renamed from: b  reason: collision with root package name */
        long f9553b;

        /* renamed from: c  reason: collision with root package name */
        long f9554c;

        /* renamed from: d  reason: collision with root package name */
        boolean f9555d;

        /* renamed from: e  reason: collision with root package name */
        int f9556e;

        /* renamed from: f  reason: collision with root package name */
        String f9557f;

        private a() {
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("startTime", this.f9552a);
                jSONObject.put("cost", this.f9553b);
                jSONObject.put(com.bytedance.apm.constant.k.RESPONSE_DELAY, this.f9554c);
                jSONObject.put("isMessage", String.valueOf(this.f9555d));
                jSONObject.put("seqNum", this.f9556e);
                jSONObject.put("stack", this.f9557f);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        public String toLog() {
            StringBuilder sb = new StringBuilder();
            sb.append("startTime:").append(this.f9552a).append(",cost:").append(this.f9553b).append(",delay:").append(this.f9554c).append(",belongMessage:").append(this.f9555d).append(",flag:").append(this.f9556e);
            return sb.toString();
        }

        void a() {
            this.f9552a = -1L;
            this.f9553b = -1L;
            this.f9554c = -1L;
            this.f9556e = -1;
            this.f9557f = null;
        }

        a b() {
            a aVar = new a();
            aVar.f9552a = this.f9552a;
            aVar.f9553b = this.f9553b;
            aVar.f9554c = this.f9554c;
            aVar.f9555d = this.f9555d;
            aVar.f9556e = this.f9556e;
            aVar.f9557f = this.f9557f;
            return aVar;
        }
    }

    private static JSONObject a(Message message, long j2) {
        JSONObject jSONObject = new JSONObject();
        if (message == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("when", message.getWhen() - j2);
            if (message.getCallback() != null) {
                jSONObject.put(com.bytedance.bdturing.methods.l.VALUE_CALLBACK, String.valueOf(message.getCallback()));
            }
            jSONObject.put(ReactVideoView.EVENT_PROP_WHAT, message.what);
            if (message.getTarget() != null) {
                jSONObject.put("target", String.valueOf(message.getTarget()));
            } else {
                jSONObject.put("barrier", message.arg1);
            }
            jSONObject.put("arg1", message.arg1);
            jSONObject.put("arg2", message.arg2);
            if (message.obj != null) {
                jSONObject.put("obj", String.valueOf(message.obj));
            }
            jSONObject.put("start", message.getWhen());
            jSONObject.put("end", -1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
