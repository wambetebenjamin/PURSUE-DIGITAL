package com.bytedance.services.apm.api;

import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ILooperMessageDumper.java */
/* loaded from: classes3.dex */
public interface h {
    JSONObject dumpDispatchingMessage();

    JSONArray dumpHistoryMessages();

    JSONObject dumpMessages();

    JSONArray dumpPendingMessages();
}
