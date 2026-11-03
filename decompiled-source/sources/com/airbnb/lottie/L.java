package com.airbnb.lottie;

import android.content.Context;
import androidx.core.os.TraceCompat;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.bytedance.librarian.c;
import java.io.File;
/* loaded from: classes.dex */
public class L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static LottieNetworkCacheProvider cacheProvider = null;
    private static int depthPastMaxDepth = 0;
    private static LottieNetworkFetcher fetcher = null;
    private static volatile NetworkCache networkCache = null;
    private static volatile NetworkFetcher networkFetcher = null;
    private static String[] sections = null;
    private static long[] startTimeNs = null;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    private L() {
    }

    public static void setTraceEnabled(boolean z) {
        if (traceEnabled == z) {
            return;
        }
        traceEnabled = z;
        if (z) {
            sections = new String[20];
            startTimeNs = new long[20];
        }
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            int i2 = traceDepth;
            if (i2 == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i2] = str;
            startTimeNs[i2] = System.nanoTime();
            TraceCompat.beginSection(str);
            traceDepth++;
        }
    }

    public static float endSection(String str) {
        int i2 = depthPastMaxDepth;
        if (i2 > 0) {
            depthPastMaxDepth = i2 - 1;
            return 0.0f;
        } else if (traceEnabled) {
            int i3 = traceDepth - 1;
            traceDepth = i3;
            if (i3 == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            }
            if (!str.equals(sections[i3])) {
                throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + sections[traceDepth] + c.a.DOT);
            }
            TraceCompat.endSection();
            return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
        } else {
            return 0.0f;
        }
    }

    public static void setFetcher(LottieNetworkFetcher lottieNetworkFetcher) {
        fetcher = lottieNetworkFetcher;
    }

    public static void setCacheProvider(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        cacheProvider = lottieNetworkCacheProvider;
    }

    public static NetworkFetcher networkFetcher(Context context) {
        NetworkFetcher networkFetcher2 = networkFetcher;
        if (networkFetcher2 == null) {
            synchronized (NetworkFetcher.class) {
                networkFetcher2 = networkFetcher;
                if (networkFetcher2 == null) {
                    NetworkCache networkCache2 = networkCache(context);
                    LottieNetworkFetcher lottieNetworkFetcher = fetcher;
                    if (lottieNetworkFetcher == null) {
                        lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                    }
                    networkFetcher2 = new NetworkFetcher(networkCache2, lottieNetworkFetcher);
                    networkFetcher = networkFetcher2;
                }
            }
        }
        return networkFetcher2;
    }

    public static NetworkCache networkCache(Context context) {
        final Context applicationContext = context.getApplicationContext();
        NetworkCache networkCache2 = networkCache;
        if (networkCache2 == null) {
            synchronized (NetworkCache.class) {
                networkCache2 = networkCache;
                if (networkCache2 == null) {
                    LottieNetworkCacheProvider lottieNetworkCacheProvider = cacheProvider;
                    if (lottieNetworkCacheProvider == null) {
                        lottieNetworkCacheProvider = new LottieNetworkCacheProvider() { // from class: com.airbnb.lottie.L.1
                            @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
                            public File getCacheDir() {
                                return new File(applicationContext.getCacheDir(), "lottie_network_cache");
                            }
                        };
                    }
                    networkCache2 = new NetworkCache(lottieNetworkCacheProvider);
                    networkCache = networkCache2;
                }
            }
        }
        return networkCache2;
    }
}
