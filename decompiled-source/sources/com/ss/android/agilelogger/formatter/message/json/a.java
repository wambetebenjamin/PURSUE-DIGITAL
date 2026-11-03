package com.ss.android.agilelogger.formatter.message.json;

import com.meituan.robust.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: DefaultJsonFormatter.java */
/* loaded from: classes9.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18006a = 4;

    @Override // com.ss.android.agilelogger.formatter.b
    public String format(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new com.ss.android.agilelogger.formatter.a("JSON empty.");
        }
        try {
            if (str.startsWith("{")) {
                return new JSONObject(str).toString(4);
            }
            if (str.startsWith(Constants.ARRAY_TYPE)) {
                return new JSONArray(str).toString(4);
            }
            throw new com.ss.android.agilelogger.formatter.a("JSON should start with { or [, but found " + str);
        } catch (Exception e2) {
            throw new com.ss.android.agilelogger.formatter.a("Parse JSON error. JSON string:" + str, e2);
        }
    }
}
