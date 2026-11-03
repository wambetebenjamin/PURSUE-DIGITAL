package com.bytedance.crash.util;

import android.text.TextUtils;
import com.bytedance.crash.jni.NativeBridge;
import com.bytedance.librarian.c;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes2.dex */
public class NativeTools {

    /* renamed from: a  reason: collision with root package name */
    private static NativeTools f7273a;
    private static HashMap<String, String> k = new HashMap<>();

    public void setResendSigquit(boolean z) {
    }

    public static NativeTools get() {
        if (f7273a == null) {
            synchronized (NativeTools.class) {
                if (f7273a == null) {
                    f7273a = new NativeTools();
                }
            }
        }
        return f7273a;
    }

    public int a(String str) {
        try {
            NativeBridge.a(str, 0, false);
            return 0;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public int dumpLogcat(String str, String str2) {
        try {
            NativeBridge.a(str, 0, false);
            return 0;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public String getSoUUIDCurrent(String str) {
        String str2 = k.get(str);
        if (TextUtils.isEmpty(str2)) {
            String compatibleBuildID = compatibleBuildID(get().getBuildID(str));
            k.put(str, compatibleBuildID);
            return compatibleBuildID;
        }
        return str2;
    }

    public static String compatibleBuildID(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            return sb.toString();
        }
        try {
            if (str.length() < 16) {
                sb.append(str);
            } else {
                sb.append(str.charAt(6));
                sb.append(str.charAt(7));
                sb.append(str.charAt(4));
                sb.append(str.charAt(5));
                sb.append(str.charAt(2));
                sb.append(str.charAt(3));
                sb.append(str.charAt(0));
                sb.append(str.charAt(1));
                sb.append(str.charAt(10));
                sb.append(str.charAt(11));
                sb.append(str.charAt(8));
                sb.append(str.charAt(9));
                sb.append(str.charAt(14));
                sb.append(str.charAt(15));
                sb.append(str.charAt(12));
                sb.append(str.charAt(13));
                if (str.length() >= 32) {
                    sb.append((CharSequence) str, 16, 32);
                    sb.append('0');
                }
            }
        } catch (Throwable unused) {
        }
        return sb.toString().toUpperCase();
    }

    private static String[] b(String str) {
        int lastIndexOf;
        int i2;
        String[] split = str.split("\\s+");
        String[] strArr = {"", ""};
        if (split.length >= 4) {
            String str2 = null;
            for (String str3 : split) {
                if (str3.startsWith(c.a.SEPARATOR) && str3.endsWith(c.a.SO_SUFFIX)) {
                    str2 = str3;
                }
            }
            if (!TextUtils.isEmpty(str2) && str2.endsWith(c.a.SO_SUFFIX) && !str2.contains("/system/lib") && (lastIndexOf = str2.lastIndexOf(47)) != -1) {
                String substring = str2.substring(lastIndexOf + 1);
                if (substring.contains(".apk!")) {
                    substring = substring.substring(substring.indexOf(".apk!") + 5);
                }
                strArr[0] = substring;
                strArr[1] = str2.substring(0, i2) + substring;
                return strArr;
            }
        }
        return null;
    }

    public String getBuildID(String str) {
        try {
            return NativeBridge.j(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public JSONArray getSoUUID(String str) {
        JSONArray jSONArray = new JSONArray();
        HashSet hashSet = new HashSet();
        for (String str2 : str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
            String[] b2 = b(str2);
            if (b2 != null) {
                String soUUIDCurrent = get().getSoUUIDCurrent(b2[1]);
                if (!TextUtils.isEmpty(soUUIDCurrent) && !hashSet.contains(b2[0])) {
                    hashSet.add(b2[0]);
                    JSONObject jSONObject = new JSONObject();
                    q.a(jSONObject, com.bytedance.crash.entity.b.LIB_NAME, (Object) b2[0]);
                    q.a(jSONObject, com.bytedance.crash.entity.b.LIB_UUID, (Object) soUUIDCurrent);
                    jSONArray.put(jSONObject);
                }
            }
        }
        return jSONArray;
    }

    public List<String> getFdListForAPM() {
        return NativeBridge.n();
    }

    public boolean is64BitRuntime() {
        return NativeBridge.i();
    }
}
