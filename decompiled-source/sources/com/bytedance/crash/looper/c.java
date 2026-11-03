package com.bytedance.crash.looper;

import com.bytedance.apm.constant.i;
import com.bytedance.apm.constant.r;
import com.bytedance.crash.util.q;
import com.facebook.internal.ServerProtocol;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: LooperMessageParser.java */
/* loaded from: classes2.dex */
public class c {
    private static String a(int i2, long j2, long j3) {
        return i2 == 1 ? j2 <= 1 ? "idle" : 2 * j2 > j3 ? "native_busy" : j2 * 10 < j3 ? "native_lock" : "native_unknown" : i2 == 8 ? 2 * j2 > j3 ? "java_busy" : j2 * 10 < j3 ? "java_lock" : "java_unknown" : "normal";
    }

    private static String a(JSONArray jSONArray, long j2, long j3) {
        if (jSONArray == null) {
            return null;
        }
        long j4 = j3 + j2;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            long optLong = optJSONObject.optLong(com.bytedance.sdk.account.save.database.a.FIELD_TIME);
            if (optLong >= j2 && optLong <= j4) {
                return optJSONObject.optString(r.STATS_KEY_TRACE);
            }
        }
        return null;
    }

    public static void a(JSONArray jSONArray, JSONObject jSONObject, long j2, boolean z) {
        long optLong;
        try {
            if (z) {
                optLong = jSONObject.optLong("currentMessageCost");
            } else {
                optLong = jSONObject.optLong("duration");
            }
            String a2 = a(jSONArray, j2, optLong);
            if (a2 != null) {
                q.a(jSONObject, "block_stack", (Object) a2);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(JSONObject jSONObject) {
        long j2;
        long j3;
        int i2;
        long j4;
        JSONArray optJSONArray = jSONObject.optJSONArray("history_message");
        JSONObject optJSONObject = jSONObject.optJSONObject("current_message");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("history_details");
        String str = "block_stack";
        JSONArray optJSONArray2 = jSONObject.optJSONArray("block_stack");
        if (optJSONObject2 == null) {
            JSONObject jSONObject2 = new JSONObject();
            q.a(jSONObject2, "longestMsgType", (Object) "no_details");
            q.a(jSONObject2, "longestMsg", (Object) "no_details");
            q.a(jSONObject, "history_details", jSONObject2);
        } else if (optJSONArray == null || optJSONArray.length() == 0) {
            q.a(optJSONObject2, "longestMsgType", (Object) "no_history");
            q.a(optJSONObject2, "longestMsg", (Object) "no_history");
        } else {
            long optLong = jSONObject.optLong("crash_time");
            long optLong2 = optJSONObject2.optLong("last_wall_time");
            long optLong3 = optJSONObject2.optLong("last_cpu_time");
            long j5 = 0;
            if (optLong == 0 || optLong2 == 0 || optLong3 == 0) {
                q.a(optJSONObject2, "longestMsgType", (Object) "no_last_times");
                q.a(optJSONObject2, "longestMsg", (Object) "no_last_times");
                return;
            }
            q.a(optJSONObject2, "longestMsgType", (Object) "not_found");
            q.a(optJSONObject2, "longestMsg", (Object) "not_found");
            if (optLong > optLong2 && optJSONObject != null) {
                j5 = optJSONObject.optLong("currentMessageCost");
                long j6 = optLong - optLong2;
                if (j6 < j5) {
                    j5 = j6;
                }
                a(optJSONArray2, optJSONObject, optLong2, true);
            }
            if (j5 < 5000) {
                long j7 = optLong - optLong2;
                int length = optJSONArray.length() - 1;
                long j8 = j5;
                int i3 = -1;
                while (length >= 0 && j7 < 10000) {
                    long j9 = optLong2;
                    JSONObject optJSONObject3 = optJSONArray.optJSONObject(length);
                    if (optJSONObject3 == null) {
                        j4 = optLong3;
                    } else {
                        long optLong4 = optJSONObject3.optLong("duration");
                        j7 += optLong4;
                        j4 = optLong3;
                        a(optJSONArray2, optJSONObject3, optLong - j7, false);
                        if (j7 > i.DEFAULT_BLOCK_INTERVAL_MS && optLong4 > j8) {
                            i3 = length;
                            j8 = optLong4;
                        }
                    }
                    length--;
                    optLong2 = j9;
                    optLong3 = j4;
                }
                j2 = optLong2;
                j3 = optLong3;
                i2 = i3;
            } else {
                j2 = optLong2;
                j3 = optLong3;
                i2 = -1;
            }
            long j10 = j5;
            if (i2 < 0) {
                if (optJSONObject != null) {
                    q.a(optJSONObject2, "longestMsgWallDuration", Long.valueOf(j10));
                    q.a(optJSONObject2, "longestMessageCpuDuration", (Object) (-1));
                    q.a(optJSONObject2, "longestMsgStartCpuTime", Long.valueOf(j3));
                    q.a(optJSONObject2, "longestMsgEndCpuTime", (Object) (-1));
                    q.a(optJSONObject2, "longestMsgStartWallTime", Long.valueOf(j2));
                    q.a(optJSONObject2, "longestMsgEndWallTime", Long.valueOf(j2 + j10));
                    q.a(optJSONObject2, "longestMsgType", (Object) i.KEY_CURRENT_GALVANIC);
                    q.a(optJSONObject2, "longestMsg", (Object) optJSONObject.optString("message"));
                    q.a(optJSONObject2, "longestMsgBlockStack", (Object) optJSONObject.optString("block_stack"));
                    return;
                }
                return;
            }
            int length2 = optJSONArray.length() - 1;
            while (length2 >= i2) {
                String str2 = str;
                JSONObject optJSONObject4 = optJSONArray.optJSONObject(length2);
                if (optJSONObject4 != null) {
                    j2 -= optJSONObject4.optLong("duration");
                    j3 -= optJSONObject4.optLong("cpuDuration");
                }
                length2--;
                str = str2;
            }
            String str3 = str;
            JSONObject optJSONObject5 = optJSONArray.optJSONObject(i2);
            if (optJSONObject5 != null) {
                long optLong5 = optJSONObject5.optLong("duration");
                long optLong6 = optJSONObject5.optLong("cpuDuration");
                q.a(optJSONObject2, "longestMsgWallDuration", Long.valueOf(optLong5));
                q.a(optJSONObject2, "longestMessageCpuDuration", Long.valueOf(optLong6));
                q.a(optJSONObject2, "longestMsgStartCpuTime", Long.valueOf(j3));
                q.a(optJSONObject2, "longestMsgEndCpuTime", Long.valueOf(j3 + optLong6));
                q.a(optJSONObject2, "longestMsgStartWallTime", Long.valueOf(j2));
                q.a(optJSONObject2, "longestMsgEndWallTime", Long.valueOf(j2 + optLong5));
                q.a(optJSONObject2, "longestMsgType", (Object) a(optJSONObject5.optInt("type"), optLong6, optLong5));
                q.a(optJSONObject2, "longestMsg", (Object) optJSONObject5.optString("message"));
                q.a(optJSONObject2, "longestMsgBlockStack", (Object) optJSONObject5.optString(str3));
            }
        }
    }

    private static boolean b(JSONObject jSONObject, JSONObject jSONObject2) {
        long optLong = jSONObject.optLong("main_thread_cpu_tick") * jSONObject.optInt(com.bytedance.crash.constants.c.p);
        long optLong2 = jSONObject2.optLong("longestMsgStartCpuTime");
        long optLong3 = jSONObject2.optLong("longestMsgEndCpuTime");
        if (optLong == 0 || optLong2 == 0) {
            return false;
        }
        return optLong >= (optLong2 / 10) * 10 && optLong < (optLong3 / 10) * 10;
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject optJSONObject;
        JSONArray optJSONArray = jSONObject.optJSONArray("history_message");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            q.a(jSONObject2, "has_history_message", (Object) ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        } else {
            q.a(jSONObject2, "has_history_message", (Object) "false");
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("pending_messages");
        if (optJSONArray2 != null && optJSONArray2.length() > 0 && (optJSONObject = optJSONArray2.optJSONObject(0)) != null) {
            q.a(jSONObject2, "npth_message_anr_with_barrier_new", (Object) String.valueOf(optJSONObject.optString("target").isEmpty()));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("history_details");
        if (optJSONObject2 != null) {
            q.a(jSONObject2, "npth_message_longest_msg", (Object) optJSONObject2.optString("longestMsg"));
            q.a(jSONObject2, "npth_message_longest_msg_type", (Object) optJSONObject2.optString("longestMsgType"));
            q.a(jSONObject2, "npth_message_trace_is_evil", (Object) String.valueOf(b(jSONObject, optJSONObject2)));
            q.a(jSONObject2, "npth_message_has_block_stack", (Object) String.valueOf(!"".equals(optJSONObject2.optString("longestMsgBlockStack"))));
        }
    }
}
