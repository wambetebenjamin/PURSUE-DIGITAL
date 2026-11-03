package com.bytedance.apm.util;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ParseUtils.java */
/* loaded from: classes.dex */
public class w {
    public static boolean isHostEquals(String str, List<String> list) {
        if (!com.bytedance.apm6.util.g.isEmpty(list) && !TextUtils.isEmpty(str)) {
            try {
                String host = Uri.parse(str).getHost();
                for (String str2 : list) {
                    if (host.equalsIgnoreCase(str2)) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static boolean isMatch(String str, List<String> list, List<Pattern> list2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!com.bytedance.apm6.util.g.isEmpty(list)) {
            for (String str2 : list) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        try {
            String path = new URI(str).getPath();
            if (!com.bytedance.apm6.util.g.isEmpty(list2)) {
                for (Pattern pattern : list2) {
                    if (pattern.matcher(path).matches()) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static JSONObject getJsonObject(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return jSONObject.optJSONObject(str);
    }

    public static List<String> parseList(JSONObject jSONObject, String str) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (com.bytedance.apm6.util.f.isEmpty(optJSONArray)) {
                return null;
            }
            int length = optJSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i2 = 0; i2 < length; i2++) {
                String string = optJSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<String> parseMapList(JSONObject jSONObject, String str) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (com.bytedance.apm6.util.f.isEmpty(optJSONObject)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (optJSONObject.optInt(next) == 1) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<String> parseListFromStr(JSONObject jSONObject, String str) {
        String optString = jSONObject.optString(str, "");
        if (TextUtils.isEmpty(optString)) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.asList(optString.split(","));
    }

    public static List<Pattern> parsePatterns(JSONObject jSONObject, String str) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            int length = optJSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i2 = 0; i2 < length; i2++) {
                String string = optJSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(Pattern.compile(string));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<Pattern> parseMapPatterns(JSONObject jSONObject, String str) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (com.bytedance.apm6.util.f.isEmpty(optJSONObject)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (optJSONObject.optInt(next) == 1) {
                    arrayList.add(Pattern.compile(next));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }
}
