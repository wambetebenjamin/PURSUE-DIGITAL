package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.react.common.ReactConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public class Inspector {
    private final HybridData mHybridData;

    /* loaded from: classes8.dex */
    public interface RemoteConnection {
        void onDisconnect();

        void onMessage(String str);
    }

    private native LocalConnection connectNative(int i2, RemoteConnection remoteConnection);

    private native Page[] getPagesNative();

    private static native Inspector instance();

    static {
        ReactBridge.staticInit();
    }

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (UnsatisfiedLinkError e2) {
            FLog.e(ReactConstants.TAG, "Inspector doesn't work in open source yet", e2);
            return Collections.emptyList();
        }
    }

    public static LocalConnection connect(int i2, RemoteConnection remoteConnection) {
        try {
            return instance().connectNative(i2, remoteConnection);
        } catch (UnsatisfiedLinkError e2) {
            FLog.e(ReactConstants.TAG, "Inspector doesn't work in open source yet", e2);
            throw new RuntimeException(e2);
        }
    }

    private Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    /* loaded from: classes8.dex */
    public static class Page {
        private final int mId;
        private final String mTitle;
        private final String mVM;

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getVM() {
            return this.mVM;
        }

        public String toString() {
            return "Page{mId=" + this.mId + ", mTitle='" + this.mTitle + "'}";
        }

        private Page(int i2, String str, String str2) {
            this.mId = i2;
            this.mTitle = str;
            this.mVM = str2;
        }
    }

    /* loaded from: classes8.dex */
    public static class LocalConnection {
        private final HybridData mHybridData;

        public native void disconnect();

        public native void sendMessage(String str);

        private LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }
    }
}
