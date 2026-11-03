package com.bytedance.apm.entity;

import java.util.List;
/* compiled from: UploadLogLegacyCommand.java */
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private List<String> f1050a;
    public long endTime;
    public long startTime;

    public m(long j2, long j3, List<String> list) {
        this.startTime = j2;
        this.endTime = j3;
        this.f1050a = list;
    }

    public List<String> getUploadTypeList() {
        return this.f1050a;
    }
}
