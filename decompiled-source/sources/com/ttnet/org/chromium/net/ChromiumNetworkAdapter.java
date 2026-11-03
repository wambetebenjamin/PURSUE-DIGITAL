package com.ttnet.org.chromium.net;

import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
/* loaded from: classes9.dex */
public final class ChromiumNetworkAdapter {
    private ChromiumNetworkAdapter() {
    }

    public static URLConnection openConnection(URL url, NetworkTrafficAnnotationTag networkTrafficAnnotationTag) {
        return url.openConnection();
    }

    public static URLConnection openConnection(URL url, Proxy proxy, NetworkTrafficAnnotationTag networkTrafficAnnotationTag) {
        return url.openConnection(proxy);
    }

    public static InputStream openStream(URL url, NetworkTrafficAnnotationTag networkTrafficAnnotationTag) {
        return url.openStream();
    }
}
