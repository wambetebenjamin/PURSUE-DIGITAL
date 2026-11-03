package com.bytedance.hades.tgrp.impl;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.bytedance.hades.tgrp.api.ResInfo;
import com.bytedance.hades.tgrp.api.TGRPBatchDownloadListener;
import com.bytedance.hades.tgrp.api.TGRPDownloader;
import com.bytedance.hades.tgrp.api.TGRPGlobalDownloadListener;
import com.bytedance.hades.tgrp.api.TGRPInitListener;
import com.bytedance.hades.tgrp.api.TGRPPatchListener;
import com.bytedance.hades.tgrp.api.TGRPResCheckListener;
import g.hades.tgrp.a;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class TGRPDownloaderImpl implements TGRPDownloader {

    /* renamed from: f  reason: collision with root package name */
    public final String f9436f;

    /* renamed from: g  reason: collision with root package name */
    public final String f9437g;

    /* renamed from: h  reason: collision with root package name */
    public final String f9438h;
    public final long k;
    public final String l;

    /* renamed from: a  reason: collision with root package name */
    public TGRPInitListener f9431a = null;

    /* renamed from: b  reason: collision with root package name */
    public TGRPGlobalDownloadListener f9432b = null;

    /* renamed from: c  reason: collision with root package name */
    public final Map<Integer, TGRPBatchDownloadListener> f9433c = new ConcurrentHashMap();

    /* renamed from: d  reason: collision with root package name */
    public final Map<Integer, TGRPResCheckListener> f9434d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    public final Map<Integer, TGRPPatchListener> f9435e = new ConcurrentHashMap();

    /* renamed from: i  reason: collision with root package name */
    public boolean f9439i = false;

    /* renamed from: j  reason: collision with root package name */
    public final SparseArray<a> f9440j = new SparseArray<>();

    public TGRPDownloaderImpl(Context context, String str, String str2, String str3) {
        long nativeGetTGRPDownloader = nativeGetTGRPDownloader(str, str2, str3);
        this.k = nativeGetTGRPDownloader;
        this.f9436f = str;
        this.f9437g = str2;
        this.f9438h = str3;
        this.l = nativeGetAbsPath(nativeGetTGRPDownloader);
    }

    private native void nativeClearBatchInfo(long j2, int i2);

    private native void nativeClearResNotInBatch(long j2);

    private native int nativeCreateBatchAllRes(long j2, boolean z);

    private native int nativeCreateBatchByLabel(long j2, String str, boolean z);

    private native int nativeCreateBatchInDirectory(long j2, String str, boolean z);

    private native int nativeCreateBatchResList(long j2, int[] iArr, boolean z);

    private native boolean nativeDeleteRes(long j2, String str);

    private native void nativeDeleteTGRPDownloader(String str, String str2, String str3);

    private native int nativeExcludeResFromBatch(long j2, int i2, int[] iArr);

    private native String nativeGetAbsPath(long j2);

    private native long nativeGetAllResDataDownloadedBytes(long j2);

    private native long nativeGetAllResDataTotalBytes(long j2);

    private native TGRPDownloader.BatchDownloadInfo nativeGetBatchDownloadInfo(long j2, int i2);

    private native long nativeGetDownloadedBytesByLabel(long j2, String str);

    private native int[] nativeGetDownloadedResInfoList(long j2);

    private native int nativeGetDownloadingBatchId(long j2);

    private native int[] nativeGetDownloadingBatchSequence(long j2);

    private native int nativeGetDownloadingFileCount(long j2);

    private native int[] nativeGetDownloadingResInfo(long j2);

    private native long nativeGetListResDataDownloadedBytes(long j2, int[] iArr);

    private native long nativeGetListResDataDownloadedBytesBatchId(long j2, int i2);

    private native long nativeGetListResDataTotalBytes(long j2, int[] iArr);

    private native long nativeGetListResDataTotalBytesByBatchId(long j2, int i2);

    private native int[] nativeGetNotDownloadedResInfoList(long j2);

    private native void nativeGetOldDeleteResInfoList(long j2);

    private native int nativeGetResCount(long j2);

    private native void nativeGetResInfoList(long j2);

    private native int[] nativeGetResInfoListByLabel(long j2, String str);

    private native String nativeGetResInfoMd5(long j2, int i2);

    private native String nativeGetResInfoResPath(long j2, int i2);

    private native long nativeGetTGRPDownloader(String str, String str2, String str3);

    private native long nativeGetTotalBytesByLabel(long j2, String str);

    private native void nativeInit(long j2, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4);

    private native boolean nativeIsInited(long j2);

    private native boolean nativeIsIniting(long j2);

    private native boolean nativeIsResFileCorrect(long j2, String str);

    private native boolean nativeIsResFileDownloaded(long j2, String str);

    private native void nativePauseDownload(long j2, int i2);

    private native int nativeResCheck(long j2, int i2);

    private native void nativeSetBatchPreDownload(long j2, int i2, boolean z);

    private native void nativeSetBatchStage(long j2, int i2, boolean z);

    private native void nativeSetFileSystem(long j2, long j3);

    private native void nativeSetMaxParallelCount(long j2, int i2);

    private native void nativeSetOnlyWifi(long j2, boolean z);

    private native void nativeSetPriority(long j2, int i2, int i3, int i4);

    private native void nativeSetRetryTime(long j2, int i2);

    private native void nativeSetThrottleNetSpeed(long j2, long j3);

    private native void nativeSetUseMultiRange(long j2, boolean z);

    private native void nativeStartDownload(long j2, int i2);

    private native void nativeStartDownloadMulti(long j2, int[] iArr);

    private void notifyBatchDownloadFromNative(int i2, int i3, long j2, long j3, double d2, String str, int[] iArr, int[] iArr2) {
        Log.i("TGRPDownloader(java)", "notifyBatchDownload: what:" + i2 + " batchId:" + i3 + " curBytes:" + j2 + " totalBytes:" + j3 + " speed:" + d2 + " " + Arrays.toString(iArr) + Arrays.toString(iArr2));
        TGRPBatchDownloadListener tGRPBatchDownloadListener = this.f9433c.get(Integer.valueOf(i3));
        int i4 = 0;
        if (i2 == 6) {
            if (tGRPBatchDownloadListener != null) {
                ResInfo[] resInfoArr = new ResInfo[iArr.length];
                for (int i5 = 0; i5 < iArr.length; i5++) {
                    resInfoArr[i5] = this.f9440j.get(iArr[i5]);
                }
                ResInfo[] resInfoArr2 = new ResInfo[iArr2.length];
                while (i4 < iArr2.length) {
                    resInfoArr2[i4] = this.f9440j.get(iArr2[i4]);
                    i4++;
                }
                tGRPBatchDownloadListener.onBatchStart(i3, j2, j3, resInfoArr, resInfoArr2);
            }
        } else if (i2 == 7) {
            if (tGRPBatchDownloadListener != null) {
                ResInfo[] resInfoArr3 = new ResInfo[iArr.length];
                while (i4 < iArr.length) {
                    resInfoArr3[i4] = this.f9440j.get(iArr[i4]);
                    i4++;
                }
                tGRPBatchDownloadListener.onBatchProgress(i3, j2, j3, d2, resInfoArr3);
            }
        } else if (i2 == 8 && tGRPBatchDownloadListener != null) {
            ResInfo[] resInfoArr4 = new ResInfo[iArr.length];
            ResInfo[] resInfoArr5 = new ResInfo[iArr2.length];
            for (int i6 = 0; i6 < iArr.length; i6++) {
                resInfoArr4[i6] = this.f9440j.get(iArr[i6]);
            }
            while (i4 < iArr2.length) {
                resInfoArr5[i4] = this.f9440j.get(iArr2[i4]);
                i4++;
            }
            tGRPBatchDownloadListener.onBatchEnd(i3, resInfoArr4, resInfoArr5, (int) j2, str);
        }
    }

    private void notifyGlobalDownloadFromNative(int i2, long j2, long j3, double d2, String str, int[] iArr, int[] iArr2) {
        TGRPGlobalDownloadListener tGRPGlobalDownloadListener = this.f9432b;
        int i3 = 0;
        switch (i2) {
            case 9:
                if (tGRPGlobalDownloadListener != null) {
                    ResInfo[] resInfoArr = new ResInfo[iArr.length];
                    while (i3 < iArr.length) {
                        resInfoArr[i3] = this.f9440j.get(iArr[i3]);
                        i3++;
                    }
                    tGRPGlobalDownloadListener.onAddToDownloadQueue(resInfoArr);
                    return;
                }
                return;
            case 10:
                if (tGRPGlobalDownloadListener != null) {
                    tGRPGlobalDownloadListener.onProgress(j2, j3, d2);
                    return;
                }
                return;
            case 11:
                if (tGRPGlobalDownloadListener != null) {
                    ResInfo[] resInfoArr2 = new ResInfo[iArr.length];
                    ResInfo[] resInfoArr3 = new ResInfo[iArr2.length];
                    for (int i4 = 0; i4 < iArr.length; i4++) {
                        resInfoArr2[i4] = this.f9440j.get(iArr[i4]);
                    }
                    while (i3 < iArr2.length) {
                        resInfoArr3[i3] = this.f9440j.get(iArr2[i3]);
                        i3++;
                    }
                    tGRPGlobalDownloadListener.onDownloadQueueEmpty(resInfoArr2, resInfoArr3, (int) j2, str);
                    return;
                }
                return;
            case 12:
                if (tGRPGlobalDownloadListener != null) {
                    tGRPGlobalDownloadListener.onAllResDownloadSuccess();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void notifyInitFromNative(int i2, long j2, long j3, int i3, String str, int[] iArr, int[] iArr2, int[] iArr3) {
        TGRPInitListener tGRPInitListener = this.f9431a;
        if (i2 == -2) {
            Log.e("TGRPDownloader(java)", "notifyInit: init_fail errorCode = " + i3 + " errorMsg = " + str);
            if (tGRPInitListener != null) {
                tGRPInitListener.onInitFail(i3, str);
            }
        } else if (i2 != -1) {
            if (i2 != 0) {
                return;
            }
            Log.i("TGRPDownloader(java)", "notifyInit: progress: curBytes:" + j2 + " totalBytes:" + j3);
            if (tGRPInitListener != null) {
                tGRPInitListener.onInitProgress(j2, j3);
            }
        } else {
            Log.i("TGRPDownloader(java)", "notifyInit: errorCode:" + i3 + " errorMsg:" + str + " " + iArr.length + " " + iArr2.length + " " + iArr3.length);
            Log.i("TGRPDownloader(java)", "notifyInit: add" + Arrays.toString(iArr) + ", delete" + Arrays.toString(iArr2) + ", modify" + Arrays.toString(iArr3));
            this.f9440j.clear();
            nativeGetResInfoList(this.k);
            nativeGetOldDeleteResInfoList(this.k);
            if (tGRPInitListener != null) {
                ResInfo[] resInfoArr = new ResInfo[iArr.length];
                ResInfo[] resInfoArr2 = new ResInfo[iArr2.length];
                ResInfo[] resInfoArr3 = new ResInfo[iArr3.length];
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    resInfoArr[i4] = this.f9440j.get(iArr[i4]);
                }
                for (int i5 = 0; i5 < iArr2.length; i5++) {
                    resInfoArr2[i5] = this.f9440j.get(iArr2[i5]);
                }
                for (int i6 = 0; i6 < iArr3.length; i6++) {
                    resInfoArr3[i6] = this.f9440j.get(iArr3[i6]);
                }
                tGRPInitListener.onInitSuccess(resInfoArr, resInfoArr2, resInfoArr3);
                this.f9431a = null;
            }
            this.f9439i = true;
        }
    }

    private void notifyPatchFromNative(int i2, int i3, int i4, int i5, long j2, long j3) {
        TGRPPatchListener tGRPPatchListener = this.f9435e.get(Integer.valueOf(i3));
        if (tGRPPatchListener == null) {
            return;
        }
        switch (i2) {
            case 16:
                tGRPPatchListener.onPatchStart(i3);
                return;
            case 17:
                tGRPPatchListener.onPatchProgress(i3, i4, i5, j2, j3);
                return;
            case 18:
                tGRPPatchListener.onPatchEnd(i3);
                return;
            default:
                return;
        }
    }

    private void notifyResCheckFromNative(int i2, int i3, int i4, int i5, long j2, long j3, int[] iArr, int[] iArr2) {
        TGRPResCheckListener tGRPResCheckListener = this.f9434d.get(Integer.valueOf(i3));
        if (tGRPResCheckListener == null) {
            return;
        }
        switch (i2) {
            case 13:
                tGRPResCheckListener.onResCheckStart(i3);
                return;
            case 14:
                tGRPResCheckListener.onResCheckProgress(i3, i4, i5, j2, j3);
                return;
            case 15:
                ResInfo[] resInfoArr = new ResInfo[iArr.length];
                ResInfo[] resInfoArr2 = new ResInfo[iArr2.length];
                for (int i6 = 0; i6 < iArr.length; i6++) {
                    resInfoArr[i6] = this.f9440j.get(iArr[i6]);
                }
                for (int i7 = 0; i7 < iArr2.length; i7++) {
                    resInfoArr2[i7] = this.f9440j.get(iArr2[i7]);
                }
                tGRPResCheckListener.onResCheckEnd(i3, resInfoArr, resInfoArr2);
                return;
            default:
                return;
        }
    }

    private void notifyResDownloadFromNative(int i2, int i3, int i4, String str, long j2, long j3) {
    }

    private void setResInfo(long j2, int i2) {
        this.f9440j.put(i2, new a(this.f9436f, j2, this.l, i2));
    }

    public void a() {
        nativeDeleteTGRPDownloader(this.f9436f, this.f9437g, this.f9438h);
    }

    public String b(int i2) {
        return nativeGetResInfoResPath(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void clearBatchInfo(int i2) {
        nativeClearBatchInfo(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void clearResNotInBatch() {
        nativeClearResNotInBatch(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByAllRes() {
        return createBatchByAllRes(false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByDirectory(String str) {
        return createBatchByDirectory(str, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByLabel(String str) {
        return createBatchByLabel(str, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByResList(ResInfo[] resInfoArr) {
        return createBatchByResList(resInfoArr, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean deleteRes(String str) {
        return nativeDeleteRes(this.k, str);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadAllRes() {
        return downloadAllRes(null, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadByLabel(String str) {
        return downloadByLabel(str, null, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadResInDirectory(String str) {
        return downloadResInDirectory(str, null, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadResList(ResInfo[] resInfoArr) {
        return downloadResList(resInfoArr, null, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public long getAllResDataDownloadedBytes() {
        return nativeGetAllResDataDownloadedBytes(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public long getAllResDataTotalBytes() {
        return nativeGetAllResDataTotalBytes(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader.BatchDownloadInfo getBatchDownloadInfo(int i2) {
        return nativeGetBatchDownloadInfo(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public long getDownloadedBytesByLabel(String str) {
        return nativeGetDownloadedBytesByLabel(this.k, str);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public ResInfo[] getDownloadedResInfoList() {
        int[] nativeGetDownloadedResInfoList = nativeGetDownloadedResInfoList(this.k);
        ResInfo[] resInfoArr = new ResInfo[nativeGetDownloadedResInfoList.length];
        for (int i2 = 0; i2 < nativeGetDownloadedResInfoList.length; i2++) {
            resInfoArr[i2] = this.f9440j.get(nativeGetDownloadedResInfoList[i2]);
        }
        return resInfoArr;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int getDownloadingBatchId() {
        return nativeGetDownloadingBatchId(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int[] getDownloadingBatchSequence() {
        return nativeGetDownloadingBatchSequence(this.k);
    }

    public int getDownloadingFileCount() {
        return nativeGetDownloadingFileCount(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public long getListResDataDownloadedBytes(ResInfo[] resInfoArr) {
        if (resInfoArr.length == 0) {
            return 0L;
        }
        int[] iArr = new int[resInfoArr.length];
        for (int i2 = 0; i2 < resInfoArr.length; i2++) {
            iArr[i2] = ((a) resInfoArr[i2]).f19107c;
        }
        return nativeGetListResDataDownloadedBytes(this.k, iArr);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public long getListResDataTotalBytes(ResInfo[] resInfoArr) {
        if (resInfoArr.length == 0) {
            return 0L;
        }
        int[] iArr = new int[resInfoArr.length];
        for (int i2 = 0; i2 < resInfoArr.length; i2++) {
            iArr[i2] = ((a) resInfoArr[i2]).f19107c;
        }
        return nativeGetListResDataTotalBytes(this.k, iArr);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public ResInfo[] getNotDownloadedResInfoList() {
        int[] nativeGetNotDownloadedResInfoList = nativeGetNotDownloadedResInfoList(this.k);
        ResInfo[] resInfoArr = new ResInfo[nativeGetNotDownloadedResInfoList.length];
        for (int i2 = 0; i2 < nativeGetNotDownloadedResInfoList.length; i2++) {
            resInfoArr[i2] = this.f9440j.get(nativeGetNotDownloadedResInfoList[i2]);
        }
        return resInfoArr;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int getResCount() {
        return nativeGetResCount(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public ResInfo getResInfo(String str) {
        int nativeGetResInfo = nativeGetResInfo(this.k, str);
        if (nativeGetResInfo == Integer.MAX_VALUE) {
            return null;
        }
        return this.f9440j.get(nativeGetResInfo);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public ResInfo[] getResInfoList() {
        ResInfo[] resInfoArr = new ResInfo[getResCount()];
        if (this.f9439i) {
            for (int i2 = 0; i2 < getResCount(); i2++) {
                a aVar = this.f9440j.get(i2);
                if (aVar != null) {
                    resInfoArr[i2] = aVar;
                }
            }
        }
        return resInfoArr;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public ResInfo[] getResInfoListByLabel(String str) {
        int[] nativeGetResInfoListByLabel = nativeGetResInfoListByLabel(this.k, str);
        ResInfo[] resInfoArr = new ResInfo[nativeGetResInfoListByLabel.length];
        for (int i2 = 0; i2 < nativeGetResInfoListByLabel.length; i2++) {
            resInfoArr[i2] = this.f9440j.get(nativeGetResInfoListByLabel[i2]);
        }
        return resInfoArr;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public long getTotalBytesByLabel(String str) {
        return nativeGetTotalBytesByLabel(this.k, str);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void init(TGRPDownloader.InitConfig initConfig) {
        nativeInit(this.k, initConfig.getUrl(), initConfig.getBackUpHosts(), initConfig.getDiffPatchUrls(), initConfig.getIncrementalPackUrls(), initConfig.getAppBaseResJson(), initConfig.getHeaders().toString(), initConfig.getConfig());
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean isInited() {
        boolean nativeIsInited = nativeIsInited(this.k);
        this.f9439i = nativeIsInited;
        return nativeIsInited;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean isIniting() {
        return nativeIsIniting(this.k);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean isResFileCorrect(String str) {
        return nativeIsResFileCorrect(this.k, str);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean isResFileDownloaded(String str) {
        return nativeIsResFileDownloaded(this.k, str);
    }

    public native int nativeGetResInfo(long j2, String str);

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void pauseDownload() {
        pauseDownload(0);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader registerBatchListener(int i2, TGRPBatchDownloadListener tGRPBatchDownloadListener) {
        if (tGRPBatchDownloadListener != null) {
            this.f9433c.put(Integer.valueOf(i2), tGRPBatchDownloadListener);
        }
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader registerGlobalDownloadListener(TGRPGlobalDownloadListener tGRPGlobalDownloadListener) {
        this.f9432b = tGRPGlobalDownloadListener;
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader registerInitListener(TGRPInitListener tGRPInitListener) {
        this.f9431a = tGRPInitListener;
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader registerPatchListener(int i2, TGRPPatchListener tGRPPatchListener) {
        if (tGRPPatchListener != null) {
            this.f9435e.put(Integer.valueOf(i2), tGRPPatchListener);
        }
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader registerResCheckListener(int i2, TGRPResCheckListener tGRPResCheckListener) {
        if (tGRPResCheckListener != null) {
            this.f9434d.put(Integer.valueOf(i2), tGRPResCheckListener);
        }
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int resCheck(int i2) {
        return nativeResCheck(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void setBatchPreDownload(int i2, boolean z) {
        nativeSetBatchPreDownload(this.k, i2, z);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void setBatchStage(int i2, boolean z) {
        nativeSetBatchStage(this.k, i2, z);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void setFileSystem(long j2) {
        nativeSetFileSystem(this.k, j2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader setMaxParallelCount(int i2) {
        nativeSetMaxParallelCount(this.k, i2);
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader setOnlyWifi(boolean z) {
        nativeSetOnlyWifi(this.k, z);
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void setPriority(int i2, int i3) {
        setPriority(i2, i3, 0);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader setRetryTime(int i2) {
        nativeSetRetryTime(this.k, i2);
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader setThrottleNetSpeed(long j2) {
        nativeSetThrottleNetSpeed(this.k, j2);
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void startDownload(int i2) {
        nativeStartDownload(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader unregisterBatchListener(int i2) {
        this.f9433c.remove(Integer.valueOf(i2));
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader unregisterGlobalDownloadListener() {
        this.f9432b = null;
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader unregisterInitListener() {
        this.f9431a = null;
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader unregisterPatchListener(int i2) {
        this.f9435e.remove(Integer.valueOf(i2));
        return this;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public TGRPDownloader unregisterResCheckListener(int i2) {
        this.f9434d.remove(Integer.valueOf(i2));
        return this;
    }

    public String a(int i2) {
        return nativeGetResInfoMd5(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void clearBatchInfo() {
        nativeClearBatchInfo(this.k, 0);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByAllRes(boolean z) {
        return nativeCreateBatchAllRes(this.k, z);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByDirectory(String str, boolean z) {
        return nativeCreateBatchInDirectory(this.k, str, z);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByLabel(String str, boolean z) {
        return nativeCreateBatchByLabel(this.k, str, z);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int createBatchByResList(ResInfo[] resInfoArr, boolean z) {
        int[] iArr = new int[resInfoArr.length];
        for (int i2 = 0; i2 < resInfoArr.length; i2++) {
            iArr[i2] = ((a) resInfoArr[i2]).f19107c;
        }
        return nativeCreateBatchResList(this.k, iArr, z);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean deleteRes(ResInfo resInfo) {
        return deleteRes(resInfo.getResPath());
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadAllRes(TGRPBatchDownloadListener tGRPBatchDownloadListener) {
        return downloadAllRes(tGRPBatchDownloadListener, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadByLabel(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener) {
        return downloadByLabel(str, tGRPBatchDownloadListener, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadResInDirectory(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener) {
        return downloadResInDirectory(str, tGRPBatchDownloadListener, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadResList(ResInfo[] resInfoArr, TGRPBatchDownloadListener tGRPBatchDownloadListener) {
        return downloadResList(resInfoArr, tGRPBatchDownloadListener, false);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean isResFileCorrect(ResInfo resInfo) {
        return isResFileCorrect(resInfo.getResPath());
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public boolean isResFileDownloaded(ResInfo resInfo) {
        return isResFileDownloaded(resInfo.getResPath());
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void pauseDownload(int i2) {
        nativePauseDownload(this.k, i2);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void setPriority(int i2, int i3, int i4) {
        nativeSetPriority(this.k, i2, i3, i4);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public void startDownload(int[] iArr) {
        nativeStartDownloadMulti(this.k, iArr);
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadAllRes(TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z) {
        int nativeCreateBatchAllRes = nativeCreateBatchAllRes(this.k, z);
        if (nativeCreateBatchAllRes > 0) {
            if (tGRPBatchDownloadListener != null) {
                this.f9433c.put(Integer.valueOf(nativeCreateBatchAllRes), tGRPBatchDownloadListener);
            }
            startDownload(nativeCreateBatchAllRes);
        }
        return nativeCreateBatchAllRes;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadByLabel(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z) {
        int nativeCreateBatchByLabel = nativeCreateBatchByLabel(this.k, str, z);
        if (nativeCreateBatchByLabel > 0) {
            if (tGRPBatchDownloadListener != null) {
                this.f9433c.put(Integer.valueOf(nativeCreateBatchByLabel), tGRPBatchDownloadListener);
            }
            startDownload(nativeCreateBatchByLabel);
        }
        return nativeCreateBatchByLabel;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadResInDirectory(String str, TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z) {
        int nativeCreateBatchInDirectory = nativeCreateBatchInDirectory(this.k, str, z);
        if (nativeCreateBatchInDirectory > 0) {
            if (tGRPBatchDownloadListener != null) {
                this.f9433c.put(Integer.valueOf(nativeCreateBatchInDirectory), tGRPBatchDownloadListener);
            }
            startDownload(nativeCreateBatchInDirectory);
        }
        return nativeCreateBatchInDirectory;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    @Deprecated
    public int downloadResList(ResInfo[] resInfoArr, TGRPBatchDownloadListener tGRPBatchDownloadListener, boolean z) {
        int[] iArr = new int[resInfoArr.length];
        for (int i2 = 0; i2 < resInfoArr.length; i2++) {
            iArr[i2] = ((a) resInfoArr[i2]).f19107c;
        }
        int nativeCreateBatchResList = nativeCreateBatchResList(this.k, iArr, z);
        if (nativeCreateBatchResList > 0) {
            if (tGRPBatchDownloadListener != null) {
                this.f9433c.put(Integer.valueOf(nativeCreateBatchResList), tGRPBatchDownloadListener);
            }
            startDownload(nativeCreateBatchResList);
        }
        return nativeCreateBatchResList;
    }

    @Override // com.bytedance.hades.tgrp.api.TGRPDownloader
    public int excludeResFromBatch(int i2, ResInfo[] resInfoArr) {
        int[] iArr;
        if (resInfoArr == null) {
            iArr = new int[0];
        } else {
            int[] iArr2 = new int[resInfoArr.length];
            for (int i3 = 0; i3 < resInfoArr.length; i3++) {
                iArr2[i3] = ((a) resInfoArr[i3]).f19107c;
            }
            iArr = iArr2;
        }
        return nativeExcludeResFromBatch(this.k, i2, iArr);
    }
}
