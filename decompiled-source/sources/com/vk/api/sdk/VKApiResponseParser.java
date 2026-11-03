package com.vk.api.sdk;

import com.vk.api.sdk.exceptions.VKApiException;
/* loaded from: classes10.dex */
public interface VKApiResponseParser<Result> {
    Result parse(String str) throws VKApiException;
}
