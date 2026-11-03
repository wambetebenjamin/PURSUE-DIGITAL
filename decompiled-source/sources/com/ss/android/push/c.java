package com.ss.android.push;

import android.content.Intent;
import android.os.Bundle;
/* compiled from: SmartIntentUtil.java */
/* loaded from: classes9.dex */
public class c {
    public static short getShortExtra(Intent intent, String str, short s) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return s;
        }
        if (obj instanceof String) {
            try {
                return Short.valueOf(Short.parseShort((String) obj)).shortValue();
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                return s;
            }
        }
        try {
            return ((Short) obj).shortValue();
        } catch (ClassCastException e3) {
            e3.printStackTrace();
            return s;
        }
    }

    public static char getCharExtra(Intent intent, String str, char c2) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return c2;
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            return str2.length() == 1 ? str2.charAt(0) : c2;
        }
        try {
            return ((Character) obj).charValue();
        } catch (ClassCastException unused) {
            return c2;
        }
    }

    public static int getIntExtra(Intent intent, String str, int i2) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return i2;
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
                return i2;
            }
        }
        try {
            return ((Integer) obj).intValue();
        } catch (ClassCastException unused2) {
            return i2;
        }
    }

    public static long getLongExtra(Intent intent, String str, long j2) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return j2;
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException unused) {
                return j2;
            }
        }
        try {
            return ((Long) obj).longValue();
        } catch (ClassCastException unused2) {
            return j2;
        }
    }

    public static float getFloatExtra(Intent intent, String str, float f2) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return f2;
        }
        if (obj instanceof String) {
            try {
                return Float.parseFloat((String) obj);
            } catch (NumberFormatException unused) {
                return f2;
            }
        }
        try {
            return ((Float) obj).floatValue();
        } catch (ClassCastException unused2) {
            return f2;
        }
    }

    public static double getDoubleExtra(Intent intent, String str, double d2) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return d2;
        }
        if (obj instanceof String) {
            try {
                return Double.parseDouble((String) obj);
            } catch (NumberFormatException unused) {
                return d2;
            }
        }
        try {
            return ((Double) obj).doubleValue();
        } catch (ClassCastException unused2) {
            return d2;
        }
    }

    public static byte getByteExtra(Intent intent, String str, byte b2) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return b2;
        }
        if (obj instanceof String) {
            try {
                return Byte.parseByte((String) obj);
            } catch (NumberFormatException unused) {
                return b2;
            }
        }
        try {
            return ((Byte) obj).byteValue();
        } catch (ClassCastException unused2) {
            return b2;
        }
    }

    public static boolean getBooleanExtra(Intent intent, String str, boolean z) {
        Object obj;
        Bundle extras = intent.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return z;
        }
        if (obj instanceof String) {
            try {
                return Boolean.parseBoolean((String) obj);
            } catch (NumberFormatException unused) {
                return z;
            }
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (ClassCastException unused2) {
            return z;
        }
    }
}
