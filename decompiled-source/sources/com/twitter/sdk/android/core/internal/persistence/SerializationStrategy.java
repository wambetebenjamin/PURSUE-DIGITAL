package com.twitter.sdk.android.core.internal.persistence;
/* loaded from: classes10.dex */
public interface SerializationStrategy<T> {
    T deserialize(String str);

    String serialize(T t);
}
