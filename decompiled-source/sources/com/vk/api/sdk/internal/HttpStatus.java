package com.vk.api.sdk.internal;

import android.util.SparseArray;
import com.bytedance.common.wschannel.channel.impl.ok.f;
import com.bytedance.frameworks.baselib.network.http.util.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HttpStatus.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/vk/api/sdk/internal/HttpStatus;", "", "()V", "sMap", "Landroid/util/SparseArray;", "", "getDescription", "status", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class HttpStatus {
    public static final HttpStatus INSTANCE = new HttpStatus();
    private static final SparseArray<String> sMap;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(100, "Continue");
        sparseArray.put(101, "Switching Protocols");
        sparseArray.put(102, "Processing");
        sparseArray.put(103, "Checkpoint");
        sparseArray.put(200, "OK");
        sparseArray.put(201, "Created");
        sparseArray.put(202, "Accepted");
        sparseArray.put(203, "Non-Authoritative Information");
        sparseArray.put(204, "No Content");
        sparseArray.put(205, "Reset Content");
        sparseArray.put(206, "Partial Content");
        sparseArray.put(207, "Multi-Status");
        sparseArray.put(208, "Already Reported");
        sparseArray.put(226, "IM Used");
        sparseArray.put(300, "Multiple Choices");
        sparseArray.put(301, "Moved Permanently");
        sparseArray.put(302, "Found");
        sparseArray.put(303, "See Other");
        sparseArray.put(304, "Not Modified");
        sparseArray.put(f.V, "Use Proxy");
        sparseArray.put(307, "Temporary Redirect");
        sparseArray.put(308, "Permanent Redirect");
        sparseArray.put(400, "Bad Request");
        sparseArray.put(401, "Unauthorized");
        sparseArray.put(402, "Payment Required");
        sparseArray.put(403, "Forbidden");
        sparseArray.put(404, "Not Found");
        sparseArray.put(f.s, "Method Not Allowed");
        sparseArray.put(f.y, "Not Acceptable");
        sparseArray.put(f.I, "Proxy Authentication Required");
        sparseArray.put(f.K, "Request Timeout");
        sparseArray.put(409, "Conflict");
        sparseArray.put(410, "Gone");
        sparseArray.put(411, "Length Required");
        sparseArray.put(412, "Precondition Failed");
        sparseArray.put(413, "Payload Too Large");
        sparseArray.put(414, "URI Too Long");
        sparseArray.put(415, "Unsupported Media Type");
        sparseArray.put(416, "Requested range not satisfiable");
        sparseArray.put(417, "Expectation Failed");
        sparseArray.put(418, "I'm a teapot");
        sparseArray.put(f.m, "Insufficient Space On Resource");
        sparseArray.put(f.r, "Method Failure");
        sparseArray.put(421, "Destination Locked");
        sparseArray.put(f.T, "Unprocessable Entity");
        sparseArray.put(f.q, "Locked");
        sparseArray.put(f.f8334h, "Failed Dependency");
        sparseArray.put(426, "Upgrade Required");
        sparseArray.put(428, "Precondition Required");
        sparseArray.put(429, "Too Many Requests");
        sparseArray.put(431, "Request Header Fields Too Large");
        sparseArray.put(500, "Internal Server Error");
        sparseArray.put(501, "Not Implemented");
        sparseArray.put(f.f8328b, "Bad Gateway");
        sparseArray.put(f.P, "Service Unavailable");
        sparseArray.put(f.f8336j, "Gateway Timeout");
        sparseArray.put(f.l, "HTTP Version not supported");
        sparseArray.put(506, "Variant Also Negotiates");
        sparseArray.put(f.n, "Insufficient Storage");
        sparseArray.put(508, "Loop Detected");
        sparseArray.put(509, "Bandwidth Limit Exceeded");
        sparseArray.put(f.b.l, "Not Extended");
        sparseArray.put(511, "Network Authentication Required");
        sMap = sparseArray;
    }

    private HttpStatus() {
    }

    public final String getDescription(int i2) {
        String str = sMap.get(i2);
        Intrinsics.checkExpressionValueIsNotNull(str, "sMap.get(status)");
        return str;
    }
}
