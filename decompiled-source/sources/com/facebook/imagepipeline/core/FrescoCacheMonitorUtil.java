package com.facebook.imagepipeline.core;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes8.dex */
public class FrescoCacheMonitorUtil {
    private static long mBigImgSizeLimit = 2097152;
    private static long mDelayTime = 0;
    private static boolean mDelayWriteDisk = false;
    private static boolean mEnableBigImgCache = false;
    private static boolean mEnableNewLocalVideoThumbnailOpt = false;
    private static boolean mEnableOOMCallerContext = false;
    private static boolean mEnableSensibleOpt = false;
    private static int mHeicDecodeThreads = 1;
    private static boolean mHeicUseWpp = false;
    private static int mHeifDecodeThreads = 1;
    private static boolean mHeifUseWpp = false;
    private static boolean mIsSplitMemCache = false;
    private static boolean mIsSplitPrefetchCache = false;
    private static boolean mMultiUrlCacheOpt = false;
    private static boolean mOptAutoSr = false;
    private static boolean mOptBitmapCacheKey = false;
    private static boolean mOptMultiOriginListener = false;
    private static boolean mOptPrefetchCacheKey = false;
    private static boolean mOptRgbaForPostProcessor = false;
    private static boolean mOptSensibleMonitor = false;
    private static boolean mRetainPreviousImage = false;
    private static boolean mSRAutoScaleLowerFirst = false;
    private static boolean mSaveAfterSrPostBitmap = false;
    private static boolean mSavePreSrPostBitmap = false;
    private static float mStretchAboveForSR = 0.0f;
    private static boolean mUseNewOptHeifBitmap = false;
    private static boolean mUseOptHeifBitmap = false;
    private static int mUsedBytes = 0;
    private static int mVvicDecodeThreads = 1;
    private static boolean mVvicUseWpp;
    private static Map<String, String> mWaitParseParamFromUrl;
    private static int maxSizeHardCap;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class SingletonHolder {
        private static final int NUM_IO_BOUND_THREADS = 2;
        private static final ScheduledExecutorService mIoBoundExecutor = Executors.newScheduledThreadPool(2, new PriorityThreadFactory(10, "FrescoDelayIoBoundExecutor", true));

        private SingletonHolder() {
        }
    }

    public static ScheduledExecutorService getNewIoBoundExecutor() {
        return SingletonHolder.mIoBoundExecutor;
    }

    public static void setDelayWriteDisk(boolean z, long j2) {
        mDelayWriteDisk = z;
        mDelayTime = j2;
        if (z || getNewIoBoundExecutor() == null) {
            return;
        }
        getNewIoBoundExecutor().shutdown();
    }

    public static boolean isDelayWriteDisk() {
        return mDelayWriteDisk;
    }

    public static long getDelayTime() {
        return mDelayTime;
    }

    public static void setMaxSizeHardCap(int i2) {
        maxSizeHardCap = i2;
    }

    public static int getMaxSizeHardCap() {
        return maxSizeHardCap;
    }

    public static void setUsedBytes(int i2) {
        mUsedBytes = i2;
    }

    public static int getUsedBytes() {
        return mUsedBytes;
    }

    public static void setSplitMemCache(boolean z) {
        mIsSplitMemCache = z;
    }

    public static boolean isSplitMemCache() {
        return mIsSplitMemCache;
    }

    public static void setSplitPrefetchCache(boolean z) {
        mIsSplitPrefetchCache = z;
    }

    public static boolean isSplitPrefetchCache() {
        return mIsSplitPrefetchCache;
    }

    public static void setOptPrefetchCacheKey(boolean z) {
        mOptPrefetchCacheKey = z;
    }

    public static boolean isOptPrefetchCacheKey() {
        return mOptPrefetchCacheKey;
    }

    public static void setOptBitmapCacheKey(boolean z) {
        mOptBitmapCacheKey = z;
    }

    public static boolean isOptBitmapCacheKey() {
        return mOptBitmapCacheKey;
    }

    public static void setEnableBigImgCache(boolean z) {
        mEnableBigImgCache = z;
    }

    public static boolean isEnableBigImgCache() {
        return mEnableBigImgCache;
    }

    public static void setEnableOOMCallerContext(boolean z) {
        mEnableOOMCallerContext = z;
    }

    public static boolean isEnableOOMCallerContext() {
        return mEnableOOMCallerContext;
    }

    public static void setEnableSensibleOpt(boolean z) {
        mEnableSensibleOpt = z;
    }

