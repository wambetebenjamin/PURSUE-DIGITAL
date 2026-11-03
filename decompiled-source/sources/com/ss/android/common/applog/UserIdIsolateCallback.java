package com.ss.android.common.applog;

import java.util.Map;
/* loaded from: classes9.dex */
public interface UserIdIsolateCallback {
    void afterIsolate();

    boolean allowIsolateDataReport(String str);

    void beforeIsolate();

    Map<String, String> generateIsolateHttpHeader();
}
