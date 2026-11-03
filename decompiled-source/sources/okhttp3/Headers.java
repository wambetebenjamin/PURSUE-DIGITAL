package okhttp3;

import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
/* loaded from: classes10.dex */
public final class Headers {
    private final String[] namesAndValues;

    Headers(Builder builder) {
        this.namesAndValues = (String[]) builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    private Headers(String[] strArr) {
        this.namesAndValues = strArr;
    }

    @Nullable
    public String get(String str) {
        return get(this.namesAndValues, str);
    }

    @Nullable
    public Date getDate(String str) {
        String str2 = get(str);
        if (str2 != null) {
            return HttpDate.parse(str2);
        }
        return null;
    }

    public int size() {
        return this.namesAndValues.length / 2;
    }

    public String name(int i2) {
        return this.namesAndValues[i2 * 2];
    }

    public String value(int i2) {
        return this.namesAndValues[(i2 * 2) + 1];
    }

    public Set<String> names() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            treeSet.add(name(i2));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public List<String> values(String str) {
        int size = size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equalsIgnoreCase(name(i2))) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(value(i2));
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public long byteCount() {
        String[] strArr = this.namesAndValues;
        long length = strArr.length * 2;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            length += this.namesAndValues[i2].length();
        }
        return length;
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.namesAndValues);
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Headers) && Arrays.equals(((Headers) obj).namesAndValues, this.namesAndValues);
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(name(i2)).append(": ").append(value(i2)).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    public Map<String, List<String>> toMultimap() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            String lowerCase = name(i2).toLowerCase(Locale.US);
            List list = (List) treeMap.get(lowerCase);
            if (list == null) {
                list = new ArrayList(2);
                treeMap.put(lowerCase, list);
            }
            list.add(value(i2));
        }
        return treeMap;
    }

    private static String get(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }

    public static Headers of(String... strArr) {
        Objects.requireNonNull(strArr, "namesAndValues == null");
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            if (strArr2[i2] == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i2] = strArr2[i2].trim();
        }
        for (int i3 = 0; i3 < strArr2.length; i3 += 2) {
            String str = strArr2[i3];
            String str2 = strArr2[i3 + 1];
            checkName(str);
            checkValue(str2, str);
        }
        return new Headers(strArr2);
    }

    public static Headers of(Map<String, String> map) {
        Objects.requireNonNull(map, "headers == null");
        String[] strArr = new String[map.size() * 2];
        int i2 = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            String trim = entry.getKey().trim();
            String trim2 = entry.getValue().trim();
            checkName(trim);
            checkValue(trim2, trim);
            strArr[i2] = trim;
            strArr[i2 + 1] = trim2;
            i2 += 2;
        }
        return new Headers(strArr);
    }

    static void checkName(String str) {
        Objects.requireNonNull(str, "name == null");
        if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt <= ' ' || charAt >= 127) {
                throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i2), str));
            }
        }
    }

    static void checkValue(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("value for name " + str2 + " == null");
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if ((charAt <= 31 && charAt != '\t') || charAt >= 127) {
                throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(charAt), Integer.valueOf(i2), str2, str));
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class Builder {
        final List<String> namesAndValues = new ArrayList(20);

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder addLenient(String str) {
            int indexOf = str.indexOf(CertificateUtil.DELIMITER, 1);
            if (indexOf != -1) {
                return addLenient(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(CertificateUtil.DELIMITER)) {
                return addLenient("", str.substring(1));
            }
            return addLenient("", str);
        }

        public Builder add(String str) {
            int indexOf = str.indexOf(CertificateUtil.DELIMITER);
            if (indexOf == -1) {
                throw new IllegalArgumentException("Unexpected header: " + str);
            }
            return add(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
        }

        public Builder add(String str, String str2) {
            Headers.checkName(str);
            Headers.checkValue(str2, str);
            return addLenient(str, str2);
        }

        public Builder addUnsafeNonAscii(String str, String str2) {
            Headers.checkName(str);
            return addLenient(str, str2);
        }

        public Builder addAll(Headers headers) {
            int size = headers.size();
            for (int i2 = 0; i2 < size; i2++) {
                addLenient(headers.name(i2), headers.value(i2));
            }
            return this;
        }

        public Builder add(String str, Date date) {
            if (date == null) {
                throw new NullPointerException("value for name " + str + " == null");
            }
            add(str, HttpDate.format(date));
            return this;
        }

        public Builder set(String str, Date date) {
            if (date == null) {
                throw new NullPointerException("value for name " + str + " == null");
            }
            set(str, HttpDate.format(date));
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder addLenient(String str, String str2) {
            this.namesAndValues.add(str);
            this.namesAndValues.add(str2.trim());
            return this;
        }

        public Builder removeAll(String str) {
            int i2 = 0;
            while (i2 < this.namesAndValues.size()) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(i2))) {
                    this.namesAndValues.remove(i2);
                    this.namesAndValues.remove(i2);
                    i2 -= 2;
                }
                i2 += 2;
            }
            return this;
        }

        public Builder set(String str, String str2) {
            Headers.checkName(str);
            Headers.checkValue(str2, str);
            removeAll(str);
            addLenient(str, str2);
            return this;
        }

        public String get(String str) {
            for (int size = this.namesAndValues.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase(this.namesAndValues.get(size))) {
                    return this.namesAndValues.get(size + 1);
                }
            }
            return null;
        }

        public Headers build() {
            return new Headers(this);
        }
    }
}
