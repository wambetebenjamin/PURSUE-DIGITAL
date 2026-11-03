package com.bytedance.apm.agent.tracing;

import com.bytedance.apm.util.ai;
import java.util.HashSet;
import java.util.Stack;
import org.json.JSONObject;
/* compiled from: TraceMachine.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f342a = "TraceMachine";

    /* renamed from: b  reason: collision with root package name */
    private static ThreadLocal<Stack<d>> f343b = new ThreadLocal<>();

    /* renamed from: c  reason: collision with root package name */
    private static HashSet<String> f344c = new HashSet<>(32);

    /* renamed from: d  reason: collision with root package name */
    private static com.bytedance.apm.agent.logging.a f345d = com.bytedance.apm.agent.logging.b.getAgentLog();

    public static void enterMethod(String str, String str2, String str3, String str4) {
        d dVar = new d(str, str2, str3, System.currentTimeMillis(), str4);
        if (f343b.get() == null) {
            Stack<d> stack = new Stack<>();
            stack.push(dVar);
            f343b.set(stack);
            return;
        }
        f343b.get().push(dVar);
    }

    public static void enterMethod(String str, String str2, String str3) {
        enterMethod(str, str, str2, str3);
    }

    public static void exitMethod() {
        exitMethod("");
    }

    public static void exitMethod(String str) {
        if (f343b.get() == null) {
            return;
        }
        Stack<d> stack = f343b.get();
        if (stack.isEmpty()) {
            return;
        }
        d pop = stack.pop();
        pop.setExitTime(System.currentTimeMillis());
        pop.setExtraData(str);
        a(pop);
    }

    @Deprecated
    private static void a(d dVar) {
        try {
            long j2 = dVar.entryTime;
            long j3 = dVar.exitTime;
            long longValue = Long.valueOf(j3 - j2).longValue();
            if (longValue > 0) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.bytedance.apm.constant.a.BEGIN_TIME, j2);
                jSONObject.put("end_time", j3);
                JSONObject jSONObject2 = new JSONObject();
                if ("page_load".equals(dVar.traceType)) {
                    jSONObject2.put(dVar.methodName, longValue);
                } else {
                    jSONObject2.put(dVar.className + "#" + dVar.methodName, longValue);
                }
                JSONObject jSONObject3 = new JSONObject();
                if ("page_load".equals(dVar.traceType)) {
                    jSONObject3.put("scene", dVar.scene);
                    jSONObject3.put("is_first", !f344c.contains(new StringBuilder().append(dVar.className).append(dVar.methodName).toString()));
                    f344c.add(dVar.className + dVar.methodName);
                } else {
                    jSONObject3.put("is_main", ai.isMainThread());
                }
                jSONObject.put("extra_data", dVar.extraData);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
