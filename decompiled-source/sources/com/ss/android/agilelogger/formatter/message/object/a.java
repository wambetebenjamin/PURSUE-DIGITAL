package com.ss.android.agilelogger.formatter.message.object;

import android.os.Bundle;
import com.ss.android.agilelogger.utils.d;
/* compiled from: BundleFormatter.java */
/* loaded from: classes9.dex */
public class a implements c<Bundle> {
    @Override // com.ss.android.agilelogger.formatter.b
    public String format(Bundle bundle) {
        return d.bundleToString(bundle);
    }
}
