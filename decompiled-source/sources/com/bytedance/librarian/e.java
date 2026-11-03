package com.bytedance.librarian;

import java.util.ArrayList;
/* compiled from: LibrarianUnsatisfiedLinkError.java */
/* loaded from: classes2.dex */
class e extends UnsatisfiedLinkError {

    /* renamed from: a  reason: collision with root package name */
    private static final ArrayList<e> f9483a = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(String str) {
        super(str);
        ArrayList<e> arrayList = f9483a;
        synchronized (arrayList) {
            arrayList.add(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(String str, Throwable th) {
        super(str);
        initCause(th);
        ArrayList<e> arrayList = f9483a;
        synchronized (arrayList) {
            arrayList.add(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnsatisfiedLinkError[] a() {
        UnsatisfiedLinkError[] unsatisfiedLinkErrorArr;
        ArrayList<e> arrayList = f9483a;
        synchronized (arrayList) {
            unsatisfiedLinkErrorArr = (UnsatisfiedLinkError[]) arrayList.toArray(new UnsatisfiedLinkError[0]);
        }
        return unsatisfiedLinkErrorArr;
    }
}
