package androidx.core.util;
/* loaded from: classes.dex */
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F f2, S s) {
        this.first = f2;
        this.second = s;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            return ObjectsCompat.equals(pair.first, this.first) && ObjectsCompat.equals(pair.second, this.second);
        }
        return false;
    }

    public int hashCode() {
        F f2 = this.first;
        int hashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.second;
        return hashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }

    public static <A, B> Pair<A, B> create(A a2, B b2) {
        return new Pair<>(a2, b2);
    }
}
