package com.bytedance.notification.supporter.impl;

import java.util.HashSet;
import java.util.Set;
/* compiled from: NotificationClickServiceImpl.java */
/* loaded from: classes2.dex */
public class c implements com.bytedance.notification.supporter.service.c {

    /* renamed from: a  reason: collision with root package name */
    private Set<Long> f9936a = new HashSet();

    @Override // com.bytedance.notification.supporter.service.c
    public void a(long j2) {
        this.f9936a.add(Long.valueOf(j2));
    }

    @Override // com.bytedance.notification.supporter.service.c
    public boolean b(long j2) {
        return this.f9936a.contains(Long.valueOf(j2));
    }
}
