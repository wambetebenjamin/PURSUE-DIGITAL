package com.bytedance.apm.util;
/* compiled from: AbsMinHeap.java */
/* loaded from: classes.dex */
public abstract class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private T[] f1640a;

    private int a(int i2) {
        return (i2 + 1) << 1;
    }

    private int b(int i2) {
        return ((i2 + 1) << 1) - 1;
    }

    abstract boolean a(T t, T t2);

    public a(T[] tArr) {
        this.f1640a = tArr;
        buildHeap(tArr.length);
    }

    public void buildHeap(int i2) {
        for (int i3 = (i2 / 2) - 1; i3 >= 0; i3--) {
            heapify(i3, i2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
        if (a(r2[r1], r2[r0]) != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0014, code lost:
        if (a(r2[r0], r2[r5]) != false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void heapify(int r5, int r6) {
        /*
            r4 = this;
            int r0 = r4.a(r5)
            int r1 = r4.b(r5)
            if (r0 >= r6) goto L17
            T[] r2 = r4.f1640a
            r3 = r2[r0]
            r2 = r2[r5]
            boolean r2 = r4.a(r3, r2)
            if (r2 == 0) goto L17
            goto L18
        L17:
            r0 = r5
        L18:
            if (r1 >= r6) goto L27
            T[] r2 = r4.f1640a
            r3 = r2[r1]
            r2 = r2[r0]
            boolean r2 = r4.a(r3, r2)
            if (r2 == 0) goto L27
            goto L28
        L27:
            r1 = r0
        L28:
            if (r1 != r5) goto L2b
            return
        L2b:
            r4.swap(r5, r1)
            r4.heapify(r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.apm.util.a.heapify(int, int):void");
    }

    public void swap(int i2, int i3) {
        T[] tArr = this.f1640a;
        T t = tArr[i2];
        tArr[i2] = tArr[i3];
        tArr[i3] = t;
    }

    public T getRoot() {
        return this.f1640a[0];
    }

    public void setRoot(T t) {
        T[] tArr = this.f1640a;
        tArr[0] = t;
        heapify(0, tArr.length);
    }
}
