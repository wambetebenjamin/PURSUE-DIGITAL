package com.ss.android.socialbase.downloader.segment;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.common.wschannel.WsConstants;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.model.HttpResponse;
import com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadStenographer;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class SegmentReader implements Runnable {
    private static final int SEGMENT_APPLY_RETRY_MAX_COUNT = 50;
    private static final int SWITCH_URL_MAX_COUNT = 30;
    private static final String TAG = "SegmentReader";
    private volatile boolean changeSegment;
    private volatile boolean closed;
    volatile long connectEndTime;
    volatile long connectStartTime;
    String curHostIp;
    String curHostRealIp;
    private int curRetryCount;
    volatile Segment curSegment;
    String curUrl;
    private final DownloadInfo downloadInfo;
    private volatile long endOffsetInConnection;
    private boolean exited;
    private boolean failed;
    private BaseException failedException;
    private Future future;
    private final ISegmentCallback host;
    private IDownloadHttpConnection httpConnection;
    private HttpResponse httpResponse;
    private boolean httpsToHttpRetryUsed;
    private long lastConnectStartTime;
    private final IBufferPool pool;
    private volatile long readBytes;
    volatile long readEndTime;
    volatile long readStartTime;
    private volatile boolean reconnect;
    private int retryCount;
    private int segmentApplyRetryTimes;
    private volatile long segmentNewEndOffset;
    private final DownloadSetting setting;
    private long startOffsetInConnection;
    private DownloadStenographer stenographer;
    private int switchUrlTimes;
    private Thread thread;
    private volatile boolean threadDirty;
    final int threadIndex;
    UrlRecord urlRecord;
    private final List<Segment> succeedSegments = new ArrayList();
    private volatile long curSegmentReadOffset = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SegmentReader(DownloadInfo downloadInfo, SegmentDispatcher segmentDispatcher, IBufferPool iBufferPool, UrlRecord urlRecord, int i2) {
        this.downloadInfo = downloadInfo;
        this.host = segmentDispatcher;
        this.pool = iBufferPool;
        this.setting = DownloadSetting.obtain(downloadInfo.getId());
        this.urlRecord = urlRecord;
        this.threadIndex = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Segment obtainSegment;
        ISegmentCallback iSegmentCallback;
        ISegmentCallback iSegmentCallback2;
        Process.setThreadPriority(10);
        try {
            this.thread = Thread.currentThread();
            this.host.onReaderRun(this);
            this.urlRecord.recordUse(this);
            while (true) {
                obtainSegment = this.host.obtainSegment(this, this.urlRecord);
                if (obtainSegment != null) {
                    this.curSegment = obtainSegment;
                    try {
                        try {
                        } catch (Throwable th) {
                            this.curSegment = null;
                            this.host.unObtainSegment(this, obtainSegment);
                            throw th;
                        }
                    } catch (SegmentApplyException e2) {
                        Logger.e(TAG, "run: SegmentApplyException, e = " + e2);
                        int i2 = this.segmentApplyRetryTimes;
                        if (i2 >= 50) {
                            Logger.e(TAG, "segment apply failed " + this.segmentApplyRetryTimes + "times, thread_index = " + this.threadIndex);
                            this.curSegment = null;
                            iSegmentCallback = this.host;
                            break;
                        }
                        this.segmentApplyRetryTimes = i2 + 1;
                        this.curSegment = null;
                        iSegmentCallback2 = this.host;
                    }
                    if (!download(obtainSegment)) {
                        if (!this.closed) {
                            Logger.e(TAG, "download segment failed, segment = " + obtainSegment + ", thread_index = " + this.threadIndex + ", failedException = " + this.failedException);
                            break;
                        }
                        break;
                    }
                    this.succeedSegments.add(obtainSegment);
                    this.curSegment = null;
                    iSegmentCallback2 = this.host;
                    iSegmentCallback2.unObtainSegment(this, obtainSegment);
                } else {
                    Logger.i(TAG, "no more segment, thread_index = " + this.threadIndex);
                    break;
                }
            }
            this.curSegment = null;
            iSegmentCallback = this.host;
            iSegmentCallback.unObtainSegment(this, obtainSegment);
            this.urlRecord.recordUnUse(this);
            this.host.onReaderExit(this);
        } catch (Throwable unused) {
        }
        this.thread = null;
    }

    private boolean download(Segment segment) {
        initParams();
        try {
            try {
                doConnect(segment);
                loopAndRead(segment);
                releaseDownload();
                return true;
            } catch (SegmentApplyException e2) {
                this.failedException = e2;
                throw e2;
            }
        }
    }

    private void releaseDownload() {
        this.lastConnectStartTime = this.connectStartTime;
        this.connectStartTime = -1L;
        this.connectEndTime = -1L;
        this.readStartTime = -1L;
        this.readEndTime = -1L;
        closeConnection();
    }

    private void closeConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.httpConnection;
        if (iDownloadHttpConnection != null) {
            try {
                Logger.i(TAG, "closeConnection: thread = " + this.threadIndex);
                iDownloadHttpConnection.end();
                iDownloadHttpConnection.cancel();
            } catch (Throwable unused) {
            }
        }
    }

    private void initParams() {
        this.httpsToHttpRetryUsed = false;
        resetRetryTimes();
    }

    private void resetRetryTimes() {
        this.retryCount = this.urlRecord.isMainUrl ? this.downloadInfo.getRetryCount() : this.downloadInfo.getBackUpUrlRetryCount();
        this.curRetryCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean switchUrlRecord(UrlRecord urlRecord) {
        int i2 = this.switchUrlTimes;
        if (i2 >= 30) {
            return false;
        }
        this.switchUrlTimes = i2 + 1;
        UrlRecord urlRecord2 = this.urlRecord;
        if (urlRecord2 != null) {
            urlRecord2.recordUnUse(this);
        }
        urlRecord.recordUse(this);
        this.urlRecord = urlRecord;
        resetRetryTimes();
        return true;
    }

    int getCurRetryCount() {
        return this.curRetryCount;
    }

    int getRetryCount() {
        return this.retryCount;
    }

    private boolean handleFailedAndCheckRetry(Segment segment, BaseException baseException) {
        Logger.e(TAG, "handleDownloadFailed:  e = " + baseException + ", curRetryCount = " + this.curRetryCount + ", retryCount = " + this.retryCount);
        this.failedException = baseException;
        this.urlRecord.recordFailed();
        this.host.onSegmentRetry(this, this.urlRecord, segment, baseException, this.curRetryCount, this.retryCount);
        int i2 = this.curRetryCount;
        if (i2 < this.retryCount) {
            this.curRetryCount = i2 + 1;
            return true;
        } else if (checkCanUseHttpsToHttpRetry(baseException)) {
            return true;
        } else {
            this.host.onSegmentFailed(this, this.urlRecord, segment, baseException);
            return false;
        }
    }

    private boolean checkCanUseHttpsToHttpRetry(BaseException baseException) {
        if (DownloadUtils.isHttpsError(baseException)) {
            String str = this.urlRecord.url;
            if (TextUtils.isEmpty(str) || !str.startsWith("https") || !this.downloadInfo.isNeedHttpsToHttpRetry() || this.httpsToHttpRetryUsed) {
                return false;
            }
            this.httpsToHttpRetryUsed = true;
            resetRetryTimes();
            return true;
        }
        return false;
    }

    private void doConnect(Segment segment) {
        createConnection(segment);
        this.host.onSegmentConnected(this, segment, this.urlRecord, this.httpResponse);
        this.urlRecord.recordSucceed();
    }

    private void createConnection(Segment segment) {
        String str;
        String str2;
        IDownloadHttpConnection downloadWithConnection;
        try {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.connectEndTime = 0L;
                this.connectStartTime = currentTimeMillis;
                this.startOffsetInConnection = segment.getCurrentOffsetRead();
                this.endOffsetInConnection = segment.getEndOffset();
                if (this.endOffsetInConnection > 0 && this.startOffsetInConnection > this.endOffsetInConnection) {
                    throw new SegmentApplyException(6, "createConn, " + segment);
                }
                this.stenographer = new DownloadStenographer();
                List<HttpHeader> addRangeHeader = DownloadUtils.addRangeHeader(this.downloadInfo.getExtraHeaders(), this.downloadInfo.geteTag(), this.startOffsetInConnection, this.endOffsetInConnection);
                addRangeHeader.add(new HttpHeader("Segment-Index", String.valueOf(segment.getIndex())));
                addRangeHeader.add(new HttpHeader("Thread-Index", String.valueOf(this.threadIndex)));
                DownloadUtils.addThrottleNetSpeed(addRangeHeader, this.downloadInfo);
                DownloadUtils.addTTNetProtectTimeout(addRangeHeader, this.downloadInfo);
                str = this.urlRecord.url;
                if (this.httpsToHttpRetryUsed && !TextUtils.isEmpty(str) && str.startsWith("https")) {
                    str = str.replaceFirst("https", "http");
                }
                str2 = this.urlRecord.ip;
                Logger.i(TAG, "createConnectionBegin: url = " + str + ", ip = " + str2 + ", segment = " + segment + ", threadIndex = " + this.threadIndex);
                this.curUrl = str;
                this.curHostIp = str2;
                downloadWithConnection = DownloadComponentManager.downloadWithConnection(this.downloadInfo.isNeedDefaultHttpServiceBackUp(), this.downloadInfo.getMaxBytes(), str, str2, addRangeHeader, 0, currentTimeMillis - this.lastConnectStartTime > WsConstants.EXIT_DELAY_TIME && this.setting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_CONNECT) > 0, this.downloadInfo);
            } finally {
                this.connectEndTime = System.currentTimeMillis();
            }
        } catch (BaseException e2) {
            throw e2;
        } catch (Throwable th) {
            DownloadUtils.parseException(th, "createConn");
        }
        if (downloadWithConnection == null) {
            throw new BaseException(1022, new IOException("download can't continue, chunk connection is null"));
        }
        this.httpConnection = downloadWithConnection;
        this.httpResponse = new HttpResponse(str, downloadWithConnection);
        if (this.closed) {
            throw new StreamClosedException("createConn");
        }
        if (downloadWithConnection instanceof AbsDownloadHttpConnection) {
            this.curHostRealIp = ((AbsDownloadHttpConnection) downloadWithConnection).getHostIp();
        }
        Log.i(TAG, "createConnectionSuccess: url = " + str + ", ip = " + str2 + ", hostRealIp = " + this.curHostRealIp + ", threadIndex = " + this.threadIndex);
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0132, code lost:
        r7 = r11 + 1;
        r11 = r13 - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x013c, code lost:
        if (r11 <= 0) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x013e, code lost:
        com.ss.android.socialbase.downloader.logger.Logger.e(com.ss.android.socialbase.downloader.segment.SegmentReader.TAG, "loopAndRead: redundant = " + r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0156, code lost:
        r31.curSegmentReadOffset = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0159, code lost:
        r13 = r7;
        r11 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x015e, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0167, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x016f, code lost:
        throw new com.ss.android.socialbase.downloader.segment.StreamClosedException("loopAndRead");
     */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void loopAndRead(com.ss.android.socialbase.downloader.segment.Segment r32) {
        /*
            Method dump skipped, instructions count: 1019
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.segment.SegmentReader.loopAndRead(com.ss.android.socialbase.downloader.segment.Segment):void");
    }

    private Buffer probeFirstBuffer(IBufferPool iBufferPool, InputStream inputStream) {
        int i2;
        Buffer obtain = iBufferPool.obtain();
        try {
            i2 = inputStream.read(obtain.data);
        } catch (Throwable th) {
            th = th;
            i2 = -1;
        }
        try {
            if (i2 == -1) {
                throw new BaseException((int) DownloadErrorCode.ERROR_PROBE_FIRST_BUFFER, "probe");
            }
            obtain.size = i2;
            if (i2 == -1) {
                iBufferPool.recycle(obtain);
            }
            return obtain;
        } catch (Throwable th2) {
            th = th2;
            if (i2 == -1) {
                iBufferPool.recycle(obtain);
            }
            throw th;
        }
    }

    private long refreshSegmentEndOffset() {
        long j2 = this.segmentNewEndOffset;
        this.segmentNewEndOffset = 0L;
        if (j2 <= 0) {
            return Long.MAX_VALUE;
        }
        return j2;
    }

    public boolean adjustSegmentEndOffset(long j2) {
        long j3 = this.endOffsetInConnection;
        if (j2 > 0 || j3 <= 0) {
            if (j2 <= j3 || j3 <= 0) {
                this.segmentNewEndOffset = j2;
                this.threadDirty = true;
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateReadBytes() {
        UrlRecord urlRecord = this.urlRecord;
        try {
            synchronized (this.host) {
                long readingBytes = getReadingBytes();
                if (readingBytes > 0) {
                    this.readBytes += readingBytes;
                    urlRecord.increaseDownloadBytes(readingBytes);
                }
                this.curSegmentReadOffset = -1L;
            }
        } catch (Throwable unused) {
        }
    }

    public long getReadBytes() {
        long readingBytes;
        synchronized (this.host) {
            readingBytes = this.readBytes + getReadingBytes();
        }
        return readingBytes;
    }

    public long getReadingBytes() {
        synchronized (this.host) {
            long j2 = this.curSegmentReadOffset;
            long j3 = this.startOffsetInConnection;
            if (j3 < 0 || j2 <= j3) {
                return 0L;
            }
            return j2 - j3;
        }
    }

    public long getCurSegmentReadOffset() {
        return this.curSegmentReadOffset;
    }

    public void close() {
        Logger.i(TAG, "close: threadIndex = " + this.threadIndex);
        synchronized (this) {
            this.closed = true;
            this.threadDirty = true;
        }
        closeConnection();
        Future future = this.future;
        if (future != null) {
            this.future = null;
            try {
                future.cancel(true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void reconnect() {
        reconnect(false);
    }

    public void reconnect(boolean z) {
        Logger.i(TAG, "reconnect: threadIndex = " + this.threadIndex);
        synchronized (this) {
            this.changeSegment = z;
            this.reconnect = true;
            this.threadDirty = true;
        }
        closeConnection();
        Thread thread = this.thread;
        if (thread != null) {
            try {
                Log.i(TAG, "reconnect: t.interrupt threadIndex = " + this.threadIndex);
                thread.interrupt();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFuture(Future future) {
        this.future = future;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFailed(boolean z) {
        this.failed = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFailed() {
        return this.failed;
    }

    public boolean isExited() {
        return this.exited;
    }

    public void setExited(boolean z) {
        this.exited = z;
    }

    BaseException getFailedException() {
        return this.failedException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markProgress(long j2) {
        long j3 = this.curSegmentReadOffset;
        DownloadStenographer downloadStenographer = this.stenographer;
        if (j3 < 0 || downloadStenographer == null) {
            return;
        }
        Log.i(TAG, "markProgress: curSegmentReadOffset = " + j3 + ", threadIndex = " + this.threadIndex);
        downloadStenographer.markProgress(j3, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getRecentDownloadSpeed(long j2, long j3) {
        DownloadStenographer downloadStenographer = this.stenographer;
        if (downloadStenographer == null) {
            return -1L;
        }
        return downloadStenographer.getRecentDownloadSpeed(j2, j3);
    }

    long getCurSegmentDownloadSpeed(long j2) {
        long j3 = this.readStartTime;
        if (j3 <= 0) {
            return -1L;
        }
        long j4 = j2 - j3;
        if (j4 <= 0) {
            return -1L;
        }
        long j5 = this.curSegmentReadOffset;
        long j6 = this.startOffsetInConnection;
        if (j6 < 0 || j5 < j6) {
            return 0L;
        }
        return (j5 - j6) / j4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getStartOffsetInConnection() {
        return this.startOffsetInConnection;
    }
}
