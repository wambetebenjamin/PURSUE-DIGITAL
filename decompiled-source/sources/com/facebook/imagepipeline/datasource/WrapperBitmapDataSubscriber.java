package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public abstract class WrapperBitmapDataSubscriber extends BaseBitmapDataSubscriber {
    private BaseBitmapDataSubscriber mBaseBitmapDataSubscriber;

    protected abstract Bitmap convertBitmap(Bitmap bitmap);

    @Override // com.facebook.datasource.BaseDataSubscriber
    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
    }

    public WrapperBitmapDataSubscriber(BaseBitmapDataSubscriber baseBitmapDataSubscriber) {
        this.mBaseBitmapDataSubscriber = baseBitmapDataSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
    public void onNewResultImpl(@Nullable Bitmap bitmap) {
        BaseBitmapDataSubscriber baseBitmapDataSubscriber = this.mBaseBitmapDataSubscriber;
        if (baseBitmapDataSubscriber != null) {
            baseBitmapDataSubscriber.onNewResultImpl(convertBitmap(bitmap));
        }
    }

    @Override // com.facebook.datasource.BaseDataSubscriber, com.facebook.datasource.DataSubscriber
    public void onFailure(DataSource<CloseableReference<CloseableImage>> dataSource) {
        BaseBitmapDataSubscriber baseBitmapDataSubscriber = this.mBaseBitmapDataSubscriber;
        if (baseBitmapDataSubscriber != null) {
            baseBitmapDataSubscriber.onFailure(dataSource);
        }
    }

    @Override // com.facebook.datasource.BaseDataSubscriber, com.facebook.datasource.DataSubscriber
    public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
        BaseBitmapDataSubscriber baseBitmapDataSubscriber = this.mBaseBitmapDataSubscriber;
        if (baseBitmapDataSubscriber != null) {
            baseBitmapDataSubscriber.onCancellation(dataSource);
        }
    }

    @Override // com.facebook.datasource.BaseDataSubscriber, com.facebook.datasource.DataSubscriber
    public void onProgressUpdate(DataSource<CloseableReference<CloseableImage>> dataSource) {
        BaseBitmapDataSubscriber baseBitmapDataSubscriber = this.mBaseBitmapDataSubscriber;
        if (baseBitmapDataSubscriber != null) {
            baseBitmapDataSubscriber.onProgressUpdate(dataSource);
        }
    }
}
