package com.bytedance.retrofit2.client;

import com.bytedance.retrofit2.HttpMethodContrants;
import com.bytedance.retrofit2.OptConfig;
import com.bytedance.retrofit2.RetrofitMetrics;
import com.bytedance.retrofit2.Utils;
import com.bytedance.retrofit2.mime.FormUrlEncodedTypedOutput;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.RequestBody;
/* loaded from: classes2.dex */
public final class Request {
    public static final String KEY_BYPASS_DISPATCH = "x-tt-bypass-dp";
    public static final String KEY_BYPASS_OKHTTP_COOKIE = "x-metasec-bypass-okhttp-cookie";
    public static final String KEY_BYPASS_TTNET_COOKIE_HEADER = "x-ttnet-bypass-cookie";
    public static final String KEY_BYPASS_TTNET_FEATURES = "x-metasec-bypass-ttnet-features";
    private final boolean addCommonParam;
    private final TypedOutput body;
    private int commonParamLevel;
    private Object extraInfo;
    private final List<Header> headers;
    private boolean isBodyEncryptEnabled;
    private boolean isQueryEncryptEnabled;
    private final int maxLength;
    private final String method;
    private RetrofitMetrics metrics;
    private final int priorityLevel;
    private int queryFilterPriority;
    private final RequestBody requestBody;
    private final int requestPriorityLevel;
    private final boolean responseStreaming;
    private final String serviceType;
    private final Map<Class<?>, Object> tags;
    private URI uri;
    private final String url;

    public Request(String str, String str2, List<Header> list, TypedOutput typedOutput, int i2, boolean z, int i3, boolean z2, Object obj) {
        this(str, str2, list, typedOutput, null, i2, 3, z, i3, z2, 0, obj, "", null);
    }

    public Request(String str, String str2, List<Header> list, TypedOutput typedOutput, RequestBody requestBody, int i2, int i3, boolean z, int i4, boolean z2, int i5, Object obj, String str3, Map<Class<?>, Object> map) {
        this.queryFilterPriority = 0;
        Objects.requireNonNull(str, "Method must not be null.");
        Objects.requireNonNull(str2, "URL must not be null.");
        this.method = str;
        this.url = str2;
        if (list == null) {
            this.headers = Collections.emptyList();
        } else {
            this.headers = Collections.unmodifiableList(new ArrayList(list));
        }
        this.body = typedOutput;
        this.requestBody = requestBody;
        this.priorityLevel = i2;
        this.requestPriorityLevel = i3;
        this.responseStreaming = z;
        this.maxLength = i4;
        this.addCommonParam = z2;
        this.commonParamLevel = i5;
        this.extraInfo = obj;
        this.serviceType = str3;
        this.tags = map;
    }

