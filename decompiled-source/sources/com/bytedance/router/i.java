package com.bytedance.router;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
/* compiled from: SmartIntent.java */
/* loaded from: classes2.dex */
class i extends Intent {
    @Override // android.content.Intent
    public int getIntExtra(String str, int i2) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return i2;
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e2) {
                a(str, obj, "Integer", Integer.valueOf(i2), e2);
                return i2;
            }
        }
        try {
            return ((Integer) obj).intValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Integer", Integer.valueOf(i2), e3);
            return i2;
        }
    }

    public static Intent smartIntent(Intent intent) {
        return (intent == null || (intent instanceof i)) ? intent : new i(intent);
    }

    public static boolean isSmartIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent instanceof i;
    }

    private i(Intent intent) {
        super(intent);
    }

    @Override // android.content.Intent
    public short getShortExtra(String str, short s) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return s;
        }
        if (obj instanceof String) {
            try {
                return Short.valueOf(Short.parseShort((String) obj)).shortValue();
            } catch (NumberFormatException e2) {
                a(str, obj, "Short", Short.valueOf(s), e2);
                return s;
            }
        }
        try {
            return ((Short) obj).shortValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Short", Short.valueOf(s), e3);
            return s;
        }
    }

    @Override // android.content.Intent
    public char getCharExtra(String str, char c2) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return c2;
        }
        if (obj instanceof String) {
            String str2 = (String) obj;
            if (str2.length() == 1) {
                return str2.charAt(0);
            }
            a(str, obj, "Char", Character.valueOf(c2), null);
            return c2;
        }
        try {
            return ((Character) obj).charValue();
        } catch (ClassCastException e2) {
            a(str, obj, "Char", Character.valueOf(c2), e2);
            return c2;
        }
    }

    @Override // android.content.Intent
    public long getLongExtra(String str, long j2) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return j2;
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e2) {
                a(str, obj, "Long", Long.valueOf(j2), e2);
                return j2;
            }
        }
        try {
            return ((Long) obj).longValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Long", Long.valueOf(j2), e3);
            return j2;
        }
    }

    @Override // android.content.Intent
    public float getFloatExtra(String str, float f2) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return f2;
        }
        if (obj instanceof String) {
            try {
                return Float.parseFloat((String) obj);
            } catch (NumberFormatException e2) {
                a(str, obj, "Float", Float.valueOf(f2), e2);
                return f2;
            }
        }
        try {
            return ((Float) obj).floatValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Float", Float.valueOf(f2), e3);
            return f2;
        }
    }

    @Override // android.content.Intent
    public double getDoubleExtra(String str, double d2) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return d2;
        }
        if (obj instanceof String) {
            try {
                return Double.parseDouble((String) obj);
            } catch (NumberFormatException e2) {
                a(str, obj, "Double", Double.valueOf(d2), e2);
                return d2;
            }
        }
        try {
            return ((Double) obj).doubleValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Double", Double.valueOf(d2), e3);
            return d2;
        }
    }

    @Override // android.content.Intent
    public byte getByteExtra(String str, byte b2) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return b2;
        }
        if (obj instanceof String) {
            try {
                return Byte.parseByte((String) obj);
            } catch (NumberFormatException e2) {
                a(str, obj, "Byte", Byte.valueOf(b2), e2);
                return b2;
            }
        }
        try {
            return ((Byte) obj).byteValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Byte", Byte.valueOf(b2), e3);
            return b2;
        }
    }

    @Override // android.content.Intent
    public boolean getBooleanExtra(String str, boolean z) {
        Object obj;
        Bundle extras = super.getExtras();
        if (extras == null || (obj = extras.get(str)) == null) {
            return z;
        }
        if (obj instanceof String) {
            try {
                return Boolean.parseBoolean((String) obj);
            } catch (NumberFormatException e2) {
                a(str, obj, "Boolean", Boolean.valueOf(z), e2);
                return z;
            }
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (ClassCastException e3) {
            a(str, obj, "Boolean", Boolean.valueOf(z), e3);
            return z;
        }
    }

    @Override // android.content.Intent
    public Bundle getExtras() {
        return super.getExtras();
    }

    @Override // android.content.Intent
    public Uri getData() {
        return super.getData();
    }

    private void a(String str, Object obj, String str2, Object obj2, RuntimeException runtimeException) {
        Log.w("SmartIntent", "Key " + str + " expected " + str2 + " but value was a " + obj.getClass().getName() + ".  The default value " + obj2 + " was returned.");
        if (runtimeException != null) {
            Log.w("SmartIntent", "Attempt to cast generated internal exception:", runtimeException);
        }
    }
}
