package com.bytedance.perf.collector;

import android.util.Log;
import com.bytedance.apm.block.trace.h;
import com.bytedance.apm.block.trace.i;
import com.bytedance.apm.c;
import com.bytedance.apm.logging.g;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.meituan.robust.Constants;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/* compiled from: TraceDataUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9961a = "TraceDataUtils";

    /* compiled from: TraceDataUtils.java */
    /* renamed from: com.bytedance.perf.collector.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0141a {
        void fallback(List<i> list, int i2);

        int getFilterMaxCount();

        boolean isFilter(long j2, int i2);
    }

    private static boolean a(long j2) {
        return ((j2 >> 63) & 1) == 1;
    }

    private static long b(long j2) {
        return j2 & 8796093022207L;
    }

    public static int getMethodId(long j2) {
        return (int) ((j2 >> 43) & 1048575);
    }

    public static void structuredDataToStack(long[] jArr, LinkedList<i> linkedList, long j2, int i2) {
        structuredDataToStack(h.METHOD_ID_DISPATCH, jArr, linkedList, true, j2, i2);
    }

    public static void structuredDataToStack(long[] jArr, LinkedList<i> linkedList, boolean z, long j2) {
        structuredDataToStack(h.METHOD_ID_DISPATCH, jArr, linkedList, z, j2, -1);
    }

    public static void structuredDataToStack(int i2, long[] jArr, LinkedList<i> linkedList, boolean z, long j2, int i3) {
        long j3;
        int methodId;
        if (jArr == null || jArr.length == 0) {
            return;
        }
        int i4 = 1;
        long j4 = 0;
        long b2 = jArr[jArr.length - 1] != 0 ? b(jArr[jArr.length - 1]) : Long.MAX_VALUE;
        int i5 = 0;
        int i6 = 0;
        while (i6 < jArr.length && b(jArr[i6]) >= b2) {
            i6++;
        }
        int i7 = i2 <= 0 ? h.METHOD_ID_DISPATCH : i2;
        LinkedList linkedList2 = new LinkedList();
        int i8 = 0;
        int i9 = 0;
        while (i6 < jArr.length) {
            long j5 = jArr[i6];
            if (j4 != j5) {
                if (z) {
                    if (a(j5) && i7 == getMethodId(j5)) {
                        i9 = i4;
                    }
                    if (i9 == 0) {
                        if (getMethodId(j5) > 1048000) {
                            Object[] objArr = new Object[2];
                            objArr[i5] = Integer.valueOf(getMethodId(j5));
                            objArr[i4] = Boolean.valueOf(a(j5));
                            g.i("SceneMethodsInfo", "s2 pass this method[%s], isIn [%b] ", objArr);
                        }
                    }
                }
                if (a(j5)) {
                    if (getMethodId(j5) == i7) {
                        linkedList2.clear();
                        i8 = i5;
                    }
                    i8 += i4;
                    linkedList2.push(Long.valueOf(j5));
                } else {
                    int methodId2 = getMethodId(j5);
                    if (!linkedList2.isEmpty()) {
                        long longValue = ((Long) linkedList2.pop()).longValue();
                        i8--;
                        LinkedList linkedList3 = new LinkedList();
                        linkedList3.push(Long.valueOf(longValue));
                        while (true) {
                            methodId = getMethodId(longValue);
                            if (methodId == methodId2 || linkedList2.isEmpty()) {
                                break;
                            }
                            if (c.isDebugMode()) {
                                Object[] objArr2 = new Object[2];
                                objArr2[i5] = Integer.valueOf(methodId);
                                objArr2[i4] = Integer.valueOf(methodId2);
                                g.w(f9961a, "pop inMethodId[%s] to continue match ouMethodId[%s]", objArr2);
                            }
                            longValue = ((Long) linkedList2.pop()).longValue();
                            i8--;
                            linkedList3.push(Long.valueOf(longValue));
                        }
                        if (methodId != methodId2 && methodId == i7) {
                            c.isDebugMode();
                            linkedList2.addAll(linkedList3);
                            i8 += linkedList2.size();
                        } else {
                            long b3 = b(j5) + h.getDiffTime();
                            while (linkedList3.size() != 0) {
                                long b4 = b(((Long) linkedList3.pop()).longValue()) + h.getDiffTime();
                                long j6 = b3 - b4;
                                if (j6 < 0) {
                                    if (c.isDebugMode()) {
                                        g.e(f9961a, "[structuredDataToStack] trace during invalid:%d", Long.valueOf(j6));
                                    }
                                    linkedList2.clear();
                                    linkedList.clear();
                                    return;
                                }
                                a(linkedList, new i(methodId2, (int) j6, b4, i8));
                            }
                        }
                        j3 = 0;
                    } else {
                        j3 = j4;
                        if (c.isDebugMode()) {
                            g.w(f9961a, "[structuredDataToStack] method[%s] not found in! ", Integer.valueOf(methodId2));
                        }
                    }
                    i6++;
                    j4 = j3;
                    i5 = 0;
                    i4 = 1;
                }
            }
            j3 = j4;
            i6++;
            j4 = j3;
            i5 = 0;
            i4 = 1;
        }
        g.i("SceneMethodsInfo", "structuredDataToStack inner, result.size: " + linkedList.size() + ", " + linkedList2.size(), new Object[0]);
        while (!linkedList2.isEmpty()) {
            long longValue2 = ((Long) linkedList2.pop()).longValue();
            int methodId3 = getMethodId(longValue2);
            boolean a2 = a(longValue2);
            long b5 = b(longValue2) + h.getDiffTime();
            if (c.isDebugMode()) {
                g.w(f9961a, "[structuredDataToStack] has never out method[%s], isIn:%s, inTime:%s, endTime:%s,rawData size:%s", Integer.valueOf(methodId3), Boolean.valueOf(a2), Long.valueOf(b5), Long.valueOf(j2), Integer.valueOf(linkedList2.size()));
            }
            if (a2) {
                if (!(i3 > 0) || i8 <= i3) {
                    a(linkedList, new i(methodId3, (int) (j2 - b5), b5, linkedList2.size()));
                }
            } else if (c.isDebugMode()) {
                g.e(f9961a, "[structuredDataToStack] why has out Method[%s]? is wrong! ", Integer.valueOf(methodId3));
            }
        }
        b bVar = new b(null, null);
        stackToTree(linkedList, bVar);
        linkedList.clear();
        a(bVar, linkedList);
    }

    private static int a(LinkedList<i> linkedList, i iVar) {
        if (c.isDebugMode()) {
            Log.v(f9961a, "method:" + iVar);
        }
        i peek = linkedList.isEmpty() ? null : linkedList.peek();
        if (peek != null && peek.methodId == iVar.methodId && peek.depth == iVar.depth && iVar.depth != 0) {
            iVar.durTime = iVar.durTime == 5000 ? peek.durTime : iVar.durTime;
            peek.mergeMore(iVar.durTime);
            return peek.durTime;
        }
        linkedList.push(iVar);
        return iVar.durTime;
    }

    private static void a(b bVar) {
        if (bVar.f9965c.isEmpty()) {
            return;
        }
        int size = bVar.f9965c.size();
        b[] bVarArr = new b[size];
        bVar.f9965c.toArray(bVarArr);
        bVar.f9965c.clear();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar2 = bVarArr[i2];
            bVar.f9965c.addFirst(bVar2);
            a(bVar2);
        }
    }

    private static void a(b bVar, LinkedList<i> linkedList) {
        LinkedList linkedList2 = new LinkedList();
        while (bVar != null) {
            if (bVar.f9963a != null) {
                linkedList.add(bVar.f9963a);
            }
            LinkedList<b> linkedList3 = bVar.f9965c;
            if (!linkedList3.isEmpty()) {
                for (int size = linkedList3.size() - 1; size >= 0; size--) {
                    linkedList2.add(linkedList3.get(size));
                }
            }
            bVar = (b) linkedList2.pollLast();
        }
    }

    public static int stackToTree(LinkedList<i> linkedList, b bVar) {
        ListIterator<i> listIterator = linkedList.listIterator(0);
        b bVar2 = null;
        int i2 = 0;
        while (listIterator.hasNext()) {
            b bVar3 = new b(listIterator.next(), bVar2);
            i2++;
            if (bVar2 == null && bVar3.a() != 0) {
                if (c.isDebugMode()) {
                    g.e(f9961a, "[stackToTree] SceneMethodsInfo begin error! why the first node'depth is not 0!", new Object[0]);
                }
                return 0;
            }
            int a2 = bVar3.a();
            if (bVar2 == null || a2 == 0) {
                bVar.b(bVar3);
            } else if (bVar2.a() >= a2) {
                while (bVar2 != null && bVar2.a() > a2) {
                    bVar2 = bVar2.f9964b;
                }
                if (bVar2 != null) {
                    if (bVar2.f9964b != null) {
                        bVar3.f9964b = bVar2.f9964b;
                        bVar2.f9964b.b(bVar3);
                    }
                }
            } else {
                bVar2.b(bVar3);
            }
            bVar2 = bVar3;
        }
        g.i("SceneMethodsInfo", "[stackToTree] count [%d] ", Integer.valueOf(i2));
        return i2;
    }

    public static long stackToString(LinkedList<i> linkedList, StringBuilder sb) {
        Iterator<i> it = linkedList.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            i next = it.next();
            sb.append(next.toString()).append('\n');
            if (j2 < next.durTime) {
                j2 = next.durTime;
            }
        }
        return j2;
    }

    public static int countTreeNode(b bVar) {
        int size = bVar.f9965c.size();
        Iterator<b> it = bVar.f9965c.iterator();
        while (it.hasNext()) {
            size += countTreeNode(it.next());
        }
        return size;
    }

    /* compiled from: TraceDataUtils.java */
    /* loaded from: classes2.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        i f9963a;

        /* renamed from: b  reason: collision with root package name */
        b f9964b;

        /* renamed from: c  reason: collision with root package name */
        LinkedList<b> f9965c = new LinkedList<>();

        b(i iVar, b bVar) {
            this.f9963a = iVar;
            this.f9964b = bVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int a() {
            i iVar = this.f9963a;
            if (iVar == null) {
                return 0;
            }
            return iVar.depth;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(b bVar) {
            this.f9965c.addFirst(bVar);
        }

        private boolean b() {
            return this.f9965c.isEmpty();
        }
    }

    public static void printTree(b bVar, StringBuilder sb) {
        sb.append("|*   TraceStack: ").append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        printTree(bVar, 0, sb, "|*        ");
    }

    public static void printTree(b bVar, int i2, StringBuilder sb, String str) {
        StringBuilder sb2 = new StringBuilder(str);
        for (int i3 = 0; i3 <= i2; i3++) {
            sb2.append("    ");
        }
        for (int i4 = 0; i4 < bVar.f9965c.size(); i4++) {
            b bVar2 = bVar.f9965c.get(i4);
            sb.append(sb2.toString()).append(bVar2.f9963a.methodId).append(Constants.ARRAY_TYPE).append(bVar2.f9963a.durTime).append("]").append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (!bVar2.f9965c.isEmpty()) {
                printTree(bVar2, i2 + 1, sb, str);
            }
        }
    }

    public static void trimStack(List<i> list, int i2) {
        ListIterator<i> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            i previous = listIterator.previous();
            if (previous != null && previous.durTime < i2) {
                listIterator.remove();
            }
        }
    }

    public static void trimStack(List<i> list, int i2, InterfaceC0141a interfaceC0141a) {
        if (i2 < 0) {
            list.clear();
            return;
        }
        int size = list.size();
        int i3 = 1;
        while (size > i2) {
            ListIterator<i> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                if (interfaceC0141a.isFilter(listIterator.previous().durTime, i3)) {
                    listIterator.remove();
                    size--;
                    if (size <= i2) {
                        return;
                    }
                }
            }
            size = list.size();
            i3++;
            if (interfaceC0141a.getFilterMaxCount() < i3) {
                break;
            }
        }
        int size2 = list.size();
        if (size2 > i2) {
            interfaceC0141a.fallback(list, size2);
        }
    }

    public static void simplyTrimStack(List<i> list, int i2) {
        if (i2 < 0) {
            list.clear();
            return;
        }
        ListIterator<i> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (listIterator.previous().durTime < i2) {
                listIterator.remove();
            }
        }
    }

    @Deprecated
    public static String getTreeKey(List<i> list, final int i2) {
        StringBuilder sb = new StringBuilder();
        LinkedList linkedList = new LinkedList(list);
        trimStack(linkedList, i2, new InterfaceC0141a() { // from class: com.bytedance.perf.collector.a.1
            @Override // com.bytedance.perf.collector.a.InterfaceC0141a
            public int getFilterMaxCount() {
                return 60;
            }

            @Override // com.bytedance.perf.collector.a.InterfaceC0141a
            public boolean isFilter(long j2, int i3) {
                return j2 < ((long) (i3 * 5));
            }

            @Override // com.bytedance.perf.collector.a.InterfaceC0141a
            public void fallback(List<i> list2, int i3) {
                if (c.isDebugMode()) {
                    g.w(a.f9961a, "[getTreeKey] size:%s targetSize:%s", Integer.valueOf(i3), Integer.valueOf(i2));
                }
                ListIterator<i> listIterator = list2.listIterator(Math.min(i3, i2));
                while (listIterator.hasNext()) {
                    listIterator.next();
                    listIterator.remove();
                }
            }
        });
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            sb.append(((i) it.next()).methodId + "|");
        }
        return sb.toString();
    }

    public static String getTreeKey(List<i> list, long j2) {
        Iterator it;
        StringBuilder sb = new StringBuilder();
        long j3 = ((float) j2) * 0.3f;
        LinkedList linkedList = new LinkedList();
        for (i iVar : list) {
            if (iVar.durTime >= j3) {
                linkedList.add(iVar);
            }
        }
        Collections.sort(linkedList, new Comparator<i>() { // from class: com.bytedance.perf.collector.a.2
            @Override // java.util.Comparator
            public int compare(i iVar2, i iVar3) {
                return Integer.compare((iVar3.depth + 1) * iVar3.durTime, (iVar2.depth + 1) * iVar2.durTime);
            }
        });
        if (linkedList.isEmpty() && !list.isEmpty()) {
            linkedList.add(list.get(0));
        } else if (linkedList.size() > 1 && ((i) linkedList.peek()).methodId == 1048574) {
            linkedList.removeFirst();
        }
        if (linkedList.iterator().hasNext()) {
            sb.append(((i) it.next()).methodId + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}
