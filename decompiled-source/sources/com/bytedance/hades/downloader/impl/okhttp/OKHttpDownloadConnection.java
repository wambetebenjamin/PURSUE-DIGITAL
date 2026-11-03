package com.bytedance.hades.downloader.impl.okhttp;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.bytedance.hades.downloader.api.data.DownloadErrorCode;
import com.bytedance.hades.downloader.impl.DownloadComponentManager;
import com.bytedance.hades.downloader.impl.settings.DownloadSetting;
import com.bytedance.hades.downloader.impl.settings.DownloadSettingKeys;
import g.hades.downloader.c;
import g.hades.downloader.f;
import g.hades.downloader.h;
import g.hades.downloader.l;
import g.hades.downloader.m;
import g.hades.downloader.o;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Future;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class OKHttpDownloadConnection {
    private static final String TAG = "Hades-J-OKCon";
    private final long mCppConn;
    private final m mRealConnection;
    private volatile boolean mClosed = false;
    private volatile b mStatus = b.IDLE;
    private Future<?> mFuture = null;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DownloadSetting downloadSetting = DownloadSetting.getInstance();
            int optInt = downloadSetting.optInt(DownloadSettingKeys.THREAD_CORE, 0);
            if (optInt != 0) {
                f.a(Process.myTid(), optInt);
            }
            int optInt2 = downloadSetting.optInt(DownloadSettingKeys.THREAD_PRIORITY, 0);
            if (optInt2 != 0) {
                Log.i(OKHttpDownloadConnection.TAG, "LoopAndRead: setThreadPriority: " + optInt2);
                Process.setThreadPriority(optInt2);
            }
            OKHttpDownloadConnection.this.LoopAndRead();
            OKHttpDownloadConnection.this.mFuture = null;
        }
    }

    /* loaded from: classes2.dex */
    public enum b {
        IDLE,
        CONNECTING,
        CONNECTED,
        FAILED,
        SUCCEED,
        CANCELED
    }

    public OKHttpDownloadConnection(long j2) {
        this.mCppConn = j2;
        if (Build.VERSION.SDK_INT <= 19) {
            this.mRealConnection = new o();
        } else {
            this.mRealConnection = new l();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LoopAndRead() {
        JavaBuffer javaBuffer;
        b bVar;
        synchronized (this) {
            if (this.mClosed) {
                this.mStatus = b.CANCELED;
                return;
            }
            this.mStatus = b.CONNECTED;
            b bVar2 = this.mStatus;
            InputStream inputStream = null;
            try {
                InputStream c2 = this.mRealConnection.c();
                try {
                    JavaBuffer obtainBuffer = obtainBuffer(this.mCppConn);
                    int i2 = 0;
                    while (obtainBuffer != null) {
                        int length = obtainBuffer.data.length;
                        int i3 = 0;
                        int i4 = length;
                        while (!this.mClosed) {
                            if (i4 > 0 && (i2 = c2.read(obtainBuffer.data, i3, i4)) != -1) {
                                i3 += i2;
                                i4 = length - i3;
                            }
                            if (i3 > 0) {
                                obtainBuffer = onRead(this.mCppConn, obtainBuffer.nativePtr, i3);
                                continue;
                            }
                            if (i2 == -1) {
                                b bVar3 = b.SUCCEED;
                                onSucceeded(this.mCppConn);
                                h.a(c2);
                                if (obtainBuffer != null) {
                                    recycleBuffer(this.mCppConn, obtainBuffer.nativePtr);
                                }
                                this.mRealConnection.b();
                                synchronized (this) {
                                    this.mStatus = bVar3;
                                    if (this.mClosed) {
                                        notifyAll();
                                    }
                                }
                                return;
                            }
                        }
                        throw new Exception("okhttp close when read");
                    }
                    throw new Exception("buffer is null");
                } catch (Throwable th) {
                    th = th;
                    javaBuffer = null;
                    inputStream = c2;
                    try {
                        if (this.mClosed) {
                            bVar = b.CANCELED;
                        } else {
                            bVar = b.FAILED;
                            onFailed(this.mCppConn, th instanceof IOException ? c.a(th) : DownloadErrorCode.ERROR_UNKNOWN, "LoopAndRead failed, " + th);
                        }
                        h.a(inputStream);
                        if (javaBuffer != null) {
                            recycleBuffer(this.mCppConn, javaBuffer.nativePtr);
                        }
                        this.mRealConnection.b();
                        synchronized (this) {
                            this.mStatus = bVar;
                            if (this.mClosed) {
                                notifyAll();
                            }
                        }
                    } catch (Throwable th2) {
                        h.a(inputStream);
                        if (javaBuffer != null) {
                            recycleBuffer(this.mCppConn, javaBuffer.nativePtr);
                        }
                        this.mRealConnection.b();
                        synchronized (this) {
                            this.mStatus = bVar2;
                            if (this.mClosed) {
                                notifyAll();
                            }
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                javaBuffer = null;
            }
        }
    }

    private void close() {
        if (this.mClosed) {
            return;
        }
        this.mClosed = true;
        if (this.mStatus == b.CONNECTING) {
            try {
                this.mRealConnection.d();
            } catch (Throwable unused) {
            }
        }
        Future<?> future = this.mFuture;
        if (future != null) {
            this.mFuture = null;
            try {
                future.cancel(true);
            } catch (Throwable unused2) {
            }
        }
    }

    private int connectImpl(String str, String str2, String str3) {
        try {
            b bVar = b.CONNECTING;
            this.mStatus = bVar;
            this.mRealConnection.a(str, new JSONObject(str2), str3);
            if (this.mClosed) {
                Log.i(TAG, "connectImpl: closed1");
                this.mStatus = b.CANCELED;
                return 0;
            }
            this.mStatus = bVar;
            if (this.mClosed) {
                Log.i(TAG, "connectImpl: closed2");
                this.mStatus = b.CANCELED;
                return 0;
            }
            this.mFuture = DownloadComponentManager.getIOThreadExecutor().submit(new a());
            return getResponseCode();
        } catch (Throwable th) {
            if (this.mClosed) {
                this.mStatus = b.CANCELED;
                return 0;
            }
            int a2 = th instanceof IOException ? c.a(th) : DownloadErrorCode.ERROR_UNKNOWN;
            Log.e(TAG, "connect ex: code = " + a2 + ", e = " + th);
            onFailed(this.mCppConn, a2, "connectImpl: " + th);
            this.mStatus = b.FAILED;
            return 0;
        }
    }

    public static native long createBuffer(JavaBuffer javaBuffer, byte[] bArr, int i2);

    private static long createJavaBuffer(int i2) {
        return new JavaBuffer(i2).nativePtr;
    }

    public static String doGet(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (Build.VERSION.SDK_INT <= 19) {
                return o.a(str, jSONObject).toString();
            }
            return l.a(str, jSONObject).toString();
        } catch (Throwable th) {
            Log.e(TAG, "doGet, e = " + th);
            return "{\"code\":" + (th instanceof IOException ? c.a(th) : DownloadErrorCode.ERROR_UNKNOWN) + ", \"response\": \"" + th + "\"}";
        }
    }

    private int getResponseCode() {
        return this.mRealConnection.a();
    }

    private String getResponseHeaderFieldImpl(String str) {
        return this.mRealConnection.a(str);
    }

    private native JavaBuffer obtainBuffer(long j2);

    private native void onFailed(long j2, int i2, String str);

    private native JavaBuffer onRead(long j2, long j3, int i2);

    private native void onSucceeded(long j2);

    private native void recycleBuffer(long j2, long j3);

    private void release() {
        synchronized (this) {
            close();
            if (this.mStatus == b.CONNECTED) {
                try {
                    Log.d(TAG, "java close wait1");
                    wait();
                    Log.d(TAG, "java close wait2");
                } catch (InterruptedException unused) {
                }
            }
        }
    }
}
