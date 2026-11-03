package com.bytedance.applog.isolate;

import java.util.Map;
/* compiled from: IDataIsolateCallback.java */
/* loaded from: classes.dex */
public interface c {
    boolean afterIsolate();

    boolean allowIsolateDataReport(a aVar, String str);

    boolean beforeIsolate();

    Map<String, String> generateIsolateHttpHeader();
}
