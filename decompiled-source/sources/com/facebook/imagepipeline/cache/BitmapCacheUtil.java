package com.facebook.imagepipeline.cache;

import android.text.TextUtils;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.ResizeOptions;
/* loaded from: classes8.dex */
public class BitmapCacheUtil extends MemoryCacheUtil {
    private static final String TAG = "BitmapCacheUtil";
    private static int mReuseRule;

    /* loaded from: classes8.dex */
    public @interface ReuseRule {
        public static final int BOTH_WH = 0;
        public static final int W_OR_H = 1;
        public static final int W_X_H = 2;
    }

    private BitmapCacheUtil() {
    }

    public static void setReuseRule(int i2) {
        mReuseRule = i2;
    }

    public static void insertReuseBitmapCacheMap(CacheKey cacheKey) {
        String uriString;
        if (mOptReuseBitmapCache && cacheKey != null && (cacheKey instanceof BitmapMemoryCacheKey) && !TextUtils.isEmpty(cacheKey.toString()) && cacheKey.toString().contains("resizeOption:")) {
            try {
                if (mReuseBitmapCacheMap.size() > maxHashMapSize) {
                    mReuseBitmapCacheMap.clear();
                }
                ResizeOptions resizeOptions = ((BitmapMemoryCacheKey) cacheKey).getResizeOptions();
                if (resizeOptions == null) {
                    return;
                }
                if (mKeyOnlyUseUri) {
                    uriString = cacheKey.getUriString();
                } else {
                    try {
                        uriString = cacheKey.toString().substring(0, cacheKey.toString().indexOf("resizeOption:"));
                    } catch (Exception unused) {
                        uriString = cacheKey.getUriString();
                    }
                }
                mReuseBitmapCacheMap.put(uriString, resizeOptions.toString());
            } catch (Throwable th) {
                FLog.d(TAG, "newDiskCacheVisitCount: Throwable = " + th);
            }
        }
    }

    public static ResizeOptions getOptReuseResizeOption(CacheKey cacheKey) {
        ResizeOptions resizeOptions;
        String uriString;
        try {
        } catch (Throwable th) {
            FLog.d(TAG, "getOptReuseResizeOption: 111 Throwable = " + th);
        }
        if (mOptReuseBitmapCache && (cacheKey instanceof BitmapMemoryCacheKey) && (resizeOptions = ((BitmapMemoryCacheKey) cacheKey).getResizeOptions()) != null) {
            if (mKeyOnlyUseUri) {
                uriString = cacheKey.getUriString();
            } else {
                try {
                    uriString = cacheKey.toString().substring(0, cacheKey.toString().indexOf("resizeOption:"));
                } catch (Exception unused) {
                    uriString = cacheKey.getUriString();
                }
            }
            String str = mReuseBitmapCacheMap.get(uriString);
            String[] split = TextUtils.isEmpty(str) ? null : str.split(WebViewConfig.LOCATION_X);
            if (split != null && split.length == 2 && resizeOptions != null) {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                int i2 = mReuseRule;
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (parseInt * parseInt2 >= resizeOptions.width * resizeOptions.height) {
                            return new ResizeOptions(parseInt, parseInt2);
                        }
                    } else if (parseInt >= resizeOptions.width && parseInt2 >= resizeOptions.height) {
                        return new ResizeOptions(parseInt, parseInt2);
                    }
                } else if (parseInt >= resizeOptions.width || parseInt2 >= resizeOptions.height) {
                    return new ResizeOptions(parseInt, parseInt2);
                }
            }
            return null;
        }
        return null;
    }
}
