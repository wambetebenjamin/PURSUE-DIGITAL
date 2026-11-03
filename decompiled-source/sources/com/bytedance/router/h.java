package com.bytedance.router;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
/* compiled from: SmartBundle.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private Bundle f12201a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Bundle bundle) {
        this.f12201a = bundle == null ? new Bundle() : bundle;
    }

    public Bundle getBundle() {
        return this.f12201a;
    }

    public void clear() {
        this.f12201a.clear();
    }

    public boolean containsKey(String str) {
        return this.f12201a.containsKey(str);
    }

    public Object get(String str) {
        return this.f12201a.get(str);
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public boolean[] getBooleanArray(String str) {
        return this.f12201a.getBooleanArray(str);
    }

    public Bundle getBundle(String str) {
        return this.f12201a.getBundle(str);
    }

    public byte getByte(String str) {
        return getByte(str, (byte) 0).byteValue();
    }

    public Byte getByte(String str, byte b2) {
        Bundle bundle = this.f12201a;
        if (bundle == null) {
            return Byte.valueOf(b2);
        }
        Object obj = bundle.get(str);
        if (obj == null) {
            return Byte.valueOf(b2);
        }
        if (obj instanceof String) {
            try {
                return Byte.valueOf(Byte.parseByte((String) obj));
            } catch (NumberFormatException e2) {
                a(str, obj, "Byte", Byte.valueOf(b2), e2);
                return Byte.valueOf(b2);
            }
        }
        try {
            return (Byte) obj;
        } catch (ClassCastException e3) {
            a(str, obj, "Byte", Byte.valueOf(b2), e3);
            return Byte.valueOf(b2);
        }
    }

    public byte[] getByteArray(String str) {
        return this.f12201a.getByteArray(str);
    }

    public char getChar(String str) {
        return getChar(str, (char) 0);
    }

    public char getChar(String str, char c2) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public char[] getCharArray(String str) {
        return this.f12201a.getCharArray(str);
    }

    public CharSequence getCharSequence(String str) {
        return this.f12201a.getCharSequence(str);
    }

    public CharSequence getCharSequence(String str, CharSequence charSequence) {
        return this.f12201a.getCharSequence(str, charSequence);
    }

    public CharSequence[] getCharSequenceArray(String str) {
        return this.f12201a.getCharSequenceArray(str);
    }

    public ArrayList<CharSequence> getCharSequenceArrayList(String str) {
        return this.f12201a.getCharSequenceArrayList(str);
    }

    public double getDouble(String str) {
        return getDouble(str, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public double getDouble(String str, double d2) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public double[] getDoubleArray(String str) {
        return this.f12201a.getDoubleArray(str);
    }

    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    public float getFloat(String str, float f2) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public float[] getFloatArray(String str) {
        return this.f12201a.getFloatArray(str);
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int getInt(String str, int i2) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public int[] getIntArray(String str) {
        return this.f12201a.getIntArray(str);
    }

    public ArrayList<Integer> getIntegerArrayList(String str) {
        return this.f12201a.getIntegerArrayList(str);
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public long getLong(String str, long j2) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public long[] getLongArray(String str) {
        return this.f12201a.getLongArray(str);
    }

    public <T extends Parcelable> T getParcelable(String str) {
        return (T) this.f12201a.getParcelable(str);
    }

    public Parcelable[] getParcelableArray(String str) {
        return this.f12201a.getParcelableArray(str);
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayList(String str) {
        return this.f12201a.getParcelableArrayList(str);
    }

    public Serializable getSerializable(String str) {
        return this.f12201a.getSerializable(str);
    }

    public short getShort(String str) {
        return getShort(str, (short) 0);
    }

    public short getShort(String str, short s) {
        Object obj;
        Bundle bundle = this.f12201a;
        if (bundle == null || (obj = bundle.get(str)) == null) {
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

    public short[] getShortArray(String str) {
        return this.f12201a.getShortArray(str);
    }

    public <T extends Parcelable> SparseArray<T> getSparseParcelableArray(String str) {
        return this.f12201a.getSparseParcelableArray(str);
    }

    public String getString(String str) {
        return this.f12201a.getString(str);
    }

    public String getString(String str, String str2) {
        return this.f12201a.getString(str, str2);
    }

    public String[] getStringArray(String str) {
        return this.f12201a.getStringArray(str);
    }

    public ArrayList<String> getStringArrayList(String str) {
        return this.f12201a.getStringArrayList(str);
    }

    public boolean hasFileDescriptors() {
        return this.f12201a.hasFileDescriptors();
    }

    public boolean isEmpty() {
        return this.f12201a.isEmpty();
    }

    public Set<String> keySet() {
        return this.f12201a.keySet();
    }

    public void putAll(Bundle bundle) {
        this.f12201a.putAll(bundle);
    }

    public void putBoolean(String str, boolean z) {
        this.f12201a.putBoolean(str, z);
    }

    public void putBooleanArray(String str, boolean[] zArr) {
        this.f12201a.putBooleanArray(str, zArr);
    }

    public void putBundle(String str, Bundle bundle) {
        this.f12201a.putBundle(str, bundle);
    }

    public void putByte(String str, byte b2) {
        this.f12201a.putByte(str, b2);
    }

    public void putByteArray(String str, byte[] bArr) {
        this.f12201a.putByteArray(str, bArr);
    }

    public void putChar(String str, char c2) {
        this.f12201a.putChar(str, c2);
    }

    public void putCharArray(String str, char[] cArr) {
        this.f12201a.putCharArray(str, cArr);
    }

    public void putCharSequence(String str, CharSequence charSequence) {
        this.f12201a.putCharSequence(str, charSequence);
    }

    public void putCharSequenceArray(String str, CharSequence[] charSequenceArr) {
        this.f12201a.putCharSequenceArray(str, charSequenceArr);
    }

    public void putCharSequenceArrayList(String str, ArrayList<CharSequence> arrayList) {
        this.f12201a.putCharSequenceArrayList(str, arrayList);
    }

    public void putDouble(String str, double d2) {
        this.f12201a.putDouble(str, d2);
    }

    public void putDoubleArray(String str, double[] dArr) {
        this.f12201a.putDoubleArray(str, dArr);
    }

    public void putFloat(String str, float f2) {
        this.f12201a.putFloat(str, f2);
    }

    public void putFloatArray(String str, float[] fArr) {
        this.f12201a.putFloatArray(str, fArr);
    }

    public void putInt(String str, int i2) {
        this.f12201a.putInt(str, i2);
    }

    public void putIntArray(String str, int[] iArr) {
        this.f12201a.putIntArray(str, iArr);
    }

    public void putIntegerArrayList(String str, ArrayList<Integer> arrayList) {
        this.f12201a.putIntegerArrayList(str, arrayList);
    }

    public void putLong(String str, long j2) {
        this.f12201a.putLong(str, j2);
    }

    public void putLongArray(String str, long[] jArr) {
        this.f12201a.putLongArray(str, jArr);
    }

    public void putParcelable(String str, Parcelable parcelable) {
        this.f12201a.putParcelable(str, parcelable);
    }

    public void putParcelableArray(String str, Parcelable[] parcelableArr) {
        this.f12201a.putParcelableArray(str, parcelableArr);
    }

    public void putParcelableArrayList(String str, ArrayList<? extends Parcelable> arrayList) {
        this.f12201a.putParcelableArrayList(str, arrayList);
    }

    public void putSerializable(String str, Serializable serializable) {
        this.f12201a.putSerializable(str, serializable);
    }

    public void putShort(String str, short s) {
        this.f12201a.putShort(str, s);
    }

    public void putShortArray(String str, short[] sArr) {
        this.f12201a.putShortArray(str, sArr);
    }

    public void putSparseParcelableArray(String str, SparseArray<? extends Parcelable> sparseArray) {
        this.f12201a.putSparseParcelableArray(str, sparseArray);
    }

    public void putString(String str, String str2) {
        this.f12201a.putString(str, str2);
    }

    public void putStringArray(String str, String[] strArr) {
        this.f12201a.putStringArray(str, strArr);
    }

    public void putStringArrayList(String str, ArrayList<String> arrayList) {
        this.f12201a.putStringArrayList(str, arrayList);
    }

    public void remove(String str) {
        this.f12201a.remove(str);
    }

    public ClassLoader getClassLoader() {
        return this.f12201a.getClassLoader();
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.f12201a.setClassLoader(classLoader);
    }

    public int size() {
        return this.f12201a.size();
    }

    public synchronized String toString() {
        return this.f12201a.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        this.f12201a.writeToParcel(parcel, i2);
    }

    private void a(String str, Object obj, String str2, Object obj2, RuntimeException runtimeException) {
        Log.w("SmartIntent", "Key " + str + " expected " + str2 + " but value was a " + obj.getClass().getName() + ".  The default value " + obj2 + " was returned.");
        if (runtimeException != null) {
            Log.w("SmartIntent", "Attempt to cast generated internal exception:", runtimeException);
        }
    }
}
