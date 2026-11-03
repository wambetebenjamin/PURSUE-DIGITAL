package com.ss.android.agilelogger.formatter.message;

import com.ss.android.agilelogger.formatter.b;
import java.util.Date;
/* compiled from: DefaultDateFormatter.java */
/* loaded from: classes9.dex */
public class a implements b<String> {

    /* renamed from: a  reason: collision with root package name */
    private Date f18005a;

    @Override // com.ss.android.agilelogger.formatter.b
    public String format(String str) {
        return str;
    }

    public a() {
        this("MM-dd HH:mm:ss");
    }

    public a(String str) {
        this.f18005a = new Date();
    }
}
