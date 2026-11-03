package com.bytedance.push.alive;

import android.content.Context;
import android.os.Handler;
/* compiled from: IAlive.java */
/* loaded from: classes2.dex */
public interface b {
    void a(Context context, boolean z);

    boolean checkConfig(Context context, String str);

    void doKeepAlive(Context context, Handler handler);

    boolean isSsWoFinished();
}
