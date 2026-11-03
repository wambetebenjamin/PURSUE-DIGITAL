package com.bytedance.apm.agent.tracing;
/* compiled from: PageTraceEntity.java */
/* loaded from: classes.dex */
public class c {
    public long onCreateEndTs;
    public long onCreateStartTs;
    public long onResumeEndTs;
    public long onResumeStartTs;
    public long onViewShowTs;
    public long onWindowFocusTs;
    public String pageName;

    public c(String str, long j2) {
        this.pageName = str;
        this.onCreateStartTs = j2;
    }

    public boolean isCreateAndResumeValid() {
        return this.onCreateStartTs > 0 && this.onCreateEndTs > 0 && this.onResumeStartTs > 0 && this.onResumeEndTs > 0;
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String str) {
        this.pageName = str;
    }

    public long getOnCreateStartTs() {
        return this.onCreateStartTs;
    }

    public void setOnCreateStartTs(long j2) {
        this.onCreateStartTs = j2;
    }

    public long getOnCreateEndTs() {
        return this.onCreateEndTs;
    }

    public void setOnCreateEndTs(long j2) {
        this.onCreateEndTs = j2;
    }

    public long getOnResumeStartTs() {
        return this.onResumeStartTs;
    }

    public void setOnResumeStartTs(long j2) {
        this.onResumeStartTs = j2;
    }

    public long getOnResumeEndTs() {
        return this.onResumeEndTs;
    }

    public void setOnResumeEndTs(long j2) {
        this.onResumeEndTs = j2;
    }

    public long getOnWindowFocusTs() {
        return this.onWindowFocusTs;
    }

    public void setOnWindowFocusTs(long j2) {
        this.onWindowFocusTs = j2;
    }
}
