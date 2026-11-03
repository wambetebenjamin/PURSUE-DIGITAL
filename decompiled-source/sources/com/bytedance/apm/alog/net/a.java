package com.bytedance.apm.alog.net;

import android.text.TextUtils;
import com.bytedance.apm.constant.k;
import com.bytedance.apm.util.ae;
import com.bytedance.bdturing.utils.b;
import com.bytedance.services.apm.api.IHttpService;
import com.bytedance.services.apm.api.d;
import com.bytedance.services.apm.api.n;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AlogUploadService.java */
/* loaded from: classes.dex */
public class a {
    public static String UPLOAD_URL = "https://i.isnssdk.com/monitor/collect/c/logcollect";

    /* compiled from: AlogUploadService.java */
    /* renamed from: com.bytedance.apm.alog.net.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0011a {
        void onSuccess(boolean z, int i2, Exception exc, JSONObject jSONObject);
    }

    public static void setUploadUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        UPLOAD_URL = str;
    }

    public static void setUploadHost(String str) {
        UPLOAD_URL = "https://" + str + k.FILE_COLLECT_PATH;
    }

    public static boolean uploadAlogFiles(String str, String str2, String str3, List<String> list, String str4, JSONObject jSONObject) {
        return uploadAlogFiles(str, str2, str3, list, str4, jSONObject, null);
    }

    public static boolean uploadAlogFiles(String str, String str2, String str3, List<String> list, String str4, JSONObject jSONObject, InterfaceC0011a interfaceC0011a) {
        d a2;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("scene", str4);
            n buildMultipartUpload = ((IHttpService) com.bytedance.apm6.service.d.getService(IHttpService.class)).buildMultipartUpload(UPLOAD_URL, "UTF-8", false, hashMap);
            buildMultipartUpload.addFormField("aid", str);
            buildMultipartUpload.addFormField("verify_info", ae.getReleaseBuild());
            buildMultipartUpload.addFormField("device_id", str2);
            buildMultipartUpload.addFormField("os", b.f4370d);
            buildMultipartUpload.addFormField("process_name", str3);
            for (String str5 : list) {
                File file = new File(str5);
                if (file.exists()) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("logtype", "alog");
                    hashMap2.put("scene", str4);
                    hashMap2.put("env", "params.txt");
                    buildMultipartUpload.a(file.getName(), file, (String) null, hashMap2);
                }
            }
            HashMap hashMap3 = new HashMap();
            hashMap3.put("filetype", "common_params");
            hashMap3.put("logtype", "env");
            String jSONObject2 = jSONObject == null ? null : jSONObject.toString();
            if (TextUtils.isEmpty(jSONObject2)) {
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("defaultData", "none commonParams");
                } catch (JSONException unused) {
                }
                jSONObject2 = jSONObject3.toString();
            }
            buildMultipartUpload.a("params.txt", jSONObject2, "text/plain", hashMap3);
            a2 = buildMultipartUpload.a();
            try {
            } catch (JSONException e2) {
                if (interfaceC0011a != null) {
                    interfaceC0011a.onSuccess(false, 7, e2, null);
                }
            }
        } catch (IOException e3) {
            if (interfaceC0011a != null) {
                interfaceC0011a.onSuccess(false, 8, e3, null);
            }
        } catch (Exception e4) {
            if (interfaceC0011a != null) {
                interfaceC0011a.onSuccess(false, 10, e4, null);
            }
        }
        if (a2.getResponseBytes() == null) {
            if (interfaceC0011a != null) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("ALOG_UPLOAD_RESPONSE", "null");
                jSONObject4.put("ALOG_UPLOAD_STATUS", a2.getStatusCode());
                interfaceC0011a.onSuccess(false, 11, null, jSONObject4);
            }
            return false;
        }
        JSONObject jSONObject5 = new JSONObject(new String(a2.getResponseBytes()));
        int optInt = jSONObject5.optInt("errno", -1);
        if (optInt == 200) {
            if (interfaceC0011a != null) {
                interfaceC0011a.onSuccess(true, -1, null, jSONObject5);
            }
            return true;
        }
        if (interfaceC0011a != null) {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("info", optInt);
            interfaceC0011a.onSuccess(false, 6, null, jSONObject6);
        }
        return false;
    }
}
