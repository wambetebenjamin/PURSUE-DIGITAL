package com.bytedance.crash.util;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: Stack.java */
/* loaded from: classes2.dex */
public final class ae {
    public static final String DIED_PROCESS_STACK = "DiedProcess.unknownReason: App was killed, please see logcat.\n";
    public static final String NO_STACK = "InvalidStack.NoStackAvailable: Not OOM.\n";
    public static final String NO_STACK_ANR = "at InvalidStack.NoStackAvailable(Invalid.java:1).\n";
    public static final String NO_STACK_OOM = "InvalidStack.NoStackAvailable: Is OOM.\n";

    /* renamed from: a  reason: collision with root package name */
    private static final String f7284a = "Suppressed: ";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7285b = "Caused by: ";

    /* renamed from: c  reason: collision with root package name */
    private static final int f7286c = 256;

    /* renamed from: d  reason: collision with root package name */
    private static final int f7287d = 128;

    /* renamed from: e  reason: collision with root package name */
    private static final StackTraceElement f7288e = new StackTraceElement("", "", "", 0);

    private ae() {
    }

    public static String getStackInfo(StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            getStackTraceElementInfo(stackTraceElement, sb);
        }
        return sb.toString();
    }

    public static StringBuilder getStackTraceElementInfo(StackTraceElement stackTraceElement, StringBuilder sb) {
        sb.append("  at ").append(stackTraceElement.getClassName()).append(c.a.DOT).append(stackTraceElement.getMethodName()).append("(").append(stackTraceElement.getFileName()).append(CertificateUtil.DELIMITER).append(stackTraceElement.getLineNumber()).append(")\n");
        return sb;
    }

    public static String getStackTraceElementInfo(StackTraceElement stackTraceElement) {
        return getStackTraceElementInfo(stackTraceElement, new StringBuilder()).toString();
    }

    public static String getExceptionStackStart(Throwable th, int i2) {
        if (th == null) {
            return null;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        return a(th, stackTrace, i2, stackTrace.length);
    }

    private static String a(Throwable th, StackTraceElement[] stackTraceElementArr, int i2, int i3) {
        if (i2 < 0 || i2 >= stackTraceElementArr.length || i3 <= i2 || i3 > stackTraceElementArr.length) {
            return null;
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTraceElementArr, i2, i3));
        String exceptionStack = getExceptionStack(th);
        th.setStackTrace(stackTraceElementArr);
        return exceptionStack;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:9:0x001b
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String getExceptionStack(java.lang.Throwable r2) {
        /*
            java.io.StringWriter r0 = new java.io.StringWriter
            r0.<init>()
            java.io.PrintWriter r1 = new java.io.PrintWriter
            r1.<init>(r0)
            a(r2, r1)     // Catch: java.lang.Throwable -> L15
            java.lang.String r2 = r0.toString()     // Catch: java.lang.Throwable -> L15
            r1.close()
            return r2
        L15:
            java.lang.String r2 = ""
            r1.close()
            return r2
        L1b:
            r2 = move-exception
            r1.close()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.util.ae.getExceptionStack(java.lang.Throwable):java.lang.String");
    }

    public static StackTraceElement[] getExceptionTraceElement(Throwable th) {
        ArrayList arrayList = new ArrayList();
        try {
            a(th, arrayList);
        } catch (Throwable unused) {
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0029 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject getAllStackTraces(java.lang.String r16) {
        /*
            r0 = r16
            java.lang.String r1 = ")"
            java.lang.String r2 = "("
            r3 = 0
            java.util.Map r4 = java.lang.Thread.getAllStackTraces()     // Catch: java.lang.Throwable -> Le4
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Le4
            r5.<init>()     // Catch: java.lang.Throwable -> Le4
            if (r4 != 0) goto L13
            return r3
        L13:
            java.lang.String r6 = "thread_all_count"
            int r7 = r4.size()     // Catch: java.lang.Throwable -> Le4
            r5.put(r6, r7)     // Catch: java.lang.Throwable -> Le4
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch: java.lang.Throwable -> Le4
            r6.<init>()     // Catch: java.lang.Throwable -> Le4
            java.util.Set r4 = r4.entrySet()     // Catch: java.lang.Throwable -> Le4
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> Le4
        L29:
            boolean r7 = r4.hasNext()     // Catch: java.lang.Throwable -> Le4
            if (r7 == 0) goto Lde
            java.lang.Object r7 = r4.next()     // Catch: java.lang.Throwable -> Le4
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch: java.lang.Throwable -> Le4
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Le4
            r8.<init>()     // Catch: java.lang.Throwable -> Le4
            java.lang.Object r9 = r7.getKey()     // Catch: java.lang.Throwable -> Le4
            java.lang.Thread r9 = (java.lang.Thread) r9     // Catch: java.lang.Throwable -> Le4
            java.lang.String r10 = r9.getName()     // Catch: java.lang.Throwable -> Le4
            if (r0 == 0) goto L59
            boolean r11 = r0.equals(r10)     // Catch: java.lang.Throwable -> Le4
            if (r11 != 0) goto L29
            boolean r11 = r10.startsWith(r0)     // Catch: java.lang.Throwable -> Le4
            if (r11 != 0) goto L29
            boolean r10 = r10.endsWith(r0)     // Catch: java.lang.Throwable -> Le4
            if (r10 == 0) goto L59
            goto L29
        L59:
            java.lang.String r10 = "thread_name"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le4
            r11.<init>()     // Catch: java.lang.Throwable -> Le4
            java.lang.String r12 = r9.getName()     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r11 = r11.append(r2)     // Catch: java.lang.Throwable -> Le4
            long r12 = r9.getId()     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r11.append(r12)     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r9.append(r1)     // Catch: java.lang.Throwable -> Le4
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Le4
            r8.put(r10, r9)     // Catch: java.lang.Throwable -> Le4
            java.lang.Object r7 = r7.getValue()     // Catch: java.lang.Throwable -> Le4
            java.lang.StackTraceElement[] r7 = (java.lang.StackTraceElement[]) r7     // Catch: java.lang.Throwable -> Le4
            if (r7 == 0) goto Ld6
            org.json.JSONArray r10 = new org.json.JSONArray     // Catch: java.lang.Throwable -> Le4
            r10.<init>()     // Catch: java.lang.Throwable -> Le4
            int r11 = r7.length     // Catch: java.lang.Throwable -> Le4
            r12 = 0
            r13 = r12
        L8f:
            if (r13 >= r11) goto Lc8
            r14 = r7[r13]     // Catch: java.lang.Throwable -> Le4
            java.lang.String r15 = r14.getClassName()     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Le4
            r9.<init>()     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r9.append(r15)     // Catch: java.lang.Throwable -> Le4
            java.lang.String r15 = "."
            java.lang.StringBuilder r9 = r9.append(r15)     // Catch: java.lang.Throwable -> Le4
            java.lang.String r15 = r14.getMethodName()     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r9.append(r15)     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r9.append(r2)     // Catch: java.lang.Throwable -> Le4
            int r14 = r14.getLineNumber()     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r9.append(r14)     // Catch: java.lang.Throwable -> Le4
            java.lang.StringBuilder r9 = r9.append(r1)     // Catch: java.lang.Throwable -> Le4
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Le4
            r10.put(r9)     // Catch: java.lang.Throwable -> Le4
            int r13 = r13 + 1
            goto L8f
        Lc8:
            java.lang.String r7 = "thread_stack"
            r8.put(r7, r10)     // Catch: java.lang.Throwable -> Le4
            int r7 = r10.length()     // Catch: java.lang.Throwable -> Le4
            if (r7 <= 0) goto Ld4
            goto Ld6
        Ld4:
            r9 = r12
            goto Ld7
        Ld6:
            r9 = 1
        Ld7:
            if (r9 == 0) goto L29
            r6.put(r8)     // Catch: java.lang.Throwable -> Le4
            goto L29
        Lde:
            java.lang.String r0 = "thread_stacks"
            r5.put(r0, r6)     // Catch: java.lang.Throwable -> Le4
            return r5
        Le4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.util.ae.getAllStackTraces(java.lang.String):org.json.JSONObject");
    }

    private static void a(Throwable th, List<StackTraceElement> list) {
        if (th == null || list == null) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        newSetFromMap.add(th);
        list.add(f7288e);
        StackTraceElement[] stackTrace = th.getStackTrace();
        boolean z = stackTrace.length > 384;
        int length = stackTrace.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (z && i3 > 256) {
                list.add(f7288e);
                break;
            }
            list.add(stackTraceElement);
            i3++;
            i2++;
        }
        if (z) {
            for (int length2 = stackTrace.length - 128; length2 < stackTrace.length; length2++) {
                list.add(stackTrace[length2]);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                a(th2, list, stackTrace, f7284a, "\t", newSetFromMap, 128);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(cause, list, stackTrace, f7285b, "", newSetFromMap, 128);
        }
    }

    private static void a(Throwable th, List<StackTraceElement> list, StackTraceElement[] stackTraceElementArr, String str, String str2, Set<Throwable> set, int i2) {
        if (set.contains(th)) {
            list.add(f7288e);
            return;
        }
        set.add(th);
        StackTraceElement[] stackTrace = th.getStackTrace();
        boolean z = stackTrace.length > i2;
        list.add(f7288e);
        int length = stackTrace.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i3];
            if (z && i4 > i2) {
                list.add(f7288e);
                break;
            }
            list.add(stackTraceElement);
            i4++;
            i3++;
        }
        if (z) {
            for (int length2 = stackTrace.length - (i2 / 2); length2 < stackTrace.length; length2++) {
                list.add(stackTrace[length2]);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                int i5 = i2 / 2;
                a(th2, list, stackTrace, f7284a, str2 + "\t", set, i5 > 10 ? i5 : 10);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            int i6 = i2 / 2;
            a(cause, list, stackTrace, f7285b, str2, set, i6 > 10 ? i6 : 10);
        }
    }

    private static void a(Throwable th, PrintWriter printWriter) {
        if (th == null || printWriter == null) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        newSetFromMap.add(th);
        printWriter.println(th);
        StackTraceElement[] stackTrace = th.getStackTrace();
        boolean z = stackTrace.length > 384;
        int length = stackTrace.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (z && i3 > 256) {
                printWriter.println("\t... skip " + ((stackTrace.length - i3) - 128) + " lines");
                break;
            }
            printWriter.println("\tat " + stackTraceElement);
            i3++;
            i2++;
        }
        if (z) {
            for (int length2 = stackTrace.length - 128; length2 < stackTrace.length; length2++) {
                printWriter.println("\tat " + stackTrace[length2]);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                a(th2, printWriter, stackTrace, f7284a, "\t", newSetFromMap, 128);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(cause, printWriter, stackTrace, f7285b, "", newSetFromMap, 128);
        }
    }

    private static void a(Throwable th, PrintWriter printWriter, StackTraceElement[] stackTraceElementArr, String str, String str2, Set<Throwable> set, int i2) {
        if (set.contains(th)) {
            printWriter.println("\t[CIRCULAR REFERENCE:" + th + "]");
            return;
        }
        set.add(th);
        StackTraceElement[] stackTrace = th.getStackTrace();
        boolean z = stackTrace.length > i2;
        printWriter.println(str2 + str + th);
        int length = stackTrace.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i3];
            if (z && i4 > i2) {
                printWriter.println("\t... skip " + ((stackTrace.length - i4) - (i2 / 2)) + " lines");
                break;
            }
            printWriter.println("\tat " + stackTraceElement);
            i4++;
            i3++;
        }
        if (z) {
            for (int length2 = stackTrace.length - (i2 / 2); length2 < stackTrace.length; length2++) {
                printWriter.println("\tat " + stackTrace[length2]);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                int i5 = i2 / 2;
                a(th2, printWriter, stackTrace, f7284a, str2 + "\t", set, i5 > 10 ? i5 : 10);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            int i6 = i2 / 2;
            a(cause, printWriter, stackTrace, f7285b, str2, set, i6 > 10 ? i6 : 10);
        }
    }

    /* compiled from: Stack.java */
    /* loaded from: classes2.dex */
    public static class a {
        public int end;
        public int start;

        public a(int i2, int i3) {
            this.start = -1;
            this.end = -1;
            this.start = i2;
            this.end = i3;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("start", this.start);
                jSONObject.put("end", this.end);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public static JSONArray getMatchLines(StackTraceElement[] stackTraceElementArr, String str) {
        a aVar = new a(-1, -1);
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < stackTraceElementArr.length; i2++) {
            if (aVar.start == -1) {
                if (stackTraceElementArr[i2].getClassName().contains(str)) {
                    aVar.start = i2;
                    aVar.end = i2;
                }
            } else if (!stackTraceElementArr[i2].getClassName().contains(str)) {
                aVar.end = i2;
                jSONArray.put(aVar.toJson());
                aVar = new a(-1, -1);
            }
        }
        if (aVar.start != -1) {
            aVar.end = stackTraceElementArr.length;
            jSONArray.put(aVar.toJson());
        }
        return jSONArray;
    }

    public static JSONArray getMatchLines(String[] strArr, String str) {
        a aVar = new a(-1, -1);
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (aVar.start == -1) {
                if (strArr[i2].contains(str)) {
                    aVar.start = i2;
                    aVar.end = i2;
                }
            } else if (!strArr[i2].contains(str)) {
                aVar.end = i2;
                jSONArray.put(aVar.toJson());
                aVar = new a(-1, -1);
            }
        }
        if (aVar.start != -1) {
            aVar.end = strArr.length;
            jSONArray.put(aVar.toJson());
        }
        return jSONArray;
    }

    public static JSONArray a(StackTraceElement[] stackTraceElementArr, String[] strArr) {
        a aVar = new a(-1, -1);
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < stackTraceElementArr.length; i2++) {
            if (aVar.start == -1) {
                if (a(stackTraceElementArr[i2].getClassName(), strArr)) {
                    aVar.start = i2;
                    aVar.end = i2;
                }
            } else if (!a(stackTraceElementArr[i2].getClassName(), strArr)) {
                aVar.end = i2;
                jSONArray.put(aVar.toJson());
                aVar = new a(-1, -1);
            }
        }
        if (aVar.start != -1) {
            aVar.end = stackTraceElementArr.length;
            jSONArray.put(aVar.toJson());
        }
        return jSONArray;
    }

    public static boolean a(String str, String[] strArr) {
        if (strArr != null && !TextUtils.isEmpty(str)) {
            for (String str2 : strArr) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
