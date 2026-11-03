package com.bytedance.ttgame.framework.module.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public class SpUtil {
    public static final String LOGIN_SUCCESS_PASSPORT_UID = "login_success_passport_uid";
    public static final String SP_NAME = "tt_game";

    public static String getSharedPreferences(String str, Context context) {
        return context != null ? context.getSharedPreferences(SP_NAME, 4).getString(str, "") : "";
    }

    public static String getSharedPreferences(String str, Context context, String str2) {
        return context != null ? context.getSharedPreferences(SP_NAME, 4).getString(str, str2) : str2;
    }

    public static String getSharedPreferences(String str, String str2, Context context) {
        return context != null ? context.getSharedPreferences(str, 4).getString(str2, "") : "";
    }

    public static String getSharedPreferences(String str, String str2, Context context, String str3) {
        return context != null ? context.getSharedPreferences(str, 0).getString(str2, str3) : str3;
    }

    public static int getSharedPreferences(String str, String str2, Context context, int i2) {
        return context != null ? context.getSharedPreferences(str, 0).getInt(str2, i2) : i2;
    }

    public static long getSharedPreferences(String str, String str2, Context context, long j2) {
        return context != null ? context.getSharedPreferences(str, 0).getLong(str2, j2) : j2;
    }

    public static boolean getSharedPreferences(String str, Context context, boolean z) {
        return context != null ? context.getSharedPreferences(SP_NAME, 4).getBoolean(str, z) : z;
    }

    public static boolean getSharedPreferences(String str, String str2, Context context, boolean z) {
        return context != null ? context.getSharedPreferences(str, 4).getBoolean(str2, z) : z;
    }

    public static long getSharedPreferences(String str, Context context, long j2) {
        return context != null ? context.getSharedPreferences(SP_NAME, 0).getLong(str, j2) : j2;
    }

    public static float getSharedPreferences(String str, Context context, float f2) {
        return context != null ? context.getSharedPreferences(SP_NAME, 0).getFloat(str, f2) : f2;
    }

    public static int getSharedPreferences(String str, Context context, int i2) {
        return context != null ? context.getSharedPreferences(SP_NAME, 0).getInt(str, i2) : i2;
    }

    public static double getSharedPreferences(String str, Context context, double d2) {
        try {
            return Double.parseDouble(getSharedPreferences(str, context, ""));
        } catch (NumberFormatException unused) {
            return d2;
        }
    }

    public static void removeSharedPreferences(String str, String str2, Context context) {
        if (context != null) {
            context.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }
    }

    public static void removeSharedPreferences(String str, Context context) {
        if (context != null) {
            context.getSharedPreferences(str, 0).edit().clear().apply();
        }
    }

    public static void setSharedPreferences(String str, int i2, Context context) {
        if (context != null) {
            context.getSharedPreferences(SP_NAME, 0).edit().putInt(str, i2).apply();
        }
    }

    public static void setSharedPreferences(String str, String str2, boolean z, Context context) {
        if (context != null) {
            context.getSharedPreferences(str, 0).edit().putBoolean(str2, z).apply();
        }
    }

    public static void setSharedPreferences(String str, long j2, Context context) {
        if (context != null) {
            context.getSharedPreferences(SP_NAME, 0).edit().putLong(str, j2).apply();
        }
    }

    public static void setSharedPreferences(String str, float f2, Context context) {
        if (context != null) {
            context.getSharedPreferences(SP_NAME, 0).edit().putFloat(str, f2).apply();
        }
    }

    public static void setSharedPreferences(String str, double d2, Context context) {
        if (context != null) {
            setSharedPreferences(str, String.valueOf(d2), context);
        }
    }

    public static void setSharedPreferences(String str, String str2, String str3, Context context) {
        if (context != null) {
            context.getSharedPreferences(str, 0).edit().putString(str2, str3).apply();
        }
    }

    public static void setSharedPreferences(String str, String str2, int i2, Context context) {
        if (context != null) {
            context.getSharedPreferences(str, 0).edit().putInt(str2, i2).apply();
        }
    }

    public static void setSharedPreferences(String str, String str2, long j2, Context context) {
        if (context != null) {
            context.getSharedPreferences(str, 0).edit().putLong(str2, j2).apply();
        }
    }

    public static void setSharedPreferences(String str, String str2, Context context) {
        if (context != null) {
            context.getSharedPreferences(SP_NAME, 4).edit().putString(str, str2).apply();
        }
    }

    public static void setSharedPreferences(String str, boolean z, Context context) {
        if (context != null) {
            context.getSharedPreferences(SP_NAME, 4).edit().putBoolean(str, z).apply();
        }
    }

    public static ArrayList<String> getSharedPreferenceList(String str, Context context) {
        if (context == null) {
            return new ArrayList<>();
        }
        String string = context.getSharedPreferences(SP_NAME, 0).getString(str, "");
        if ("".equals(string)) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(string.split("#")));
    }

    public static void setSharedPreferenceList(String str, List<String> list, Context context) {
        String str2;
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
        if (list == null || list.size() <= 0) {
            return;
        }
        String str3 = "";
        for (String str4 : list) {
            if ("".equals(str4)) {
                str2 = str3 + " ";
            } else {
                str2 = str3 + str4;
            }
            str3 = str2 + "#";
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, str3);
        edit.apply();
    }

    public static <K extends Serializable, V extends Serializable> void setSharedPreferenceMap(Context context, String str, Map<K, V> map) {
        try {
            putObject(SP_NAME, context, str, map);
        } catch (Exception unused) {
        }
    }

    public static <K extends Serializable, V extends Serializable> void setSharedPreferenceMap(String str, Context context, String str2, Map<K, V> map) {
        try {
            putObject(str, context, str2, map);
        } catch (Exception unused) {
        }
    }

    public static <K extends Serializable, V extends Serializable> Map<K, V> getSharedPreferenceMap(Context context, String str) {
        try {
            return (Map) getObject(SP_NAME, context, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static <K extends Serializable, V extends Serializable> Map<K, V> getSharedPreferenceMap(String str, Context context, String str2) {
        try {
            return (Map) getObject(str, context, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void putObject(String str, Context context, String str2, Object obj) {
        if (obj == null) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String str3 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
        byteArrayOutputStream.close();
        objectOutputStream.close();
        setSharedPreferences(str, str2, str3, context);
    }

    private static Object getObject(String str, Context context, String str2) {
        String sharedPreferences = getSharedPreferences(str, str2, context);
        if (StringUtil.isNullOrEmpty(sharedPreferences)) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(sharedPreferences.getBytes(), 0));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object readObject = objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return readObject;
    }
}
