package com.bytedance.apm.trace.model.wrapper;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
/* compiled from: TracingWrapperRecord.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private Deque<com.bytedance.apm.trace.api.b> f1633a;

    /* renamed from: b  reason: collision with root package name */
    private Map<Long, com.bytedance.apm.trace.api.b> f1634b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.apm.trace.api.b f1635c;

    public void startTrace() {
        this.f1633a = new LinkedList();
        this.f1634b = new LinkedHashMap();
    }

    public void endTrace() {
        this.f1633a.clear();
        this.f1634b.clear();
        this.f1635c = null;
    }

    public void startSpan(com.bytedance.apm.trace.api.b bVar) {
        if (this.f1635c == null) {
            this.f1635c = bVar;
        } else if (!this.f1633a.isEmpty()) {
            long spanId = this.f1633a.peek().getSpanId();
            bVar.setParentId(spanId);
            com.bytedance.apm.trace.api.b bVar2 = this.f1634b.get(Long.valueOf(spanId));
            if (bVar2 != null) {
                bVar.setReferenceId(bVar2.getSpanId());
            }
            this.f1634b.put(Long.valueOf(spanId), bVar);
        } else {
            bVar.setReferenceId(this.f1635c.getSpanId());
        }
        this.f1633a.push(bVar);
    }

    public void endSpan() {
        com.bytedance.apm.trace.api.b poll = this.f1633a.poll();
        if (poll != null) {
            this.f1635c = poll;
            this.f1634b.remove(Long.valueOf(poll.getSpanId()));
        }
    }

    public com.bytedance.apm.trace.api.b getCurrentTopSpan() {
        return this.f1633a.peek();
    }

    public com.bytedance.apm.trace.api.b getCurrentPopSpan() {
        return this.f1635c;
    }
}
