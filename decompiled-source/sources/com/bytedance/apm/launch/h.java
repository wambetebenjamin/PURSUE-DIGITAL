package com.bytedance.apm.launch;

import android.os.Looper;
import android.text.TextUtils;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
/* compiled from: LaunchPerfDataFetcher.java */
/* loaded from: classes.dex */
public class h {
    /* JADX WARN: Can't wrap try/catch for region: R(14:2|3|4|5|6|(4:8|9|(3:11|(3:45|46|47)(3:13|14|(3:42|43|44)(3:16|17|(3:39|40|41)(3:19|20|(3:36|37|38)(3:22|23|(3:33|34|35)(3:25|26|(3:28|29|30)(1:32))))))|31)(0)|48)|49|(2:50|51)|52|53|(1:55)|57|58|(2:62|63)) */
    /* JADX WARN: Can't wrap try/catch for region: R(2:(3:8|9|(3:11|(3:45|46|47)(3:13|14|(3:42|43|44)(3:16|17|(3:39|40|41)(3:19|20|(3:36|37|38)(3:22|23|(3:33|34|35)(3:25|26|(3:28|29|30)(1:32))))))|31)(0))|48) */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0131 A[Catch: all -> 0x0165, TRY_LEAVE, TryCatch #1 {all -> 0x0165, blocks: (B:34:0x012b, B:36:0x0131), top: B:53:0x012b }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x016a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedance.apm.launch.h.a getLaunchTraceData() {
        /*
            Method dump skipped, instructions count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.apm.launch.h.getLaunchTraceData():com.bytedance.apm.launch.h$a");
    }

    private static void a(a aVar) {
        int i2;
        ThreadGroup threadGroup;
        try {
            i2 = new File("/proc/self/task/").listFiles().length;
        } catch (Throwable unused) {
            i2 = 0;
        }
        if (i2 == 0 || (threadGroup = Looper.getMainLooper().getThread().getThreadGroup()) == null) {
            return;
        }
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        int activeCount = threadGroup.activeCount();
        Thread[] threadArr = new Thread[activeCount + (activeCount / 2)];
        int enumerate = threadGroup.enumerate(threadArr);
        LinkedList linkedList = new LinkedList();
        for (int i3 = 0; i3 < enumerate; i3++) {
            String name = threadArr[i3].getName();
            if (!TextUtils.isEmpty(name)) {
                linkedList.add(name);
            }
        }
        aVar.o = i2;
        aVar.p = linkedList;
    }

    /* compiled from: LaunchPerfDataFetcher.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private long f1200a = -1;

        /* renamed from: b  reason: collision with root package name */
        private long f1201b = -1;

        /* renamed from: c  reason: collision with root package name */
        private long f1202c = -1;

        /* renamed from: d  reason: collision with root package name */
        private long f1203d = -1;

        /* renamed from: e  reason: collision with root package name */
        private double f1204e = -1.0d;

        /* renamed from: f  reason: collision with root package name */
        private long f1205f = -1;

        /* renamed from: g  reason: collision with root package name */
        private long f1206g = -1;

        /* renamed from: h  reason: collision with root package name */
        private double f1207h = -1.0d;

        /* renamed from: i  reason: collision with root package name */
        private double f1208i = -1.0d;

        /* renamed from: j  reason: collision with root package name */
        private double f1209j = -1.0d;
        private long k = -1;
        private long l = -1;
        private long m = -1;
        private long n = -1;
        private long o = -1;
        private List<String> p = null;

        public List<String> getJavaThreadNameList() {
            return this.p;
        }

        public long getGcCount() {
            return this.f1200a;
        }

        public long getGcTime() {
            return this.f1201b;
        }

        public long getBlockGcCount() {
            return this.f1202c;
        }

        public long getBlockGcTime() {
            return this.f1203d;
        }

        public double getIowaitTime() {
            return this.f1207h;
        }

        public double getRunnableTime() {
            return this.f1208i;
        }

        public double getSleepTime() {
            return this.f1209j;
        }

        public long getMinorFault() {
            return this.m;
        }

        public long getMajorFault() {
            return this.n;
        }

        public long getAllThreadCount() {
            return this.o;
        }

        public double getCpuTime() {
            return this.f1204e;
        }

        public long getVoluntarySwitches() {
            return this.f1205f;
        }

        public long getInVoluntarySwitches() {
            return this.f1206g;
        }

        public long getLockTime() {
            return this.k;
        }

        public void setLockTime(long j2) {
            this.k = j2;
        }

        public long getBinderTime() {
            return this.l;
        }

        public void setBinderTime(long j2) {
            this.l = j2;
        }

        public String toString() {
            return "LaunchTraceData{gcCount=" + this.f1200a + ", gcTime=" + this.f1201b + ", blockGcCount=" + this.f1202c + ", blockGcTime=" + this.f1203d + ", cpuTime=" + this.f1204e + ", voluntarySwitches=" + this.f1205f + ", inVoluntarySwitches=" + this.f1206g + ", iowaitTime=" + this.f1207h + ", runnableTime=" + this.f1208i + ", sleepTime=" + this.f1209j + ", minorFault=" + this.m + ", majorFault=" + this.n + ", allThreadCount=" + this.o + ", javaThreadNameList=" + this.p + ", lockTime=" + this.k + ", binderTime=" + this.l + '}';
        }
    }
}
