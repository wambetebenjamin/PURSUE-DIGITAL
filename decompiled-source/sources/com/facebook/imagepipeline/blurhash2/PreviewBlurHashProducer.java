package com.facebook.imagepipeline.blurhash2;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.core.PriorityThreadFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes8.dex */
public class PreviewBlurHashProducer {
    public static final int DEFAULT_MAX_NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final String TAG = "PreviewBlurHashProducer";
    private static Executor mDecodeExecutor;
    private static String mGlobalMetaData;
    private PreviewBlurDecodeCallBack mBlurDecodeCallBack;
    private String mBlurHash;
    private final Runnable mDoJobRunnable;
    private String mMetaData;
    private PreviewHashConfig mPreviewHashConfig;

    /* loaded from: classes8.dex */
    public interface PreviewBlurDecodeCallBack {
        void decodeResult(Bitmap bitmap);
    }

    public static void setGlobalMetaData(String str) {
        mGlobalMetaData = str;
    }

    public PreviewBlurHashProducer(String str, PreviewHashConfig previewHashConfig, PreviewBlurDecodeCallBack previewBlurDecodeCallBack) {
        this(str, mGlobalMetaData, previewHashConfig, previewBlurDecodeCallBack);
    }

    public PreviewBlurHashProducer(String str, String str2, PreviewHashConfig previewHashConfig, PreviewBlurDecodeCallBack previewBlurDecodeCallBack) {
        Runnable runnable = new Runnable() { // from class: com.facebook.imagepipeline.blurhash2.PreviewBlurHashProducer.1
            @Override // java.lang.Runnable
            public void run() {
                Bitmap blurhashBitmap = PreviewBlurHashProducer.this.mPreviewHashConfig != null ? PreviewBlurHashDecoder.getBlurhashBitmap(PreviewBlurHashProducer.this.mBlurHash, PreviewBlurHashProducer.this.mMetaData, PreviewBlurHashProducer.this.mPreviewHashConfig.getIterations(), PreviewBlurHashProducer.this.mPreviewHashConfig.getBlurRadius()) : PreviewBlurHashDecoder.getBlurhashBitmap(PreviewBlurHashProducer.this.mBlurHash, PreviewBlurHashProducer.this.mMetaData, 1, 1);
                if (PreviewBlurHashProducer.this.mBlurDecodeCallBack != null) {
                    PreviewBlurHashProducer.this.mBlurDecodeCallBack.decodeResult(blurhashBitmap);
                }
            }
        };
        this.mDoJobRunnable = runnable;
        if (mDecodeExecutor == null) {
            mDecodeExecutor = Executors.newFixedThreadPool(DEFAULT_MAX_NUM_THREADS, new PriorityThreadFactory(10, "BlurDecodeExecutor", true));
        }
        this.mBlurHash = str;
        this.mMetaData = str2;
        this.mPreviewHashConfig = previewHashConfig;
        this.mBlurDecodeCallBack = previewBlurDecodeCallBack;
        mDecodeExecutor.execute(runnable);
    }

    public void doJob() {
        mDecodeExecutor.execute(this.mDoJobRunnable);
    }
}
