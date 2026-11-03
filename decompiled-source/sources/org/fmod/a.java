package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final FMODAudioDevice f21114a;

    /* renamed from: b  reason: collision with root package name */
    private final ByteBuffer f21115b;

    /* renamed from: c  reason: collision with root package name */
    private final int f21116c;

    /* renamed from: d  reason: collision with root package name */
    private final int f21117d;

    /* renamed from: e  reason: collision with root package name */
    private volatile Thread f21118e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f21119f;

    /* renamed from: g  reason: collision with root package name */
    private AudioRecord f21120g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f21121h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(FMODAudioDevice fMODAudioDevice, int i2, int i3) {
        this.f21114a = fMODAudioDevice;
        this.f21116c = i2;
        this.f21117d = i3;
        this.f21115b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i2, i3, 2));
    }

    private void b() {
        AudioRecord audioRecord = this.f21120g;
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                this.f21120g.stop();
            }
            this.f21120g.release();
            this.f21120g = null;
        }
        this.f21115b.position(0);
        this.f21121h = false;
    }

    public final int a() {
        return this.f21115b.capacity();
    }

    public final void c() {
        if (this.f21118e != null) {
            d();
        }
        this.f21119f = true;
        this.f21118e = new Thread(this);
        this.f21118e.start();
    }

    public final void d() {
        while (this.f21118e != null) {
            this.f21119f = false;
            try {
                this.f21118e.join();
                this.f21118e = null;
            } catch (InterruptedException unused) {
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = 3;
        while (this.f21119f) {
            if (!this.f21121h && i2 > 0) {
                b();
                AudioRecord audioRecord = new AudioRecord(1, this.f21116c, this.f21117d, 2, this.f21115b.capacity());
                this.f21120g = audioRecord;
                boolean z = audioRecord.getState() == 1;
                this.f21121h = z;
                if (z) {
                    this.f21115b.position(0);
                    this.f21120g.startRecording();
                    i2 = 3;
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f21120g.getState() + ")");
                    i2--;
                    b();
                }
            }
            if (this.f21121h && this.f21120g.getRecordingState() == 3) {
                AudioRecord audioRecord2 = this.f21120g;
                ByteBuffer byteBuffer = this.f21115b;
                this.f21114a.fmodProcessMicData(this.f21115b, audioRecord2.read(byteBuffer, byteBuffer.capacity()));
                this.f21115b.position(0);
            }
        }
        b();
    }
}
