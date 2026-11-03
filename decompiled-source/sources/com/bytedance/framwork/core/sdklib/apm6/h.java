package com.bytedance.framwork.core.sdklib.apm6;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: LogSenderHelper.java */
/* loaded from: classes2.dex */
class h {

    /* renamed from: a  reason: collision with root package name */
    private static final long f8656a = 604800000;

    /* renamed from: b  reason: collision with root package name */
    private static final String f8657b = ".log";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8658c = "_";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8659e = "sdk_log_report_message";

    /* renamed from: d  reason: collision with root package name */
    private SharedPreferences f8660d;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f8661f;

    /* renamed from: g  reason: collision with root package name */
    private ConcurrentHashMap<String, b> f8662g;

    /* renamed from: h  reason: collision with root package name */
    private long f8663h;

    private h() {
        this.f8662g = new ConcurrentHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static h a() {
        return a.f8667a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean a(byte[] bArr, int i2, long j2) {
        d();
        if (com.bytedance.framwork.core.sdklib.apm6.b.e() == null) {
            return false;
        }
        File file = new File(com.bytedance.framwork.core.sdklib.apm6.b.e(), String.format("%d%s%s%s", Long.valueOf(System.currentTimeMillis()), "_", UUID.randomUUID().toString(), f8657b));
        a(file, i2, j2);
        FileChannel channel = new FileOutputStream(file).getChannel();
        channel.write(ByteBuffer.wrap(bArr));
        if (l.e()) {
            com.bytedance.framwork.core.sdklib.apm6.safety.d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "saveFile:" + file.getName());
        }
        com.bytedance.apm6.util.e.closeQuietly(channel);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(File file) {
        if (file == null) {
            return;
        }
        d();
        com.bytedance.apm6.util.d.deleteFile(file);
        this.f8662g.remove(file.getName());
        SharedPreferences.Editor edit = this.f8660d.edit();
        edit.remove(file.getName());
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized File b() {
        File e2 = com.bytedance.framwork.core.sdklib.apm6.b.e();
        File file = null;
        if (e2 == null) {
            return null;
        }
        String[] list = e2.list();
        if (list != null && list.length != 0) {
            List<String> asList = Arrays.asList(list);
            Collections.sort(asList, new Comparator<String>() { // from class: com.bytedance.framwork.core.sdklib.apm6.h.1
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(String str, String str2) {
                    return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
                }
            });
            b bVar = null;
            for (String str : asList) {
                File file2 = new File(e2, str);
                b b2 = b(file2);
                if (b2 != null) {
                    if (l.e()) {
                        com.bytedance.framwork.core.sdklib.apm6.safety.d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "list send file:" + file2.getName() + " " + b2.f8669a + " " + b2.f8670b + " " + System.currentTimeMillis());
                    }
                    if (b2.f8669a > e.a().d()) {
                        a(file2);
                        com.bytedance.framwork.core.sdklib.apm6.safety.d.e(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "retry count reached top");
                    } else if (b2.f8669a != 0 && b2.f8670b >= System.currentTimeMillis()) {
                        if (bVar == null || bVar.f8670b > b2.f8670b) {
                            bVar = b2;
                            file = file2;
                        }
                    }
                }
                file = file2;
            }
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b b(File file) {
        if (file == null) {
            return null;
        }
        d();
        String name = file.getName();
        if (this.f8662g.containsKey(name)) {
            return this.f8662g.get(name);
        }
        if (this.f8660d.contains(name)) {
            String string = this.f8660d.getString(name, "");
            r0 = TextUtils.isEmpty(string) ? null : b.a(string);
            if (r0 != null) {
                this.f8662g.put(name, r0);
            }
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(File file, int i2, long j2) {
        b bVar;
        if (file == null) {
            return;
        }
        d();
        SharedPreferences.Editor edit = this.f8660d.edit();
        String name = file.getName();
        if (this.f8662g.containsKey(name)) {
            bVar = this.f8662g.get(name);
        } else {
            bVar = new b(i2, j2);
            this.f8662g.put(name, bVar);
        }
        bVar.a(i2, j2);
        edit.putString(name, bVar.b());
        edit.commit();
    }

    synchronized void c() {
        if (System.currentTimeMillis() - this.f8663h < 604800000) {
            return;
        }
        this.f8663h = System.currentTimeMillis();
        d();
        File e2 = com.bytedance.framwork.core.sdklib.apm6.b.e();
        if (e2 == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        e2.listFiles(new FileFilter() { // from class: com.bytedance.framwork.core.sdklib.apm6.h.2
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                b a2;
                String name = file.getName();
                String string = h.this.f8660d.getString(name, "");
                boolean z = true;
                if (((!TextUtils.isEmpty(string) ? b.a(string) : null) != null ? a2.f8669a : 0) > e.a().d()) {
                    arrayList.add(file.getName());
                } else {
                    try {
                        String[] split = name.split("_");
                        if (name.endsWith(h.f8657b) && split.length == 2) {
                            if (System.currentTimeMillis() - Long.parseLong(split[0]) < 604800000) {
                                z = false;
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (z) {
                    if (l.e()) {
                        com.bytedance.framwork.core.sdklib.apm6.safety.d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "cleanExpireLog:" + file.getName());
                    }
                    arrayList.add(file.getName());
                    com.bytedance.apm6.util.d.deleteFile(file);
                }
                return false;
            }
        });
        if (arrayList.size() > 0) {
            com.bytedance.framwork.core.sdklib.apm6.safety.d.e(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "cleanExpireLog:" + arrayList);
        }
    }

    private synchronized void d() {
        if (this.f8661f) {
            return;
        }
        this.f8660d = l.a().getSharedPreferences(f8659e, 0);
        this.f8661f = true;
    }

    /* compiled from: LogSenderHelper.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final h f8667a = new h();

        private a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LogSenderHelper.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: c  reason: collision with root package name */
        private static final String f8668c = "_";

        /* renamed from: a  reason: collision with root package name */
        private int f8669a;

        /* renamed from: b  reason: collision with root package name */
        private long f8670b;

        public b(int i2, long j2) {
            this.f8669a = i2;
            this.f8670b = j2;
        }

        void a(int i2, long j2) {
            this.f8669a = i2;
            this.f8670b = j2;
        }

        public static b a(String str) {
            try {
                String[] split = str.split("_");
                if (split.length == 2) {
                    return new b(Integer.parseInt(split[0]), Long.parseLong(split[1]));
                }
                return new b(0, 0L);
            } catch (Exception e2) {
                com.bytedance.framwork.core.sdklib.apm6.safety.d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "parseRetryMessage " + str, e2);
                return null;
            }
        }

        public int a() {
            return this.f8669a;
        }

        public String b() {
            return this.f8669a + "_" + this.f8670b;
        }
    }
}
