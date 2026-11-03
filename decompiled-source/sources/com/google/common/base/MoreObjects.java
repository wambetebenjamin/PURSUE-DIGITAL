package com.google.common.base;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
public final class MoreObjects {
    public static <T> T firstNonNull(@CheckForNull T t, T t2) {
        if (t != null) {
            return t;
        }
        java.util.Objects.requireNonNull(t2, "Both parameters are null");
        return t2;
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }

    /* loaded from: classes6.dex */
    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitEmptyValues;
        private boolean omitNullValues;

        private ToStringHelper(String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.omitEmptyValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public ToStringHelper add(String str, @CheckForNull Object obj) {
            return addHolder(str, obj);
        }

        public ToStringHelper add(String str, boolean z) {
            return addUnconditionalHolder(str, String.valueOf(z));
        }

        public ToStringHelper add(String str, char c2) {
            return addUnconditionalHolder(str, String.valueOf(c2));
        }

        public ToStringHelper add(String str, double d2) {
            return addUnconditionalHolder(str, String.valueOf(d2));
        }

        public ToStringHelper add(String str, float f2) {
            return addUnconditionalHolder(str, String.valueOf(f2));
        }

        public ToStringHelper add(String str, int i2) {
            return addUnconditionalHolder(str, String.valueOf(i2));
        }

        public ToStringHelper add(String str, long j2) {
            return addUnconditionalHolder(str, String.valueOf(j2));
        }

        public ToStringHelper addValue(@CheckForNull Object obj) {
            return addHolder(obj);
        }

        public ToStringHelper addValue(boolean z) {
            return addUnconditionalHolder(String.valueOf(z));
        }

        public ToStringHelper addValue(char c2) {
            return addUnconditionalHolder(String.valueOf(c2));
        }

        public ToStringHelper addValue(double d2) {
            return addUnconditionalHolder(String.valueOf(d2));
        }

        public ToStringHelper addValue(float f2) {
            return addUnconditionalHolder(String.valueOf(f2));
        }

        public ToStringHelper addValue(int i2) {
            return addUnconditionalHolder(String.valueOf(i2));
        }

        public ToStringHelper addValue(long j2) {
            return addUnconditionalHolder(String.valueOf(j2));
        }

        private static boolean isEmpty(Object obj) {
            if (obj instanceof CharSequence) {
                return ((CharSequence) obj).length() == 0;
            } else if (obj instanceof Collection) {
                return ((Collection) obj).isEmpty();
            } else {
                if (obj instanceof Map) {
                    return ((Map) obj).isEmpty();
                }
                if (obj instanceof Optional) {
                    return !((Optional) obj).isPresent();
                }
                return obj.getClass().isArray() && Array.getLength(obj) == 0;
            }
        }

        public String toString() {
            boolean z = this.omitNullValues;
            boolean z2 = this.omitEmptyValues;
            StringBuilder append = new StringBuilder(32).append(this.className).append('{');
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object obj = valueHolder.value;
                if (!(valueHolder instanceof UnconditionalValueHolder)) {
                    if (obj == null) {
                        if (z) {
                        }
                    } else if (z2 && isEmpty(obj)) {
                    }
                }
                append.append(str);
                if (valueHolder.name != null) {
                    append.append(valueHolder.name).append('=');
                }
                if (obj != null && obj.getClass().isArray()) {
                    String deepToString = Arrays.deepToString(new Object[]{obj});
                    append.append((CharSequence) deepToString, 1, deepToString.length() - 1);
                } else {
                    append.append(obj);
                }
                str = ", ";
            }
            return append.append('}').toString();
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(@CheckForNull Object obj) {
            addHolder().value = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, @CheckForNull Object obj) {
            ValueHolder addHolder = addHolder();
            addHolder.value = obj;
            addHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        private UnconditionalValueHolder addUnconditionalHolder() {
            UnconditionalValueHolder unconditionalValueHolder = new UnconditionalValueHolder();
            this.holderTail.next = unconditionalValueHolder;
            this.holderTail = unconditionalValueHolder;
            return unconditionalValueHolder;
        }

        private ToStringHelper addUnconditionalHolder(Object obj) {
            addUnconditionalHolder().value = obj;
            return this;
        }

        private ToStringHelper addUnconditionalHolder(String str, Object obj) {
            UnconditionalValueHolder addUnconditionalHolder = addUnconditionalHolder();
            addUnconditionalHolder.value = obj;
            addUnconditionalHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static class ValueHolder {
            @CheckForNull
            String name;
            @CheckForNull
            ValueHolder next;
            @CheckForNull
            Object value;

            private ValueHolder() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes6.dex */
        public static final class UnconditionalValueHolder extends ValueHolder {
            private UnconditionalValueHolder() {
                super();
            }
        }
    }

    private MoreObjects() {
    }
}
