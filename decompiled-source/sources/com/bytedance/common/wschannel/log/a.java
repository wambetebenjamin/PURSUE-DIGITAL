package com.bytedance.common.wschannel.log;

import android.content.Context;
import android.os.Bundle;
import com.ss.android.message.log.c;
/* compiled from: WsChannelLog.java */
/* loaded from: classes2.dex */
public class a {
    public static void a(Context context, String str, Bundle bundle) {
        try {
            c.onEventV3Bundle(context, str, bundle);
        } catch (Throwable unused) {
        }
    }
}
