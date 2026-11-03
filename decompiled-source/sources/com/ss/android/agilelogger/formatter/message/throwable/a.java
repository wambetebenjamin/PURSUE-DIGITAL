package com.ss.android.agilelogger.formatter.message.throwable;

import com.ss.android.agilelogger.utils.e;
/* compiled from: DefaultThrowableFormatter.java */
/* loaded from: classes9.dex */
public class a implements b {
    @Override // com.ss.android.agilelogger.formatter.b
    public String format(Throwable th) {
        return e.getStackTraceString(th);
    }
}
