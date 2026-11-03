package com.bytedance.apm6.cpu;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.apm6.cpu.collect.f;
import com.bytedance.apm6.perf.base.e;
import com.bytedance.apm6.service.perf.a;
import com.bytedance.apm6.util.g;
import com.bytedance.common.utility.l;
import com.bytedance.monitor.collector.o;
import com.bytedance.watson.assist.api.c;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: ApmCpuManager.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f1941b;

    /* renamed from: a  reason: collision with root package name */
    private CopyOnWriteArraySet<String> f1942a = new CopyOnWriteArraySet<>();

    /* compiled from: ApmCpuManager.java */
    /* renamed from: com.bytedance.apm6.cpu.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0030a {
        void onCpuData(double d2, double d3, String str, c.a aVar, l.b bVar);
    }

    /* compiled from: ApmCpuManager.java */
    /* loaded from: classes.dex */
    public interface b {
        boolean needFilter();
    }

    /* compiled from: ApmCpuManager.java */
    /* loaded from: classes.dex */
    public interface c {
        void onException(double d2);
    }

    private a() {
    }

    public static a getInstance() {
        if (f1941b == null) {
            synchronized (a.class) {
                if (f1941b == null) {
                    f1941b = new a();
                }
            }
        }
        return f1941b;
    }

    public void startScene(String str) {
        this.f1942a.add(str);
        e.getInstance().startScene(str);
    }

    public void stopScene(String str) {
        this.f1942a.remove(str);
        e.getInstance().stopScene(str);
    }

    public void startUsageDetect(String str) {
        f.startCollect(str);
    }

    public void stopUsageDetect(String str, boolean z) {
        f.stopCollect(str, z);
    }

    public void startExceptionDetectNoStack() {
        if (com.bytedance.apm.c.isInitFinish()) {
            com.bytedance.apm6.cpu.exception.c.getInstance().forceOpenNoStack();
        }
    }

    public void stopExceptionDetectNoStack() {
        if (com.bytedance.apm.c.isInitFinish()) {
            com.bytedance.apm6.cpu.exception.c.getInstance().stopForceOpenNoStack();
        }
    }

    public void setCpuExceptionFilter(b bVar) {
        com.bytedance.apm6.cpu.exception.c.getInstance().setCpuFilterListener(bVar);
    }

    @Deprecated
    public void tmpStartExceptionDetect() {
        com.bytedance.apm6.cpu.exception.c.getInstance().forceOpen();
    }

    public long getLastCpuExceptionTimestamp() {
        return com.bytedance.apm6.cpu.exception.c.getInstance().getLastExceptionTimestamp();
    }

    public void setExceptionListener(c cVar) {
        com.bytedance.apm6.cpu.exception.c.getInstance().setExceptionListener(cVar);
    }

    public void setCpuDataListener(InterfaceC0030a interfaceC0030a) {
        com.bytedance.apm6.cpu.collect.b.getInstance().setDataListener(interfaceC0030a);
    }

    public double getCpuRate() {
        return com.bytedance.apm6.cpu.service.a.getInstance().getCpuRate();
    }

    public double getCpuSpeed() {
        return com.bytedance.apm6.cpu.service.a.getInstance().getCpuSpeed();
    }

    public com.bytedance.apm.perf.entity.a getCurrentCpuRate() {
        return a();
    }

    public com.bytedance.apm.perf.entity.a a() {
        int cpuCoreNum;
        com.bytedance.apm.perf.entity.a aVar = new com.bytedance.apm.perf.entity.a();
        try {
            cpuCoreNum = com.bytedance.apm.util.d.getCpuCoreNum();
        } catch (Exception unused) {
        }
        if (cpuCoreNum <= 0) {
            return aVar;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long i2 = o.getInstance().i();
        long a2 = o.getInstance().a(cpuCoreNum);
        try {
            Thread.sleep(360L);
        } catch (InterruptedException unused2) {
        }
        long i3 = o.getInstance().i();
        long a3 = o.getInstance().a(cpuCoreNum) - a2;
        aVar.cpuAppRate = a3 > 0 ? (((float) i3) - ((float) i2)) / ((float) a3) : -1.0d;
        aVar.cpuAppSpeed = (((i3 - i2) * 1000.0d) / (System.currentTimeMillis() - currentTimeMillis)) / com.bytedance.apm.util.d.getScClkTck(100L);
        return aVar;
    }

    public Pair<Long, LinkedList<a.C0033a>> getThreadList() {
        return com.bytedance.apm6.cpu.service.a.getInstance().getThreadList();
    }

    public Pair<Long, LinkedList<a.C0033a>> getExceptionThreadList() {
        return com.bytedance.apm6.cpu.service.a.getInstance().getExceptionThreadList();
    }

    public String getCpuSceneString() {
        String arrayToString = g.arrayToString(this.f1942a.toArray(), "#");
        return !TextUtils.isEmpty(arrayToString) ? arrayToString : "";
    }

    public CopyOnWriteArraySet<String> getCpuSceneSet() {
        return this.f1942a;
    }
}
