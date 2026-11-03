package com.ss.android.socialbase.downloader.thread;

import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
/* loaded from: classes9.dex */
public class DownloadChunkRunnable implements Runnable {
    private static final String TAG = "DownloadChunkRunnable";
    private final IDownloadRunnableCallback callback;
    private volatile boolean canceled;
    private DownloadChunk curDownloadChunk;
    private IDownloadCache downloadCache;
    private DownloadChunk downloadChunk;
    private DownloadInfo downloadInfo;
    private DownloadResponseHandler downloadResponseHandler;
    private final DownloadTask downloadTask;
    private IDownloadHttpConnection httpConnection;
    private boolean isHttpConnectionInject;
    private volatile boolean paused;

    public DownloadChunkRunnable(DownloadChunk downloadChunk, DownloadTask downloadTask, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this.isHttpConnectionInject = false;
        this.downloadChunk = downloadChunk;
        this.downloadTask = downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
        }
        this.callback = iDownloadRunnableCallback;
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        this.downloadChunk.setChunkRunnable(this);
    }

    public DownloadChunkRunnable(DownloadChunk downloadChunk, DownloadTask downloadTask, IDownloadHttpConnection iDownloadHttpConnection, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this(downloadChunk, downloadTask, iDownloadRunnableCallback);
        this.httpConnection = iDownloadHttpConnection;
    }

    public int getChunkIndex() {
        return this.downloadChunk.getChunkIndex();
    }

    private String getUrl() {
        return this.downloadInfo.getConnectionUrl();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
        r3.curDownloadChunk.setDownloading(false);
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r3 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            com.ss.android.socialbase.downloader.model.DownloadChunk r0 = r3.downloadChunk
            r3.curDownloadChunk = r0
        L9:
            r0 = 0
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            r1.setChunkRunnable(r3)     // Catch: java.lang.Throwable -> L5d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r3.downloadChunkInner(r1)     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L1d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            r1.setDownloading(r0)     // Catch: java.lang.Throwable -> L5d
            goto L4d
        L1d:
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            r1.setDownloading(r0)     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r3.isStoppedStatus()     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto L29
            goto L4d
        L29:
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r1 = r3.callback     // Catch: java.lang.Throwable -> L5d
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            int r2 = r2.getChunkIndex()     // Catch: java.lang.Throwable -> L5d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r1.getUnCompletedSubChunk(r2)     // Catch: java.lang.Throwable -> L5d
            r3.curDownloadChunk = r1     // Catch: java.lang.Throwable -> L5d
            boolean r1 = r3.isStoppedStatus()     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L4d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5d
            if (r1 != 0) goto L42
            goto L4d
        L42:
            r1 = 50
            java.lang.Thread.sleep(r1)     // Catch: java.lang.Throwable -> L48
            goto L9
        L48:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L5d
            goto L9
        L4d:
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk
            if (r1 == 0) goto L54
            r1.setDownloading(r0)
        L54:
            r3.closeConnection()
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r0 = r3.callback
            r0.onCompleted(r3)
            return
        L5d:
            r1 = move-exception
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk
            if (r2 == 0) goto L65
            r2.setDownloading(r0)
        L65:
            r3.closeConnection()
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r0 = r3.callback
            r0.onCompleted(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable.run():void");
    }

    public void refreshResponseHandleOffset(long j2, long j3) {
        DownloadResponseHandler downloadResponseHandler = this.downloadResponseHandler;
        if (downloadResponseHandler == null) {
            return;
        }
        downloadResponseHandler.setEndOffset(j2, j3);
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x01dd A[Catch: all -> 0x02a0, TRY_ENTER, TryCatch #0 {all -> 0x02a0, blocks: (B:132:0x01d3, B:136:0x01dd, B:138:0x01e3, B:141:0x01ec, B:143:0x01f4, B:145:0x01fa, B:149:0x0205, B:151:0x0209, B:153:0x0211, B:155:0x0222, B:164:0x0248, B:166:0x024e, B:168:0x025b, B:172:0x0263, B:167:0x0255, B:158:0x022f, B:159:0x023b, B:174:0x026e, B:176:0x0276, B:178:0x027e, B:180:0x0286, B:182:0x028e, B:185:0x0297, B:120:0x01b6, B:124:0x01c0, B:127:0x01c7), top: B:191:0x01d3, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x024e A[Catch: all -> 0x02a0, TryCatch #0 {all -> 0x02a0, blocks: (B:132:0x01d3, B:136:0x01dd, B:138:0x01e3, B:141:0x01ec, B:143:0x01f4, B:145:0x01fa, B:149:0x0205, B:151:0x0209, B:153:0x0211, B:155:0x0222, B:164:0x0248, B:166:0x024e, B:168:0x025b, B:172:0x0263, B:167:0x0255, B:158:0x022f, B:159:0x023b, B:174:0x026e, B:176:0x0276, B:178:0x027e, B:180:0x0286, B:182:0x028e, B:185:0x0297, B:120:0x01b6, B:124:0x01c0, B:127:0x01c7), top: B:191:0x01d3, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0255 A[Catch: all -> 0x02a0, TryCatch #0 {all -> 0x02a0, blocks: (B:132:0x01d3, B:136:0x01dd, B:138:0x01e3, B:141:0x01ec, B:143:0x01f4, B:145:0x01fa, B:149:0x0205, B:151:0x0209, B:153:0x0211, B:155:0x0222, B:164:0x0248, B:166:0x024e, B:168:0x025b, B:172:0x0263, B:167:0x0255, B:158:0x022f, B:159:0x023b, B:174:0x026e, B:176:0x0276, B:178:0x027e, B:180:0x0286, B:182:0x028e, B:185:0x0297, B:120:0x01b6, B:124:0x01c0, B:127:0x01c7), top: B:191:0x01d3, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0263 A[Catch: all -> 0x02a0, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x02a0, blocks: (B:132:0x01d3, B:136:0x01dd, B:138:0x01e3, B:141:0x01ec, B:143:0x01f4, B:145:0x01fa, B:149:0x0205, B:151:0x0209, B:153:0x0211, B:155:0x0222, B:164:0x0248, B:166:0x024e, B:168:0x025b, B:172:0x0263, B:167:0x0255, B:158:0x022f, B:159:0x023b, B:174:0x026e, B:176:0x0276, B:178:0x027e, B:180:0x0286, B:182:0x028e, B:185:0x0297, B:120:0x01b6, B:124:0x01c0, B:127:0x01c7), top: B:191:0x01d3, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x025f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x01d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x00fb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ff A[Catch: all -> 0x01b4, BaseException -> 0x01d0, TRY_ENTER, TryCatch #16 {BaseException -> 0x01d0, all -> 0x01b4, blocks: (B:22:0x004f, B:26:0x0059, B:30:0x0064, B:35:0x00b8, B:37:0x00bc, B:45:0x00d1, B:60:0x00f5, B:64:0x00ff, B:66:0x0103, B:77:0x0131, B:74:0x0129, B:76:0x0130, B:49:0x00db, B:51:0x00df), top: B:208:0x004f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean downloadChunkInner(com.ss.android.socialbase.downloader.model.DownloadChunk r31) {
        /*
            Method dump skipped, instructions count: 677
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable.downloadChunkInner(com.ss.android.socialbase.downloader.model.DownloadChunk):boolean");
    }

    private void revertDownloadChunk(DownloadChunk downloadChunk, long j2) {
        DownloadChunk firstReuseChunk = downloadChunk.isHostChunk() ? downloadChunk.getFirstReuseChunk() : downloadChunk;
        if (firstReuseChunk != null) {
            if (firstReuseChunk.canRefreshCurOffsetForReuseChunk()) {
                this.downloadCache.updateDownloadChunk(firstReuseChunk.getId(), firstReuseChunk.getHostChunkIndex(), j2);
            }
            firstReuseChunk.setCurrentOffset(j2);
            this.downloadCache.updateSubDownloadChunk(firstReuseChunk.getId(), firstReuseChunk.getChunkIndex(), firstReuseChunk.getHostChunkIndex(), j2);
        } else if (downloadChunk.isHostChunk()) {
            this.downloadCache.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getChunkIndex(), j2);
        }
    }

    private void closeConnection() {
        IDownloadHttpConnection iDownloadHttpConnection = this.httpConnection;
        if (iDownloadHttpConnection != null) {
            iDownloadHttpConnection.end();
            this.httpConnection = null;
        }
    }

    private boolean isStoppedStatus() {
        return this.paused || this.canceled;
    }

    public void pause() {
        this.paused = true;
        DownloadResponseHandler downloadResponseHandler = this.downloadResponseHandler;
        if (downloadResponseHandler != null) {
            downloadResponseHandler.pause();
        }
    }

    public void cancel() {
        this.canceled = true;
        DownloadResponseHandler downloadResponseHandler = this.downloadResponseHandler;
        if (downloadResponseHandler != null) {
            downloadResponseHandler.cancel();
        }
    }
}
