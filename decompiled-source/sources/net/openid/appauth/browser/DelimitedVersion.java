package net.openid.appauth.browser;
/* loaded from: classes10.dex */
public class DelimitedVersion implements Comparable<DelimitedVersion> {
    private static final long BIT_MASK_32 = -1;
    private static final int PRIME_HASH_FACTOR = 92821;
    private final long[] mNumericParts;

    private int compareLongs(long j2, long j3) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 > 0 ? 1 : 0;
    }

    public DelimitedVersion(long[] jArr) {
        this.mNumericParts = jArr;
    }

    public String toString() {
        if (this.mNumericParts.length == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mNumericParts[0]);
        for (int i2 = 1; i2 < this.mNumericParts.length; i2++) {
            sb.append('.');
            sb.append(this.mNumericParts[i2]);
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && (obj instanceof DelimitedVersion) && compareTo((DelimitedVersion) obj) == 0;
    }

    public int hashCode() {
        int i2 = 0;
        for (long j2 : this.mNumericParts) {
            i2 = (i2 * PRIME_HASH_FACTOR) + ((int) (j2 & (-1)));
        }
        return i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(DelimitedVersion delimitedVersion) {
        long[] jArr;
        int i2 = 0;
        while (true) {
            jArr = this.mNumericParts;
            if (i2 >= jArr.length) {
                break;
            }
            long[] jArr2 = delimitedVersion.mNumericParts;
            if (i2 >= jArr2.length) {
                break;
            }
            int compareLongs = compareLongs(jArr[i2], jArr2[i2]);
            if (compareLongs != 0) {
                return compareLongs;
            }
            i2++;
        }
        return compareLongs(jArr.length, delimitedVersion.mNumericParts.length);
    }

    public static DelimitedVersion parse(String str) {
        if (str == null) {
            return new DelimitedVersion(new long[0]);
        }
        String[] split = str.split("[^0-9]+");
        long[] jArr = new long[split.length];
        int i2 = 0;
        for (String str2 : split) {
            if (!str2.isEmpty()) {
                jArr[i2] = Long.parseLong(str2);
                i2++;
            }
        }
        do {
            i2--;
            if (i2 < 0) {
                break;
            }
        } while (jArr[i2] <= 0);
        int i3 = i2 + 1;
        long[] jArr2 = new long[i3];
        System.arraycopy(jArr, 0, jArr2, 0, i3);
        return new DelimitedVersion(jArr2);
    }
}
