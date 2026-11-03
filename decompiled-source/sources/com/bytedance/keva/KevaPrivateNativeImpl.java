package com.bytedance.keva;

import com.bytedance.Interface.b;
import com.bytedance.keva.Keva;
import com.bytedance.librarian.c;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class KevaPrivateNativeImpl extends KevaImpl implements b {
    long handle;
    private ConcurrentHashMap<String, Object[]> keyValueMap;
    boolean needRelease;

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void dump() {
    }

    native long getRepoWithNative(String str, String str2, int i2, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeClearRepo(long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeContains(String str, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeErase(String str, long j2);

    native void nativeGetAll(long j2, Map<String, Object> map);

    native void nativeGetAllKey(long j2, Set<String> set);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeGetBoolean(String str, boolean z, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native byte[] nativeGetBytes(String str, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native int nativeGetCount(long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native double nativeGetDouble(String str, double d2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native float nativeGetFloat(String str, float f2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native int nativeGetInt(String str, int i2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native long nativeGetLong(String str, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native String nativeGetString(String str, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native String[] nativeGetStringArray(String str, long j2);

    native void nativeReleaseRepo(long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreBoolean(String str, boolean z, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreBytes(String str, byte[] bArr, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreDouble(String str, double d2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreFloat(String str, float f2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreInt(String str, int i2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreLong(String str, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreString(String str, String str2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native boolean nativeStoreStringArray(String str, String[] strArr, long j2);

    @Override // com.bytedance.keva.KevaImpl
    public /* bridge */ /* synthetic */ void dumpNative() {
        super.dumpNative();
    }

    @Override // com.bytedance.keva.KevaImpl
    public /* bridge */ /* synthetic */ int mode() {
        return super.mode();
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public /* bridge */ /* synthetic */ void registerChangeListener(Keva.OnChangeListener onChangeListener) {
        super.registerChangeListener(onChangeListener);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public /* bridge */ /* synthetic */ void unRegisterChangeListener(Keva.OnChangeListener onChangeListener) {
        super.unRegisterChangeListener(onChangeListener);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public String name() {
        return this.mName;
    }

    public KevaPrivateNativeImpl(String str, String str2, int i2) {
        super(str, str2, i2);
        this.keyValueMap = null;
        this.needRelease = true;
        this.handle = 0L;
        if (sIsEnableLoadFromNative == 1) {
            this.keyValueMap = new ConcurrentHashMap<>(5);
            KevaCacheManager.registerClearListener(this);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeFloat(String str, float f2) {
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        try {
            saveValueAccessInformation(str2, Float.valueOf(f2));
            nativeStoreFloat(str2, f2, this.handle);
            super.notifyChangeListeners(this, str2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str2, Float.valueOf(f2), th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeBoolean(String str, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        try {
            saveValueAccessInformation(str2, Boolean.valueOf(z));
            nativeStoreBoolean(str2, z, this.handle);
            super.notifyChangeListeners(this, str2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str2, Boolean.valueOf(z), th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeInt(String str, int i2) {
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        try {
            saveValueAccessInformation(str2, Integer.valueOf(i2));
            nativeStoreInt(str2, i2, this.handle);
            super.notifyChangeListeners(this, str2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str2, Integer.valueOf(i2), th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeLong(String str, long j2) {
        if ("".equals(str)) {
            str = null;
        }
        try {
            saveValueAccessInformation(str, Long.valueOf(j2));
            nativeStoreLong(str, j2, this.handle);
            super.notifyChangeListeners(this, str);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str, Long.valueOf(j2), th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeDouble(String str, double d2) {
        if ("".equals(str)) {
            str = null;
        }
        try {
            saveValueAccessInformation(str, Double.valueOf(d2));
            nativeStoreDouble(str, d2, this.handle);
            super.notifyChangeListeners(this, str);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str, Double.valueOf(d2), th);
        }
    }

    private void storeString(String str, String str2, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        String str3 = str;
        if (!z) {
            try {
                saveValueAccessInformation(str3, str2);
            } catch (Throwable th) {
                sMonitor.reportThrowable(3, this.mName, str3, str2, th);
                return;
            }
        }
        nativeStoreString(str3, str2, this.handle);
        super.notifyChangeListeners(this, str3);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeString(String str, String str2) {
        storeString(str, str2, false);
    }

    private void storeStringSet(String str, Set<String> set, boolean z) {
        try {
            String[] strArr = null;
            if ("".equals(str)) {
                str = null;
            }
            if (set != null) {
                strArr = new String[set.size()];
                set.toArray(strArr);
                if (!z) {
                    saveValueAccessInformation(str, strArr);
                }
            }
            nativeStoreStringArray(str, strArr, this.handle);
            super.notifyChangeListeners(this, str);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str, set, th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeStringSet(String str, Set<String> set) {
        storeStringSet(str, set, false);
    }

    private void storeStringArray(String str, String[] strArr, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        if (!z) {
            try {
                saveValueAccessInformation(str2, strArr);
            } catch (Throwable th) {
                sMonitor.reportThrowable(3, this.mName, str2, strArr, th);
                return;
            }
        }
        nativeStoreStringArray(str2, strArr, this.handle);
        super.notifyChangeListeners(this, str2);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeStringArray(String str, String[] strArr) {
        storeStringArray(str, strArr, false);
    }

    private void storeBytes(String str, byte[] bArr, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        if (!z) {
            try {
                saveValueAccessInformation(str2, bArr);
            } catch (Throwable th) {
                sMonitor.reportThrowable(3, this.mName, str2, bArr, th);
                return;
            }
        }
        nativeStoreBytes(str2, bArr, this.handle);
        super.notifyChangeListeners(this, str2);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeBytes(String str, byte[] bArr) {
        storeBytes(str, bArr, false);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeStringJustDisk(String str, String str2) {
        storeString(str, str2, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeStringArrayJustDisk(String str, String[] strArr) {
        storeStringArray(str, strArr, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeStringSetJustDisk(String str, Set<String> set) {
        storeStringSet(str, set, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void storeBytesJustDisk(String str, byte[] bArr) {
        storeBytes(str, bArr, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void erase(String str) {
        if ("".equals(str)) {
            str = null;
        }
        String str2 = str;
        try {
            this.keyValueMap.remove(str2);
            nativeErase(str2, this.handle);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, str2, null, th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public void clear() {
        try {
            this.keyValueMap.clear();
            nativeClearRepo(this.handle);
        } catch (Throwable th) {
            sMonitor.reportThrowable(3, this.mName, "clear_function", null, th);
        }
    }

    @Override // com.bytedance.keva.Keva
    public int count() {
        return nativeGetCount(this.handle);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public Map<String, Object> buildNewMap(Map<String, Object> map) {
        nativeGetAll(this.handle, map);
        return map;
    }

    @Override // com.bytedance.keva.Keva
    public Map<String, ?> getAll() {
        HashMap hashMap = new HashMap();
        try {
            nativeGetAll(this.handle, hashMap);
            return hashMap;
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, "getAll_function", null, th);
            return hashMap;
        }
    }

    @Override // com.bytedance.keva.Keva
    public Set<String> getAllKey() {
        HashSet hashSet = new HashSet();
        nativeGetAllKey(this.handle, hashSet);
        return hashSet;
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public int getInt(String str, int i2) {
        try {
            return fetchInt(str, i2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, Integer.valueOf(i2), th);
            return i2;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public long getLong(String str, long j2) {
        try {
            return fetchLong(str, j2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, Long.valueOf(j2), th);
            return j2;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public double getDouble(String str, double d2) {
        try {
            return fetchDouble(str, d2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, Double.valueOf(d2), th);
            return d2;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public float getFloat(String str, float f2) {
        try {
            return fetchFloat(str, f2);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, Float.valueOf(f2), th);
            return f2;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public boolean getBoolean(String str, boolean z) {
        try {
            return fetchBoolean(str, z);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, Boolean.valueOf(z), th);
            return z;
        }
    }

    private byte[] getBytes(String str, byte[] bArr, boolean z) {
        try {
            return fetchBytes(str, bArr, -1, z);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, bArr, th);
            return bArr;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public byte[] getBytes(String str, byte[] bArr) {
        return getBytes(str, bArr, false);
    }

    private Set<String> getStringSet(String str, Set<String> set, boolean z) {
        try {
            String[] fetchStringArray = fetchStringArray(str, null, z);
            if (fetchStringArray == null) {
                return set != null ? new HashSet(set) : set;
            }
            return new HashSet(Arrays.asList(fetchStringArray));
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, set, th);
            return set;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public Set<String> getStringSet(String str, Set<String> set) {
        return getStringSet(str, set, false);
    }

    private String[] getStringArray(String str, String[] strArr, boolean z) {
        try {
            String[] fetchStringArray = fetchStringArray(str, null, z);
            return fetchStringArray == null ? strArr : fetchStringArray;
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, strArr, th);
            return strArr;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public String[] getStringArray(String str, String[] strArr) {
        return getStringArray(str, strArr, false);
    }

    private String getString(String str, String str2, boolean z) {
        try {
            return fetchString(str, str2, z);
        } catch (Throwable th) {
            sMonitor.reportThrowable(2, this.mName, str, str2, th);
            return str2;
        }
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public String getString(String str, String str2) {
        return getString(str, str2, false);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public String getStringJustDisk(String str, String str2) {
        return getString(str, str2, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public String[] getStringArrayJustDisk(String str, String[] strArr) {
        return getStringArray(str, strArr, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public Set<String> getStringSetJustDisk(String str, Set<String> set) {
        return getStringSet(str, set, true);
    }

    @Override // com.bytedance.keva.KevaImpl, com.bytedance.keva.Keva
    public byte[] getBytesJustDisk(String str, byte[] bArr) {
        return getBytes(str, bArr, true);
    }

    @Override // com.bytedance.keva.Keva
    public boolean contains(String str) {
        if ("".equals(str)) {
            str = null;
        }
        return nativeContains(str, this.handle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.keva.KevaImpl
    public void init(boolean z) {
        if (this.handle != 0) {
            return;
        }
        doLoadRepo(z);
    }

    @Override // com.bytedance.keva.KevaImpl
    void doLoadRepo(boolean z) {
        try {
            this.handle = getRepoWithNative(this.mName, this.mSpecifiedPath, this.mMode, z);
            if (z) {
                return;
            }
            sMonitor.onLoadRepo(this.mName, this.mMode);
        } catch (Throwable th) {
            sMonitor.reportThrowable(1, this.mName, null, null, th);
        }
    }

    @Override // com.bytedance.keva.KevaImpl
    protected int fetchInt(String str, int i2) {
        if ("".equals(str)) {
            str = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Object[] objArr = this.keyValueMap.get(str);
        if (objArr != null) {
            objArr[1] = Long.valueOf(currentTimeMillis);
            return ((Integer) objArr[0]).intValue();
        }
        int nativeGetInt = nativeGetInt(str, i2, this.handle);
        this.keyValueMap.put(str, new Object[]{Integer.valueOf(nativeGetInt), Long.valueOf(currentTimeMillis)});
        return nativeGetInt;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected long fetchLong(String str, long j2) {
        if ("".equals(str)) {
            str = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Object[] objArr = this.keyValueMap.get(str);
        if (objArr != null) {
            objArr[1] = Long.valueOf(currentTimeMillis);
            return ((Long) objArr[0]).longValue();
        }
        long nativeGetLong = nativeGetLong(str, j2, this.handle);
        this.keyValueMap.put(str, new Object[]{Long.valueOf(nativeGetLong), Long.valueOf(currentTimeMillis)});
        return nativeGetLong;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected float fetchFloat(String str, float f2) {
        if ("".equals(str)) {
            str = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Object[] objArr = this.keyValueMap.get(str);
        if (objArr != null) {
            objArr[1] = Long.valueOf(currentTimeMillis);
            return ((Float) objArr[0]).floatValue();
        }
        float nativeGetFloat = nativeGetFloat(str, f2, this.handle);
        this.keyValueMap.put(str, new Object[]{Float.valueOf(nativeGetFloat), Long.valueOf(currentTimeMillis)});
        return nativeGetFloat;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected boolean fetchBoolean(String str, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Object[] objArr = this.keyValueMap.get(str);
        if (objArr != null) {
            objArr[1] = Long.valueOf(currentTimeMillis);
            return ((Boolean) objArr[0]).booleanValue();
        }
        boolean nativeGetBoolean = nativeGetBoolean(str, z, this.handle);
        this.keyValueMap.put(str, new Object[]{Boolean.valueOf(nativeGetBoolean), Long.valueOf(currentTimeMillis)});
        return nativeGetBoolean;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected double fetchDouble(String str, double d2) {
        if ("".equals(str)) {
            str = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Object[] objArr = this.keyValueMap.get(str);
        if (objArr != null) {
            objArr[1] = Long.valueOf(currentTimeMillis);
            return ((Double) objArr[0]).doubleValue();
        }
        double nativeGetDouble = nativeGetDouble(str, d2, this.handle);
        this.keyValueMap.put(str, new Object[]{Double.valueOf(nativeGetDouble), Long.valueOf(currentTimeMillis)});
        return nativeGetDouble;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected String fetchString(String str, String str2, boolean z) {
        String nativeGetString;
        if ("".equals(str)) {
            str = null;
        }
        if (!z) {
            long currentTimeMillis = System.currentTimeMillis();
            Object[] objArr = this.keyValueMap.get(str);
            if (objArr != null) {
                objArr[1] = Long.valueOf(currentTimeMillis);
                nativeGetString = (String) objArr[0];
            } else {
                String nativeGetString2 = nativeGetString(str, this.handle);
                this.keyValueMap.put(str, new Object[]{nativeGetString2, Long.valueOf(currentTimeMillis)});
                nativeGetString = nativeGetString2;
            }
        } else {
            nativeGetString = nativeGetString(str, this.handle);
        }
        return nativeGetString == null ? str2 : nativeGetString;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected byte[] fetchBytes(String str, byte[] bArr, int i2, boolean z) {
        byte[] nativeGetBytes;
        if ("".equals(str)) {
            str = null;
        }
        if (!z) {
            long currentTimeMillis = System.currentTimeMillis();
            Object[] objArr = this.keyValueMap.get(str);
            if (objArr != null) {
                objArr[1] = Long.valueOf(currentTimeMillis);
                nativeGetBytes = (byte[]) objArr[0];
            } else {
                byte[] nativeGetBytes2 = nativeGetBytes(str, this.handle);
                this.keyValueMap.put(str, new Object[]{nativeGetBytes2, Long.valueOf(currentTimeMillis)});
                nativeGetBytes = nativeGetBytes2;
            }
        } else {
            nativeGetBytes = nativeGetBytes(str, this.handle);
        }
        return nativeGetBytes == null ? bArr : nativeGetBytes;
    }

    @Override // com.bytedance.keva.KevaImpl
    protected String[] fetchStringArray(String str, String[] strArr, boolean z) {
        if ("".equals(str)) {
            str = null;
        }
        if (!z) {
            long currentTimeMillis = System.currentTimeMillis();
            Object[] objArr = this.keyValueMap.get(str);
            if (objArr != null) {
                objArr[1] = Long.valueOf(currentTimeMillis);
                return (String[]) objArr[0];
            }
            String[] nativeGetStringArray = nativeGetStringArray(str, this.handle);
            this.keyValueMap.put(str, new Object[]{nativeGetStringArray, Long.valueOf(currentTimeMillis)});
            return nativeGetStringArray;
        }
        return nativeGetStringArray(str, this.handle);
    }

    private void saveValueAccessInformation(String str, Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        Object[] objArr = this.keyValueMap.get(str);
        if (objArr == null) {
            this.keyValueMap.put(str, new Object[]{obj, Long.valueOf(currentTimeMillis)});
            return;
        }
        objArr[0] = obj;
        objArr[1] = Long.valueOf(currentTimeMillis);
    }

    void addBooleanValue(String str, boolean z, Map<String, Object> map) {
        map.put(str, Boolean.valueOf(z));
    }

    void addDoubleValue(String str, double d2, Map<String, Object> map) {
        map.put(str, Double.valueOf(d2));
    }

    void addFloatValue(String str, float f2, Map<String, Object> map) {
        map.put(str, Float.valueOf(f2));
    }

    void addIntValue(String str, int i2, Map<String, Object> map) {
        map.put(str, Integer.valueOf(i2));
    }

    void addLongValue(String str, long j2, Map<String, Object> map) {
        map.put(str, Long.valueOf(j2));
    }

    void addObjectValue(String str, Object obj, Map<String, Object> map) {
        map.put(str, obj);
    }

    @Override // com.bytedance.Interface.b
    public long clearCache(int i2, boolean z) {
        int i3;
        int length;
        long currentTimeMillis = System.currentTimeMillis();
        int i4 = 0;
        try {
            i3 = 0;
            for (Map.Entry<String, Object[]> entry : this.keyValueMap.entrySet()) {
                try {
                    Object[] value = entry.getValue();
                    i3 += (entry.getKey().length() * 2) + 40;
                    if (currentTimeMillis - ((Long) value[1]).longValue() > i2) {
                        this.keyValueMap.remove(entry.getKey());
                        Object obj = value[0];
                        if (obj != null) {
                            if (!(obj instanceof Boolean)) {
                                if (obj instanceof String) {
                                    length = (((String) obj).length() * 2) + 40;
                                } else {
                                    if (!(obj instanceof Double) && !(obj instanceof Long)) {
                                        if (!(obj instanceof Integer) && !(obj instanceof Float)) {
                                            if (obj instanceof String[]) {
                                                for (String str : (String[]) obj) {
                                                    i3 += (str.length() * 2) + 40;
                                                }
                                            } else if (obj instanceof byte[]) {
                                                length = ((byte[]) obj).length;
                                            }
                                            i3 += 16;
                                        }
                                    }
                                    i3 += 24;
                                    i3 += 16;
                                }
                                i3 += length;
                                i3 += 16;
                            }
                            i3 += 16;
                            i3 += 16;
                        }
                    }
                    this.needRelease = true;
                } catch (Exception e2) {
                    e = e2;
                    i4 = i3;
                    e.printStackTrace();
                    i3 = i4;
                    return i3;
                }
            }
            if (z && this.keyValueMap.size() == 0 && this.needRelease) {
                nativeReleaseRepo(this.handle);
                this.needRelease = false;
            }
        } catch (Exception e3) {
            e = e3;
        }
        return i3;
    }

    @Override // com.bytedance.Interface.b
    public String getRepoName() {
        return this.mSpecifiedPath == null ? this.mName : this.mSpecifiedPath + c.a.SEPARATOR + this.mName;
    }
}
