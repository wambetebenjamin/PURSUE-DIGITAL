package com.bytedance.react.framework.reactnativefs;

import com.facebook.react.bridge.ReadableMap;
import java.net.URL;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class UploadParams {
    public boolean binaryStreamOnly;
    public ReadableMap fields;
    public ArrayList<ReadableMap> files;
    public ReadableMap headers;
    public String method;
    public String name;
    public onUploadBegin onUploadBegin;
    public onUploadComplete onUploadComplete;
    public onUploadProgress onUploadProgress;
    public URL src;

    /* loaded from: classes2.dex */
    public interface onUploadBegin {
        void onUploadBegin();
    }

    /* loaded from: classes2.dex */
    public interface onUploadComplete {
        void onUploadComplete(UploadResult uploadResult);
    }

    /* loaded from: classes2.dex */
    public interface onUploadProgress {
        void onUploadProgress(int i2, int i3);
    }
}
