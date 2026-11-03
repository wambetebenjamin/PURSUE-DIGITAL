package com.bytedance.router.route;

import android.content.Context;
import android.os.Bundle;
/* compiled from: IRoute.java */
/* loaded from: classes2.dex */
public interface d {
    String getHost();

    Bundle getParams();

    String getPath();

    String getScheme();

    String getUrl();

    void open(Context context);
}
