package com.bytedance.retrofit2.client;

import com.bytedance.retrofit2.mime.TypedInput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public final class Response {
    private final TypedInput body;
    private Object extraInfo;
    private final List<Header> headers;
    private String message;
    private final String reason;
    private final int status;
    private String traceCode;
    private final String url;

    public Response(String str, int i2, String str2, List<Header> list, TypedInput typedInput) {
        if (str == null) {
            throw new IllegalArgumentException("url == null");
        }
        if (i2 < 200) {
            throw new IllegalArgumentException("Invalid status code: " + i2);
        }
        if (str2 == null) {
            throw new IllegalArgumentException("reason == null");
        }
        if (list == null) {
            throw new IllegalArgumentException("headers == null");
        }
        this.url = str;
        this.status = i2;
        this.reason = str2;
        this.headers = Collections.unmodifiableList(new ArrayList(list));
        this.body = typedInput;
    }

    public String getUrl() {
        return this.url;
    }

    public int getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public Header getFirstHeader(String str) {
        List<Header> list;
        if (str != null && (list = this.headers) != null) {
            for (Header header : list) {
                if (str.equalsIgnoreCase(header.getName())) {
                    return header;
                }
            }
        }
        return null;
    }

    public List<Header> headers(String str) {
        List<Header> list;
        ArrayList arrayList = null;
        if (str != null && (list = this.headers) != null) {
            for (Header header : list) {
                if (str.equalsIgnoreCase(header.getName())) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(header);
                }
            }
        }
        return arrayList;
    }

    public TypedInput getBody() {
        return this.body;
    }

    public boolean isSuccessful() {
        int i2 = this.status;
        return i2 >= 200 && i2 < 300;
    }

    public void setExtraInfo(Object obj) {
        this.extraInfo = obj;
    }

    public Object getExtraInfo() {
        return this.extraInfo;
    }

    public void setTraceCode(String str) {
        this.traceCode = str;
    }

    public String getTraceCode() {
        return this.traceCode;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String message() {
        return this.message;
    }
}
