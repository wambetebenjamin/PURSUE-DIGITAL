package com.bytedance.crash.entity;

import android.text.TextUtils;
import com.bytedance.crash.TagService;
import com.bytedance.crash.dumper.Scraps;
import com.bytedance.crash.dumper.h;
import com.bytedance.crash.dumper.n;
import com.bytedance.crash.dumper.p;
import com.bytedance.crash.dumper.t;
import com.bytedance.crash.general.HardwareInfo;
import com.bytedance.crash.jni.NativeBridge;
import com.bytedance.crash.util.o;
import com.bytedance.crash.util.q;
import com.facebook.internal.ServerProtocol;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONObject;
/* compiled from: CrashFilter.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6642a = "filters";

    /* renamed from: b  reason: collision with root package name */
    public static final String f6643b = "is_x86_devices";

    /* renamed from: c  reason: collision with root package name */
    public static final String f6644c = "is_64_runtime";

    /* renamed from: d  reason: collision with root package name */
    public static final String f6645d = "is_root";

    private static String a(long j2) {
        return j2 < 0 ? "( - 0s)" : j2 < 60000 ? "(0s - 1m)" : j2 < 600000 ? "[1m - 10m)" : j2 < 3600000 ? "[10m - 1h)" : j2 < 10800000 ? "[1h - 3h)" : j2 < 43200000 ? "[3h - 12h)" : j2 < 86400000 ? "[12h - 1d)" : j2 < 259200000 ? "[1d - 3d)" : j2 < com.bytedance.common.utility.date.a.WEEK ? "[3d - 7d)" : j2 < 1209600000 ? "[7d - 14d)" : j2 < com.bytedance.common.utility.date.a.MONTH ? "[14d - 30d)" : "[30d - )";
    }

    private static String a(long j2, long j3) {
        float f2 = (float) j3;
        float f3 = (float) j2;
        return f2 > 0.95f * f3 ? "(95% - 100%]" : f2 > 0.9f * f3 ? "(90% - 95%]" : f2 > 0.8f * f3 ? "(80% - 90%]" : f2 > 0.7f * f3 ? "(70% - 80%]" : f2 > 0.6f * f3 ? "(60% - 70%]" : f2 > f3 * 0.5f ? "(50% - 60%]" : "(0% - 50%]";
    }

    private static boolean a(File file, String str, int i2) {
        File file2 = new File(file, str);
        return file2.exists() && file2.length() > ((long) i2);
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2, File file) {
        JSONObject a2 = q.a(jSONObject2, "filters");
        h.b(jSONObject2, file);
        h.a(jSONObject2, file);
        q.b(a2, com.bytedance.crash.constants.c.f6392i, (Object) true);
        q.b(a2, "is_root", Boolean.valueOf(com.bytedance.crash.general.a.d()));
        q.b(a2, "alog_inited", Boolean.valueOf(com.bytedance.crash.alog.f.b()));
        q.b(a2, "sdk_version", com.bytedance.crash.config.h.f6371a);
        q.b(a2, b.CRASH_THREAD_NAME, jSONObject2.optString(b.CRASH_THREAD_NAME, "unknown"));
        q.b(a2, "is_64_runtime", Boolean.valueOf(NativeBridge.i()));
        q.b(a2, com.bytedance.crash.constants.c.M, jSONObject.optString(com.bytedance.crash.constants.c.M));
        q.b(a2, "version_type", jSONObject.optString("version_type"));
        q.b(a2, "kernel_version", jSONObject.optString("kernel_version"));
        q.b(a2, "app_version_exact", jSONObject.optString("app_version_exact"));
        q.b(a2, "crash_md5", jSONObject2.optString("crash_md5"));
        q.b(a2, HardwareInfo.KEY_HW_CPU_MODEL, jSONObject.optString(HardwareInfo.KEY_HW_CPU_MODEL));
        q.b(a2, HardwareInfo.KEY_HW_CPU_HARDWARE, jSONObject.optString(HardwareInfo.KEY_HW_CPU_HARDWARE));
        q.b(a2, HardwareInfo.KEY_HW_RAM_SIZE, jSONObject.optString(HardwareInfo.KEY_HW_RAM_SIZE));
        q.b(a2, com.bytedance.crash.tracker.a.f7153c, com.bytedance.crash.tracker.a.c(jSONObject2));
        String i2 = NativeBridge.i(file.getAbsolutePath());
        if (i2 != null) {
            q.b(a2, b.r, i2);
        }
        JSONObject optJSONObject = jSONObject2.optJSONObject(Scraps.STORAGE);
        if (optJSONObject != null) {
            q.b(a2, Scraps.INNER_TOTAL, optJSONObject.optString(Scraps.INNER_TOTAL));
            q.b(a2, Scraps.INNER_FREE, optJSONObject.optString(Scraps.INNER_FREE));
        }
        String optString = jSONObject.optString("cpu_abi");
        if (!TextUtils.isEmpty(optString)) {
            boolean contains = optString.contains("64");
            q.b(a2, "is_x86_devices", Boolean.valueOf(optString.contains("86")));
            q.b(a2, com.bytedance.crash.constants.c.I, Boolean.valueOf(contains));
            a(a2, jSONObject2, file, contains);
        }
        a(a2, jSONObject, jSONObject2);
        com.bytedance.crash.util.c.a(a2);
        c(a2, file);
        d(a2, file);
        e(a2, file);
        a(a2, file);
        f(a2, file);
        g(a2, file);
        d(a2, jSONObject2);
        e(a2, jSONObject2);
        com.bytedance.crash.resource.a.a(a2, file);
        c(a2, jSONObject2);
        b(a2, jSONObject, jSONObject2);
        b(a2, jSONObject2);
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        long optLong = jSONObject3.optLong("crash_time");
        long optLong2 = jSONObject3.optLong("app_start_time");
        int i2 = (optLong > 0L ? 1 : (optLong == 0L ? 0 : -1));
        if (i2 != 0 && optLong2 != 0) {
            q.b(jSONObject, "use_time", Long.valueOf((optLong - optLong2) / 1000));
        }
        long optLong3 = jSONObject2.optLong(Header.f6637c);
        if (optLong3 == 0) {
            return;
        }
        int i3 = (optLong2 > 0L ? 1 : (optLong2 == 0L ? 0 : -1));
        String str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        if (i3 != 0) {
            q.b(jSONObject, "valid_app_info", optLong2 > optLong3 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        }
        long a2 = com.bytedance.crash.general.a.a(0L);
        if (a2 == 0) {
            return;
        }
        int i4 = (optLong3 > a2 ? 1 : (optLong3 == a2 ? 0 : -1));
        q.b(jSONObject, "crash_same_upload", i4 == 0 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        if (i4 == 0 || i2 == 0) {
            return;
        }
        long a3 = com.bytedance.crash.general.a.a(optLong);
        if (a3 != 0) {
            if (optLong3 == a3) {
                str = "false";
            }
            q.b(jSONObject, "crash_when_update", str);
        }
    }

    private static void b(JSONObject jSONObject, JSONObject jSONObject2) {
        String optString = jSONObject2.optString("process_name");
        long optLong = jSONObject2.optLong("app_start_time");
        if (TextUtils.isEmpty(optString) || optLong == 0) {
            return;
        }
        TagService.a(jSONObject, optLong, optString);
    }

    private static void b(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        long optLong = jSONObject2.optLong(Header.f6637c);
        long optLong2 = jSONObject3.optLong("crash_time");
        if (optLong > 0) {
            q.b(jSONObject, "crash_since_updated", a(optLong2 - optLong));
        }
    }

    private static void c(JSONObject jSONObject, JSONObject jSONObject2) {
        long optLong = jSONObject2.optLong("crash_time");
        long currentTimeMillis = System.currentTimeMillis();
        if (optLong <= 0 || currentTimeMillis <= optLong) {
            return;
        }
        q.b(jSONObject, "crash_length", a(currentTimeMillis - optLong));
    }

    private static void d(JSONObject jSONObject, JSONObject jSONObject2) {
        q.b(jSONObject, "has_logcat", Boolean.valueOf(!q.isEmpty(jSONObject2.optJSONArray(com.bytedance.crash.constants.c.x))));
    }

    private static void a(JSONObject jSONObject, File file) {
        q.b(jSONObject, "has_tombstone_file", Boolean.valueOf(a(file, "tombstone.txt", 10)));
        q.b(jSONObject, "has_dlog_file", Boolean.valueOf(a(file, "dlog.txt", 10)));
        q.b(jSONObject, "has_logcat_file", Boolean.valueOf(a(file, "logcat.txt", 512)));
        q.b(jSONObject, "has_logcat_file_os", Boolean.valueOf(a(file, "logcat_os.txt", 512)));
        q.b(jSONObject, "has_malloc_file", Boolean.valueOf(a(file, "malloc.txt", 64)));
        q.b(jSONObject, "has_meminfo_file", Boolean.valueOf(a(file, "meminfo.txt", 64)));
        q.b(jSONObject, "has_maps_file", Boolean.valueOf(a(file, "maps.txt", 1024)));
        q.b(jSONObject, "has_smaps_file", Boolean.valueOf(a(file, "smaps.txt", 1024)));
        q.b(jSONObject, "has_pthreads_file", Boolean.valueOf(a(file, "pthreads.txt", 64)));
        q.b(jSONObject, "has_threads_file", Boolean.valueOf(a(file, "threads.txt", 64)));
        q.b(jSONObject, "has_fds_file", Boolean.valueOf(a(file, "fds.txt", 64)));
        q.b(jSONObject, "has_view_tree", Boolean.valueOf(a(file, t.f6603a, 64)));
        q.b(jSONObject, "has_pthread_key_map_file", Boolean.valueOf(a(file, "pthread_key_map.txt", 10)));
        q.b(jSONObject, "has_pthread_key_file", Boolean.valueOf(a(file, "pthread_key_info.txt", 10)));
        q.b(jSONObject, "has_routine_file", Boolean.valueOf(a(file, "rountines.txt", 64)));
        q.b(jSONObject, "has_gfx_file", Boolean.valueOf(a(file, "gfxinfo.txt", 64)));
        q.b(jSONObject, "has_os_memory", Boolean.valueOf(a(file, "os_memory.txt", 64)));
        q.b(jSONObject, "has_environ", Boolean.valueOf(a(file, "environ.txt", 64)));
        q.b(jSONObject, "has_signal_monitor_file", Boolean.valueOf(a(file, "signal_monitor.txt", 64)));
        q.b(jSONObject, "has_heaptrack_file", Boolean.valueOf(a(file, "leak_heap.txt", 64)));
        q.b(jSONObject, "has_setpriority_file", Boolean.valueOf(a(file, "priority_stack.txt", 64)));
        q.b(jSONObject, "has_setpriority_file", Boolean.valueOf(a(file, "priority_jstack.txt", 64)));
        q.b(jSONObject, "has_memtrack_dump", Boolean.valueOf(a(file, "sys_memtrack.txt", 16)));
    }

    private static void b(JSONObject jSONObject, File file) {
        String[] o = NativeBridge.o(file.getAbsolutePath());
        if (o == null || o.length != 5) {
            return;
        }
        q.b(jSONObject, "native_oom_reason", o[0]);
        q.b(jSONObject, "vma_leak_reason", o[1]);
        q.b(jSONObject, "mem_rss_reason", o[2]);
        q.b(jSONObject, "mem_pss_reason", o[3]);
        q.b(jSONObject, "mem_ref_reason", o[4]);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(org.json.JSONObject r10, org.json.JSONObject r11, java.io.File r12, boolean r13) {
        /*
            boolean r0 = com.bytedance.crash.jni.NativeBridge.i()
            long r1 = com.bytedance.crash.dumper.n.e(r12)
            int r3 = com.bytedance.crash.dumper.n.g(r12)
            boolean r3 = com.bytedance.crash.dumper.n.g(r3)
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            java.lang.String r5 = "vma_leak"
            com.bytedance.crash.util.q.b(r10, r5, r4)
            r4 = 0
            int r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r6 <= 0) goto L42
            if (r0 == 0) goto L2b
            java.lang.String r1 = com.bytedance.crash.dumper.n.a(r1)
            java.lang.String r2 = "vm_peak_range"
            com.bytedance.crash.util.q.b(r10, r2, r1)
            goto L42
        L2b:
            java.lang.String r6 = com.bytedance.crash.dumper.n.a(r1, r13)
            java.lang.String r7 = "vm_peak_usage"
            com.bytedance.crash.util.q.b(r10, r7, r6)
            boolean r1 = com.bytedance.crash.dumper.n.b(r1, r13)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            java.lang.String r6 = "memory_leak"
            com.bytedance.crash.util.q.b(r10, r6, r2)
            goto L43
        L42:
            r1 = 0
        L43:
            long r6 = com.bytedance.crash.dumper.n.d(r12)
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            java.lang.String r8 = "vmsize_range"
            if (r2 <= 0) goto L5f
            if (r0 == 0) goto L57
            java.lang.String r13 = com.bytedance.crash.dumper.n.a(r6)
            com.bytedance.crash.util.q.b(r10, r8, r13)
            goto L64
        L57:
            java.lang.String r13 = com.bytedance.crash.dumper.n.a(r6, r13)
            com.bytedance.crash.util.q.b(r10, r8, r13)
            goto L64
        L5f:
            java.lang.String r13 = "(0 - 0)"
            com.bytedance.crash.util.q.b(r10, r8, r13)
        L64:
            java.lang.String r13 = "memory_size"
            com.bytedance.crash.util.q.a(r11, r13, r6)
            long r6 = com.bytedance.crash.dumper.n.f(r12)
            java.lang.String r13 = "rss"
            com.bytedance.crash.util.q.a(r11, r13, r6)
            r8 = 1024(0x400, double:5.06E-321)
            long r8 = r8 * r6
            boolean r11 = com.bytedance.crash.dumper.n.c(r8)
            int r13 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r13 <= 0) goto L86
            java.lang.String r13 = com.bytedance.crash.dumper.n.b(r6)
            java.lang.String r0 = "rss_size_range"
            com.bytedance.crash.util.q.b(r10, r0, r13)
        L86:
            if (r1 != 0) goto L8c
            if (r3 != 0) goto L8c
            if (r11 == 0) goto L8f
        L8c:
            b(r10, r12)
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.entity.a.a(org.json.JSONObject, org.json.JSONObject, java.io.File, boolean):void");
    }

    private static void c(JSONObject jSONObject, File file) {
        int a2 = n.a(file);
        if (a2 != 0) {
            q.b(jSONObject, "thread_count_range", n.a(a2));
            boolean b2 = n.b(a2);
            q.b(jSONObject, "threads_leak", Boolean.valueOf(b2));
            if (b2) {
                q.b(jSONObject, "threads_leak_name", NativeBridge.c(file));
                q.b(jSONObject, "threads_leak_library", NativeBridge.d(file));
            }
        }
    }

    private static void d(JSONObject jSONObject, File file) {
        int b2 = n.b(file);
        if (b2 != 0) {
            q.b(jSONObject, "fd_count_range", n.c(b2));
            boolean d2 = n.d(b2);
            q.b(jSONObject, "fd_leak", Boolean.valueOf(d2));
            if (d2) {
                q.b(jSONObject, "fd_leak_reason", NativeBridge.a(file));
            }
        }
    }

    private static void e(JSONObject jSONObject, File file) {
        int c2 = n.c(file);
        if (c2 != 0) {
            q.b(jSONObject, "pthread_key_range", n.e(c2));
            q.b(jSONObject, "has_pthread_key_leak", Boolean.valueOf(n.f(c2)));
            if (n.f(c2)) {
                q.b(jSONObject, "pthread_key_leak_lib", NativeBridge.b(file));
            }
        }
    }

    private static void f(JSONObject jSONObject, File file) {
        boolean z;
        int lastIndexOf;
        File file2 = new File(file, "dlog.txt");
        boolean z2 = false;
        String str = "none";
        if (file2.exists() && file2.length() > 0) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));
                z = false;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.contains("[dumper] onload")) {
                            z2 = true;
                        } else if (readLine.contains("[dumper] exit status:") && (lastIndexOf = readLine.lastIndexOf(58)) != -1) {
                            str = readLine.substring(lastIndexOf + 1).trim();
                            z = str.equals("0");
                        }
                    } catch (Throwable unused) {
                    }
                }
            } catch (Throwable unused2) {
            }
            q.b(jSONObject, "dumper_loaded", Boolean.valueOf(z2));
            q.b(jSONObject, "dumper_exit_code", str);
            q.b(jSONObject, "npth_dumper_success", Boolean.valueOf(z));
        }
        z = false;
        q.b(jSONObject, "dumper_loaded", Boolean.valueOf(z2));
        q.b(jSONObject, "dumper_exit_code", str);
        q.b(jSONObject, "npth_dumper_success", Boolean.valueOf(z));
    }

    private static void g(JSONObject jSONObject, File file) {
        p pVar = new p(file);
        if (pVar.a()) {
            q.b(jSONObject, "has_protector_history", Boolean.valueOf(pVar.c()));
            if (pVar.c()) {
                q.b(jSONObject, "protector_sid", pVar.d());
                q.b(jSONObject, "protector_performed_count", Integer.valueOf(pVar.e()));
                q.b(jSONObject, "protector_total_performed_count", Integer.valueOf(pVar.f()));
                q.b(jSONObject, "protector_performed", Boolean.valueOf(pVar.g()));
                q.b(jSONObject, "protector_final_performed", Boolean.valueOf(pVar.h()));
                q.b(jSONObject, "protector_origin_scope", pVar.j());
                q.b(jSONObject, "protector_previous_scope", pVar.i());
                if (pVar.g()) {
                    q.b(jSONObject, "protector_scope", pVar.k());
                }
                pVar.b();
            }
        }
    }

    private static void e(JSONObject jSONObject, JSONObject jSONObject2) {
        a(jSONObject, jSONObject2);
        JSONObject optJSONObject = jSONObject2.optJSONObject("app_memory_info");
        if (optJSONObject != null) {
            long optLong = optJSONObject.optLong("native_heap_size");
            q.b(jSONObject, "native_heap_range", c(optLong));
            if (optLong > 0) {
                q.b(jSONObject, "native_heap_range", c(optLong));
                q.b(jSONObject, "native_heap_leak", Boolean.valueOf(optLong > 268435456));
                long optLong2 = optJSONObject.optLong("native_heap_alloc_size");
                if (optLong2 > 0) {
                    q.b(jSONObject, "native_heap_alloc_leak", Boolean.valueOf(optLong2 > 268435456));
                    q.b(jSONObject, "native_heap_alloc_usage", a(optLong, optLong2));
                }
            }
        }
        JSONObject optJSONObject2 = jSONObject2.optJSONObject("memory_info");
        if (optJSONObject2 != null) {
            long optLong3 = optJSONObject2.optLong("totalPss");
            if (optLong3 > 0) {
                q.b(jSONObject, "total_pss_range", c(optLong3));
            }
        }
    }

    private static String b(long j2) {
        o oVar = new o("MB");
        oVar.a(0L, 512L, 64L);
        return oVar.a(j2);
    }

    private static String c(long j2) {
        o oVar = new o("MB");
        oVar.a(new Long[]{0L, 1L, 64L, 128L, 256L, 384L, 512L, 768L, 1024L, 1536L});
        return oVar.a(j2);
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject optJSONObject = jSONObject2.optJSONObject("app_memory_info");
        if (optJSONObject != null) {
            long optLong = optJSONObject.optLong("max_memory");
            long optLong2 = optJSONObject.optLong("free_memory");
            long optLong3 = optJSONObject.optLong("total_memory");
            long j2 = optLong3 - optLong2;
            if (j2 > 0) {
                q.b(jSONObject, "java_heap_range", b(j2 / 1048576));
                if (optLong > j2) {
                    q.b(jSONObject, "java_heap_alloc_usage", a(optLong3, j2));
                    q.b(jSONObject, "java_heap_usage", a(optLong, optLong3));
                }
            }
            q.b(jSONObject, "java_heap_leak", String.valueOf(((float) j2) > ((float) optLong) * 0.95f));
        }
    }
}
