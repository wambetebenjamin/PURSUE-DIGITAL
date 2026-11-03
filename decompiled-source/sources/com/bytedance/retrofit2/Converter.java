package com.bytedance.retrofit2;

import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface Converter<F, T> {

    /* loaded from: classes2.dex */
    public static abstract class Factory {
        @Nullable
        public Converter<?, Header> headerConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
            return null;
        }

        @Nullable
        public Converter<?, Object> objectConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
            return null;
        }

        @Nullable
        public Converter<?, TypedOutput> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
            return null;
        }

        @Nullable
        public Converter<TypedInput, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
            return null;
        }

        @Nullable
        public Converter<?, String> stringConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
            return null;
        }
    }

    @Nullable
    T convert(F f2);
}
