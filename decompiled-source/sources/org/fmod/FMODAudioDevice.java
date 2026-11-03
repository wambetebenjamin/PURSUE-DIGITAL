package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public class FMODAudioDevice implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private volatile Thread f21107a = null;

    /* renamed from: b  reason: collision with root package name */
    private volatile boolean f21108b = false;

    /* renamed from: c  reason: collision with root package name */
    private AudioTrack f21109c = null;

    /* renamed from: d  reason: collision with root package name */
    private boolean f21110d = false;

    /* renamed from: e  reason: collision with root package name */
    private ByteBuffer f21111e = null;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f21112f = null;

    /* renamed from: g  reason: collision with root package name */
    private volatile a f21113g;

    private native int fmodGetInfo(int i2);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        AudioTrack audioTrack = this.f21109c;
        if (audioTrack != null) {
            if (audioTrack.getState() == 1) {
                this.f21109c.stop();
            }
            this.f21109c.release();
            this.f21109c = null;
        }
        this.f21111e = null;
        this.f21112f = null;
        this.f21110d = false;
    }

    public synchronized void close() {
        stop();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public native int fmodProcessMicData(ByteBuffer byteBuffer, int i2);

    public boolean isRunning() {
        return this.f21107a != null && this.f21107a.isAlive();
    }

    @Override // java.lang.Runnable
    public void run() {
        int i2 = 3;
        while (this.f21108b) {
            if (!this.f21110d && i2 > 0) {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(0);
                int i3 = fmodGetInfo(4) == 1 ? 4 : 12;
                int minBufferSize = AudioTrack.getMinBufferSize(fmodGetInfo, i3, 2);
                int fmodGetInfo2 = fmodGetInfo(4) * 2;
                int round = Math.round(minBufferSize * 1.1f) & (~(fmodGetInfo2 - 1));
                int fmodGetInfo3 = fmodGetInfo(1);
                int fmodGetInfo4 = fmodGetInfo(2) * fmodGetInfo3 * fmodGetInfo2;
                AudioTrack audioTrack = new AudioTrack(3, fmodGetInfo, i3, 2, fmodGetInfo4 > round ? fmodGetInfo4 : round, 1);
                this.f21109c = audioTrack;
                boolean z = audioTrack.getState() == 1;
                this.f21110d = z;
                if (z) {
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fmodGetInfo3 * fmodGetInfo2);
                    this.f21111e = allocateDirect;
                    this.f21112f = new byte[allocateDirect.capacity()];
                    this.f21109c.play();
                    i2 = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f21109c.getState() + ")");
                    releaseAudioTrack();
                    i2--;
                }
            }
            if (this.f21110d) {
                if (fmodGetInfo(3) == 1) {
                    fmodProcess(this.f21111e);
                    ByteBuffer byteBuffer = this.f21111e;
                    byteBuffer.get(this.f21112f, 0, byteBuffer.capacity());
                    this.f21109c.write(this.f21112f, 0, this.f21111e.capacity());
                    this.f21111e.position(0);
                } else {
                    releaseAudioTrack();
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f21107a != null) {
            stop();
        }
        this.f21107a = new Thread(this, "FMODAudioDevice");
        this.f21107a.setPriority(10);
        this.f21108b = true;
        this.f21107a.start();
        if (this.f21113g != null) {
            this.f21113g.c();
        }
    }

    public synchronized int startAudioRecord(int i2, int i3, int i4) {
        if (this.f21113g == null) {
            this.f21113g = new a(this, i2, i3);
            this.f21113g.c();
        }
        return this.f21113g.a();
    }

    public synchronized void stop() {
        while (this.f21107a != null) {
            this.f21108b = false;
            try {
                this.f21107a.join();
                this.f21107a = null;
            } catch (InterruptedException unused) {
            }
        }
        if (this.f21113g != null) {
            this.f21113g.d();
        }
    }

    public synchronized void stopAudioRecord() {
        if (this.f21113g != null) {
            this.f21113g.d();
            this.f21113g = null;
        }
    }
}
