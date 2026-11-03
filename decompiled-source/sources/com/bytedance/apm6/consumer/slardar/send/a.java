package com.bytedance.apm6.consumer.slardar.send;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: DiskFileHelper.java */
/* loaded from: classes.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1833a = "log";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1834b = "_";

    /* renamed from: c  reason: collision with root package name */
    private static final String f1835c = ".";

    /* renamed from: e  reason: collision with root package name */
    private static final String f1836e = "log_report_message";

    /* renamed from: i  reason: collision with root package name */
    private static final long f1837i = 5000;

    /* renamed from: d  reason: collision with root package name */
    private SharedPreferences f1838d;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f1839f;

    /* renamed from: g  reason: collision with root package name */
    private File f1840g;

    /* renamed from: h  reason: collision with root package name */
    private ConcurrentHashMap<String, b> f1841h;

    /* renamed from: j  reason: collision with root package name */
    private long f1842j;
    private volatile boolean k;
    private ArrayList<String> l;

    private a() {
        this.f1841h = new ConcurrentHashMap<>();
        this.f1842j = 0L;
        this.k = false;
        this.l = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a a() {
        return C0027a.f1844a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean a(byte[] bArr, String str, int i2, long j2) {
        d();
        if (this.f1840g == null) {
            return false;
        }
        String format = String.format("%d%s%s%s%s", Long.valueOf(System.currentTimeMillis()), "_", UUID.randomUUID().toString(), ".", str);
        File file = new File(this.f1840g, format);
        a(file, i2, j2);
        FileChannel channel = new FileOutputStream(file).getChannel();
        channel.write(ByteBuffer.wrap(bArr));
        if (!this.l.contains(format)) {
            b(format);
        }
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "saveFile:" + file.getName());
        }
        com.bytedance.apm6.util.e.closeQuietly(channel);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(File file) {
        if (file == null) {
            return;
        }
        this.l.remove(file.getName());
        d();
        com.bytedance.apm6.util.d.deleteFile(file);
        this.f1841h.remove(file.getName());
        SharedPreferences.Editor edit = this.f1838d.edit();
        edit.remove(file.getName());
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized File a(String str) {
        String str2 = "." + str;
        e();
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "failedFiles:" + this.l + " " + str2);
        }
        File file = null;
        if (this.l.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        Iterator<String> it = this.l.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.endsWith(str2)) {
                arrayList.add(next);
            }
        }
        if (com.bytedance.apm6.util.g.isEmpty(arrayList)) {
            return null;
        }
        Collections.sort(arrayList, new Comparator<String>() { // from class: com.bytedance.apm6.consumer.slardar.send.a.1
            @Override // java.util.Comparator
            public int compare(String str3, String str4) {
                return String.CASE_INSENSITIVE_ORDER.compare(str3, str4);
            }
        });
        b bVar = null;
        for (String str3 : arrayList) {
            File file2 = new File(a().b(), str3);
            b b2 = b(file2);
            if (b2 != null) {
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "list send file:" + file2.getName() + " " + b2.f1846a + " " + b2.f1847b + " " + System.currentTimeMillis());
                }
                if (b2.f1846a != 0 && b2.f1847b >= System.currentTimeMillis()) {
                    if (bVar == null || bVar.f1847b > b2.f1847b) {
                        bVar = b2;
                        file = file2;
                    }
                }
            }
            file = file2;
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File b() {
        d();
        return this.f1840g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File[] c() {
        d();
        File file = this.f1840g;
        if (file == null) {
            return null;
        }
        return file.listFiles();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b b(File file) {
        if (file == null) {
            return null;
        }
        d();
        String name = file.getName();
        if (this.f1841h.containsKey(name)) {
            return this.f1841h.get(name);
        }
        if (this.f1838d.contains(name)) {
            String string = this.f1838d.getString(name, "");
            r0 = TextUtils.isEmpty(string) ? null : b.parse(string);
            if (r0 != null) {
                this.f1841h.put(name, r0);
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
        try {
            d();
            SharedPreferences.Editor edit = this.f1838d.edit();
            String name = file.getName();
            if (this.f1841h.containsKey(name)) {
                bVar = this.f1841h.get(name);
            } else {
                bVar = new b(i2, j2);
                this.f1841h.put(name, bVar);
            }
            bVar.a(i2, j2);
            edit.putString(name, bVar.getString());
            edit.commit();
        } catch (Throwable th) {
            com.bytedance.apm6.util.log.b.e(com.bytedance.apm6.consumer.slardar.b.TAG, "updateRetryMessage", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long c(File file) {
        try {
            String[] split = file.getName().split("_");
            if (split.length != 2) {
                return -1L;
            }
            return Long.parseLong(split[0]);
        } catch (Throwable unused) {
            return -1L;
        }
    }

    private synchronized void d() {
        if (this.f1839f) {
            return;
        }
        File file = new File(com.bytedance.apm6.consumer.slardar.c.getWorkingDirectory(), f1833a);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.f1840g = file;
        this.f1838d = com.bytedance.apm6.foundation.context.a.getContext().getSharedPreferences(f1836e, 0);
        this.f1839f = true;
    }

    private synchronized void e() {
        int i2 = 0;
        if (!this.k) {
            String[] list = a().b().list();
            int length = list.length;
            while (i2 < length) {
                String str = list[i2];
                if (!this.l.contains(str)) {
                    b(str);
                }
                i2++;
            }
            this.k = true;
        } else if (this.f1842j > 0 && this.l.size() == 0) {
            String[] list2 = a().b().list();
            int length2 = list2.length;
            while (i2 < length2) {
                String str2 = list2[i2];
                if (!this.l.contains(str2)) {
                    b(str2);
                }
                i2++;
            }
            this.f1842j -= this.l.size();
        }
    }

    private void b(String str) {
        if (this.l.size() > 5000) {
            this.f1842j++;
        } else {
            this.l.add(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskFileHelper.java */
    /* renamed from: com.bytedance.apm6.consumer.slardar.send.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0027a {

        /* renamed from: a  reason: collision with root package name */
        private static a f1844a = new a();

        private C0027a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DiskFileHelper.java */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: c  reason: collision with root package name */
        private static final String f1845c = "_";

        /* renamed from: a  reason: collision with root package name */
        private int f1846a;

        /* renamed from: b  reason: collision with root package name */
        private long f1847b;

        public b(int i2, long j2) {
            this.f1846a = i2;
            this.f1847b = j2;
        }

        void a(int i2, long j2) {
            this.f1846a = i2;
            this.f1847b = j2;
        }

        public static b parse(String str) {
            try {
                String[] split = str.split("_");
                return new b(Integer.parseInt(split[0]), Long.parseLong(split[1]));
            } catch (Exception unused) {
                return null;
            }
        }

        public int getRetryCount() {
            return this.f1846a;
        }

        public String getString() {
            return this.f1846a + "_" + this.f1847b;
        }
    }
}
