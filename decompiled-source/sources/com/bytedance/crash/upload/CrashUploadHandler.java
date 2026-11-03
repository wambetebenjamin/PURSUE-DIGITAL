package com.bytedance.crash.upload;

import com.bytedance.crash.CrashType;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes2.dex */
public abstract class CrashUploadHandler {
    private static final ConcurrentLinkedQueue<CrashUploadHandler> sHandlers = new ConcurrentLinkedQueue<>();
    private static volatile boolean sUploadEnd = false;
    private static final LinkedList<CrashInfo> sUploadData = new LinkedList<>();

    public abstract void afterUploadOne(CrashType crashType, CrashInfo crashInfo);

    public void uploadEnd() {
    }

    public static void set(CrashUploadHandler crashUploadHandler) {
        if (sUploadEnd) {
            com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.upload.CrashUploadHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = CrashUploadHandler.sUploadData.iterator();
                    while (it.hasNext()) {
                        CrashInfo crashInfo = (CrashInfo) it.next();
                        CrashUploadHandler.this.afterUploadOne(crashInfo.mCrashType, crashInfo);
                    }
                    CrashUploadHandler.this.uploadEnd();
                }
            });
        } else {
            sHandlers.add(crashUploadHandler);
        }
    }

    public static void afterUploadEnd() {
        sUploadEnd = true;
        ConcurrentLinkedQueue<CrashUploadHandler> concurrentLinkedQueue = sHandlers;
        if (concurrentLinkedQueue.isEmpty()) {
            return;
        }
        Iterator<CrashUploadHandler> it = concurrentLinkedQueue.iterator();
        while (it.hasNext()) {
            try {
                it.next().uploadEnd();
            } catch (Throwable unused) {
            }
        }
    }

    public static void afterUploadOne(CrashType crashType) {
        CrashInfo crashInfo = new CrashInfo(crashType);
        sUploadData.add(crashInfo);
        ConcurrentLinkedQueue<CrashUploadHandler> concurrentLinkedQueue = sHandlers;
        if (concurrentLinkedQueue.isEmpty()) {
            return;
        }
        Iterator<CrashUploadHandler> it = concurrentLinkedQueue.iterator();
        while (it.hasNext()) {
            try {
                it.next().afterUploadOne(crashType, crashInfo);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class CrashInfo {
        private final CrashType mCrashType;

        public CrashInfo(CrashType crashType) {
            this.mCrashType = crashType;
        }
    }
}
