package com.facebook.imagepipeline.cache;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes8.dex */
public class MemoryCacheUtil {
    private static final int CACHE_MAP_DEFAULT_MAX_SIZE = 1000;
    protected static final String SPLIT_RESIZE_OPTION_MARKER = "resizeOption:";
    protected static final String SPLIT_URI_MARKER = "_uri_end";
    private static final String TAG = "MemoryCacheUtil";
    protected static boolean mKeyOnlyUseUri = false;
    protected static boolean mOptReuseBitmapCache = false;
    protected static ConcurrentHashMap<String, String> mReuseBitmapCacheMap = new ConcurrentHashMap<>();
    protected static int maxHashMapSize = 1000;

    public static void setKeyOnlyUseUri(boolean z) {
        mKeyOnlyUseUri = z;
    }

    public static void setOptReuseBitmapCache(boolean z) {
        mOptReuseBitmapCache = z;
    }

    public static boolean isOptReuseBitmapCache() {
        return mOptReuseBitmapCache;
    }

    public static void setReuseBitmapCacheMapMaxSize(int i2) {
        maxHashMapSize = i2;
    }

    public static void clearReuseBitmapCacheMap() {
        if (mOptReuseBitmapCache) {
            try {
                mReuseBitmapCacheMap.clear();
            } catch (Throwable th) {
                FLog.d(TAG, "clearDiskCacheVisitCountMap mImageLoadDurationMap clear failed! t = " + th);
            }
        }
    }

    public static void removeReuseBitmapCache(String str) {
        if (mOptReuseBitmapCache && !TextUtils.isEmpty(str) && str.contains(SPLIT_URI_MARKER) && str.contains(SPLIT_RESIZE_OPTION_MARKER)) {
            try {
                if (mKeyOnlyUseUri) {
                    mReuseBitmapCacheMap.remove(str.substring(0, str.indexOf(SPLIT_RESIZE_OPTION_MARKER)));
                } else {
                    mReuseBitmapCacheMap.remove(str.substring(0, str.indexOf(SPLIT_RESIZE_OPTION_MARKER)));
                }
            } catch (Throwable th) {
                FLog.d(TAG, "removeReuseBitmapCache: Throwable = " + th);
            }
        }
    }
}
