package com.bytedance.perf.monitor;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
/* compiled from: AnrEntryManager.java */
/* loaded from: classes2.dex */
public class b {
    public static long FOUND_DURATION = 600000;
    public static int MAX_ANR_SIZE = 3;
    public static int MAX_QUEUE_SIZE = 10;
    public static LinkedBlockingQueue<a> mAnrEntries = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);

    public static void putAnr(a aVar) {
        if (mAnrEntries == null) {
            mAnrEntries = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
        }
        try {
            if (mAnrEntries.size() == MAX_QUEUE_SIZE) {
                mAnrEntries.take();
            }
            mAnrEntries.put(aVar);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public static String getCostTop2Stack(a aVar) {
        if (a(aVar)) {
            return aVar.stack;
        }
        StringBuilder sb = new StringBuilder(aVar == null ? "" : aVar.stack);
        ArrayList arrayList = new ArrayList();
        Iterator<a> it = mAnrEntries.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (next != null) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList);
        int min = Math.min(MAX_ANR_SIZE, arrayList.size());
        for (int i2 = 0; i2 < min - 1; i2++) {
            if (arrayList.get(i2) != null) {
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).append(((a) arrayList.get(i2)).stack);
            }
        }
        return sb.toString();
    }

    private static boolean a(a aVar) {
        LinkedBlockingQueue<a> linkedBlockingQueue = mAnrEntries;
        return (linkedBlockingQueue == null || linkedBlockingQueue.isEmpty()) && aVar != null;
    }

    public static void clear() {
        mAnrEntries.clear();
    }
}
