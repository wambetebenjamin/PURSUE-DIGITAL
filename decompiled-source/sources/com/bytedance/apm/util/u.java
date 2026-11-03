package com.bytedance.apm.util;
/* compiled from: ObjectsCompat.java */
/* loaded from: classes.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    private static final a f1716a = new a();

    private u() {
    }

    public static boolean equals(Object obj, Object obj2) {
        return f1716a.equals(obj, obj2);
    }

    /* compiled from: ObjectsCompat.java */
    /* loaded from: classes.dex */
    private static class a {
        private a() {
        }

        public boolean equals(Object obj, Object obj2) {
            return obj == obj2 || (obj != null && obj.equals(obj2));
        }
    }
}
