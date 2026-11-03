package com.google.common.collect;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.Comparator;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class ComparisonChain {
    private static final ComparisonChain ACTIVE = new ComparisonChain() { // from class: com.google.common.collect.ComparisonChain.1
        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return 0;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return classify(comparable.compareTo(comparable2));
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(@ParametricNullness T t, @ParametricNullness T t2, Comparator<T> comparator) {
            return classify(comparator.compare(t, t2));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int i2, int i3) {
            return classify(Ints.compare(i2, i3));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long j2, long j3) {
            return classify(Longs.compare(j2, j3));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(float f2, float f3) {
            return classify(Float.compare(f2, f3));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(double d2, double d3) {
            return classify(Double.compare(d2, d3));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return classify(Booleans.compare(z2, z));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return classify(Booleans.compare(z, z2));
        }

        ComparisonChain classify(int i2) {
            if (i2 < 0) {
                return ComparisonChain.LESS;
            }
            return i2 > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }
    };
    private static final ComparisonChain LESS = new InactiveComparisonChain(-1);
    private static final ComparisonChain GREATER = new InactiveComparisonChain(1);

    public abstract ComparisonChain compare(double d2, double d3);

    public abstract ComparisonChain compare(float f2, float f3);

    public abstract ComparisonChain compare(int i2, int i3);

    public abstract ComparisonChain compare(long j2, long j3);

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract <T> ComparisonChain compare(@ParametricNullness T t, @ParametricNullness T t2, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean z, boolean z2);

    public abstract ComparisonChain compareTrueFirst(boolean z, boolean z2);

    public abstract int result();

    private ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    /* loaded from: classes9.dex */
    private static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(double d2, double d3) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(float f2, float f3) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int i2, int i3) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long j2, long j3) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(@ParametricNullness T t, @ParametricNullness T t2, Comparator<T> comparator) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return this;
        }

        InactiveComparisonChain(int i2) {
            super();
            this.result = i2;
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return this.result;
        }
    }

    @Deprecated
    public final ComparisonChain compare(Boolean bool, Boolean bool2) {
        return compareFalseFirst(bool.booleanValue(), bool2.booleanValue());
    }
}
