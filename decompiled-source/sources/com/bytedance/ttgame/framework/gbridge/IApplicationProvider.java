package com.bytedance.ttgame.framework.gbridge;

import android.app.Activity;
import android.app.Application;
/* loaded from: classes3.dex */
public interface IApplicationProvider {
    Application getApplication();

    Activity getCurrentActivity();
}
