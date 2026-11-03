package com.ttnet.org.chromium.net.urlconnection;

import com.ttnet.org.chromium.net.ExperimentalCronetEngine;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Objects;
/* loaded from: classes3.dex */
public class CronetURLStreamHandlerFactory implements URLStreamHandlerFactory {
    private final ExperimentalCronetEngine mCronetEngine;

    public CronetURLStreamHandlerFactory(ExperimentalCronetEngine experimentalCronetEngine) {
        Objects.requireNonNull(experimentalCronetEngine, "CronetEngine is null.");
        this.mCronetEngine = experimentalCronetEngine;
    }

    @Override // java.net.URLStreamHandlerFactory
    public URLStreamHandler createURLStreamHandler(String str) {
        if ("http".equals(str) || "https".equals(str)) {
            return new CronetHttpURLStreamHandler(this.mCronetEngine);
        }
        return null;
    }
}
