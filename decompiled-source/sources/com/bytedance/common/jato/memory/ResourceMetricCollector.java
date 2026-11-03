package com.bytedance.common.jato.memory;
/* loaded from: classes.dex */
public class ResourceMetricCollector {

    /* renamed from: a  reason: collision with root package name */
    private static final long[] f4877a = new long[4];

    private static native void nativeGetMallocInfo(long[] jArr);

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public long f4878a;

        /* renamed from: b  reason: collision with root package name */
        public long f4879b;

        /* renamed from: c  reason: collision with root package name */
        public long f4880c;

        /* renamed from: d  reason: collision with root package name */
        public long f4881d;

        public String toString() {
            return "LightMemoryItem{nativeMMapped=" + this.f4878a + ", nativeTotalAllocated=" + this.f4879b + ", nativeTotalFree=" + this.f4880c + ", nativeMaxTotalAllocated=" + this.f4881d + '}';
        }
    }

    public static a a() {
        a aVar = new a();
        long[] jArr = f4877a;
        a(jArr);
        aVar.f4878a = jArr[0];
        aVar.f4879b = jArr[1];
        aVar.f4880c = jArr[2];
        aVar.f4881d = jArr[3];
        return aVar;
    }

    private static void a(long[] jArr) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            nativeGetMallocInfo(jArr);
        }
    }
}
