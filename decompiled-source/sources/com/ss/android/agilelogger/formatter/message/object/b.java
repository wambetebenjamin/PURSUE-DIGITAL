package com.ss.android.agilelogger.formatter.message.object;

import android.content.Intent;
import com.ss.android.agilelogger.utils.d;
/* compiled from: IntentFormatter.java */
/* loaded from: classes9.dex */
public class b implements c<Intent> {
    @Override // com.ss.android.agilelogger.formatter.b
    public String format(Intent intent) {
        return d.intentToString(intent);
    }
}