    public static boolean isEnableSensibleOpt() {
        return mEnableSensibleOpt;
    }

    public static void setBigImgSizeLimit(long j2) {
        mBigImgSizeLimit = j2;
    }

    public static long getBigImgSizeLimit() {
        return mBigImgSizeLimit;
    }

    public static void setEnableSavePreSrPostBitmap(boolean z) {
        mSavePreSrPostBitmap = z;
    }

    public static boolean isSavePrePostBitmap() {
        return mSavePreSrPostBitmap;
    }

    public static void setEnableSaveAfterSrPostBitmap(boolean z) {
        mSaveAfterSrPostBitmap = z;
    }

    public static boolean isSaveAfterPostBitmap() {
        return mSaveAfterSrPostBitmap;
    }

    public static void setRetainPreviousImage(boolean z) {
        mRetainPreviousImage = z;
    }

    public static boolean isRetainPreviousImage() {
        return mRetainPreviousImage;
    }

    public static boolean isNewLocalVideoThumbnailOptEnabled() {
        return mEnableNewLocalVideoThumbnailOpt;
    }

    public static void setNewLocalVideoThumbnailOptEnabled(boolean z) {
        mEnableNewLocalVideoThumbnailOpt = z;
    }

    public static void setWaitParseParamFromUrl(Map<String, String> map) {
        mWaitParseParamFromUrl = map;
    }

    public static Map<String, String> getWaitParseParamFromUrl() {
        return mWaitParseParamFromUrl;
    }

    public static void setUseOptHeifBitmap(boolean z) {
        mUseOptHeifBitmap = z;
    }

    public static boolean isUseOptHeifBitmap() {
        return mUseOptHeifBitmap;
    }

    public static void setUseNewOptHeifBitmap(boolean z) {
        mUseNewOptHeifBitmap = z;
    }

    public static boolean isUseNewOptHeifBitmap() {
        return mUseNewOptHeifBitmap;
    }

    public static void setOptMultiOriginListener(boolean z) {
        mOptMultiOriginListener = z;
    }

    public static boolean isOptMultiOriginListener() {
        return mOptMultiOriginListener;
    }

    public static void setSRAutoScaleLowerFirst(boolean z) {
        mSRAutoScaleLowerFirst = z;
    }

    public static boolean isSRAutoScaleLowerFirst() {
        return mSRAutoScaleLowerFirst;
    }

    public static void setVvicUseWpp(boolean z) {
        mVvicUseWpp = z;
    }

    public static boolean isVvicUseWpp() {
        return mVvicUseWpp;
    }

    public static void setVvicDecodeThreads(int i2) {
        mVvicDecodeThreads = i2;
    }

    public static int getVvicDecodeThreads() {
        return mVvicDecodeThreads;
    }

    public static void setHeicUseWpp(boolean z) {
        mHeicUseWpp = z;
    }

    public static boolean isHeicUseWpp() {
        return mHeicUseWpp;
    }

    public static void setHeicDecodeThreads(int i2) {
        mHeicDecodeThreads = i2;
    }

    public static int getHeicDecodeThreads() {
        return mHeicDecodeThreads;
    }

    public static void setHeifUseWpp(boolean z) {
        mHeifUseWpp = z;
    }

    public static boolean isHeifUseWpp() {
        return mHeifUseWpp;
    }

    public static void setHeifDecodeThreads(int i2) {
        mHeifDecodeThreads = i2;
    }

    public static int getHeifDecodeThreads() {
        return mHeifDecodeThreads;
    }

    public static void setOptAutoSr(boolean z) {
        mOptAutoSr = z;
    }

    public static boolean isOptAutoSr() {
        return mOptAutoSr;
    }

    public static void setStretchAboveForSR(float f2) {
        mStretchAboveForSR = f2;
    }

    public static float getStretchAboveForSR() {
        return mStretchAboveForSR;
    }

    public static void setMultiUrlCacheOpt(boolean z) {
        mMultiUrlCacheOpt = z;
    }

    public static boolean isMultiUrlCacheOpt() {
        return mMultiUrlCacheOpt;
    }

    public static void setOptSensibleMonitor(boolean z) {
        mOptSensibleMonitor = z;
    }

    public static boolean isOptSensibleMonitor() {
        return mOptSensibleMonitor;
    }

    public static void setOptRgbaForPostProcessor(boolean z) {
        mOptRgbaForPostProcessor = z;
    }

    public static boolean isOptRgbaForPostProcessor() {
        return mOptRgbaForPostProcessor;
    }
}
