package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class FetchState {
    private final Consumer<EncodedImage> mConsumer;
    private final ProducerContext mContext;
    @Nullable
    private Map<String, String> mEncodeImageExtraInfo;
    @Nullable
    private String mMd5;
    private int mOnNewResultStatusFlags;
    @Nullable
    private BytesRange mResponseBytesRange;
    private boolean mNeedMd5 = true;
    private long mLastIntermediateResultTimeMs = 0;

    public FetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mConsumer = consumer;
        this.mContext = producerContext;
    }

    public Consumer<EncodedImage> getConsumer() {
        return this.mConsumer;
    }

    public ProducerContext getContext() {
        return this.mContext;
    }

    public String getId() {
        return this.mContext.getId();
    }

    public ProducerListener getListener() {
        return this.mContext.getListener();
    }

    public Uri getUri() {
        return this.mContext.getImageRequest().getSourceUri();
    }

    @Nullable
    public List<Uri> getBackupUris() {
        return this.mContext.getImageRequest().getBackupUris();
    }

    public long getLastIntermediateResultTimeMs() {
        return this.mLastIntermediateResultTimeMs;
    }

    public void setLastIntermediateResultTimeMs(long j2) {
        this.mLastIntermediateResultTimeMs = j2;
    }

    public int getOnNewResultStatusFlags() {
        return this.mOnNewResultStatusFlags;
    }

    public void setOnNewResultStatusFlags(int i2) {
        this.mOnNewResultStatusFlags = i2;
    }

    @Nullable
    public BytesRange getResponseBytesRange() {
        return this.mResponseBytesRange;
    }

    public void setResponseBytesRange(BytesRange bytesRange) {
        this.mResponseBytesRange = bytesRange;
    }

    @Nullable
    public String getMd5() {
        return this.mMd5;
    }

    public void setMd5(String str) {
        this.mMd5 = str;
    }

    public boolean needMd5() {
        return this.mNeedMd5;
    }

    public void setNeedMd5(boolean z) {
        this.mNeedMd5 = z;
    }

    @Nullable
    public Map<String, String> getEncodeImageExtraInfo() {
        return this.mEncodeImageExtraInfo;
    }

    public void setEncodeImageExtraInfo(@Nullable Map<String, String> map) {
        this.mEncodeImageExtraInfo = map;
    }
}
