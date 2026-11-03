package gsdk.impl.account.toutiao;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class ct {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, SharedPreferences> f19496a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private static Context f19497b;

    public static void a(Context context) {
        f19497b = context;
    }

    private ct() {
    }

    private static SharedPreferences a(String str) {
        if (f19497b == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        Map<String, SharedPreferences> map = f19496a;
        SharedPreferences sharedPreferences = map.get(str);
        if (sharedPreferences == null) {
            SharedPreferences sharedPreferences2 = f19497b.getSharedPreferences(str, 0);
            map.put(str, sharedPreferences2);
            return sharedPreferences2;
        }
        return sharedPreferences;
    }

    public static <T> void a(String str, String str2, T t) {
        SharedPreferences.Editor edit = a(str).edit();
        if (str2 != null) {
            if (t instanceof Integer) {
                edit.putInt(str2, ((Integer) t).intValue());
            } else if (t instanceof Long) {
                edit.putLong(str2, ((Long) t).longValue());
            } else if (t instanceof Boolean) {
                edit.putBoolean(str2, ((Boolean) t).booleanValue());
            } else if (t instanceof Float) {
                edit.putFloat(str2, ((Float) t).floatValue());
            } else if (t instanceof Set) {
                edit.putStringSet(str2, (Set) t);
            } else if (t instanceof String) {
                edit.putString(str2, String.valueOf(t));
            }
        }
        edit.apply();
    }

    public static <T> T b(String str, String str2, T t) {
        SharedPreferences a2 = a(str);
        try {
            Class<?> cls = t.getClass();
            if (cls == String.class) {
                return (T) a2.getString(str2, (String) t);
            }
            if (cls == Integer.class) {
                return (T) Integer.valueOf(a2.getInt(str2, ((Integer) t).intValue()));
            }
            if (cls == Float.class) {
                return (T) Float.valueOf(a2.getFloat(str2, ((Float) t).floatValue()));
            }
            if (cls == Long.class) {
                return (T) Long.valueOf(a2.getLong(str2, ((Long) t).longValue()));
            }
            if (cls == Boolean.class) {
                return (T) Boolean.valueOf(a2.getBoolean(str2, ((Boolean) t).booleanValue()));
            }
            if (Set.class.isAssignableFrom(cls)) {
                return (T) a2.getStringSet(str2, (Set) t);
            }
            throw new AssertionError();
        } catch (Exception e2) {
            e2.printStackTrace();
            return t;
        }
    }
}
