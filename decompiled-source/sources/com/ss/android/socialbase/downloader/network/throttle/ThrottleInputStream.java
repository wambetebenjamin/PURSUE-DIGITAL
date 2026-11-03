package com.ss.android.socialbase.downloader.network.throttle;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes9.dex */
public class ThrottleInputStream extends InputStream {
    public static final int DEFAULT_THROTTLE_SMOOTHNESS = 40;
    private final InputStream in;
    private long overReadAndSleepTime;
    protected long periodStartTime;
    private long readBytesInPeriod;
    protected long throttleNetSpeed;
    protected volatile long throttlePeriod;
    private volatile long throttlePeriodBytes;
    protected int throttleSmoothness = 40;

    public ThrottleInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    public void setThrottleNetSpeed(long j2) {
        setThrottleNetSpeed(j2, 0);
    }

    public void setThrottleNetSpeed(long j2, int i2) {
        this.throttleNetSpeed = j2;
        if (i2 >= 1 && i2 <= 100) {
            this.throttleSmoothness = i2;
        }
        this.throttlePeriod = 1000 / this.throttleSmoothness;
        this.throttlePeriodBytes = j2 / this.throttleSmoothness;
    }

    @Override // java.io.InputStream
    public int read() {
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (this.throttlePeriodBytes > 0) {
            if (this.periodStartTime == 0) {
                this.periodStartTime = System.currentTimeMillis();
            }
            long j2 = this.throttlePeriodBytes - this.readBytesInPeriod;
            if (j2 > 0 && i3 > j2) {
                i3 = (int) j2;
            }
            int read = this.in.read(bArr, i2, i3);
            if (read != -1) {
                this.readBytesInPeriod += read;
            }
            if (this.readBytesInPeriod >= this.throttlePeriodBytes) {
                long currentTimeMillis = System.currentTimeMillis();
                long j3 = currentTimeMillis - this.periodStartTime;
                this.periodStartTime = currentTimeMillis;
                long j4 = this.throttlePeriod - j3;
                int i4 = (j4 > 0L ? 1 : (j4 == 0L ? 0 : -1));
                if (i4 > 0) {
                    long j5 = this.overReadAndSleepTime;
                    if (j4 > j5) {
                        long j6 = j4 - j5;
                        this.overReadAndSleepTime = 0L;
                        if (j6 > 0) {
                            this.periodStartTime += j6;
                            try {
                                Thread.sleep(j6);
                            } catch (InterruptedException e2) {
                                throw new IOException(e2);
                            }
                        }
                    } else {
                        this.overReadAndSleepTime = j5 - j4;
                    }
                } else if (i4 < 0) {
                    long j7 = this.overReadAndSleepTime + (-j4);
                    this.overReadAndSleepTime = j7;
                    if (j7 > 100) {
                        this.overReadAndSleepTime = 100L;
                    }
                }
                this.readBytesInPeriod = 0L;
            }
            return read;
        }
        return this.in.read(bArr, i2, i3);
    }

    public int superRead(byte[] bArr, int i2, int i3) {
        return super.read(bArr, i2, i3);
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        return this.in.skip(j2);
    }

    @Override // java.io.InputStream
    public int available() {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.in.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i2) {
        this.in.mark(i2);
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.in.reset();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
