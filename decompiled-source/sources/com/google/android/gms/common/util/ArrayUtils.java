package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> T[] concat(T[]... tArr) {
        if (tArr.length != 0) {
            int i2 = 0;
            for (T[] tArr2 : tArr) {
                i2 += tArr2.length;
            }
            T[] tArr3 = (T[]) Arrays.copyOf(tArr[0], i2);
            int length = tArr[0].length;
            for (int i3 = 1; i3 < tArr.length; i3++) {
                T[] tArr4 = tArr[i3];
                int length2 = tArr4.length;
                System.arraycopy(tArr4, 0, tArr3, length, length2);
                length += length2;
            }
            return tArr3;
        }
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass(), 0));
    }

    public static byte[] concatByteArrays(byte[]... bArr) {
        if (bArr.length != 0) {
            int i2 = 0;
            for (byte[] bArr2 : bArr) {
                i2 += bArr2.length;
            }
            byte[] copyOf = Arrays.copyOf(bArr[0], i2);
            int length = bArr[0].length;
            for (int i3 = 1; i3 < bArr.length; i3++) {
                byte[] bArr3 = bArr[i3];
                int length2 = bArr3.length;
                System.arraycopy(bArr3, 0, copyOf, length, length2);
                length += length2;
            }
            return copyOf;
        }
        return new byte[0];
    }

    public static boolean contains(int[] iArr, int i2) {
        if (iArr != null) {
            for (int i3 : iArr) {
                if (i3 == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        int length;
        int i2;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || (length = tArr2.length) == 0) {
            return (T[]) Arrays.copyOf(tArr, tArr.length);
        }
        Class<?> cls = tArr2.getClass();
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(cls.getComponentType(), tArr.length));
        if (length == 1) {
            i2 = 0;
            for (T t : tArr) {
                if (!Objects.equal(tArr2[0], t)) {
                    tArr3[i2] = t;
                    i2++;
                }
            }
        } else {
            int i3 = 0;
            for (T t2 : tArr) {
                if (!contains(tArr2, t2)) {
                    tArr3[i3] = t2;
                    i3++;
                }
            }
            i2 = i3;
        }
        if (tArr3 == null) {
            return null;
        }
        return i2 == tArr3.length ? tArr3 : (T[]) Arrays.copyOf(tArr3, i2);
    }

    public static <T> ArrayList<T> toArrayList(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        for (T t : tArr) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public static int[] toPrimitiveArray(Collection<Integer> collection) {
        int i2 = 0;
        if (collection == null || collection.isEmpty()) {
            return new int[0];
        }
        int[] iArr = new int[collection.size()];
        for (Integer num : collection) {
            iArr[i2] = num.intValue();
            i2++;
        }
        return iArr;
    }

    public static Integer[] toWrapperArray(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i2 = 0; i2 < length; i2++) {
            numArr[i2] = Integer.valueOf(iArr[i2]);
        }
        return numArr;
    }

    public static void writeArray(StringBuilder sb, double[] dArr) {
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(dArr[i2]);
        }
    }

    public static void writeStringArray(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[i2]);
            sb.append("\"");
        }
    }

    public static <T> boolean contains(T[] tArr, T t) {
        int length = tArr != null ? tArr.length : 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (!Objects.equal(tArr[i2], t)) {
                i2++;
            } else if (i2 >= 0) {
                return true;
            }
        }
        return false;
    }

    public static void writeArray(StringBuilder sb, float[] fArr) {
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(fArr[i2]);
        }
    }

    public static void writeArray(StringBuilder sb, int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(iArr[i2]);
        }
    }

    public static void writeArray(StringBuilder sb, long[] jArr) {
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(jArr[i2]);
        }
    }

    public static <T> void writeArray(StringBuilder sb, T[] tArr) {
        int length = tArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(tArr[i2]);
        }
    }

    public static void writeArray(StringBuilder sb, boolean[] zArr) {
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(zArr[i2]);
        }
    }
}
