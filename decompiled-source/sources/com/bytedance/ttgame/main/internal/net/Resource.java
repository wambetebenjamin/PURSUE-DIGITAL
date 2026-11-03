package com.bytedance.ttgame.main.internal.net;

import com.bytedance.ttgame.framework.module.util.Objects;
/* loaded from: classes3.dex */
public class Resource<T> {
    public final T data;
    public final String logId;
    public final String message;
    public final String requestUrl;
    public final Status status;
    public final Throwable throwable;

    /* loaded from: classes3.dex */
    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    public String toString() {
        return "Resource{status=" + this.status + ", data=" + this.data + ", message='" + this.message + "', logId=" + this.logId + '}';
    }

    private Resource(Status status, T t, String str, String str2) {
        this.status = status;
        this.data = t;
        this.message = str;
        this.logId = str2;
        this.throwable = null;
        this.requestUrl = null;
    }

    private Resource(Status status, T t, String str, String str2, Throwable th, String str3) {
        this.status = status;
        this.data = t;
        this.message = str;
        this.logId = str2;
        this.throwable = th;
        this.requestUrl = str3;
    }

    public static <T> Resource<T> success(T t) {
        return new Resource<>(Status.SUCCESS, t, null, null);
    }

    public static <T> Resource<T> success(T t, String str) {
        return new Resource<>(Status.SUCCESS, t, null, str);
    }

    public static <T> Resource<T> error(String str, T t) {
        return new Resource<>(Status.ERROR, t, str, null);
    }

    public static <T> Resource<T> error(String str, T t, String str2) {
        return new Resource<>(Status.ERROR, t, str, str2);
    }

    public static <T> Resource<T> error(String str, T t, String str2, Throwable th, String str3) {
        return new Resource<>(Status.ERROR, t, str, str2, th, str3);
    }

    public static <T> Resource<T> loading(T t) {
        return new Resource<>(Status.LOADING, t, null, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Resource resource = (Resource) obj;
        return this.status == resource.status && Objects.equals(this.data, resource.data) && Objects.equals(this.message, resource.message) && Objects.equals(this.logId, resource.logId);
    }

    public int hashCode() {
        return Objects.hash(this.status, this.data, this.message, this.logId);
    }
}
