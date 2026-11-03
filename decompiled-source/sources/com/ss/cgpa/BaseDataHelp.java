package com.ss.cgpa;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes9.dex */
public abstract class BaseDataHelp implements SocketChannelStream {
    private int msgSn = 0;
    private final int maxBuferSizes = 8192;
    private final int maxCacheWriteMsgCount = 1024;
    private Queue<Long> readCgpMsgs = new LinkedList();
    private List<Long> writeMsgList = new ArrayList();
    private ByteBuffer readByteBuffer = ByteBuffer.allocate(8192);
    private ByteBuffer writeByteBuffer = ByteBuffer.allocate(8192);
    private ReentrantLock lock = new ReentrantLock();
    protected NativeDataHelp nativeDataHelp = new NativeDataHelp();
    private String debugTag = getClass().getName();

    @Override // com.ss.cgpa.SocketChannelStream
    public void close() {
        while (this.writeMsgList.size() > 0) {
            long longValue = this.writeMsgList.get(0).longValue();
            this.writeMsgList.remove(0);
            this.nativeDataHelp.freeMsgInfo(longValue);
        }
    }

    public abstract byte[] decryptMsg(byte[] bArr);

    public abstract byte[] encryptMsg(byte[] bArr);

    public boolean fastWriteMsg(long j2) {
        if (this.writeMsgList.size() > 1024) {
            return false;
        }
        this.writeMsgList.add(Long.valueOf(j2));
        while (this.writeMsgList.size() > 0) {
            long longValue = this.writeMsgList.get(0).longValue();
            byte[] packCgpMsgInfo = this.nativeDataHelp.packCgpMsgInfo(longValue, this.msgSn);
            if (packCgpMsgInfo.length > this.writeByteBuffer.remaining()) {
                break;
            }
            this.writeByteBuffer.put(packCgpMsgInfo);
            this.msgSn++;
            this.writeMsgList.remove(0);
            this.nativeDataHelp.freeMsgInfo(longValue);
        }
        return true;
    }

    @Override // com.ss.cgpa.SocketChannelStream
    public ByteBuffer getReadByteBuffer() {
        return this.readByteBuffer;
    }

    @Override // com.ss.cgpa.SocketChannelStream
    public ByteBuffer getWriteByteBuffer() {
        return this.writeByteBuffer;
    }

    @Override // com.ss.cgpa.SocketChannelStream
    public boolean procByteBuffer() {
        int available;
        this.readByteBuffer.flip();
        int remaining = this.readByteBuffer.remaining();
        byte[] bArr = new byte[remaining];
        this.readByteBuffer.get(bArr);
        this.readByteBuffer.clear();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (true) {
            available = byteArrayInputStream.available();
            if (available <= 0) {
                break;
            }
            long unpackCgpMsgInfo = this.nativeDataHelp.unpackCgpMsgInfo(bArr);
            if (unpackCgpMsgInfo == 0) {
                break;
            }
            byteArrayInputStream.skip(this.nativeDataHelp.getCgpMsgInfoSizes(unpackCgpMsgInfo));
            this.readCgpMsgs.offer(Long.valueOf(unpackCgpMsgInfo));
        }
        if (available > 0) {
            try {
                this.readByteBuffer.put(bArr, remaining - available, available);
            } catch (Exception e2) {
                Log.e(this.debugTag, e2.toString());
                return false;
            }
        }
        byteArrayInputStream.close();
        boolean procReadCgpMsg = procReadCgpMsg();
        procWriteCgpMsg();
        return procReadCgpMsg;
    }

    public boolean procReadCgpMsg() {
        Long poll;
        do {
            poll = this.readCgpMsgs.poll();
            if (poll == null) {
                return true;
            }
        } while (this.nativeDataHelp.procReadCgpMsg(poll.longValue()));
        this.readCgpMsgs.clear();
        return false;
    }

    public int procWriteCgpMsg() {
        this.nativeDataHelp.flushCacheMsg();
        int size = this.writeMsgList.size();
        if (size > 0) {
            this.lock.lock();
            while (this.writeMsgList.size() > 0) {
                long longValue = this.writeMsgList.get(0).longValue();
                byte[] packCgpMsgInfo = this.nativeDataHelp.packCgpMsgInfo(longValue, this.msgSn);
                if (packCgpMsgInfo.length > this.writeByteBuffer.remaining()) {
                    break;
                }
                this.writeByteBuffer.put(packCgpMsgInfo);
                this.msgSn++;
                this.writeMsgList.remove(0);
                this.nativeDataHelp.freeMsgInfo(longValue);
            }
            this.lock.unlock();
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDebugTag(String str) {
        this.debugTag = str;
    }

    public boolean writeMsg(long j2) {
        if (this.writeMsgList.size() < 1024) {
            this.lock.lock();
            this.writeMsgList.add(Long.valueOf(j2));
            this.lock.unlock();
            return true;
        }
        return false;
    }
}
