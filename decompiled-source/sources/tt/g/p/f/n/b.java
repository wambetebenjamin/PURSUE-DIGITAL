package tt.g.p.f.n;

import android.text.TextUtils;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes10.dex */
class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, Map<String, Object> map) {
        StringBuilder append;
        if (str == null || map == null || map.size() <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            sb.append(URLEncoder.encode(next.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(next.getValue().toString(), "UTF-8"));
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        if (TextUtils.isEmpty(sb)) {
            return str;
        }
        String sb2 = sb.toString();
        if (str.indexOf("?") != -1) {
            append = new StringBuilder().append(str).append("&");
        } else {
            append = new StringBuilder().append(str).append("?");
            sb2 = sb.toString();
        }
        return append.append(sb2).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject.toString().getBytes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> b(Map<String, Object> map) {
        if (map == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }
}
