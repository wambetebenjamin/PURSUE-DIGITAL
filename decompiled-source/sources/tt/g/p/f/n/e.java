package tt.g.p.f.n;

import com.ss.sys.ces.gg.tt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
public class e {
    private static Map<String, List<String>> a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String lowerCase = entry.getKey().toLowerCase(Locale.US);
            List list = (List) hashMap.get(lowerCase);
            if (list == null) {
                list = new ArrayList(2);
                hashMap.put(lowerCase, list);
            }
            list.add(entry.getValue());
        }
        return hashMap;
    }

    public static boolean a(String str, Map<String, String> map) {
        return b(str, map);
    }

    public static boolean a(String str, Map<String, String> map, byte[] bArr) {
        map.put("X-SS-STUB", z.b(bArr).toUpperCase());
        boolean b2 = b(str, map);
        if (!b2) {
            map.remove("X-SS-STUB");
        }
        return b2;
    }

    private static boolean b(String str, Map<String, String> map) {
        Map<String, String> calcHttpRequestChecksum = tt.calcHttpRequestChecksum(str, a(map));
        if (calcHttpRequestChecksum.isEmpty()) {
            return false;
        }
        map.putAll(calcHttpRequestChecksum);
        return true;
    }
}
