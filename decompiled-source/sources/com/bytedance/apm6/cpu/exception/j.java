package com.bytedance.apm6.cpu.exception;

import com.bytedance.librarian.c;
import java.util.Objects;
/* compiled from: ThreadExceptionItem.java */
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private int f2065a;

    /* renamed from: b  reason: collision with root package name */
    private String f2066b;

    /* renamed from: c  reason: collision with root package name */
    private long f2067c;

    /* renamed from: d  reason: collision with root package name */
    private double f2068d;

    /* renamed from: e  reason: collision with root package name */
    private String f2069e;

    /* renamed from: f  reason: collision with root package name */
    private String f2070f;

    /* renamed from: g  reason: collision with root package name */
    private StackTraceElement f2071g;

    /* renamed from: h  reason: collision with root package name */
    private long f2072h;

    /* renamed from: i  reason: collision with root package name */
    private int f2073i;

    public StackTraceElement getFirstElement() {
        return this.f2071g;
    }

    public void setFirstElement(StackTraceElement stackTraceElement) {
        this.f2071g = stackTraceElement;
    }

    public int getPriority() {
        return this.f2073i;
    }

    public void setPriority(int i2) {
        this.f2073i = i2;
    }

    public String getWeight() {
        return this.f2069e;
    }

    public void setWeight(String str) {
        this.f2069e = str;
    }

    public long getProcessCpuTime() {
        return this.f2072h;
    }

    public void setProcessCpuTime(long j2) {
        this.f2072h = j2;
    }

    public String getStack() {
        return this.f2070f;
    }

    public void setStack(String str) {
        this.f2070f = str;
    }

    public double getCpuUsage() {
        return this.f2068d;
    }

    public void setCpuUsage(double d2) {
        this.f2068d = d2;
    }

    public int getThreadId() {
        return this.f2065a;
    }

    public void setThreadId(int i2) {
        this.f2065a = i2;
    }

    public String getThreadName() {
        return this.f2066b;
    }

    public void setThreadName(String str) {
        this.f2066b = str;
    }

    public long getThreadCpuTime() {
        return this.f2067c;
    }

    public void setThreadCpuTime(long j2) {
        this.f2067c = j2;
    }

    public String getKey() {
        return this.f2066b + c.a.SEPARATOR + this.f2065a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            return getThreadId() == jVar.getThreadId() && getThreadName().equals(jVar.getThreadName());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getThreadId()), getThreadName());
    }

    public String toString() {
        return "ThreadExceptionItem{threadId=" + this.f2065a + ", threadName='" + this.f2066b + "', threadCpuTime=" + this.f2067c + ", processCpuTime=" + this.f2072h + ", cpuUsage=" + this.f2068d + ", weight=" + this.f2069e + ", nice=" + this.f2073i + '}';
    }
}
