package com.bytedance.ttgame.library.luffy.core;

import kotlin.Metadata;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: IRemoteData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0010H&J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/IRemoteData;", "", "optBoolean", "", "key", "", "fallback", "optDouble", "", "optInt", "", "optJsonArray", "Lorg/json/JSONArray;", "optJsonObject", "Lorg/json/JSONObject;", "optLong", "", "optString", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface f {
    boolean optBoolean(String str, boolean z);

    double optDouble(String str, double d2);

    int optInt(String str, int i2);

    JSONArray optJsonArray(String str);

    JSONObject optJsonObject(String str);

    long optLong(String str, long j2);

    String optString(String str, String str2);
}
