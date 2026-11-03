package com.ss.cgpa;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Selector f18742a = null;

    /* renamed from: b  reason: collision with root package name */
    private boolean f18743b = false;

    /* renamed from: c  reason: collision with root package name */
    private String f18744c = getClass().getName();

    private SocketChannelStream c(SelectionKey selectionKey) {
        Object attachment = selectionKey.attachment();
        if (attachment == null || !(attachment instanceof SocketChannelStream)) {
            return null;
        }
        return (SocketChannelStream) attachment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(int i2) {
        if (!this.f18743b) {
            try {
                Selector selector = this.f18742a;
                if (selector != null) {
                    return selector.select(i2);
                }
            } catch (Exception e2) {
                Log.e(this.f18744c, e2.toString());
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        Selector selector = this.f18742a;
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception unused) {
            }
            this.f18742a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str) {
        this.f18744c = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0026, code lost:
        if (r3.hasRemaining() != false) goto L13;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x002a -> B:17:0x0033). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.nio.channels.SelectionKey r7) {
        /*
            r6 = this;
            com.ss.cgpa.SocketChannelStream r0 = r6.c(r7)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.nio.channels.SelectableChannel r2 = r7.channel()
            java.nio.channels.SocketChannel r2 = (java.nio.channels.SocketChannel) r2
            java.nio.ByteBuffer r3 = r0.getReadByteBuffer()
            r4 = 1
            int r5 = r2.read(r3)     // Catch: java.lang.Exception -> L29
            if (r5 <= 0) goto L20
            boolean r3 = r0.procByteBuffer()     // Catch: java.lang.Exception -> L29
            if (r3 != 0) goto L34
            goto L33
        L20:
            if (r5 != 0) goto L33
            boolean r3 = r3.hasRemaining()     // Catch: java.lang.Exception -> L29
            if (r3 != 0) goto L34
            goto L33
        L29:
            r1 = move-exception
            java.lang.String r3 = r6.f18744c
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r3, r1)
        L33:
            r1 = r4
        L34:
            if (r1 == 0) goto L4b
            r7.cancel()
            r2.close()     // Catch: java.lang.Exception -> L40
            r0.close()     // Catch: java.lang.Exception -> L40
            goto L5e
        L40:
            r7 = move-exception
            java.lang.String r0 = r6.f18744c
            java.lang.String r7 = r7.toString()
            android.util.Log.e(r0, r7)
            goto L5e
        L4b:
            java.nio.ByteBuffer r0 = r0.getWriteByteBuffer()
            int r0 = r0.position()
            if (r0 <= 0) goto L5e
            int r0 = r7.interestOps()
            r0 = r0 | 4
            r7.interestOps(r0)
        L5e:
            r7 = r1 ^ 1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.cgpa.a.a(java.nio.channels.SelectionKey):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b() {
        try {
            if (this.f18742a == null) {
                this.f18742a = Selector.open();
            }
        } catch (Exception e2) {
            Log.e(this.f18744c, e2.toString());
            a();
        }
        return this.f18742a != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(SelectionKey selectionKey) {
        SocketChannelStream c2 = c(selectionKey);
        boolean z = false;
        if (c2 == null) {
            return false;
        }
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer writeByteBuffer = c2.getWriteByteBuffer();
        writeByteBuffer.flip();
        boolean z2 = true;
        try {
            socketChannel.write(writeByteBuffer);
            if (writeByteBuffer.hasRemaining()) {
                selectionKey.interestOps(selectionKey.interestOps() | 4);
            } else {
                z = true;
            }
        } catch (Exception e2) {
            Log.e(this.f18744c, e2.toString());
            z2 = false;
            z = true;
        }
        writeByteBuffer.compact();
        if (z) {
            selectionKey.interestOps(selectionKey.interestOps() & (-5));
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set<SelectionKey> c() {
        return this.f18742a.selectedKeys();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Selector d() {
        return this.f18742a;
    }
}
