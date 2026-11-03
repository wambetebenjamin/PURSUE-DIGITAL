package com.bytedance.crash.crash;

import java.util.Iterator;
import java.util.Objects;
/* compiled from: D8$$SyntheticClass */
/* loaded from: classes2.dex */
public final /* synthetic */ class NativeCrashSummary$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(CharSequence charSequence, Iterable iterable) {
        Objects.requireNonNull(charSequence, "delimiter");
        StringBuilder sb = new StringBuilder();
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            while (true) {
                sb.append((CharSequence) it.next());
                if (!it.hasNext()) {
                    break;
                }
                sb.append(charSequence);
            }
        }
        return sb.toString();
    }
}