    Request(Builder builder) {
        this.queryFilterPriority = 0;
        Objects.requireNonNull(builder.url, "URL must not be null.");
        this.url = builder.url;
        Objects.requireNonNull(builder.method, "Method must not be null.");
        this.method = builder.method;
        if (builder.headers == null) {
            this.headers = Collections.emptyList();
        } else {
            this.headers = Collections.unmodifiableList(new ArrayList(builder.headers));
        }
        this.body = builder.body;
        this.requestBody = builder.requestBody;
        this.priorityLevel = builder.priorityLevel;
        this.requestPriorityLevel = builder.requestPriorityLevel;
        this.responseStreaming = builder.responseStreaming;
        this.maxLength = builder.maxLength;
        this.addCommonParam = builder.addCommonParam;
        this.commonParamLevel = builder.commonParamLevel;
        this.extraInfo = builder.extraInfo;
        this.serviceType = builder.serviceType;
        this.metrics = builder.metrics;
        this.tags = builder.tags;
        this.isQueryEncryptEnabled = builder.isQueryEncryptEnabled;
        this.isBodyEncryptEnabled = builder.isBodyEncryptEnabled;
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
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

    public TypedOutput getBody() {
        RequestBody requestBody = this.requestBody;
        if (requestBody != null) {
            return Utils.convert(requestBody);
        }
        return this.body;
    }

    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    public int getRequestPriorityLevel() {
        return this.requestPriorityLevel;
    }

    public boolean isResponseStreaming() {
        return this.responseStreaming;
    }

    public boolean isAddCommonParam() {
        return this.addCommonParam;
    }

    public int getCommonParamLevel() {
        return this.commonParamLevel;
    }

    public void setCommonParamLevel(int i2) {
        this.commonParamLevel = i2;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void setExtraInfo(Object obj) {
        this.extraInfo = obj;
    }

    public Object getExtraInfo() {
        return this.extraInfo;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public String getHost() {
        URI uri = getUri();
        if (uri == null) {
            return null;
        }
        return uri.getHost();
    }

    public String getPath() {
        URI uri = getUri();
        if (uri == null) {
            return null;
        }
        return uri.getPath();
    }

    public RetrofitMetrics getMetrics() {
        return this.metrics;
    }

    public void setMetrics(RetrofitMetrics retrofitMetrics) {
        this.metrics = retrofitMetrics;
    }

    public int getQueryFilterPriority() {
        return this.queryFilterPriority;
    }

    public void setQueryFilterPriority(int i2) {
        this.queryFilterPriority = i2;
    }

    public boolean isQueryEncryptEnabled() {
        return this.isQueryEncryptEnabled;
    }

    public void setQueryEncryptEnabled(boolean z) {
        this.isQueryEncryptEnabled = z;
    }

    public boolean isBodyEncryptEnabled() {
        return this.isBodyEncryptEnabled;
    }

    public void setBodyEncryptEnabled(boolean z) {
        this.isBodyEncryptEnabled = z;
    }

    private URI getUri() {
        if (OptConfig.isEnableRequestOpt()) {
            if (this.uri == null) {
                synchronized (this) {
                    if (this.uri == null) {
                        this.uri = safeCreateUri(this.url);
                    }
                }
            }
            return this.uri;
        }
        return safeCreateUri(this.url);
    }

    private static URI safeCreateUri(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            try {
                return new URI(str);
            } catch (URISyntaxException unused) {
                return URI.create(str.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            }
        } catch (Exception unused2) {
            return createUriWithOutQuery(str);
        }
    }

    private static URI createUriWithOutQuery(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            int indexOf = str.indexOf("?");
            if (indexOf > 0) {
                str = str.substring(0, indexOf);
            }
            return URI.create(str);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public Object tag() {
        return tag(Object.class);
    }

    public <T> T tag(Class<? extends T> cls) {
        Map<Class<?>, Object> map = this.tags;
        if (map == null) {
            return null;
        }
        return cls.cast(map.get(cls));
    }

    public boolean isPureRequest() {
        List<Header> headers = headers(KEY_BYPASS_TTNET_FEATURES);
        return headers != null && headers.size() > 0 && "1".equals(headers.get(0).getValue());
    }

    public boolean isBypassDispatch() {
        List<Header> headers = headers(KEY_BYPASS_DISPATCH);
        return headers != null && headers.size() > 0 && "1".equals(headers.get(0).getValue());
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        boolean addCommonParam;
        TypedOutput body;
        int commonParamLevel;
        Object extraInfo;
        List<Header> headers;
        boolean isBodyEncryptEnabled;
        boolean isQueryEncryptEnabled;
        int maxLength;
        String method;
        RetrofitMetrics metrics;
        int priorityLevel;
        RequestBody requestBody;
        int requestPriorityLevel;
        boolean responseStreaming;
        String serviceType;
        Map<Class<?>, Object> tags;
        String url;

        public Builder() {
            this.method = "GET";
        }

        Builder(Request request) {
            this.method = request.method;
            this.url = request.url;
            LinkedList linkedList = new LinkedList();
            this.headers = linkedList;
            linkedList.addAll(request.headers);
            this.body = request.body;
            this.requestBody = request.requestBody;
            this.priorityLevel = request.priorityLevel;
            this.requestPriorityLevel = request.requestPriorityLevel;
            this.responseStreaming = request.responseStreaming;
            this.maxLength = request.maxLength;
            this.addCommonParam = request.addCommonParam;
            this.commonParamLevel = request.commonParamLevel;
            this.extraInfo = request.extraInfo;
            this.serviceType = request.serviceType;
            this.metrics = request.metrics;
            this.tags = request.tags;
            this.isQueryEncryptEnabled = request.isQueryEncryptEnabled;
            this.isBodyEncryptEnabled = request.isBodyEncryptEnabled;
        }

        public Builder get() {
            return method("GET", null);
        }

        public Builder head() {
            return method(HttpMethodContrants.HEAD, null);
        }

        public Builder post(TypedOutput typedOutput) {
            return method("POST", typedOutput);
        }

        public Builder delete(TypedOutput typedOutput) {
            return method(HttpMethodContrants.DELETE, typedOutput);
        }

        public Builder put(TypedOutput typedOutput) {
            return method(HttpMethodContrants.PUT, typedOutput);
        }

        public Builder patch(TypedOutput typedOutput) {
            return method(HttpMethodContrants.PATCH, typedOutput);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [com.bytedance.retrofit2.mime.TypedOutput] */
        /* JADX WARN: Type inference failed for: r4v2, types: [com.bytedance.retrofit2.mime.TypedOutput] */
        /* JADX WARN: Type inference failed for: r4v3, types: [com.bytedance.retrofit2.mime.FormUrlEncodedTypedOutput] */
        public Builder method(String str, TypedOutput typedOutput) {
            Objects.requireNonNull(str, "method == null");
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (typedOutput != 0 && !Utils.permitsRequestBody(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
            if (typedOutput == 0 && Utils.requiresRequestBody(str)) {
                typedOutput = new FormUrlEncodedTypedOutput();
                typedOutput.addField("body", "null");
            }
            this.method = str;
            this.body = typedOutput;
            return this;
        }

        public Builder url(String str) {
            Objects.requireNonNull(str, "url == null");
            this.url = str;
            return this;
        }

        public Builder headers(List<Header> list) {
            this.headers = list;
            return this;
        }

        public Builder priorityLevel(int i2) {
            this.priorityLevel = i2;
            return this;
        }

        public Builder requestPriorityLevel(int i2) {
            this.requestPriorityLevel = i2;
            return this;
        }

        public Builder serviceType(String str) {
            this.serviceType = str;
            return this;
        }

        public Builder responseStreaming(boolean z) {
            this.responseStreaming = z;
            return this;
        }

        public Builder maxLength(int i2) {
            this.maxLength = i2;
            return this;
        }

        public Builder setExtraInfo(Object obj) {
            this.extraInfo = obj;
            return this;
        }

        public Builder tag(Object obj) {
            return tag(Object.class, obj);
        }

        public <T> Builder tag(Class<? super T> cls, T t) {
            Objects.requireNonNull(cls, "type == null");
            if (this.tags == null) {
                this.tags = new HashMap();
            }
            if (t == null) {
                this.tags.remove(cls);
            } else {
                this.tags.put(cls, cls.cast(t));
            }
            return this;
        }

        public Request build() {
            if (this.url == null) {
                throw new IllegalStateException("url == null");
            }
            return new Request(this);
        }
    }
}
