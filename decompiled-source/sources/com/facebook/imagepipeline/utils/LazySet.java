package com.facebook.imagepipeline.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
/* compiled from: LazySet.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\b\u001a\u00020\tH&R\u0018\u0010\u0003\u001a\u00028\u0000X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/facebook/imagepipeline/utils/LazySet;", ExifInterface.GPS_DIRECTION_TRUE, "", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "isInitialized", "", "imagepipeline_noasanRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface LazySet<T> {
    T getValue();

    boolean isInitialized();

    void setValue(T t);
}
