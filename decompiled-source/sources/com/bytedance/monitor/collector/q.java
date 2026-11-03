package com.bytedance.monitor.collector;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/* compiled from: SamplingStackUtil.java */
/* loaded from: classes2.dex */
public class q {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SamplingStackUtil.java */
    /* loaded from: classes2.dex */
    public static class a implements Comparable<a> {

        /* renamed from: a  reason: collision with root package name */
        boolean f9636a;

        /* renamed from: b  reason: collision with root package name */
        int f9637b;

        /* renamed from: c  reason: collision with root package name */
        long f9638c;

        /* renamed from: d  reason: collision with root package name */
        long f9639d;

        /* renamed from: e  reason: collision with root package name */
        long f9640e;

        /* renamed from: f  reason: collision with root package name */
        long f9641f;

        /* renamed from: g  reason: collision with root package name */
        long f9642g;

        public a(boolean z, int i2, long j2, long j3, long j4, long j5) {
            this.f9636a = false;
            this.f9636a = z;
            this.f9637b = i2;
            this.f9638c = j2;
            this.f9639d = j3;
            this.f9640e = j4;
            this.f9641f = j5;
            this.f9642g = (j4 - j3) / 1000000;
        }

        @Override // java.lang.Comparable
        public int compareTo(a aVar) {
            long j2 = this.f9639d;
            long j3 = aVar.f9639d;
            if (j2 > j3) {
                return 1;
            }
            return j2 == j3 ? 0 : -1;
        }

        public String toString() {
            return this.f9637b + "," + (this.f9636a ? "sys" : "app") + "," + this.f9638c + "," + this.f9639d + "," + this.f9640e + "," + this.f9641f;
        }
    }

    public static String trimStack(String str, StringBuilder sb, int i2, long j2) {
        LinkedList<a> a2 = a(str);
        while (a2.size() > i2) {
            long j3 = 21;
            Iterator<a> it = a2.iterator();
            while (it.hasNext()) {
                if (it.next().f9642g < j3) {
                    it.remove();
                }
            }
        }
        Iterator<a> it2 = a2.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().toString()).append('\n');
        }
        if (a2.size() == 0) {
            return null;
        }
        return getStackKey(a2, j2);
    }

    private static LinkedList<a> a(String str) {
        LinkedList<a> linkedList = new LinkedList<>();
        for (String str2 : str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
            String[] split = str2.split(",");
            if (split != null && split.length >= 6) {
                linkedList.add(new a(split[1].startsWith("sys"), Integer.parseInt(split[0]), Long.parseLong(split[2]), Long.parseLong(split[3]), Long.parseLong(split[4]), Long.parseLong(split[5])));
            }
        }
        Collections.sort(linkedList);
        return linkedList;
    }

    public static String getStackString(String str) {
        if (str == null) {
            return null;
        }
        Iterator<a> it = a(str).iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append('\n');
        }
        return sb.toString();
    }

    public static String getStackKey(List<a> list, long j2) {
        Iterator it;
        StringBuilder sb = new StringBuilder();
        long j3 = ((float) j2) * 0.3f;
        LinkedList linkedList = new LinkedList();
        for (a aVar : list) {
            if (aVar.f9640e - aVar.f9639d >= j3) {
                linkedList.add(aVar);
            }
        }
        Collections.sort(linkedList, new Comparator<a>() { // from class: com.bytedance.monitor.collector.q.1
            @Override // java.util.Comparator
            public int compare(a aVar2, a aVar3) {
                return Long.compare((aVar3.f9637b + 1) * (aVar3.f9640e - aVar3.f9639d), (aVar2.f9637b + 1) * (aVar2.f9640e - aVar2.f9639d));
            }
        });
        if (linkedList.isEmpty() && !list.isEmpty()) {
            linkedList.add(list.get(0));
        }
        if (linkedList.iterator().hasNext()) {
            sb.append(((a) it.next()).f9638c + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}
