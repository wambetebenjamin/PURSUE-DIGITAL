package com.ss.android.socialbase.downloader.downloader;

import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.segment.Segment;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public interface IDownloadCache {
    DownloadInfo OnDownloadTaskCancel(int i2, long j2);

    DownloadInfo OnDownloadTaskCompleted(int i2, long j2);

    DownloadInfo OnDownloadTaskConnected(int i2, long j2, String str, String str2);

    DownloadInfo OnDownloadTaskError(int i2, long j2);

    DownloadInfo OnDownloadTaskIntercept(int i2);

    DownloadInfo OnDownloadTaskPause(int i2, long j2);

    DownloadInfo OnDownloadTaskPrepare(int i2);

    DownloadInfo OnDownloadTaskProgress(int i2, long j2);

    DownloadInfo OnDownloadTaskRetry(int i2);

    void addDownloadChunk(DownloadChunk downloadChunk);

    void addSubDownloadChunk(DownloadChunk downloadChunk);

    boolean cacheExist(int i2);

    void clearData();

    boolean ensureDownloadCacheSyncSuccess();

    List<DownloadInfo> getAllDownloadInfo();

    List<DownloadChunk> getDownloadChunk(int i2);

    DownloadInfo getDownloadInfo(int i2);

    List<DownloadInfo> getDownloadInfoList(String str);

    List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str);

    Map<Long, Segment> getSegmentMap(int i2);

    List<Segment> getSegments(int i2);

    List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str);

    List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str);

    void init();

    boolean isDownloadCacheSyncSuccess();

    DownloadInfo onDownloadTaskStart(int i2);

    void removeAllDownloadChunk(int i2);

    boolean removeDownloadInfo(int i2);

    boolean removeDownloadTaskData(int i2);

    void removeSegments(int i2);

    void syncDownloadChunks(int i2, List<DownloadChunk> list);

    void syncDownloadInfo(DownloadInfo downloadInfo);

    void syncDownloadInfoFromOtherCache(int i2, List<DownloadChunk> list);

    DownloadInfo updateChunkCount(int i2, int i3);

    void updateDownloadChunk(int i2, int i3, long j2);

    boolean updateDownloadInfo(DownloadInfo downloadInfo);

    boolean updateSegments(int i2, Map<Long, Segment> map);

    void updateSubDownloadChunk(int i2, int i3, int i4, long j2);

    void updateSubDownloadChunkIndex(int i2, int i3, int i4, int i5);
}
