package com.facebook.imagepipeline.blurhash;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.core.PriorityThreadFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes8.dex */
public class BlurDecodeProducer {
    public static final int DEFAULT_MAX_NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final String TAG = "BlurDecode";
    private static Executor mDecodeExecutor;
    private BlurDecodeCallBack mBlurDecodeCallBack;
    private String mBlurHash;
    private BlurHashConfig mBlurHashConfig;
    private final Runnable mDoJobRunnable;

    /* loaded from: classes8.dex */
    public interface BlurDecodeCallBack {
        void decodeResult(Bitmap bitmap);
    }

    public BlurDecodeProducer(String str, BlurHashConfig blurHashConfig, BlurDecodeCallBack blurDecodeCallBack) {
        Runnable runnable = new Runnable() { // from class: com.facebook.imagepipeline.blurhash.BlurDecodeProducer.1
            @Override // java.lang.Runnable
            public void run() {
                Bitmap decode;
                long uptimeMillis = SystemClock.uptimeMillis();
                if (BlurDecodeProducer.this.mBlurHashConfig == null) {
                    decode = new BlurHashDecoder().decode(BlurDecodeProducer.this.mBlurHash, 200, 120, 1.0f, true);
                } else {
                    decode = new BlurHashDecoder().decode(BlurDecodeProducer.this.mBlurHash, BlurDecodeProducer.this.mBlurHashConfig.getWidth(), BlurDecodeProducer.this.mBlurHashConfig.getHeight(), BlurDecodeProducer.this.mBlurHashConfig.getPunch(), BlurDecodeProducer.this.mBlurHashConfig.isUseCache());
                }
                FLog.d("SimpleDraweeView", "setBlurHashImage: decode blurHash cost time is " + (SystemClock.uptimeMillis() - uptimeMillis));
                if (BlurDecodeProducer.this.mBlurDecodeCallBack != null) {
                    BlurDecodeProducer.this.mBlurDecodeCallBack.decodeResult(decode);
                }
            }
        };
        this.mDoJobRunnable = runnable;
        if (mDecodeExecutor == null) {
            mDecodeExecutor = Executors.newFixedThreadPool(DEFAULT_MAX_NUM_THREADS, new PriorityThreadFactory(10, "BlurDecodeExecutor", true));
        }
        this.mBlurHash = str;
        this.mBlurHashConfig = blurHashConfig;
        this.mBlurDecodeCallBack = blurDecodeCallBack;
        mDecodeExecutor.execute(runnable);
    }

    public void doJob() {
        mDecodeExecutor.execute(this.mDoJobRunnable);
    }
}
