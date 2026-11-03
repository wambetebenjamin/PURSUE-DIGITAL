package com.bytedance.falconx.utils;
/* compiled from: MimeUtils.java */
/* loaded from: classes2.dex */
public class b {
    public static String a(String str) {
        return str.endsWith(".js") ? "application/x-javascript" : str.endsWith(".json") ? "application/json" : str.endsWith(".css") ? "text/css" : str.endsWith(".html") ? "text/html" : str.endsWith(".ico") ? "image/x-icon" : (str.endsWith(".jpeg") || str.endsWith(".jpg")) ? "image/jpeg" : str.endsWith(".png") ? "image/png" : str.endsWith(".gif") ? "image/gif" : str.endsWith(".woff") ? "font/woff" : str.endsWith(".svg") ? "image/svg+xml" : str.endsWith(".ttf") ? "font/ttf" : "";
    }
}
