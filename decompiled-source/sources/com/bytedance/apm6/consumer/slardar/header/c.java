package com.bytedance.apm6.consumer.slardar.header;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.json.JSONObject;
/* compiled from: HeaderStoreManager.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: c  reason: collision with root package name */
    private static final String f1808c = ".bin";

    /* renamed from: d  reason: collision with root package name */
    private static final long f1809d = 604800000;

    /* renamed from: a  reason: collision with root package name */
    private volatile boolean f1810a;

    /* renamed from: b  reason: collision with root package name */
    private File f1811b;

    public static c getInstance() {
        return a.f1813a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, com.bytedance.apm6.consumer.slardar.header.a aVar) {
        JSONObject serialize;
        b();
        if (this.f1811b == null || (serialize = d.serialize(aVar)) == null) {
            return;
        }
        File file = new File(this.f1811b, b(str));
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileOutputStream(file).getChannel();
            fileChannel.write(ByteBuffer.wrap(serialize.toString().getBytes()));
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.bytedance.apm6.consumer.slardar.header.a a(String str) {
        byte[] readFileToByteArray;
        b();
        if (this.f1811b == null || (readFileToByteArray = com.bytedance.apm6.util.d.readFileToByteArray(new File(this.f1811b, b(str)))) == null) {
            return null;
        }
        return d.deserialize(readFileToByteArray);
    }

    private String b(String str) {
        return str + f1808c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        b();
        File file = this.f1811b;
        if (file == null) {
            return;
        }
        file.listFiles(new FileFilter() { // from class: com.bytedance.apm6.consumer.slardar.header.c.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                boolean z = true;
                try {
                    String name = file2.getName();
                    int lastIndexOf = name.lastIndexOf(c.f1808c);
                    if (lastIndexOf != -1) {
                        if (System.currentTimeMillis() - d.a(Long.parseLong(name.substring(0, lastIndexOf))) < 604800000) {
                            z = false;
                        }
                    }
                } catch (Throwable unused) {
                }
                if (z) {
                    if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                        com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "deleteExpireHeader:" + file2.getName());
                    }
                    com.bytedance.apm6.util.d.deleteFile(file2);
                }
                return false;
            }
        });
    }

    private synchronized void b() {
        if (this.f1810a) {
            return;
        }
        File file = new File(com.bytedance.apm6.consumer.slardar.c.getWorkingDirectory(), "header");
        this.f1811b = file;
        if (!file.exists()) {
            this.f1811b.mkdirs();
        }
        this.f1810a = true;
    }

    /* compiled from: HeaderStoreManager.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final c f1813a = new c();

        private a() {
        }
    }
}
