package com.bytedance.apm.util;

import android.text.TextUtils;
import com.bytedance.apm.net.DefaultHttpServiceImpl;
import com.bytedance.services.apm.api.IHttpService;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: FileUploadUtils.java */
/* loaded from: classes.dex */
public class h {
    public static com.bytedance.services.apm.api.d uploadFiles(String str, List<File> list, Map<String, String> map) {
        try {
            IHttpService iHttpService = (IHttpService) com.bytedance.news.common.service.manager.e.getService(IHttpService.class);
            if (iHttpService == null) {
                iHttpService = new DefaultHttpServiceImpl();
            }
            com.bytedance.services.apm.api.n buildMultipartUpload = iHttpService.buildMultipartUpload(str, "UTF-8", false);
            if (list != null && !list.isEmpty()) {
                for (File file : list) {
                    if (file.exists()) {
                        buildMultipartUpload.a(file.getName(), file, (String) null, new HashMap());
                    }
                }
            }
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    buildMultipartUpload.addFormField(entry.getKey(), entry.getValue());
                }
            }
            String str2 = new String(buildMultipartUpload.a().getResponseBytes());
            if (!TextUtils.isEmpty(str2)) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    return new com.bytedance.services.apm.api.d(jSONObject.optInt("error_code", 0), jSONObject.optString("message", "").getBytes());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
