package com.bytedance.apm.agent.tracing;

import android.text.TextUtils;
import com.bytedance.apm.constant.r;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes.dex */
public class AutoPageTraceHelper {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f328a;

    /* renamed from: b  reason: collision with root package name */
    private static final HashSet<String> f329b = new HashSet<>(32);

    /* renamed from: c  reason: collision with root package name */
    private static final ConcurrentLinkedQueue<c> f330c = new ConcurrentLinkedQueue<>();

    /* renamed from: d  reason: collision with root package name */
    private static long f331d = r.PAGE_LOAD_MAX_LIMIT_MS;

    public static void setMaxValidTimeMs(long j2) {
        f331d = j2;
    }

    public static void onTrace(String str, String str2, boolean z) {
        if (TextUtils.equals("onCreate", str2)) {
            if (z) {
                if (!f328a) {
                    a.launcherActivityOnCreateStart(str);
                }
                ConcurrentLinkedQueue<c> concurrentLinkedQueue = f330c;
                if (concurrentLinkedQueue.size() > 50) {
                    concurrentLinkedQueue.poll();
                }
                concurrentLinkedQueue.add(new c(str, System.currentTimeMillis()));
                return;
            }
            if (!f328a) {
                a.launcherActivityOnCreateEnd();
            }
            c peek = f330c.peek();
            if (peek != null) {
                peek.onCreateEndTs = System.currentTimeMillis();
            }
        } else if (TextUtils.equals("onResume", str2)) {
            if (z) {
                if (!f328a) {
                    a.launcherActivityOnResumeStart(str);
                }
                c peek2 = f330c.peek();
                if (peek2 != null) {
                    peek2.onResumeStartTs = System.currentTimeMillis();
                    return;
                }
                return;
            }
            if (!f328a) {
                a.launcherActivityOnResumeEnd();
            }
            c peek3 = f330c.peek();
            if (peek3 != null) {
                peek3.onResumeEndTs = System.currentTimeMillis();
            }
        } else if (!TextUtils.equals(com.bytedance.apm.constant.a.ON_WINDOW_FOCUS_CHANGED, str2)) {
            if (TextUtils.equals(com.bytedance.apm.constant.a.ON_START, str2)) {
                if (z) {
                    if (f328a) {
                        return;
                    }
                    a.launcherActivityOnStartStart(str);
                } else if (f328a) {
                } else {
                    a.launcherActivityOnStartEnd(str);
                }
            }
        } else if (z) {
            if (!f328a) {
                a.launcherActivityOnWindowFocusChangedStart(str);
                f328a = true;
            }
            c peek4 = f330c.peek();
            if (peek4 == null || peek4.onWindowFocusTs != 0) {
                return;
            }
            peek4.onWindowFocusTs = System.currentTimeMillis();
            if (com.bytedance.apm.trace.mapping.a.queryViewId(str) == null) {
                com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.tracing.AutoPageTraceHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AutoPageTraceHelper.b();
                    }
                });
            }
        }
    }

    public static void reportViewIdStats(long j2, String str) {
        c peek = f330c.peek();
        if (peek == null || !TextUtils.equals(str, peek.pageName)) {
            return;
        }
        peek.onViewShowTs = j2;
        com.bytedance.apm.thread.b.getInstance().post(new Runnable() { // from class: com.bytedance.apm.agent.tracing.AutoPageTraceHelper.2
            @Override // java.lang.Runnable
            public void run() {
                AutoPageTraceHelper.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x011d A[Catch: Exception -> 0x0130, TryCatch #0 {Exception -> 0x0130, blocks: (B:3:0x0006, B:5:0x000f, B:7:0x0019, B:9:0x0021, B:12:0x0029, B:15:0x0037, B:17:0x0082, B:18:0x0094, B:20:0x00b0, B:22:0x00be, B:30:0x00e4, B:34:0x00f7, B:36:0x011d, B:37:0x0126, B:25:0x00ca, B:27:0x00d8), top: B:44:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b() {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.apm.agent.tracing.AutoPageTraceHelper.b():void");
    }
}
