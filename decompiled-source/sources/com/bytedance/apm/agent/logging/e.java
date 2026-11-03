package com.bytedance.apm.agent.logging;
/* compiled from: DefaultAgentLog.java */
/* loaded from: classes.dex */
public class e implements a {

    /* renamed from: a  reason: collision with root package name */
    private a f306a = new f();

    public void setImpl(a aVar) {
        synchronized (this) {
            this.f306a = aVar;
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void audit(String str) {
        synchronized (this) {
            this.f306a.audit(str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void debug(String str) {
        synchronized (this) {
            this.f306a.debug(str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void info(String str) {
        synchronized (this) {
            this.f306a.info(str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void verbose(String str) {
        synchronized (this) {
            this.f306a.verbose(str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void warning(String str) {
        synchronized (this) {
            this.f306a.warning(str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void error(String str) {
        synchronized (this) {
            this.f306a.error(str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void error(String str, Throwable th) {
        synchronized (this) {
            this.f306a.error(str, th);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public int getLevel() {
        int level;
        synchronized (this) {
            level = this.f306a.getLevel();
        }
        return level;
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void setLevel(int i2) {
        synchronized (this) {
            this.f306a.setLevel(i2);
        }
    }
}
