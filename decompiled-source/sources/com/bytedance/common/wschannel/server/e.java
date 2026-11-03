package com.bytedance.common.wschannel.server;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: FrontierPersistentBuffer.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final short f5943a = 2114;

    /* renamed from: b  reason: collision with root package name */
    public static final short f5944b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5945c = 262144;

    /* renamed from: d  reason: collision with root package name */
    private static final int f5946d = 12;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5947e = 40;

    /* renamed from: f  reason: collision with root package name */
    private static final int f5948f = 32;

    /* renamed from: g  reason: collision with root package name */
    private static final int f5949g = 8;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5950h = 262104;

    /* renamed from: i  reason: collision with root package name */
    private static final int f5951i = 6553;

    /* renamed from: j  reason: collision with root package name */
    private static final int f5952j = 1092;
    private static final int k = 3600000;
    private static final String m = "e";
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    private static final int u = 3;
    private static final int v = 4;
    private static final int w = 5;
    private MappedByteBuffer n;
    private byte[] l = new byte[40];
    private PriorityQueue<Integer> o = new PriorityQueue<>();
    private ConcurrentHashMap<String, a> p = new ConcurrentHashMap<>();
    private final AtomicBoolean q = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FrontierPersistentBuffer.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private long f5961a;

        /* renamed from: b  reason: collision with root package name */
        private int f5962b;

        public a(long j2, int i2) {
            this.f5961a = j2;
            this.f5962b = i2;
        }

        public long a() {
            return this.f5961a;
        }

        public int b() {
            return this.f5962b;
        }

        public void a(int i2) {
            this.f5962b = i2;
        }
    }

    public e(Context context) {
        FileLock fileLock;
        Throwable th;
        FileChannel fileChannel;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            fileChannel = new RandomAccessFile(com.bytedance.common.wschannel.utils.a.a(context), "rw").getChannel();
            try {
                fileLock = fileChannel.tryLock();
                try {
                    this.n = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0L, PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
                    if (Logger.debug()) {
                        Logger.d(m, "magic number: " + ((int) d()) + " file version: " + e() + " total count: " + f() + " total bytes: " + g());
                    }
                    if (d() == 2114 && g() > 0 && f() > 0) {
                        c();
                        com.bytedance.common.wschannel.utils.f.a().a(new com.bytedance.common.wschannel.utils.g(3600000L, 3600000L) { // from class: com.bytedance.common.wschannel.server.e.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (e.this.q.get()) {
                                    return;
                                }
                                e.this.j();
                                e.this.a((String) null, 2, -1L);
                            }
                        });
                        a((String) null, 4, System.currentTimeMillis() - currentTimeMillis);
                    }
                    h();
                    com.bytedance.common.wschannel.utils.f.a().a(new com.bytedance.common.wschannel.utils.g(3600000L, 3600000L) { // from class: com.bytedance.common.wschannel.server.e.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (e.this.q.get()) {
                                return;
                            }
                            e.this.j();
                            e.this.a((String) null, 2, -1L);
                        }
                    });
                    a((String) null, 4, System.currentTimeMillis() - currentTimeMillis);
                } catch (Throwable th2) {
                    th = th2;
                    Logger.e(m, "create MappedByteBuffer failed: ", th);
                    this.q.set(true);
                    a(5, th);
                    if (fileLock != null) {
                        try {
                            fileLock.release();
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                }
            } catch (Throwable th3) {
                fileLock = null;
                th = th3;
            }
        } catch (Throwable th4) {
            fileLock = null;
            th = th4;
            fileChannel = null;
        }
    }

    public void a() {
        d.a();
    }

    private void c() {
        a aVar;
        this.n.position(12);
        long currentTimeMillis = System.currentTimeMillis();
        int g2 = g() / 40;
        boolean z = false;
        for (int i2 = 0; i2 < g2; i2++) {
            byte[] bArr = new byte[32];
            this.n.get(bArr);
            long j2 = this.n.getLong();
            if (a(bArr)) {
                if (Logger.debug()) {
                    Logger.d(m, "Data is empty, bypass remove offset:" + i2);
                }
                this.o.add(Integer.valueOf(i2));
            } else if (j2 < currentTimeMillis) {
                c(i2);
                if (!z) {
                    z = true;
                }
            } else {
                this.p.put(new String(bArr, StandardCharsets.UTF_8), new a(j2, i2));
                if (Logger.debug()) {
                    Logger.d(m, "initPersistentBuffer item: " + new String(bArr) + "&" + aVar.a() + "_" + aVar.b());
                }
            }
        }
        if (z) {
            k();
        }
        if (Logger.debug()) {
            Logger.d(m, "map size:" + this.p.size() + " total count:" + f() + " data size:" + g2 + " total bytes:" + g());
        }
    }

    private boolean a(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            for (byte b2 : bArr) {
                if (b2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private short d() {
        return this.n.getShort(0);
    }

    private long e() {
        return this.n.getShort(2);
    }

    private int f() {
        return this.n.getInt(4);
    }

    private void a(int i2) {
        this.n.putInt(4, i2);
    }

    private int g() {
        return this.n.getInt(8);
    }

    private void b(int i2) {
        this.n.putInt(8, i2);
    }

    private void h() {
        Logger.i(m, "reset");
        this.n.clear();
        this.n.putShort(f5943a);
        this.n.putShort((short) 1);
        this.n.putInt(0);
        this.n.putInt(0);
    }

    private void a(int i2, Throwable th) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", i2);
            String a2 = com.bytedance.common.wschannel.utils.e.a(th);
            if (!TextUtils.isEmpty(a2) && a2.length() > 2048) {
                a2 = a2.substring(0, 2048);
            }
            jSONObject.put("exception", a2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        d.a(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, long j2) {
        int size = this.p.size();
        int size2 = this.o.size();
        int f2 = f();
        int g2 = g();
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("messageId", str);
            }
            if (j2 >= 0) {
                jSONObject.put("duration", j2);
            }
            jSONObject.put("action", i2);
            jSONObject.put("mapSize", size);
            jSONObject.put("queueSize", size2);
            jSONObject.put("totalCount", f2);
            jSONObject.put(DBDefinition.TOTAL_BYTES, g2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        d.a(jSONObject);
    }

    public boolean a(final String str, long j2) {
        if (this.q.get()) {
            return false;
        }
        if (this.p.containsKey(str)) {
            Logger.i(m, "Drop existed messageId:" + str);
            com.bytedance.common.wschannel.utils.f.a().a(new com.bytedance.common.wschannel.utils.g() { // from class: com.bytedance.common.wschannel.server.e.2
                @Override // java.lang.Runnable
                public void run() {
                    e.this.a(str, 0, -1L);
                }
            });
            return true;
        }
        final long currentTimeMillis = System.currentTimeMillis() + j2;
        this.p.put(str, new a(currentTimeMillis, -1));
        com.bytedance.common.wschannel.utils.f.a().a(new com.bytedance.common.wschannel.utils.g() { // from class: com.bytedance.common.wschannel.server.e.3
            @Override // java.lang.Runnable
            public void run() {
                int b2 = e.this.b(str, currentTimeMillis);
                if (b2 >= 0) {
                    a aVar = (a) e.this.p.get(str);
                    if (aVar != null) {
                        aVar.a(b2);
                    }
                    if (Logger.debug()) {
                        Logger.d(e.m, "mapToFile success, message id:" + str + " offset:" + b2);
                        return;
                    }
                    return;
                }
                e.this.a(str, 1, -1L);
            }
        });
        return false;
    }

    public void a(String[] strArr) {
        if (Logger.debug()) {
            Logger.d(m, "ack message id: " + Arrays.toString(strArr));
        }
        if (this.q.get() || strArr == null || strArr.length == 0) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    if (str.getBytes("UTF-8").length == 32) {
                        arrayList.add(str);
                    }
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        com.bytedance.common.wschannel.utils.f.a().a(new com.bytedance.common.wschannel.utils.g() { // from class: com.bytedance.common.wschannel.server.e.4
            @Override // java.lang.Runnable
            public void run() {
                boolean z = false;
                for (String str2 : arrayList) {
                    if (e.this.p.containsKey(str2)) {
                        int b2 = ((a) e.this.p.get(str2)).b();
                        e.this.p.remove(str2);
                        e.this.c(b2);
                        if (!z) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    e.this.k();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        if (i2 < 0) {
            return;
        }
        this.n.position((i2 * 40) + 12);
        this.n.put(this.l);
        a(f() - 1);
        this.o.add(Integer.valueOf(i2));
        if (Logger.debug()) {
            Logger.d(m, String.format("remove success: offset=%s, totalCount=%s, totalBytes=%s", Integer.valueOf(i2), Integer.valueOf(f()), Integer.valueOf(g())));
        }
    }

    private int a(byte[] bArr, long j2) {
        if (this.o.isEmpty()) {
            return -1;
        }
        int intValue = this.o.poll().intValue();
        this.n.position((intValue * 40) + 12);
        this.n.put(bArr);
        this.n.putLong(j2);
        a(f() + 1);
        if (Logger.debug()) {
            Logger.d(m, String.format("push success in queue: offset=%s, totalCount=%s, totalBytes=%s, logItem=%s", Integer.valueOf(intValue), Integer.valueOf(f()), Integer.valueOf(g()), new String(bArr)));
        }
        return intValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(String str, long j2) {
        byte[] bytes = str.getBytes();
        if (bytes == null || bytes.length != 32) {
            return -1;
        }
        int a2 = a(bytes, j2);
        if (a2 >= 0) {
            return a2;
        }
        if (this.n.remaining() < 40 || f() >= f5951i || g() >= f5950h) {
            String str2 = m;
            Logger.i(str2, "remaining:" + this.n.remaining() + " total count:" + f() + " threshold:" + f5951i + " total bytes:" + g() + " threshold:" + f5950h);
            a(str, 3, -1L);
            i();
            int a3 = a(bytes, j2);
            if (a3 < 0) {
                Logger.e(str2, "Push data failed, removeDataForAvailableStorage is not working.");
                return -1;
            }
            return a3;
        }
        this.n.position(g() + 12);
        this.n.put(bytes);
        this.n.putLong(j2);
        int f2 = f();
        a(f2 + 1);
        b(g() + 40);
        if (Logger.debug()) {
            Logger.d(m, String.format("push success in file: offset=%s, totalCount=%s, totalBytes=%s, logItem=%s", Integer.valueOf(f2), Integer.valueOf(f()), Integer.valueOf(g()), str));
        }
        k();
        return f2;
    }

    private void i() {
        Logger.i(m, "available:1092 queue:" + this.o.size());
        Iterator<Map.Entry<String, a>> it = this.p.entrySet().iterator();
        while (it.hasNext()) {
            a value = it.next().getValue();
            if (value.b() < 1092) {
                c(value.b());
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (Logger.debug()) {
            Logger.d(m, "removeExpiredCachedData");
        }
        Iterator<Map.Entry<String, a>> it = this.p.entrySet().iterator();
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        while (it.hasNext()) {
            a value = it.next().getValue();
            if (value.a() < currentTimeMillis) {
                c(value.b());
                it.remove();
                if (!z) {
                    z = true;
                }
            }
        }
        if (z) {
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.n.force();
    }
}
