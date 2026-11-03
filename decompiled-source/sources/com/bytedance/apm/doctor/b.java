package com.bytedance.apm.doctor;

import android.text.TextUtils;
import com.ss.android.common.util.d;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DoctorDataUtil.java */
/* loaded from: classes.dex */
public class b {
    public static final String KEY_DATA = "data";
    public static final String KEY_LIST = "list";

    /* renamed from: a  reason: collision with root package name */
    private static AtomicInteger f1010a = new AtomicInteger(0);

    public static void onReceivedEvent(String str, JSONObject jSONObject, boolean z) {
        JSONObject optJSONObject = jSONObject.optJSONObject(a.DATA_DOCTOR);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        try {
            if (optJSONObject.optInt(a.DATA_ID, -1) == -1) {
                optJSONObject.put(a.DATA_ID, f1010a.incrementAndGet());
            }
            optJSONObject.put(a.DATA_PROCESS, d.getCurProcessName(com.bytedance.apm6.foundation.context.a.getContext()));
            optJSONObject.put(a.DATA_TYPE, str);
            optJSONObject.put(a.DATA_SAMPLE, z);
            optJSONObject.put(a.DATA_SAVE_DB_IMMEDIATE, false);
            optJSONObject.put(a.DATA_UPLOAD_IMMEDIATE, false);
            optJSONObject.put(a.f1008d, com.bytedance.apm6.foundation.context.a.getAid());
            jSONObject.put(a.DATA_DOCTOR, optJSONObject);
            JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
            c.getInstance().onDataEvent(a.DATA_RECEIVE, jSONObject2);
            c.getInstance().onDataEvent(a.DATA_CACHE, jSONObject2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void saveToFileEvent(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            c.getInstance().onDataEvent(a.DATA_SAVE_TO_DB, new JSONObject(jSONObject.toString()));
        } catch (Exception unused) {
        }
    }

    public static void sendBeginEvent(byte[] bArr) {
        List<JSONObject> a2 = a(bArr);
        if (a2 == null) {
            return;
        }
        for (JSONObject jSONObject : a2) {
            a(jSONObject, a.DATA_SEND_BEGIN);
        }
    }

    public static void sendSuccessEvent(byte[] bArr) {
        for (JSONObject jSONObject : a(bArr)) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(a.DATA_DOCTOR);
                if (optJSONObject != null) {
                    optJSONObject.put(a.DATA_SEND_RESULT, 200);
                }
            } catch (Exception unused) {
            }
            a(jSONObject, a.DATA_SEND_SUCCESS);
            a(jSONObject, a.DATA_SEND_END);
        }
    }

    public static void sendFailEvent(byte[] bArr) {
        for (JSONObject jSONObject : a(bArr)) {
            a(jSONObject, a.DATA_SEND_FAIL);
            a(jSONObject, a.DATA_SEND_END);
        }
    }

    public static void a(byte[] bArr, JSONObject jSONObject) {
        for (JSONObject jSONObject2 : a(bArr)) {
            try {
                JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
                JSONObject optJSONObject = jSONObject2.optJSONObject(a.DATA_DOCTOR);
                if (optJSONObject != null) {
                    jSONObject3.put(a.DATA_DOCTOR, optJSONObject);
                }
                a(jSONObject3, a.f1009e);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return;
        }
        try {
            c.getInstance().onDataEvent(str, jSONObject);
        } catch (Exception unused) {
        }
    }

    private static List<JSONObject> a(byte[] bArr) {
        JSONArray optJSONArray;
        if (bArr == null) {
            return null;
        }
        String str = new String(bArr);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("list")) {
                JSONArray optJSONArray2 = jSONObject.optJSONArray("list");
                if (optJSONArray2 == null) {
                    return null;
                }
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    JSONObject jSONObject2 = optJSONArray2.getJSONObject(i2);
                    if (jSONObject2 != null && (optJSONArray = jSONObject2.optJSONArray("data")) != null) {
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            arrayList.add(optJSONArray.getJSONObject(i3));
                        }
                    }
                }
            } else {
                JSONArray optJSONArray3 = jSONObject.optJSONArray("data");
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    arrayList.add(optJSONArray3.getJSONObject(i4));
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }
}
