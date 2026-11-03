package com.bytedance.router.interceptor;

import android.content.Context;
import com.bytedance.router.c;
/* compiled from: IInterceptor.java */
/* loaded from: classes2.dex */
public interface a {
    boolean matchInterceptRules(c cVar);

    boolean onInterceptRoute(Context context, c cVar);
}
